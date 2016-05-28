package org.s23m.cell.persistence.jdbc.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.After;
import org.junit.Before;
import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Edge;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Identity;

import com.zaxxer.hikari.HikariDataSource;

public abstract class AbstractJdbcTest {

	private HikariDataSource dataSource;

	private QueryRunner queryRunner;

	protected JdbcGraphDao graphDao;
	protected JdbcIdentityDao identityDao;
	protected JdbcArrowDao arrowDao;
	protected JdbcEdgeDao edgeDao;

	@Before
	public void setUp() throws Exception {
		dataSource = createHsqldbDatasource();
		executeDDL(dataSource);

		queryRunner = new QueryRunner(dataSource);

		graphDao = new JdbcGraphDao(queryRunner);
		identityDao = new JdbcIdentityDao(queryRunner);
		arrowDao = new JdbcArrowDao(queryRunner);
		edgeDao = new JdbcEdgeDao(queryRunner);
	}

	@After
	public void tearDown() throws Exception {
		// must drop tables in dependency order
		final Class<?>[] entityClasses = new Class<?>[] { Edge.class, Arrow.class, Graph.class, Identity.class };

		for (final Class<?> c : entityClasses) {
			dropTable(dataSource, c);
		}
		dataSource.close();
	}

	private void dropTable(final DataSource dataSource, final Class<?> c) throws SQLException {
		final String sql = "DROP TABLE " + c.getSimpleName();
		executeSql(dataSource, sql);
	}

	private void executeDDL(final DataSource dataSource) throws SQLException {
		final InputStream stream = JdbcArrowDaoTest.class.getClassLoader().getResourceAsStream("standard_ddl.sql");
		final String ddl = readStream(stream);
		executeSql(dataSource, ddl);
	}

	private void executeSql(final DataSource dataSource, final String sql) throws SQLException {
		final Connection connection = dataSource.getConnection();
		final Statement statement = connection.createStatement();
		statement.execute(sql);
		connection.close();
	}

	private HikariDataSource createHsqldbDatasource() {
		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");

		// set the database name based on the test name, so that we can run tests in parallel
		// see http://hsqldb.org/doc/guide/dbproperties-chapt.html

		final String databaseName = getClass().getSimpleName();
		final String hsqldbDriverAndProtocol = "jdbc:hsqldb:mem:";
		final String hsqldbUrl = hsqldbDriverAndProtocol + databaseName;
		dataSource.setJdbcUrl(hsqldbUrl);

		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	private String readStream(final InputStream is) {
		final Scanner scanner = new Scanner(is);
		final Scanner s = scanner.useDelimiter("\\A");
		try {
			return s.hasNext() ? s.next() : "";
		} finally {
			scanner.close();
		}
	}
}
