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
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.objectpool;

import java.util.UUID;

@SuppressWarnings("serial")
public class PersonalObjectPool extends GenericObjectPool {

	private final UUID userId;

	public PersonalObjectPool(final UUID userId) {
		this.userId = userId;
	}
	
	/* (non-Javadoc)
	 * @see org.s23m.cell.connector.mediator.ObjectPool#addArtifact(java.util.UUID, org.s23m.cell.connector.mediator.ObjectPoolArtifact)
	 */
	@Override
	public void addArtifact(final String key, final ObjectPoolArtifact artifact) {
		final ObjectPoolArtifact val = poolMap.get(key);
		if (val != null) {
			poolMap.replace(key, val, artifact);
		} else {
			poolMap.putIfAbsent(key, artifact);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.s23m.cell.connector.mediator.ObjectPool#getArtifact(java.util.UUID)
	 */
	@Override
	public ObjectPoolArtifact getArtifact(final String key) throws IllegalArgumentException {
		if (poolMap.containsKey(key)) {
			return poolMap.get(key);
		} else {
			throw new IllegalArgumentException("Non existent container");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalObjectPool other = (PersonalObjectPool) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
