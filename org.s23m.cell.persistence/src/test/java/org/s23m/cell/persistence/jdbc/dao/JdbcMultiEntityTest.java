package org.s23m.cell.persistence.jdbc.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createIdentity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.s23m.cell.persistence.model.Agent;
import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Edge;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Identity;
import org.s23m.cell.persistence.model.ProperClass;

/**
 * A larger scale, more realistic test in which the foreign key constraints are respected.
 */
public class JdbcMultiEntityTest extends AbstractJdbcTest {

	private static int id;

	@Test
	public void testEdgeInsertion() throws SQLException {
		createAndSaveEdge();

		// check that auto-incrementing of sequence numbers works
		final Long edgeCount = 1L;
		checkSequenceNumber(edgeCount, Edge.class);
		checkSequenceNumber(edgeCount, Arrow.class);

		checkSequenceNumber(5L, Graph.class);
		checkSequenceNumber(39L, Identity.class);
	}

	@Test
	public void testAgentInsertion() throws SQLException {
		createAndSaveAgent();

		checkSequenceNumber(1L, Agent.class);
		checkSequenceNumber(2L, Identity.class);
	}

	private void checkSequenceNumber(final Long expectedNumber, final Class<?> entityClass) throws SQLException {
		final Long sequenceNumber = findMaximumSequenceNumber(entityClass);
		assertThat(sequenceNumber, is(expectedNumber));

		final Long count = findRecordCount(entityClass);
		assertThat(sequenceNumber, is(count));
	}

	private Long findRecordCount(final Class<?> entityClass) throws SQLException {
		return executeCountQuery("select count(*) from " + entityClass.getSimpleName());
	}

	private Long findMaximumSequenceNumber(final Class<?> entityClass) throws SQLException {
		return executeCountQuery("select max(sequenceNumber) from " + entityClass.getSimpleName());
	}

	private Long executeCountQuery(final String sql) throws SQLException {
		final Connection connection = dataSource.getConnection();
		final Statement statement = connection.createStatement();
		final ResultSet resultSet = statement.executeQuery(sql);
		resultSet.next();
		final Long result = resultSet.getLong(1);
		connection.close();
		return result;
	}

	private Identity createAndSaveIdentity() {
		final String uuid = generateUUID();
		final Identity identity = createIdentity(uuid);
		identityDao.insert(identity);
		return identity;
	}

	private Graph createAndSaveGraph() {
		final Identity uuid = createAndSaveIdentity();
		final Identity category = createAndSaveIdentity();
		final Identity container = createAndSaveIdentity();
		final Identity isAbstractValue = createAndSaveIdentity();
		final Identity maxCardinalityValueInContainer = createAndSaveIdentity();
		final ProperClass properClass = ProperClass.Edge;
		final String contentAsXml = "content";
		final Identity urr = createAndSaveIdentity();

		final Graph graph = new Graph(urr, uuid, category, container, isAbstractValue, properClass, maxCardinalityValueInContainer, contentAsXml);

		graphDao.insert(graph);

		return graph;
	}

	private Arrow createAndSaveArrow() {
		final Graph urr = createAndSaveGraph();
		final Identity category = createAndSaveIdentity();
		final Graph fromGraph = createAndSaveGraph();
		final Graph toGraph = createAndSaveGraph();

		final Arrow arrow = new Arrow(urr, category, ProperClass.Visibility, fromGraph, toGraph);

		arrowDao.insert(arrow);

		return arrow;
	}

	private Agent createAndSaveAgent() {
		final Identity urr = createAndSaveIdentity();
		final Identity uuid = createAndSaveIdentity();

		final Agent agent = new Agent(urr, uuid, "email", "password", "mobile", "firstName", "lastName", "alias");

		agentDao.insert(agent);

		return agent;
	}

	private Edge createAndSaveEdge() {
		final Arrow urr = createAndSaveArrow();

		final Identity minCardinalityValueFromEdgeEnd = createAndSaveIdentity();
		final Identity minCardinalityValueToEdgeEnd = createAndSaveIdentity();
		final Identity maxCardinalityValueFromEdgeEnd = createAndSaveIdentity();
		final Identity maxCardinalityValueToEdgeEnd = createAndSaveIdentity();
		final Identity isNavigableValueFromEdgeEnd = createAndSaveIdentity();
		final Identity isNavigableValueToEdgeEnd = createAndSaveIdentity();
		final Identity isContainerValueFromEdgeEnd = createAndSaveIdentity();
		final Identity isContainerValueToEdgeEnd = createAndSaveIdentity();
		final Graph fromEdgeEnd = createAndSaveGraph();
		final Graph toEdgeEnd = createAndSaveGraph();

		final Edge edge = new Edge(urr, minCardinalityValueFromEdgeEnd, minCardinalityValueToEdgeEnd,
				maxCardinalityValueFromEdgeEnd, maxCardinalityValueToEdgeEnd,
				isNavigableValueFromEdgeEnd, isNavigableValueToEdgeEnd, isContainerValueFromEdgeEnd,
				isContainerValueToEdgeEnd, fromEdgeEnd, toEdgeEnd);

		edgeDao.insert(edge);

		return edge;
	}

	private String generateUUID() {
		id++;
		return String.valueOf(id);
	}
}
