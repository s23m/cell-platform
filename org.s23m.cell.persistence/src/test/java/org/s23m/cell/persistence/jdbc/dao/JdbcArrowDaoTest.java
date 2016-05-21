package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;
import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Graph.ProperClasses;
import org.s23m.cell.persistence.model.Identity;

public class JdbcArrowDaoTest extends AbstractJdbcTest {

	@Test
	public void testPersistence() throws SQLException {
		final JdbcGraphDao graphDao = new JdbcGraphDao(queryRunner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(queryRunner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(queryRunner);

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

		final Arrow result = new Arrow();
		result.setFromGraph(uuid);
		result.setToGraph(uuid);
		result.setUrr(uuid);
		// (typically a different uuid)
		result.setCategory(uuid);
		result.setProperClass(ProperClasses.VISIBILITY);

		// save identity
		identityDao.saveOrUpdate(identity);

		// save graph
		graphDao.saveOrUpdate(graph);

		// save arrow
		arrowDao.saveOrUpdate(result);

		// now retrieve the result
		final Arrow retrieved = arrowDao.get(result.getUrr());
		assertNotNull(retrieved);

		assertEquals(result.getUrr(), retrieved.getUrr());
	}
}
