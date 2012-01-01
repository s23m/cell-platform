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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@SuppressWarnings("serial")
public abstract class GenericObjectPool implements ObjectPool {

	protected final ConcurrentMap<String, ObjectPoolArtifact> poolMap; //concurrent map that can be shared
	
	protected GenericObjectPool() {
		poolMap = new ConcurrentHashMap<String, ObjectPoolArtifact>();
	}
	
	/* (non-Javadoc)
	 * @see org.s23m.cell.connector.mediator.ObjectPool#addArtifact(java.util.UUID, org.s23m.cell.connector.mediator.ObjectPoolArtifact)
	 */
	public void addArtifact(final String key, final ObjectPoolArtifact artifact) {
		final ObjectPoolArtifact val = poolMap.get(key);
		if (val != null) {
			poolMap.replace(key, val, artifact);
		} else {
			poolMap.putIfAbsent(key, artifact);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.s23m.cell.connector.mediator.ObjectPool#clear()
	 */
	public void clear() {
		poolMap.clear();
	}
	
	/* (non-Javadoc)
	 * @see org.s23m.cell.connector.mediator.ObjectPool#getArtifact(java.util.UUID)
	 */
	public ObjectPoolArtifact getArtifact(final String key) throws IllegalArgumentException {
		if (poolMap.containsKey(key)) {
			return poolMap.get(key);
		} else {
			throw new IllegalArgumentException("Non existent container");
		}
	}
	
	/* (non-Javadoc)
	 * @see org.s23m.cell.connector.mediator.ObjectPool#removeArtifact(java.util.UUID)
	 */
	public void removeArtifact(final String key) {
		if (poolMap.containsKey(key))  {
			poolMap.remove(key);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.s23m.cell.connector.mediator.ObjectPool#hasArtifact(java.util.UUID)
	 */
	public boolean hasArtifact(final String key) {
		return poolMap.containsKey(key);
	}	
	
}
