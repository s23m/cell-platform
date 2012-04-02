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
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.api;

import org.s23m.cell.Set;
import org.s23m.cell.platform.impl.F_CellQueries;

public class CellQueries {

	/**
	 * QUERIES
	 */

	public static final Set copyrightHolders(final Set cell) {
		return F_CellQueries.copyrightHolders(cell);
	}

	public static final Set availableLicenses(final Set cell) {
		return F_CellQueries.availableLicenses(cell);
	}

	public static final Set usageLicense(final Set user, final Set product) {
		return F_CellQueries.usageLicense(user, product);
	}
	public static final Set name(final Set cell, final Set session) {
		return F_CellQueries.name(cell, session);
	}
}
