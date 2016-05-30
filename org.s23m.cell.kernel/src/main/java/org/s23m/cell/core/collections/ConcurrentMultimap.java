package org.s23m.cell.core.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A simple concurrent Multimap implementation. Each key is associated with a list.
 *
 * Based on the PartiallyBlockingCopyOnWriteArrayListMultiMap implementation from
 *
 * http://dhruba.name/2011/06/11/concurrency-pattern-concurrent-multimaps-in-java/
 *
 * with necessary corrections.
 *
 * @param <K> type of the key
 * @param <V> type of the elements in the lists associated with each key
 *
 * @see https://en.wikipedia.org/wiki/Multimap
 */
public final class ConcurrentMultimap<K, V> {

	/**
	 * Underlying map
	 */
	private final ConcurrentMap<K, List<V>> cache;

	/**
	 * Constructor
	 */
	public ConcurrentMultimap() {
		this.cache = new ConcurrentHashMap<K, List<V>>();
	}

	/**
	 * Indicates whether a mapping exists for the specified key.
	 *
	 * @param key
	 * @return a boolean indicating whether the map contains the specified key
	 */
	public boolean containsKey(final K key) {
		return cache.containsKey(key);
	}

	/**
	 * Retrieves the list associated with the specified key.
	 *
	 * @param key
	 * @return the associated list
	 */
	public List<V> get(final K key) {
		return cache.get(key);
	}

	/**
	 * Removes the list associated with the specified key.
	 *
	 * @param key
	 * @return the list previously associated with the specified key.
	 */
	public List<V> remove(final K key) {
		synchronized (cache) {
			return cache.remove(key);
		}
	}

	/**
	 * Adds the value to the list associated with the specified key
	 *
	 * @param key
	 * @param value
	 */
	public void put(final K key, final V value) {
		List<V> list = new ArrayList<V>(Collections.singletonList(value));

		synchronized (cache) {

			final List<V> oldList = cache.putIfAbsent(key, list);
			if (oldList != null) {
				// existing mapping was found - add to it
				list = cache.get(key);
				if (list == null || list.isEmpty()) {
					list = new ArrayList<V>();
				} else {
					list = new ArrayList<V>(list);
				}
				list.add(value);
				cache.put(key, list);
			}
		}
	}

	/**
	 * Removes the value to the list associated with the specified key (if it exists),
	 * and indicates whether it was removed.
	 *
	 * @param key
	 * @param value
	 */
	public boolean remove(final K key, final V value) {
		List<V> list = cache.get(key);
		if (list == null) {
			return false;
		}
		synchronized (cache) {
			list = cache.get(key);
			if (list == null) {
				return false;
			}
			if (list.isEmpty()) {
				cache.remove(key);
				return false;
			}
			final boolean removed = list.remove(value);
			if (removed) {
				if (list.isEmpty()) {
					cache.remove(key);
				} else {
					list = new ArrayList<V>(list);
					cache.put(key, list);
				}
			}
			return removed;
		}
	}
}