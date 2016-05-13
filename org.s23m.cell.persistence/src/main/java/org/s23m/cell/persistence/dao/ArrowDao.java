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
	 * Save or update the given {@link Arrow}
	 * 
	 * @param entity
	 */
	void saveOrUpdate(Arrow entity);
}
