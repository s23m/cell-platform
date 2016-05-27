package org.s23m.cell.persistence.dao;

import org.s23m.cell.persistence.model.Arrow;

public interface ArrowDao {

	/**
	 * Retrieves the {@link Arrow} with the given URR
	 *
	 * @param urr
	 */
	Arrow get(String urr);

	/**
	 * Inserts a new row into the database corresponding to the provided {@link Arrow}
	 *
	 * @param entity
	 */
	void insert(Arrow entity);

	/**
	 * Updates the row corresponding to the provided {@link Arrow}
	 *
	 * @param entity
	 */
	void update(Arrow entity);
}
