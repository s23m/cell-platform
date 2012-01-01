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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ObjectPoolRegistry {
	
	private static final ObjectPoolRegistry registry = new ObjectPoolRegistry();
	
	private final ConcurrentMap<UUID, ObjectPool> poolMap;
	
	private final ObjectPool sharedObjectPool;

	public static synchronized ObjectPoolRegistry getInstance() {
		if (registry == null) {
			throw new RuntimeException("No registry instance is available");
		} else {
			return registry;
		}
	}

	private ObjectPoolRegistry() {
		poolMap = new ConcurrentHashMap<UUID, ObjectPool>();
		sharedObjectPool = new SharedObjectPool();
	}
	
	public ObjectPool getPersonalObjectPool(final UUID uuid) {
		if (!poolMap.containsKey(uuid)) {
			poolMap.putIfAbsent(uuid, new PersonalObjectPool(uuid));
		} 
		return poolMap.get(uuid);
	}
	
	public ObjectPool getSharedObjectPool(final UUID uuid) {
		return sharedObjectPool;
	}
}
