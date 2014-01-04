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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.core.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.cliffc.high_scale_lib.NonBlockingHashMap;
import org.s23m.cell.core.concurrency.ehcache.LockType;
import org.s23m.cell.core.concurrency.ehcache.StripedReadWriteLockSync;
import org.s23m.cell.core.concurrency.ehcache.Sync;

/**
 * Slightly modified version of the class presented in the blog post
 * 
 * http://dhruba.name/2011/06/11/concurrency-pattern-concurrent-multimaps-in-java/
 * 
 * ADVANTAGES
 * - Strongly consistent. Implements correct mutual exclusion of calls.
 * - Uses {@link NonBlockingHashMap} instead of {@link ConcurrentHashMap} so the backing cache member does not block at all. Far more efficient and scalable than {@link ConcurrentHashMap}.
 * - The read calls are completely non-blocking even at the cache structure level.
 * - The {@link #put(Object, Object)} and {@link #remove(Object, Object)} methods do block but only for certain paths. There are paths through these methods which won't block at all.
 *   The {@link #put(Object, Object)} method only blocks if the {@link NonBlockingHashMap#putIfAbsent(Object, Object)} fails and the {@link #remove(Object, Object)} only blocks if there is something there to remove.
 * And to save the best for last, there is no longer any blocking at the cache level. We now apply mutual exclusion only at the key level.
 * This implementation has the best of all worlds really as long as the copy on write approach is acceptable to you.
 * 
 * DISADVANTAGES
 * - Fundamentally being a copy on write approach it does more allocation than a mutative approach.
 */
public class StripedLockArrayListMultiMap<K, V> {

	/*
	 * Replacement for name.dhruba.kb.concurrency.striping.StripedLockProvider
	 * (source code not available)
	 */
	private final StripedReadWriteLockSync stripedLockProvider;

	private final ConcurrentMap<K, List<V>> cache;

	private static final LockType WRITE_LOCK = LockType.WRITE;

	public StripedLockArrayListMultiMap() {
		this(StripedReadWriteLockSync.DEFAULT_NUMBER_OF_MUTEXES);
	}

	public StripedLockArrayListMultiMap(final int numberOfStripes) {
		stripedLockProvider = new StripedReadWriteLockSync(numberOfStripes);
		cache = new NonBlockingHashMap<K, List<V>>();
	}

	public boolean containsKey(final K key) {
		return cache.containsKey(key);
	}

	public List<V> get(final K k) {
		return cache.get(k);
	}

	public List<V> remove(final K key) {
		final Sync sync = stripedLockProvider.getSyncForKey(key);
		try {
			sync.lock(WRITE_LOCK);
			return cache.remove(key);
		} finally {
			sync.unlock(WRITE_LOCK);
		}
	}

	public void put(final K key, final V value) {
		List<V> list = Collections.singletonList(value);
		final List<V> oldList = cache.putIfAbsent(key, list);
		if (oldList != null) {
			final Sync sync = stripedLockProvider.getSyncForKey(key);
			try {
				sync.lock(WRITE_LOCK);
				list = cache.get(key);
				if (list == null || list.isEmpty()) {
					list = new ArrayList<V>();
				} else {
					list = new ArrayList<V>(list);
				}
				list.add(value);
				cache.put(key, list);
			} finally {
				sync.unlock(WRITE_LOCK);
			}
		}
	}

	public boolean remove(final K key, final V value) {
		List<V> list = cache.get(key);
		if (list == null) {
			return false;
		}
		final Sync sync = stripedLockProvider.getSyncForKey(key);
		try {
			sync.lock(WRITE_LOCK);
			list = cache.get(key);
			if (list == null) {
				return false;
			} else if (list.isEmpty()) {
				cache.remove(key);
				return false;
			} else {
				list = new ArrayList<V>(list);
				final boolean removed = list.remove(value);
				if (removed) {
					if (list.isEmpty()) {
						cache.remove(key);
					} else {
						cache.put(key, list);
					}
				}
				return removed;
			}
		} finally {
			sync.unlock(WRITE_LOCK);
		}
	}
}
