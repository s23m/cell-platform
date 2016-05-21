package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Graph.ProperClasses;
import org.s23m.cell.persistence.model.Identity;

public class JdbcGraphDaoTest extends AbstractJdbcTest {

	@Test
	public void testPersistence() throws SQLException {

		final JdbcGraphDao graphDao = new JdbcGraphDao(queryRunner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(queryRunner);

		final String uuid = UUID.randomUUID().toString();
		final Identity identity = new Identity();
		identity.setUuid(uuid);
		identity.setName("graph test");
		identity.setPluralName("tests");
		identity.setCodeName("test");
		identity.setPluralCodeName("tests");
		identity.setPayload("some text");

		final Graph result = new Graph();
		result.setUrr(uuid);
		result.setUuid(uuid);
		// (typically a different uuid)
		result.setCategory(uuid);
		// (typically a different uuid)
		result.setContainer(uuid);
		// (typically a different uuid)
		result.setIsAbstractValue(uuid);
		// (typically a different uuid)
		result.setMaxCardinalityValueInContainer(uuid);
		result.setProperClass(ProperClasses.VERTEX);
		result.setContentAsXml("<xml></xml>");

		// save identity
		identityDao.saveOrUpdate(identity);

		// save graph
		graphDao.saveOrUpdate(result);

		// now retrieve the result
		final Graph retrieved = graphDao.get(result.getUrr());

		assertNotNull(retrieved);

		assertEquals(result.getUrr(), retrieved.getUrr());
	}
}
