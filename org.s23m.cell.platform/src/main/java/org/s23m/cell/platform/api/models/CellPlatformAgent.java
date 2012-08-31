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

package org.s23m.cell.platform.api.models;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.Instantiation;

/**
 * {@link CellPlatformAgent} defines the software development, testing, and production stages of the S23M Cell Platform
 */
public final class CellPlatformAgent {

	public static final Set s23mCellPlatform = org.s23m.cell.platform.api.Instantiation.addAgent("cell platform", "cell platform");
	public static final Set development = org.s23m.cell.platform.api.Instantiation.addStage(s23mCellPlatform, "development", "development");
	public static final Set testing = org.s23m.cell.platform.api.Instantiation.addStage(s23mCellPlatform, "test", "test");
	public static final Set production = org.s23m.cell.platform.api.Instantiation.addStage(s23mCellPlatform, "production", "production");
	public static final Set cellMetaLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.cellMetaLanguage);
	public static final Set codingLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.codingLanguage);

	public static final Set xmlJargon = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.jargon, CellPlatformDomain.xmlJargon);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, xmlJargon, codingLanguage);
	public static final Set javaClassJargon = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.jargon, CellPlatformDomain.javaClassJargon);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, javaClassJargon, codingLanguage);
	public static final Set javaMemberJargon = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.jargon, CellPlatformDomain.javaMemberJargon);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, javaMemberJargon, codingLanguage);
	public static final Set javaPackageJargon = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.jargon, CellPlatformDomain.javaPackageJargon);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, javaPackageJargon, codingLanguage);
	public static final Set sqlJargon = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.jargon, CellPlatformDomain.sqlJargon);
	private static final Set s5 = Instantiation.arrow(coreGraphs.superSetReference, sqlJargon, codingLanguage);

	public static final Set englishLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.englishLanguage);
	public static final Set deutschLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.deutschLanguage);
	public static final Set koreanLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.koreanLanguage);

	public static final Set technology = org.s23m.cell.platform.api.Instantiation.addAgent("Technology", "Technology");
	public static final Set xmlProcessor = org.s23m.cell.platform.api.Instantiation.addAgent("XML Processor", "XML Processor");
	public static final Set javaVirtualMachine = org.s23m.cell.platform.api.Instantiation.addAgent("Java VM", "Java VM");
	public static final Set sqlDatabase = org.s23m.cell.platform.api.Instantiation.addAgent("SQL DB", "SQL DB");

	public static final Set	v1 = Instantiation.arrow(S23MPlatform.coreGraphs.visibility, production.filter(CellEngineering.language).extractFirst(), cellMetaLanguage);

	public static final Set s23mNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
			CellPlatformDomain.s23mNativeLanguage,
			CellPlatformDomain.agent,
			s23mCellPlatform,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.nativeLanguage,
			cellMetaLanguage,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set s23m_mozilla1dot1 = production.filter(CellEngineering.legal).extractFirst().addConcrete(Legal.license, CellPlatformDomain.mozilla1dot1);
	public static final Set originalCode = s23m_mozilla1dot1.addConcrete(Legal.originalCode, CellPlatformDomain.s23mCellPlatformCode);
	public static final Set initialDeveloper1 = s23m_mozilla1dot1.addConcrete(Legal.initialDevelopers, CellPlatformDomain.thes23mfoundation);
	public static final Set initialDeveloper2 = s23m_mozilla1dot1.addConcrete(Legal.initialDevelopers, CellPlatformDomain.softmetaware);
	public static final Set copyrightHolder1 = s23m_mozilla1dot1.addConcrete(Legal.copyrightHolders, CellPlatformDomain.thes23mfoundation);
	public static final Set copyrightHolder2 = s23m_mozilla1dot1.addConcrete(Legal.copyrightHolders, CellPlatformDomain.softmetaware);
	public static final Set contributor1 = s23m_mozilla1dot1.addConcrete(Legal.contributors, CellPlatformDomain.jornbettin);
	public static final Set contributor2 = s23m_mozilla1dot1.addConcrete(Legal.contributors, CellPlatformDomain.andrewshewring);
	public static final Set contributor3 = s23m_mozilla1dot1.addConcrete(Legal.contributors, CellPlatformDomain.chulkim);
	public static final Set contributor4 = s23m_mozilla1dot1.addConcrete(Legal.contributors, CellPlatformDomain.xaverwiesmann);

	public static final Set s23mCellPlatformOrg = production.filter(CellEngineering.organization).extractFirst().addConcrete(Organization.cell, s23mCellPlatform);
	public static final Set s23m_platform_license = Instantiation.arrow(Organization.semanticUnit_to_availableLicenses,
			CellPlatformDomain.s23m_platform_license,
			CellPlatformDomain.semanticUnit,
			s23mCellPlatformOrg,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.availableLicense,
			s23m_mozilla1dot1,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set s23mCellKernel = org.s23m.cell.platform.api.Instantiation.addAgent("cell kernel", "cell kernel");
	public static final Set developmentK = org.s23m.cell.platform.api.Instantiation.addStage(s23mCellKernel, "development", "development");
	public static final Set testingK = org.s23m.cell.platform.api.Instantiation.addStage(s23mCellKernel, "test", "test");
	public static final Set productionK = org.s23m.cell.platform.api.Instantiation.addStage(s23mCellKernel, "production", "production");

	public static final Set s23m_kernel_mozilla1dot1 = productionK.filter(CellEngineering.legal).extractFirst().addConcrete(Legal.license, CellPlatformDomain.mozilla1dot1);
	public static final Set originalCode2 = s23m_kernel_mozilla1dot1.addConcrete(Legal.originalCode, S23MSemanticDomains.cellKernel);
	public static final Set initialDeveloper3 = s23m_kernel_mozilla1dot1.addConcrete(Legal.initialDevelopers, CellPlatformDomain.thes23mfoundation);
	public static final Set initialDeveloper4 = s23m_kernel_mozilla1dot1.addConcrete(Legal.initialDevelopers, CellPlatformDomain.softmetaware);
	public static final Set copyrightHolder3 = s23m_kernel_mozilla1dot1.addConcrete(Legal.copyrightHolders, CellPlatformDomain.thes23mfoundation);
	public static final Set copyrightHolder4 = s23m_kernel_mozilla1dot1.addConcrete(Legal.copyrightHolders, CellPlatformDomain.softmetaware);
	public static final Set contributor5 = s23m_kernel_mozilla1dot1.addConcrete(Legal.contributors, CellPlatformDomain.jornbettin);
	public static final Set contributor6 = s23m_kernel_mozilla1dot1.addConcrete(Legal.contributors, CellPlatformDomain.andrewshewring);
	public static final Set contributor7 = s23m_kernel_mozilla1dot1.addConcrete(Legal.contributors, CellPlatformDomain.chulkim);
	public static final Set contributor8 = s23m_kernel_mozilla1dot1.addConcrete(Legal.contributors, CellPlatformDomain.xaverwiesmann);

	public static final Set s23mCellKernelOrg = productionK.filter(CellEngineering.organization).extractFirst().addConcrete(Organization.cell, S23MSemanticDomains.cellKernel);
	public static final Set s23mkernel_license = Instantiation.arrow(Organization.semanticUnit_to_availableLicenses,
			CellPlatformDomain.s23m_kernel_license,
			CellPlatformDomain.semanticUnit,
			s23mCellKernelOrg,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.availableLicense,
			s23m_kernel_mozilla1dot1,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set perspective_cellplatform_kernel = Instantiation.arrow(Agency.perspective,
			CellPlatformDomain.perspective_cellplatform_kernel,
			S23MSemanticDomains.from,
			s23mCellPlatform,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			S23MSemanticDomains.to,
			s23mCellKernel,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set perspective_technology = Instantiation.arrow(Agency.perspective,
			CellPlatformDomain.perspective_technology,
			S23MSemanticDomains.from,
			s23mCellPlatform,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			S23MSemanticDomains.to,
			technology,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	public static final Set perspective_technologyV = perspective_technology.addConcrete(coreGraphs.vertex, perspective_technology);
	public static final Set technology_to_jargon = Instantiation.arrow(Agency.perspective_to_jargons,
			CellPlatformDomain.technology_to_jargon,
			CellPlatformDomain.perspective,
			perspective_technologyV,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.jargon,
			codingLanguage,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set perspective_xmlProcessor = Instantiation.arrow(Agency.perspective,
			CellPlatformDomain.perspective_xmlProcessor,
			S23MSemanticDomains.from,
			s23mCellPlatform,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			S23MSemanticDomains.to,
			xmlProcessor,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	public static final Set perspective_xmlProcessorV = perspective_xmlProcessor.addConcrete(coreGraphs.vertex, perspective_xmlProcessor);
	public static final Set xmlProcessor_to_jargon = Instantiation.arrow(Agency.perspective_to_jargons,
			CellPlatformDomain.xmlProcessor_to_jargon,
			CellPlatformDomain.perspective,
			perspective_xmlProcessorV,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.jargon,
			xmlJargon,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set perspective_javaVirtualMachine = Instantiation.arrow(Agency.perspective,
			CellPlatformDomain.perspective_javaVirtualMachine,
			S23MSemanticDomains.from,
			s23mCellPlatform,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			S23MSemanticDomains.to,
			javaVirtualMachine,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	public static final Set perspective_javaVirtualMachineV = perspective_javaVirtualMachine.addConcrete(coreGraphs.vertex, perspective_javaVirtualMachine);
	public static final Set javaVirtualMachine_to_javaClassJargon = Instantiation.arrow(Agency.perspective_to_jargons,
			CellPlatformDomain.javaVirtualMachine_to_classJargon,
			CellPlatformDomain.perspective,
			perspective_javaVirtualMachineV,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.jargon,
			javaClassJargon,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	public static final Set javaVirtualMachine_to_memberJargon = Instantiation.arrow(Agency.perspective_to_jargons,
			CellPlatformDomain.javaVirtualMachine_to_memberJargon,
			CellPlatformDomain.perspective,
			perspective_javaVirtualMachineV,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.jargon,
			javaMemberJargon,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	public static final Set javaVirtualMachine_to_packageJargon = Instantiation.arrow(Agency.perspective_to_jargons,
			CellPlatformDomain.javaVirtualMachine_to_packageJargon,
			CellPlatformDomain.perspective,
			perspective_javaVirtualMachineV,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.jargon,
			javaPackageJargon,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set perspective_sqlDatabase = Instantiation.arrow(Agency.perspective,
			CellPlatformDomain.perspective_sqlDatabase,
			S23MSemanticDomains.from,
			s23mCellPlatform,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			S23MSemanticDomains.to,
			sqlDatabase,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	public static final Set perspective_sqlDatabaseV = perspective_sqlDatabase.addConcrete(coreGraphs.vertex, perspective_sqlDatabase);
	public static final Set sqlDatabase_to_jargon = Instantiation.arrow(Agency.perspective_to_jargons,
			CellPlatformDomain.sqlDatabase_to_jargon,
			CellPlatformDomain.perspective,
			perspective_sqlDatabaseV,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.jargon,
			sqlJargon,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set s23mKernelNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
			CellPlatformDomain.s23mKernelNativeLanguage,
			CellPlatformDomain.agent,
			s23mCellKernel,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.nativeLanguage,
			cellMetaLanguage,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	private static final Set w1 = cellMetaLanguage.addConcrete(Language.word, S23MSemanticDomains.orderedPair);
	private static final Set w2 = cellMetaLanguage.addConcrete(Language.word, S23MSemanticDomains.orderedSet);
	private static final Set w3 = cellMetaLanguage.addConcrete(Language.word, S23MPlatform.coreGraphs.graph);
	private static final Set w4 = cellMetaLanguage.addConcrete(Language.word, S23MPlatform.coreGraphs.vertex);
	private static final Set w5 = cellMetaLanguage.addConcrete(Language.word, S23MPlatform.coreGraphs.edge);
	private static final Set w6 = cellMetaLanguage.addConcrete(Language.word, S23MPlatform.coreGraphs.visibility);
	private static final Set w7 = cellMetaLanguage.addConcrete(Language.word, S23MPlatform.coreGraphs.superSetReference);

	private static final Set w11 = javaClassJargon.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("orderedPair", "orderedPairs", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w12 = javaClassJargon.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("orderedSet", "orderedSets", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w13 = javaClassJargon.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("graph", "graphs", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w14 = javaClassJargon.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("vertex", "vertices", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w15 = javaClassJargon.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("edge", "edges", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w16 = javaClassJargon.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("visibility", "visibilities", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w17 = javaClassJargon.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("superSetReference", "superSetReferences", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w11e = w11.addConcrete(Language.word, w1);
	private static final Set w12e = w12.addConcrete(Language.word, w2);
	private static final Set w13e = w13.addConcrete(Language.word, w3);
	private static final Set w14e = w14.addConcrete(Language.word, w4);
	private static final Set w15e = w15.addConcrete(Language.word, w5);
	private static final Set w16e = w16.addConcrete(Language.word, w6);
	private static final Set w17e = w17.addConcrete(Language.word, w7);

	private static final Set w21 = englishLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("ordered pair", "ordered pairs", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w22 = englishLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("ordered set", "ordered sets", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w23 = englishLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("graph", "graphs", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w24 = englishLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("vertex", "vertices", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w25 = englishLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("edge", "edges", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w26 = englishLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("visibility", "visibilities", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w27 = englishLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("super set reference", "super set references", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w21e = w21.addConcrete(Language.word, w1);
	private static final Set w22e = w22.addConcrete(Language.word, w2);
	private static final Set w23e = w23.addConcrete(Language.word, w3);
	private static final Set w24e = w24.addConcrete(Language.word, w4);
	private static final Set w25e = w25.addConcrete(Language.word, w5);
	private static final Set w26e = w26.addConcrete(Language.word, w6);
	private static final Set w27e = w27.addConcrete(Language.word, w7);

	// potentially useful visibilities
	private static final Set v80 = Instantiation.arrow(coreGraphs.visibility, s23mCellPlatform, Instantiation.toSemanticDomain(s23mCellPlatform));
	private static final Set v82 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, Instantiation.toSemanticDomain(s23mCellPlatform));

	private static final Set w31 = deutschLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("geordnetes Paar", "geordnete Paare", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w32 = deutschLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("geordnete Menge", "geordnete Mengen", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w33 = deutschLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("Graph", "Graphen", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w34 = deutschLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("Knoten", "Knoten", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w35 = deutschLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("Kante", "Kante", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w36 = deutschLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("Sichtbarkeit", "Sichtbarkeiten", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w37 = deutschLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("Obermengenreferenz", "Obermengenreferenzen", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w31e = w31.addConcrete(Language.word, w1);
	private static final Set w32e = w32.addConcrete(Language.word, w2);
	private static final Set w33e = w33.addConcrete(Language.word, w3);
	private static final Set w34e = w34.addConcrete(Language.word, w4);
	private static final Set w35e = w35.addConcrete(Language.word, w5);
	private static final Set w36e = w36.addConcrete(Language.word, w6);
	private static final Set w37e = w37.addConcrete(Language.word, w7);

	private static final Set w41 = koreanLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("*TO BE TRANSLATED*", "*TO BE TRANSLATED*", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w42 = koreanLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("*TO BE TRANSLATED*", "*TO BE TRANSLATED*", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w43 = koreanLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("*TO BE TRANSLATED*", "*TO BE TRANSLATED*", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w44 = koreanLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("*TO BE TRANSLATED*", "*TO BE TRANSLATED*", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w45 = koreanLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("*TO BE TRANSLATED*", "*TO BE TRANSLATED*", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w46 = koreanLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("*TO BE TRANSLATED*", "*TO BE TRANSLATED*", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w47 = koreanLanguage.addConcrete(Language.word, Instantiation.addDisjunctSemanticIdentitySet("*TO BE TRANSLATED*", "*TO BE TRANSLATED*", Instantiation.toSemanticDomain(s23mCellPlatform)));
	private static final Set w41e = w41.addConcrete(Language.word, w1);
	private static final Set w42e = w42.addConcrete(Language.word, w2);
	private static final Set w43e = w43.addConcrete(Language.word, w3);
	private static final Set w44e = w44.addConcrete(Language.word, w4);
	private static final Set w45e = w45.addConcrete(Language.word, w5);
	private static final Set w46e = w46.addConcrete(Language.word, w6);
	private static final Set w47e = w47.addConcrete(Language.word, w7);

	static Set instantiateFeature() {

		// additional semantics
		 xmlJargon.addConcrete(Jargon.characterTransformation, CellPlatformDomain.allCharactersToLower) ;
		 xmlJargon.addConcrete(Jargon.wordTransformation, CellPlatformDomain.firstCharacterOfAllWordsToUpper) ;
		 xmlJargon.addConcrete(Jargon.statementTransformation, CellPlatformDomain.firstCharacterToLower) ;
		 xmlJargon.addConcrete(Jargon.whiteTransformation, CellPlatformDomain.whiteToNoCharacter) ;

		 javaPackageJargon.addConcrete(Jargon.characterTransformation, CellPlatformDomain.allCharactersToLower) ;
		 javaPackageJargon.addConcrete(Jargon.whiteTransformation, CellPlatformDomain.whiteToNoCharacter) ;

		 javaClassJargon.addConcrete(Jargon.characterTransformation, CellPlatformDomain.allCharactersToLower) ;
		 javaClassJargon.addConcrete(Jargon.wordTransformation, CellPlatformDomain.firstCharacterOfAllWordsToUpper) ;
		 javaClassJargon.addConcrete(Jargon.whiteTransformation, CellPlatformDomain.whiteToNoCharacter) ;

		 javaMemberJargon.addConcrete(Jargon.characterTransformation, CellPlatformDomain.allCharactersToLower) ;
		 javaMemberJargon.addConcrete(Jargon.wordTransformation, CellPlatformDomain.firstCharacterOfAllWordsToUpper) ;
		 javaMemberJargon.addConcrete(Jargon.statementTransformation, CellPlatformDomain.firstCharacterToLower) ;
		 javaMemberJargon.addConcrete(Jargon.whiteTransformation, CellPlatformDomain.whiteToNoCharacter) ;

		 sqlJargon.addConcrete(Jargon.characterTransformation, CellPlatformDomain.allCharactersToUpper) ;
		 sqlJargon.addConcrete(Jargon.whiteTransformation, CellPlatformDomain.whiteToUnderscore) ;

		return s23mCellPlatform;
	}

}
