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

import static org.s23m.cell.G.coreSets;

import java.util.List;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.SemanticDomain;

public final class F_Transaction {

	public static Set commitChangedSets() {
		for (final Set element : Graph.changedSets) {
			if (element.isDecommissioned().isEqualTo(coreSets.is_TRUE)) {
				((Graph) element.container()).delete(element);
			}
			if (element.category().isEqualTo(SemanticDomain.semanticIdentity)
					|| element.category().isEqualTo(SemanticDomain.semanticIdentitySet)
					|| element.category().isEqualTo(SemanticDomain.disjunctSemanticIdentitySet)
					//	|| iSet.category().isEqualTo(SemanticDomain.abstractSemanticRole)
					|| element.category().isEqualTo(SemanticDomain.semanticRole)
					|| element.category().isEqualTo(SemanticDomain.variantDisjunctSemanticIdentitySet)) {
				if (element.hasNewName().isEqualTo(coreSets.is_TRUE)) {
					((IdentityImpl)element.identity()).commitNewName();
				}
				if (element.hasNewPluralName().isEqualTo(coreSets.is_TRUE)) {
					((IdentityImpl) element.identity()).commitNewPluralName();
				}
			}
			((Graph) element).clearModificationState();
		}

		final List<Set> c = Graph.changedSets.asList();
		for (final Set element : c) {
			Graph.changedSets.remove(element);
		}

		final int max = Graph.changedSets.size();
		return Graph.changedSets;
	}

	public static Set rollbackChangedSets() {
		for (final Set element : Graph.changedSets) {
			//final Set iSet = Graph.changedSets.getFirstElement();
			if (element.isNewInstance().isEqualTo(coreSets.is_TRUE)) {
				((Graph) element.container()).delete(element);
			}
			if (element.category().isEqualTo(SemanticDomain.semanticIdentity)
					|| element.category().isEqualTo(SemanticDomain.semanticIdentitySet)
					|| element.category().isEqualTo(SemanticDomain.disjunctSemanticIdentitySet)
					//	|| iSet.category().isEqualTo(SemanticDomain.abstractSemanticRole)
					|| element.category().isEqualTo(SemanticDomain.semanticRole)
					|| element.category().isEqualTo(SemanticDomain.variantDisjunctSemanticIdentitySet)) {
				if (element.hasNewName().isEqualTo(coreSets.is_TRUE)) {
					((IdentityImpl) element.identity()).rollbackNewName();
				}
				if (element.hasNewPluralName().isEqualTo(coreSets.is_TRUE)) {
					((IdentityImpl) element.identity()).rollbackNewPluralName();
				}
			}
			((Graph) element).clearModificationState();
		}

		final List<Set> c = Graph.changedSets.asList();
		for (final Set element : c) {
			Graph.changedSets.remove(element);
		}

		final int max = Graph.changedSets.size();
		return Graph.changedSets;
	}



}

