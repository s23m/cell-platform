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
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Identity;
import org.s23m.cell.SemanticStateOfInMemoryModel;
import org.s23m.cell.Set;

public final class Edge extends Link  {

	private static final Set minCardinality_0 = coreSets.minCardinality_0;
	private static final Set minCardinality_1 = coreSets.minCardinality_1;

	private static final Set maxCardinality_1 = coreSets.maxCardinality_1;
	private static final Set maxCardinality_n = coreSets.maxCardinality_n;

	private static final Set isNavigable_FALSE = coreSets.isNavigable_FALSE;
	private static final Set isNavigable_TRUE = coreSets.isNavigable_TRUE;

	private static final Set isContainer_FALSE = coreSets.isContainer_FALSE;

	/* Reify the Gmodel Edge concept */
	public static final Edge edge =  new Edge();
	private Set container;
	private final OrderedSet edgeEnd = new OrderedSet(identityFactory.createAnonymousIdentityInKernel());

	protected Edge(final Identity firstEdgeEndIdentity,
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
			final Set secondIsContainer,
			final Set category) {
		// The new Edge must be made part of the same container as the firstProperty (a subtype Graph) edgeEnd!
		super(identityFactory.createAnonymousIdentity(firstSet.identity().isPartOfKernel()), category);
		this.setContainer(firstSet.container());
		this.addToValues(coreSets.isAbstract_FALSE);
		this.addEdgeEnds(firstEdgeEndIdentity,
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
				secondIsContainer);
		((Graph) this.container()).addToEdges(this);
		Graph.addSetToInMemorySets(this);
		if (SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			((Graph) this.container()).setContainsNewSets(true);
			Graph.addSetToChangedSets(this);
			Graph.addSetToChangedSets(this.container());
		}
	}
	protected Edge(final Identity firstEdgeEndIdentity,
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
			final Set secondIsContainer,
			final Set category,
			final Identity edgeFlavoredIdentity) {
		// The new Edge must be made part of the same container as the firstProperty (a subtype Graph) edgeEnd!
		super(edgeFlavoredIdentity, category);
		this.setContainer(firstSet.container());
		this.addToValues(coreSets.isAbstract_FALSE);
		this.addEdgeEnds(firstEdgeEndIdentity,
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
				secondIsContainer);
		((Graph) this.container()).addToEdges(this);
		Graph.addSetToInMemorySets(this);
		if (SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			((Graph) this.container()).setContainsNewSets(true);
			Graph.addSetToChangedSets(this);
			Graph.addSetToChangedSets(this.container());
		}
	}
	protected Edge(final Identity semanticIdentity,
			final Set firstOrderedPair,
			final Identity semanticIdentity2,
			final Set secondOrderedPair) {
		// The new Edge must be made part of the Graph.graph container!
		super(identityFactory.createAnonymousIdentity(firstOrderedPair.identity().isPartOfKernel()), Edge.edge);
		this.setContainer(Graph.graph);
		this.addToValues(coreSets.isAbstract_FALSE);
		this.addEdgeEnds(semanticIdentity,
				firstOrderedPair, minCardinality_0, maxCardinality_n, isNavigable_FALSE, isContainer_FALSE,
				semanticIdentity2,
				secondOrderedPair, minCardinality_1, maxCardinality_1, isNavigable_TRUE, isContainer_FALSE);
		((Graph) this.container()).addToEdges(this);
		Graph.addSetToInMemorySets(this);
		if (SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			((Graph) this.container()).setContainsNewSets(true);
			Graph.addSetToChangedSets(this);
			Graph.addSetToChangedSets(this.container());
		}
	}

	private Edge() {
		super(identityFactory.edge());
		this.setContainer(Graph.graph);
		this.addToValues(coreSets.isAbstract_FALSE);
		this.addFlavorQueries();
	}

	@Override
	public String toString() {
		return this.localVisualRecognitionText();
	}

	@Override
	public String localVisualRecognitionText() {
		if (this.category().isEqualTo(this)) {
			return visualRecognitionText() + " : " + visualRecognitionText();
		}
		if (this.isExternal().is_TRUE()) {
			return "(" + this.from().identity().name() + " -E-> "
			+ this.to().visualRecognitionText()  + ") : "
			+ this.category().localVisualRecognitionText();
		} else {
			return "(" + this.from().identity().name() + " -E-> "
			+ this.to().identity().name() + ") : "
			+ this.category().localVisualRecognitionText();
		}
	}
	@Override
	public String localVisualRecognitionTextWithEdgeEnds() {
		if (this.isExternal().is_TRUE()) {
			return "(" + this.from().identity().name()
			+ "[" + this.fromEdgeEnd().identity().name() + "] -E-> ["
			+ this.toEdgeEnd().identity().name() + "]"
			+ this.to().visualRecognitionText() + ")";
		} else {
			return "(" + this.from().identity().name()
			+ "[" + this.fromEdgeEnd().identity().name() + "] -E-> ["
			+ this.toEdgeEnd().identity().name() + "]"
			+  this.to().identity().name() + ")";
		}
	}
	@Override
	public String visualRecognitionText() {
		if (this.category().isEqualTo(this)) {
			return this.identity().name();
		} else {
			if (this.isExternal().is_TRUE()) {
				return "(" + this.from().identity().name() + " -E-> "
				+ this.to().visualRecognitionText() + ")."
				+ this.container().visualRecognitionText();
			} else {
				return "(" + this.from().identity().name() + " -E-> "
				+ this.to().identity().name() + ")."
				+ this.container().visualRecognitionText();
			}
		}
	}
	@Override
	public String fullVisualRecognitionText() {
		return this.visualRecognitionText() + " : " + this.category().visualRecognitionText();
	}


	/* Implementation of semantics */

	@Override
	public Set container() {
		return container;
	}
	private void setContainer(final Set set) {
		this.container = set;
	}

	private void addToEdgeEnds(final EdgeEnd anElement) {
		this.edgeEnd.add(anElement);
	}

	private void addEdgeEnds(final Identity firstEdgeEndIdentity,
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
			final Set secondIsContainer)
	{
		final EdgeEnd first = F_InstantiationImpl.createEdgeEnd(firstSet.container(), firstEdgeEndIdentity,  firstSet);
		first.addToValues(firstMinCardinality);
		first.addToValues(firstMaxCardinality);
		first.addToValues(firstIsNavigable);
		first.addToValues(firstIsContainer);
		this.addToEdgeEnds(first);
		final EdgeEnd second = F_InstantiationImpl.createEdgeEnd(firstSet.container(), secondEdgeEndIdentity,  secondSet);
		second.addToValues(secondMinCardinality);
		second.addToValues(secondMaxCardinality);
		second.addToValues(secondIsNavigable);
		second.addToValues(secondIsContainer);
		this.addToEdgeEnds(second);
	}

	@Override
	public Set isExternal() {
		for (final Set connectedSet : this.edgeEnds()) {
			if ((connectedSet.flavor().isEqualTo(coreSets.orderedPair)) && (!(this.container().isEqualTo(Graph.graph)))) {
				return coreSets.is_TRUE;
			} else
				if (!(((Graph)connectedSet).container().isEqualTo(this.container()))) {
					return coreSets.is_TRUE;
				}
		}
		return coreSets.is_FALSE;
	}

	@Override
	public final Set from() {
		if (!this.isEqualTo(edge)) {
			return ((EdgeEnd) fromEdgeEnd()).connectedSet();
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}
	@Override
	public final Set to() {
		if (!this.isEqualTo(edge)) {
			return ((EdgeEnd) toEdgeEnd()).connectedSet();
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public final Set fromEdgeEnd() {
		if (this.isEqualTo(Edge.edge)){
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
		return this.edgeEnds().extractFirst();
	}
	@Override
	public final Set toEdgeEnd() {
		if (this.isEqualTo(Edge.edge)){
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
		return this.edgeEnds().extractLast();
	}


	@Override
	public final Set edgeEnds() {
		return this.edgeEnd;
	}

	@Override
	public Set flavor() {
		return coreGraphs.edge;
	}
	/**
	 * EdgeFlavor queries
	 */
	@Override
	protected final void addFlavorQueries() {
		super.addFlavorQueries();
		this.addToQueries(coreSets.edgeEnds);
		this.addToQueries(coreSets.from);
		this.addToQueries(coreSets.to);
	}
}