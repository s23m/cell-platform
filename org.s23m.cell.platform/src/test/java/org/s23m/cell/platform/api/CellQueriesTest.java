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
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;

public class CellQueriesTest extends TestCase {

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
	public void testAgent() {
		// TODO
	}

	@Test
	public void testStage() {
		// TODO
	}

	@Test
	public void testIsCell() {
		// TODO
	}

	@Test
	public void testCopyrightHolders() {
		// TODO
	}

	@Test
	public void testAvailableLicenses() {
		// TODO
	}

	@Test
	public void testUsageLicense() {
		// TODO
	}

	@Test
	public void testNativeLanguage() {
		// TODO
	}

	@Test
	public void testPerspectiveLanguage() {
		// TODO
	}

	@Test
	public void testCellLanguage() {
		// TODO
	}

	@Test
	public void testNameInCellLanguage() {
		// TODO
	}

	@Test
	public void testNameInAgentLanguage() {
		// TODO
	}

	@Test
	public void testNameInPerspective() {
		// TODO
	}

	@Test
	public void testName() {
		// TODO
	}

	@Test
	public void testAbbreviations() {
		// TODO
	}

	@Test
	public void testNameAsString() {
		// TODO
	}

	@Test
	public void testPluralNameAsString() {
		// TODO
	}

	@Test
	public void testTransformToSemanticUnit() {
		// TODO
	}

}
