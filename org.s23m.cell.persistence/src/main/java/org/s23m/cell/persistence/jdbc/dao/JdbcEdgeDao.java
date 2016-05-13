package org.s23m.cell.persistence.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.s23m.cell.persistence.dao.EdgeDao;
import org.s23m.cell.persistence.model.Edge;

public class JdbcEdgeDao implements EdgeDao {

	private static final String URR = "urr";
	private static final String MIN_CARDINALITY_VALUE_FROM_EDGE_END = "minCardinalityValueFromEdgeEnd";
	private static final String MIN_CARDINALITY_VALUE_TO_EDGE_END  = "minCardinalityValueToEdgeEnd";
	private static final String MAX_CARDINALITY_VALUE_FROM_EDGE_END = "maxCardinalityValueFromEdgeEnd";
	private static final String MAX_CARDINALITY_VALUE_TO_EDGE_END = "maxCardinalityValueToEdgeEnd";
	private static final String IS_NAVIGABLE_VALUE_FROM_EDGE_END = "isNavigableValueFromEdgeEnd";
	private static final String IS_NAVIGABLE_VALUE_TO_EDGE_END = "isNavigableValueToEdgeEnd";
	private static final String IS_CONTAINER_VALUE_FROM_EDGE_END = "isContainerValueFromEdgeEnd";
	private static final String IS_CONTAINER_VALUE_TO_EDGE_END = "isContainerValueToEdgeEnd";
	private static final String FROM_EDGE_END = "fromEdgeEnd";
	private static final String TO_EDGE_END = "toEdgeEnd";

	private static final String[] COLUMN_NAMES = {
		MIN_CARDINALITY_VALUE_FROM_EDGE_END,
		MIN_CARDINALITY_VALUE_TO_EDGE_END ,
		MAX_CARDINALITY_VALUE_FROM_EDGE_END,
		MAX_CARDINALITY_VALUE_TO_EDGE_END,
		IS_NAVIGABLE_VALUE_FROM_EDGE_END,
		IS_NAVIGABLE_VALUE_TO_EDGE_END,
		IS_CONTAINER_VALUE_FROM_EDGE_END,
		IS_CONTAINER_VALUE_TO_EDGE_END,
		FROM_EDGE_END,
		TO_EDGE_END,
		URR
	};

	private static final String SELECT_BY_PK_TEMPLATE = SqlQueryTemplates.createSelectByIdQueryTemplate(Edge.class, URR);

	private static final String UPDATE_TEMPLATE = SqlQueryTemplates.createUpdateStatementTemplate(Edge.class, COLUMN_NAMES);

	private static final String INSERT_TEMPLATE = SqlQueryTemplates.createInsertStatementTemplate(Edge.class, COLUMN_NAMES);

	private final QueryRunner queryRunner;

	private final EdgeGetHandler handler;

	public JdbcEdgeDao(final QueryRunner queryRunner) {
		this.queryRunner = queryRunner;
		this.handler = new EdgeGetHandler();
	}

	public Edge get(final String urr) {
		try {
			return queryRunner.query(SELECT_BY_PK_TEMPLATE, handler, urr);
		} catch (final SQLException e) {
			throw new RuntimeException("Could not retrieve Edge with URR '" + urr + "'", e);
		}
	}

	public void saveOrUpdate(final Edge edge) {
		try {
			// try updating first
			final Object[] parameters = {
					edge.getMinCardinalityValueFromEdgeEnd(),
					edge.getMinCardinalityValueToEdgeEnd(),
					edge.getMaxCardinalityValueFromEdgeEnd(),
					edge.getMaxCardinalityValueToEdgeEnd(),
					edge.getIsNavigableValueFromEdgeEnd(),
					edge.getIsNavigableValueToEdgeEnd(),
					edge.getIsContainerValueFromEdgeEnd(),
					edge.getIsContainerValueToEdgeEnd(),
					edge.getFromEdgeEnd(),
					edge.getToEdgeEnd(),
					edge.getUrr()
			};
			final int updates = queryRunner.update(UPDATE_TEMPLATE, parameters);
			if (updates == 0) {
				// record does not exist - insert it
				final int inserts = queryRunner.update(INSERT_TEMPLATE, parameters);
				if (inserts == 0) {
					throw new RuntimeException("Failed to insert Edge: " + edge);
				}
			}
		} catch (final SQLException e) {
			throw new RuntimeException("Could not save or update Edge: " + edge, e);
		}
	}

	private static class EdgeGetHandler implements ResultSetHandler<Edge> {

		public Edge handle(final ResultSet resultSet) throws SQLException {
			final boolean hasNext = resultSet.next();
			if (hasNext) {
				final String minCardinalityValueFromEdgeEnd = resultSet.getString(MIN_CARDINALITY_VALUE_FROM_EDGE_END);
				final String minCardinalityValueToEdgeEnd = resultSet.getString(MIN_CARDINALITY_VALUE_TO_EDGE_END);
				final String maxCardinalityValueFromEdgeEnd = resultSet.getString(MAX_CARDINALITY_VALUE_FROM_EDGE_END);
				final String maxCardinalityValueToEdgeEnd = resultSet.getString(MAX_CARDINALITY_VALUE_TO_EDGE_END);
				final String isNavigableValueFromEdgeEnd = resultSet.getString(IS_NAVIGABLE_VALUE_FROM_EDGE_END);
				final String isNavigableValueToEdgeEnd = resultSet.getString(IS_NAVIGABLE_VALUE_TO_EDGE_END);
				final String isContainerValueFromEdgeEnd = resultSet.getString(IS_CONTAINER_VALUE_FROM_EDGE_END);
				final String isContainerValueToEdgeEnd = resultSet.getString(IS_CONTAINER_VALUE_TO_EDGE_END);
				final String fromEdgeEnd = resultSet.getString(FROM_EDGE_END);
				final String toEdgeEnd = resultSet.getString(TO_EDGE_END);
				final String urr = resultSet.getString(URR);

				final Edge result = new Edge();
				result.setMinCardinalityValueFromEdgeEnd(minCardinalityValueFromEdgeEnd);
				result.setMinCardinalityValueToEdgeEnd(minCardinalityValueToEdgeEnd);
				result.setMaxCardinalityValueFromEdgeEnd(maxCardinalityValueFromEdgeEnd);
				result.setMaxCardinalityValueToEdgeEnd(maxCardinalityValueToEdgeEnd);
				result.setIsNavigableValueFromEdgeEnd(isNavigableValueFromEdgeEnd);
				result.setIsNavigableValueToEdgeEnd(isNavigableValueToEdgeEnd);
				result.setIsContainerValueFromEdgeEnd(isContainerValueFromEdgeEnd);
				result.setIsContainerValueToEdgeEnd(isContainerValueToEdgeEnd);
				result.setFromEdgeEnd(fromEdgeEnd);
				result.setToEdgeEnd(toEdgeEnd);
				result.setUrr(urr);
				return result;
			} else {
				return null;
			}
		}
	}
}
