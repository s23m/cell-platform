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
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.testfoundation;

import org.s23m.cell.Set;
import org.s23m.cell.platform.api.Instantiation;
import org.s23m.cell.platform.models.CellEngineering;
import org.s23m.cell.platform.models.CellPlatformDomain;
import org.s23m.cell.platform.models.LogicalFormula;

public class AgencyTestFoundation {

	public static Set instantiateFeature() {
		final Set snowy = org.s23m.cell.platform.api.Instantiation.addAgent("Snowy", "set of Snowy");
		final Set dev1 = org.s23m.cell.platform.api.Instantiation.addStage(snowy, "dev1", "dev1s");
		final Set test1 = org.s23m.cell.platform.api.Instantiation.addStage(snowy, "test1", "test1s");
		final Set prod1 = org.s23m.cell.platform.api.Instantiation.addStage(snowy, "prod1", "prod1s");
		final Set snowySubA = org.s23m.cell.platform.api.Instantiation.addAgent(snowy, "Snowy Sub A", "set of Snowy Sub A");
		final Set dev0 = org.s23m.cell.platform.api.Instantiation.addStage(snowySubA, "dev0", "dev0s");
		final Set scruffy = org.s23m.cell.platform.api.Instantiation.addAgent("Scruffy", "set of Scruffy");
		org.s23m.cell.platform.api.Instantiation.addStage(scruffy, "dev2", "dev2s");
		org.s23m.cell.platform.api.Instantiation.addStage(scruffy, "test2", "test2s");
		org.s23m.cell.platform.api.Instantiation.addStage(scruffy, "prod2", "prod2s");
		final Set louise = org.s23m.cell.platform.api.Instantiation.addAgent("Louise", "set of Louise");
		final Set lf1 = Instantiation.addDisjunctSemanticIdentitySet("logical formula 1", "logical formula 1", Instantiation.toSemanticDomain(dev1));
		final Set formula1 = dev1.filter(CellEngineering.formula).extractFirst().addConcrete(CellEngineering.logicalFormula, lf1);
		final Set literal1 = formula1.addConcrete(LogicalFormula.and, CellPlatformDomain.literal);

		return snowy;
	}
}
