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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.artifactpool;

import junit.framework.TestCase;

public class HashMapArtifactPoolTest extends TestCase {
	
	private HashMapObjectPool<Integer, String> pool;
	
	@Override
	protected void setUp() throws Exception {
		pool = new HashMapObjectPool<Integer, String>();
	}
	
	public void testContains() {
		Integer key = 1;
		assertFalse(pool.contains(key));
		pool.put(key, "artifact");
		assertTrue(pool.contains(key));
	}

	public void testExpire() {
		assertEquals(0, pool.size());
		Integer key = 1;
		pool.put(key, "artifact");
		assertEquals(1, pool.size());
		pool.expire();
		assertEquals(0, pool.size());
	}

	public void testPutAndGet() {
		Integer key = 1;
		String value = "artifact";
		pool.put(key, value);
		assertEquals(value, pool.get(key));
	}
	
	public void testRemove() {
		Integer key = 1;
		pool.put(key, "artifact");
		assertEquals(1, pool.size());
		pool.remove(1);
		assertEquals(0, pool.size());
	}
	
	public void testLimitedCacheSize() {
		for (int i = 0; i < 2000; i++) {
			pool.put(i, "Value " + i);
		}
		
		assertEquals(1000, pool.size());
	}

	
}
