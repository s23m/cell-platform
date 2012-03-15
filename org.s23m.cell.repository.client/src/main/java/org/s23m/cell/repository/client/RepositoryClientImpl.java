/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla private License Version
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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.objectpool.ObjectPool;
import org.s23m.cell.objectpool.ObjectPoolArtifact;
import org.s23m.cell.repository.Repository;
import org.s23m.cell.repository.mediator.RepositoryMediator;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ArtefactContainer.Content;
import org.s23m.cell.serialization.container.ArtefactContainer.SearchResult;
import org.s23m.cell.serialization.container.ContainerTypeMapper;
import org.s23m.cell.serialization.container.ObjectFactory;
import org.s23m.cell.serialization.container.ObjectFactoryHolder;
import org.s23m.cell.serialization.container.SearchResultType;
import org.s23m.cell.serialization.serializer.ProtocolType;
import org.s23m.cell.serialization.serializer.SerializationType;
import org.s23m.cell.serialization.serializer.Serializer;
import org.s23m.cell.serialization.serializer.SerializerHolder;
import org.s23m.cell.statistics.Timer;

public class RepositoryClientImpl implements RepositoryClient {

	static class RepositoryClientHolder {
		private static final RepositoryClient CLIENT = new RepositoryClientImpl();
	}

	public static RepositoryClient getInstance() {
		return RepositoryClientHolder.CLIENT;
	}

	final ObjectPool objectPool;

	private static final Serializer serializer = SerializerHolder.getS23MInstanceSerializer(SerializationType.XML);

	private RepositoryClientImpl() {
		objectPool = RepositoryClientObjectPool.getObjectPool();
	}

	private void buildChangesetNode(final Map<UUID, Set> instanceMap, final ChangesetNode node, final Map<UUID, Set> skipMap) {
		final Set instance = node.getSet();
		skipMap.put(instance.identity().uniqueRepresentationReference(), instance);
		final List<Set> containedInstances = findContentNodes(instanceMap, instance.identity().uniqueRepresentationReference());
		for (final Set i : containedInstances) {
			final ChangesetNode childNode = new ChangesetNode(node, i);
			node.addChildNode(childNode);
			buildChangesetNode(instanceMap, childNode,skipMap);
		}
	}

	private List<String> fetchInstancesFromObjectPool(final List<String> containementTreeUUIDs, final Map<String, String> treeInstances) {
		final List<String> nonObjectPoolUUIDs = new ArrayList<String>();
		for (final String uuid : containementTreeUUIDs) {
			if (objectPool.hasArtifact(uuid)) {
				treeInstances.put(uuid.toString(), (String)objectPool.getArtifact(uuid).getContent());
			} else {
				nonObjectPoolUUIDs.add(uuid);
			}
		}
		return nonObjectPoolUUIDs;
	}

	private  List<Set> findContentNodes(final Map<UUID, Set> instanceMap, final UUID containerUUID) {
		final List<Set> contentNodes = new ArrayList<Set>();
		for (final Entry<UUID, Set> entry : instanceMap.entrySet()) {
			final Set i = entry.getValue();
			if (i.container() != null && i.container().identity().uniqueRepresentationReference().equals(containerUUID)) {
				contentNodes.add(i);
			}
		}
		return contentNodes;
	}

	public ArtefactContainer get(final ArtefactContainer artifact) throws UnsupportedOperationException {
		final SerializationType type = getArtefactContainerType(artifact.getContentType());
		final ObjectFactory containerFactory = 	ObjectFactoryHolder.getInstance();
		ArtefactContainer resultsContainer = containerFactory.createArtefactContainer();

		switch (type) {
		case CONTAINMENT_TREE:
			resultsContainer = retrieveContainmentTree(artifact.getContent().get(0).getContent(),
					Integer.parseInt(artifact.getContent().get(1).getContent()));
			resultsContainer.setContentType(SerializationType.CONTAINMENT_TREE.name());
			break;
		case DEPENDENT_INSTANCES:
			final List<String> dependentUUIDs = getAllDependentInstanceUUIDsOf(artifact.getContent().get(0).getContent());
			resultsContainer.setContentType(SerializationType.DEPENDENT_INSTANCES.name());
			for (final String uuid : dependentUUIDs) {
				final Content content = containerFactory.createArtefactContainerContent();
				content.setContent(uuid);
				resultsContainer.getContent().add(content);
			}
			break;
		case SEARCH_ARGUMENTS:
			final List<SearchResultType> searchResults = searchInstanceByName(artifact.getContent().get(0).getContent());
			resultsContainer.setContentType(SerializationType.SEARCH_ARGUMENTS.name());
			final Iterator<SearchResultType> itr = searchResults.iterator();
			while(itr.hasNext()) {
				final SearchResultType res = itr.next();
				final SearchResult searchRes = ObjectFactoryHolder.getInstance().createArtefactContainerSearchResult();
				searchRes.setContainerIdentity(res.getContainerIdentity());
				searchRes.setInstanceIdentity(res.getInstanceIdentity());
				searchRes.setMetaInstanceIdentity(res.getMetaInstanceIdentity());
				resultsContainer.getSearchResult().add(searchRes);
			}
			break;
		default: throw new UnsupportedOperationException("This method is not yet supported");
		}
		return resultsContainer;
	}

	private List<String> getAllDependentInstanceUUIDsOf(final String uuid) {
		final Repository repository = RepositoryMediator.getInstance().getComponent(ProtocolType.REPOSITORY_ACCESS);
		final ArtefactContainer artifactContainer = ContainerTypeMapper.mapArugmentToArtefactContainerContent(uuid, SerializationType.DEPENDENT_INSTANCE_UUIDS);
		final ArtefactContainer resultsContainer = repository.get(artifactContainer);
		final List<String> results = ContainerTypeMapper.mapContentsToStringList(resultsContainer);
		return results;
	}

	private SerializationType getArtefactContainerType(
			final String contentType) {
		try {
			final SerializationType type = SerializationType.valueOf(contentType);
			return type;
		} catch (final IllegalArgumentException ex){
			throw new UnsupportedOperationException("This type is not supported", ex);
		}
	}

	private List<String> getContentList(final List<Content> contents) {
		final List<String> contentList = new ArrayList<String>();
		for (final Content c : contents) {
			contentList.add(c.getContent());
		}
		return contentList;
	}

	public String getName() {
		return "repository client"; //TODO: to be replaced by the name of a corresponding S23M instance
	}

	public ObjectPool getPool() {
		return this.objectPool;
	}

	private void persistArtifacts(final List<String> artifacts) {
		final Repository repository = RepositoryMediator.getInstance().getComponent(ProtocolType.REPOSITORY_ACCESS);
		final ObjectFactory facto = ObjectFactoryHolder.getInstance();
		repository.put(
				ContainerTypeMapper.mapArugmentListToArtefactContainerContent
				(artifacts, SerializationType.ARTIFACT_PERSISTENCE));
	}

	private void persistChangeset() {
		final Set changeSet = org.s23m.cell.api.Query.changedSets();
		if (changeSet != null && changeSet.size() > 0) {
			final Map<UUID, Set> instanceMap = new HashMap<UUID, Set>();
			final Map<UUID, Set> containerMap = new HashMap<UUID, Set>();
			final Map<UUID, Set> skipMap = new HashMap<UUID, Set>();
			final List<ChangesetNode> nodePaths = new ArrayList<ChangesetNode>();
			final Iterator<Set> setItr = changeSet.iterator();
			while(setItr.hasNext()) {
				final Set changeSetInstance = setItr.next();
				instanceMap.put(changeSetInstance.identity().uniqueRepresentationReference(), changeSetInstance);
				if (changeSetInstance.container() != null) {
					containerMap.put(changeSetInstance.container().identity().uniqueRepresentationReference(), changeSetInstance);
				}
			}
			for (final Entry<UUID, Set> entry : instanceMap.entrySet()) {
				if(!skipMap.containsKey(entry.getKey())) {
					final Set changesetInstance = entry.getValue();
					if(containerMap.containsKey(changesetInstance.identity().uniqueRepresentationReference())) {
						//Check for cases where changesetInstance is a middle node
						if (changesetInstance.container() != null &&
								!instanceMap.containsKey(changesetInstance.container().identity().uniqueRepresentationReference())) {
							final ChangesetNode node = new ChangesetNode(null, changesetInstance);
							buildChangesetNode(instanceMap, node,skipMap);
							nodePaths.add(node);
						}
					} else {
						//Got its container in the map? if not put it the path list
						if (changesetInstance.container() != null &&
								!instanceMap.containsKey(changesetInstance.container().identity().uniqueRepresentationReference())) {
							nodePaths.add(new ChangesetNode(null, changesetInstance));
						}
					}
				}
			}
			//Serialize nodepaths and flick them to the repository
			final Serializer serializer = SerializerHolder.getS23MInstanceSerializer(SerializationType.XML);
			final  List<String> serializedInstances = new ArrayList<String>();
			for (final ChangesetNode node : nodePaths) {
				serializeNodePath(serializer, node, serializedInstances);
			}
			persistArtifacts(serializedInstances);
		}
		//persistObjectPool();
	}

	private void persistInstancesInMemory() {
		final Serializer serializer = SerializerHolder.getS23MInstanceSerializer(SerializationType.XML);
		final List<String> serializedInstances = serializer.serializeAllInstancesInMemory();
		persistArtifacts(serializedInstances);
	}

	private void persistObjectPool() {
		RepositoryClientObjectPool.saveObjectPool();
	}

	public void put(final ArtefactContainer artifact) throws UnsupportedOperationException {
		final SerializationType type = getArtefactContainerType(artifact.getContentType());

		switch (type) {
		case CHANGESET_PERSISTENCE:
			persistChangeset();
			break;
		case IN_MEMORY_PERSISTENCE:
			persistInstancesInMemory();
			break;
		case ARTIFACT_PERSISTENCE:
			final List<String> contents = getContentList(artifact.getContent());
			persistArtifacts(contents);
			break;
		case OBJECT_POOL_PERSISTENCE:
			persistObjectPool();
			break;
		default: throw new UnsupportedOperationException("This method is not yet supported");
		}
	}

	private ArtefactContainer retrieveContainmentTree(final String uuid, final int depth) {
		return retrieveContainmentTreeArtifacts(uuid, depth, true);
	}

	private ArtefactContainer retrieveContainmentTreeArtifacts(final String uuid, final int depth, final boolean isRootTree) {
		final String timerId = "fetching a containment tree";
		Timer.getInstance().start(timerId);
		final Map<String,String> serializedTreeInstances =  new HashMap<String,String>();
		final Repository repository = RepositoryMediator.getInstance().getComponent(ProtocolType.REPOSITORY_ACCESS);
		final ObjectFactory facto = ObjectFactoryHolder.getInstance();
		final ArtefactContainer artifactContainer = facto.createArtefactContainer();
		final Content uuidRetRequest = facto.createArtefactContainerContent();
		artifactContainer.setContentType(SerializationType.CONTAINMENT_TREE_UUIDS_RETRIEVAL.name());
		uuidRetRequest.setContent(uuid);
		artifactContainer.getContent().add(uuidRetRequest);
		//Send a get request (CONTAINMENT_TREE_UUIDS_RETRIEVAL) to the repository access component
		final ArtefactContainer treeUUIDContainer = repository.get(artifactContainer);
		for (final Content c : treeUUIDContainer.getContent()) {
			c.getContent();
		}
		final List<String> containementTreeUUIDs = ContainerTypeMapper.mapContentToStringList(treeUUIDContainer);
		System.err.println("Number containemnt tree members that are part of "+uuid+": "+containementTreeUUIDs.size());
		//find those instances that are in the object pool and fetch those are not from the repository
		final List<String> uuidsToFetchFromRepository = containementTreeUUIDs;//fetchInstancesFromObjectPool(containementTreeUUIDs, serializedTreeInstances);
		System.err.println("# fetched from the objectpool "+serializedTreeInstances.size());
		final ArtefactContainer requestContainer = ObjectFactoryHolder.getInstance().createArtefactContainer();
		if (!uuidsToFetchFromRepository.isEmpty()) {
			requestContainer.setContentType(SerializationType.ARTIFACT_RETRIEVAL.name());
			ContainerTypeMapper.mapToContentList(requestContainer, uuidsToFetchFromRepository);
			final ArtefactContainer responseContainer = repository.get(requestContainer);
			System.err.println("Total # fetched: "+responseContainer.getContent().size());
			final long timeTaken = Timer.getInstance().time(timerId, TimeUnit.MILLISECONDS);
			System.err.println("Time taken to fetch a containment tree (ms): "+timeTaken);
			return responseContainer;
		} else {
			return requestContainer;
		}
	}

	private List<SearchResultType> searchInstanceByName(final String name) {
		final Repository repository = RepositoryMediator.getInstance().getComponent(ProtocolType.REPOSITORY_ACCESS);
		final ObjectFactory facto = ObjectFactoryHolder.getInstance();
		final ArtefactContainer artifactContainer = facto.createArtefactContainer();
		final List<SearchResultType> searchResults = new ArrayList<SearchResultType>();
		artifactContainer.setContentType(SerializationType.SEARCH_ARGUMENTS.name());
		final Content searchArgument = facto.createArtefactContainerContent();
		searchArgument.setContent(name);
		artifactContainer.getContent().add(searchArgument);
		final ArtefactContainer reponseContaner = repository.get(artifactContainer);
		for (final SearchResult s : reponseContaner.getSearchResult()) {
			searchResults.add(s);
		}
		return searchResults;
	}

	private void serializeNodePath(final Serializer serializer, final ChangesetNode node, final List<String> serializedInstances) {
		final Set set = node.getSet();
		if (set.properClass().isEqualTo(S23MKernel.coreGraphs.vertex)) {
			final String content = serializer.serializeInstance(set, false).getContent();
			serializedInstances.add(content);
			final UUID uuid = node.getSet().identity().uniqueRepresentationReference();
			objectPool.addArtifact(uuid.toString(),
					new ObjectPoolArtifact(uuid, S23MKernel.coreGraphs.graph.identity().uniqueRepresentationReference(),
							content));
			for (final ChangesetNode childNode : node.getChildNodes()){
				serializeNodePath(serializer, childNode, serializedInstances);
			}
		}
	}

	public ProtocolType getProtocolType() {
		return ProtocolType.REPOSITORY_CLIENT;
	}

}
