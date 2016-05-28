package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow arrow = createArrow(uuid, ProperClasses.EDGE);

		identityDao.insert(identity);
		graphDao.insert(graph);
		arrowDao.insert(arrow);

		// now retrieve the result
		final Arrow retrieved = arrowDao.get(arrow.getUrr());
		assertNotNull(retrieved);
		assertEquals(arrow.getUrr(), retrieved.getUrr());
		assertEquals(arrow.toString(), retrieved.toString());
	}

	@Test
	public void testMultipleInsertionAttemptsFail() throws SQLException {
		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow result = createArrow(uuid, ProperClasses.EDGE);

		identityDao.insert(identity);
		graphDao.insert(graph);
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
		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow result = createArrow(uuid, ProperClasses.VISIBILITY);
		final String urr = result.getUrr();

		identityDao.insert(identity);
		graphDao.insert(graph);
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

	@Test
	public void testForeignKeyConstraintViolated() {
		final String uuid = UUID.randomUUID().toString();

		final Identity identity = createIdentity(uuid);
		final Graph graph = createGraph(uuid, ProperClasses.VERTEX);
		final Arrow arrow = createArrow(uuid, ProperClasses.EDGE);

		identityDao.insert(identity);
		graphDao.insert(graph);
		arrowDao.insert(arrow);

		try {
			// violate foreign key by pointing to a non-existent graph UUID
			arrow.setFromGraph("nonexistent");
			arrowDao.update(arrow);
			fail("Violation should have thrown an exception");
		} catch (final RuntimeException e) {
			// expected
			final Throwable cause = e.getCause();
			assertTrue(cause instanceof SQLException);
			final String message = cause.getMessage();
			final String expectedPrefix = "integrity constraint violation: foreign key no parent; FK_FROMGRAPH";
			assertTrue(message.startsWith(expectedPrefix));
		}
	}

}
