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

package org.s23m.cell.platform.models;

import org.s23m.cell.Set;
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
	public static final Set javaLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.javaLanguage);
	public static final Set sqlLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.sqlLanguage);
	public static final Set englishLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.englishLanguage);
	public static final Set deutschLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.deutschLanguage);
	public static final Set koreanLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.koreanLanguage);

	public static final Set	v1 = Instantiation.arrow(S23MPlatform.coreGraphs.visibility, production.filter(CellEngineering.language).extractFirst(), cellMetaLanguage);

	public static final Set s23mNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
			CellPlatformDomain.s23mNativeLanguage,
			CellPlatformDomain.agent,
			s23mCellPlatform,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.nativeLanguage,
			cellMetaLanguage,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
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
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.availableLicense,
			s23m_mozilla1dot1,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
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
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.availableLicense,
			s23m_kernel_mozilla1dot1,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set perspective_cellplatform_kernel = Instantiation.arrow(Agency.perspective,
			CellPlatformDomain.perspective_cellplatform_kernel,
			S23MSemanticDomains.from,
			s23mCellPlatform,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			S23MSemanticDomains.to,
			s23mCellKernel,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set s23mKernelNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
			CellPlatformDomain.s23mKernelNativeLanguage,
			CellPlatformDomain.agent,
			s23mCellKernel,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.nativeLanguage,
			cellMetaLanguage,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	static Set instantiateFeature() {


		// additional semantics


		return s23mCellPlatform;
	}

}
