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
package org.s23m.cell.platform.api;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.Transaction;
import org.s23m.cell.kernel.testbench.TestSequence;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;

public class CellKernelTest extends TestCase {

	private static boolean agencyTestFoundationInitialised = false;

	@Override
	@Before
	protected void setUp() throws Exception {
		S23MPlatform.boot();
		if (!agencyTestFoundationInitialised) {
			AgencyTestFoundation.instantiateFeature();
			agencyTestFoundationInitialised = true;
		}
	}

	@Test
	public void testBasicTransactions() {
		TestSequence.run();
		Transaction.commitChangedSets();
		checkForRuntimeErrors();

		TestSequence.run();
		Transaction.commitChangedSets();
		checkForRuntimeErrors();

		TestSequence.run();
		Transaction.commitChangedSets();
		checkForRuntimeErrors();
	}


	private void checkForRuntimeErrors() {
		final Set runtimeErrors = Query.runtimeErrors();
		if (!runtimeErrors.isEmpty()) {
			final StringBuilder builder = new StringBuilder("The following runtime errors were encountered:\n");
			// TODO improve display of sets
			for (final Set set: runtimeErrors) {
				builder.append(set);
				builder.append("\n");
			}
			fail(builder.toString());
		}
	}
}