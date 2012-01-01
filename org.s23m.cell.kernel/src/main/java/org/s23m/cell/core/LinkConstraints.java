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
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;

import org.s23m.cell.Identity;
import org.s23m.cell.SemanticStateOfInMemoryModel;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.SemanticDomain;

/**
 * {@link LinkConstraints} implements all instantiation semantics that are not enforced within the kernel (instantiation level 0)
 * but that must be enforced for all other Instances/artifacts (instantiation level n, with n > 0)
 * 
 * The semantics enforced in LinkConstraints amount to assertions/constraints that apply to all use cases that involve Gmodel.
 * 
 * An extension of the the Gmodel meta model may necessitate the creation of a further Shell in case
 * specific semantics (that complement the LinkConstraints semantics) apply to the additional meta model elements.
 * Such additional Shells must be built on top of LinkConstraints, and must never access the Gmodel kernel directly.
 */
public final class LinkConstraints {

	private static final Set vertex = coreGraphs.vertex;
	private static final Set edgeEnd = coreGraphs.edgeEnd;
	private static final Set link = coreGraphs.link;
	private static final Set edge = coreGraphs.edge;
	private static final Set superSetReference = coreGraphs.superSetReference;
	private static final Set visibility = coreGraphs.visibility;
	private static final Set graph = coreGraphs.graph;

	private   Identity firstEdgeEndIdentity;
	private   Set firstSet;
	private   Set 	firstMinCardinality;
	private   Set 	firstMaxCardinality;
	private   Set 	firstIsNavigable;
	private   Set 	firstIsContainer;
	private   Identity 	secondEdgeEndIdentity;
	private   Set 	secondSet;
	private   Set 	secondMinCardinality;
	private   Set 	secondMaxCardinality;
	private   Set 	secondIsNavigable;
	private   Set 	secondIsContainer;
	private   Set 	category;
	private   Identity 	edgeFlavoredIdentity;


	public static Set raiseError(final Identity semanticIdentity, final Set category) {
		return F_InstantiationImpl.raiseError(semanticIdentity, category);
	}

	public static Set link(final Set category,
			final Identity edgeFlavoredIdentity,
			final Identity firstEdgeEndIdentity,
			final Set firstSet,
			final Set firstMinCardinality,
			final Set firstMaxCardinality,
			final Set firstIsNavigable,
			final Set firstIsContainer,
			final Identity secondEdgeEndIdentity,
			final Set secondSet,
			final Set secondMinCardinality,
			final Set secondMaxCardinality,
			final Set secondIsNavigable,
			final Set secondIsContainer
	) {
		final LinkConstraints input = new LinkConstraints();
		input.firstEdgeEndIdentity = firstEdgeEndIdentity;
		input.firstSet = firstSet;
		input.firstMinCardinality =	firstMinCardinality;
		input.firstMaxCardinality =	firstMaxCardinality;
		input.firstIsNavigable	= firstIsNavigable;
		input.firstIsContainer	= firstIsContainer;
		input.secondEdgeEndIdentity	= secondEdgeEndIdentity;
		input.secondSet	= secondSet;
		input.secondMinCardinality	= secondMinCardinality;
		input.secondMaxCardinality =	secondMaxCardinality;
		input.secondIsNavigable	= secondIsNavigable;
		input.secondIsContainer = secondIsContainer;
		input.category =	category;
		input.edgeFlavoredIdentity =	edgeFlavoredIdentity;

		/* code to handle reconstitution of links involving semantic roles: START*/
		if (SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
			// TODO Add relevant kinds of edges that appear in SemanticDomain!
			if (category.isEqualTo(SemanticDomain.semanticRole_to_equivalenceClass)) {
				return input.linkSemanticIdentities();
			}
		}
		/* code to handle reconstitution of links involving semantic roles: END*/

		if (category.isEqualTo(edge)) {
			if ((firstSet.category().isEqualTo(graph))
					|| 	(firstSet.category().isEqualTo(link))
					|| 	(firstSet.category().isEqualTo(edge))
					|| 	(firstSet.category().isEqualTo(edgeEnd))
					|| 	(firstSet.category().isEqualTo(superSetReference))
					|| 	(firstSet.category().isEqualTo(visibility))
					|| 	(firstSet.category().isEqualTo(vertex))
					|| (((coreGraphs.vertex.isEqualTo(firstSet.category())) || (coreGraphs.vertex.isSuperSetOf(firstSet.category())).isEqualTo(coreSets.is_TRUE))
							&&
							((coreGraphs.vertex.isEqualTo(secondSet.category())) || (coreGraphs.vertex.isSuperSetOf(secondSet.category())).isEqualTo(coreSets.is_TRUE)))
			) {return input.addEdge();} else {return F_InstantiationImpl.raiseError(coreSets.semanticErr_LinkIsNotApplicable.identity(), coreSets.semanticErr);}
		} else {
			final Set fromCategory = category.from();
			final Set toCategory = category.to();
			if (
					((fromCategory.isEqualTo(firstSet.category())) || (fromCategory.isSuperSetOf(firstSet.category())).isEqualTo(coreSets.is_TRUE))
					&&
					(
							(toCategory.isEqualTo(secondSet.category()))
							|| (toCategory.isSuperSetOf(secondSet.category())).isEqualTo(coreSets.is_TRUE)
							|| (toCategory.isEqualTo(secondSet.flavor()))
							//|| (toCategory.isEqualTo(coreGraphs.graph)))
							|| (toCategory.isEqualTo(coreGraphs.graph))
							|| ((SemanticDomain.semanticIdentity.isSuperSetOf(toCategory.category())).isEqualTo(coreSets.is_TRUE))
							|| (((SemanticDomain.semanticIdentity.isSuperSetOf(secondSet)).isEqualTo(coreSets.is_TRUE)) && (secondSet.isElementOf(toCategory).isEqualTo(coreSets.is_TRUE)))

					)
			) {
				final int fromInstanceCount = firstSet.container().filterPolymorphic(category).filterByLinkedFrom(firstSet).size();
				final int toInstanceCount = firstSet.container().filterPolymorphic(category).filterByLinkedTo(secondSet).size();

				if (category.fromEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_1)) {
					if (toInstanceCount > 0) {return F_InstantiationImpl.raiseError(coreSets.semanticErr_maxFromCardinalityIsOne.identity(), coreSets.semanticErr);}
				}
				if (category.fromEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_2)) {
					if (toInstanceCount > 1) {return F_InstantiationImpl.raiseError(coreSets.semanticErr_maxFromCardinalityIsTwo.identity(), coreSets.semanticErr);}
				}
				if (category.fromEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_NOTAPPLICABLE)
						|| category.fromEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_UNKNOWN)) {
					return F_InstantiationImpl.raiseError(coreSets.semanticErr_maxFromCardinalityIsIllegal.identity(), coreSets.semanticErr);
				}
				if (category.toEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_1)) {
					if (fromInstanceCount > 0) {return F_InstantiationImpl.raiseError(coreSets.semanticErr_maxToCardinalityIsOne.identity(), coreSets.semanticErr);}
				}
				if (category.toEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_2)) {
					if (fromInstanceCount > 1) {return F_InstantiationImpl.raiseError(coreSets.semanticErr_maxToCardinalityIsTwo.identity(), coreSets.semanticErr);}
				}
				if (category.toEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_NOTAPPLICABLE)
						|| category.toEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_UNKNOWN)) {
					return F_InstantiationImpl.raiseError(coreSets.semanticErr_maxToCardinalityIsIllegal.identity(), coreSets.semanticErr);
				}
				return input.addEdge();
			} else {
				return F_InstantiationImpl.raiseError(coreSets.semanticErr_LinkIsNotApplicable.identity(), coreSets.semanticErr);
			}
		}
	}

	public static Set link(final Set category, final Set fromInstance, final Set toInstance) {
		if (category.flavor().isEqualTo(superSetReference)) {
			return F_Instantiation.addSuperSetReference(fromInstance, toInstance, category);
		} else if (category.flavor().isEqualTo(visibility)) {
			return F_Instantiation.addVisibility(fromInstance, toInstance);
		} else {
			return raiseError(coreSets.semanticErr_LinkIsNotApplicable.identity(), coreSets.semanticErr);
		}
	}
	/* only used for reconstitution during deserialisation */
	public static Set reconstituteLink(final Set category, final Identity identity, final Set fromInstance, final Set toInstance) {
		if (category.flavor().isEqualTo(superSetReference)) {
			return F_Instantiation.reconstituteSuperSetReference(identity, fromInstance, toInstance, category);
		} else if (category.flavor().isEqualTo(visibility)) {
			return F_Instantiation.reconstituteVisibility(identity, fromInstance, toInstance);
		} else {
			return raiseError(coreSets.semanticErr_LinkIsNotApplicable.identity(), coreSets.semanticErr);
		}
	}

	private Set linkSemanticIdentities() {
		// TODO Add relevant constraints for edges in Semantic Domain!
		return this.addEdge();
	}

	public static Set isAllowableEdgeFlavoredLinkCategory(final Set category,
			final Set firstSet,
			final Set secondSet
	) {

		if (category.isEqualTo(edge)) {
			if (    (firstSet.category().isEqualTo(graph))
					|| 	(firstSet.category().isEqualTo(link))
					|| 	(firstSet.category().isEqualTo(edge))
					|| 	(firstSet.category().isEqualTo(edgeEnd))
					|| 	(firstSet.category().isEqualTo(superSetReference))
					|| 	(firstSet.category().isEqualTo(visibility))
					|| 	(firstSet.category().isEqualTo(vertex))
					|| (((coreGraphs.vertex.isEqualTo(firstSet.category())) || (coreGraphs.vertex.isSuperSetOf(firstSet.category())).isEqualTo(coreSets.is_TRUE))
							&&
							((coreGraphs.vertex.isEqualTo(secondSet.category())) || (coreGraphs.vertex.isSuperSetOf(secondSet.category())).isEqualTo(coreSets.is_TRUE)))
			) {
				return coreSets.is_TRUE;} else {/* semanticErr_LinkIsNotApplicable */ return coreSets.is_FALSE;}
		} else {
			final Set fromCategory = category.from();
			final Set toCategory = category.to();

			if (
					((fromCategory.isEqualTo(firstSet.category())) || (fromCategory.isSuperSetOf(firstSet.category())).isEqualTo(coreSets.is_TRUE))
					&&
					(
							(toCategory.isEqualTo(secondSet.category()))
							|| (toCategory.isSuperSetOf(secondSet.category())).isEqualTo(coreSets.is_TRUE)
							|| (toCategory.isEqualTo(secondSet.flavor()))
							//|| (toCategory.isEqualTo(coreGraphs.graph)))
							|| (toCategory.isEqualTo(coreGraphs.graph))
							|| ((SemanticDomain.semanticIdentity.isSuperSetOf(toCategory.category())).isEqualTo(coreSets.is_TRUE))
							|| (((SemanticDomain.semanticIdentity.isSuperSetOf(secondSet)).isEqualTo(coreSets.is_TRUE)) && (secondSet.isElementOf(toCategory).isEqualTo(coreSets.is_TRUE)))

					)
			) {
				final int fromInstanceCount = firstSet.container().filterPolymorphic(category).filterByLinkedFrom(firstSet).size();
				final int toInstanceCount = firstSet.container().filterPolymorphic(category).filterByLinkedTo(secondSet).size();

				if (category.fromEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_1)) {
					if (toInstanceCount > 0) {// semanticErr_maxFromCardinalityIsOne
						return coreSets.is_FALSE;} else {return coreSets.is_TRUE;}
				}
				if (category.fromEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_2)) {
					if (toInstanceCount > 1) {//semanticErr_maxFromCardinalityIsTwo
						return coreSets.is_FALSE;} else {return coreSets.is_TRUE;}
				}
				if (category.fromEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_NOTAPPLICABLE)
						|| category.fromEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_UNKNOWN)) {//semanticErr_maxFromCardinalityIsIllegal
					return coreSets.is_FALSE;
				}
				if (category.toEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_1)) {
					if (fromInstanceCount > 0) {//raiseError(coreSets.semanticErr_maxToCardinalityIsOne
						return coreSets.is_FALSE;} else {return coreSets.is_TRUE;}
				}
				if (category.toEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_2)) {
					if (fromInstanceCount > 1) {//semanticErr_maxToCardinalityIsTwo
						return coreSets.is_FALSE;} else {return coreSets.is_TRUE;}
				}
				if (category.toEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_NOTAPPLICABLE)
						|| category.toEdgeEnd().value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_UNKNOWN)) {
					//semanticErr_maxToCardinalityIsIllegal
					return coreSets.is_FALSE;} else {return coreSets.is_TRUE;}
			}
			//semanticErr_LinkIsNotApplicable
			return coreSets.is_FALSE;
		}
	}

	private Set addEdge() {
		if (firstSet.hasVisibilityOf(secondSet).isEqualTo(coreSets.is_TRUE)) {
			return F_InstantiationImpl.createEdge(firstEdgeEndIdentity,
					firstSet,
					firstMinCardinality,
					firstMaxCardinality,
					firstIsNavigable,
					firstIsContainer,
					secondEdgeEndIdentity,
					secondSet,
					secondMinCardinality,
					secondMaxCardinality,
					secondIsNavigable,
					secondIsContainer,
					category,
					edgeFlavoredIdentity);
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_TargetIsNotWithinVisibility.identity(), coreSets.semanticErr);
		}
	}
}