package org.s23m.cell.persistence.jdbc.dao;

import com.zaxxer.hikari.HikariDataSource;

public class TestDatabaseSetup {

	public static HikariDataSource createHsqldbDatasource() {
		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		dataSource.setJdbcUrl("jdbc:hsqldb:mem:test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}
}
