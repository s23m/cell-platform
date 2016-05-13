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
	 * Save or update the given {@link Edge}
	 * 
	 * @param entity
	 */
	void saveOrUpdate(Edge entity);
}
