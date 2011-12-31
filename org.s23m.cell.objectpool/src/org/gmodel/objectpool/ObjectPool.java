/* ***** BEGIN LICENSE BLOCK *****
 * Version: SMTL 1.0
 *
 * The contents of this file are subject to the Sofismo Model Technology License Version
 * 1.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.sofismo.ch/SMTL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis.
 * See the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is Gmodel Semantic Extensions Edition.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.objectpool;

import java.io.Serializable;
import java.util.UUID;

public interface ObjectPool extends Serializable {

	/**
	 * Add an container to the pool
	 * @param key
	 * @param container
	 */
	public void addArtifact(final String key, final ObjectPoolArtifact artifact);

	
	/**
	 * Clear pool
	 */
	public void clear();
	
	/**
	 * Return an container with matching UUID
	 * @param key
	 * @return
	 * @throws IllegalArgumentException
	 */
	public ObjectPoolArtifact getArtifact(final String key) throws IllegalArgumentException;

	/**
	 * Check if an container with matching UUUID exists
	 * @param key
	 * @return true if an container with matching UUID is found
	 */
	public boolean hasArtifact(final String key);

	/**
	 * Remove an container with matching UUID
	 * @param key
	 */
	public void removeArtifact(final String key);

}