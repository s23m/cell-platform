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
	public static final Set s23mLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.s23mLanguage);
	public static final Set javaLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.javaLanguage);
	public static final Set sqlLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.sqlLanguage);
	public static final Set englishLanguage = production.filter(CellEngineering.language).extractFirst().addConcrete(CellEngineering.language, CellPlatformDomain.englishLanguage);

	public static final Set	v1 = Instantiation.arrow(S23MPlatform.coreGraphs.visibility, production.filter(CellEngineering.language).extractFirst(), s23mLanguage);

	public static final Set s23mNativeLanguage = Instantiation.arrow(Agency.agent_to_nativeLanguage,
			CellPlatformDomain.s23mNativeLanguage,
			CellPlatformDomain.agent,
			s23mCellPlatform,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.is_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.nativeLanguage,
			s23mLanguage,
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
