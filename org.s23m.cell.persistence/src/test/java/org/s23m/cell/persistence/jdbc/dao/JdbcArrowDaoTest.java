package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createArrow;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createGraph;
import static org.s23m.cell.persistence.jdbc.dao.TestData.createIdentity;

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
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow result = createArrow(uuid, ProperClasses.EDGE);

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
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow result = createArrow(uuid, ProperClasses.EDGE);

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
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow result = createArrow(uuid, ProperClasses.VISIBILITY);
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
		createArrow(uuid, "invalid");
	}

}
