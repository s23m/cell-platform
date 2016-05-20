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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import static org.s23m.cell.S23MKernel.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.s23m.cell.Identity;
import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.EventListener;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.core.collections.LazyValue;
import org.s23m.cell.core.collections.StripedLockArrayListMultiMap;
import org.s23m.cell.core.collections.highscalelib.NonBlockingHashMap;
import org.s23m.cell.impl.SemanticDomainCode;

public class OrderedSet extends OrderedPair implements Set, Iterable<Set> {

	private static final int NUMBER_OF_STRIPES = 16;

	/* Reify the S23M OrderedSet concept */
	protected static final OrderedSet orderedSet = new OrderedSet();

	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock writeLock = readWriteLock.writeLock();
	private final Lock readLock = readWriteLock.readLock();

	private final LazyValue<List<Set>> contentsHolder = new LazyValue<List<Set>>() {
		@Override protected List<Set> computeValue() {
			return new CopyOnWriteArrayList<Set>();
		}
	};

	private final LazyValue<StripedLockArrayListMultiMap<String, Set>> identifierMapHolder = new LazyValue<StripedLockArrayListMultiMap<String,Set>>() {
		@Override protected StripedLockArrayListMultiMap<String, Set> computeValue() {
			return new StripedLockArrayListMultiMap<String, Set>(NUMBER_OF_STRIPES);
		}
	};

	private final LazyValue<Map<String, Set>> mapHolder = new LazyValue<Map<String, Set>>() {
		@Override protected Map<String, Set> computeValue() {
			return new NonBlockingHashMap<String, Set>();
		}
	};

	private final LazyValue<List<EventListener>> subscribersHolder = new LazyValue<List<EventListener>>() {
		@Override protected List<EventListener> computeValue() {
			return new CopyOnWriteArrayList<EventListener>();
		}
	};

	protected OrderedSet(final Identity semanticIdentity, final Set category) {
		super(semanticIdentity, category);
	}

	protected OrderedSet(final Identity semanticIdentity) {
		super(semanticIdentity, OrderedSet.orderedSet);
	}

	OrderedSet() {
		super(identityFactory.orderedSet());
	}

	public static OrderedSet createTransientOrderedSet() {
		return new OrderedSet(F_Instantiation.identityFactory.aTransientResultSet());
	}

	@Override
	public Set properClass() {
		return OrderedSet.orderedSet;
	}

	// ***************************************************************
	//  Implementation of the equivalent of java.util.List operations
	// ***************************************************************

	protected boolean remove(final Set o) {
		try {
			writeLock.lock();

			final boolean isRemovedFromMap1 = getMap().remove(o.identity().identifier().toString()) != null;
			final boolean isRemovedFromMap2 = getMap().remove(o.identity().uniqueRepresentationReference().toString()) != null;
			final boolean isRemovedFromIteratorMap = removeByURR(o) != null;
			final boolean isRemovedFromIdMap1 = getIdentifierMap().remove(o.identity().identifier().toString()) == null;
			final boolean isRemovedFromIdMap2 = getIdentifierMap().remove(o.identity().uniqueRepresentationReference().toString()) == null;
			// Create and propagate Set Maintenance EventImpl
			final Set removeEvent = elementRemoved(o);
			final Iterator<EventListener> i = getSubscribers().iterator();
			while (i.hasNext()) {
				i.next().processEvent(removeEvent);
			}
			return isRemovedFromMap1 && isRemovedFromMap2 && isRemovedFromIdMap1 && isRemovedFromIdMap2 && isRemovedFromIteratorMap;
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public boolean containsSemanticMatch(final Set o) {
		return getIdentifierMap().containsKey(o.identity().identifier().toString());
	}

	@Override
	public boolean containsRepresentation(final Set o) {
		final Map<String, Set> map = getMap();
		return map.containsKey(o.identity().uniqueRepresentationReference().toString()) || map.containsKey(o.identity().identifier().toString());
	}

	@Override
	public boolean containsSemanticMatchesForAll(final Set c) {
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
		final Iterator<Set> i = c.iterator();
		while (i.hasNext()) {
			if (!this.containsRepresentation(i.next())) {
				return false;
			}
		}
		return true;
	}

	private Set getByUUID(final UUID o) {
		return getMap().get(o.toString());
	}

	private int iteratorIndexOfUUID(final List<Set> contents, final UUID o) {
		int i = 0;
		for (final Set set : contents) {
			final UUID key = getURR(set);
			if (key.equals(o)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Set getValue(final List<Set> contents, final int index) {
		return contents.get(index);
	}

	private Set removeByURR(final Set o) {
		// we don't rely on the equals method here, but do the checking based on URR instead
		final UUID uuidToRemove = getURR(o);
		for (final Set set : getContents()) {
			final UUID key = getURR(set);
			if (key.equals(uuidToRemove)) {
				return set;
			}
		}
		return null;
	}

	private UUID getURR(final Set o) {
		return o.identity().uniqueRepresentationReference();
	}

	@Override
	public boolean isEmpty() {
		return getContents().isEmpty();
	}

	@Override
	public Iterator<Set> iterator() {
		return getContents().iterator();
	}

	@Override
	public ListIterator<Set> listIterator() {
		return getContents().listIterator();
	}

	@Override
	public ListIterator<Set> listIterator(final int index) {
		return getContents().listIterator(index);
	}

	@Override
	public int size() {
		return getContents().size();
	}

	@Override
	public Set[] toArray() {
		try {
			readLock.lock();
			final List<Set> contents = getContents();
			return contents.toArray(new Set[contents.size()]);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Set[] toArray(final Set[] a) {
		return getContents().toArray(a);
	}

	@Override
	public List<Set> asList() {
		return new ArrayList<Set>(getContents());
	}

	protected boolean add(final Set o) {
		try {
			writeLock.lock();

			getContents().add(o);
			getMap().put(o.identity().uniqueRepresentationReference().toString(), o);
			getIdentifierMap().put(o.identity().identifier().toString(), o);
			if (!o.identity().uniqueRepresentationReference().equals(o.identity().identifier())) {
				getMap().put(o.identity().identifier().toString(), o);
				getIdentifierMap().put(o.identity().uniqueRepresentationReference().toString() , o);
			}

			// Create and propagate Set Maintenance EventImpl
			if (org.s23m.cell.core.F_SemanticStateOfInMemoryModel.cellKernelSemanticDomainIsInitialized()) {
				final Set addEvent = this.elementAdded(o);
				final Iterator<EventListener> i = getSubscribers().iterator();
				while (i.hasNext()) {
					i.next().processEvent(addEvent);
				}
			}
			return true;
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public Set extractFirst() {
		try {
			readLock.lock();
			final List<Set> contents = getContents();
			if (!contents.isEmpty()) {
				return contents.get(0);
			}
			return S23MSemanticDomains.is_NOTAPPLICABLE;
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Set extractSecond() {
		try {
			readLock.lock();
			final List<Set> contents = getContents();
			if (contents.size() > 1) {
				return contents.get(1);
			}
			return S23MSemanticDomains.is_NOTAPPLICABLE;
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Set extractLast() {
		try {
			readLock.lock();
			final List<Set> contents = getContents();
			if (!contents.isEmpty()) {
				return contents.get(contents.size() - 1);
			}
			return S23MSemanticDomains.is_NOTAPPLICABLE;
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Set extractNext(final Set element) {
		try {
			readLock.lock();
			final List<Set> contents = getContents();
			final int indexOf = iteratorIndexOfUUID(contents, getURR(element));
			if (indexOf >= 0) {
				final int next = indexOf + 1;
				if (size() > next && next > -1) {
					return getValue(contents, next);
				}
			}
			return S23MSemanticDomains.is_NOTAPPLICABLE;
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Set extractPrevious(final Set element) {
		try {
			readLock.lock();
			final List<Set> contents = getContents();
			final int indexOf = iteratorIndexOfUUID(contents, getURR(element));
			if (indexOf >= 0) {
				final int previous = indexOf - 1;
				if (-1 < previous && previous < size()) {
					return getValue(contents, previous);
				}
			}
			return S23MSemanticDomains.is_NOTAPPLICABLE;
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Set extractUniqueMatch(final Identity identity) {
		final Set i = getByUUID(identity.uniqueRepresentationReference());
		if (i != null) {
			return i;
		}
		final Set j = getByUUID(identity.identifier());
		if (j != null) {
			return j;
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
		final Set i = getByUUID(uuid);
		if (i != null) {
			return i;
		}
		return S23MSemanticDomains.is_NOTAPPLICABLE;
	}

	@Override
	public Set filterBySemanticIdentity(final Set set) {
		final OrderedSet result = createTransientOrderedSet();
		final Collection<Set> r = getIdentifierMap().get(set.identity().identifier().toString());
		if (r != null) {
			for (final Set element : r) {
				result.add(element);
			}
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
		final OrderedSet result = createTransientOrderedSet();
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
		final OrderedSet result = createTransientOrderedSet();
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
			return filterByLinkedTo(toSet);
		} else if (toSet.isInformation().is_FALSE()) {
			return filterByLinkedFrom(fromSet);
		} else {
			final OrderedSet result = createTransientOrderedSet();
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
	}

	@Override
	public Set filterFrom() {
		final OrderedSet result = createTransientOrderedSet();
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				result.add(element.from());
			}
		}
		return result;
	}

	@Override
	public Set filterTo() {
		final OrderedSet result = createTransientOrderedSet();
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				result.add(element.to());
			}
		}
		return result;
	}

	@Override
	public Set filterFromAndTo() {
		final OrderedSet result = createTransientOrderedSet();
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
		final OrderedSet result = createTransientOrderedSet();
		final Set semanticIdentities = toSetReferencedSemanticRole.container()
				.filterArrows(SemanticDomain.semanticRole_to_equivalenceClass, S23MSemanticDomains.is_NOTAPPLICABLE, toSetReferencedSemanticRole)
				.filterFrom();
		((OrderedSet) semanticIdentities).add(toSetReferencedSemanticRole);
		for (final Set element : this) {
			if (semanticIdentities.containsSemanticMatch(element.to())) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filterByLinkedFromSemanticRole(final Set fromSetReferencedSemanticRole) {
		final OrderedSet result = createTransientOrderedSet();
		final Set semanticIdentities = fromSetReferencedSemanticRole.container()
				.filterArrows(SemanticDomain.semanticRole_to_equivalenceClass, fromSetReferencedSemanticRole, S23MSemanticDomains.is_NOTAPPLICABLE)
				.filterTo();
		((OrderedSet) semanticIdentities).add(fromSetReferencedSemanticRole);
		for (final Set element : this) {
			if (semanticIdentities.containsSemanticMatch(element.from())) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filterByLinkedFromAndToSemanticRole(final Set fromSetReferencedSemanticRole, final Set toSetReferencedSemanticRole) {
		final OrderedSet result = createTransientOrderedSet();
		final Set fromSemanticIdentities = fromSetReferencedSemanticRole.container()
				.filterArrows(SemanticDomain.semanticRole_to_equivalenceClass, fromSetReferencedSemanticRole, S23MSemanticDomains.is_NOTAPPLICABLE)
				.filterTo();
		((OrderedSet) fromSemanticIdentities).add(fromSetReferencedSemanticRole);
		final Set toSemanticIdentities = toSetReferencedSemanticRole.container()
				.filterArrows(SemanticDomain.semanticRole_to_equivalenceClass, S23MSemanticDomains.is_NOTAPPLICABLE, toSetReferencedSemanticRole)
				.filterFrom();
		((OrderedSet) toSemanticIdentities).add(toSetReferencedSemanticRole);
		for (final Set element : this) {
			if (fromSemanticIdentities.containsSemanticMatch(element.from()) && toSemanticIdentities.containsSemanticMatch(element.to())) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filterByLinkedToVia(final Set toEdgeEnd) {
		final OrderedSet result = createTransientOrderedSet();
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
		final OrderedSet result = createTransientOrderedSet();
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
			return filterByLinkedToVia(toEdgeEnd);
		} else if (toEdgeEnd.isInformation().is_FALSE()) {
			return filterByLinkedFromVia(fromEdgeEnd);
		} else {
			final OrderedSet result = createTransientOrderedSet();
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
				|| properClass.isEqualTo(coreSets.orderedPair)) {
			final OrderedSet result = createTransientOrderedSet();
			for (final Set element : this) {
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
		final OrderedSet result = createTransientOrderedSet();
		Set aSemantics = set;
		if (SemanticDomain.semanticIdentity.isSuperSetOf(set.category()).is_FALSE()) {
			aSemantics = set.semanticIdentity();
		}
		aSemantics = SemanticDomainCode.transformSemanticRoleToEquivalenceClass(aSemantics);
		for (final Set element : this) {
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
		final OrderedSet result = createTransientOrderedSet();
		for (final Set element : this) {
			if (element.category().isEqualTo(category)) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filterPolymorphic(final Set category) {
		final OrderedSet result = createTransientOrderedSet();
		for (final Set element : this) {
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
		final OrderedSet result = createTransientOrderedSet();
		for (final Set element : this) {
			if (element.isAnArrow().is_TRUE())  {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set filterArrows(final Set properClassOrCategory, final Set fromSet, final Set toSet) {
		if (properClassOrCategory.isInformation().is_TRUE()) {
			if (fromSet.isInformation().is_FALSE() && toSet.isInformation().is_FALSE()) {
				if (properClassOrCategory.isAnArrow().is_TRUE()) {
					return filterProperClass(properClassOrCategory);
				} else {
					return filterArrows().filter(properClassOrCategory);
				}
			} else if (properClassOrCategory.isAnArrow().is_TRUE()) {
				return filterProperClass(properClassOrCategory).filterByLinkedFromAndTo(fromSet, toSet);
			} else {
				return filterArrows().filter(properClassOrCategory).filterByLinkedFromAndTo(fromSet, toSet);
			}
		} else {
			return filterArrows().filterByLinkedFromAndTo(fromSet, toSet);
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
		return new EventImpl(identityFactory.createAnonymousIdentity(), category, element, this);
	}

	private Set elementAdded(final Set newElement) {
		return createEvent(S23MKernel.coreSets.elementAdded, newElement);
	}

	private Set elementRemoved(final Set removedElement) {
		return createEvent(S23MKernel.coreSets.elementRemoved, removedElement);
	}

	@Override
	public Set addSubscriber(final EventListener instance) {
		getSubscribers().add(instance);
		return this;
	}

	@Override
	public Set removeSubscriber(final EventListener instance) {
		getSubscribers().remove(instance);
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
			final OrderedSet result = createTransientOrderedSet();
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
			final OrderedSet result = createTransientOrderedSet();
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
				final Set si = element.semanticIdentity();
				result = result.and(setSemantics.extractUniqueMatch(si)).isEqualTo(si, equivalenceClass);
			}
		}
		return result;
	}

	@Override
	public Set setMaintenanceCommand() {
		return this;
	}

	private List<Set> getContents() {
		return contentsHolder.get();
	}

	private Map<String, Set> getMap() {
		return mapHolder.get();
	}

	private List<EventListener> getSubscribers() {
		return subscribersHolder.get();
	}

	private StripedLockArrayListMultiMap<String,Set> getIdentifierMap() {
		return identifierMapHolder.get();
	}
}