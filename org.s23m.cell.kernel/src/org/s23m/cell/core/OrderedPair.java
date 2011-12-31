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

import static org.s23m.cell.G.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.s23m.cell.G;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.EventListener;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.VisitorFunction;
import org.s23m.cell.api.models.GmodelSemanticDomains;

public class OrderedPair implements Set {

	/* Reify the Gmodel OrderedPair concept */
	public static final OrderedPair orderedPair = new OrderedPair();

	protected Set category;
	private Identity element;

	protected OrderedPair(final Identity semanticIdentity, final Set categoryOfElement) {
		setElement(semanticIdentity);
		setCategory(categoryOfElement);
	}

	protected OrderedPair(final Identity semanticIdentity) {
		setElement(semanticIdentity);
		setCategory(this);
	}

	private OrderedPair() {
		setElement(identityFactory.orderedPair());
		setCategory(this);
	}

	public Set category() {
		return category;
	}
	/* orderedPairCategory() duplicates category() but is essential to break recursive loop in Graph */
	protected Set orderedPairCategory() {
		return category;
	}

	private void setCategory(final Set category) {
		this.category = category;
	}

	public Identity identity() {
		return element;
	}

	private void setElement(final Identity semanticIdentity) {
		this.element = semanticIdentity;
	}

	@Override
	public String toString() {
		return this.localVisualRecognitionText();
	}

	/* Implementation of semantics */

	public boolean isEqualTo(final Set orderedPair) {
		return identity().isEqualTo(orderedPair.identity());
	}

	public boolean isEqualToRepresentation(final Set orderedPair) {
		return identity().isEqualToRepresentation(orderedPair.identity());
	}


	public Set flavor() {
		if (isEqualTo(category()) ) {
			return this;
		} else {
			return category().flavor();
		}
	}

	protected boolean isFlavor() {
		return isEqualTo(Query.vertex) ||
		isEqualTo(Query.visibility) ||
		isEqualTo(Query.edge) ||
		isEqualTo(Query.superSetReference) ||
		isEqualTo(Query.link) ||
		isEqualTo(Query.orderedSet) ||
		isEqualTo(Query.graph) ||
		isEqualTo(coreSets.orderedPair);
	}

	protected boolean isLinkFlavor() {
		return isEqualTo(Query.visibility) ||
		isEqualTo(Query.edge) ||
		isEqualTo(Query.superSetReference) ||
		isEqualTo(Query.link);
	}

	public Set isALink() {
		final boolean flavorIsLinkConcept = flavor().isEqualTo(Query.visibility)
		|| flavor().isEqualTo(Query.edge)
		|| flavor().isEqualTo(Query.superSetReference);

		final boolean isLinkConcept = isEqualTo(Query.visibility)
		|| isEqualTo(Query.edge)
		|| isEqualTo(Query.superSetReference);

		if (flavorIsLinkConcept && !isLinkConcept) {
			return GmodelSemanticDomains.is_TRUE;
		} else {
			return GmodelSemanticDomains.is_FALSE;
		}
	}

	public Set addToVariables(final Set set) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addToValues(final Set set) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set container() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().container();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filter(final Set category) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().filter(category);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}


	public Set edgeEnds() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set containsEdgeTo(final Set orderedPair) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().containsEdgeTo(orderedPair);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set decommission() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterFlavor(final Set flavor) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().filterFlavor(flavor);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set from() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set fromEdgeEnd() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set hasVisibilityOf(final Set target) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().hasVisibilityOf(target);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterInstances() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().filterInstances();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set isExternal() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().isExternal();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set isLocalSuperSetOf(final Set orderedPair) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().isLocalSuperSetOf(orderedPair);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterLinks() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().filterLinks();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set localRootSuperSetOf(final Set orderedPair) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().localRootSuperSetOf(orderedPair);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set isSuperSetOf(final Set orderedPair) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().isSuperSetOf(orderedPair);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set directSuperSetOf(final Set orderedPair) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().directSuperSetOf(orderedPair);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set categoryOfVisibility(final Set target) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return ((Graph) this.semanticIdentity()).categoryOfVisibility(target);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set variables() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().variables();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set value(final Set variable) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().value(variable);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set values() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().values();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set removeFromVariables(final Set set) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set removeFromValues(final Set set) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set to() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set toEdgeEnd() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set visibleArtifactsForSubGraph(final Set subgraph) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().visibleArtifactsForSubGraph(subgraph);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addAbstract(final Set category, final Set semanticIdentity) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addConcrete(final Set category, final Set semanticIdentity) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public boolean containsSemanticMatch(final Set o) {
		return this.isEqualTo(o);
	}

	public boolean containsSemanticMatchesForAll(final Set c) {
		return false;
	}

	public boolean isEmpty() {
		return false;
	}

	public ListIterator<Set> listIterator() {
		return null;
	}

	public ListIterator<Set> listIterator(final int index) {
		return null;
	}

	public int size() {
		return 0;
	}

	public Set[] toArray() {
		return null;
	}

	public Set[] toArray(final Set[] a) {
		return null;
	}

	public List<Set> asList() {
		return null;
	}

	public Iterator<Set> iterator() {
		//	return null;
		return Collections.<Set>emptySet().iterator();
	}

	public Set addToCommands(final Set anElement) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addToQueries(final Set anElement) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set removeFromCommands(final Set anElement) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set removeFromQueries(final Set anElement) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set commands() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().commands();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set queries() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().queries();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public boolean containsAllRepresentations(final Set c) {
		return false;
	}

	public boolean containsRepresentation(final Set o) {
		return false;
	}

	public Set containsDecommissionedSets() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().containsDecommissionedSets();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set containsNewSets() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().containsNewSets();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set hasNewName() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().hasNewName();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set hasNewPluralName() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().hasNewPluralName();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set isDecommissioned() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().isDecommissioned();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set isNewInstance() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().isNewInstance();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set assignNewName(final String newName) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set assignNewPluralName(final String newPluralName) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set assignNewPayload(final String newPayload) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set decommissionPayload() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set hasDecommissionedPayload() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().hasDecommissionedPayload();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set hasNewPayload() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().hasNewPayload();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set allowableEdgeCategories(final Set orderedSet) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterPolymorphic(final Set category) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().filterPolymorphic(category);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}
	// only for performance optimisation
	protected Set transformOrderedPairToSemanticIdentity() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()
				&& this.flavor().isEqualTo(coreSets.orderedPair)) {
			if (this.isEqualTo(coreSets.isAbstract)) {return GmodelSemanticDomains.isAbstract;};
			if (this.isEqualTo(coreSets.isAbstract_TRUE)) {return GmodelSemanticDomains.isAbstract_TRUE;};
			if (this.isEqualTo(coreSets.isAbstract_FALSE)) {return GmodelSemanticDomains.isAbstract_FALSE;};

			if (this.isEqualTo(coreSets.minCardinality)) {return GmodelSemanticDomains.minCardinality;};
			if (this.isEqualTo(coreSets.minCardinality_0)) {return GmodelSemanticDomains.minCardinality_0;};
			if (this.isEqualTo(coreSets.minCardinality_1)) {return GmodelSemanticDomains.minCardinality_1;};
			if (this.isEqualTo(coreSets.minCardinality_2)) {return GmodelSemanticDomains.minCardinality_2;};
			if (this.isEqualTo(coreSets.minCardinality_n)) {return GmodelSemanticDomains.minCardinality_n;};
			if (this.isEqualTo(coreSets.minCardinality_NOTAPPLICABLE)) {return GmodelSemanticDomains.minCardinality_NOTAPPLICABLE;};
			if (this.isEqualTo(coreSets.minCardinality_UNKNOWN)) {return GmodelSemanticDomains.minCardinality_UNKNOWN;};

			if (this.isEqualTo(coreSets.maxCardinality)) {return GmodelSemanticDomains.maxCardinality;};
			if (this.isEqualTo(coreSets.maxCardinality_0)) {return GmodelSemanticDomains.maxCardinality_0;};
			if (this.isEqualTo(coreSets.maxCardinality_1)) {return GmodelSemanticDomains.maxCardinality_1;};
			if (this.isEqualTo(coreSets.maxCardinality_2)) {return GmodelSemanticDomains.maxCardinality_2;};
			if (this.isEqualTo(coreSets.maxCardinality_n)) {return GmodelSemanticDomains.maxCardinality_n;};
			if (this.isEqualTo(coreSets.maxCardinality_NOTAPPLICABLE)) {return GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE;};
			if (this.isEqualTo(coreSets.maxCardinality_UNKNOWN)) {return GmodelSemanticDomains.maxCardinality_UNKNOWN;};

			if (this.isEqualTo(coreSets.isNavigable)) {return GmodelSemanticDomains.isNavigable;};
			if (this.isEqualTo(coreSets.isNavigable_TRUE)) {return GmodelSemanticDomains.isNavigable_TRUE;};
			if (this.isEqualTo(coreSets.isNavigable_FALSE)) {return GmodelSemanticDomains.isNavigable_FALSE;};
			if (this.isEqualTo(coreSets.isNavigable_NOTAPPLICABLE)) {return GmodelSemanticDomains.isNavigable_NOTAPPLICABLE;};
			if (this.isEqualTo(coreSets.isNavigable_UNKNOWN)) {return GmodelSemanticDomains.isNavigable_UNKNOWN;};

			if (this.isEqualTo(coreSets.isContainer)) {return GmodelSemanticDomains.isContainer;};
			if (this.isEqualTo(coreSets.isContainer_TRUE)) {return GmodelSemanticDomains.isContainer_TRUE;};
			if (this.isEqualTo(coreSets.isContainer_FALSE)) {return GmodelSemanticDomains.isContainer_FALSE;};
			if (this.isEqualTo(coreSets.isContainer_NOTAPPLICABLE)) {return GmodelSemanticDomains.isContainer_NOTAPPLICABLE;};
			if (this.isEqualTo(coreSets.isContainer_UNKNOWN)) {return GmodelSemanticDomains.isContainer_UNKNOWN;};
		}
		return this;
	}

	// only for performance optimisation
	// this operation is not public because it only works if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized())
	public boolean isASemanticIdentity() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {
			if (this.category().isEqualTo(GmodelSemanticDomains.semanticIdentitySet)
					|| this.category().isEqualTo(GmodelSemanticDomains.disjunctSemanticIdentitySet)
					|| this.category().isEqualTo(GmodelSemanticDomains.semanticRole)
					|| this.category().isEqualTo(GmodelSemanticDomains.variantDisjunctSemanticIdentitySet)
			) {
				return true;
			}
		}
		return false;
	}

	public Set semanticIdentity() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {if (this.isASemanticIdentity()) {return this;}}
		final Set t = this.transformOrderedPairToSemanticIdentity();
		if (!t.isEqualToRepresentation(this)) {return t;}
		final Set r =  F_Query.inMemorySemanticIdentities().extractUniqueMatch(this);
		if (!r.is_NOTAPPLICABLE()) {return r;};
		if (this.identity().isPartOfKernel()
				|| this.flavor().isEqualTo(G.coreGraphs.superSetReference)
				|| this.flavor().isEqualTo(G.coreGraphs.visibility)) {
			return GmodelSemanticDomains.is_NOTAPPLICABLE;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.kernelDefect_KernelHasReachedAnIllegalState.identity(), coreSets.kernelDefect);
		}
	}

	public Set executableQueries() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().executableQueries();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set executableCommands() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().executableCommands();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);

	}

	public Set union(final Set set) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().union(set);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set intersection(final Set set) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().intersection(set);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set complement(final Set set) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().complement(set);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addElement(final Set s) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().addElement(s);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set removeElement(final Set e) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().removeElement(e);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addSemanticRole(final Set s) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set isElementOf(final Set semanticIdentity) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().isElementOf(semanticIdentity);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set transformToOrderedSetOfSemanticIdentities() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().transformToOrderedSetOfSemanticIdentities();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set processEvent(final Set event) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addSubscriber(final EventListener instance) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set removeSubscriber(final EventListener instance) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set generatingSet() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);

	}

	public Set generatingElement() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addGeneratingSet(final Set generatingSet) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set addGeneratingElement(final Set generatingElement) {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}
	public Set unionOfconnectingLinks(final Set instance) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().unionOfconnectingLinks(instance);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	/**
	 * Support for Information Quality Logic
	 */

	public Set not() {
		return semanticIdentity().not();
	}

	public Set and(final Set b) {
		return semanticIdentity().and(b);
	}

	public Set or(final Set b) {
		return semanticIdentity().or(b);
	}

	public Set isQuality() {
		return semanticIdentity().isQuality();
	}

	public Set isInformation() {
		return semanticIdentity().isInformation();
	}

	public boolean is_NOTAPPLICABLE() {
		return semanticIdentity().is_NOTAPPLICABLE();
	}

	public boolean is_FALSE() {
		return semanticIdentity().is_FALSE();
	}

	public boolean is_UNKNOWN() {
		return semanticIdentity().is_UNKNOWN();
	}

	public boolean is_TRUE() {
		return semanticIdentity().is_TRUE();
	}

	public Set includesValue(final Set value, final Set equivalenceClass) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().includesValue(value, equivalenceClass);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set and() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set or() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public String localVisualRecognitionText() {
		return this.visualRecognitionText() + " : " + this.category().visualRecognitionText();
	}

	public String visualRecognitionText() {
		return this.identity().name();
	}

	public String fullVisualRecognitionText() {
		return this.visualRecognitionText() + " : " + this.category().visualRecognitionText();
	}

	public String localVisualRecognitionTextWithEdgeEnds() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr).toString();
	}

	public Set elementsOfSemanticIdentitySet() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().elementsOfSemanticIdentitySet();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedTo(final Set toSet) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedFrom(final Set fromSet) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedFromAndTo(final Set fromSet, final Set toSet) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterLinks(final Set flavorOrCategory, final Set fromSet, final Set toSet) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedToVia(final Set toEdgeEnd) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedFromVia(final Set fromEdgeEnd) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedFromAndToVia(final Set fromEdgeEnd, final Set toEdgeEnd) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterFrom() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterTo() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterFromAndTo() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedToSemanticRole(final Set toSetReferencedSemanticRole) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedFromSemanticRole(final Set fromSetReferencedSemanticRole) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByLinkedFromAndToSemanticRole(final Set fromSetReferencedSemanticRole, final Set toSetReferencedSemanticRole) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set isEqualTo(final Set set, final Set equivalenceClass) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterBySemanticIdentity(final Set set) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set filterByEquivalenceClass(final Set set) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set extractUniqueMatch(final Set set) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set extractUniqueMatch(final Identity identity) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set extractUniqueMatch(final String uuidAsString) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return GmodelSemanticDomains.is_NOTAPPLICABLE;}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set extractFirst() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().extractFirst();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set extractSecond() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().extractSecond();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set extractLast() {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().extractLast();}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set extractNext(final Set element) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().extractNext(element);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set extractPrevious(final Set element) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().extractPrevious(element);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set initializeWalk(final VisitorFunction visitorFunction) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().initializeWalk(visitorFunction);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set walkDownThenRight(final VisitorFunction visitorFunction) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().walkDownThenRight(visitorFunction);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set walkDownThenLeft(final VisitorFunction visitorFunction) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().walkDownThenLeft(visitorFunction);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set walkRightThenDown(final VisitorFunction visitorFunction) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().walkRightThenDown(visitorFunction);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set walkLeftThenDown(final VisitorFunction visitorFunction) {
		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()) {return this.semanticIdentity().walkLeftThenDown(visitorFunction);}
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}

	public Set setMaintenanceCommand() {
		return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
	}
}
