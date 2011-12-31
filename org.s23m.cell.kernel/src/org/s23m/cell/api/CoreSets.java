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

package org.s23m.cell.api;

import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.core.F_InstantiationImpl;
import org.s23m.cell.core.F_Query;
import org.s23m.cell.core.KernelIdentities;
import org.s23m.cell.core.OrderedPair;

//functionality is scheduled to be limited to the Gmodel core
//TODO remove all usage of this class outside the core package

public final class CoreSets {

	public final Set orderedPair;
	public final Set anonymous;

	public final Set isAbstract;
	public final Set isAbstract_TRUE;
	public final Set isAbstract_FALSE;

	public final Set minCardinality;
	public final Set minCardinality_0;
	public final Set minCardinality_1;
	public final Set minCardinality_2;
	public final Set minCardinality_n;
	public final Set minCardinality_NOTAPPLICABLE;
	public final Set minCardinality_UNKNOWN;

	public final Set maxCardinality;
	public final Set maxCardinality_0;
	public final Set maxCardinality_1;
	public final Set maxCardinality_2;
	public final Set maxCardinality_n;
	public final Set maxCardinality_NOTAPPLICABLE;
	public final Set maxCardinality_UNKNOWN;

	public final Set isNavigable;
	public final Set isNavigable_TRUE;
	public final Set isNavigable_FALSE;
	public final Set isNavigable_NOTAPPLICABLE;
	public final Set isNavigable_UNKNOWN;

	public final Set isContainer;
	public final Set isContainer_TRUE;
	public final Set isContainer_FALSE;
	public final Set isContainer_NOTAPPLICABLE;
	public final Set isContainer_UNKNOWN;

	public final Set iqLogicValue;
	public final Set is_TRUE;
	public final Set is_FALSE;
	public final Set is_NOTAPPLICABLE;
	public final Set is_UNKNOWN;

	public final Set booleanValue;

	public final Set completion;
	public final Set successful;
	public final Set maxSearchSpaceDepth;

	public final Set kernelDefect;
	public final Set kernelDefect_KernelHasReachedAnIllegalState;

	public final Set semanticErr;
	public final Set semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors;
	public final Set semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles;
	public final Set semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances;
	public final Set semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph;
	public final Set semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph;
	public final Set semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet;
	public final Set semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet;
	public final Set semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction;
	public final Set semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail;
	public final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex;
	public final Set semanticErr_OnlyInstancesHaveIsAbstract;
	public final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality;
	public final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality;
	public final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer;
	public final Set semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable;
	public final Set semanticErr_ValueIsNotAssigned;
	public final Set semanticErr_LinkIsNotApplicable;
	public final Set semanticErr_TargetIsNotWithinVisibility;
	public final Set semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor;
	public final Set semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor;
	public final Set semanticErr_GraphGraphCantBeModified;
	public final Set semanticErr_VariableCantBeRemovedArtifactStillHasInstances;
	public final Set semanticErr_GraphsCantBeDecommissioned;
	public final Set semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance;
	public final Set semanticErr_maxFromCardinalityIsOne;
	public final Set semanticErr_maxFromCardinalityIsTwo;
	public final Set semanticErr_maxFromCardinalityIsIllegal;
	public final Set semanticErr_maxToCardinalityIsOne;
	public final Set semanticErr_maxToCardinalityIsTwo;
	public final Set semanticErr_maxToCardinalityIsIllegal;
	public final Set semanticErr_operationIsIllegalOnThisInstance;
	public final Set semanticErr_operationIsNotYetImplemented;
	public final Set semanticErr_OnlyTransportContainerCanHaveContentElements;
	public final Set  semanticErr_ThisSetIsNotAvailableInMemory;

	public final Set parameter;
	public final Set target;
	public final Set subGraph;
	public final Set isInformation;
	public final Set flavor;

	public final Set orderedSet;

	/**
	 * core functions
	 */
	public final Set function;
	public final Set command;
	public final Set commandFunction;
	public final Set flavorCommandFunction;
	public final Set query ;
	public final Set queryFunction ;
	public final Set flavorQueryFunction ;

	/**
	 * OrderedPairFlavor queries
	 */

	//public final Set flavor;
	public final Set identity ;
	public final Set isEqualTo ;
	public final Set semanticIdentity;

	//public final Set isInformation ;
	/**
	 * OrderedSetFlavor commands
	 */

	public final Set union;
	public final Set intersection;
	public final Set complement;

	/**
	 * OrderedSetFlavor queries
	 */

	public final Set contains ;
	public final Set containsAll ;
	public final Set get ;
	public final Set indexOf ;
	public final Set isEmpty ;
	public final Set lastIndexOf ;
	public final Set listIterator ;
	public final Set listIteratorInt ;
	public final Set size ;
	public final Set toArray ;
	public final Set toArrayInstance ;
	public final Set indexOfIdentifier;

	/**
	 * GraphFlavor commands
	 */

	public final Set addAbstract ;
	public final Set addConcrete ;
	public final Set addAbstractSubGraph ;
	public final Set isASemanticIdentity ;
	public final Set addToVariables ;
	public final Set addToValues ;
	public final Set decommission ;
	public final Set instantiateAbstract ;
	public final Set instantiateConcrete ;
	public final Set removeFromVariables ;
	public final Set removeFromValues ;
	public final Set setValue ;

	/**
	 * SemanticIdntity commands
	 */

	public final Set addElement ;
	public final Set removeElement ;

	public final Set setMaintenanceCommand ;

	/**
	 * SemanticIdntity queries
	 */
	public final Set isElementOf;
	/**
	 * GraphFlavor, VertexFlavor, EdgeEndFlavor queries
	 */

	public final Set artifact ;
	public final Set filter ;
	public final Set containsEdgeFromOrTo ;
	public final Set filterFlavor;
	public final Set hasVisibilityOf ;
	public final Set filterInstances ;
	public final Set isSuperSetOf ;
	public final Set isLocalSuperSetOf ;
	public final Set filterLinks ;
	public final Set localRootSuperSetOf ;
	public final Set directSuperSetOf ;
	public final Set category ;
	public final Set containerCategory ;
	public final Set variables ;
	public final Set value ;
	public final Set values ;
	public final Set visibleArtifactsForSubGraph ;
	public final Set allowableEdgeCategories;
	public final Set filterPolymorphic;
	public final Set queries ;
	public final Set commands ;
	public final Set executableQueries ;
	public final Set executableCommands ;


	/**
	 * LinkFlavor queries
	 */

	public final Set from ;
	public final Set isExternal ;
	public final Set to;

	/**
	 * EdgeFlavor queries
	 */

	public final Set edgeEnds;
	public final Set fromEdgeEnd ;
	public final Set toEdgeEnd ;


	public final Set name;
	public final Set pluralName;
	public final Set technicalName;
	public final Set identifier;

	public final Set referencingSemanticRole;

	/**
	 * Events
	 */

	public final Set event;
	public final Set elementAdded;
	public final Set elementRemoved;

	public CoreSets(final KernelIdentities fundamentalSIDs) {
		orderedPair = OrderedPair.orderedPair;

		anonymous = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.anonymous(), orderedPair);

		isAbstract = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isAbstract(), orderedPair);
		isAbstract_TRUE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isAbstract_TRUE(), isAbstract);
		isAbstract_FALSE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isAbstract_FALSE(), isAbstract);

		minCardinality = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.minCardinality(), orderedPair);
		minCardinality_0 = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.minCardinality_0(), minCardinality);
		minCardinality_1 = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.minCardinality_1(), minCardinality);
		minCardinality_2 = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.minCardinality_2(), minCardinality);
		minCardinality_n = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.minCardinality_n(), minCardinality);
		minCardinality_NOTAPPLICABLE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.minCardinality_NOTAPPLICABLE(), minCardinality);
		minCardinality_UNKNOWN = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.minCardinality_UNKNOWN(), minCardinality);

		maxCardinality = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.maxCardinality(), orderedPair);
		maxCardinality_0 = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.maxCardinality_0(), maxCardinality);
		maxCardinality_1 = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.maxCardinality_1(), maxCardinality);
		maxCardinality_2 = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.maxCardinality_2(), maxCardinality);
		maxCardinality_n = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.maxCardinality_n(), maxCardinality);
		maxCardinality_NOTAPPLICABLE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.maxCardinality_NOTAPPLICABLE(), maxCardinality);
		maxCardinality_UNKNOWN = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.maxCardinality_UNKNOWN(), maxCardinality);

		isNavigable = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isNavigable(), orderedPair);
		isNavigable_TRUE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isNavigable_TRUE(), isNavigable);
		isNavigable_FALSE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isNavigable_FALSE(), isNavigable);
		isNavigable_NOTAPPLICABLE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isNavigable_NOTAPPLICABLE(), isNavigable);
		isNavigable_UNKNOWN = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isNavigable_UNKNOWN(), isNavigable);

		isContainer = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isContainer(), orderedPair);
		isContainer_TRUE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isContainer_TRUE(), isContainer);
		isContainer_FALSE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isContainer_FALSE(), isContainer);
		isContainer_NOTAPPLICABLE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isContainer_NOTAPPLICABLE(), isContainer);
		isContainer_UNKNOWN = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.isContainer_UNKNOWN(), isContainer);

		iqLogicValue = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.iqLogicValue(), orderedPair);
		is_TRUE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.is_TRUE(), iqLogicValue);
		is_FALSE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.is_FALSE(), iqLogicValue);
		is_NOTAPPLICABLE = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.is_NOTAPPLICABLE(), iqLogicValue);
		is_UNKNOWN = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.is_UNKNOWN(), iqLogicValue);

		booleanValue = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.booleanValue(), orderedPair);


		completion = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.completion(), orderedPair);
		successful = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.completion_successful(), completion);

		maxSearchSpaceDepth	= F_InstantiationImpl.createOrderedPair(fundamentalSIDs.maxSearchSpaceDepth(), orderedPair);

		kernelDefect = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.kernelDefect(), orderedPair);
		kernelDefect_KernelHasReachedAnIllegalState = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.kernelDefect_KernelHasReachedAnIllegalState(), kernelDefect);

		semanticErr = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr(), orderedPair);
		semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors(), semanticErr);
		semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles(), semanticErr);
		semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances(), semanticErr);
		semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph(), semanticErr);
		semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph(), semanticErr);
		semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet(), semanticErr);
		semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet(), semanticErr);
		semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction(), semanticErr);
		semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail(), semanticErr);
		semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex(), semanticErr);
		semanticErr_OnlyInstancesHaveIsAbstract = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyInstancesHaveIsAbstract(), semanticErr);
		semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality(), semanticErr);
		semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality(), semanticErr);
		semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer(), semanticErr);
		semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable(), semanticErr);
		semanticErr_ValueIsNotAssigned = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_ValueIsNotAssigned(), semanticErr);
		semanticErr_LinkIsNotApplicable = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_LinkIsNotApplicable(), semanticErr);
		semanticErr_TargetIsNotWithinVisibility = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_TargetIsNotWithinVisibility(), semanticErr);
		semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor(), semanticErr);
		semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor(), semanticErr);
		semanticErr_GraphGraphCantBeModified = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_GraphGraphCantBeModified(), semanticErr);
		semanticErr_VariableCantBeRemovedArtifactStillHasInstances = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_VariableCantBeRemovedArtifactStillHasInstances(), semanticErr);
		semanticErr_GraphsCantBeDecommissioned = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_GraphsCantBeDecommissioned(), semanticErr);
		semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance(), semanticErr);
		semanticErr_maxFromCardinalityIsOne = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_maxFromCardinalityIsOne(), semanticErr);
		semanticErr_maxFromCardinalityIsTwo = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_maxFromCardinalityIsTwo(), semanticErr);
		semanticErr_maxFromCardinalityIsIllegal = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_maxFromCardinalityIsIllegal(), semanticErr);
		semanticErr_maxToCardinalityIsOne = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_maxToCardinalityIsOne(), semanticErr);
		semanticErr_maxToCardinalityIsTwo = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_maxToCardinalityIsTwo(), semanticErr);
		semanticErr_maxToCardinalityIsIllegal = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_maxToCardinalityIsIllegal(), semanticErr);
		semanticErr_operationIsIllegalOnThisInstance = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_operationIsIllegalOnThisInstance(), semanticErr);
		semanticErr_operationIsNotYetImplemented = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_operationIsNotYetImplemented(), semanticErr);
		semanticErr_OnlyTransportContainerCanHaveContentElements = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyTransportContainerCanHaveContentElements(), semanticErr);
		semanticErr_ThisSetIsNotAvailableInMemory = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_ThisSetIsNotAvailableInMemory(), semanticErr);

		parameter = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.parameter(), orderedPair);
		flavor = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.flavor(), parameter);
		target = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.target(), parameter);
		subGraph = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.subGraph(), parameter);
		name = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.name(), orderedPair);
		pluralName = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.pluralName(), orderedPair);
		technicalName = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.technicalName(), orderedPair);
		identifier = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.identifier(), orderedPair);

		/**
		 * EventImpl concepts
		 */

		event = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.event(), orderedPair);
		elementAdded = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.elementAdded(), event);
		elementRemoved = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.elementRemoved(), event);

		/**
		 * Graph concepts
		 */
		orderedSet = F_Query.orderedSetFlavor();

		/** Core functions
		 */
		function = F_InstantiationImpl.createFunction(identityFactory.function(), orderedSet);

		command = F_InstantiationImpl.createFunction(identityFactory.command(), function);
		commandFunction = F_InstantiationImpl.createFunction(identityFactory.commandFunction(), command);
		flavorCommandFunction = F_InstantiationImpl.createFunction(identityFactory.flavorCommandFunction(), command);

		query = F_InstantiationImpl.createFunction(identityFactory.query(), function) ;
		queryFunction = F_InstantiationImpl.createOrderedPair(identityFactory.queryFunction(), query) ;
		flavorQueryFunction = F_InstantiationImpl.createOrderedPair(identityFactory.flavorQueryFunction(), query) ;

		/**
		 * OrderedPairFlavor queries
		 */
		identity = F_InstantiationImpl.createFunction(identityFactory.identity(), flavorQueryFunction) ;
		isEqualTo = F_InstantiationImpl.createFunction(identityFactory.isEqualTo(), flavorQueryFunction) ;
		semanticIdentity = F_InstantiationImpl.createFunction(identityFactory.filterPolymorphic(), flavorQueryFunction);
		isASemanticIdentity = F_InstantiationImpl.createFunction(identityFactory.isASemanticIdentity(), flavorCommandFunction) ;
		isInformation = F_InstantiationImpl.createFunction(identityFactory.isInformation(), query);

		/**
		 * OrderedSetFlavor commands
		 */
		union = F_InstantiationImpl.createFunction(identityFactory.union(), flavorCommandFunction) ;
		intersection = F_InstantiationImpl.createFunction(identityFactory.intersection(), flavorCommandFunction) ;
		complement = F_InstantiationImpl.createFunction(identityFactory.complement(), flavorCommandFunction) ;

		/**
		 * OrderedSetFlavor queries
		 */
		contains = F_InstantiationImpl.createFunction(identityFactory.contains(), flavorQueryFunction) ;
		containsAll = F_InstantiationImpl.createFunction(identityFactory.containsAll(), flavorQueryFunction) ;
		get = F_InstantiationImpl.createFunction(identityFactory.get(), flavorQueryFunction) ;
		indexOf = F_InstantiationImpl.createFunction(identityFactory.indexOf(), flavorQueryFunction) ;
		isEmpty = F_InstantiationImpl.createFunction(identityFactory.isEmpty(), flavorQueryFunction) ;
		lastIndexOf = F_InstantiationImpl.createFunction(identityFactory.lastIndexOf(), flavorQueryFunction) ;
		listIterator = F_InstantiationImpl.createFunction(identityFactory.listIterator(), flavorQueryFunction) ;
		listIteratorInt = F_InstantiationImpl.createFunction(identityFactory.listIteratorInt(), flavorQueryFunction) ;
		size = F_InstantiationImpl.createFunction(identityFactory.size(), flavorQueryFunction) ;
		toArray = F_InstantiationImpl.createFunction(identityFactory.toArray(), flavorQueryFunction) ;
		toArrayInstance = F_InstantiationImpl.createFunction(identityFactory.toArrayInstance(), flavorQueryFunction) ;
		indexOfIdentifier = F_InstantiationImpl.createFunction(identityFactory.indexOfIdentifier(), flavorQueryFunction) ;

		/**
		 * SemanticIdentity commands
		 */
		addElement = F_InstantiationImpl.createFunction(identityFactory.addElement(), commandFunction) ;
		removeElement = F_InstantiationImpl.createFunction(identityFactory.removeElement(), commandFunction) ;

		setMaintenanceCommand = F_InstantiationImpl.createFunction(identityFactory.setMaintenanceCommand(), commandFunction) ;

		isElementOf = F_InstantiationImpl.createFunction(identityFactory.isElementOf(), queryFunction) ;

		/**
		 * GraphFlavor commands
		 */
		addAbstract = F_InstantiationImpl.createFunction(identityFactory.addAbstract(), flavorCommandFunction) ;
		addConcrete = F_InstantiationImpl.createFunction(identityFactory.addConcrete(), flavorCommandFunction) ;
		addAbstractSubGraph = F_InstantiationImpl.createFunction(identityFactory.isALink(), flavorCommandFunction) ;
		addToVariables = F_InstantiationImpl.createFunction(identityFactory.addToVariables(), flavorCommandFunction) ;
		addToValues = F_InstantiationImpl.createFunction(identityFactory.addToValues(), flavorCommandFunction) ;
		decommission = F_InstantiationImpl.createFunction(identityFactory.decommission(), flavorCommandFunction) ;
		instantiateAbstract = F_InstantiationImpl.createFunction(identityFactory.instantiateAbstract(), flavorCommandFunction) ;
		instantiateConcrete = F_InstantiationImpl.createFunction(identityFactory.instantiateConcrete(), flavorCommandFunction) ;
		removeFromVariables = F_InstantiationImpl.createFunction(identityFactory.removeFromVariables(), flavorCommandFunction) ;
		removeFromValues = F_InstantiationImpl.createFunction(identityFactory.removeFromValues(), flavorCommandFunction) ;
		setValue = F_InstantiationImpl.createFunction(identityFactory.setValue(), flavorCommandFunction) ;
		/**
		 * GraphFlavor queries
		 */
		artifact = F_InstantiationImpl.createFunction(identityFactory.container(), flavorQueryFunction);
		filter = F_InstantiationImpl.createFunction(identityFactory.filter(), isInformation, flavorQueryFunction);
		containsEdgeFromOrTo = F_InstantiationImpl.createFunction(identityFactory.containsEdgeFromOrTo(), orderedPair, flavorQueryFunction);
		filterFlavor = F_InstantiationImpl.createFunction(identityFactory.filterFlavor(), flavor, flavorQueryFunction);
		hasVisibilityOf = F_InstantiationImpl.createFunction(identityFactory.hasVisibilityOf(), target, flavorQueryFunction);
		filterInstances = F_InstantiationImpl.createFunction(identityFactory.filterInstances(), flavorQueryFunction);
		isSuperSetOf = F_InstantiationImpl.createFunction(identityFactory.isSuperSetOf(), orderedPair, flavorQueryFunction);
		isLocalSuperSetOf = F_InstantiationImpl.createFunction(identityFactory.isLocalSuperSetOf(), orderedPair, flavorQueryFunction);
		filterLinks = F_InstantiationImpl.createFunction(identityFactory.filterLinks(), flavorQueryFunction);
		localRootSuperSetOf = F_InstantiationImpl.createFunction(identityFactory.localRootSuperSetOf(), orderedPair, flavorQueryFunction);
		directSuperSetOf = F_InstantiationImpl.createFunction(identityFactory.directSuperSetOf(), orderedPair, flavorQueryFunction);
		category = F_InstantiationImpl.createFunction(identityFactory.category(), flavorQueryFunction);
		containerCategory = F_InstantiationImpl.createFunction(identityFactory.containerCategory(), target, flavorQueryFunction);
		variables = F_InstantiationImpl.createFunction(identityFactory.variables(), flavorQueryFunction);
		value = F_InstantiationImpl.createFunction(identityFactory.value(), orderedPair, flavorQueryFunction);
		values = F_InstantiationImpl.createFunction(identityFactory.values(), flavorQueryFunction);
		visibleArtifactsForSubGraph = F_InstantiationImpl.createFunction(identityFactory.visibleArtifactsForSubGraph(), subGraph, flavorQueryFunction);
		allowableEdgeCategories = F_InstantiationImpl.createFunction(identityFactory.allowableEdgeCategories(), orderedPair, flavorQueryFunction);
		filterPolymorphic = F_InstantiationImpl.createFunction(identityFactory.filterPolymorphic(), category, flavorQueryFunction);
		queries = F_InstantiationImpl.createFunction(identityFactory.queries(), flavorQueryFunction);
		commands = F_InstantiationImpl.createFunction(identityFactory.commands(), flavorQueryFunction);
		executableQueries = F_InstantiationImpl.createFunction(identityFactory.executableQueries(), flavorQueryFunction);
		executableCommands = F_InstantiationImpl.createFunction(identityFactory.executableCommands(), flavorQueryFunction);

		/**
		 * LinkFlavor queries
		 */
		from = F_InstantiationImpl.createFunction(identityFactory.from(), flavorQueryFunction);
		isExternal = F_InstantiationImpl.createFunction(identityFactory.isExternal(), flavorQueryFunction);
		to = F_InstantiationImpl.createFunction(identityFactory.to(), flavorQueryFunction);

		/**
		 * EdgeFlavor queries
		 */
		edgeEnds = F_InstantiationImpl.createFunction(identityFactory.edgeEnds(), flavorQueryFunction);
		fromEdgeEnd = F_InstantiationImpl.createFunction(identityFactory.fromEdgeEnd(), flavorQueryFunction);
		toEdgeEnd = F_InstantiationImpl.createFunction(identityFactory.toEdgeEnd(), flavorQueryFunction);



		referencingSemanticRole = F_InstantiationImpl.createFunction(identityFactory.referencingSemanticRole(), orderedPair);

	}

}
