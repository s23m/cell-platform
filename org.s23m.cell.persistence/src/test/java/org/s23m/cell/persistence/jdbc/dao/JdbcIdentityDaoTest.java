package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;
import org.s23m.cell.persistence.model.Identity;

public class JdbcIdentityDaoTest extends AbstractJdbcTest {

	@Test
	public void testPersistence() throws SQLException {
		final Identity identity = new Identity();
		identity.setUuid(UUID.randomUUID().toString());
		identity.setName("test");
		identity.setPluralName("tests");
		identity.setCodeName("test");
		identity.setPluralCodeName("tests");
		identity.setPayload("some text");

		// save identity
		identityDao.insert(identity);

		// now retrieve the result
		final Identity retrieved = identityDao.get(identity.getUuid());
		assertNotNull(retrieved);

		assertEquals(identity.getUuid(), retrieved.getUuid());
	}
}
