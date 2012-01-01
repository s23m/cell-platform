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
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import junit.framework.TestCase;

import org.s23m.cell.common.search.BasicSearchIdentity;
import org.s23m.cell.common.search.BasicSearchResult;
import org.s23m.cell.common.search.SearchResult;
import org.s23m.cell.repository.search.SearchResultsPool;
import org.junit.Test;

public class SearchResultsPoolTest extends TestCase {

	// TODO improve this test
	@Test
	public void testIt() {
		final SearchResultsPool pool = SearchResultsPool.getInstance();
		final List<SearchResult> rList = new ArrayList<SearchResult>();
		final UUID id = UUID.randomUUID();
		rList.add(new BasicSearchResult("1", "1",
				new BasicSearchIdentity("", "",
						UUID.randomUUID()),
						new BasicSearchIdentity("", "",
								UUID.randomUUID())));
		rList.add(new BasicSearchResult("2", "2",
				new BasicSearchIdentity("", "",
						UUID.randomUUID()),
						new BasicSearchIdentity("", "",
								id)));
		pool.put("test1", rList);
		pool.put("test2", rList);
		assertTrue(pool.contains("test1"));
		pool.removeFromPool(id.toString());
		pool.removeFromPool(UUID.randomUUID().toString());
	}

}
