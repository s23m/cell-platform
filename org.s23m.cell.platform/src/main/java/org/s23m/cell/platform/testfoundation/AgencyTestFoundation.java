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
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.platform.api.Instantiation;
import org.s23m.cell.platform.impl.F_CellQueries;
import org.s23m.cell.platform.models.Agency;
import org.s23m.cell.platform.models.CellEngineering;
import org.s23m.cell.platform.models.CellPlatformAgent;
import org.s23m.cell.platform.models.CellPlatformDomain;
import org.s23m.cell.platform.models.LogicalFormula;
import org.s23m.cell.platform.models.ValidityInterval;

public class AgencyTestFoundation {

	public static Set instantiateFeature() {
		final Set ithanku = org.s23m.cell.platform.api.Instantiation.addAgent("i thank u", "set of i thank u");
		final Set dev = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "development", "set of development");
		final Set test = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "test", "set of test");
		final Set prod = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "production", "set of production");
		final Set ithanku1 = org.s23m.cell.platform.api.Instantiation.addAgent("i thank u #1", "set of i thank u #1");

		final Set ernst = org.s23m.cell.platform.api.Instantiation.addAgent("Ernst Zermelo", "Ernst Zermelo");
		final Set dev1 = org.s23m.cell.platform.api.Instantiation.addStage(ernst, "dev1", "dev1s");
		final Set test1 = org.s23m.cell.platform.api.Instantiation.addStage(ernst, "test1", "test1s");
		final Set prod1 = org.s23m.cell.platform.api.Instantiation.addStage(ernst, "prod1", "prod1s");
		final Set ernstSmartApp = org.s23m.cell.platform.api.Instantiation.addAgent(ernst, "Ernst's smart shopping app", "Ernst's smart shopping app");
		final Set dev0 = org.s23m.cell.platform.api.Instantiation.addStage(ernstSmartApp, "dev0", "dev0s");
		final Set samuel = org.s23m.cell.platform.api.Instantiation.addAgent("Samuel Eilenberg", "Samuel Eilenberg");
		org.s23m.cell.platform.api.Instantiation.addStage(samuel, "dev2", "dev2s");
		org.s23m.cell.platform.api.Instantiation.addStage(samuel, "test2", "test2s");
		org.s23m.cell.platform.api.Instantiation.addStage(samuel, "prod2", "prod2s");
		final Set christopher = org.s23m.cell.platform.api.Instantiation.addAgent("Christopher Strachey", "Christopher Strachey");
		final Set lf1 = Instantiation.addDisjunctSemanticIdentitySet("logical formula 1", "logical formula 1", Instantiation.toSemanticDomain(dev1));
		final Set formula1 = dev1.filter(CellEngineering.formula).extractFirst().addConcrete(CellEngineering.logicalFormula, lf1);
		final Set literal1 = formula1.addConcrete(LogicalFormula.and, CellPlatformDomain.literal);


		final Set snowyNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("ErnstNativeLanguage", "ErnstNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set snowyNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				snowyNativeLanguageSI,
				CellPlatformDomain.agent,
				ernst,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformDomain.nativeLanguage,
				CellPlatformAgent.deutschLanguage,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);
		final Set perspective_snowy_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_Ernst_cellplatform", "perspective_Ernst_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_snowy_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_snowy_cellplatformSI,
				S23MSemanticDomains.from,
				ernst,
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
		final Set scruffyNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("SamuelNativeLanguage", "SamuelNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set scruffyNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				scruffyNativeLanguageSI,
				CellPlatformDomain.agent,
				samuel,
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
		final Set perspective_scruffy_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_Samuel_cellplatform", "perspective_Samuel_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_scruffy_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_scruffy_cellplatformSI,
				S23MSemanticDomains.from,
				samuel,
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
		final Set louiseNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("ChristopherNativeLanguage", "ChristopherNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set louiseNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				louiseNativeLanguageSI,
				CellPlatformDomain.agent,
				christopher,
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
		final Set perspective_louise_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_Christopher_cellplatform", "perspective_Christopher_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_louise_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_louise_cellplatformSI,
				S23MSemanticDomains.from,
				christopher,
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
				CellPlatformAgent.cellMetaLanguage,
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

		final Set kernelLicense = F_CellQueries.availableLicenses(Root.root).extractFirst();
		final Set platformLicense = F_CellQueries.availableLicenses(CellEngineering.language).extractFirst();
		final Set platformLicense2 = F_CellQueries.availableLicenses(ValidityInterval.validFromTimestamp).extractFirst();
		final Set platformLicense3 = F_CellQueries.availableLicenses(CellEngineering.timeConsciousness).extractFirst();

		return ernst;
	}
}
