package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createArrow;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createEdge;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createGraph;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createIdentity;

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
	public void testInsertionAndRetrieval() throws SQLException {

		final JdbcGraphDao graphDao = new JdbcGraphDao(queryRunner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(queryRunner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(queryRunner);
		final JdbcEdgeDao edgeDao = new JdbcEdgeDao(queryRunner);

		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow arrow = createArrow(uuid, ProperClasses.VISIBILITY);
		final Edge edge = createEdge(uuid);

		identityDao.saveOrUpdate(identity);
		graphDao.saveOrUpdate(graph);
		arrowDao.insert(arrow);
		edgeDao.insert(edge);

		// now retrieve the result
		final Edge retrieved = edgeDao.get(edge.getUrr());
		assertNotNull(retrieved);
		assertEquals(edge.getUrr(), retrieved.getUrr());
	}

	@Test
	public void testMultipleInsertionAttemptsFail() throws SQLException {
		final JdbcGraphDao graphDao = new JdbcGraphDao(queryRunner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(queryRunner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(queryRunner);
		final JdbcEdgeDao edgeDao = new JdbcEdgeDao(queryRunner);

		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow arrow = createArrow(uuid, ProperClasses.VISIBILITY);
		final Edge edge = createEdge(uuid);

		identityDao.saveOrUpdate(identity);
		graphDao.saveOrUpdate(graph);
		arrowDao.insert(arrow);
		edgeDao.insert(edge);

		try {
			edgeDao.insert(edge);
			fail("Multiple inserts should be disallowed");
		} catch (final RuntimeException e) {
			// expected
		}
	}

	@Test
	public void testUpdate() throws SQLException {
		final JdbcGraphDao graphDao = new JdbcGraphDao(queryRunner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(queryRunner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(queryRunner);
		final JdbcEdgeDao edgeDao = new JdbcEdgeDao(queryRunner);

		final String uuid = "1";

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow arrow = createArrow(uuid, ProperClasses.VISIBILITY);
		final Edge edge = createEdge(uuid);

		identityDao.saveOrUpdate(identity);
		graphDao.saveOrUpdate(graph);
		arrowDao.insert(arrow);
		edgeDao.insert(edge);

		// now retrieve the result
		final Edge retrieved1 = edgeDao.get(uuid);
		assertEquals(uuid, retrieved1.getIsNavigableValueToEdgeEnd());

		// store another identity
		final String uuid2 = "2";
		final Identity identity2 = createIdentity(uuid2);
		identityDao.saveOrUpdate(identity2);

		// modify edge property and update
		edge.setIsNavigableValueToEdgeEnd(uuid2);
		edgeDao.update(edge);

		final Edge retrieved2 = edgeDao.get(uuid);
		assertEquals(uuid2, retrieved2.getIsNavigableValueToEdgeEnd());
	}

}
