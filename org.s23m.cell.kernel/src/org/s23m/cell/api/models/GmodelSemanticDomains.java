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

package org.s23m.cell.api.models;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;
import static org.s23m.cell.core.F_Instantiation.xtensionIdentityFactory;

import org.s23m.cell.G;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.Graph;

public class GmodelSemanticDomains {

	// Instantiation level 2, SemanticDomain : Gmodel

	// STRUCTURE OF SEMANTIC DOMAIN REPOSITORY

	public static final Set infiniteSets =  F_Instantiation.instantiateSemanticDomain(org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(xtensionIdentityFactory.infiniteSets()));
	public static final Set finiteSets =  F_Instantiation.instantiateSemanticDomain(org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(xtensionIdentityFactory.finiteSets()));

	public static final Set gmodel = ((Graph)finiteSets).addAbstract(SemanticDomain.semanticdomain, xtensionIdentityFactory.gmodel() );
	public static final Set root = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, Root.root);
	public static final Set semanticDomains = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, Root.semanticdomains);
	public static final Set models = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, Root.models);

	public static final Set semanticDomain = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticdomain);
	public static final Set sd = semanticDomain;
	public static final Set semanticIdentity = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticIdentity);

	public static final Set semanticIdentitySet = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticIdentitySet);
	public static final Set semanticRole = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticRole);
	public static final Set disjunctSemanticIdentitySet = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.disjunctSemanticIdentitySet);
	public static final Set variantDisjunctSemanticIdentitySet = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.variantDisjunctSemanticIdentitySet);
	public static final Set equivalenceClass = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.semanticRole_to_equivalenceClass.toEdgeEnd());
	public static final Set element = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, SemanticDomain.elements_to_semanticIdentitySet.fromEdgeEnd());

	public static final Set orderedPair = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.orderedPair);
	public static final Set orderedSet = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.orderedSet);
	public static final Set anonymous = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.anonymous);
	public static final Set parameter = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.parameter);
	public static final Set target = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.target);
	public static final Set subGraph = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.subGraph);
	public static final Set categoryOfOrderedPair = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isInformation);
	public static final Set flavor = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.flavor);

	public static final Set iqLogicValue = gmodel.addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.iqLogicValue);
	public static final Set is_NOTAPPLICABLE = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.is_NOTAPPLICABLE);
	public static final Set is_FALSE = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.is_FALSE);
	public static final Set is_UNKNOWN = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.is_UNKNOWN);
	public static final Set is_TRUE = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.is_TRUE);

	public static final Set booleanValue = gmodel.addConcrete(SemanticDomain.semanticIdentitySet, coreSets.booleanValue);

	public static final Set isAbstract = ((Graph)gmodel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.isAbstract);
	public static final Set isAbstract_TRUE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isAbstract_TRUE);
	public static final Set isAbstract_FALSE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isAbstract_FALSE);

	public static final Set minCardinality = ((Graph)gmodel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.minCardinality);
	public static final Set minCardinality_0 = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.minCardinality_0);
	public static final Set minCardinality_1 = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.minCardinality_1);
	public static final Set minCardinality_2 = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.minCardinality_2);
	public static final Set minCardinality_n = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.minCardinality_n);
	public static final Set minCardinality_NOTAPPLICABLE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.minCardinality_NOTAPPLICABLE);
	public static final Set minCardinality_UNKNOWN = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.minCardinality_UNKNOWN);

	public static final Set maxCardinality = ((Graph)gmodel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.maxCardinality);
	public static final Set maxCardinality_0 = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.maxCardinality_0);
	public static final Set maxCardinality_1 = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.maxCardinality_1);
	public static final Set maxCardinality_2 = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.maxCardinality_2);
	public static final Set maxCardinality_n = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.maxCardinality_n);
	public static final Set maxCardinality_NOTAPPLICABLE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.maxCardinality_NOTAPPLICABLE);
	public static final Set maxCardinality_UNKNOWN = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.maxCardinality_UNKNOWN);

	public static final Set isNavigable = ((Graph)gmodel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.isNavigable);
	public static final Set isNavigable_TRUE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isNavigable_TRUE);
	public static final Set isNavigable_FALSE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isNavigable_FALSE);
	public static final Set isNavigable_NOTAPPLICABLE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isNavigable_NOTAPPLICABLE);
	public static final Set isNavigable_UNKNOWN = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isNavigable_UNKNOWN);

	public static final Set isContainer = ((Graph)gmodel).addConcrete(SemanticDomain.semanticIdentitySet, coreSets.isContainer);
	public static final Set isContainer_TRUE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isContainer_TRUE);
	public static final Set isContainer_FALSE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isContainer_FALSE);
	public static final Set isContainer_NOTAPPLICABLE = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isContainer_NOTAPPLICABLE);
	public static final Set isContainer_UNKNOWN = ((Graph)gmodel).addConcrete(SemanticDomain.semanticRole, coreSets.isContainer_UNKNOWN);

	public static final Set completion = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.completion);
	public static final Set successful = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.successful);

	public static final Set name = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.name);
	public static final Set pluralName = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.pluralName);
	public static final Set identifier = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.identifier);
	public static final Set technicalName = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.technicalName);
	public static final Set referencingSemanticRole = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.referencingSemanticRole);

	public static final Set kernelDefect = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.kernelDefect);
	public static final Set kernelDefect_KernelHasReachedAnIllegalState = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.kernelDefect_KernelHasReachedAnIllegalState);

	public static final Set semanticErr = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr);
	public static final Set semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors);
	public static final Set semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles);
	public static final Set semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances);
	public static final Set semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph);
	public static final Set semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph);
	public static final Set semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet);
	public static final Set semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet);
	public static final Set semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction);
	public static final Set semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail);
	public static final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex);
	public static final Set semanticErr_OnlyInstancesHaveIsAbstract = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyInstancesHaveIsAbstract);
	public static final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality);
	public static final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality);
	public static final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer);
	public static final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable);
	public static final Set semanticErr_ValueIsNotAssigned = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_ValueIsNotAssigned);
	public static final Set semanticErr_LinkIsNotApplicable = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_LinkIsNotApplicable);
	public static final Set semanticErr_TargetIsNotWithinVisibility = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_TargetIsNotWithinVisibility);
	public static final Set semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor);
	public static final Set semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor);
	public static final Set semanticErr_GraphGraphCantBeModified = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_GraphGraphCantBeModified);
	public static final Set semanticErr_VariableCantBeRemovedArtifactStillHasInstances = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_VariableCantBeRemovedArtifactStillHasInstances);
	public static final Set semanticErr_GraphsCantBeDecommissioned = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_GraphsCantBeDecommissioned);
	public static final Set semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance);
	public static final Set semanticErr_maxFromCardinalityIsOne = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxFromCardinalityIsOne);
	public static final Set semanticErr_maxFromCardinalityIsTwo = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxFromCardinalityIsTwo);
	public static final Set semanticErr_maxFromCardinalityIsIllegal = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxFromCardinalityIsIllegal);
	public static final Set semanticErr_maxToCardinalityIsOne = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxToCardinalityIsOne);
	public static final Set semanticErr_maxToCardinalityIsTwo = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxToCardinalityIsTwo);
	public static final Set semanticErr_maxToCardinalityIsIllegal = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_maxToCardinalityIsIllegal);
	public static final Set semanticErr_operationIsIllegalOnThisInstance = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_operationIsIllegalOnThisInstance);
	public static final Set semanticErr_operationIsNotYetImplemented = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_operationIsNotYetImplemented);
	public static final Set semanticErr_ThisSetIsNotAvailableInMemory = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.semanticErr_ThisSetIsNotAvailableInMemory);

	/**
	 * core functions
	 */
	public static final Set function = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.function);
	public static final Set command = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.command);
	public static final Set commandFunction = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.commandFunction);
	public static final Set flavorCommandFunction = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.flavorCommandFunction);
	public static final Set query = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.query);
	public static final Set queryFunction = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.queryFunction);
	public static final Set flavorQueryFunction = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.flavorQueryFunction);

	/**
	 * OrderedPairFlavor queries
	 */
	public static final Set identity = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.identity);
	public static final Set isEqualTo = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isEqualTo);

	/**
	 * OrderedSetFlavor queries
	 */
	public static final Set contains = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.contains);
	public static final Set containsAll = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.containsAll);
	public static final Set get = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.get);
	public static final Set indexOf = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.indexOf);
	public static final Set isEmpty = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isEmpty);
	public static final Set lastIndexOf = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.lastIndexOf);
	public static final Set listIterator = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.listIterator);
	public static final Set listIteratorInt = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.listIteratorInt);
	public static final Set size = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.size);
	public static final Set toArray = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.toArray);
	public static final Set toArrayInstance = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.toArrayInstance);

	/**
	 * GraphFlavor commands
	 */
	public static final Set addAbstract = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addAbstract);
	public static final Set addConcrete = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addConcrete);
	public static final Set isALink = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addAbstractSubGraph);
	public static final Set addConcreteSubGraph = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isASemanticIdentity);
	public static final Set addToVariables = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addToVariables);
	public static final Set addToValues = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.addToValues);
	public static final Set decommission = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.decommission);
	public static final Set instantiateAbstract = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.instantiateAbstract);
	public static final Set instantiateConcrete = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.instantiateConcrete);
	public static final Set removeFromVariables = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.removeFromVariables);
	public static final Set removeFromValues = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.removeFromValues);
	public static final Set setPropertyValue = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.setValue);

	/**
	 * GraphFlavor, VertexFlavor, EdgeEndFlavor queries
	 */

	public static final Set artifact = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.artifact);
	public static final Set categorizedSet = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.filter);
	public static final Set containsEdgeFromOrTo = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.containsEdgeFromOrTo);
	public static final Set flavoredSet = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.filterFlavor);
	public static final Set hasVisibilityOf = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.hasVisibilityOf);
	public static final Set instanceSet = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.filterInstances);
	public static final Set isSuperSetOf = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isSuperSetOf);
	public static final Set isLocalSuperSetOf = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isLocalSuperSetOf);
	public static final Set linkSet = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.filterLinks);
	public static final Set localRootSuperSetOf = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.localRootSuperSetOf);
	public static final Set directSuperSetOf = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.directSuperSetOf);
	public static final Set category = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.category);
	public static final Set containerCategory = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.containerCategory);
	public static final Set variables = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.variables);
	public static final Set value = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.value);
	public static final Set values = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.values);
	public static final Set visibleArtifactsForSubGraph = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.visibleArtifactsForSubGraph);

	/**
	 * F_SemanticStateOfInMemoryModel commands
	 */

	/**
	 * LinkFlavor queries
	 */

	public static final Set from = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.from);
	public static final Set isExternal = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.isExternal);
	public static final Set to = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.to);

	/**
	 * EdgeFlavor queries
	 */

	public static final Set edgeEnds = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.edgeEnds);
	public static final Set fromEdgeEnd = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.fromEdgeEnd);
	public static final Set toEdgeEnd = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.toEdgeEnd);


	public static final Set event = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.event);
	public static final Set setMaintenanceCommand = ((Graph)gmodel).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, coreSets.setMaintenanceCommand);

	public static void instantiateFeature() {

		// Instantiation level 2

		// VISIBILITIES WITHIN THE SEMANTICDOMAINS REPOSITORY
		Instantiation.link(coreGraphs.visibility, GmodelSemanticDomains.finiteSets, GmodelSemanticDomains.infiniteSets);

		// MODELS --> SEMANTICDOMAINS
		Instantiation.link(coreGraphs.visibility, Root.models, GmodelSemanticDomains.finiteSets);
		Instantiation.link(coreGraphs.visibility, Root.models, GmodelSemanticDomains.infiniteSets);

		iqLogicValue.addElement(is_NOTAPPLICABLE);
		iqLogicValue.addElement(is_FALSE);
		iqLogicValue.addElement(is_UNKNOWN);
		iqLogicValue.addElement(is_TRUE);

		booleanValue.addElement(is_FALSE);
		booleanValue.addElement(is_TRUE);

		isAbstract.addElement(is_TRUE);
		isAbstract.addElement(is_FALSE);
		Instantiation.linktoEquivalenceClass(isAbstract_TRUE, is_TRUE);
		Instantiation.linktoEquivalenceClass(isAbstract_FALSE, is_FALSE);

		minCardinality.addElement(minCardinality_0);
		minCardinality.addElement(minCardinality_1);
		minCardinality.addElement(minCardinality_2);
		minCardinality.addElement(minCardinality_n);
		minCardinality.addElement(is_NOTAPPLICABLE);
		minCardinality.addElement(is_UNKNOWN);
		Instantiation.linktoEquivalenceClass(minCardinality_NOTAPPLICABLE, is_NOTAPPLICABLE);
		Instantiation.linktoEquivalenceClass(minCardinality_UNKNOWN, is_UNKNOWN);

		maxCardinality.addElement(maxCardinality_0);
		maxCardinality.addElement(maxCardinality_1);
		maxCardinality.addElement(maxCardinality_2);
		maxCardinality.addElement(maxCardinality_n);
		maxCardinality.addElement(is_NOTAPPLICABLE);
		maxCardinality.addElement(is_UNKNOWN);
		Instantiation.linktoEquivalenceClass(maxCardinality_NOTAPPLICABLE, is_NOTAPPLICABLE);
		Instantiation.linktoEquivalenceClass(maxCardinality_UNKNOWN, is_UNKNOWN);


		isNavigable.addElement(is_NOTAPPLICABLE);
		isNavigable.addElement(is_FALSE);
		isNavigable.addElement(is_UNKNOWN);
		isNavigable.addElement(is_TRUE);
		Instantiation.linktoEquivalenceClass(isNavigable_TRUE, is_TRUE);
		Instantiation.linktoEquivalenceClass(isNavigable_FALSE, is_FALSE);
		Instantiation.linktoEquivalenceClass(isNavigable_NOTAPPLICABLE, is_NOTAPPLICABLE);
		Instantiation.linktoEquivalenceClass(isNavigable_UNKNOWN, is_UNKNOWN);

		isContainer.addElement(is_NOTAPPLICABLE);
		isContainer.addElement(is_FALSE);
		isContainer.addElement(is_UNKNOWN);
		isContainer.addElement(is_TRUE);
		Instantiation.linktoEquivalenceClass(isContainer_TRUE, is_TRUE);
		Instantiation.linktoEquivalenceClass(isContainer_FALSE, is_FALSE);
		Instantiation.linktoEquivalenceClass(isContainer_NOTAPPLICABLE, is_NOTAPPLICABLE);
		Instantiation.linktoEquivalenceClass(isContainer_UNKNOWN, is_UNKNOWN);

		// ensure that the inMemorySets contain all elements, including all elememts of the Gmodel kernel
		addGmodelKernelElementsToInMemorySets();

	}

	private static void addGmodelKernelElementsToInMemorySets() {
		Graph.addSetToInMemorySets(infiniteSets );
		Graph.addSetToInMemorySets(finiteSets);

		Graph.addSetToInMemorySets(gmodel) ;
		Graph.addSetToInMemorySets(root);
		Graph.addSetToInMemorySets(semanticDomains );
		Graph.addSetToInMemorySets(models );

		Graph.addSetToInMemorySets(semanticDomain );
		Graph.addSetToInMemorySets(semanticIdentity );

		Graph.addSetToInMemorySets(semanticIdentitySet );
		Graph.addSetToInMemorySets(semanticRole );
		Graph.addSetToInMemorySets(disjunctSemanticIdentitySet) ;
		Graph.addSetToInMemorySets(variantDisjunctSemanticIdentitySet);
		Graph.addSetToInMemorySets(equivalenceClass);
		Graph.addSetToInMemorySets(element);

		Graph.addSetToInMemorySets(G.coreGraphs.graph);
		Graph.addSetToInMemorySets(G.coreGraphs.vertex);
		Graph.addSetToInMemorySets(G.coreGraphs.link);
		Graph.addSetToInMemorySets(G.coreGraphs.edge);
		Graph.addSetToInMemorySets(G.coreGraphs.visibility);
		Graph.addSetToInMemorySets(G.coreGraphs.superSetReference);
		Graph.addSetToInMemorySets(G.coreGraphs.edgeEnd);
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
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances);
		Graph.addSetToInMemorySets(semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph);
		Graph.addSetToInMemorySets(semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph);
		Graph.addSetToInMemorySets(semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet);
		Graph.addSetToInMemorySets(semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex);
		Graph.addSetToInMemorySets(semanticErr_OnlyInstancesHaveIsAbstract);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer);
		Graph.addSetToInMemorySets(semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable);
		Graph.addSetToInMemorySets(semanticErr_ValueIsNotAssigned);
		Graph.addSetToInMemorySets(semanticErr_LinkIsNotApplicable);
		Graph.addSetToInMemorySets(semanticErr_TargetIsNotWithinVisibility);
		Graph.addSetToInMemorySets(semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor);
		Graph.addSetToInMemorySets(semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor);
		Graph.addSetToInMemorySets(semanticErr_GraphGraphCantBeModified);
		Graph.addSetToInMemorySets(semanticErr_VariableCantBeRemovedArtifactStillHasInstances);
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
		Graph.addSetToInMemorySets(flavor);

		/**
		 * core functions
		 */
		Graph.addSetToInMemorySets(function);
		Graph.addSetToInMemorySets(command);
		Graph.addSetToInMemorySets(commandFunction);
		Graph.addSetToInMemorySets(flavorCommandFunction);
		Graph.addSetToInMemorySets(query );
		Graph.addSetToInMemorySets(queryFunction );
		Graph.addSetToInMemorySets(flavorQueryFunction );

		/**
		 * OrderedPairFlavor queries
		 */

		Graph.addSetToInMemorySets(identity );
		Graph.addSetToInMemorySets(isEqualTo );


		/**
		 * OrderedSetFlavor queries
		 */

		Graph.addSetToInMemorySets(contains );
		Graph.addSetToInMemorySets(containsAll );
		Graph.addSetToInMemorySets(get );
		Graph.addSetToInMemorySets(indexOf );
		Graph.addSetToInMemorySets(isEmpty );
		Graph.addSetToInMemorySets(lastIndexOf );
		Graph.addSetToInMemorySets(listIterator );
		Graph.addSetToInMemorySets(listIteratorInt );
		Graph.addSetToInMemorySets(size );
		Graph.addSetToInMemorySets(toArray );
		Graph.addSetToInMemorySets(toArrayInstance );

		/**
		 * GraphFlavor commands
		 */

		Graph.addSetToInMemorySets(addAbstract );
		Graph.addSetToInMemorySets(addConcrete );
		Graph.addSetToInMemorySets(isALink );
		Graph.addSetToInMemorySets(addConcreteSubGraph );
		Graph.addSetToInMemorySets(addToVariables );
		Graph.addSetToInMemorySets(addToValues );
		Graph.addSetToInMemorySets(decommission );
		Graph.addSetToInMemorySets(instantiateAbstract );
		Graph.addSetToInMemorySets(instantiateConcrete );
		Graph.addSetToInMemorySets(removeFromVariables );
		Graph.addSetToInMemorySets(removeFromValues );
		Graph.addSetToInMemorySets(setPropertyValue );

		/**
		 * GraphFlavor, VertexFlavor, EdgeEndFlavor queries
		 */

		Graph.addSetToInMemorySets(artifact );
		Graph.addSetToInMemorySets(categorizedSet );
		Graph.addSetToInMemorySets(containsEdgeFromOrTo );
		Graph.addSetToInMemorySets(flavoredSet);
		Graph.addSetToInMemorySets(hasVisibilityOf );
		Graph.addSetToInMemorySets(instanceSet );
		Graph.addSetToInMemorySets(isSuperSetOf );
		Graph.addSetToInMemorySets(isLocalSuperSetOf );
		Graph.addSetToInMemorySets(linkSet );
		Graph.addSetToInMemorySets(localRootSuperSetOf );
		Graph.addSetToInMemorySets(directSuperSetOf );
		Graph.addSetToInMemorySets(category );
		Graph.addSetToInMemorySets(containerCategory );
		Graph.addSetToInMemorySets(variables );
		Graph.addSetToInMemorySets(value );
		Graph.addSetToInMemorySets(values );
		Graph.addSetToInMemorySets(visibleArtifactsForSubGraph );

		/**
		 * LinkFlavor queries
		 */

		Graph.addSetToInMemorySets(from );
		Graph.addSetToInMemorySets(isExternal );
		Graph.addSetToInMemorySets(to);

		/**
		 * EdgeFlavor queries
		 */

		Graph.addSetToInMemorySets(edgeEnds );
		Graph.addSetToInMemorySets(fromEdgeEnd );
		Graph.addSetToInMemorySets(toEdgeEnd );

		Graph.addSetToInMemorySets(event );
		Graph.addSetToInMemorySets(setMaintenanceCommand );

	}

}
