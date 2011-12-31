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

import org.s23m.cell.Identity;
import org.s23m.cell.Set;

public final class EventImpl extends OrderedPair {

	private final Set generatingSet;
	private final Set generatingElement;

	protected EventImpl(final Identity semanticIdentity, final Set category, final Set generatingElement, final Set generatingSet) {
		super(semanticIdentity, category);
		this.generatingElement = generatingElement;
		this.generatingSet = generatingSet;
	}

	@Override
	public Set generatingSet(){
		if (this.category().isEqualTo(coreSets.elementAdded)
				|| this.category().isEqualTo(coreSets.elementRemoved) ) {
			return generatingSet;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}
	@Override
	public Set generatingElement(){
		if (this.category().isEqualTo(coreSets.elementAdded)
				|| this.category().isEqualTo(coreSets.elementRemoved)) {
			return generatingElement;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}
	@Override
	public Set setMaintenanceCommand(){
		if (this.category().isEqualTo(coreSets.elementAdded)
				|| this.category().isEqualTo(coreSets.elementRemoved) ) {
			return this.category;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}
}
