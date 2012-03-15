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

package org.s23m.cell.core;

import static org.s23m.cell.api.InformationQualityLogic.is_TRUE;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.impl.SemanticDomainCode;

public final class F_IqLogic {

	public static Set isEqualTo(final Set a, final Set b) {
		if (a.isEqualTo(b)) {
			return S23MSemanticDomains.is_TRUE;
		} else {
			return S23MSemanticDomains.is_FALSE;
		}
	}

	private static Set isEqualToRepresentation(final Set a, final Set b) {
		if (a.isEqualToRepresentation(b)) {
			return S23MSemanticDomains.is_TRUE;
		} else {
			return S23MSemanticDomains.is_FALSE;
		}
	}

	public static Set isEqualTo(final Set a, final Set b, final Set equivalenceClass) {
		if (equivalenceClass.isEqualToRepresentation(S23MSemanticDomains.semanticIdentity)) {
			return isEqualTo(a,b);
		} else {
			if (equivalenceClass.isEqualToRepresentation(S23MSemanticDomains.identifier)) {
				return isEqualToRepresentation(a,b);
			}
			if (SemanticDomain.semanticIdentity.isSuperSetOf(equivalenceClass.category()).is_TRUE()) {
				Set aSemantics = a;
				if (SemanticDomain.semanticIdentity.isSuperSetOf(a.category()).is_FALSE()) {
					aSemantics = a.semanticIdentity();
				}
				Set bSemantics = b;
				if (SemanticDomain.semanticIdentity.isSuperSetOf(b.category()).is_FALSE()) {
					bSemantics = b.semanticIdentity();
				}
				return isEqualTo(SemanticDomainCode.transformSemanticRoleToEquivalenceClass(aSemantics),(SemanticDomainCode.transformSemanticRoleToEquivalenceClass(bSemantics)));
			}
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}

	private static int order(final Set set) {
		if (set.isEqualTo(S23MSemanticDomains.is_NOTAPPLICABLE))	{
			return 1;
		}
		if (set.isEqualTo(S23MSemanticDomains.is_FALSE))	{
			return 2;
		}
		if (set.isEqualTo(S23MSemanticDomains.is_UNKNOWN))	{
			return 3;
		}
		if (set.isEqualTo(S23MSemanticDomains.is_TRUE))	{
			return 4;
		}
		return 1;
	}

	public static Set isGreaterThan(final Set a, final Set b) {
		if 	(order(a) > order(b)) {
			return S23MSemanticDomains.is_TRUE;
		} else {
			return S23MSemanticDomains.is_FALSE;
		}
	}

	public static Set isSmallerThan(final Set a, final Set b) {
		return isGreaterThan(b, a);
	}

	public static Set maximum(final Set a, final Set b) {
		if (isGreaterThan(a,b).isEqualTo(S23MSemanticDomains.is_TRUE)) {
			return a;
		} else {
			if (order(b) > 1) {
				return b;
			} else {
				return S23MSemanticDomains.is_NOTAPPLICABLE;
			}
		}
	}

	public static Set minimum(final Set a, final Set b) {
		if (isGreaterThan(a,b).isEqualTo(S23MSemanticDomains.is_FALSE)) {
			return a;
		} else {
			if (order(b) > 1) {
				return b;
			} else {
				return S23MSemanticDomains.is_NOTAPPLICABLE;
			}
		}
	}

	public static Set not(final Set set) {
		if (set.properClass().isEqualTo(S23MSemanticDomains.orderedSet)) {
			final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
			for (final Set element: set) {
				result.add(not(element));
			}
			return result;
		} else {
			if (set.isEqualTo(S23MSemanticDomains.is_UNKNOWN)) {return S23MSemanticDomains.is_NOTAPPLICABLE;}
			if (set.isEqualTo(S23MSemanticDomains.is_NOTAPPLICABLE)) {return S23MSemanticDomains.is_UNKNOWN;}
			if (set.isEqualTo(S23MSemanticDomains.is_FALSE)) {return S23MSemanticDomains.is_TRUE;}
			if (set.isEqualTo(S23MSemanticDomains.is_TRUE)) {return S23MSemanticDomains.is_FALSE;}
			return S23MSemanticDomains.is_NOTAPPLICABLE;
		}

	}

	public static Set and(final Set a, final Set b) {
		if (a.properClass().isEqualTo(S23MSemanticDomains.orderedSet)) {
			and(and(a),b);
		}
		if (b.properClass().isEqualTo(S23MSemanticDomains.orderedSet)) {
			and(a,and(b));
		}
		return minimum(a,b);
	}
	public static Set or(final Set a, final Set b) {
		if (a.properClass().isEqualTo(S23MSemanticDomains.orderedSet)) {
			or(or(a),b);
		}
		if (b.properClass().isEqualTo(S23MSemanticDomains.orderedSet)) {
			or(a,or(b));
		}
		return maximum(a,b);
	}

	public static Set and(final Set orderedSet) {
		if (orderedSet.properClass().isEqualTo(S23MSemanticDomains.orderedSet) && orderedSet.size() > 0) {
			final OrderedSet b = new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
			final Set a = orderedSet.extractFirst();
			for (final Set element : orderedSet) {
				if (!element.isEqualToRepresentation(a)) {
					b.add(element);
				}
			}
			if (b.size() > 1) {
				return and(a, and(b));
			} else
				if (b.size() == 1) {
					return and(a, b.extractFirst());
				} else {
					return a;
				}
		} else {
			if (!orderedSet.properClass().isEqualTo(S23MSemanticDomains.orderedSet)) {
				return and(orderedSet, orderedSet);
			} else {
				return 	S23MSemanticDomains.is_NOTAPPLICABLE;
			}
		}
	}

	public static Set or(final Set orderedSet) {
		if (orderedSet.properClass().isEqualTo(S23MSemanticDomains.orderedSet) &&  orderedSet.size() > 0) {
			final OrderedSet b = new OrderedSet(F_Instantiation.identityFactory.createAnonymousIdentity());
			final Set a = orderedSet.extractFirst();
			for (final Set element : orderedSet) {
				if (!element.isEqualToRepresentation(a)) {
					b.add(element);
				}
			}
			if (b.size() > 1) {
				return or(a, or(b));
			} else
				if (b.size() == 1) {
					return or(a, b.extractFirst());
				} else {
					return a;
				}
		} else {
			if (!orderedSet.properClass().isEqualTo(S23MSemanticDomains.orderedSet)) {
				return or(orderedSet, orderedSet);
			} else {
				return 	S23MSemanticDomains.is_NOTAPPLICABLE;
			}
		}
	}
	public static Set minimum(final Set orderedSet) {
		return and(orderedSet);
	}
	public static Set maximum(final Set orderedSet) {
		return or(orderedSet);
	}

	public static Set isQuality(final Set set) {
		if (set.isEqualTo(S23MSemanticDomains.is_NOTAPPLICABLE)
				|| set.isEqualTo(S23MSemanticDomains.is_UNKNOWN)) {
			return S23MSemanticDomains.is_TRUE;
		} else {
			return S23MSemanticDomains.is_FALSE;
		}
	}
	public static Set isInformation(final Set set) {
		return(not(isQuality(set)));
	}
	public static Set includesValue(final Set set, final Set value, final Set equivalenceClass) {
		if (set.isEqualTo(value)) {
			return S23MSemanticDomains.is_TRUE;
		} else {
			if (set.properClass().isEqualTo(S23MSemanticDomains.orderedSet)) {
				Set result = S23MSemanticDomains.is_FALSE;
				for (final Set element: set) {
					if (is_TRUE(includesValue(element, value, equivalenceClass))) {
						result = S23MSemanticDomains.is_TRUE;
					}
				}
				return result;
			} else {
				return includesValue(set.filterInstances(), value, equivalenceClass);
			}
		}
	}

}