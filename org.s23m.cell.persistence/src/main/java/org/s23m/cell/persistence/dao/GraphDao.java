package org.s23m.cell.persistence.dao;

import org.s23m.cell.persistence.model.Graph;

public interface GraphDao {

	/**
	 * Retrieves the {@link Graph} with the given URR
	 *
	 * @param urr
	 */
	Graph get(String urr);

	/**
	 * Inserts a new row into the database corresponding to the provided {@link Graph}
	 *
	 * @param entity
	 */
	void insert(Graph entity);

	/**
	 * Updates the row corresponding to the provided {@link Graph}
	 *
	 * @param entity
	 */
	void update(Graph entity);
}
