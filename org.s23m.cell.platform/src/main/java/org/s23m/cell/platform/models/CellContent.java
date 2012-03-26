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
import org.s23m.cell.api.models.Root;

/**
 * {@link CellContent} implements all instantiation semantics related to the modelling of container state machines
 * that must be enforced for all Instances/cells (instantiation level n, with n > 0)
 *
 * The semantics enforced in CellEngineering provide the basis for modelling the dynamic evolution of the S23M instantiation semantics
 */
public final class CellContent {

	/*

	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.cellContent);
	private static final Set v7 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.cellContent, CellEngineering.language);
	private static final Set v8 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.cellContent, CellEngineering.terminology);
	private static final Set v9 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.cellContent, CellEngineering.licensing);
	private static final Set v10 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.cellContent, CellEngineering.timeConsciousness);

	public static final Set cell = CellEngineering.cellContent.addConcrete(TimeConsciousness.timeConsciousVertex, CellPlatformDomain.cell);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, cell, TimeConsciousness.timeConsciousVertex);

	// additional semantics
	public static final Set semanticUnit_to_abstractWords = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.semanticUnit_to_abstractWords,
			CellPlatformDomain.semanticUnit,
			cell,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			Language.word,
			Language.abstractWord,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, semanticUnit_to_abstractWords, TimeConsciousness.timeConsciousEdge);

	public static final Set semanticUnit_to_abbreviations = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.semanticUnit_to_abbreviations,
			CellPlatformDomain.semanticUnit,
			cell,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			Terminology.abbreviation,
			Terminology.abbreviation,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, semanticUnit_to_abbreviations, TimeConsciousness.timeConsciousEdge);

	public static final Set semanticUnit_to_wordDefinitions = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.semanticUnit_to_wordDefinitions,
			CellPlatformDomain.semanticUnit,
			cell,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			Terminology.wordDefinition,
			Terminology.wordDefinition,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set s5 = Instantiation.arrow(coreGraphs.superSetReference, semanticUnit_to_wordDefinitions, TimeConsciousness.timeConsciousEdge);

	public static final Set semanticUnit_to_oppositeSemanticUnit = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.semanticUnit_to_oppositeSemanticUnit,
			CellPlatformDomain.semanticUnit,
			CellContent.cell,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.oppositeSemanticUnit,
			cell,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set s6 = Instantiation.arrow(coreGraphs.superSetReference, semanticUnit_to_oppositeSemanticUnit, TimeConsciousness.timeConsciousEdge);

	public static final Set semanticUnit_to_copyrightHolder = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.semanticUnit_to_copyrightHolder,
			CellPlatformDomain.semanticUnit,
			cell,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.copyrightHolder,
			Legal.legalEntity,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set s7 = Instantiation.arrow(coreGraphs.superSetReference, semanticUnit_to_copyrightHolder, TimeConsciousness.timeConsciousEdge);

	public static final Set semanticUnit_to_availableLicense = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.semanticUnit_to_availableLicense,
			CellPlatformDomain.semanticUnit,
			cell,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.availableLicense,
			Legal.license,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set s8 = Instantiation.arrow(coreGraphs.superSetReference, semanticUnit_to_copyrightHolder, TimeConsciousness.timeConsciousEdge);

	public static final Set cell_to_nativeLanguage = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.cell_to_nativeLanguage,
			CellPlatformDomain.semanticUnit,
			cell,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.nativeLanguage,
			CellEngineering.language,
			S23MSemanticDomains.minCardinality_1,
			S23MSemanticDomains.maxCardinality_1,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	private static final Set s9 = Instantiation.arrow(coreGraphs.superSetReference, cell_to_nativeLanguage, TimeConsciousness.timeConsciousEdge);

*/
	static Set instantiateFeature() {


		// additional semantics


		return Root.cellengineering;
	}

}
