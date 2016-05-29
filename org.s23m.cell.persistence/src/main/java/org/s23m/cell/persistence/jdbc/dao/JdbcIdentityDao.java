package org.s23m.cell.persistence.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.s23m.cell.persistence.dao.IdentityDao;
import org.s23m.cell.persistence.model.Identity;

public final class JdbcIdentityDao implements IdentityDao {

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

	private static final int NAME_LENGTH_LIMIT = 100;

	private static final int PLURAL_NAME_LENGTH_LIMIT = 100;

	private static final int CODE_NAME_LENGTH_LIMIT = 100;

	private static final int PLURAL_CODE_NAME_LENGTH_LIMIT = 100;

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

	@Override
	public void insert(final Identity identity) {
		final Object[] parameters = createParameters(identity);

		try {
			final int updates = queryRunner.update(INSERT_TEMPLATE, parameters);
			if (updates != 1) {
				// failure - check if the provided identity did not have a primary key
				if (identity.isTransient()) {
					throw new RuntimeException("Failed to insert Identity - no primary key provided: " + identity);
				} else {
					throw new RuntimeException("Failed to insert Identity: " + identity);
				}
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Could not insert Identity: " + identity, e);
		}
	}

	@Override
	public void update(final Identity identity) {
		final Object[] parameters = createParameters(identity);

		try {
			final int updates = queryRunner.update(UPDATE_TEMPLATE, parameters);
			if (updates != 1) {
				// failure - check if the provided identity did not have a primary key
				if (identity.isTransient()) {
					throw new RuntimeException("Failed to update Identity - no primary key provided: " + identity);
				} else {
					throw new RuntimeException("Failed to update Identity: " + identity);
				}
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Could not update Identity: " + identity, e);
		}
	}

	private Object[] createParameters(final Identity identity) {
		// validate the lengths of fields
		final String name = identity.getName();
		final String pluralName = identity.getPluralName();
		final String codeName = identity.getCodeName();
		final String pluralCodeName = identity.getPluralCodeName();

		validateLength(name, "name", NAME_LENGTH_LIMIT);
		validateLength(pluralName, "pluralName", PLURAL_NAME_LENGTH_LIMIT);
		validateLength(codeName, "codeName", CODE_NAME_LENGTH_LIMIT);
		validateLength(pluralCodeName, "pluralCodeName", PLURAL_CODE_NAME_LENGTH_LIMIT);

		return new Object[] {
				name,
				pluralName,
				codeName,
				pluralCodeName,
				identity.getPayload(),
				identity.getUuid()
		};
	}

	private void validateLength(final String string, final String fieldName, final int lengthLimit) {
		if (string.length() > lengthLimit) {
			throw new IllegalArgumentException("Identity " + fieldName + " is invalid (exceeds length limit of " + lengthLimit + ")");
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

				return new Identity(uuid, name, pluralName, codeName, pluralCodeName, payload);
			} else {
				return null;
			}
		}
	}
}
