package org.s23m.cell.persistence.jdbc.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;

public class AbstractJdbcTest {

	protected DataSource dataSource;

	protected QueryRunner queryRunner;

	@Before
	public void setUp() throws Exception {
		dataSource = TestDatabaseSetup.createHsqldbDatasource();
		executeDDL(dataSource);

		queryRunner = new QueryRunner(dataSource);
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
