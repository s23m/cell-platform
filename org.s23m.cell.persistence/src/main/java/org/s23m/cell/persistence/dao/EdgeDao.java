package org.s23m.cell.persistence.dao;

import org.s23m.cell.persistence.model.Edge;

public interface EdgeDao {

	/**
	 * Retrieves the {@link Edge} with the given URR
	 *
	 * @param urr
	 */
	Edge get(String urr);

	/**
	 * Inserts a new row into the database corresponding to the provided {@link Edge}
	 *
	 * @param entity
	 */
	void insert(Edge entity);

	/**
	 * Updates the row corresponding to the provided {@link Edge}
	 *
	 * @param entity
	 */
	void update(Edge entity);
}
