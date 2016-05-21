package org.s23m.cell.persistence.jdbc.dao;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPDataSource;

public class TestDatabaseSetup {

	public static DataSource createHsqldbDatasource() {
		final BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:hsqldb:mem:test");
		dataSource.setUser("sa");
		dataSource.setPassword("");
		/*
		dataSource.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
		dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
		dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
		dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
		dataSource.setPartitionCount(partitionCount);
		dataSource.setAcquireIncrement(acquireIncrement);
		dataSource.setStatementsCacheSize(statementsCacheSize);
		 */
		return dataSource;
	}
}
