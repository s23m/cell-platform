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

package org.s23m.cell.artifactpool;

import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapArtifactPool<K,V> implements ArtifactPool<K,V> {

	private static final int MAX_SIZE = 100;
	  
	private final Map<K,V> cache;
	
	public HashMapArtifactPool() {
		cache = new LinkedHashMap<K,V>(MAX_SIZE, 0.75F, true) {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			protected boolean removeEldestEntry(Map.Entry eldest) {
		      return size() > MAX_SIZE;
		    }
		};
	}
	
	public boolean contains(K key) {
		synchronized(cache) {
		    return cache.containsKey(key);
		}
	}
	
	public void expire() {
		synchronized(cache) {
			cache.clear();
		}
	}

	public V get(K key) {
		synchronized(cache) {
		    return cache.get(key);
		}	
	}

	public void put(K key, V artifact) {
		synchronized(cache) {
		    if (!cache.containsKey(key)) {
		    	cache.put(key, artifact);
		    }
		}
	}
	
	public void remove(K key) {
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
