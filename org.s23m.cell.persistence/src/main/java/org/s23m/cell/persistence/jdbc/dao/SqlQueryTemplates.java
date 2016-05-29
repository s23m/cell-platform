package org.s23m.cell.persistence.jdbc.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class SqlQueryTemplates {

	private static final String EQUALS_PARAMETER = "=?";

	/**
	 * Creates a SELECT statement which looks up the record by the provided identifier.
	 */
	public static String createSelectByIdQueryTemplate(final Class<?> entityClass, final String identifierColumn) {
		final StringBuilder builder = new StringBuilder("SELECT * FROM ");
		builder.append(entityClass.getSimpleName());
		builder.append(" WHERE ");
		builder.append(identifierColumn);
		builder.append(EQUALS_PARAMETER);
		return builder.toString();
	}

	/**
	 * Creates an UPDATE statement template
	 *
	 * @param entityClass
	 * @param columnNames array of column names, with the last one being used for the WHERE clause
	 *  and all preceding ones being used in the SET clause
	 */
	public static String createUpdateStatementTemplate(final Class<?> entityClass, final String[] columnNames) {
		final StringBuilder builder = new StringBuilder("UPDATE ");
		builder.append(entityClass.getSimpleName());
		builder.append(" SET ");
		// set all values but the last one
		final int columnCount = columnNames.length;
		for (int i = 0; i < columnCount - 1; i++) {
			builder.append(columnNames[i]);
			builder.append(EQUALS_PARAMETER);
			if (i < columnCount - 2) {
				builder.append(",");
			}
		}
		// where clause
		builder.append(" WHERE ");
		builder.append(columnNames[columnCount - 1]);
		builder.append(EQUALS_PARAMETER);
		return builder.toString();
	}

	/**
	 * Creates an INSERT statement template
	 *
	 * @param entityClass
	 * @param columnNames
	 */
	public static String createInsertStatementTemplate(final Class<?> entityClass, final String[] columnNames) {
		final StringBuilder builder = new StringBuilder("INSERT INTO ");
		builder.append(entityClass.getSimpleName());
		builder.append(" (");

		final List<String> columnNamesList = Arrays.asList(columnNames);
		final Collector<CharSequence, ?, String> commaJoiner = Collectors.joining(",");
		builder.append(columnNamesList.stream().collect(commaJoiner));

		builder.append(") VALUES (");

		final List<String> placeHolders = Collections.nCopies(columnNamesList.size(), "?");
		builder.append(placeHolders.stream().collect(commaJoiner));

		builder.append(")");
		return builder.toString();
	}

}
