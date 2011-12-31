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

package org.s23m.cell.artifactpool;

import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapObjectPool<K,V> implements ObjectPool<K,V> {

	private static final int MAX_SIZE = 1000;
	  
	private final Map<K,V> cache;
	
	public HashMapObjectPool() {
		cache = new LinkedHashMap<K,V>(MAX_SIZE, 0.75F, true) {

			private static final long serialVersionUID = 1L;

			@Override
			@SuppressWarnings("unchecked")
			protected boolean removeEldestEntry(final Map.Entry eldest) {
		      return size() > MAX_SIZE;
		    }
		};
	}
	
	public boolean contains(final K key) {
		synchronized(cache) {
		    return cache.containsKey(key);
		}
	}
	
	public void expire() {
		synchronized(cache) {
			cache.clear();
		}
	}

	public V get(final K key) {
		synchronized(cache) {
		    return cache.get(key);
		}	
	}

	public void put(final K key, final V artifact) {
		synchronized(cache) {
		    if (!cache.containsKey(key)) {
		    	cache.put(key, artifact);
		    }
		}
	}
	
	public void remove(final K key) {
		synchronized(cache) {
			cache.remove(key);
		}
	}

	public int size() {
		synchronized(cache) {
			return cache.size();
		}
	}

}
