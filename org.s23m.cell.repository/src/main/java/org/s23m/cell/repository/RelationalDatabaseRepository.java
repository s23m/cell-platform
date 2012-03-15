/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is S23M.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.s23m.cell.S23MKernel;
import org.s23m.cell.connector.Component;
import org.s23m.cell.serialization.EdgeType;
import org.s23m.cell.serialization.EdgeType.EdgeEnd;
import org.s23m.cell.serialization.Flavor;
import org.s23m.cell.serialization.S23M;
import org.s23m.cell.serialization.S23M.Instance;
import org.s23m.cell.serialization.SemanticIdType;
import org.s23m.cell.serialization.SuperSetReferenceType;
import org.s23m.cell.serialization.VisibilityType;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ArtefactContainer.Content;
import org.s23m.cell.serialization.container.ContainerTypeMapper;
import org.s23m.cell.serialization.container.InstanceIdentityType;
import org.s23m.cell.serialization.container.ObjectFactory;
import org.s23m.cell.serialization.container.ObjectFactoryHolder;
import org.s23m.cell.serialization.container.SearchResultType;
import org.s23m.cell.serialization.serializer.ProtocolType;
import org.s23m.cell.serialization.serializer.SerializationType;
import org.s23m.cell.serialization.serializer.Serializer;
import org.s23m.cell.serialization.serializer.SerializerHolder;
import org.s23m.cell.statistics.Timer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetlang.channels.Channel;
import org.jetlang.channels.MemoryChannel;
import org.jetlang.core.Callback;
import org.jetlang.fibers.Fiber;
import org.jetlang.fibers.PoolFiberFactory;

// FIXME use named queries instead of constructing them via Strings
// TODO create a DAO to perform queries and lookups
public class RelationalDatabaseRepository implements Repository, Component {

	private static final int MIN_ARGUMENT_SIZE = 1;

	private static final int UPDATE_BATCH_SIZE = Integer.parseInt(ConfigValues.getValue("Repository.UPDATE_BATCH_SIZE"));

	private static final int READ_BATCH_SIZE = Integer.parseInt(ConfigValues.getValue("Repository.READ_BATCH_SIZE"));

	private static final String ROOT_URR = "root";

	private static final int MAX_DEPTH = Integer.parseInt(ConfigValues.getValue("Repository.MAX_DEPTH"));

	private static final int INITIAL_CONNECTION_POOL_SIZE =  Integer.parseInt(ConfigValues.getValue("Repository.INITIAL_CONNECTION_POOL_SIZE"));

	private static final String REPOSITORY_CONNECTION_POOL_ID = ConfigValues.getValue("Repository.RelationalDatabaseRepository_ID");

	private static final int MAX_ACTIVE_CONNECTION = Integer.parseInt(ConfigValues.getValue("Repository.MAX_NUM_ACTIVE_CONNECTION"));

	static class FiberResources {
		public static final ExecutorService EXEC_SERVICE = Executors.newCachedThreadPool(); //should be created externally
		public static final PoolFiberFactory FIBER_FACTORY = new PoolFiberFactory(EXEC_SERVICE);
	}

	static class RepositoryHolder {
		private static boolean isConnectionPoolInitialized = false;
		public static final RelationalDatabaseRepository REPOSITORY = new RelationalDatabaseRepository();
		public static final GenericObjectPool CONNECTION_POOL = new GenericObjectPool(null);
		public static RelationalDatabaseRepository PLUGIN_REPOSITORY = new RelationalDatabaseRepository();

		protected static boolean isConnectionPoolInitialized() {
			return isConnectionPoolInitialized;
		}
		protected static void setConnectionPoolInitialized(final boolean isConnectionPoolInitialized) {
			RepositoryHolder.isConnectionPoolInitialized = isConnectionPoolInitialized;
		}
	}

	private static void closeAllStatement(final List<StatementBatchManager> prepStatementsManagers) throws SQLException {
		for (final StatementBatchManager mangr : prepStatementsManagers) {
			mangr.getStatement().close();
		}
	}

	private static void executeBatchStatements(final List<StatementBatchManager> prepStatementsManagers, final Connection connection) throws SQLException {
		final Fiber fiber = FiberResources.FIBER_FACTORY.create();
		fiber.start();

		final Channel<List<StatementBatchManager>> msgChannel = new MemoryChannel<List<StatementBatchManager>>();

		final Callback<List<StatementBatchManager>> callBack = new Callback<List<StatementBatchManager>>() {
			public void onMessage(final List<StatementBatchManager> statementManagers) {
				final String timerId = UUID.randomUUID().toString();
				Timer.getInstance().start(timerId);
				try {
					for (final StatementBatchManager mangr : statementManagers) {
						mangr.commitStatementBatches();
					}
					connection.commit();
					closeAllStatement(prepStatementsManagers);
					connection.close();
					Timer.getInstance().time(timerId, TimeUnit.MILLISECONDS);
					final long timeTaken = Timer.getInstance().getTimerRecords(timerId).getTimeTakenInMilliseconds();
					System.err.println("Time taken: "+timeTaken); //$NON-NLS-1$
					fiber.dispose();
				} catch (final SQLException ex) {
					ex.printStackTrace();
				}
			}
		};

		msgChannel.subscribe(fiber, callBack);
		msgChannel.publish(prepStatementsManagers);
	}

	public static RelationalDatabaseRepository getRepository() {
		if (!RepositoryHolder.isConnectionPoolInitialized()) {
			initializeConnectionPool();
		}
		return RepositoryHolder.REPOSITORY;
	}

	private static void initializeConnectionPool() {
		final Properties props = new Properties();
		props.setProperty("user", ConfigValues.getValue("Repository.REPOSITORY_ACCOUNT"));
		props.setProperty("password", ConfigValues.getValue("Repository.REPOSITORY_PW"));
		props.setProperty("rewriteBatchedStatements", "true");
		final ConnectionFactory  cf =  new DriverManagerConnectionFactory(ConfigValues.getValue("Repository.REPOSITORY_CONNECTION_STRING"), props);
		final KeyedObjectPoolFactory keyedObjecPoolFacto =new GenericKeyedObjectPoolFactory(null, MAX_ACTIVE_CONNECTION);
		new PoolableConnectionFactory(cf, RepositoryHolder.CONNECTION_POOL, keyedObjecPoolFacto, null, false, true);
		for(int i = 0; i < INITIAL_CONNECTION_POOL_SIZE; i++) {
			try {
				RepositoryHolder.CONNECTION_POOL.addObject();
			} catch (final Exception e) {}
		}
		new PoolingDriver().registerPool(REPOSITORY_CONNECTION_POOL_ID, RepositoryHolder.CONNECTION_POOL);
		RepositoryHolder.setConnectionPoolInitialized(true);
	}


	public static void shutDownAsynchServices() {
		FiberResources.FIBER_FACTORY.dispose();
		FiberResources.EXEC_SERVICE.shutdown();
	}

	private final Serializer serializer;

	private SessionFactory factory;

	private RelationalDatabaseRepository() {
		serializer = SerializerHolder.getS23MInstanceSerializer(SerializationType.XML);
	}

	private RelationalDatabaseRepository(final Configuration config) {
		this();
		updateConfiguration(config);
	}

	private void addMapItemsToContentList(final ObjectFactory containerFactory, final ArtefactContainer resultContainer,
			final Map<String, String> resultEntries) {
		for (final Map.Entry<String, String> entry : resultEntries.entrySet()) {
			final Content content = containerFactory.createArtefactContainerContent();
			content.setContent(entry.getValue());
			content.setId(entry.getKey());
			resultContainer.getContent().add(content);
		}
	}


	private void addUUIDs(final ResultSet rs, final ListOrderedMap uuidMap) throws SQLException {
		if (!uuidMap.containsKey(ROOT_URR)) {
			final String rootUrr = rs.getString(ROOT_URR); //$NON-NLS-1$
			uuidMap.put(rootUrr, rootUrr);
		}
		for (int n = 1; n <= MAX_DEPTH; n++) {
			final String urr = rs.getString("lev"+n); //$NON-NLS-1$
			if (urr != null) {
				if (!uuidMap.containsKey(urr)) {
					uuidMap.put(urr, urr);
				}
			} else {
				break;
			}
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private Map<String,String> createArtefactUUIDMap(final List<String> artefactIds) {
		final Map<String,String> map = new HashMap<String,String>();
		for (final String uuid : artefactIds) {
			map.put(uuid.toString(), uuid.toString());
		}
		return map;
	}


	private CallableStatement execueteUUIDQuery(final String queryName, final String uuid) throws SQLException {
		final Connection connection = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+REPOSITORY_CONNECTION_POOL_ID); //$NON-NLS-1$
		connection.setAutoCommit(false);
		final CallableStatement stmt = connection.prepareCall("{ call "+queryName+"(?) }",ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); //$NON-NLS-1$
		stmt.setString(1, uuid);
		stmt.setFetchSize(Integer.MIN_VALUE);
		stmt.execute();
		returnConnectionToPool(connection);
		return stmt;
	}

	private void executeAllStatements(final List<StatementBatchManager> prepStatementsManagers)	throws SQLException {
		for (final StatementBatchManager mangr : prepStatementsManagers) {
			try {
				mangr.commitStatementBatches();
			} catch (final Throwable th) {
				throw new SQLException("Statement batch execution failed", th);
			}
		}
	}

	private ListOrderedMap fetchArtifacts(final Map uuidsToFetch, final StatementBatchManager fetchXmlStatementMan) throws SQLException {
		for (final Object entryObject : uuidsToFetch.entrySet()) {
			final Entry entry = (Entry) entryObject;
			final String xmlKey = (String) entry.getKey();
			fetchXmlStatementMan.addToURRList(xmlKey);
		}
		final ListOrderedMap fetchedArtifacts = new ListOrderedMap();
		fetchXmlStatementMan.executeSelectQueries(fetchedArtifacts);
		return fetchedArtifacts;
	}

	public ArtefactContainer get(final ArtefactContainer artifact) throws UnsupportedOperationException {
		validateGetArguments(artifact);
		final SerializationType type = getArtefactContainerType(artifact.getContentType());
		final ObjectFactory containerFactory = 	ObjectFactoryHolder.getInstance();
		final ArtefactContainer responseContainer = containerFactory.createArtefactContainer();
		switch (type) {
		case ARTIFACT_RETRIEVAL:
			final List<String> artifactsToStore = getContentList(artifact.getContent());
			final Map<String,String> returnedArtifacts = get(artifactsToStore);
			responseContainer.setContentType(SerializationType.ARTIFACT_RETRIEVAL.name());
			addMapItemsToContentList(containerFactory, responseContainer, returnedArtifacts);
			break;
		case CONTAINMENT_TREE:
			final Map<String,String> treeArtifacts = getContainmentTree(artifact.getContent().get(0).getContent(), MAX_DEPTH);
			responseContainer.setContentType(SerializationType.CONTAINMENT_TREE.name());
			addMapItemsToContentList(containerFactory, responseContainer, treeArtifacts);
			break;
		case CONTAINMENT_TREE_UUIDS_RETRIEVAL:
			final List<String> treeUUIDs = getContainmentTreeUUIDs(artifact.getContent().get(0).getContent(), MAX_DEPTH);
			responseContainer.setContentType(SerializationType.CONTAINMENT_TREE_UUIDS_RETRIEVAL.name());
			ContainerTypeMapper.mapToContentList(responseContainer, treeUUIDs);
			break;
		case DEPENDENT_INSTANCE_UUIDS:
			final List<String> dependentUUIDs = getDependentInstanceUUIDsOf(artifact.getContent().get(0).getContent());
			responseContainer.setContentType(SerializationType.DEPENDENT_INSTANCE_UUIDS.name());
			ContainerTypeMapper.mapToContentList(responseContainer, dependentUUIDs);
			break;
		case SEARCH_ARGUMENTS:
			final List<SearchResultType> searchResults = search(artifact.getContent().get(0).getContent());
			responseContainer.setContentType(SerializationType.SEARCH_ARGUMENTS.name());
			responseContainer.getSearchResult().addAll(ContainerTypeMapper.mapSearchResultTypeListToSearchResultList(searchResults));
			break;
		default: throw new UnsupportedOperationException("This method is not yet supported");
		}
		return responseContainer;
	}

	@SuppressWarnings("unchecked")
	protected Map<String,String> get(final List<String> artefactIds) throws IllegalStateException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+REPOSITORY_CONNECTION_POOL_ID); //$NON-NLS-1$
			connection.setAutoCommit(false);

			final String fetchXmlString = "select urr, contentAsXml from artifact where urr = ?";
			final StatementBatchManager fetchXmlStatementMan = new StatementBatchManager(connection.prepareStatement(fetchXmlString),"artifact","", "", UPDATE_BATCH_SIZE, READ_BATCH_SIZE);
			final ListOrderedMap fetchedArtifacts = fetchArtifacts(createArtefactUUIDMap(artefactIds), fetchXmlStatementMan);
			fetchXmlStatementMan.getStatement().close();
			return fetchedArtifacts;
		} catch (final SQLException ex) {
			throw new IllegalStateException("SQL exception", ex); //$NON-NLS-1$
		} finally {
			returnConnectionToPool(connection);
		}
	}

	private SerializationType getArtefactContainerType(final String contentType) {
		try {
			final SerializationType type = SerializationType.valueOf(contentType);
			return type;
		} catch (final IllegalArgumentException ex){
			throw new UnsupportedOperationException("This type is not supported", ex);
		}
	}


	@SuppressWarnings("unchecked")
	protected Map<String, String> getContainmentTree(final String uuid, final int depth) throws IllegalStateException {
		CallableStatement fetchContainedInstanceProc = null;
		final PreparedStatement preStmt = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+REPOSITORY_CONNECTION_POOL_ID); //$NON-NLS-1$
			connection.setAutoCommit(false);
			fetchContainedInstanceProc = connection.prepareCall("{ call getContainedInstances(?) }",ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); //$NON-NLS-1$
			fetchContainedInstanceProc.setString(1, uuid);
			fetchContainedInstanceProc.setFetchSize(Integer.MIN_VALUE);
			final Map uuidsToFetch = getContainmentTreeUUIDs(uuid);
			System.err.println(uuidsToFetch.size());

			final String fetchXmlString = "select urr, contentAsXml from artifact where urr = ?";
			final StatementBatchManager fetchXmlStatementMan = new StatementBatchManager(connection.prepareStatement(fetchXmlString),"artifact","", "", UPDATE_BATCH_SIZE, READ_BATCH_SIZE);

			final ListOrderedMap fetchedArtifacts = fetchArtifacts(
					uuidsToFetch, fetchXmlStatementMan);
			fetchXmlStatementMan.getStatement().close();
			return fetchedArtifacts;
		} catch (final SQLException ex) {
			throw new IllegalStateException("SQL exception", ex); //$NON-NLS-1$
		} finally {
			returnConnectionToPool(connection);
		}
	}


	@SuppressWarnings("unchecked")
	private Map getContainmentTreeUUIDs(final String uuid) throws SQLException {
		final Connection connection = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+REPOSITORY_CONNECTION_POOL_ID); //$NON-NLS-1$
		connection.setAutoCommit(false);
		final CallableStatement fetchContainedInstanceUUIDs = execueteUUIDQuery("getContainedInstanceUUIDs",uuid);
		Timer.getInstance().start("getContainmentTreeUUIDs"); //$NON-NLS-1$
		final ResultSet rs =fetchContainedInstanceUUIDs.getResultSet();
		final ListOrderedMap uuidMap = new ListOrderedMap();
		while (rs.next()) {
			addUUIDs(rs, uuidMap);
		}
		System.err.println("Time taken for doing getContainmentTreeUUIDs: "+Timer.getInstance().time("getContainmentTreeUUIDs", TimeUnit.MILLISECONDS)); //$NON-NLS-1$
		rs.close();
		fetchContainedInstanceUUIDs.close();
		returnConnectionToPool(connection);
		return uuidMap;
	}

	private void returnConnectionToPool(final Connection connection) {
		if (connection != null) {
			try {
				RepositoryHolder.CONNECTION_POOL.returnObject(connection);
			} catch (final Throwable e) {
				Logger.getLogger("global").log(Level.SEVERE, "Connection object is not returned to the pool", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}


	@SuppressWarnings("unchecked")
	protected List<String> getContainmentTreeUUIDs(final String artefactUUID, final int depth) throws IllegalStateException {
		try {
			final Map<String,String> uuidMap = getContainmentTreeUUIDs(artefactUUID);
			final List<String> uuidStringList = new ArrayList<String>(uuidMap.values());
			return uuidStringList;
		} catch (final SQLException ex) {
			throw new IllegalStateException("Containment tree UUID fetching failed", ex);
		}
	}


	private List<String> getContentList(final List<Content> contents) {
		final List<String> contentList = new ArrayList<String>();
		for (final Content c : contents) {
			contentList.add(c.getContent());
		}
		return contentList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getDependentInstanceUUIDs(final String uuid) throws SQLException {
		final CallableStatement fetchContainedInstanceUUIDs = execueteUUIDQuery("getDependentInstanceUUIDs",uuid);
		Timer.getInstance().start("getDependentInstanceUUIDs"); //$NON-NLS-1$
		final ResultSet rs =fetchContainedInstanceUUIDs.getResultSet();
		final ListOrderedMap uuidMap = new ListOrderedMap();
		while (rs.next()) {
			final String urr = rs.getString("urr");
			if (urr != null && !uuidMap.containsKey(urr)) {
				uuidMap.put(urr, urr);
			}
		}
		System.err.println("Time taken for doing getDependentInstanceUUIDs: "+Timer.getInstance().time("getDependentInstanceUUIDs", TimeUnit.MILLISECONDS)); //$NON-NLS-1$
		rs.close();
		fetchContainedInstanceUUIDs.close();
		return uuidMap;
	}


	protected List<String> getDependentInstanceUUIDsOf(final String uuid) throws IllegalStateException {
		try {
			return mergeUUIDMapsToList(uuid, getDependentInstanceUUIDs(uuid), null);
		} catch (final SQLException ex) {
			throw new IllegalStateException("Failed to fetch dependent instance UUIDs",ex);
		}
	}

	private String getQueryString(final String s) {
		return s.substring(s.indexOf(":")+1).trim();
	}

	private String getSearchQueryString() {
		return "SELECT tId.uuid as uuid, tId.name as name, tId.pluralName as pluralName, tArtifact.container as containerId,"+
				"(SELECT  tCId.name FROM  `identity` as tCId WHERE tCId.uuid  = containerId) as containerName,"+
				"(SELECT  tCId.pluralName FROM  `identity` as tCId WHERE tCId.uuid  = containerId) as containerPluralName,"+
				"tArtifact.category as metaElementId,"+
				"(SELECT  tMId.name FROM  `identity` as tMId WHERE tMId.uuid  = metaElementId) as metaElementName "+
				"FROM  `identity` as tId, artifact as tArtifact "+
				"WHERE (tId.uuid = tArtifact.urr  AND tId.uuid  IN "+
				"(SELECT identityTable.uuid "+
				"FROM `identity` as identityTable, artifact as artifactTable "+
				"WHERE "+
				"(identityTable.uuid = artifactTable.urr AND  (artifactTable.flavor='"+Flavor.VER.name()+"' OR artifactTable.flavor='"+Flavor.EDG.name()+"') AND identityTable.name LIKE  ?))) LIMIT 100";
	}

	@SuppressWarnings("unchecked")
	private List<String> mergeUUIDMapsToList(final String uuid, final Map map1, final Map map2) {
		final List<String> uuidLst = new ArrayList<String>();
		if (map2 != null) {
			for (final Object k : map2.keySet()) {
				map1.put(k, k);
			}
		}
		map1.remove(uuid);
		CollectionUtils.collect(
				new ArrayList<Object>(map1.values()),
				new ObjectToStringTransformer(),
				uuidLst);
		return uuidLst;
	}

	private void persistArtifact(final StatementBatchManager idInsertStatementMan,
			final StatementBatchManager artifactInsertStatementMan,
			final Connection connection,
			final SemanticIdType id, final String metaElement, final String container, final String isAbstract, final Flavor flavor, final String xmlContent) throws SQLException {

		final String payload = id.getPayload() == null ? "" : id.getPayload(); //$NON-NLS-1$

		setUpIdInsertStatement(idInsertStatementMan.getStatement(),
				id.getUniqueRepresentationReference(),
				id.getName(),
				id.getPluralName(),
				payload);
		idInsertStatementMan.addToBatch(getQueryString(idInsertStatementMan.getStatement().toString()));

		setUpArtifactInsertStatement(artifactInsertStatementMan.getStatement(),
				id.getUniqueRepresentationReference(),
				id.getIdentifier(),
				metaElement,
				container,
				isAbstract,
				flavor.name(),
				xmlContent);
		artifactInsertStatementMan.addToBatch(getQueryString(artifactInsertStatementMan.getStatement().toString()));

	}

	private void persisteEdge(final StatementBatchManager edgeInsertStatementMan, final EdgeType e, final EdgeEnd fromEdgeEnd, final EdgeEnd toEdgeEnd) throws SQLException {
		edgeInsertStatementMan.getStatement().setString(1, e.getSemanticIdentity().getUniqueRepresentationReference());
		edgeInsertStatementMan.getStatement().setString(2, fromEdgeEnd.getMinCardinality());
		edgeInsertStatementMan.getStatement().setString(3, toEdgeEnd.getMinCardinality());
		edgeInsertStatementMan.getStatement().setString(4, fromEdgeEnd.getMaxCardinality());
		edgeInsertStatementMan.getStatement().setString(5, toEdgeEnd.getMaxCardinality());
		edgeInsertStatementMan.getStatement().setString(6, ""+fromEdgeEnd.isIsNavigable()); //$NON-NLS-1$
		edgeInsertStatementMan.getStatement().setString(7, ""+toEdgeEnd.isIsNavigable()); //$NON-NLS-1$
		edgeInsertStatementMan.getStatement().setString(8, ""+fromEdgeEnd.isIsContainer()); //$NON-NLS-1$
		edgeInsertStatementMan.getStatement().setString(9, ""+toEdgeEnd.isIsContainer()); //$NON-NLS-1$
		edgeInsertStatementMan.getStatement().setString(10, fromEdgeEnd.getSemanticIdentity().getUniqueRepresentationReference());
		edgeInsertStatementMan.getStatement().setString(11, toEdgeEnd.getSemanticIdentity().getUniqueRepresentationReference());
		edgeInsertStatementMan.addToBatch(getQueryString(edgeInsertStatementMan.getStatement().toString()));
	}


	private void persistLink(final StatementBatchManager idInsertStatementMan, final StatementBatchManager artifactInsertStatementMan, final StatementBatchManager linkInsertStatementMan,
			final Connection connection, final String category, final String  container, final Flavor flavor, final String payload, final SemanticIdType id, final String src, final String target) throws SQLException {
		persistArtifact(idInsertStatementMan, artifactInsertStatementMan, connection, id,
				category,
				container, "false", flavor, ""); //$NON-NLS-1$ //$NON-NLS-2$
		setUpLinkStatement(linkInsertStatementMan, id.getUniqueRepresentationReference(), flavor,
				category.toString(), src, target);
	}

	public void put(final ArtefactContainer artifact)	throws UnsupportedOperationException {
		final SerializationType type = getArtefactContainerType(artifact.getContentType());
		switch (type) {
		case ARTIFACT_PERSISTENCE:
			final List<String> contentToStore = getContentList(artifact.getContent());
			store(contentToStore);
			break;
		default:
			throw new UnsupportedOperationException("This method is not yet supported");
		}
	}

	protected List<SearchResultType> search(final String queryString) {
		final List<SearchResultType> searchResults = new ArrayList<SearchResultType>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+REPOSITORY_CONNECTION_POOL_ID); //$NON-NLS-1$
			connection.setAutoCommit(false);
			final PreparedStatement searchStatement =  connection.prepareStatement(getSearchQueryString()); //$NON-NLS-1$
			searchStatement.setString(1, "%"+queryString.trim()+"%");
			searchStatement.execute();
			rs =searchStatement.getResultSet();
			final ObjectFactory factory  = ObjectFactoryHolder.getInstance();

			while (rs.next()) {
				final SearchResultType searchResult = factory.createSearchResultType();
				final InstanceIdentityType instanceId = factory.createInstanceIdentityType();
				instanceId.setName(rs.getString("name"));
				instanceId.setPluralName(rs.getString("pluralName"));
				instanceId.setUuid(rs.getString("uuid"));
				searchResult.setInstanceIdentity(instanceId);
				final InstanceIdentityType containerId = factory.createInstanceIdentityType();
				containerId.setName(rs.getString("containerName"));
				containerId.setPluralName(rs.getString("containerPluralName"));
				containerId.setUuid(rs.getString("containerId"));
				searchResult.setContainerIdentity(containerId);
				final InstanceIdentityType metaInstanceId = factory.createInstanceIdentityType();
				metaInstanceId.setUuid(rs.getString("metaElementId"));
				metaInstanceId.setName(rs.getString("metaElementName"));
				searchResult.setMetaInstanceIdentity(metaInstanceId);
				searchResults.add(searchResult);
			}
		} catch (final SQLException ex) {
			throw new IllegalStateException("Error in sql execution: "+ex.getMessage(), ex); //$NON-NLS-1$
		} finally {
			returnConnectionToPool(connection);
		}
		return searchResults;
	}

	private void setUpArtifactInsertStatement(final PreparedStatement artifactInsertStatement, final String urr, final String uuid, final String category,
			final String container, final String isAbstractId, final String flavor, final String xmlContent) throws SQLException {
		artifactInsertStatement.setString(1, urr);
		artifactInsertStatement.setString(2, uuid);
		artifactInsertStatement.setString(3, category);
		artifactInsertStatement.setString(4, container);
		artifactInsertStatement.setString(5, isAbstractId); //isAbstract to be URR
		artifactInsertStatement.setString(6, flavor);
		artifactInsertStatement.setString(7, xmlContent);
	}

	private void setUpIdInsertStatement(final PreparedStatement statement, final String uuid, final String name, final String pluralName, final String payload) throws SQLException {
		statement.setString(1,uuid);
		statement.setString(2, name);
		statement.setString(3,pluralName);
		statement.setString(4, payload);
	}

	private void setUpLinkStatement(final StatementBatchManager linkInsertStatementMan, final String urr, final Flavor flavor,
			final String category, final String sourceInstance, final String targetInstance) throws SQLException {
		linkInsertStatementMan.getStatement().setString(1,urr);
		linkInsertStatementMan.getStatement().setString(2, category);
		linkInsertStatementMan.getStatement().setString(3, flavor.name());
		linkInsertStatementMan.getStatement().setString(4, sourceInstance);
		linkInsertStatementMan.getStatement().setString(5, targetInstance);
		linkInsertStatementMan.addToBatch(getQueryString(linkInsertStatementMan.getStatement().toString()));

	}

	protected void store(final List<String> artefacts) throws IllegalStateException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+REPOSITORY_CONNECTION_POOL_ID); //$NON-NLS-1$
			connection.setAutoCommit(false);

			final List<StatementBatchManager> prepStatementsManagers = new ArrayList<StatementBatchManager>();
			final String identityStatementString = "(?, ?, ?, ?)";
			final PreparedStatement idInsertStatement =  connection.prepareStatement(identityStatementString); //$NON-NLS-1$
			final StatementBatchManager idInsertStatementMan = new StatementBatchManager(idInsertStatement, "identity",
					"insert into identity (uuid,name,pluralName,payLoad) values ",
					" ON DUPLICATE KEY update name=values(name), pluralName=values(pluralName), payLoad=values(payLoad)",UPDATE_BATCH_SIZE, READ_BATCH_SIZE);
			final String artifactStatementString = "(?, ?, ?, ?, ?, ?, ?)";
			final PreparedStatement artifactInsertStatement = connection.prepareStatement(artifactStatementString); //$NON-NLS-1$
			final StatementBatchManager artifactInsertStatementMan = new StatementBatchManager(artifactInsertStatement,"artifact","insert into artifact (urr,uuid,category,container,isAbstractValue,flavor,contentAsXml) values ",
					" ON DUPLICATE KEY update uuid=values(uuid), category=values(category), container=values(container), isAbstractValue=values(isAbstractValue), flavor=values(flavor), contentAsXml=values(contentAsXml)",UPDATE_BATCH_SIZE, READ_BATCH_SIZE);
			final String linkStatementString = "(?, ?, ?, ?, ?)";
			final PreparedStatement linkInsertStatement = connection.prepareStatement(linkStatementString); //$NON-NLS-1$
			final StatementBatchManager linkInsertStatementMan = new StatementBatchManager(linkInsertStatement,"link","insert into link (urr,category,flavor,fromArtifact,toArtifact) values ",
					" ON DUPLICATE KEY update category=values(category), flavor=values(flavor), fromArtifact=values(fromArtifact), toArtifact=values(toArtifact)", UPDATE_BATCH_SIZE, READ_BATCH_SIZE);
			final String edgeStatementString = "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			final PreparedStatement edgeInsertStatement = connection.prepareStatement(edgeStatementString); //$NON-NLS-1$
			final StatementBatchManager edgeInsertStatementMan = new StatementBatchManager(edgeInsertStatement,"edge",
					"insert into edge (urr,minCardinalityValueFromEdgeEnd,minCardinalityValueToEdgeEnd,maxCardinalityValueFromEdgeEnd,maxCardinalityValueToEdgeEnd,isNavigableValueFromEdgeEnd,isNavigableValueToEdgeEnd,isContainerValueFromEdgeEnd,isContainerValueToEdgeEnd,fromEdgeEnd,toEdgeEnd)values ",
					" ON DUPLICATE KEY update minCardinalityValueFromEdgeEnd=values(minCardinalityValueFromEdgeEnd), minCardinalityValueToEdgeEnd=values(minCardinalityValueToEdgeEnd) ,maxCardinalityValueFromEdgeEnd=values(maxCardinalityValueFromEdgeEnd), maxCardinalityValueToEdgeEnd=values(maxCardinalityValueToEdgeEnd), isNavigableValueFromEdgeEnd=values(isNavigableValueFromEdgeEnd), isNavigableValueToEdgeEnd=values(isNavigableValueToEdgeEnd), isContainerValueFromEdgeEnd=values(isContainerValueFromEdgeEnd), isContainerValueToEdgeEnd=values(isContainerValueToEdgeEnd), fromEdgeEnd=values(fromEdgeEnd), toEdgeEnd=values(toEdgeEnd)",
					UPDATE_BATCH_SIZE, READ_BATCH_SIZE);

			for (final String artefact : artefacts) {
				final S23M model = serializer.unmarshallModel(artefact);
				final Instance root = model.getInstance().get(0);

				persistArtifact(idInsertStatementMan, artifactInsertStatementMan,connection, root.getSemanticIdentity(),
						root.getMetaElement(), root.getArtifact(), ""+root.isIsAbstract(), Flavor.VER, artefact); //$NON-NLS-1$

				if (root.getLink() != null) {
					for (final Object link : root.getLink().getVisibilityAndEdgeAndEdgeTrace()) {
						if (link instanceof VisibilityType) {
							final VisibilityType v = (VisibilityType) link;
							persistLink(idInsertStatementMan, artifactInsertStatementMan, linkInsertStatementMan, connection, S23MKernel.coreGraphs.visibility.identity().uniqueRepresentationReference().toString(),
									root.getSemanticIdentity().getUniqueRepresentationReference(), Flavor.VIS, null, v.getSemanticIdentity(), v.getSourceInstance(), v.getTargetInstance());
						} else if (link instanceof SuperSetReferenceType) {
							final SuperSetReferenceType s = (SuperSetReferenceType) link;
							persistLink(idInsertStatementMan, artifactInsertStatementMan, linkInsertStatementMan, connection, S23MKernel.coreGraphs.superSetReference.identity().uniqueRepresentationReference().toString(),
									root.getSemanticIdentity().getUniqueRepresentationReference(), Flavor.SUP, null, s.getSemanticIdentity(),  s.getSubSetInstance(), s.getSuperSetInstance());
						} else if (link instanceof EdgeType) {
							final EdgeType e = (EdgeType) link;
							final EdgeEnd fromEdgeEnd = e.getEdgeEnd().get(0);
							final EdgeEnd toEdgeEnd = e.getEdgeEnd().get(1);

							persistArtifact(idInsertStatementMan, artifactInsertStatementMan, connection, fromEdgeEnd.getSemanticIdentity(),
									fromEdgeEnd.getMetaElement(), root.getSemanticIdentity().getUniqueRepresentationReference(), ""+root.isIsAbstract(), Flavor.END, ""); //$NON-NLS-1$ //$NON-NLS-2$
							persistArtifact(idInsertStatementMan, artifactInsertStatementMan, connection, toEdgeEnd.getSemanticIdentity(),
									toEdgeEnd.getMetaElement(), root.getSemanticIdentity().getUniqueRepresentationReference(), ""+root.isIsAbstract(), Flavor.END, null); //$NON-NLS-1$

							persistLink(idInsertStatementMan, artifactInsertStatementMan, linkInsertStatementMan, connection, e.getMetaElement(),
									root.getSemanticIdentity().getUniqueRepresentationReference(), Flavor.EDG, null, e.getSemanticIdentity(),fromEdgeEnd.getInstanceId(), toEdgeEnd.getInstanceId());
							persisteEdge(edgeInsertStatementMan, e, fromEdgeEnd, toEdgeEnd);
						}

					}
				}
			}

			prepStatementsManagers.add(idInsertStatementMan);
			prepStatementsManagers.add(artifactInsertStatementMan);
			prepStatementsManagers.add(linkInsertStatementMan);
			prepStatementsManagers.add(edgeInsertStatementMan);

			System.err.println("Batch execution request is sent"); //$NON-NLS-1$
			final String timerId = UUID.randomUUID().toString();
			Timer.getInstance().start(timerId);
			idInsertStatement.executeQuery("start transaction");
			//executeBatchStatements(prepStatementsManagers,connection); //non-blocking version
			executeAllStatements(prepStatementsManagers);//blocking version
			connection.commit();
			Timer.getInstance().time(timerId, TimeUnit.MILLISECONDS);
			System.err.println("Taken (ms): "+Timer.getInstance().getTimerRecords(timerId).getTimeTakenInMilliseconds());
		} catch (final SQLException ex) {
			try {
				connection.rollback();
				Logger.getLogger("global").log(Level.SEVERE, "Transaction has been rolled back", ex); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (final SQLException e) {}
			throw new IllegalStateException("Error in sql execution: "+ex.getMessage(), ex); //$NON-NLS-1$
		} catch (final IllegalStateException ex) {
			try {
				connection.rollback();
			} catch (final SQLException e) {}
			throw new IllegalStateException("Error in sql execution: "+ex.getMessage(), ex); //$NON-NLS-1$
		} finally {
			returnConnectionToPool(connection);
		}
	}

	/**
	 * Called each time configuration settings changes.
	 * 
	 * @param configuration
	 */
	public void updateConfiguration(final Configuration configuration) {
		if (configuration == null) {
			factory = null;
		} else {
			//add mapping
			//configuration.addClass(Document.class);
			factory = configuration.buildSessionFactory();
		}
	}

	private void validateGetArguments(final ArtefactContainer artifact) {
		if (artifact.getContent().size() < MIN_ARGUMENT_SIZE) {
			throw new UnsupportedOperationException("Ill-formatted get operation");
		}
	}

	public ProtocolType getProtocolType() {
		return ProtocolType.REPOSITORY_ACCESS;
	}

}