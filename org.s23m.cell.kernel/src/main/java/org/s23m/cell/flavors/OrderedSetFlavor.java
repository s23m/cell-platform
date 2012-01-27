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

package org.s23m.cell.flavors;

import java.util.List;
import java.util.ListIterator;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.event.SetMaintenanceEvents;

/**
 * Operations that provide the List aspect of {@link org.s23m.cell.core.Graph Graphs}.
 * The use of Java-compatible signatures are a convenience to enable Gmodel to
 * easily talk to other Java based tools
 */
public interface OrderedSetFlavor extends SetMaintenanceEvents, Iterable<Set> {

	/**
	 * QUERIES
	 */

	Set and();
	Set and(Set b);

	/**
	 * Exposes the contents of this {@link Set} as a list
	 * 
	 * @return the contents as a list
	 */
	List<Set> asList();
	/**
	 * the complement of this and s
	 */
	Set complement(Set set);


	/**
	 * contains exactly the same representations of the sets within c
	 */
	boolean containsAllRepresentations(Set c);

	/**
	 * contains exactly the same representation of the set o
	 */
	boolean containsRepresentation(Set o);

	/**
	 * See {@link java.util.List#contains(Object)}
	 */
	boolean containsSemanticMatch(Set o);

	/**
	 * See {@link java.util.List#containsAll(java.util.Collection)}
	 */
	boolean containsSemanticMatchesForAll(Set c);

	Set extractUniqueMatch(Identity identity);

	Set extractUniqueMatch(Set set);

	Set extractUniqueMatch(String uuidAsString);

	/**
	 * Retrieves all elements of a given category that are contained in the flavored sets within this {@link Set}
	 * 
	 * @param category
	 * @return the resulting filtered list {@link Set}
	 */
	Set filter(Set category);
	Set filterByEquivalenceClass(Set set);
	Set filterByLinkedFrom(Set fromSet);
	Set filterByLinkedFromAndTo(Set fromSet,  Set toSet);
	Set filterByLinkedFromAndToSemanticRole(Set fromSetReferencedSemanticRole,  Set toSetReferencedSemanticRole);
	Set filterByLinkedFromAndToVia(Set fromEdgeEnd,  Set toEdgeEnd);
	Set filterByLinkedFromSemanticRole(Set fromSetReferencedSemanticRole);
	Set filterByLinkedFromVia(Set fromEdgeEnd);
	Set filterByLinkedTo(Set toSet);
	Set filterByLinkedToSemanticRole(Set toSetReferencedSemanticRole);
	Set filterByLinkedToVia(Set toEdgeEnd);
	Set filterBySemanticIdentity(Set set);

	/**
	 * Retrieves a given flavored Set contained within the Set
	 * 
	 * @param flavor
	 * @return the resulting filtered list {@link Set}
	 */
	Set filterFlavor(Set flavor);
	Set filterFrom();
	Set filterFromAndTo();
	Set filterInstances();
	/**
	 * queries that emulate graph functionality
	 */
	/**
	 * Retrieves the set of all links between Instances that are contained in this {@link Set}
	 * 
	 * @return the resulting list {@link Set}
	 */
	Set filterLinks();
	/**
	 * Retrieves a filtered set of all links between Instances that are contained in this {@link Set}
	 * 
	 * @return the resulting list {@link Set}
	 */
	Set filterLinks(Set flavorOrCategory,  Set fromSet,  Set toSet);
	/**
	 * Retrieves all elements of a given category or a subset of that category from the flavored sets within this {@link Set}
	 * 
	 * @param category
	 * @return the resulting filtered list {@link Set}
	 */
	Set filterPolymorphic(Set category);
	Set filterTo();
	Set extractFirst();
	Set extractSecond();
	Set extractLast();
	Set extractNext(Set element);
	Set extractPrevious(Set element);
	Set includesValue(Set value, Set equivalenceClass);
	/**
	 * the intersection of this and s
	 */
	Set intersection(Set set);
	/**
	 * See {@link java.util.List#isEmpty()}
	 */
	boolean isEmpty();
	Set isEqualTo(Set set, Set equivalenceClass);

	/**
	 * See {@link java.util.List#listIterator()}
	 */
	ListIterator<Set> listIterator();

	/**
	 * See {@link java.util.List#listIterator(int)}
	 */
	ListIterator<Set> listIterator(int index);
	/**
	 * Support for Information Quality Logic
	 */
	Set not();

	Set or();
	Set or(Set b);
	/**
	 * See {@link java.util.List#size()}
	 */
	int size();
	/**
	 * See {@link java.util.List#toArray()}
	 */
	Set[] toArray();
	/**
	 * See {@link java.util.List#toArray(Object[])}
	 */
	Set[] toArray(Set[] a);
	/**
	 * transform into a corresponding ordered set of semantic identities, applying semantic equivalence rules,
	 * eliminating any duplicated representations from the result
	 */
	Set transformToOrderedSetOfSemanticIdentities();
	/**
	 * the union of this and s
	 */
	Set union(Set set);



}
