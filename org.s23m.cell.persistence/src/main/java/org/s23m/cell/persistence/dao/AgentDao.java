package org.s23m.cell.persistence.dao;

import org.s23m.cell.persistence.model.Agent;

public interface AgentDao {

	/**
	 * Retrieves the {@link Agent} with the given URR
	 *
	 * @param urr
	 */
	Agent get(String urr);

	/**
	 * Inserts a new row into the database corresponding to the provided {@link Agent}
	 *
	 * @param entity
	 */
	void insert(Agent entity);

	/**
	 * Updates the row corresponding to the provided {@link Agent}
	 *
	 * @param entity
	 */
	void update(Agent entity);
}
