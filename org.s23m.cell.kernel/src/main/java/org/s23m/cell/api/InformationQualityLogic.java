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
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.core.F_IqLogic;

public class InformationQualityLogic {

	public static Set and(final Set orderedSet) {
		return F_IqLogic.and(orderedSet);
	}
	public static Set and(final Set a, final Set b) {
		return F_IqLogic.and(a, b);
	}
	public static Set includesValue(final Set set, final Set value, final Set equivalenceClass) {
		return F_IqLogic.includesValue(set, value, equivalenceClass);
	}

	public static boolean is_FALSE(final Set set) {
		return set.isEqualTo(S23MSemanticDomains.is_FALSE);
	}

	public static boolean is_NOTAPPLICABLE(final Set set) {
		return set.isEqualTo(S23MSemanticDomains.is_NOTAPPLICABLE);
	}

	public static boolean is_TRUE(final Set set) {
		return set.isEqualTo(S23MSemanticDomains.is_TRUE);
	}

	public static boolean is_UNKNOWN(final Set set) {
		return set.isEqualTo(S23MSemanticDomains.is_UNKNOWN);
	}

	public static Set isEqualTo(final Set a, final Set b) {
		return F_IqLogic.isEqualTo(a, b);
	}
	public static Set isEqualTo(final Set a, final Set b, final Set equivalenceClass) {
		return F_IqLogic.isEqualTo(a, b, equivalenceClass);
	}

	public static Set isGreaterThan(final Set a, final Set b) {
		return F_IqLogic.isGreaterThan(a, b);
	}

	public static Set isInformation(final Set set) {
		return F_IqLogic.isInformation(set);
	}
	public static Set isQuality(final Set set) {
		return F_IqLogic.isQuality(set);
	}
	public static Set isSmallerThan(final Set a, final Set b) {
		return F_IqLogic.isSmallerThan(a, b);
	}

	public static Set maximum(final Set orderedSet) {
		return F_IqLogic.maximum(orderedSet);
	}

	public static Set maximum(final Set a, final Set b) {
		return F_IqLogic.maximum(a, b);
	}
	public static Set minimum(final Set orderedSet) {
		return F_IqLogic.minimum(orderedSet);
	}
	public static Set minimum(final Set a, final Set b) {
		return F_IqLogic.minimum(a, b);
	}
	public static Set not(final Set set) {
		return F_IqLogic.not(set);
	}
	public static Set or(final Set orderedSet) {
		return F_IqLogic.or(orderedSet);
	}
	public static Set or(final Set a, final Set b) {
		return F_IqLogic.or(a, b);
	}


}
