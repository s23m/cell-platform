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

package org.s23m.cell.api;

import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.core.F_InstantiationImpl;
import org.s23m.cell.core.F_Query;
import org.s23m.cell.core.KernelIdentities;
import org.s23m.cell.core.OrderedPair;

//functionality is scheduled to be limited to the S23M core
//TODO remove all usage of this class outside the core package

public final class KernelSets {

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
	public final Set semanticErr_OnlyEdgeInstancesHaveEdgeEnds;
	public final Set semanticErr_OnlyEdgeInstancesHaveConnectedRoles;
	public final Set semanticErr_OnlyEdgeInstancesHaveConnectedInstances;
	public final Set semanticErr_OnlyVisibilityInstancesHaveFromSubGraph;
	public final Set semanticErr_OnlyVisibilityInstancesHaveToSubGraph;
	public final Set semanticErr_OnlySuperSetReferenceInstancesHaveSuperSet;
	public final Set semanticErr_OnlySuperSetReferenceInstancesHaveSubSet;
	public final Set semanticErr_OnlyEdgeEndInstancesHaveEdgeEndVertex;
	public final Set semanticErr_OnlyInstancesHaveIsAbstract;
	public final Set semanticErr_OnlyEdgeEndInstancesHaveMinCardinality;
	public final Set semanticErr_OnlyEdgeEndInstancesHaveMaxCardinality;
	public final Set semanticErr_OnlyEdgeEndInstancesHaveIsContainer;
	public final Set semanticErr_OnlyEdgeEndInstancesHaveIsNavigable;
	public final Set semanticErr_ValueIsNotAssigned;
	public final Set semanticErr_ArrowIsNotApplicable;
	public final Set semanticErr_TargetIsNotWithinVisibility;
	public final Set semanticErr_AddConcreteIsOnlyValidForConcreteVertex;
	public final Set semanticErr_AddAbstractIsOnlyValidForAbstractVertex;
	public final Set semanticErr_GraphGraphCantBeModified;
	public final Set semanticErr_VariableCantBeRemovedCategoryStillHasInstances;
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
	public final Set properClass;

	public final Set orderedSet;

	/**
	 * core functions
	 */
	public final Set function;
	public final Set command;
	public final Set commandFunction;
	public final Set properClassCommandFunction;
	public final Set query ;
	public final Set queryFunction ;
	public final Set properClassQueryFunction ;

	/**
	 * OrderedPair queries
	 */

	//public final Set ;
	public final Set identity ;
	public final Set isEqualTo ;
	public final Set semanticIdentity;

	//public final Set isInformation ;
	/**
	 * OrderedSet commands
	 */

	public final Set union;
	public final Set intersection;
	public final Set complement;

	/**
	 * OrderedSet queries
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
	 * Graph commands
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
	 * Graph, Vertex, EdgeEnd queries
	 */

	public final Set container ;
	public final Set filter ;
	public final Set containsEdgeFromOrTo ;
	public final Set filterProperClass;
	public final Set hasVisibilityOf ;
	public final Set filterInstances ;
	public final Set isSuperSetOf ;
	public final Set isLocalSuperSetOf ;
	public final Set filterArrows ;
	public final Set localRootSuperSetOf ;
	public final Set directSuperSetOf ;
	public final Set category ;
	public final Set containerCategory ;
	public final Set variables ;
	public final Set value ;
	public final Set values ;
	public final Set visibleInstancesForSubGraph ;
	public final Set allowableEdgeCategories;
	public final Set filterPolymorphic;
	public final Set queries ;
	public final Set commands ;
	public final Set executableQueries ;
	public final Set executableCommands ;


	/**
	 * Arrow queries
	 */

	public final Set from ;
	public final Set isExternal ;
	public final Set to;

	/**
	 * Edge queries
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

	public KernelSets(final KernelIdentities fundamentalSIDs) {
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
		semanticErr_OnlyEdgeInstancesHaveEdgeEnds = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeInstancesHaveEdgeEnds(), semanticErr);
		semanticErr_OnlyEdgeInstancesHaveConnectedRoles = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeInstancesHaveConnectedRoles(), semanticErr);
		semanticErr_OnlyEdgeInstancesHaveConnectedInstances = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeInstancesHaveConnectedInstances(), semanticErr);
		semanticErr_OnlyVisibilityInstancesHaveFromSubGraph = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyVisibilityInstancesHaveFromSubGraph(), semanticErr);
		semanticErr_OnlyVisibilityInstancesHaveToSubGraph = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyVisibilityInstancesHaveToSubGraph(), semanticErr);
		semanticErr_OnlySuperSetReferenceInstancesHaveSuperSet = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlySuperSetReferenceInstancesHaveSuperSet(), semanticErr);
		semanticErr_OnlySuperSetReferenceInstancesHaveSubSet = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlySuperSetReferenceInstancesHaveSubSet(), semanticErr);
		semanticErr_OnlyEdgeEndInstancesHaveEdgeEndVertex = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndInstancesHaveEdgeEndVertex(), semanticErr);
		semanticErr_OnlyInstancesHaveIsAbstract = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyInstancesHaveIsAbstract(), semanticErr);
		semanticErr_OnlyEdgeEndInstancesHaveMinCardinality = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndInstancesHaveMinCardinality(), semanticErr);
		semanticErr_OnlyEdgeEndInstancesHaveMaxCardinality = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndInstancesHaveMaxCardinality(), semanticErr);
		semanticErr_OnlyEdgeEndInstancesHaveIsContainer = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndInstancesHaveIsContainer(), semanticErr);
		semanticErr_OnlyEdgeEndInstancesHaveIsNavigable = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_OnlyEdgeEndInstancesHaveIsNavigable(), semanticErr);
		semanticErr_ValueIsNotAssigned = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_ValueIsNotAssigned(), semanticErr);
		semanticErr_ArrowIsNotApplicable = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_ArrowIsNotApplicable(), semanticErr);
		semanticErr_TargetIsNotWithinVisibility = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_TargetIsNotWithinVisibility(), semanticErr);
		semanticErr_AddConcreteIsOnlyValidForConcreteVertex = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_AddConcreteIsOnlyValidForConcreteVertex(), semanticErr);
		semanticErr_AddAbstractIsOnlyValidForAbstractVertex = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_AddAbstractIsOnlyValidForAbstractVertex(), semanticErr);
		semanticErr_GraphGraphCantBeModified = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_GraphGraphCantBeModified(), semanticErr);
		semanticErr_VariableCantBeRemovedCategoryStillHasInstances = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.semanticErr_VariableCantBeRemovedCategoryStillHasInstances(), semanticErr);
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
		properClass = F_InstantiationImpl.createOrderedPair(fundamentalSIDs.properClass(), parameter);
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
		orderedSet = F_Query.orderedSet();

		/** Core functions
		 */
		function = F_InstantiationImpl.createFunction(identityFactory.function(), orderedSet);

		command = F_InstantiationImpl.createFunction(identityFactory.command(), function);
		commandFunction = F_InstantiationImpl.createFunction(identityFactory.commandFunction(), command);
		properClassCommandFunction = F_InstantiationImpl.createFunction(identityFactory.properClassCommandFunction(), command);

		query = F_InstantiationImpl.createFunction(identityFactory.query(), function) ;
		queryFunction = F_InstantiationImpl.createOrderedPair(identityFactory.queryFunction(), query) ;
		properClassQueryFunction = F_InstantiationImpl.createOrderedPair(identityFactory.properClassQueryFunction(), query) ;

		/**
		 * OrderedPair queries
		 */
		identity = F_InstantiationImpl.createFunction(identityFactory.identity(), properClassQueryFunction) ;
		isEqualTo = F_InstantiationImpl.createFunction(identityFactory.isEqualTo(), properClassQueryFunction) ;
		semanticIdentity = F_InstantiationImpl.createFunction(identityFactory.filterPolymorphic(), properClassQueryFunction);
		isASemanticIdentity = F_InstantiationImpl.createFunction(identityFactory.isASemanticIdentity(), properClassCommandFunction) ;
		isInformation = F_InstantiationImpl.createFunction(identityFactory.isInformation(), query);

		/**
		 * OrderedSet commands
		 */
		union = F_InstantiationImpl.createFunction(identityFactory.union(), properClassCommandFunction) ;
		intersection = F_InstantiationImpl.createFunction(identityFactory.intersection(), properClassCommandFunction) ;
		complement = F_InstantiationImpl.createFunction(identityFactory.complement(), properClassCommandFunction) ;

		/**
		 * OrderedSet queries
		 */
		contains = F_InstantiationImpl.createFunction(identityFactory.contains(), properClassQueryFunction) ;
		containsAll = F_InstantiationImpl.createFunction(identityFactory.containsAll(), properClassQueryFunction) ;
		get = F_InstantiationImpl.createFunction(identityFactory.get(), properClassQueryFunction) ;
		indexOf = F_InstantiationImpl.createFunction(identityFactory.indexOf(), properClassQueryFunction) ;
		isEmpty = F_InstantiationImpl.createFunction(identityFactory.isEmpty(), properClassQueryFunction) ;
		lastIndexOf = F_InstantiationImpl.createFunction(identityFactory.lastIndexOf(), properClassQueryFunction) ;
		listIterator = F_InstantiationImpl.createFunction(identityFactory.listIterator(), properClassQueryFunction) ;
		listIteratorInt = F_InstantiationImpl.createFunction(identityFactory.listIteratorInt(), properClassQueryFunction) ;
		size = F_InstantiationImpl.createFunction(identityFactory.size(), properClassQueryFunction) ;
		toArray = F_InstantiationImpl.createFunction(identityFactory.toArray(), properClassQueryFunction) ;
		toArrayInstance = F_InstantiationImpl.createFunction(identityFactory.toArrayInstance(), properClassQueryFunction) ;
		indexOfIdentifier = F_InstantiationImpl.createFunction(identityFactory.indexOfIdentifier(), properClassQueryFunction) ;

		/**
		 * SemanticIdentity commands
		 */
		addElement = F_InstantiationImpl.createFunction(identityFactory.addElement(), commandFunction) ;
		removeElement = F_InstantiationImpl.createFunction(identityFactory.removeElement(), commandFunction) ;

		setMaintenanceCommand = F_InstantiationImpl.createFunction(identityFactory.setMaintenanceCommand(), commandFunction) ;

		isElementOf = F_InstantiationImpl.createFunction(identityFactory.isElementOf(), queryFunction) ;

		/**
		 * Graph commands
		 */
		addAbstract = F_InstantiationImpl.createFunction(identityFactory.addAbstract(), properClassCommandFunction) ;
		addConcrete = F_InstantiationImpl.createFunction(identityFactory.addConcrete(), properClassCommandFunction) ;
		addAbstractSubGraph = F_InstantiationImpl.createFunction(identityFactory.isAnArrow(), properClassCommandFunction) ;
		addToVariables = F_InstantiationImpl.createFunction(identityFactory.addToVariables(), properClassCommandFunction) ;
		addToValues = F_InstantiationImpl.createFunction(identityFactory.addToValues(), properClassCommandFunction) ;
		decommission = F_InstantiationImpl.createFunction(identityFactory.decommission(), properClassCommandFunction) ;
		instantiateAbstract = F_InstantiationImpl.createFunction(identityFactory.instantiateAbstract(), properClassCommandFunction) ;
		instantiateConcrete = F_InstantiationImpl.createFunction(identityFactory.instantiateConcrete(), properClassCommandFunction) ;
		removeFromVariables = F_InstantiationImpl.createFunction(identityFactory.removeFromVariables(), properClassCommandFunction) ;
		removeFromValues = F_InstantiationImpl.createFunction(identityFactory.removeFromValues(), properClassCommandFunction) ;
		setValue = F_InstantiationImpl.createFunction(identityFactory.setValue(), properClassCommandFunction) ;
		/**
		 * Graph queries
		 */
		container = F_InstantiationImpl.createFunction(identityFactory.container(), properClassQueryFunction);
		filter = F_InstantiationImpl.createFunction(identityFactory.filter(), isInformation, properClassQueryFunction);
		containsEdgeFromOrTo = F_InstantiationImpl.createFunction(identityFactory.containsEdgeFromOrTo(), orderedPair, properClassQueryFunction);
		filterProperClass = F_InstantiationImpl.createFunction(identityFactory.filterProperClass(), properClass, properClassQueryFunction);
		hasVisibilityOf = F_InstantiationImpl.createFunction(identityFactory.hasVisibilityOf(), target, properClassQueryFunction);
		filterInstances = F_InstantiationImpl.createFunction(identityFactory.filterInstances(), properClassQueryFunction);
		isSuperSetOf = F_InstantiationImpl.createFunction(identityFactory.isSuperSetOf(), orderedPair, properClassQueryFunction);
		isLocalSuperSetOf = F_InstantiationImpl.createFunction(identityFactory.isLocalSuperSetOf(), orderedPair, properClassQueryFunction);
		filterArrows = F_InstantiationImpl.createFunction(identityFactory.filterArrows(), properClassQueryFunction);
		localRootSuperSetOf = F_InstantiationImpl.createFunction(identityFactory.localRootSuperSetOf(), orderedPair, properClassQueryFunction);
		directSuperSetOf = F_InstantiationImpl.createFunction(identityFactory.directSuperSetOf(), orderedPair, properClassQueryFunction);
		category = F_InstantiationImpl.createFunction(identityFactory.category(), properClassQueryFunction);
		containerCategory = F_InstantiationImpl.createFunction(identityFactory.containerCategory(), target, properClassQueryFunction);
		variables = F_InstantiationImpl.createFunction(identityFactory.variables(), properClassQueryFunction);
		value = F_InstantiationImpl.createFunction(identityFactory.value(), orderedPair, properClassQueryFunction);
		values = F_InstantiationImpl.createFunction(identityFactory.values(), properClassQueryFunction);
		visibleInstancesForSubGraph = F_InstantiationImpl.createFunction(identityFactory.visibleInstancesForSubGraph(), subGraph, properClassQueryFunction);
		allowableEdgeCategories = F_InstantiationImpl.createFunction(identityFactory.allowableEdgeCategories(), orderedPair, properClassQueryFunction);
		filterPolymorphic = F_InstantiationImpl.createFunction(identityFactory.filterPolymorphic(), category, properClassQueryFunction);
		queries = F_InstantiationImpl.createFunction(identityFactory.queries(), properClassQueryFunction);
		commands = F_InstantiationImpl.createFunction(identityFactory.commands(), properClassQueryFunction);
		executableQueries = F_InstantiationImpl.createFunction(identityFactory.executableQueries(), properClassQueryFunction);
		executableCommands = F_InstantiationImpl.createFunction(identityFactory.executableCommands(), properClassQueryFunction);

		/**
		 * Arrow queries
		 */
		from = F_InstantiationImpl.createFunction(identityFactory.from(), properClassQueryFunction);
		isExternal = F_InstantiationImpl.createFunction(identityFactory.isExternal(), properClassQueryFunction);
		to = F_InstantiationImpl.createFunction(identityFactory.to(), properClassQueryFunction);

		/**
		 * Edge queries
		 */
		edgeEnds = F_InstantiationImpl.createFunction(identityFactory.edgeEnds(), properClassQueryFunction);
		fromEdgeEnd = F_InstantiationImpl.createFunction(identityFactory.fromEdgeEnd(), properClassQueryFunction);
		toEdgeEnd = F_InstantiationImpl.createFunction(identityFactory.toEdgeEnd(), properClassQueryFunction);



		referencingSemanticRole = F_InstantiationImpl.createFunction(identityFactory.referencingSemanticRole(), orderedPair);

	}

}
