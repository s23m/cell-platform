package org.s23m.cell.persistence.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.s23m.cell.persistence.dao.ArrowDao;
import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.ProperClass;

public final class JdbcArrowDao implements ArrowDao {

	private static final String URR = "urr";
	private static final String CATEGORY = "category";
	private static final String PROPER_CLASS = "properClass";
	private static final String FROM_GRAPH = "fromGraph";
	private static final String TO_GRAPH = "toGraph";

	private static final String[] COLUMN_NAMES = {
			CATEGORY,
			PROPER_CLASS,
			FROM_GRAPH,
			TO_GRAPH,
			URR
	};

	private static final String SELECT_BY_PK_TEMPLATE = SqlQueryTemplates.createSelectByIdQueryTemplate(Arrow.class, URR);

	private static final String UPDATE_TEMPLATE = SqlQueryTemplates.createUpdateStatementTemplate(Arrow.class, COLUMN_NAMES);

	private static final String INSERT_TEMPLATE = SqlQueryTemplates.createInsertStatementTemplate(Arrow.class, COLUMN_NAMES);

	private final QueryRunner queryRunner;

	private final ArrowGetHandler handler;

	public JdbcArrowDao(final QueryRunner queryRunner) {
		this.queryRunner = queryRunner;
		this.handler = new ArrowGetHandler();
	}

	public Arrow get(final String urr) {
		try {
			return queryRunner.query(SELECT_BY_PK_TEMPLATE, handler, urr);
		} catch (final SQLException e) {
			throw new RuntimeException("Could not retrieve Arrow with URR '" + urr + "'", e);
		}
	}

	@Override
	public void insert(final Arrow arrow) {
		final Object[] parameters = createParameters(arrow);

		try {
			final int updates = queryRunner.update(INSERT_TEMPLATE, parameters);
			if (updates != 1) {
				// failure - check if the provided arrow did not have a primary key
				if (arrow.isTransient()) {
					throw new RuntimeException("Failed to insert Arrow - no primary key provided: " + arrow);
				} else {
					throw new RuntimeException("Failed to insert Arrow: " + arrow);
				}
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Could not insert Arrow: " + arrow, e);
		}
	}

	@Override
	public void update(final Arrow arrow) {
		final Object[] parameters = createParameters(arrow);

		try {
			final int updates = queryRunner.update(UPDATE_TEMPLATE, parameters);
			if (updates != 1) {
				// failure - check if the provided arrow did not have a primary key
				if (arrow.isTransient()) {
					throw new RuntimeException("Failed to update Arrow - no primary key provided: " + arrow);
				} else {
					throw new RuntimeException("Failed to update Arrow: " + arrow);
				}
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Could not update Arrow: " + arrow, e);
		}
	}

	private Object[] createParameters(final Arrow arrow) {
		return new Object[] {
				arrow.getCategory(),
				arrow.getProperClass().name(),
				arrow.getFromGraph(),
				arrow.getToGraph(),
				arrow.getUrr()
		};
	}

	private static class ArrowGetHandler implements ResultSetHandler<Arrow> {

		public Arrow handle(final ResultSet resultSet) throws SQLException {
			final boolean hasNext = resultSet.next();
			if (hasNext) {
				final String category = resultSet.getString(CATEGORY);
				final ProperClass properClass = ProperClass.valueOf(resultSet.getString(PROPER_CLASS));
				final String fromGraph = resultSet.getString(FROM_GRAPH);
				final String toGraph = resultSet.getString(TO_GRAPH);
				final String urr = resultSet.getString(URR);

				return new Arrow(urr, category, properClass, fromGraph, toGraph);
			} else {
				return null;
			}
		}
	}
}
