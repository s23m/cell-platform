package org.s23m.cell.core.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ConcurrentMultimapTest {

	/*
	 * Uses several competing threads which add to and remove from the same ArrayList (identified by a fixed key).
	 *
	 * As each thread has a unique name, no thread should remove a different thread's entry in the list.
	 */
	@Test
	public void testConcurrentUpdatesAreProperlyHandled() {

		final int threadCount = 5;

		final ConcurrentMultimap<String, String> multimap = new ConcurrentMultimap<>();

		final List<String> problems = new ArrayList<String>();

		final Runnable repeatedAddAndRemoveTask = new Runnable() {

			@Override
			public void run() {
				final String key = "key";

				final String name = Thread.currentThread().getName();
				for (int i = 0; i < 1000; i++) {
					multimap.put(key, name);
					final boolean wasRemoved = multimap.remove(key, name);
					if (!wasRemoved) {
						final String problem = "Thread '" + name + "' added but then could not remove its entry";
						problems.add(problem);

						System.err.println(problem);
					}
				}
			}
		};

		final List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < threadCount; i++) {
			final Thread t = new Thread(repeatedAddAndRemoveTask, "thread-" + i);
			list.add(t);
		}

		// start all threads
		for (final Thread thread : list) {
			thread.start();
		}

		// wait for them to complete
		for (final Thread thread : list) {
			try {
				thread.join();
			} catch (final InterruptedException e) {
				// ignore
			}
		}

		if (!problems.isEmpty()) {
			fail("There were " + problems.size() + " problems: " + problems);
		}
	}

	@Test
	public void testRemoveKey() {
		final ConcurrentMultimap<String, Integer> s = new ConcurrentMultimap<String, Integer>();
		final String key = "key";
		s.put(key, 1);
		s.put(key, 2);
		s.put(key, 3);

		assertEquals(createList(1,2,3), s.get(key));

		s.remove(key);

		assertNull(s.get(key));
	}

	@Test
	public void testRemoveKeyValue() {
		final ConcurrentMultimap<String, Integer> s = new ConcurrentMultimap<String, Integer>();
		final String key = "key";
		s.put(key, 1);
		s.put(key, 2);
		s.put(key, 3);

		assertEquals(createList(1,2,3), s.get(key));

		s.remove(key, 1);

		assertEquals(createList(2,3), s.get(key));

		s.remove(key, 2);

		assertEquals(createList(3), s.get(key));

		s.remove(key, 3);

		assertNull(s.get(key));
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> createList(final T... elements) {
		final List<T> result = new ArrayList<T>();
		for (final T element : elements) {
			result.add(element);
		}
		return result;
	}
}
