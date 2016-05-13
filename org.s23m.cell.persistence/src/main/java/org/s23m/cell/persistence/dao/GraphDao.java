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
	 * Save or update the given {@link Graph}
	 * 
	 * @param entity
	 */
	void saveOrUpdate(Graph entity);
}
