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
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.semanticextensions.outershells;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;


public class Terminology {

	public static final Set terminology = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.terminology);
	public static final Set idiom = terminology.addConcrete(SemanticEnterprise.what, SemanticExtensionsDomain.idiom);
	public static final Set abbreviation = terminology.addConcrete(SemanticEnterprise.what, SemanticExtensionsDomain.abbreviation);
	public static final Set semanticUnit = terminology.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.semanticUnit);
	public static final Set wordDefinition = terminology.addConcrete(SemanticEnterprise.what, SemanticExtensionsDomain.wordDefinition);

	public static Set instantiateFeature() {
		Instantiation.link(coreGraphs.visibility, terminology, Language.language);
		Instantiation.link(coreGraphs.visibility, Root.universalartifactengineering, terminology);

		final Set idiom_to_languages = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.idiom_to_languages,
				idiom,
				idiom,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				Language.language,
				Language.language,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set idiom_to_idiomParts = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.idiom_to_idiomParts,
				idiom,
				idiom,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.idiomPart,
				Language.abstractWord,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		final Set abbreviation_to_languages = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.abbreviation_to_languages,
				abbreviation,
				abbreviation,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				Language.language,
				Language.language,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set wordDefinition_to_language = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
				SemanticExtensionsDomain.wordDefinition_to_language,
				wordDefinition,
				wordDefinition,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				Language.language,
				Language.language,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set semanticUnit_to_abstractWords = Instantiation.link(coreGraphs.edge,
				SemanticExtensionsDomain.semanticUnit_to_abstractWords,
				semanticUnit,
				semanticUnit,
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
		final Set semanticUnit_to_abbreviations = Instantiation.link(coreGraphs.edge,
				SemanticExtensionsDomain.semanticUnit_to_abbreviations,
				semanticUnit,
				semanticUnit,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE,
				abbreviation,
				abbreviation,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set semanticUnit_to_wordDefinitions = Instantiation.link(coreGraphs.edge,
				SemanticExtensionsDomain.semanticUnit_to_wordDefinitions,
				semanticUnit,
				semanticUnit,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_TRUE,
				wordDefinition,
				wordDefinition,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set semanticUnit_to_oppositeSemanticUnit = Instantiation.link(coreGraphs.edge,
				SemanticExtensionsDomain.semanticUnit_to_oppositeSemanticUnit,
				semanticUnit,
				semanticUnit,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				SemanticExtensionsDomain.oppositeSemanticUnit,
				semanticUnit,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set semanticAtom_to_semanticUnit = Instantiation.link(coreGraphs.edge,
				SemanticExtensionsDomain.artifact_to_semanticUnit,

				Artifact.artifact,
				Artifact.artifact,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				semanticUnit,
				semanticUnit,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		    Instantiation.link(coreGraphs.superSetReference, terminology, coreGraphs.vertex);
			Instantiation.link(coreGraphs.superSetReference, idiom, Language.abstractWord);
			Instantiation.link(coreGraphs.superSetReference, abbreviation, Language.abstractWord);
			Instantiation.link(coreGraphs.superSetReference, semanticUnit, coreGraphs.vertex);
			Instantiation.link(coreGraphs.superSetReference, wordDefinition, Language.languageElement);

		return terminology;
	}

}
