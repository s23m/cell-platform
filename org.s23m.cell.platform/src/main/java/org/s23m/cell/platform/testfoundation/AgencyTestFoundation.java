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
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.platform.api.Instantiation;
import org.s23m.cell.platform.models.Agency;
import org.s23m.cell.platform.models.CellEngineering;
import org.s23m.cell.platform.models.CellPlatformAgent;
import org.s23m.cell.platform.models.CellPlatformDomain;
import org.s23m.cell.platform.models.LogicalFormula;

public class AgencyTestFoundation {

	public static Set instantiateFeature() {
		final Set ithanku = org.s23m.cell.platform.api.Instantiation.addAgent("i thank u", "set of i thank u");
		final Set dev = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "development", "set of development");
		final Set test = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "test", "set of test");
		final Set prod = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "production", "set of production");
		final Set ithanku1 = org.s23m.cell.platform.api.Instantiation.addAgent("i thank u #1", "set of i thank u #1");

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


		final Set snowyNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("snowyNativeLanguage", "snowyNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set snowyNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				snowyNativeLanguageSI,
				CellPlatformDomain.agent,
				snowy,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformDomain.nativeLanguage,
				CellPlatformAgent.englishLanguage,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set perspective_snowy_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_snowy_cellplatform", "perspective_snowy_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_snowy_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_snowy_cellplatformSI,
				S23MSemanticDomains.from,
				snowy,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				S23MSemanticDomains.to,
				ithanku,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set scruffyNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("scruffyNativeLanguage", "scruffyNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set scruffyNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				scruffyNativeLanguageSI,
				CellPlatformDomain.agent,
				scruffy,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformDomain.nativeLanguage,
				CellPlatformAgent.englishLanguage,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set perspective_scruffy_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_scruffy_cellplatform", "perspective_scruffy_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_scruffy_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_scruffy_cellplatformSI,
				S23MSemanticDomains.from,
				scruffy,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				S23MSemanticDomains.to,
				ithanku,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set louiseNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("louiseNativeLanguage", "louiseNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set louiseNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				louiseNativeLanguageSI,
				CellPlatformDomain.agent,
				louise,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformDomain.nativeLanguage,
				CellPlatformAgent.englishLanguage,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set perspective_louise_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_louise_cellplatform", "perspective_louise_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_louise_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_louise_cellplatformSI,
				S23MSemanticDomains.from,
				louise,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				S23MSemanticDomains.to,
				ithanku,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set ithankuNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("ithankuNativeLanguage", "ithankuNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set ithankuNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				ithankuNativeLanguageSI,
				CellPlatformDomain.agent,
				ithanku,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformDomain.nativeLanguage,
				CellPlatformAgent.s23mLanguage,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set perspective_ithanku_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_ithanku_cellplatformSI", "perspective_ithanku_cellplatformSI", Instantiation.toSemanticDomain(dev));
		final Set perspective_ithanku_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_ithanku_cellplatformSI,
				S23MSemanticDomains.from,
				ithanku,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				S23MSemanticDomains.to,
				CellPlatformAgent.s23mCellPlatform,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set replication_ithankuSI = Instantiation.addDisjunctSemanticIdentitySet("replication_ithanku_1", "replication_ithanku_1", Instantiation.toSemanticDomain(dev));
		final Set replication_ithanku = Instantiation.arrow(Agency.replication,
				replication_ithankuSI,
				CellPlatformDomain.original,
				ithanku,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformDomain.copy,
				ithanku1,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);

		return snowy;
	}
}
