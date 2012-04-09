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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api;

import org.s23m.cell.Set;
import org.s23m.cell.core.F_SetAlgebra;

public class SetAlgebra {
	public static Set complement(final Set set1, final Set set2) {
		return F_SetAlgebra.complement(set1, set2);
	}
	public static Set intersection(final Set set1, final Set set2) {
		return  F_SetAlgebra.intersection(set1, set2);
	}
	public static Set isElementOf(final Set set1, final Set set2) {
		return F_SetAlgebra.isElementOf(set1, set2);
	}
	public static Set transformToOrderedSetOfSemanticIdentities(final Set set) {
		return F_SetAlgebra.transformToOrderedSetOfSemanticIdentities(set);
	}
	public static Set union(final Set set1, final Set set2) {
		return  F_SetAlgebra.union(set1, set2);
	}
	public static Set unionOfconnectingArrows(final Set set1, final Set set2) {
		return F_SetAlgebra.unionOfconnectingArrows( set1, set2);
	}

}
