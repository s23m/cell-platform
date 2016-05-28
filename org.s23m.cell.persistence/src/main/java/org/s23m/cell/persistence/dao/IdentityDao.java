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
	 * Inserts a new row into the database corresponding to the provided {@link Identity}
	 *
	 * @param entity
	 */
	void insert(Identity entity);

	/**
	 * Updates the row corresponding to the provided {@link Identity}
	 *
	 * @param entity
	 */
	void update(Identity entity);
}
