package org.s23m.cell.persistence.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.s23m.cell.persistence.dao.IdentityDao;
import org.s23m.cell.persistence.model.Identity;

public class JdbcIdentityDao implements IdentityDao {

	private static final String UUID = "uuid";
	private static final String NAME = "name";
	private static final String PLURAL_NAME = "pluralName";
	private static final String CODE_NAME = "codeName";
	private static final String PLURAL_CODE_NAME = "pluralCodeName";
	private static final String PAYLOAD = "payload";

	private static final String[] COLUMN_NAMES = {
		NAME,
		PLURAL_NAME,
		CODE_NAME,
		PLURAL_CODE_NAME,
		PAYLOAD,
		UUID
	};

	private static final String SELECT_BY_PK_TEMPLATE = SqlQueryTemplates.createSelectByIdQueryTemplate(Identity.class, UUID);

	private static final String UPDATE_TEMPLATE = SqlQueryTemplates.createUpdateStatementTemplate(Identity.class, COLUMN_NAMES);

	private static final String INSERT_TEMPLATE = SqlQueryTemplates.createInsertStatementTemplate(Identity.class, COLUMN_NAMES);


	private final QueryRunner queryRunner;

	private final IdentityGetHandler handler;

	public JdbcIdentityDao(final QueryRunner queryRunner) {
		this.queryRunner = queryRunner;
		this.handler = new IdentityGetHandler();
	}

	public Identity get(final String uuid) {
		try {
			return queryRunner.query(SELECT_BY_PK_TEMPLATE, handler, uuid);
		} catch (final SQLException e) {
			throw new RuntimeException("Could not retrieve Identity with uuid '" + uuid + "'", e);
		}
	}

	public void saveOrUpdate(final Identity i) {
		try {
			// try updating first
			final Object[] parameters = {
					i.getName(),
					i.getPluralName(),
					i.getCodeName(),
					i.getPluralCodeName(),
					i.getPayload(),
					i.getUuid()
			};
			final int updates = queryRunner.update(UPDATE_TEMPLATE, parameters);
			if (updates == 0) {
				// record does not exist - insert it
				final int inserts = queryRunner.update(INSERT_TEMPLATE, parameters);
				if (inserts == 0) {
					throw new RuntimeException("Failed to insert Identity: " + i);
				}
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Could not save or update Identity: " + i, e);
		}
	}

	private static class IdentityGetHandler implements ResultSetHandler<Identity> {

		public Identity handle(final ResultSet resultSet) throws SQLException {
			final boolean hasNext = resultSet.next();
			if (hasNext) {
				final String uuid = resultSet.getString(UUID);
				final String name = resultSet.getString(NAME);
				final String pluralName = resultSet.getString(PLURAL_NAME);
				final String codeName = resultSet.getString(CODE_NAME);
				final String pluralCodeName = resultSet.getString(PLURAL_CODE_NAME);
				final String payload = resultSet.getString(PAYLOAD);

				final Identity result = new Identity();
				result.setUuid(uuid);
				result.setName(name);
				result.setPluralName(pluralName);
				result.setCodeName(codeName);
				result.setPluralCodeName(pluralCodeName);
				result.setPayload(payload);
				return result;
			} else {
				return null;
			}
		}
	}
}
