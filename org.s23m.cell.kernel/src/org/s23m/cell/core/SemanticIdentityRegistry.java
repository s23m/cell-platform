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

package org.s23m.cell.core;

/**
 * {@link SemanticIdentityRegistry} is an ordered list of the SemanticIdentities
 * that are used to construct the Instances and Properties of the Gmodel kernel.
 * 
 * Important: Elements in this list may never be removed or resequenced,
 * as the stability of the UUIDs of semantic identities in the kernel of Gmodel depends
 * on the sequence of elements in this list.
 * 
 * 	==>	If new semantic identities need to be added to the Gmodel kernel,
 * 		this list needs to be appended with a corresponding element.
 * 	==>	If a semantic identity <si> becomes obsolete, the corresponding element in this list must
 * 		be renamed from <si> to <si>_DEPRECATED.
 */

public enum SemanticIdentityRegistry {
	anonymous,
	anonymousInKernel,
	semanticIdentity,
	orderedPair,
	orderedSet,
	graph,
	link,
	edge,
	superSetReference,
	visibility,
	edgeTrace,
	vertex,
	edgeEnd,

	isAbstract,
	isAbstract_TRUE,
	isAbstract_FALSE,

	isNavigable,
	isNavigable_TRUE,
	isNavigable_FALSE,
	isNavigable_NOTAPPLICABLE,
	isNavigable_UNKNOWN,

	isContainer,
	isContainer_TRUE,
	isContainer_FALSE,
	isContainer_NOTAPPLICABLE,
	isContainer_UNKNOWN,

	minCardinality,
	minCardinality_0,
	minCardinality_1,
	minCardinality_2,
	minCardinality_n,
	minCardinality_NOTAPPLICABLE,
	minCardinality_UNKNOWN,

	maxCardinality,
	maxCardinality_0,
	maxCardinality_1,
	maxCardinality_2,
	maxCardinality_n,
	maxCardinality_NOTAPPLICABLE,
	maxCardinality_UNKNOWN,
	iqLogicValue,
	is_TRUE,
	is_FALSE,
	is_NOTAPPLICABLE,
	is_UNKNOWN,

	completion,
	completion_successful,

	kernelDefect,
	kernelDefect_KernelHasReachedAnIllegalState,
	semanticErr,
	semanticErr_OnlyEdgeFlavoredInstancesHaveEdgeEndFlavors,
	semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedRoles,
	semanticErr_OnlyEdgeFlavoredInstancesHaveConnectedInstances,
	semanticErr_OnlyVisibilityFlavoredInstancesHaveFromSubGraph,
	semanticErr_OnlyVisibilityFlavoredInstancesHaveToSubGraph,
	semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSuperSet,
	semanticErr_OnlySuperSetReferenceFlavoredInstancesHaveSubSet,
	semanticErr_OnlyEdgeTraceFlavoredInstancesHaveAbstraction,
	semanticErr_OnlyEdgeTraceFlavoredInstancesHaveDetail,
	semanticErr_OnlyEdgeEndFlavoredInstancesHaveEdgeEndVertex,
	semanticErr_OnlyInstancesHaveIsAbstract,
	semanticErr_OnlyEdgeEndFlavoredInstancesHaveMinCardinality,
	semanticErr_OnlyEdgeEndFlavoredInstancesHaveMaxCardinality,
	semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsContainer,
	semanticErr_OnlyEdgeEndFlavoredInstancesHaveIsNavigable,
	semanticErr_ValueIsNotAssigned,
	semanticErr_LinkIsNotApplicable,
	semanticErr_TargetIsNotWithinVisibility,
	semanticErr_AddConcreteIsOnlyValidForConcreteVertexFlavor,
	semanticErr_AddAbstractIsOnlyValidForAbstractVertexFlavor,
	semanticErr_GraphGraphCantBeModified,
	semanticErr_VariableCantBeRemovedArtifactStillHasInstances,
	semanticErr_GraphsCantBeDecommissioned,
	semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance,
	semanticErr_maxFromCardinalityIsOne,
	semanticErr_maxFromCardinalityIsTwo,
	semanticErr_maxFromCardinalityIsIllegal,
	semanticErr_maxToCardinalityIsOne,
	semanticErr_maxToCardinalityIsTwo,
	semanticErr_maxToCardinalityIsIllegal,
	semanticErr_operationIsIllegalOnThisInstance,
	semanticErr_operationIsNotYetImplemented,
	semanticErr_OnlyTransportContainerCanHaveContentElements,

	/**
	 * semantic domain
	 */

	semanticDomain,

	/**
	 * commands & query parameters
	 */
	parameter,
	target,
	subGraph,

	/**
	 * kernel commands & queries
	 */

	function,
	command,
	commandFunction,
	flavorCommandFunction,
	query,
	queryFunction,
	flavorQueryFunction,

	/**
	 * OrderedPairFlavor queries
	 */

	flavor,
	identity,
	isEqualTo,
	isInformation,


	/**
	 * OrderedSetFlavor queries
	 */

	contains,
	containsAll,
	get,
	indexOf,
	isEmpty,
	lastIndexOf,
	listIterator,
	listIteratorInt,
	size,
	toArray,
	toArrayInstance,

	/**
	 * GraphFlavor commands
	 */

	addAbstract,
	addConcrete,
	isALink,
	isASemantikIdentity,
	addToVariables,
	addToValues,
	decommission,
	instantiateAbstract,
	instantiateConcrete,
	removeFromVariables,
	removeFromValues,
	setPropertyValue,

	/**
	 * GraphFlavor, VertexFlavor, EdgeEndFlavor queries
	 */

	container,
	filter,
	containsEdgeFromOrTo,
	filterFlavor,
	hasVisibilityOf,
	filterInstances,
	isSuperSetOf,
	isLocalSuperSetOf,
	filterLinks,
	localRootSuperSetOf,
	directSuperSetOf,
	category,
	containerCategory,
	variables,
	value,
	values,
	visibleArtifactsForSubGraph,

	/**
	 * LinkFlavor queries
	 */

	from,
	isExternal,
	to,

	/**
	 * EdgeFlavor queries
	 */

	edgeEnds,
	connectedInstances,
	fromEdgeEnd,
	toEdgeEnd,

	/**
	 * EdgeTraceFlavor queries
	 */

	fromAbstraction,
	toDetail,

	/**
	 * SuperSetReferenceFlavor queries
	 */

	fromSubSet,
	toSuperSet,

	/**
	 * VisibilityFlavor queries
	 */

	fromSubGraph,
	toSubGraph,

	/**
	 * semantic identities used by test scripts and inner shells
	 */

	semanticdomains,
	models,
	universalartifactengineering,
	extractUniqueMatch,
	isQuality,
	root,
	timestamp,

	file,
	derivedFile,
	derivationTechnology,
	xpand,
	locationFunction,
	derivationRule,
	derivedArtifact,
	sourceArtifact,
	execute,

	htmlRepresentation,
	htmlTargetLocation,
	html_to_artifact,
	somePathInFileSystem,

	semanticIdentitySet,
	equivalenceClass,
	semanticRole,
	disjunctSemanticIdentitySet,
	variantDisjunctSemanticIdentitySet,
	abstractSemanticRole,
	referencedSemanticRole,
	referencingSemanticRole,
	element,
	DEPRECATED_addSubscriber,
	variabilityDimension,
	variantIdentifier,
	addElement,
	removeElement,
	elements,
	semanticIdentitySet_addElement,
	semanticIdentitySet_removeElement,
	semanticIdentitySet_elements,
	disjunctSemanticIdentitySet_addElement,
	disjunctSemanticIdentitySet_removeElement,
	disjunctSemanticIdentitySet_elements,

	set,
	semanticErr_ASetWithThisIdentityAndRepresentationIsAlreadyLoaded,
	semanticErr_ThisSetIsNotAvailableInMemory,
	allowableEdgeCategories,
	filterPolymorphic,
	indexOfIdentifier,
	isEqualToRepresentation,
	containsRepresentations,
	containsAllRepresentations,
	asList,
	addToCommands,
	addToQueries,
	removeFromCommands,
	removeFromQueries,
	assignNewName,
	assignNewPluralName,
	assignNewPayload,
	maxSearchSpaceDepth,

	name,
	pluralName,
	technicalName,
	identifier,
	queries,
	commands,
	executableQueries,
	executableCommands,
	union,
	intersection,
	complement,
	isElementOf,

	event,
	elementAdded,
	elementRemoved,
	processEvent,
	setMaintenanceEvents,
	addSubscriber,
	removeSubscriber,
	generatingSet,
	generatingElement,
	booleanValue,
	elementsOfSemanticIdentitySet,

	// 2011 11 25
	localVisualRecognitionTextWithEdgeEnds,
	addGeneratingElement,
	addGeneratingSet,
	and,
	containsDecommissionedSets,
	containsEdgeTo,
	containsNewSets,
	decommissionPayload,
	hasDecommissionedPayload,
	hasNewName,
	hasNewPayload,
	hasNewPluralName,
	includesValue,
	isDecommissioned,
	isNewInstance,
	not,
	or,
	unionOfconnectingLinks,
	fullVisualRecognitionText,
	isASemanticIdentity,
	localVisualRecognitionText,
	visualRecognitionText,
	containsRepresentation,
	containsSemanticMatch,
	containsSemanticMatchesForAll,
	filterByEquivalenceClass,
	filterByLinkedFrom,
	filterByLinkedFromAndTo,
	filterByLinkedFromAndToSemanticRole,
	filterByLinkedFromAndToVia,
	filterByLinkedFromSemanticRole,
	filterByLinkedFromVia,
	filterByLinkedTo,
	filterByLinkedToSemanticRole,
	filterByLinkedToVia,
	filterFrom,
	filterFromAndTo,
	filterTo,
	extractFirst,
	extractSecond,
	extractLast,
	ListIterator,
	transformToOrderedSetOfSemanticIdentities,
	uuidAsString,
	fromSet,
	toSet,
	fromSetReferencedSemanticRole,
	toSetReferencedSemanticRole,
	flavorOrCategory,
	a,
	b,
	setMaintenanceCommand,
}

