package org.s23m.cell.repository;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.s23m.cell.serialization.Gmodel;
import org.s23m.cell.serialization.Gmodel.Instance;
import org.s23m.cell.serialization.serializer.SerializationContent;
import org.s23m.cell.statistics.Timer;
import org.jetlang.channels.Channel;
import org.jetlang.channels.MemoryChannel;
import org.jetlang.core.Callback;
import org.jetlang.fibers.Fiber;
import org.jetlang.fibers.PoolFiberFactory;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

public class RepositoryStats {


	public static void testArtifactTransportation(final List<SerializationContent> artefacts) {
		Connection connection = null;
		final List<PreparedStatement> prepStatements = new ArrayList<PreparedStatement>();

		final CallableStatement storedProc = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/repository?user=root&password=");
			connection.setAutoCommit(false);
			final PreparedStatement idInsertStatement =  (PreparedStatement) connection.prepareStatement("insert into identity values (?, ?, ?, ?)");;
			final PreparedStatement artifactInsertStatement = (PreparedStatement) connection.prepareStatement("insert into artifact values (?, ?, ?, ?, ?, ?, ?)");
			final PreparedStatement linkInsertStatement = (PreparedStatement) connection.prepareStatement("insert into link values (?, ?, ?, ?, ?, ?, ?)");

			for (final SerializationContent artifact : artefacts) {
				final Gmodel model = artifact.getModel();
				final Instance root = model.getInstance().get(0);
				final String payload = root.getSemanticIdentity().getPayload() == null ? null : root.getSemanticIdentity().getPayload();

				setUpIdInsertStatement(idInsertStatement, root.getSemanticIdentity().getUniqueRepresentationReference(),
						root.getSemanticIdentity().getName(),
						root.getSemanticIdentity().getPluralName(),
						payload);
				idInsertStatement.addBatch();

				setUpArtifactInsertStatement(artifactInsertStatement,
						root.getSemanticIdentity().getUniqueRepresentationReference(),
						root.getSemanticIdentity().getIdentifier(),
						root.getMetaElement(),
						root.getArtifact(),
						""+root.isIsAbstract(),
						"VER",
						artifact.getContent());
				artifactInsertStatement.addBatch();
			}
			prepStatements.add(idInsertStatement);
			prepStatements.add(artifactInsertStatement);
			executeBatchStatements(prepStatements, connection);
			System.err.println("Batch execution request is sent");
		}catch (final SQLException ex) {
			Logger.getLogger("global").log(Level.SEVERE, "Transaction has been rolled back", ex);
		} catch (final ClassNotFoundException ex) {
			Logger.getLogger("global").log(Level.SEVERE, "DB driver not found", ex);
		}
	}

	public static void testInstanceRetrieval(final String uuid) {
		Connection connection = null;
		CallableStatement fetchContainedInstanceProc = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/repository?"
					+ "user=root&password=");
			connection.setAutoCommit(false);
			fetchContainedInstanceProc = (CallableStatement) connection.prepareCall("{ call getContainedInstances(?) }");
			fetchContainedInstanceProc.setString(1, uuid);
			Timer.getInstance().start(uuid);
			fetchContainedInstanceProc.execute();
			final ResultSet rs = fetchContainedInstanceProc.getResultSet();
			final ResultSetMetaData rsMetaData = (ResultSetMetaData) rs.getMetaData();
			final List<String> xmlDocs = new ArrayList<String>();

			while (rs.next()) {
				xmlDocs.add(rs.getString("xml1"));
				xmlDocs.add(rs.getString("xml2"));
				xmlDocs.add(rs.getString("xml3"));
				xmlDocs.add(rs.getString("xml4"));
			}
			Timer.getInstance().time(uuid, TimeUnit.MILLISECONDS);
			System.err.println("Time taken: "+Timer.getInstance().getTimerRecords(uuid).getTimeTakenInMilliseconds());

			rs.close();
			fetchContainedInstanceProc.close();
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (final SQLException ex) {
			Logger.getLogger("global").log(Level.SEVERE, "Transaction has been rolled back", ex);
		} catch (final ClassNotFoundException ex) {
			Logger.getLogger("global").log(Level.SEVERE, "DB driver not found", ex);
		}
	}


	private static void setUpIdInsertStatement(final PreparedStatement statement, final String uuid, final String name, final String pluralName, final String payload) throws SQLException {
		statement.setString(1,uuid);
		statement.setString(2, name);
		statement.setString(3,pluralName);
		statement.setString(4, payload);
	}

	private static void setUpArtifactInsertStatement(final PreparedStatement artifactInsertStatement, final String urr, final String uuid, final String category,
			final String container, final String isAbstractId, final String flavor, final String xmlContent) throws SQLException {
		artifactInsertStatement.setString(1, urr);
		artifactInsertStatement.setString(2, uuid);
		artifactInsertStatement.setString(3, category);
		artifactInsertStatement.setString(4, container);
		artifactInsertStatement.setString(5, isAbstractId); //isAbstract to be URR
		artifactInsertStatement.setString(6, flavor);
		artifactInsertStatement.setString(7, xmlContent);
	}

	private static void setUpSupersetReferenceInsertStatement(final PreparedStatement statement) {

	}

	public static void testBactchInsert(final int numberOfInserts) throws SQLException {
		final GenericObjectPool pool = new GenericObjectPool(null);
		final ConnectionFactory  cf =  new DriverManagerConnectionFactory("jdbc:mysql://localhost/repository","root","");

		final KeyedObjectPoolFactory kopf =new GenericKeyedObjectPoolFactory(null, 8);

		final PoolableConnectionFactory pcf =  new PoolableConnectionFactory(cf,
				pool,
				kopf,
				null,
				false,
				true);

		for(int i = 0; i < 5; i++) {
			try {
				pool.addObject();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		new PoolingDriver().registerPool("repositoryConnectionPool", pool);

		final java.sql.Connection pooledConn = DriverManager.getConnection("jdbc:apache:commons:dbcp:repositoryConnectionPool");
		System.err.println("Are we connected? " + !pooledConn.isClosed());
		System.err.println("Idle Connections: " + pool.getNumIdle() + ", out of " + pool.getNumActive());

		Connection connection = null;
		final List<PreparedStatement> prepStatements = new ArrayList<PreparedStatement>();
		PreparedStatement preparedStatement = null;
		final CallableStatement storedProc = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/repository?"
					+ "user=root&password=");
			//connection = DriverManager.getConnection("jdbc:mysql://gmodeldemo.cmclk9pnjkez.us-east-1.rds.amazonaws.com/tmp?"
			//		+ "user=adminrds&password=biF3ld896i");
			//Multiple statements are grouped into a single transaction
			connection.setAutoCommit(false);
			//storedProc = connection.prepareCall("{ call createidentity(?, ?, ?, ?) }");

			preparedStatement = (PreparedStatement) connection.prepareStatement("insert into identity values (?, ?, ?, ?)");

			for (int n = 0; n < numberOfInserts; n++) {

				//				storedProc.setString(1, UUID.randomUUID().toString());
				//				storedProc.setString(2, "MyName "+n);
				//				storedProc.setString(3, "My Plural Name "+n);
				//				storedProc.setString(4, null);
				//				storedProc.addBatch();

				preparedStatement.setString(1, UUID.randomUUID().toString());
				preparedStatement.setString(2, "MyName "+n);
				preparedStatement.setString(3, "My Plural Name "+n);
				preparedStatement.setString(4, null);
				preparedStatement.addBatch();
			}
			//Timer.getInstance().start("testBactchInsert");
			//storedProc.executeBatch();
			//final int[] updateCounts = preparedStatement.executeBatch();
			//System.err.println(updateCounts);
			//connection.commit();
			prepStatements.add(preparedStatement);
			executeBatchStatements(prepStatements, connection);
			System.err.println("Batch execution request is sent");
		}catch (final SQLException ex) {
			Logger.getLogger("global").log(Level.SEVERE, "Transaction has been rolled back", ex);
		} catch (final ClassNotFoundException ex) {
			Logger.getLogger("global").log(Level.SEVERE, "DB driver not found", ex);
		}
	}

	private static void executeBatchStatements(final List<PreparedStatement> statements, final Connection connection) throws SQLException {
		final ExecutorService service = Executors.newCachedThreadPool(); //should be created externally
		final PoolFiberFactory fact = new PoolFiberFactory(service);
		final Fiber fiber = fact.create();
		fiber.start();

		final Channel<List<PreparedStatement>> msgChannel = new MemoryChannel<List<PreparedStatement>>();

		final Callback<List<PreparedStatement>> callBack = new Callback<List<PreparedStatement>>() {
			public void onMessage(final List<PreparedStatement> stmts) {
				final String timerId = UUID.randomUUID().toString();
				Timer.getInstance().start(timerId);
				try {
					for (final PreparedStatement statement : stmts) {
						statement.executeBatch();
					}
					connection.commit();
					Timer.getInstance().time(timerId, TimeUnit.MILLISECONDS);
					final long timeTaken = Timer.getInstance().getTimerRecords(timerId).getTimeTakenInMilliseconds();
					System.err.println("Time taken: "+timeTaken);
					if (connection!= null) {
						connection.setAutoCommit(true);
						connection.close();
					}
					fact.dispose();
					service.shutdown();
				} catch (final SQLException ex) {
					ex.printStackTrace();
					if (connection != null) {
						try {
							connection.rollback();
						} catch (final SQLException e) {
							e.printStackTrace();
						}
						Logger.getLogger("global").log(Level.SEVERE, "Transaction has been rolled back", ex);
					}
				}
			}
		};

		msgChannel.subscribe(fiber, callBack);
		msgChannel.publish(statements);
	}

}