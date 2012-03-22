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

package org.s23m.cell.platform;

import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.api.KernelSets;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.platform.models.CellPlatform;
import org.s23m.cell.platform.models.CellPlatformDomain;


/**
 * {@link F_SemanticStateOfInMemoryModel} provides access to the Sets and Properties of the S23M kernel
 * that constitute the basic S23M vocabulary.
 *
 * Additionally F_SemanticStateOfInMemoryModel enables the creation of arrows between Sets,
 * and automatically attaches the arrow to the appropriate container Set.
 *
 * Note: F_SemanticStateOfInMemoryModel contains no implementation, it simply delegates to ArrowConstraints, IdentityFactory, CoreSets,
 * and KernelOrderedSets.
 *
 * Extensions: S23M is designed to be extensible. All extensions that only involve a structural extension
 * of the meta model can be achieved by modelling the extension in S23M. Beyond such basic extensions,
 * S23M can be extended/modified by plugging in a different IdentityFactory and/or by writing a custom Shell.
 * Such extensions are created by creating a subclass of F_SemanticStateOfInMemoryModel that
 *
 * 	(a) adds a method that references the appropriate SemanticIndentityFactory:
 *
 * 		public static final CustomSemanticIdentityFactory customSemanticIdentityFactory = new CustomSemanticIdentityFactory();
 *
 * 	and/or
 *
 * 	(b) reference the appropriate custom Shell by overriding the raiseError and arrow methods in F_SemanticStateOfInMemoryModel and by delegating to ArrowConstraints
 * 		to invoke the raiseError and arrow methods in the kernel.
 *
 * All extensions must use F_SemanticStateOfInMemoryModel's CoreSets and KernelOrderedSets.
 *
 */
public class S23MPlatform {

	/**
	 * QUERIES
	 */

	public static final KernelSets coreSets = org.s23m.cell.S23MKernel.coreSets;
	//public static final CoreGraphs coreGraphs = org.s23m.cell.G.coreGraphs;
	private static boolean cellPlatformIsInitialized = false;


	public static boolean semanticDomainIsInitialized() {
		return org.s23m.cell.SemanticStateOfInMemoryModel.semanticDomainIsInitialized();
	}
	public static boolean cellKernelSemanticDomainIsInitialized() {
		return org.s23m.cell.SemanticStateOfInMemoryModel.cellKernelSemanticDomainIsInitialized();
	}

	public static boolean cellEditorIsLive() {
		return org.s23m.cell.SemanticStateOfInMemoryModel.cellEditorIsLive();
	}
	public static boolean cellKernelIsInitialized() {
		return org.s23m.cell.SemanticStateOfInMemoryModel.cellKernelIsInitialized();
	}
	public static boolean cellPlatformIsInitialized() {
		return cellPlatformIsInitialized;
	}

	/**
	 * COMMANDS
	 */

	public static void goLiveWithCellEditor() {
		/**
		 * TODO as needed depending on context, boot the repository & editor
		 */
		org.s23m.cell.S23MKernel.goLiveWithCellEditor();
	}
	public static void completeCellKernelInitialization() {
		org.s23m.cell.S23MKernel.completeCellKernelInitialization();
	}
	public static void completeCellPlatformInitialization() {
		org.s23m.cell.S23MKernel.completeCellKernelInitialization();
		if (!cellEditorIsLive()) {
			CellPlatform.instantiateFeature();
		};
		cellPlatformIsInitialized = true;
	}
	public static void bootTemplate() {
		completeCellPlatformInitialization();
	}
	public static void boot() {
		Root.instantiateFeature();
		SemanticDomain.instantiateFeature();
		final int kernelComplexity = identityFactory.kernelComplexity();
		final int inMemoryComplexity = identityFactory.inMemoryComplexity();
		goLiveWithCellEditor();
		bootTemplate();
	}
	public static Set raiseError(final Set semanticIdentity, final Set metaElement) {
		return org.s23m.cell.api.Instantiation.raiseError(semanticIdentity, metaElement);
	}

	public static Set arrow(final Set metaElement,
			final Set edgeIdentity,
			final Set firstSemanticIdentity,
			final Set firstOrderedPair,
			final Set firstMinCardinality,
			final Set firstMaxCardinality,
			final Set firstIsNavigable,
			final Set firstIsContainer,
			final Set secondSemanticIdentity,
			final Set secondOrderedPair,
			final Set secondMinCardinality,
			final Set secondMaxCardinality,
			final Set secondIsNavigable,
			final Set secondIsContainer
	) {
		return org.s23m.cell.api.Instantiation.arrow(metaElement,
				edgeIdentity,
				firstSemanticIdentity,
				firstOrderedPair,
				firstMinCardinality,
				firstMaxCardinality,
				firstIsNavigable,
				firstIsContainer,
				secondSemanticIdentity,
				secondOrderedPair,
				secondMinCardinality,
				secondMaxCardinality,
				secondIsNavigable,
				secondIsContainer
		);
	}

	public static Set arrow(final Set metaElement, final Set fromInstance, final Set toInstance) {
		return org.s23m.cell.api.Instantiation.arrow(metaElement, fromInstance, toInstance);
	}

	public static Set instantiateConcrete(final Set metaElement, final Set semanticIdentity) {
		return org.s23m.cell.api.Instantiation.instantiateConcrete(metaElement, semanticIdentity);
	}
	public static Set instantiateAbstract(final Set metaElement, final Set semanticIdentity) {
		return org.s23m.cell.api.Instantiation.instantiateAbstract(metaElement, semanticIdentity);
	}

	public static Set addSemanticDomain(final String name, final String pluralName, final Set semanticDomain) {
		return org.s23m.cell.api.Instantiation.addSemanticDomain(name, pluralName, semanticDomain);
	}

	public static Set addDisjunctSemanticIdentitySet(final String name, final String pluralName, final Set semanticDomain) {
		return org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet(name, pluralName, semanticDomain);
	}
	public static Set addAnonymousDisjunctSemanticIdentitySet(final Set semanticDomain) {
		return org.s23m.cell.api.Instantiation.addAnonymousDisjunctSemanticIdentitySet(semanticDomain);
	}
	public static Set addSemanticRole(final String name, final String pluralName, final Set semanticDomain, final Set referencedSemanticIdentity) {
		return org.s23m.cell.api.Instantiation.addSemanticRole(name, pluralName, semanticDomain, referencedSemanticIdentity);
	}
	public static Set addAnonymousSemanticRole(final Set semanticDomain, final Set referencedSemanticIdentity) {
		return org.s23m.cell.api.Instantiation.addAnonymousSemanticRole(semanticDomain, referencedSemanticIdentity);
	}
	public static Set addSemanticIdentitySet(final String name, final String pluralName, final Set semanticDomain) {
		return org.s23m.cell.api.Instantiation.addSemanticIdentitySet(name, pluralName, semanticDomain);
	}

//	public static Set addElement(final Set set, final Set element) {
//		return org.s23m.cell.G.addElement(set, element);
//	}
	//public static Set addToTransportContainer(final Set set) {
	//	return org.s23m.cell.G.addToTransportContainer(set);
	//}
	//public static Set purgeTransportContainer() {
	//	return org.s23m.cell.G.purgeTransportContainer();
	//}
	//public static Set transportContainer() {
	//	return org.s23m.cell.G.transportContainer();
	//}

	public static Set graphVisualization(final Set set) {
		for (final Set s : RepositoryStructure.graphVisualizations.filterPolymorphic(Visualization.graphVisualization)) {
			final Set visualization_to_visualizedGraphs = s.filterPolymorphic(Visualization.visualizedGraph_to_graph);
			if (!visualization_to_visualizedGraphs.isEmpty()
				&& 	visualization_to_visualizedGraphs.to().isEqualTo(set)) {
				return visualization_to_visualizedGraphs.from();
			}
		}
		return coreSets.is_UNKNOWN;
	}

	public static Set defaultSymbol(final Set set) {
		final Set containerOfSemanticIdentity = set.semanticIdentity().container();
		final Set graphVisualization = graphVisualization(containerOfSemanticIdentity);
		if (!graphVisualization.isEqualTo(coreSets.is_UNKNOWN)) {
			final Set symbolArrows = graphVisualization.filterPolymorphic(Visualization.symbol_to_semantic_identity);
			for (final Set symbolArrow: symbolArrows) {
				if (symbolArrow.to().isEqualTo(set)
						&& symbolArrow.fromEdgeEnd().isEqualTo(CellPlatformDomain.theDefault)) {
					return symbolArrow.from();
				}
			}
		}
		if (set.container().isEqualTo(org.s23m.cell.api.Query.graph)) {
			// terminate recursion
			return set.properClass();
		} else {
			// recurse up the category tree
			return defaultSymbol(set.category());
		}
	}
}
