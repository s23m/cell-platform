package org.s23m.cell.persistence.jdbc.dao;

import static org.s23m.cell.persistence.jdbc.dao.TestData.createIdentity;

import java.sql.SQLException;

import org.junit.Test;
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
	}

	private Identity createAndSaveIdentity() {
		final String uuid = generateUUID();
		final Identity identity = createIdentity(uuid);
		identityDao.insert(identity);
		return identity;
	}

	private Graph createAndSaveGraph() {
		final String uuid = createAndSaveIdentity().getUuid();
		final String category = createAndSaveIdentity().getUuid();
		final String container = createAndSaveIdentity().getUuid();
		final String isAbstractValue = createAndSaveIdentity().getUuid();
		final String maxCardinalityValueInContainer = createAndSaveIdentity().getUuid();
		final ProperClass properClass = ProperClass.Edge;
		final String contentAsXml = "content";
		final String urr = createAndSaveIdentity().getUuid();

		final Graph graph = new Graph(urr, uuid, category, container, isAbstractValue, properClass, maxCardinalityValueInContainer, contentAsXml);

		graphDao.insert(graph);

		return graph;
	}

	private Arrow createAndSaveArrow() {
		final String category = createAndSaveIdentity().getUuid();
		final String fromGraph = createAndSaveGraph().getUrr();
		final String toGraph = createAndSaveGraph().getUrr();
		final String urr = createAndSaveGraph().getUrr();

		final Arrow arrow = new Arrow(urr, category, ProperClass.Visibility, fromGraph, toGraph);

		arrowDao.insert(arrow);

		return arrow;
	}

	private Edge createAndSaveEdge() {
		final Edge edge = new Edge();
		edge.setMinCardinalityValueFromEdgeEnd(createAndSaveIdentity().getUuid());
		edge.setMinCardinalityValueToEdgeEnd(createAndSaveIdentity().getUuid());
		edge.setMaxCardinalityValueFromEdgeEnd(createAndSaveIdentity().getUuid());
		edge.setMaxCardinalityValueToEdgeEnd(createAndSaveIdentity().getUuid());
		edge.setIsNavigableValueFromEdgeEnd(createAndSaveIdentity().getUuid());
		edge.setIsNavigableValueToEdgeEnd(createAndSaveIdentity().getUuid());
		edge.setIsContainerValueFromEdgeEnd(createAndSaveIdentity().getUuid());
		edge.setIsContainerValueToEdgeEnd(createAndSaveIdentity().getUuid());
		edge.setFromEdgeEnd(createAndSaveGraph().getUrr());
		edge.setToEdgeEnd(createAndSaveGraph().getUrr());
		edge.setUrr(createAndSaveArrow().getUrr());

		edgeDao.insert(edge);

		return edge;
	}

	private String generateUUID() {
		id++;
		return String.valueOf(id);
	}
}
