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
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import static org.s23m.cell.S23MKernel.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.s23m.cell.Identity;
import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.EventListener;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.impl.SemanticDomainCode;

@SuppressWarnings("unchecked")
public class OrderedSet extends OrderedPair implements Set, Iterable<Set> {

	/* Reify the S23M OrderedSet concept */
	protected static final OrderedSet orderedSet = new OrderedSet();

	private boolean listInitialized = false;

	// TODO use an equivalent from Google Guava
	private ListOrderedMap map;
	private ListOrderedMap iteratorMap;

	// TODO use an equivalent from Google Guava
	private MultiMap identifierMap;

	private List<EventListener> subscribers;

	protected OrderedSet(final Identity semanticIdentity, final Set category) {
		super(semanticIdentity, category);
	}

	protected OrderedSet(final Identity semanticIdentity) {
		super(semanticIdentity, OrderedSet.orderedSet);
	}

	OrderedSet() {
		super(identityFactory.orderedSet());
	}

	@Override
	public Set properClass() {
		return OrderedSet.orderedSet;
	}

	// ***************************************
	//    Implementation of the equivalent of java.util.List operations
	// ***************************************

	private void ensureInitializedList() {
		if (!listInitialized) {
			iteratorMap = new ListOrderedMap();
			map = new ListOrderedMap();
			identifierMap = new MultiValueMap();
			subscribers = new ArrayList<EventListener>();
			listInitialized = true;
		}
	}

	protected boolean remove(final Set o) {
		this.ensureInitializedList();
		final boolean isRemovedFromMap1 =
				(this.map.remove(o.identity().identifier().toString()) != null)
				? true : false;
		final boolean isRemovedFromMap2 =
				(this.map.remove(o.identity().uniqueRepresentationReference().toString()) != null)
				? true : false;
		final boolean isRemovedFromIteratorMap =
				(this.iteratorMap.remove(o.identity().uniqueRepresentationReference().toString()) != null)
				? true : false;
		final boolean isRemovedFromIdMap1 =
				(this.identifierMap.remove(o.identity().identifier().toString()) != null)
				? true : false;
		final boolean isRemovedFromIdMap2 =
				(this.identifierMap.remove(o.identity().uniqueRepresentationReference().toString()) != null)
				? true : false;
		// Create and propagate Set Maintenance EventImpl
		final Set removeEvent = this.elementRemoved(o);
		final Iterator<EventListener> i = subscribers.iterator();
		while (i.hasNext()) {
			i.next().processEvent(removeEvent);
		}
		return isRemovedFromMap1 && isRemovedFromMap2 && isRemovedFromIdMap1 && isRemovedFromIdMap2 && isRemovedFromIteratorMap;
	}

	@Override
	public boolean containsSemanticMatch(final Set o) {
		this.ensureInitializedList();
		return this.identifierMap.containsKey(o.identity().identifier().toString());
	}

	@Override
	public boolean containsRepresentation(final Set o) {
		this.ensureInitializedList();
		return (this.map.containsKey(o.identity().uniqueRepresentationReference().toString())) || (this.map.containsKey(o.identity().identifier().toString()));
	}

	@Override
	public boolean containsSemanticMatchesForAll(final Set c) {
		this.ensureInitializedList();
		final Iterator<Set> i = c.iterator();
		while (i.hasNext()) {
			if (!this.containsSemanticMatch(i.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean containsAllRepresentations(final Set c) {
		this.ensureInitializedList();
		final Iterator<Set> i = c.iterator();
		while (i.hasNext()) {
			if (!this.containsRepresentation(i.next())) {
				return false;
			}
		}
		return true;
	}

	private Set get(final int index) {
		this.ensureInitializedList();
		return (Set) this.map.getValue(index);
	}
	private int indexOfUUID(final UUID o) {
		this.ensureInitializedList();
		return this.map.indexOf(o.toString());
	}
	private Set getValue(final int index) {
		this.ensureInitializedList();
		return (Set) this.iteratorMap.getValue(index);
	}
	private int iteratorIndexOfUUID(final UUID o) {
		this.ensureInitializedList();
		return this.iteratorMap.indexOf(o.toString());
	}

	@Override
	public boolean isEmpty() {
		this.ensureInitializedList();
		return this.iteratorMap.isEmpty();
	}

	@Override
	public Iterator<Set> iterator() {
		this.ensureInitializedList();
		return this.iteratorMap.values().iterator();
	}

	@Override
	public ListIterator<Set> listIterator() {
		this.ensureInitializedList();
		return new ArrayList(Arrays.asList(this.iteratorMap.values().toArray())).listIterator();
	}

	@Override
	public ListIterator<Set> listIterator(final int index) {
		this.ensureInitializedList();
		return new ArrayList(Arrays.asList(this.iteratorMap.values().toArray())).listIterator(index);
	}

	@Override
	public int size() {
		this.ensureInitializedList();
		return this.iteratorMap.size();
	}

	@Override
	public Set[] toArray() {
		this.ensureInitializedList();
		return (Set[]) this.iteratorMap.values().toArray();
	}

	@Override
	public Set[] toArray(final Set[] a) {
		this.ensureInitializedList();
		return (Set[]) this.iteratorMap.values().toArray(a);
	}

	@Override
	public List<Set> asList() {
		this.ensureInitializedList();
		return new ArrayList<Set>(this.iteratorMap.values());
	}

	protected boolean add(final Set o) {
		this.ensureInitializedList();
		this.iteratorMap.put(o.identity().uniqueRepresentationReference().toString(), o);
		this.map.put(o.identity().uniqueRepresentationReference().toString(), o);
		this.identifierMap.put(o.identity().identifier().toString() , o);
		if (!o.identity().uniqueRepresentationReference().equals(o.identity().identifier())) {
			this.map.put(o.identity().identifier().toString(), o);
			this.identifierMap.put(o.identity().uniqueRepresentationReference().toString() , o);
		}

		// Create and propagate Set Maintenance EventImpl
		if (org.s23m.cell.core.F_SemanticStateOfInMemoryModel.cellKernelSemanticDomainIsInitialized()) {
			final Set addEvent = this.elementAdded(o);
			final Iterator<EventListener> i = subscribers.iterator();
			while (i.hasNext()) {
				i.next().processEvent(addEvent);
			}
		}
		return true;
	}
	@Override
	public Set extractFirst() {
		if (this.size() > 0) {
			return this.getValue(0);
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}
	@Override
	public Set extractSecond() {
		if (this.size() > 1) {
			return this.getValue(1);
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}
	@Override
	public Set extractLast() {
		if (this.size() > 0) {
			return this.getValue(this.size()-1);
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}
	@Override
	public Set extractNext(final Set element) {
		final int next = this.iteratorIndexOfUUID(element.identity().uniqueRepresentationReference()) +1;
		if (this.size() > next && next > -1) {
			return this.getValue(next);
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}
	@Override
	public Set extractPrevious(final Set element) {
		final int previous = this.iteratorIndexOfUUID(element.identity().uniqueRepresentationReference()) -1;
		if (-1 < previous && previous < this.size()) {
			return this.getValue(previous);
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}
	@Override
	public Set extractUniqueMatch(final Identity identity) {
		final int i = this.indexOfUUID(identity.uniqueRepresentationReference());
		final int j = this.indexOfUUID(identity.identifier());
		if (i > -1) {
			return this.get(i);
		}
		if (j > -1) {
			return this.get(j);
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}
	@Override
	public Set extractUniqueMatch(final Set set) {
		return extractUniqueMatch(set.identity());
	}

	@Override
	public Set extractUniqueMatch(final String uuidAsString) {
		final UUID uuid = UUID.fromString(uuidAsString);
		final int i = this.indexOfUUID(uuid);
		if (i >= -1) {
			return this.get(i);
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}

	@Override
	public Set filterBySemanticIdentity(final Set set) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		final List<Set> r = (List<Set>) this.identifierMap.get(set.identity().identifier().toString());
		for (final Set element : r) {
			result.add(element);
		}
		return result;
	}

	/**
	 * the union of this and s
	 */
	@Override
	public Set union(final Set set) {
		return F_SetAlgebra.union(this, set);
	}

	/**
	 * the intersection of this and s
	 */
	@Override
	public Set intersection(final Set set) {
		return F_SetAlgebra.intersection(this, set);
	}

	/**
	 * the complement of this and s
	 */
	@Override
	public Set complement(final Set set) {
		return F_SetAlgebra.complement(this, set);
	}
	@Override
	public Set filterByLinkedTo(final Set toSet) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				if (element.to().isEqualToRepresentation(toSet)) {
					result.add(element);
				}
			}
		}
		return result;
	}
	@Override
	public Set filterByLinkedFrom(final Set fromSet) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				if (element.from().isEqualToRepresentation(fromSet)) {
					result.add(element);
				}
			}
		}
		return result;
	}
	@Override
	public Set filterByLinkedFromAndTo(final Set fromSet, final Set toSet) {
		if (fromSet.isInformation().is_FALSE()) {
			return this.filterByLinkedTo(toSet);
		}
		if (toSet.isInformation().is_FALSE()) {
			return this.filterByLinkedFrom(fromSet);
		}
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				if (element.to().isEqualToRepresentation(toSet)
						&& element.from().isEqualToRepresentation(fromSet)) {
					result.add(element);
				}
			}
		}
		return result;
	}
	@Override
	public Set filterFrom() {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				result.add(element.from());
			}
		}
		return result;
	}
	@Override
	public Set filterTo() {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				result.add(element.to());
			}
		}
		return result;
	}
	@Override
	public Set filterFromAndTo() {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				result.add(element.from());
				result.add(element.to());
			}
		}
		return result;
	}
	@Override
	public Set filterByLinkedToSemanticRole(final Set toSetReferencedSemanticRole) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		final Set semanticIdentities = toSetReferencedSemanticRole.container()
				.filterArrows(SemanticDomain.semanticRole_to_equivalenceClass, S23MSemanticDomains.is_NOTAPPLICABLE, toSetReferencedSemanticRole)
				.filterFrom();
		((OrderedSet)semanticIdentities).add(toSetReferencedSemanticRole);
		for (final Set element : this) {
			if (semanticIdentities.containsSemanticMatch(element.to())) {
				result.add(element);
			}
		}
		return result;
	}
	@Override
	public Set filterByLinkedFromSemanticRole(final Set fromSetReferencedSemanticRole) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		final Set semanticIdentities = fromSetReferencedSemanticRole.container()
				.filterArrows(SemanticDomain.semanticRole_to_equivalenceClass, fromSetReferencedSemanticRole, S23MSemanticDomains.is_NOTAPPLICABLE)
				.filterTo();
		((OrderedSet)semanticIdentities).add(fromSetReferencedSemanticRole);
		for (final Set element : this) {
			if (semanticIdentities.containsSemanticMatch(element.from())) {
				result.add(element);
			}
		}
		return result;
	}
	@Override
	public Set filterByLinkedFromAndToSemanticRole(final Set fromSetReferencedSemanticRole, final Set toSetReferencedSemanticRole) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		final Set fromSemanticIdentities = fromSetReferencedSemanticRole.container()
				.filterArrows(SemanticDomain.semanticRole_to_equivalenceClass, fromSetReferencedSemanticRole, S23MSemanticDomains.is_NOTAPPLICABLE)
				.filterTo();
		((OrderedSet)fromSemanticIdentities).add(fromSetReferencedSemanticRole);
		final Set toSemanticIdentities = toSetReferencedSemanticRole.container()
				.filterArrows(SemanticDomain.semanticRole_to_equivalenceClass, S23MSemanticDomains.is_NOTAPPLICABLE, toSetReferencedSemanticRole)
				.filterFrom();
		((OrderedSet)toSemanticIdentities).add(toSetReferencedSemanticRole);
		for (final Set element : this) {
			if (fromSemanticIdentities.containsSemanticMatch(element.from()) && toSemanticIdentities.containsSemanticMatch(element.to())) {
				result.add(element);
			}
		}
		return result;
	}
	@Override
	public Set filterByLinkedToVia(final Set toEdgeEnd) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				if (element.toEdgeEnd().isEqualTo(toEdgeEnd)) {
					result.add(element);
				}
			}
		}
		return result;
	}

	@Override
	public Set filterByLinkedFromVia(final Set fromEdgeEnd) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				if (element.fromEdgeEnd().isEqualTo(fromEdgeEnd)) {
					result.add(element);
				}
			}
		}
		return result;
	}

	@Override
	public Set filterByLinkedFromAndToVia(final Set fromEdgeEnd, final Set toEdgeEnd) {
		if (fromEdgeEnd.isInformation().is_FALSE()) {
			return this.filterByLinkedToVia(toEdgeEnd);
		}
		if (toEdgeEnd.isInformation().is_FALSE()) {
			return this.filterByLinkedFromVia(fromEdgeEnd);
		}
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				if (element.toEdgeEnd().isEqualTo(toEdgeEnd)
						&& element.fromEdgeEnd().isEqualTo(fromEdgeEnd)) {
					result.add(element);
				}
			}
		}
		return result;
	}

	/**
	 * queries that emulate graph functionality
	 */

	@Override
	public Set filterProperClass(final Set properClass) {
		if (properClass.isEqualTo(F_Query.vertex())
				|| properClass.isEqualTo(F_Query.graph())
				|| properClass.isEqualTo(F_Query.visibility())
				|| properClass.isEqualTo(F_Query.superSetReference())
				|| properClass.isEqualTo(F_Query.edge())
				|| properClass.isEqualTo(F_Query.edgeEnd())
				|| properClass.isEqualTo(F_Query.orderedSet())
				|| properClass.isEqualTo(coreSets.orderedPair)
				) {
			final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
			for ( final Set element : this) {
				if (element.properClass().isEqualTo(properClass)) {
					result.add(element);
				}
			}
			return result;
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	@Override
	public Set filterByEquivalenceClass(final Set set) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		Set aSemantics = set;
		if (SemanticDomain.semanticIdentity.isSuperSetOf(set.category()).is_FALSE()) {
			aSemantics = set.semanticIdentity();
		}
		aSemantics = SemanticDomainCode.transformSemanticRoleToEquivalenceClass(aSemantics);
		for ( final Set element : this) {
			Set bSemantics = element;
			if (SemanticDomain.semanticIdentity.isSuperSetOf(element.category()).is_FALSE()) {
				bSemantics = element.semanticIdentity();
			}
			if (isEqualTo(aSemantics,(SemanticDomainCode.transformSemanticRoleToEquivalenceClass(bSemantics))).is_TRUE()) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filter(final Set category) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for ( final Set element : this) {
			if (element.category().isEqualTo(category)) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filterPolymorphic(final Set category) {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for ( final Set element : this) {
			if (category.isSuperSetOf(element.category()).isEqualTo(coreSets.is_TRUE)
					&& category.isSuperSetOf(element).isEqualTo(coreSets.is_FALSE)) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filterInstances() {
		return this;
	}
	@Override
	public Set filterArrows() {
		final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
		for ( final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filterArrows(final Set properClassOrCategory, final Set fromSet, final Set toSet) {
		if (properClassOrCategory.isInformation().is_TRUE()) {
			if (fromSet.isInformation().is_FALSE()
					&& toSet.isInformation().is_FALSE()) {
				if (properClassOrCategory.isAnArrow().is_TRUE()) {
					return this.filterProperClass(properClassOrCategory);
				} else {
					return this.filterArrows().filter(properClassOrCategory);
				}
			}
			if (properClassOrCategory.isAnArrow().is_TRUE()) {
				return this.filterProperClass(properClassOrCategory).filterByLinkedFromAndTo(fromSet, toSet);
			} else {
				return this.filterArrows().filter(properClassOrCategory).filterByLinkedFromAndTo(fromSet, toSet);
			}
		} else {
			return this.filterArrows().filterByLinkedFromAndTo(fromSet, toSet);
		}
	}

	/**
	 * transform into a corresponding ordered set of semantic identities, applying semantic equivalence rules,
	 * eliminating any duplicated representations from the result
	 */
	@Override
	public Set transformToOrderedSetOfSemanticIdentities() {
		return F_SetAlgebra.transformToOrderedSetOfSemanticIdentities(this);
	}

	private Set createEvent(final Set category, final Set element) {
		return  new EventImpl(identityFactory.createAnonymousIdentity(), category, element, this);
	}
	private Set elementAdded(final Set newElement) {
		final Set addEvent = createEvent(S23MKernel.coreSets.elementAdded, newElement);
		return addEvent;
	}

	private Set elementRemoved(final Set removedElement) {
		final Set removeEvent = createEvent(S23MKernel.coreSets.elementRemoved, removedElement);
		return removeEvent;
	}

	@Override
	public Set addSubscriber(final EventListener instance) {
		subscribers.add(instance);
		return this;
	}

	@Override
	public Set removeSubscriber(final EventListener instance) {
		subscribers.remove(instance);
		return this;
	}
	/**
	 * Support for Information Quality Logic
	 */
	@Override
	public Set not() {
		return F_IqLogic.not(this);
	}

	@Override
	public Set and(final Set b) {
		if (b.properClass().isEqualTo(Query.orderedSet)) {
			return F_IqLogic.and(this.union(b));
		} else {
			final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
			for (final Set element : this) {
				result.add(element);
			}
			result.add(b);
			return F_IqLogic.and(result);
		}
	}

	@Override
	public Set or(final Set b) {
		if (b.properClass().isEqualTo(Query.orderedSet)) {
			return F_IqLogic.or(this.union(b));
		} else {
			final OrderedSet result = new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
			for (final Set element : this) {
				result.add(element);
			}
			result.add(b);
			return F_IqLogic.or(result);
		}
	}

	@Override
	public Set and() {
		return F_IqLogic.and(this);
	}

	@Override
	public Set or() {
		return F_IqLogic.or(this);
	}

	@Override
	public Set includesValue(final Set value, final Set equivalenceClass) {
		return F_IqLogic.includesValue(this, value, equivalenceClass);
	}

	@Override
	public Set isEqualTo(final Set set, final Set equivalenceClass) {
		final Set setSemantics = set.transformToOrderedSetOfSemanticIdentities();
		Set result = S23MSemanticDomains.is_FALSE;
		for (final Set element : this) {
			if (setSemantics.containsSemanticMatch(element)) {
				result = result.and(setSemantics.extractUniqueMatch(element.semanticIdentity())).isEqualTo(element.semanticIdentity(), equivalenceClass);
			}
		}
		return result;
	}
	@Override
	public Set setMaintenanceCommand() {
		return this;

	}
}