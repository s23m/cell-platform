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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;

public final class F_Semantics {

	private static Set transformToUniqueSemanticIdentity(final Set a) {
		if (a.flavor().isEqualTo(GmodelSemanticDomains.orderedSet)) {
			final Set aS = a.transformToOrderedSetOfSemanticIdentities();
			if (aS.size() == 1) {
				return aS.extractFirst();
			} else {
				return GmodelSemanticDomains.is_NOTAPPLICABLE;
			}
		}
		else {
			if (a.isASemanticIdentity()) {
				return a;
			} else {
				return a.semanticIdentity();
			}
		}
	}

	public static Set $D(final Set a, final Set b) {
		final Set a1 = transformToUniqueSemanticIdentity(a);
		final Set b1 = transformToUniqueSemanticIdentity(b);
		if (a1.isEqualTo(b1)) {
			return a1;
		} else {
			return F_IqLogic.and(a1,b1);
		}
	}

	public static Set $C(final Set a, final Set b) {
		if (a.isASemanticIdentity()) {
			if (a.isEqualTo(b)) {

			}
		}
		if (a.isASemanticIdentity() && b.isASemanticIdentity()) {

		}
		return a;
	}


}

