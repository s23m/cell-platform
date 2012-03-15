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
 * Chul Kim
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.core.OrderedSet;

@SuppressWarnings("serial")
public class EdgeData implements Serializable {

	public static final String SECOND_IS_CONTAINER = "secondIsContainer";
	public static final String SECOND_IS_NAVIGABLE = "secondIsNavigable";
	public static final String SECOND_MAX_CARDINALITY = "secondMaxCardinality";
	public static final String SECOND_MIN_CARDINALITY = "secondMinCardinality";
	public static final String SECOND_SEMANTIC_IDENTITY = "secondSemanticIdentity";
	public static final String TARGET_INSTANCE = "targetInstance";
	public static final String FIRST_IS_CONTAINER = "firstIsContainer";
	public static final String FIRST_IS_NAVIGABLE = "firstIsNavigable";
	public static final String FIRST_MAX_CARDINALITY = "firstMaxCardinality";
	public static final String FIRST_MIN_CARDINALITY = "firstMinCardinality";
	public static final String FIRST_SEMANTIC_IDENTITY = "firstSemanticIdentity";
	public static final String SOURCE_INSTANCE = "sourceInstance";
	public static final String EDGE_IDENTITY_INSTANCE = "edgeIdentityInstance";
	public static final String META_ELEMENT = "metaElement";

	private static final String[] DISPLAYED_TABLE_INSTANCES = {
		// META_INSTANCE
		EDGE_IDENTITY_INSTANCE,
		SOURCE_INSTANCE,
		// FIRST_SEMANTIC_IDENTITY
		FIRST_MIN_CARDINALITY,
		FIRST_MAX_CARDINALITY,
		// FIRST_IS_NAVIGABLE
		// FIRST_IS_CONTAINER
		TARGET_INSTANCE,
		// SECOND_SEMANTIC_IDENTITY
		SECOND_MIN_CARDINALITY,
		SECOND_MAX_CARDINALITY
		// SECOND_IS_NAVIGABLE
		// SECOND_IS_CONTAINER
	};

	private static final java.util.Set<String> INSTANCES_WITH_ASSOCIATED_LIST = new HashSet<String>(Arrays.asList(
		FIRST_MIN_CARDINALITY,
		FIRST_MAX_CARDINALITY,
		FIRST_IS_NAVIGABLE,
		FIRST_IS_CONTAINER,
		SECOND_MIN_CARDINALITY,
		SECOND_MAX_CARDINALITY,
		SECOND_IS_NAVIGABLE,
		SECOND_IS_CONTAINER
	));

	private static final List<String> DISPLAYED_INSTANCES = Arrays.asList(
		META_ELEMENT,
		EDGE_IDENTITY_INSTANCE,
		SOURCE_INSTANCE,
		FIRST_SEMANTIC_IDENTITY,
		FIRST_MIN_CARDINALITY,
		FIRST_MAX_CARDINALITY,
		FIRST_IS_NAVIGABLE,
		FIRST_IS_CONTAINER,
		TARGET_INSTANCE,
		SECOND_SEMANTIC_IDENTITY,
		SECOND_MIN_CARDINALITY,
		SECOND_MAX_CARDINALITY,
		SECOND_IS_NAVIGABLE,
		SECOND_IS_CONTAINER
	);

	private Set metaElement = S23MKernel.coreGraphs.edge;
	private Set edgeIdentityInstance;
	private Set sourceInstance;
	private Set targetInstance;
	private Set firstSemanticIdentity;
	private Set firstMinCardinality;
	private Set firstMaxCardinality;
	private Set firstIsNavigable;
	private Set firstIsContainer;
	private Set secondSemanticIdentity;
	private Set secondMinCardinality;
	private Set secondMaxCardinality;
	private Set secondIsNavigable;
	private Set secondIsContainer;

	public EdgeData(final Set sourceInstance) {
		this.sourceInstance = sourceInstance;
	}

	public EdgeData(final Set metaInstance,
					final Set sourceInstance,
					final Set targetInstance,
					final Set edgeIdentityInstance,
					final Set firstSemanticIdentity,
					final Set secondSemanticIdentity,
					final Set firstMinCardinality,
					final Set firstMaxCardinality,
					final Set firstIsNavigable,
					final Set firstIsContainer,
					final Set secondMinCardinality,
					final Set secondMaxCardinality,
					final Set secondIsNavigable,
					final Set secondIsContainer) {

		this.metaElement = metaInstance;
		this.sourceInstance = sourceInstance;
		this.targetInstance = targetInstance;
		this.edgeIdentityInstance = edgeIdentityInstance;
		this.firstSemanticIdentity = firstSemanticIdentity;
		this.secondSemanticIdentity = secondSemanticIdentity;
		this.firstMinCardinality = firstMinCardinality;
		this.firstMaxCardinality = firstMaxCardinality;
		this.firstIsNavigable = firstIsNavigable;
		this.firstIsContainer = firstIsContainer;
		this.secondMinCardinality = secondMinCardinality;
		this.secondMaxCardinality = secondMaxCardinality;
		this.secondIsNavigable = secondIsNavigable;
		this.secondIsContainer = secondIsContainer;
	}

	public void setFirstMinCardinality(final Set firstMinCardinality) {
		this.firstMinCardinality = firstMinCardinality;
	}

	public void setFirstMaxCardinality(final Set firstMaxCardinality) {
		this.firstMaxCardinality = firstMaxCardinality;
	}

	public void setFirstIsNavigable(final Set firstIsNavigable) {
		this.firstIsNavigable = firstIsNavigable;
	}

	public void setFirstIsContainer(final Set firstIsContainer) {
		this.firstIsContainer = firstIsContainer;
	}

	public void setSecondMinCardinality(final Set secondMinCardinality) {
		this.secondMinCardinality = secondMinCardinality;
	}

	public void setSecondMaxCardinality(final Set secondMaxCardinality) {
		this.secondMaxCardinality = secondMaxCardinality;
	}

	public void setSecondIsNavigable(final Set secondIsNavigable) {
		this.secondIsNavigable = secondIsNavigable;
	}

	public void setSecondIsContainer(final Set secondIsContainer) {
		this.secondIsContainer = secondIsContainer;
	}

	public Set getEdgeIdentityInstance() {
		return edgeIdentityInstance;
	}

	public Set getFirstIsContainer() {
		return firstIsContainer;
	}

	public Set getFirstIsNavigable() {
		return firstIsNavigable;
	}

	public Set getFirstMaxCardinality() {
		return firstMaxCardinality;
	}

	public Set getFirstMinCardinality() {
		return firstMinCardinality;
	}

	public Set getFirstSemanticIdentity() {
		return firstSemanticIdentity;
	}

	public Set getMetaElement() {
		return metaElement;
	}

	public Set getSecondIsContainer() {
		return secondIsContainer;
	}

	public Set getSecondIsNavigable() {
		return secondIsNavigable;
	}

	public Set getSecondMaxCardinality() {
		return secondMaxCardinality;
	}

	public Set getSecondMinCardinality() {
		return secondMinCardinality;
	}

	public Set getSecondSemanticIdentity() {
		return secondSemanticIdentity;
	}

	public Set getSourceInstance() {
		return sourceInstance;
	}

	public Set getTargetInstance() {
		return targetInstance;
	}

	public void setEdgeIdentityInstance(final Set edgeIdentityInstance) {
		this.edgeIdentityInstance = edgeIdentityInstance;
	}

	public void setFirstSemanticIdentity(final Set firstSemanticIdentity) {
		this.firstSemanticIdentity = firstSemanticIdentity;
	}

	public void setMetaElement(final Set metaElement) {
		this.metaElement = metaElement;
	}

	public void setSecondSemanticIdentity(final Set secondSemanticIdentity) {
		this.secondSemanticIdentity = secondSemanticIdentity;
	}

	public void setSourceInstance(final Set sourceInstance) {
		this.sourceInstance = sourceInstance;
	}

	public void setTargetInstance(final Set targetInstance) {
		this.targetInstance = targetInstance;
	}

	public static List<String> getDisplayedInstances() {
		return DISPLAYED_INSTANCES;
	}

	public static String[] getDisplayedTableInstances() {
		return DISPLAYED_TABLE_INSTANCES;
	}

	public static java.util.Set<String> getInstancesWithAssociatedList(){
		return INSTANCES_WITH_ASSOCIATED_LIST;
	}

	public static List<SetData> getAssociatedListOf(final String pid) {
		final List<SetData> list = new ArrayList<SetData>();
		if (pid.equals(FIRST_MIN_CARDINALITY) || pid.equals(SECOND_MIN_CARDINALITY)) {
			list.add(new SetData(S23MSemanticDomains.minCardinality_0));
			list.add(new SetData(S23MSemanticDomains.minCardinality_1));
			list.add(new SetData(S23MSemanticDomains.minCardinality_2));
			list.add(new SetData(S23MSemanticDomains.minCardinality_n));
			list.add(new SetData(S23MSemanticDomains.minCardinality_NOTAPPLICABLE));
			list.add(new SetData(S23MSemanticDomains.minCardinality_UNKNOWN));
		} else if (pid.equals(FIRST_MAX_CARDINALITY) || pid.equals(SECOND_MAX_CARDINALITY)) {
			list.add(new SetData(S23MSemanticDomains.maxCardinality_0));
			list.add(new SetData(S23MSemanticDomains.maxCardinality_1));
			list.add(new SetData(S23MSemanticDomains.maxCardinality_2));
			list.add(new SetData(S23MSemanticDomains.maxCardinality_n));
			list.add(new SetData(S23MSemanticDomains.maxCardinality_NOTAPPLICABLE));
			list.add(new SetData(S23MSemanticDomains.maxCardinality_UNKNOWN));
		} else if (pid.equals(FIRST_IS_NAVIGABLE) || pid.equals(SECOND_IS_NAVIGABLE)) {
			list.add(new SetData(S23MSemanticDomains.isNavigable_FALSE));
			list.add(new SetData(S23MSemanticDomains.isNavigable_TRUE));
			list.add(new SetData(S23MSemanticDomains.isNavigable_NOTAPPLICABLE));
			list.add(new SetData(S23MSemanticDomains.isNavigable_UNKNOWN));
		} else if (pid.equals(FIRST_IS_CONTAINER) || pid.equals(SECOND_IS_CONTAINER)) {
			list.add(new SetData(S23MSemanticDomains.isContainer_FALSE));
			list.add(new SetData(S23MSemanticDomains.isContainer_TRUE));
			list.add(new SetData(S23MSemanticDomains.isContainer_NOTAPPLICABLE));
			list.add(new SetData(S23MSemanticDomains.isContainer_UNKNOWN));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SetData> getAllVisibleVertices() {
		final List<SetData> setData = new ArrayList<SetData>();
		getAllVisibleSets(Root.models, sourceInstance, setData);
		Collections.sort(setData);
		return setData;
	}

	private void getAllVisibleSets(final Set set, final Set setToMatch, final List<SetData> acc) {
		for (final Set s : set.filterInstances()) {
			if (setToMatch.hasVisibilityOf(s).isEqualTo(S23MSemanticDomains.is_TRUE)) {
				acc.add(new SetData(s));
			}
			if (!s.filterInstances().isEmpty()) {
				getAllVisibleSets(s, setToMatch, acc);
			}
		}
	}

	public List<SetData> getAllValidEdgeFlavoredInstances(final Set targetInstance) {
		final List<SetData> visibleEdges = new ArrayList<SetData>();
		if (targetInstance == null) {
			return visibleEdges;
		}
		final Set allValidEdges = sourceInstance.allowableEdgeCategories(targetInstance);
		final OrderedSet edgeSet = (OrderedSet) allValidEdges;
		if (!edgeSet.isEmpty()) {
		  	final List<Set> edges = edgeSet.asList();
		  	for (final Set e : edges) {
		  		visibleEdges.add(new SetData(e));
		  	}
		}
		return visibleEdges;
	}

}
