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

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.platform.api.CellQueries;
import org.s23m.cell.platform.api.Instantiation;
import org.s23m.cell.platform.api.models.Agency;
import org.s23m.cell.platform.api.models.CellEngineering;
import org.s23m.cell.platform.api.models.CellPlatformAgent;
import org.s23m.cell.platform.api.models.CellPlatformDomain;
import org.s23m.cell.platform.api.models.Language;
import org.s23m.cell.platform.api.models.LogicalFormula;
import org.s23m.cell.platform.api.models.Organization;
import org.s23m.cell.platform.api.models.SessionHandling;
import org.s23m.cell.platform.api.models.ValidityInterval;
import org.s23m.cell.platform.impl.F_CellQueries;

public class AgencyTestFoundation {

	public static Set ithanku;
	public static Set object_cell;
	public static Set test1;
	public static Set ernst;


	public static Set instantiateFeature() {
		ithanku = org.s23m.cell.platform.api.Instantiation.addAgent("i thank u", "set of i thank u");
		final Set dev = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "development", "set of development");
		final Set test = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "test", "set of test");
		final Set prod = org.s23m.cell.platform.api.Instantiation.addStage(ithanku, "production", "set of production");
		final Set ithanku1 = org.s23m.cell.platform.api.Instantiation.addAgent("i thank u #1", "set of i thank u #1");

		ernst = org.s23m.cell.platform.api.Instantiation.addAgent("Ernst Zermelo", "Ernst Zermelo");
		final Set dev1 = org.s23m.cell.platform.api.Instantiation.addStage(ernst, "dev1", "dev1s");
		test1 = org.s23m.cell.platform.api.Instantiation.addStage(ernst, "test1", "test1s");
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


		final Set english = dev.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformAgent.englishLanguage);
		final Set deutsch = dev.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformAgent.deutschLanguage);
		final Set cellMeta = dev.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.jargon, CellPlatformAgent.cellMetaLanguage);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.language).extractFirst(), english);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.language).extractFirst(), deutsch);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.language).extractFirst(), cellMeta);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.language).extractFirst().container(), english);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.language).extractFirst().container(), deutsch);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.language).extractFirst().container(), cellMeta);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.organization).extractFirst(), english);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.organization).extractFirst(), deutsch);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.organization).extractFirst(), cellMeta);


		final Set objectCellSI = Instantiation.addDisjunctSemanticIdentitySet("object", "objects", Instantiation.toSemanticDomain(dev));
		final Set object = Instantiation.addDisjunctSemanticIdentitySet("object", "objects", Instantiation.toSemanticDomain(dev));
		final Set gegenstand = Instantiation.addDisjunctSemanticIdentitySet("Gegenstand", "Gegenstaende", Instantiation.toSemanticDomain(dev));
		final Set object1 = cellMeta.addConcrete(Language.word, object);
		final Set object2 = english.addConcrete(Language.word, object);
		final Set object3 = deutsch.addConcrete(Language.word, gegenstand);
		object_cell = dev.filter(CellEngineering.organization).extractFirst().addConcrete(Organization.cell, objectCellSI);

		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.organization).extractFirst(), object_cell);

		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				object_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.cellMetaLanguage,
				object1,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);
		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				object_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.englishLanguage,
				object2,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);
		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				object_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.deutschLanguage,
				object3,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);

		final Set giverCellSI = Instantiation.addDisjunctSemanticIdentitySet("giver", "givers", Instantiation.toSemanticDomain(dev));
		final Set giver = Instantiation.addDisjunctSemanticIdentitySet("giver", "givers", Instantiation.toSemanticDomain(dev));
		final Set schenker = Instantiation.addDisjunctSemanticIdentitySet("Schenker", "Schenker", Instantiation.toSemanticDomain(dev));
		final Set giver1 = cellMeta.addConcrete(Language.word, giver);
		final Set giver2 = english.addConcrete(Language.word, giver);
		final Set giver3 = deutsch.addConcrete(Language.word, schenker);
		final Set giver_cell = dev.filter(CellEngineering.organization).extractFirst().addConcrete(Organization.cell, giverCellSI);

		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.organization).extractFirst(), giver_cell);

		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				giver_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.cellMetaLanguage,
				giver1,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);
		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				giver_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.englishLanguage,
				giver2,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);
		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				giver_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.deutschLanguage,
				giver3,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);

		final Set recipientCellSI = Instantiation.addDisjunctSemanticIdentitySet("recipient", "recipients", Instantiation.toSemanticDomain(dev));
		final Set recipient = Instantiation.addDisjunctSemanticIdentitySet("recipient", "recipients", Instantiation.toSemanticDomain(dev));
		final Set empfaenger = Instantiation.addDisjunctSemanticIdentitySet("Empfaenger", "Empfaenger", Instantiation.toSemanticDomain(dev));
		final Set recipient1 = cellMeta.addConcrete(Language.word, recipient);
		final Set recipient2 = english.addConcrete(Language.word, recipient);
		final Set recipient3 = deutsch.addConcrete(Language.word, empfaenger);
		final Set recipient_cell = dev.filter(CellEngineering.organization).extractFirst().addConcrete(Organization.cell, recipientCellSI);

		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, dev.filter(CellEngineering.organization).extractFirst(), recipient_cell);

		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				recipient_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.cellMetaLanguage,
				recipient1,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);
		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				recipient_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.englishLanguage,
				recipient2,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);
		Instantiation.arrow(Organization.semanticUnit_to_abstractWords,
				S23MSemanticDomains.anonymous,
				CellPlatformDomain.semanticUnit,
				recipient_cell,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				CellPlatformAgent.deutschLanguage,
				recipient3,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);

		final Set sessionSI = Instantiation.addDisjunctSemanticIdentitySet("example session", "example session", Instantiation.toSemanticDomain(dev));

		final Set session = dev.filter(CellEngineering.sessionHandling).extractFirst().addConcrete(SessionHandling.session, sessionSI);



		final Set ernstNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("ErnstNativeLanguage", "ErnstNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set ernstNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				ernstNativeLanguageSI,
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
		final Set perspective_ernst_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_Ernst_cellplatform", "perspective_Ernst_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_ernst_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_ernst_cellplatformSI,
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
		final Set samuelNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("SamuelNativeLanguage", "SamuelNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set samuelNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				samuelNativeLanguageSI,
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
		final Set perspective_samuel_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_Samuel_cellplatform", "perspective_Samuel_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_samuel_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_samuel_cellplatformSI,
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
		final Set christopherNativeLanguageSI = Instantiation.addDisjunctSemanticIdentitySet("ChristopherNativeLanguage", "ChristopherNativeLanguage", Instantiation.toSemanticDomain(dev1));
		final Set christopherNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
				christopherNativeLanguageSI,
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
		final Set perspective_christopher_cellplatformSI = Instantiation.addDisjunctSemanticIdentitySet("perspective_Christopher_cellplatform", "perspective_Christopher_cellplatform", Instantiation.toSemanticDomain(dev));
		final Set perspective_christopher_cellplatform = Instantiation.arrow(Agency.perspective,
				perspective_christopher_cellplatformSI,
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

		final String name_d = CellQueries.nameAsString(CellQueries.name(object_cell, deutsch));
		final String name_e = CellQueries.nameAsString(CellQueries.name(object_cell, english));
		final String name_c = CellQueries.nameAsString(CellQueries.name(object_cell, cellMeta));
		final String pasted = name_d + " " + name_e + " " + name_c;

		return ernst;
	}
}
