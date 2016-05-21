package org.s23m.cell.persistence.jdbc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Graph.ProperClasses;
import org.s23m.cell.persistence.model.Identity;

public class JdbcArrowDaoTest {

	@Test
	public void testPersistence() throws SQLException {

		final DataSource dataSource = TestDatabaseSetup.createHsqldbDatasource();

		// set up DDL first
		executeDDL(dataSource);

		// now run test
		final QueryRunner runner = new QueryRunner(dataSource);
		final JdbcGraphDao graphDao = new JdbcGraphDao(runner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(runner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(runner);

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

	private void executeDDL(final DataSource dataSource) throws SQLException {
		final Connection connection = dataSource.getConnection();
		final Statement statement = connection.createStatement();

		final InputStream stream = JdbcArrowDaoTest.class.getClassLoader().getResourceAsStream("standard_ddl.sql");
		final String ddl = readStream(stream);

		statement.execute(ddl);

		connection.close();
	}

	private static String readStream(final InputStream is) {
		final Scanner scanner = new Scanner(is);
		final Scanner s = scanner.useDelimiter("\\A");
		try {
			return s.hasNext() ? s.next() : "";
		} finally {
			scanner.close();
		}
	}
}
