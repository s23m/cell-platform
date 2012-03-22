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

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;


public class Terminology {

	private static final Set v3 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.terminology);
	private static final Set v4 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.terminology, CellEngineering.language);
	private static final Set v5 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.terminology, CellEngineering.timeConsciousness);

	public static final Set idiom = CellEngineering.terminology.addConcrete(TimeConsciousness.timeConsciousVertex, CellPlatformDomain.idiom);
	public static final Set abbreviation = CellEngineering.terminology.addConcrete(TimeConsciousness.timeConsciousVertex, CellPlatformDomain.abbreviation);
	public static final Set wordDefinition = CellEngineering.terminology.addConcrete(TimeConsciousness.timeConsciousVertex, CellPlatformDomain.wordDefinition);

	public static Set instantiateFeature() {

		final Set idiom_to_languages = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.idiom_to_languages,
				idiom,
				idiom,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellEngineering.language,
				CellEngineering.language,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set idiom_to_idiomParts = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.idiom_to_idiomParts,
				idiom,
				idiom,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellPlatformDomain.idiomPart,
				Language.abstractWord,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		final Set abbreviation_to_languages = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.abbreviation_to_languages,
				abbreviation,
				abbreviation,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellEngineering.language,
				CellEngineering.language,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set wordDefinition_to_language = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
				CellPlatformDomain.wordDefinition_to_language,
				wordDefinition,
				wordDefinition,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				CellEngineering.language,
				CellEngineering.language,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

			Instantiation.arrow(coreGraphs.superSetReference, idiom, Language.abstractWord);
			Instantiation.arrow(coreGraphs.superSetReference, abbreviation, Language.abstractWord);
			Instantiation.arrow(coreGraphs.superSetReference, wordDefinition, Language.languageElement);

		return CellEngineering.terminology;
	}

}
