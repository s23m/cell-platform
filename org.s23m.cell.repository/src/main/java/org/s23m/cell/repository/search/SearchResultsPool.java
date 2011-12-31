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
 * The Original Code is Gmodel.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.s23m.cell.artifactpool.HashMapObjectPool;
import org.s23m.cell.artifactpool.ObjectPool;
import org.s23m.cell.common.search.SearchResult;

public final class SearchResultsPool {

	private final ObjectPool<String, List<SearchResult>> pool;

	private final Map<String, List<String>> instanceToQueryKeyMap; // map instance UUIs to search query keys

	private static class SearchResultsPoolHolder {
		public static final SearchResultsPool INSTANCE = new SearchResultsPool();
	}

	public static SearchResultsPool getInstance() {
		return SearchResultsPoolHolder.INSTANCE;
	}

	private SearchResultsPool() {
		pool = new HashMapObjectPool<String, List<SearchResult>>();
		instanceToQueryKeyMap = new HashMap<String, List<String>>();
	}

	@Override
	public Object clone() throws CloneNotSupportedException  {
		throw new CloneNotSupportedException();
	}

	public boolean contains(final String query) {
		return pool.contains(query);
	}

	/**
	 * Expire the pool
	 */
	public void expire() {
		pool.expire();
		synchronized(instanceToQueryKeyMap) {
			instanceToQueryKeyMap.clear();
		}
	}

	public List<SearchResult> get(final String query) {
		return pool.get(query);
	}

	public void put(final String query, final List<SearchResult> results) {
		pool.put(query, results);
		synchronized(instanceToQueryKeyMap) {
			for(final SearchResult r : results) {
				final String uuid = r.getInstanceIdentity().getUUID().toString();
				if (instanceToQueryKeyMap.containsKey(uuid)) {
					final List<String> qKeys = instanceToQueryKeyMap.get(uuid);
					if (!qKeys.contains(query)) {
						qKeys.add(query);
					}
				} else {
					final List<String> qKeys = new ArrayList<String>();
					qKeys.add(query);
					instanceToQueryKeyMap.put(uuid, qKeys);
				}
			}
		}
	}

	/**
	 * Remove all search results which contain the instance UUID
	 * 
	 * @param instanceID
	 */
	public void removeFromPool(final String instanceUUID) {
		synchronized(instanceToQueryKeyMap) {
			if (instanceToQueryKeyMap.containsKey(instanceUUID)) {
				final List<String> qKeys = instanceToQueryKeyMap.get(instanceUUID);
				for (final String k : qKeys) {
					pool.remove(k);
				}
			}
		}
	}

}
