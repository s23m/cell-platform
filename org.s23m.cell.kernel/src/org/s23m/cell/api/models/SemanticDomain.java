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
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api.models;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.F_InstantiationImpl;
import org.s23m.cell.core.Graph;

public class SemanticDomain {

	// Instantiation level 1, Graph : SemanticDomain

	public static final Set semanticdomain = ((Graph)Root.models).addConcrete(coreGraphs.vertex, identityFactory.semanticDomain());
	public static final Set semanticIdentity = ((Graph)semanticdomain).addAbstract(coreGraphs.vertex, identityFactory.semanticIdentity());
	public static final Set semanticIdentitySet = ((Graph)semanticdomain).addConcrete(coreGraphs.vertex, identityFactory.semanticIdentitySet());
	public static final Set semanticRole = ((Graph)semanticdomain).addConcrete(coreGraphs.vertex, identityFactory.semanticRole());
	public static final Set disjunctSemanticIdentitySet = ((Graph)semanticdomain).addConcrete(coreGraphs.vertex, identityFactory.disjunctSemanticIdentitySet());
	public static final Set variantDisjunctSemanticIdentitySet = ((Graph)semanticdomain).addConcrete(coreGraphs.vertex, identityFactory.variantDisjunctSemanticIdentitySet());

	public static final Set semanticRole_to_equivalenceClass = F_Instantiation.link(coreGraphs.edge,
			F_Instantiation.reuseSemanticIdentity(semanticRole.identity()),
			semanticRole,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			F_Instantiation.reuseSemanticIdentity(identityFactory.equivalenceClass()),
			//abstractSemanticRole,
			semanticIdentity,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set elements_to_disjunctSemanticIdentitySet = F_Instantiation.link(coreGraphs.edge,
			F_Instantiation.reuseSemanticIdentity(identityFactory.element()),
			disjunctSemanticIdentitySet,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,

			F_Instantiation.reuseSemanticIdentity(identityFactory.disjunctSemanticIdentitySet()),
			disjunctSemanticIdentitySet,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE

	);
	public static final Set elements_to_semanticIdentitySet = F_Instantiation.link(coreGraphs.edge,

			F_Instantiation.reuseSemanticIdentity(identityFactory.element()),
			semanticIdentity,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,

			F_Instantiation.reuseSemanticIdentity(semanticIdentitySet.identity()),
			semanticIdentitySet,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE
	);

	public static void instantiateFeature() {

		// Instantiation level 1

		F_Instantiation.link(coreGraphs.superSetReference, semanticIdentitySet, semanticIdentity);
		F_Instantiation.link(coreGraphs.superSetReference, semanticRole, semanticIdentity);
		F_Instantiation.link(coreGraphs.superSetReference, disjunctSemanticIdentitySet, semanticIdentity);
		F_Instantiation.link(coreGraphs.superSetReference, variantDisjunctSemanticIdentitySet, disjunctSemanticIdentitySet);

		final Set disjunctSemanticIdentitySet_to_semanticIdentitySet = F_Instantiation.link(coreGraphs.edge,
				F_Instantiation.reuseSemanticIdentity(disjunctSemanticIdentitySet.identity()),
				disjunctSemanticIdentitySet,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				F_Instantiation.reuseSemanticIdentity(identityFactory.variabilityDimension()),
				semanticIdentitySet,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);
		final Set disjunctSemanticIdentitySet_to_variantDisjunctSemanticIdentitySet = F_Instantiation.link(coreGraphs.edge,
				F_Instantiation.reuseSemanticIdentity(disjunctSemanticIdentitySet.identity()),
				disjunctSemanticIdentitySet,
				coreSets.minCardinality_1,
				coreSets.maxCardinality_1,
				coreSets.isNavigable_FALSE,
				coreSets.isContainer_FALSE,
				F_Instantiation.reuseSemanticIdentity(identityFactory.variantIdentifier()),
				variantDisjunctSemanticIdentitySet,
				coreSets.minCardinality_0,
				coreSets.maxCardinality_n,
				coreSets.isNavigable_TRUE,
				coreSets.isContainer_FALSE
		);

		semanticIdentitySet.addToQueries(F_InstantiationImpl.createFunction(identityFactory.filterFlavor(), coreSets.flavor, coreSets.queryFunction));

		/**
		 * semanticIdentitySet and disjunctSemanticIdentitySet commands
		 */

		final Set semanticIdentitySet_addElement = F_InstantiationImpl.createFunction(identityFactory.addElement(), semanticIdentity, coreSets.commandFunction);
		semanticIdentitySet.addToCommands(semanticIdentitySet_addElement);

		final Set disjunctSemanticIdentitySet_addElement = F_InstantiationImpl.createFunction(identityFactory.addElement(), disjunctSemanticIdentitySet, coreSets.commandFunction);
		disjunctSemanticIdentitySet.addToCommands(disjunctSemanticIdentitySet_addElement);

		final Set semanticIdentitySet_removeElement = F_InstantiationImpl.createFunction(identityFactory.removeElement(), semanticIdentity, coreSets.commandFunction);
		semanticIdentitySet.addToCommands(semanticIdentitySet_removeElement);

		final Set disjunctSemanticIdentitySet_removeElement = F_InstantiationImpl.createFunction(identityFactory.removeElement(), disjunctSemanticIdentitySet, coreSets.commandFunction);
		disjunctSemanticIdentitySet.addToCommands(disjunctSemanticIdentitySet_removeElement);

		/**
		 * semanticIdentitySet and disjunctSemanticIdentitySet queries
		 */

		final Set semanticIdentitySet_elements = F_InstantiationImpl.createFunction(identityFactory.elementsOfSemanticIdentitySet(), coreSets.queryFunction);
		semanticIdentitySet.addToQueries(semanticIdentitySet_elements);

		final Set disjunctSemanticIdentitySet_elements = F_InstantiationImpl.createFunction(identityFactory.elementsOfSemanticIdentitySet(), coreSets.queryFunction);
		disjunctSemanticIdentitySet.addToQueries(disjunctSemanticIdentitySet_elements);

	}
}
