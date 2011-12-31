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

package org.s23m.cell.core;

import static org.s23m.cell.G.coreSets;

import java.util.UUID;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.impl.SemanticDomainCode;

public final class F_Instantiation {

	public static final IdentityFactory identityFactory = new IdentityFactory();
	public static final XtensionIdentityFactory xtensionIdentityFactory = new XtensionIdentityFactory();

	public static Vertex addConcreteVertex(final Graph container, final Identity semanticIdentity, final Set category) {
		// the category (vertex) must be an isAbstract_FALSE vertex in the container.metaArtifact() container
		final Graph containerCategory = container.category();
		if (	(containerCategory.isSuperSetOf(category)).isEqualTo(coreSets.is_TRUE)
				|| (containerCategory.container().localRootSuperSetOf(containerCategory).isEqualTo(category.container().localRootSuperSetOf(category))) // see example ecoreERmodelling
				||	(containerCategory.getVertices().containsSemanticMatch(category))
				||	(containerCategory.containsEdgeTo(category).isEqualTo(coreSets.is_TRUE)))
		{
			if (category.value(coreSets.isAbstract).isEqualTo( coreSets.isAbstract_FALSE)) {

				if ((category.value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_1) &&  container.filterPolymorphic(category).size() < 1)
						||
						(category.value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_2) &&  container.filterPolymorphic(category).size() < 2)
						||
						(category.value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_n))
				) {
					final Vertex temp = new Vertex(container, semanticIdentity, category);
					temp.addToValues(coreSets.isAbstract_FALSE);
					return temp;
				} else {
					return F_InstantiationImpl.raiseError(coreSets.semanticErr_maxToCardinalityIsOne.identity(), coreSets.semanticErr);
				}
			}
		} else {
			if (F_SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
				// TODO Potentially add further kinds of vertices that appear in SemanticDomain!
				if (category.isEqualTo(SemanticDomain.semanticRole)) {
					final Vertex temp = new Vertex(container, semanticIdentity, category);
					temp.addToValues(coreSets.isAbstract_FALSE);
					return temp;
				}
			}
		}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor.identity(), coreSets.semanticErr);
	}

	public static Vertex addAbstractVertex(final Graph container, final Identity semanticIdentity, final Set category) {
		// the category must be an isAbstract_FALSE vertex in the container.category() container
		final Graph containerCategory = container.category();
		if (	(containerCategory.isSuperSetOf(category)).isEqualTo(coreSets.is_TRUE)
				|| (containerCategory.container().localRootSuperSetOf(containerCategory).isEqualTo(category.container().localRootSuperSetOf(category))) // see example ecoreERmodelling
				||	(containerCategory.getVertices().containsSemanticMatch(category))
				||	(containerCategory.containsEdgeTo(category).isEqualTo(coreSets.is_TRUE)))
		{
			if (category.value(coreSets.isAbstract).isEqualTo( coreSets.isAbstract_FALSE)) {
				if ((category.value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_1) &&  container.filterPolymorphic(category).size() < 1)
						||
						(category.value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_2) &&  container.filterPolymorphic(category).size() < 2)
						||
						(category.value(coreSets.maxCardinality).isEqualTo(coreSets.maxCardinality_n))
				) {
					final Vertex temp = new Vertex(container, semanticIdentity, category);
					temp.addToValues(coreSets.isAbstract_TRUE);
					return temp;

				} else {
					return F_InstantiationImpl.raiseError(coreSets.semanticErr_maxToCardinalityIsOne.identity(), coreSets.semanticErr);
				}
			}
		}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor.identity(), coreSets.semanticErr);
	}

	public static Set addVisibility(final Set fromSubGraph, final Set toSubGraph) {
		if (fromSubGraph.hasVisibilityOf(toSubGraph).isEqualTo(coreSets.is_TRUE)
				|| ((toSubGraph.category().isEqualTo(SemanticDomain.semanticdomain))
						&&
						!(fromSubGraph.category().isEqualTo(SemanticDomain.semanticdomain)))) {
			return new Visibility(fromSubGraph, toSubGraph, ((Graph)fromSubGraph).categoryOfVisibility(toSubGraph));
		}
		else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_TargetIsNotWithinVisibility.identity(), coreSets.semanticErr);
		}
	}

	/* only used for reconstitution during deserialisation */
	public static Set reconstituteVisibility(final Identity identity, final Set fromSubGraph, final Set toSubGraph) {
		if (fromSubGraph.hasVisibilityOf(toSubGraph).isEqualTo(coreSets.is_TRUE)
				|| ((toSubGraph.category().isEqualTo(SemanticDomain.semanticdomain))
						&&
						!(fromSubGraph.category().isEqualTo(SemanticDomain.semanticdomain)))) {
			return new Visibility(identity, fromSubGraph, toSubGraph, ((Graph)fromSubGraph).categoryOfVisibility(toSubGraph));
		}
		else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_TargetIsNotWithinVisibility.identity(), coreSets.semanticErr);
		}
	}

	public static Set addSuperSetReference(final Set subSet, final Set superSet, final Set category) {
		if (   (subSet.hasVisibilityOf(superSet).isEqualTo(coreSets.is_TRUE))
				|| (subSet.category().isEqualTo(superSet))
		) {
			return F_InstantiationImpl.createSuperSetReference(subSet, superSet, category);
		}
		else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_TargetIsNotWithinVisibility.identity(), coreSets.semanticErr);
		}
	}

	/* only used for reconstitution during deserialisation */
	public static Set reconstituteSuperSetReference(final Identity identity, final Set subSet, final Set superSet, final Set category) {
		if (   (subSet.hasVisibilityOf(superSet).isEqualTo(coreSets.is_TRUE))
				|| (subSet.category().isEqualTo(superSet))
		) {
			return new SuperSetReference(identity, subSet, superSet, category);
		}
		else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_TargetIsNotWithinVisibility.identity(), coreSets.semanticErr);
		}
	}

	public static Vertex instantiateConcreteArtifact(final Identity semanticIdentity, final Set category) {
		if (semanticIdentity.isEqualTo(SemanticDomain.semanticdomain.identity())) {
			final Vertex temp = new Vertex((Graph) Root.models, semanticIdentity, category);
			temp.addToValues(coreSets.isAbstract_FALSE);

			return temp;
		} else {
			if (category.identity().isEqualTo(SemanticDomain.semanticdomain.identity())) {
				return F_Instantiation.instantiateSemanticDomain(semanticIdentity);
			} else {
				if ((category).value(coreSets.isAbstract).isEqualTo( coreSets.isAbstract_FALSE)	) {
					final Vertex temp;
					temp = new Vertex((Graph) Root.models, semanticIdentity, category);
					temp.addToValues(coreSets.isAbstract_FALSE);
					return temp;
				} else {
					return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
				}
			}
		}
	}

	public static Vertex instantiateSemanticDomain(final Identity semanticIdentity) {
		final Vertex temp;
		temp = new Vertex((Graph) Root.semanticdomains, semanticIdentity, ((Graph) SemanticDomain.semanticdomain));
		temp.addToValues(coreSets.isAbstract_FALSE);

		return temp;
	}

	public static Vertex instantiateAbstractArtifact(final Identity semanticIdentity, final Set category) {
		if ((category).value(coreSets.isAbstract).isEqualTo( coreSets.isAbstract_FALSE)	) {
			final Vertex temp;
			temp = new Vertex((Graph) Root.models, semanticIdentity, category);
			temp.addToValues(coreSets.isAbstract_TRUE);
			return temp;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	public static Set addDisjunctSemanticIdentitySetInKernel(final String name, final String pluralName, final Set semanticDomain, final int index) {
		return ((Graph)semanticDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, identityFactory.createIdentityInKernel(name, pluralName, index));
	}

	public static Set addAnonymousDisjunctSemanticIdentitySetInKernel(final Set semanticDomain) {
		return ((Graph)semanticDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, identityFactory.createAnonymousIdentity(true));
	}

	public static Set link(final Set category,
			final Identity firstSemanticIdentity,
			final Set firstOrderedPair,
			final Set firstMinCardinality,
			final Set firstMaxCardinality,
			final Set firstIsNavigable,
			final Set firstIsContainer,
			final Identity secondSemanticIdentity,
			final Set secondOrderedPair,
			final Set secondMinCardinality,
			final Set secondMaxCardinality,
			final Set secondIsNavigable,
			final Set secondIsContainer
	) {
		return LinkConstraints.link(category,
				identityFactory.anonymous(),
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

	public static Set link(final Set category,
			final Identity edgeFlavoredIdentity,
			final Identity firstSemanticIdentity,
			final Set firstOrderedPair,
			final Set firstMinCardinality,
			final Set firstMaxCardinality,
			final Set firstIsNavigable,
			final Set firstIsContainer,
			final Identity secondSemanticIdentity,
			final Set secondOrderedPair,
			final Set secondMinCardinality,
			final Set secondMaxCardinality,
			final Set secondIsNavigable,
			final Set secondIsContainer
	) {
		return LinkConstraints.link(category,
				edgeFlavoredIdentity,
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

	public static Set link(final Set category, final Set fromInstance, final Set toInstance) {
		return LinkConstraints.link(category, fromInstance, toInstance);
	}

	// only for use in the context of deserialization!
	public static Set reconstituteLink(final Identity category,
			final Identity edgeFlavoredIdentity,
			final Identity firstSemanticIdentity,
			final Identity firstOrderedPair,
			final Identity firstMinCardinality,
			final Identity firstMaxCardinality,
			final Identity firstIsNavigable,
			final Identity firstIsContainer,
			final Identity secondSemanticIdentity,
			final Identity secondOrderedPair,
			final Identity secondMinCardinality,
			final Identity secondMaxCardinality,
			final Identity secondIsNavigable,
			final Identity secondIsContainer
	) {
		return link(F_Query.getSetFromLocalMemory(category),
				edgeFlavoredIdentity,
				firstSemanticIdentity,
				F_Query.getSetFromLocalMemory(firstOrderedPair),
				F_Query.getSetFromLocalMemory(firstMinCardinality),
				F_Query.getSetFromLocalMemory(firstMaxCardinality),
				F_Query.getSetFromLocalMemory(firstIsNavigable),
				F_Query.getSetFromLocalMemory(firstIsContainer),
				secondSemanticIdentity,
				F_Query.getSetFromLocalMemory(secondOrderedPair),
				F_Query.getSetFromLocalMemory(secondMinCardinality),
				F_Query.getSetFromLocalMemory(secondMaxCardinality),
				F_Query.getSetFromLocalMemory(secondIsNavigable),
				F_Query.getSetFromLocalMemory(secondIsContainer)
		) ;
	}

	public static Set addSemanticRole(final String name, final String pluralName, final Set semanticDomain, final Set referencedSemanticIdentity) {
		final Set result = ((Graph)semanticDomain).addConcrete(SemanticDomain.semanticRole, identityFactory.createIdentity(name, pluralName, Instantiation.indexIsNotAvailable));
		SemanticDomainCode.addSemanticRole(result, referencedSemanticIdentity);
		return result;
	}

	public static Set linkToEquivalenceClass(final Set newSemanticRole, final Set equivalenceClass) {
		return SemanticDomainCode.linkSemanticRole(newSemanticRole, equivalenceClass);
	}

	// only for use in the context of deserialization!
	public static Set reconstituteLink(final Identity category, final Identity edgeFlavoredIdentity, final Identity fromInstance, final Identity toInstance) {
		return LinkConstraints.reconstituteLink(F_Query.getSetFromLocalMemory(category), edgeFlavoredIdentity, F_Query.getSetFromLocalMemory(fromInstance), F_Query.getSetFromLocalMemory(toInstance));
	}

	// only for use in the context of deserialization!
	public static Identity reconstituteIdentity(final String name, final String pluralName, final UUID identifier, final UUID uniqueRepresentationReference) {
		return new IdentityImpl(name, pluralName, identifier, uniqueRepresentationReference);
	}

	public static Set raiseError(final Identity semanticIdentity, final Set category) {
		return LinkConstraints.raiseError(semanticIdentity, category);
	}

	// to reuse an existing SemanticIdentity
	public static Identity reuseSemanticIdentity(final Set semanticIdentity) {
		return new IdentityImpl(semanticIdentity.identity());
	}

	// only for use within the inner Shell � is not part of the public Gmodel API
	public static Identity reuseSemanticIdentity(final Identity semanticIdentity) {
		return new IdentityImpl(semanticIdentity);
	}
}

