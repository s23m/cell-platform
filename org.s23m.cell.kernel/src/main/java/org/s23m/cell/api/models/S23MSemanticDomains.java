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

package org.s23m.cell.api.models;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;
import static org.s23m.cell.core.F_Instantiation.xtensionIdentityFactory;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.Graph;

public class S23MSemanticDomains {

	// Instantiation level 2, SemanticDomain : CellKernel

	// STRUCTURE OF SEMANTIC DOMAIN REPOSITORY

	public static final Set sandboxSemanticDomains =  F_Instantiation.instantiateSemanticDomain(org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(xtensionIdentityFactory.sandboxSemanticDomains()));
	public static final Set agentSemanticDomains =  F_Instantiation.instantiateSemanticDomain(org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(xtensionIdentityFactory.agentSemanticDomains()));

	public static final Set cellKernel= ((Graph)agentSemanticDomains).addAbstract(SemanticDomain.semanticdomain, xtensionIdentityFactory.cellKernel());
	public static final Set root = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, Root.root);
	public static final Set semanticDomains = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, Root.semanticdomains);
	public static final Set models = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, Root.models);

	public static final Set semanticDomain = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticdomain);
	public static final Set sd = semanticDomain;
	public static final Set semanticIdentity = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticIdentity);

	public static final Set semanticIdentitySet = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticIdentitySet);
	public static final Set semanticRole = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticRole);
	public static final Set disjunctSemanticIdentitySet = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.disjunctSemanticIdentitySet);
	public static final Set variantDisjunctSemanticIdentitySet = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.variantDisjunctSemanticIdentitySet);
	public static final Set equivalenceClass = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticRole_to_equivalenceClass.toEdgeEnd());
	public static final Set element = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.elements_to_semanticIdentitySet.fromEdgeEnd());

	public static final Set orderedPair = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.orderedPair);
	public static final Set orderedSet = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.orderedSet);
	public static final Set anonymous = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.anonymous);
	public static final Set parameter = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.parameter);
	public static final Set target = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.target);
	public static final Set subGraph = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.subGraph);
	public static final Set categoryOfOrderedPair = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isInformation);
	public static final Set properClass = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.properClass);

	public static final Set iqLogicValue = cellKernel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.iqLogicValue);
	public static final Set is_NOTAPPLICABLE = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.is_NOTAPPLICABLE);
	public static final Set is_FALSE = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.is_FALSE);
	public static final Set is_UNKNOWN = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.is_UNKNOWN);
	public static final Set is_TRUE = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.is_TRUE);

	public static final Set booleanValue = cellKernel.addConcrete(SemanticDomain.semanticIdentitySet, coreSets.booleanValue);

	public static final Set isAbstract = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.isAbstract);
	public static final Set isAbstract_TRUE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isAbstract_TRUE);
	public static final Set isAbstract_FALSE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isAbstract_FALSE);

	public static final Set minCardinality = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.minCardinality);
	public static final Set minCardinality_0 = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.minCardinality_0);
	public static final Set minCardinality_1 = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.minCardinality_1);
	public static final Set minCardinality_2 = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.minCardinality_2);
	public static final Set minCardinality_n = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.minCardinality_n);
	public static final Set minCardinality_NOTAPPLICABLE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.minCardinality_NOTAPPLICABLE);
	public static final Set minCardinality_UNKNOWN = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.minCardinality_UNKNOWN);

	public static final Set maxCardinality = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.maxCardinality);
	public static final Set maxCardinality_0 = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.maxCardinality_0);
	public static final Set maxCardinality_1 = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.maxCardinality_1);
	public static final Set maxCardinality_2 = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.maxCardinality_2);
	public static final Set maxCardinality_n = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.maxCardinality_n);
	public static final Set maxCardinality_NOTAPPLICABLE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.maxCardinality_NOTAPPLICABLE);
	public static final Set maxCardinality_UNKNOWN = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.maxCardinality_UNKNOWN);

	public static final Set isNavigable = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.isNavigable);
	public static final Set isNavigable_TRUE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isNavigable_TRUE);
	public static final Set isNavigable_FALSE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isNavigable_FALSE);
	public static final Set isNavigable_NOTAPPLICABLE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isNavigable_NOTAPPLICABLE);
	public static final Set isNavigable_UNKNOWN = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isNavigable_UNKNOWN);

	public static final Set isContainer = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.isContainer);
	public static final Set isContainer_TRUE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isContainer_TRUE);
	public static final Set isContainer_FALSE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isContainer_FALSE);
	public static final Set isContainer_NOTAPPLICABLE = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isContainer_NOTAPPLICABLE);
	public static final Set isContainer_UNKNOWN = ((Graph)cellKernel).addConcrete(SemanticDomain.semanticRole, coreSets.isContainer_UNKNOWN);

	public static final Set completion = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.completion);
	public static final Set successful = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.successful);

	public static final Set name = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.name);
	public static final Set pluralName = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.pluralName);
	public static final Set identifier = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.identifier);
	public static final Set technicalName = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.technicalName);
	public static final Set referencingSemanticRole = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.referencingSemanticRole);

	public static final Set kernelDefect = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.kernelDefect);
	public static final Set kernelDefect_KernelHasReachedAnIllegalState = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.kernelDefect_KernelHasReachedAnIllegalState);

	public static final Set semanticErr = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr);
	public static final Set semanticErr_OnlyEdgeMembersHaveEdgeEnds = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeInstancesHaveEdgeEnds);
	public static final Set semanticErr_OnlyEdgeMembersHaveConnectedRoles = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeInstancesHaveConnectedRoles);
	public static final Set semanticErr_OnlyEdgeMembersHaveConnectedInstances = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeInstancesHaveConnectedInstances);
	public static final Set semanticErr_OnlyVisibilityMembersHaveFromSubGraph = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyVisibilityInstancesHaveFromSubGraph);
	public static final Set semanticErr_OnlyVisibilityMembersHaveToSubGraph = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyVisibilityInstancesHaveToSubGraph);
	public static final Set semanticErr_OnlySuperSetReferenceMembersHaveSuperSet = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlySuperSetReferenceInstancesHaveSuperSet);
	public static final Set semanticErr_OnlySuperSetReferenceMembersHaveSubSet = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlySuperSetReferenceInstancesHaveSubSet);
	public static final Set semanticErr_OnlyEdgeEndMembersHaveEdgeEndVertex = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndInstancesHaveEdgeEndVertex);
	public static final Set semanticErr_OnlyInstancesHaveIsAbstract = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyInstancesHaveIsAbstract);
	public static final Set semanticErr_OnlyEdgeEndMambersHaveMinCardinality = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndInstancesHaveMinCardinality);
	public static final Set semanticErr_OnlyEdgeEndMembersHaveMaxCardinality = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndInstancesHaveMaxCardinality);
	public static final Set semanticErr_OnlyEdgeEndMambersHaveIsContainer = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndInstancesHaveIsContainer);
	public static final Set semanticErr_OnlyEdgeEndMembersHaveIsNavigable = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndInstancesHaveIsNavigable);
	public static final Set semanticErr_ValueIsNotAssigned = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_ValueIsNotAssigned);
	public static final Set semanticErr_ArrowIsNotApplicable = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_ArrowIsNotApplicable);
	public static final Set semanticErr_TargetIsNotWithinVisibility = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_TargetIsNotWithinVisibility);
	public static final Set semanticErr_AddConcreteIsOnlyValidForConcreteVertex = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_AddConcreteIsOnlyValidForConcreteVertex);
	public static final Set semanticErr_AddAbstractIsOnlyValidForAbstractVertex = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_AddAbstractIsOnlyValidForAbstractVertex);
	public static final Set semanticErr_GraphGraphCantBeModified = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_GraphGraphCantBeModified);
	public static final Set semanticErr_VariableCantBeRemovedCategoryStillHasInstances = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_VariableCantBeRemovedCategoryStillHasInstances);
	public static final Set semanticErr_GraphsCantBeDecommissioned = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_GraphsCantBeDecommissioned);
	public static final Set semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance);
	public static final Set semanticErr_maxFromCardinalityIsOne = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxFromCardinalityIsOne);
	public static final Set semanticErr_maxFromCardinalityIsTwo = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxFromCardinalityIsTwo);
	public static final Set semanticErr_maxFromCardinalityIsIllegal = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxFromCardinalityIsIllegal);
	public static final Set semanticErr_maxToCardinalityIsOne = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxToCardinalityIsOne);
	public static final Set semanticErr_maxToCardinalityIsTwo = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxToCardinalityIsTwo);
	public static final Set semanticErr_maxToCardinalityIsIllegal = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxToCardinalityIsIllegal);
	public static final Set semanticErr_operationIsIllegalOnThisInstance = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_operationIsIllegalOnThisInstance);
	public static final Set semanticErr_operationIsNotYetImplemented = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_operationIsNotYetImplemented);
	public static final Set semanticErr_ThisSetIsNotAvailableInMemory = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_ThisSetIsNotAvailableInMemory);

	/**
	 * core functions
	 */
	public static final Set function = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.function);
	public static final Set command = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.command);
	public static final Set commandFunction = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.commandFunction);
	public static final Set properClassCommandFunction = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.properClassCommandFunction);
	public static final Set query = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.query);
	public static final Set queryFunction = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.queryFunction);
	public static final Set properClassQueryFunction = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.properClassQueryFunction);

	/**
	 * OrderedPair queries
	 */
	public static final Set identity = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.identity);
	public static final Set isEqualTo = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isEqualTo);

	/**
	 * OrderedSet queries
	 */
	public static final Set contains = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.contains);
	public static final Set containsAll = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.containsAll);
	public static final Set get = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.get);
	public static final Set indexOf = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.indexOf);
	public static final Set isEmpty = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isEmpty);
	public static final Set lastIndexOf = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.lastIndexOf);
	public static final Set listIterator = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.listIterator);
	public static final Set listIteratorInt = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.listIteratorInt);
	public static final Set size = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.size);
	public static final Set toArray = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.toArray);
	public static final Set toArrayInstance = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.toArrayInstance);

	/**
	 * Graph commands
	 */
	public static final Set addAbstract = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addAbstract);
	public static final Set addConcrete = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addConcrete);
	public static final Set isAnArrow = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addAbstractSubGraph);
	public static final Set addConcreteSubGraph = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isASemanticIdentity);
	public static final Set addToVariables = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addToVariables);
	public static final Set addToValues = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addToValues);
	public static final Set decommission = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.decommission);
	public static final Set instantiateAbstract = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.instantiateAbstract);
	public static final Set instantiateConcrete = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.instantiateConcrete);
	public static final Set removeFromVariables = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.removeFromVariables);
	public static final Set removeFromValues = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.removeFromValues);
	public static final Set setPropertyValue = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.setValue);

	/**
	 * Graph, Vertex, EdgeEnd queries
	 */

	public static final Set instance = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.container);
	public static final Set categorizedSet = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.filter);
	public static final Set containsEdgeFromOrTo = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.containsEdgeFromOrTo);
	public static final Set filterMembersOfProperClass = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.filterProperClass);
	public static final Set hasVisibilityOf = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.hasVisibilityOf);
	public static final Set instanceSet = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.filterInstances);
	public static final Set isSuperSetOf = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isSuperSetOf);
	public static final Set isLocalSuperSetOf = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isLocalSuperSetOf);
	public static final Set arrowSet = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.filterArrows);
	public static final Set localRootSuperSetOf = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.localRootSuperSetOf);
	public static final Set directSuperSetOf = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.directSuperSetOf);
	public static final Set category = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.category);
	public static final Set containerCategory = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.containerCategory);
	public static final Set variables = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.variables);
	public static final Set value = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.value);
	public static final Set values = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.values);
	public static final Set visibleInstancesForSubGraph = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.visibleInstancesForSubGraph);

	/**
	 * F_SemanticStateOfInMemoryModel commands
	 */

	/**
	 * Arrow queries
	 */

	public static final Set from = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.from);
	public static final Set isExternal = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isExternal);
	public static final Set to = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.to);

	/**
	 * Edge queries
	 */

	public static final Set edgeEnds = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.edgeEnds);
	public static final Set fromEdgeEnd = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.fromEdgeEnd);
	public static final Set toEdgeEnd = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.toEdgeEnd);


	public static final Set event = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.event);
	public static final Set setMaintenanceCommand = ((Graph)cellKernel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.setMaintenanceCommand);

	public static void instantiateFeature() {

		// Instantiation level 2

		// VISIBILITIES WITHIN THE SEMANTICDOMAINS REPOSITORY
		//Instantiation.arrow(coreGraphs.visibility, S23MSemanticDomains.agentSemanticDomains, S23MSemanticDomains.sandboxSemanticDomains);
		Instantiation.arrow(coreGraphs.visibility, S23MSemanticDomains.sandboxSemanticDomains, S23MSemanticDomains.agentSemanticDomains);

		// MODELS --> SEMANTICDOMAINS
		Instantiation.arrow(coreGraphs.visibility, Root.models, S23MSemanticDomains.agentSemanticDomains);
		Instantiation.arrow(coreGraphs.visibility, Root.models, S23MSemanticDomains.sandboxSemanticDomains);

		iqLogicValue.addElement(is_NOTAPPLICABLE);
		iqLogicValue.addElement(is_FALSE);
		iqLogicValue.addElement(is_UNKNOWN);
		iqLogicValue.addElement(is_TRUE);

		booleanValue.addElement(is_FALSE);
		booleanValue.addElement(is_TRUE);

		isAbstract.addElement(is_TRUE);
		isAbstract.addElement(is_FALSE);
		Instantiation.arrowToEquivalenceClass(isAbstract_TRUE, is_TRUE);
		Instantiation.arrowToEquivalenceClass(isAbstract_FALSE, is_FALSE);

		minCardinality.addElement(minCardinality_0);
		minCardinality.addElement(minCardinality_1);
		minCardinality.addElement(minCardinality_2);
		minCardinality.addElement(minCardinality_n);
		minCardinality.addElement(is_NOTAPPLICABLE);
		minCardinality.addElement(is_UNKNOWN);
		Instantiation.arrowToEquivalenceClass(minCardinality_NOTAPPLICABLE, is_NOTAPPLICABLE);
		Instantiation.arrowToEquivalenceClass(minCardinality_UNKNOWN, is_UNKNOWN);

		maxCardinality.addElement(maxCardinality_0);
		maxCardinality.addElement(maxCardinality_1);
		maxCardinality.addElement(maxCardinality_2);
		maxCardinality.addElement(maxCardinality_n);
		maxCardinality.addElement(is_NOTAPPLICABLE);
		maxCardinality.addElement(is_UNKNOWN);
		Instantiation.arrowToEquivalenceClass(maxCardinality_NOTAPPLICABLE, is_NOTAPPLICABLE);
		Instantiation.arrowToEquivalenceClass(maxCardinality_UNKNOWN, is_UNKNOWN);


		isNavigable.addElement(is_NOTAPPLICABLE);
		isNavigable.addElement(is_FALSE);
		isNavigable.addElement(is_UNKNOWN);
		isNavigable.addElement(is_TRUE);
		Instantiation.arrowToEquivalenceClass(isNavigable_TRUE, is_TRUE);
		Instantiation.arrowToEquivalenceClass(isNavigable_FALSE, is_FALSE);
		Instantiation.arrowToEquivalenceClass(isNavigable_NOTAPPLICABLE, is_NOTAPPLICABLE);
		Instantiation.arrowToEquivalenceClass(isNavigable_UNKNOWN, is_UNKNOWN);

		isContainer.addElement(is_NOTAPPLICABLE);
		isContainer.addElement(is_FALSE);
		isContainer.addElement(is_UNKNOWN);
		isContainer.addElement(is_TRUE);
		Instantiation.arrowToEquivalenceClass(isContainer_TRUE, is_TRUE);
		Instantiation.arrowToEquivalenceClass(isContainer_FALSE, is_FALSE);
		Instantiation.arrowToEquivalenceClass(isContainer_NOTAPPLICABLE, is_NOTAPPLICABLE);
		Instantiation.arrowToEquivalenceClass(isContainer_UNKNOWN, is_UNKNOWN);

		// ensure that the inMemorySets contain all elements, including all elememts of the S23M kernel
		addS23MKernelElementsToInMemorySets();

	}

	private static void addS23MKernelElementsToInMemorySets() {
		Graph.addSetToInMemorySets(sandboxSemanticDomains);
		Graph.addSetToInMemorySets(agentSemanticDomains);

		Graph.addSetToInMemorySets(cellKernel) ;
		Graph.addSetToInMemorySets(root);
		Graph.addSetToInMemorySets(semanticDomains);
		Graph.addSetToInMemorySets(models);

		Graph.addSetToInMemorySets(semanticDomain);
		Graph.addSetToInMemorySets(semanticIdentity);

		Graph.addSetToInMemorySets(semanticIdentitySet);
		Graph.addSetToInMemorySets(semanticRole);
		Graph.addSetToInMemorySets(disjunctSemanticIdentitySet) ;
		Graph.addSetToInMemorySets(variantDisjunctSemanticIdentitySet);
		Graph.addSetToInMemorySets(equivalenceClass);
		Graph.addSetToInMemorySets(element);

		Graph.addSetToInMemorySets(S23MKernel.coreGraphs.graph);
		Graph.addSetToInMemorySets(S23MKernel.coreGraphs.vertex);
		Graph.addSetToInMemorySets(S23MKernel.coreGraphs.arrow);
		Graph.addSetToInMemorySets(S23MKernel.coreGraphs.edge);
		Graph.addSetToInMemorySets(S23MKernel.coreGraphs.visibility);
		Graph.addSetToInMemorySets(S23MKernel.coreGraphs.superSetReference);
		Graph.addSetToInMemorySets(S23MKernel.coreGraphs.edgeEnd);
		/* core sets */
		Graph.addSetToInMemorySets(orderedPair);
		Graph.addSetToInMemorySets(orderedSet);
		Graph.addSetToInMemorySets(anonymous);

		Graph.addSetToInMemorySets(isAbstract);
		Graph.addSetToInMemorySets(isAbstract_TRUE);
		Graph.addSetToInMemorySets(isAbstract_FALSE);

		Graph.addSetToInMemorySets(minCardinality);
		Graph.addSetToInMemorySets(minCardinality_0);
		Graph.addSetToInMemorySets(minCardinality_1);
		Graph.addSetToInMemorySets(minCardinality_2);
		Graph.addSetToInMemorySets(minCardinality_n);
		Graph.addSetToInMemorySets(minCardinality_NOTAPPLICABLE);
		Graph.addSetToInMemorySets(minCardinality_UNKNOWN);

		Graph.addSetToInMemorySets(maxCardinality);
		Graph.addSetToInMemorySets(maxCardinality_0);
		Graph.addSetToInMemorySets(maxCardinality_1);
		Graph.addSetToInMemorySets(maxCardinality_2);
		Graph.addSetToInMemorySets(maxCardinality_n);
		Graph.addSetToInMemorySets(maxCardinality_NOTAPPLICABLE);
		Graph.addSetToInMemorySets(maxCardinality_UNKNOWN);

		Graph.addSetToInMemorySets(isNavigable);
		Graph.addSetToInMemorySets(isNavigable_TRUE);
		Graph.addSetToInMemorySets(isNavigable_FALSE);
		Graph.addSetToInMemorySets(isNavigable_NOTAPPLICABLE);
		Graph.addSetToInMemorySets(isNavigable_UNKNOWN);

		Graph.addSetToInMemorySets(isContainer);
		Graph.addSetToInMemorySets(isContainer_TRUE);
		Graph.addSetToInMemorySets(isContainer_FALSE);
		Graph.addSetToInMemorySets(isContainer_NOTAPPLICABLE);
		Graph.addSetToInMemorySets(isContainer_UNKNOWN);

		Graph.addSetToInMemorySets(iqLogicValue);
		Graph.addSetToInMemorySets(is_TRUE);
		Graph.addSetToInMemorySets(is_FALSE);
		Graph.addSetToInMemorySets(is_NOTAPPLICABLE);
		Graph.addSetToInMemorySets(is_UNKNOWN);

		Graph.addSetToInMemorySets(completion);
		Graph.addSetToInMemorySets(successful);
		Graph.addSetToInMemorySets(kernelDefect);
		Graph.addSetToInMemorySets(kernelDefect_KernelHasReachedAnIllegalState);

		Graph.addSetToInMemorySets(semanticErr);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeMembersHaveEdgeEnds);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeMembersHaveConnectedRoles);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeMembersHaveConnectedInstances);
		Graph.addSetToInMemorySets(semanticErr_OnlyVisibilityMembersHaveFromSubGraph);
		Graph.addSetToInMemorySets(semanticErr_OnlyVisibilityMembersHaveToSubGraph);
		Graph.addSetToInMemorySets(semanticErr_OnlySuperSetReferenceMembersHaveSuperSet);
		Graph.addSetToInMemorySets(semanticErr_OnlySuperSetReferenceMembersHaveSubSet);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndMembersHaveEdgeEndVertex);
		Graph.addSetToInMemorySets(semanticErr_OnlyInstancesHaveIsAbstract);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndMambersHaveMinCardinality);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndMembersHaveMaxCardinality);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndMambersHaveIsContainer);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndMembersHaveIsNavigable);
		Graph.addSetToInMemorySets(semanticErr_ValueIsNotAssigned);
		Graph.addSetToInMemorySets(semanticErr_ArrowIsNotApplicable);
		Graph.addSetToInMemorySets(semanticErr_TargetIsNotWithinVisibility);
		Graph.addSetToInMemorySets(semanticErr_AddConcreteIsOnlyValidForConcreteVertex);
		Graph.addSetToInMemorySets(semanticErr_AddAbstractIsOnlyValidForAbstractVertex);
		Graph.addSetToInMemorySets(semanticErr_GraphGraphCantBeModified);
		Graph.addSetToInMemorySets(semanticErr_VariableCantBeRemovedCategoryStillHasInstances);
		Graph.addSetToInMemorySets(semanticErr_GraphsCantBeDecommissioned);
		Graph.addSetToInMemorySets(semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance);
		Graph.addSetToInMemorySets(semanticErr_maxFromCardinalityIsOne);
		Graph.addSetToInMemorySets(semanticErr_maxFromCardinalityIsTwo);
		Graph.addSetToInMemorySets(semanticErr_maxFromCardinalityIsIllegal);
		Graph.addSetToInMemorySets(semanticErr_maxToCardinalityIsOne);
		Graph.addSetToInMemorySets(semanticErr_maxToCardinalityIsTwo);
		Graph.addSetToInMemorySets(semanticErr_maxToCardinalityIsIllegal);
		Graph.addSetToInMemorySets(semanticErr_operationIsIllegalOnThisInstance);
		Graph.addSetToInMemorySets(semanticErr_operationIsNotYetImplemented);

		Graph.addSetToInMemorySets(parameter);
		Graph.addSetToInMemorySets(target);
		Graph.addSetToInMemorySets(subGraph);
		Graph.addSetToInMemorySets(categoryOfOrderedPair);
		Graph.addSetToInMemorySets(properClass);

		/**
		 * core functions
		 */
		Graph.addSetToInMemorySets(function);
		Graph.addSetToInMemorySets(command);
		Graph.addSetToInMemorySets(commandFunction);
		Graph.addSetToInMemorySets(properClassCommandFunction);
		Graph.addSetToInMemorySets(query);
		Graph.addSetToInMemorySets(queryFunction);
		Graph.addSetToInMemorySets(properClassQueryFunction);

		/**
		 * OrderedPair queries
		 */

		Graph.addSetToInMemorySets(identity);
		Graph.addSetToInMemorySets(isEqualTo);


		/**
		 * OrderedSet queries
		 */

		Graph.addSetToInMemorySets(contains);
		Graph.addSetToInMemorySets(containsAll);
		Graph.addSetToInMemorySets(get);
		Graph.addSetToInMemorySets(indexOf);
		Graph.addSetToInMemorySets(isEmpty);
		Graph.addSetToInMemorySets(lastIndexOf);
		Graph.addSetToInMemorySets(listIterator);
		Graph.addSetToInMemorySets(listIteratorInt);
		Graph.addSetToInMemorySets(size);
		Graph.addSetToInMemorySets(toArray);
		Graph.addSetToInMemorySets(toArrayInstance);

		/**
		 * Graph commands
		 */

		Graph.addSetToInMemorySets(addAbstract);
		Graph.addSetToInMemorySets(addConcrete);
		Graph.addSetToInMemorySets(isAnArrow);
		Graph.addSetToInMemorySets(addConcreteSubGraph);
		Graph.addSetToInMemorySets(addToVariables);
		Graph.addSetToInMemorySets(addToValues);
		Graph.addSetToInMemorySets(decommission);
		Graph.addSetToInMemorySets(instantiateAbstract);
		Graph.addSetToInMemorySets(instantiateConcrete);
		Graph.addSetToInMemorySets(removeFromVariables);
		Graph.addSetToInMemorySets(removeFromValues);
		Graph.addSetToInMemorySets(setPropertyValue);

		/**
		 * Graph, Vertex, EdgeEnd queries
		 */

		Graph.addSetToInMemorySets(instance);
		Graph.addSetToInMemorySets(categorizedSet);
		Graph.addSetToInMemorySets(containsEdgeFromOrTo);
		Graph.addSetToInMemorySets(filterMembersOfProperClass);
		Graph.addSetToInMemorySets(hasVisibilityOf);
		Graph.addSetToInMemorySets(instanceSet);
		Graph.addSetToInMemorySets(isSuperSetOf);
		Graph.addSetToInMemorySets(isLocalSuperSetOf);
		Graph.addSetToInMemorySets(arrowSet);
		Graph.addSetToInMemorySets(localRootSuperSetOf);
		Graph.addSetToInMemorySets(directSuperSetOf);
		Graph.addSetToInMemorySets(category);
		Graph.addSetToInMemorySets(containerCategory);
		Graph.addSetToInMemorySets(variables);
		Graph.addSetToInMemorySets(value);
		Graph.addSetToInMemorySets(values);
		Graph.addSetToInMemorySets(visibleInstancesForSubGraph);

		/**
		 * Arrow queries
		 */

		Graph.addSetToInMemorySets(from);
		Graph.addSetToInMemorySets(isExternal);
		Graph.addSetToInMemorySets(to);

		/**
		 * Edge queries
		 */

		Graph.addSetToInMemorySets(edgeEnds);
		Graph.addSetToInMemorySets(fromEdgeEnd);
		Graph.addSetToInMemorySets(toEdgeEnd);

		Graph.addSetToInMemorySets(event);
		Graph.addSetToInMemorySets(setMaintenanceCommand);

	}

}
