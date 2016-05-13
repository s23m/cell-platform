package org.s23m.cell.persistence.dao;

import org.s23m.cell.persistence.model.Identity;

public interface IdentityDao {

	/**
	 * Retrieves the {@link Identity} with the given UUID
	 * 
	 * @param uuid
	 */
	Identity get(String uuid);

	/**
	 * Save or update the given {@link Identity}
	 * 
	 * @param entity
	 */
	void saveOrUpdate(Identity entity);
}
