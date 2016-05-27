package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;
import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Edge;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Graph.ProperClasses;
import org.s23m.cell.persistence.model.Identity;

public class JdbcEdgeDaoTest extends AbstractJdbcTest {

	@Test
	public void testPersistence() throws SQLException {

		final JdbcGraphDao graphDao = new JdbcGraphDao(queryRunner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(queryRunner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(queryRunner);
		final JdbcEdgeDao edgeDao = new JdbcEdgeDao(queryRunner);

		final String uuid = UUID.randomUUID().toString();
		final Identity identity = new Identity();
		identity.setUuid(uuid);
		identity.setName("graph test");
		identity.setPluralName("tests");
		identity.setCodeName("test");
		identity.setPluralCodeName("tests");
		identity.setPayload("some text");

		final Graph graph = new Graph();
		graph.setUuid(uuid);
		graph.setUrr(uuid);
		// (typically a different uuid)
		graph.setCategory(uuid);
		// (typically a different uuid)
		graph.setContainer(uuid);
		// (typically a different uuid)
		graph.setIsAbstractValue(uuid);
		// (typically a different uuid)
		graph.setMaxCardinalityValueInContainer(uuid);
		graph.setProperClass(ProperClasses.VERTEX);
		graph.setContentAsXml("<xml></xml>");

		final Arrow arrow = new Arrow();
		arrow.setFromGraph(uuid);
		arrow.setToGraph(uuid);
		arrow.setUrr(uuid);
		// (typically a different uuid)
		arrow.setCategory(uuid);
		arrow.setProperClass(ProperClasses.VISIBILITY);

		final Edge edge = new Edge();
		edge.setUrr(uuid);
		edge.setMinCardinalityValueFromEdgeEnd(uuid);
		edge.setMinCardinalityValueToEdgeEnd(uuid);
		edge.setMaxCardinalityValueFromEdgeEnd(uuid);
		edge.setMaxCardinalityValueToEdgeEnd(uuid);
		edge.setIsNavigableValueFromEdgeEnd(uuid);
		edge.setIsNavigableValueToEdgeEnd(uuid);
		edge.setIsContainerValueFromEdgeEnd(uuid);
		edge.setIsContainerValueToEdgeEnd(uuid);
		edge.setFromEdgeEnd(uuid);
		edge.setToEdgeEnd(uuid);

		// save identity
		identityDao.saveOrUpdate(identity);

		// save graph
		graphDao.saveOrUpdate(graph);

		// save arrow
		arrowDao.insert(arrow);

		// save edge
		edgeDao.saveOrUpdate(edge);

		// now retrieve the result
		final Edge retrieved = edgeDao.get(edge.getUrr());
		assertNotNull(retrieved);

		assertEquals(edge.getUrr(), retrieved.getUrr());
	}
}
