package org.s23m.cell.persistence.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.s23m.cell.persistence.dao.AgentDao;
import org.s23m.cell.persistence.model.Agent;

public final class JdbcAgentDao implements AgentDao {

	private static final String URR = "urr";
	private static final String UUID = "uuid";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String MOBILE = "mobile";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String ALIAS = "alias";

	private static final String[] COLUMN_NAMES = {
			UUID,
			EMAIL,
			PASSWORD,
			MOBILE,
			FIRST_NAME,
			LAST_NAME,
			ALIAS,
			URR
	};

	private static final String SELECT_BY_PK_TEMPLATE = SqlQueryTemplates.createSelectByIdQueryTemplate(Agent.class, URR);

	private static final String UPDATE_TEMPLATE = SqlQueryTemplates.createUpdateStatementTemplate(Agent.class, COLUMN_NAMES);

	private static final String INSERT_TEMPLATE = SqlQueryTemplates.createInsertStatementTemplate(Agent.class, COLUMN_NAMES);

	private final QueryRunner queryRunner;

	private final AgentGetHandler handler;

	public JdbcAgentDao(final QueryRunner queryRunner) {
		this.queryRunner = queryRunner;
		this.handler = new AgentGetHandler();
	}

	public Agent get(final String urr) {
		try {
			return queryRunner.query(SELECT_BY_PK_TEMPLATE, handler, urr);
		} catch (final SQLException e) {
			throw new RuntimeException("Could not retrieve Agent with URR '" + urr + "'", e);
		}
	}

	@Override
	public void insert(final Agent agent) {
		final Object[] parameters = createParameters(agent);

		try {
			final int updates = queryRunner.update(INSERT_TEMPLATE, parameters);
			if (updates != 1) {
				// failure - check if the provided arrow did not have a primary key
				if (agent.isTransient()) {
					throw new RuntimeException("Failed to insert Agent - no primary key provided: " + agent);
				} else {
					throw new RuntimeException("Failed to insert Agent: " + agent);
				}
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Could not insert Agent: " + agent, e);
		}
	}

	@Override
	public void update(final Agent agent) {
		final Object[] parameters = createParameters(agent);

		try {
			final int updates = queryRunner.update(UPDATE_TEMPLATE, parameters);
			if (updates != 1) {
				// failure - check if the provided arrow did not have a primary key
				if (agent.isTransient()) {
					throw new RuntimeException("Failed to update Agent - no primary key provided: " + agent);
				} else {
					throw new RuntimeException("Failed to update Agent: " + agent);
				}
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Could not update Agent: " + agent, e);
		}
	}

	private Object[] createParameters(final Agent agent) {
		return new Object[] {
				agent.getUuid(),
				agent.getEmail(),
				agent.getPassword(),
				agent.getMobile(),
				agent.getFirstName(),
				agent.getLastName(),
				agent.getAlias(),
				agent.getUrr()
		};
	}

	private static class AgentGetHandler implements ResultSetHandler<Agent> {

		public Agent handle(final ResultSet resultSet) throws SQLException {
			final boolean hasNext = resultSet.next();
			if (hasNext) {
				final String uuid = resultSet.getString(UUID);
				final String email = resultSet.getString(EMAIL);
				final String password = resultSet.getString(PASSWORD);
				final String mobile = resultSet.getString(MOBILE);
				final String firstName = resultSet.getString(FIRST_NAME);
				final String lastName = resultSet.getString(LAST_NAME);
				final String alias = resultSet.getString(ALIAS);
				final String urr = resultSet.getString(URR);

				return new Agent(urr, uuid, email, password, mobile, firstName, lastName, alias);
			} else {
				return null;
			}
		}
	}
}
