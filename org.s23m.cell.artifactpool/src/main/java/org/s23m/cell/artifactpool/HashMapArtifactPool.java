/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is S23M.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
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
