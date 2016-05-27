package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;
import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Graph.ProperClasses;
import org.s23m.cell.persistence.model.Identity;

public class JdbcArrowDaoTest extends AbstractJdbcTest {

	@Test
	public void testInsertionAndRetrieval() throws SQLException {
		final JdbcGraphDao graphDao = new JdbcGraphDao(queryRunner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(queryRunner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(queryRunner);

		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid);
		final Arrow result = createArrow(uuid);

		identityDao.saveOrUpdate(identity);
		graphDao.saveOrUpdate(graph);
		arrowDao.insert(result);

		// now retrieve the result
		final Arrow retrieved = arrowDao.get(result.getUrr());
		assertNotNull(retrieved);
		assertEquals(result.getUrr(), retrieved.getUrr());
	}

	@Test
	public void testMultipleInsertionAttemptsFail() throws SQLException {
		final JdbcGraphDao graphDao = new JdbcGraphDao(queryRunner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(queryRunner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(queryRunner);

		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid);
		final Arrow result = createArrow(uuid);

		identityDao.saveOrUpdate(identity);
		graphDao.saveOrUpdate(graph);
		arrowDao.insert(result);

		try {
			arrowDao.insert(result);
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

		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid);
		final Arrow result = createArrow(uuid);
		final String urr = result.getUrr();

		identityDao.saveOrUpdate(identity);
		graphDao.saveOrUpdate(graph);
		arrowDao.insert(result);

		// now retrieve the result
		final Arrow retrieved1 = arrowDao.get(urr);
		assertEquals(ProperClasses.VISIBILITY, retrieved1.getProperClass());

		// modify proper class and update
		result.setProperClass(ProperClasses.EDGE);
		arrowDao.update(result);

		final Arrow retrieved2 = arrowDao.get(urr);
		assertEquals(ProperClasses.EDGE, retrieved2.getProperClass());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidProperClassSpecified() {
		final String uuid = UUID.randomUUID().toString();
		final Arrow a = createArrow(uuid);
		a.setProperClass("invalid");
	}

	private Arrow createArrow(final String uuid) {
		final Arrow result = new Arrow();
		result.setFromGraph(uuid);
		result.setToGraph(uuid);
		result.setUrr(uuid);
		// (typically a different uuid)
		result.setCategory(uuid);
		result.setProperClass(ProperClasses.VISIBILITY);
		return result;
	}

	private Graph createGraph(final String uuid) {
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
		return graph;
	}

	private Identity createIdentity(final String uuid) {
		final Identity identity = new Identity();
		identity.setUuid(uuid);
		identity.setName("graph test");
		identity.setPluralName("tests");
		identity.setCodeName("test");
		identity.setPluralCodeName("tests");
		identity.setPayload("some text");
		return identity;
	}

}
