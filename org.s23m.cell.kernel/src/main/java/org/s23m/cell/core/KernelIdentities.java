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

import org.s23m.cell.Identity;

/**
 * {@link KernelIdentities} provides access to the SemanticIdentities
 * that are used to construct the Instances and Properties of the S23M kernel.
 */
public interface KernelIdentities {

	Identity addAbstract() ;
	Identity addConcrete() ;
	Identity addElement() ;
	Identity addToValues() ;
	Identity addToVariables() ;
	Identity allowableEdgeCategories() ;
	Identity anonymous();

	Identity booleanValue();

	Identity category() ;
	Identity command() ;
	Identity commandFunction() ;
	Identity complement() ;
	Identity completion();
	Identity completion_successful();
	Identity container() ;
	Identity containerCategory() ;
	Identity contains() ;
	Identity containsAll() ;
	Identity containsEdgeFromOrTo() ;

	Identity decommission() ;
	Identity directSuperSetOf() ;

	Identity edge();
	Identity edgeEnd();
	Identity edgeEnds() ;
	Identity edgeTrace();
	Identity elementAdded();
	Identity elementRemoved();
	Identity elementsOfSemanticIdentitySet() ;
	Identity event();
	Identity extractFirst();
	Identity extractSecond();
	Identity extractLast();
	Identity extractUniqueMatch();

	Identity filter() ;
	Identity filterProperClass() ;
	Identity filterInstances() ;
	Identity filterArrows() ;
	Identity filterPolymorphic() ;
	Identity properClass();
	Identity properClassCommandFunction() ;
	Identity properClassQueryFunction() ;
	Identity from() ;
	Identity fromEdgeEnd() ;
	Identity function() ;

	Identity get() ;
	Identity graph();

	Identity hasVisibilityOf() ;

	Identity identifier();
	Identity identity() ;
	Identity indexOf() ;
	Identity instantiateAbstract() ;
	Identity instantiateConcrete() ;
	Identity intersection() ;
	Identity iqLogicValue();
	Identity is_FALSE();
	Identity is_NOTAPPLICABLE();
	Identity is_TRUE();
	Identity is_UNKNOWN();
	Identity isAbstract();
	Identity isAbstract_FALSE();
	Identity isAbstract_TRUE();
	Identity isAnArrow() ;
	Identity isASemanticIdentity() ;
	Identity isContainer();
	Identity isContainer_FALSE();
	Identity isContainer_NOTAPPLICABLE();
	Identity isContainer_TRUE();
	Identity isContainer_UNKNOWN();
	Identity isElementOf() ;
	Identity isEmpty() ;
	Identity isEqualTo() ;
	Identity isExternal() ;
	Identity isInformation() ;
	Identity isLocalSuperSetOf() ;
	Identity isNavigable();
	Identity isNavigable_FALSE();
	Identity isNavigable_NOTAPPLICABLE();
	Identity isNavigable_TRUE();
	Identity isNavigable_UNKNOWN();
	Identity isSuperSetOf() ;

	Identity kernelDefect();
	Identity kernelDefect_KernelHasReachedAnIllegalState();

	Identity lastIndexOf() ;
	Identity arrow();
	Identity listIterator() ;
	Identity listIteratorInt() ;
	Identity localRootSuperSetOf() ;

	Identity maxCardinality();
	Identity maxCardinality_0();
	Identity maxCardinality_1();
	Identity maxCardinality_2();
	Identity maxCardinality_n();
	Identity maxCardinality_NOTAPPLICABLE();
	Identity maxCardinality_UNKNOWN();
	Identity maxSearchSpaceDepth();
	Identity minCardinality();
	Identity minCardinality_0();
	Identity minCardinality_1();
	Identity minCardinality_2();
	Identity minCardinality_n();
	Identity minCardinality_NOTAPPLICABLE();
	Identity minCardinality_UNKNOWN();

	Identity name();

	Identity orderedPair();
	Identity orderedSet();

	Identity parameter() ;
	Identity pluralName();

	Identity query() ;
	Identity queryFunction() ;

	Identity removeElement() ;
	Identity removeFromValues() ;
	Identity removeFromVariables() ;

	Identity semanticDomain();
	Identity semanticErr();
	Identity semanticErr_AddAbstractIsOnlyValidForAbstractVertex();
	Identity semanticErr_AddConcreteIsOnlyValidForConcreteVertex();
	Identity semanticErr_GraphGraphCantBeModified();
	Identity semanticErr_GraphsCantBeDecommissioned();
	Identity semanticErr_ArrowIsNotApplicable();
	Identity semanticErr_maxFromCardinalityIsIllegal();
	Identity semanticErr_maxFromCardinalityIsOne();
	Identity semanticErr_maxFromCardinalityIsTwo();
	Identity semanticErr_maxToCardinalityIsIllegal();
	Identity semanticErr_maxToCardinalityIsOne();
	Identity semanticErr_maxToCardinalityIsTwo();
	Identity semanticErr_OnlyEdgeEndInstancesHaveEdgeEndVertex();
	Identity semanticErr_OnlyEdgeEndInstancesHaveIsContainer();
	Identity semanticErr_OnlyEdgeEndInstancesHaveIsNavigable();
	Identity semanticErr_OnlyEdgeEndInstancesHaveMaxCardinality();
	Identity semanticErr_OnlyEdgeEndInstancesHaveMinCardinality();
	Identity semanticErr_OnlyEdgeInstancesHaveConnectedInstances();
	Identity semanticErr_OnlyEdgeInstancesHaveConnectedRoles();
	Identity semanticErr_OnlyEdgeInstancesHaveEdgeEnds();
	Identity semanticErr_OnlyEdgeTraceInstancesHaveAbstraction();
	Identity semanticErr_OnlyEdgeTraceInstancesHaveDetail();
	Identity semanticErr_OnlyInstancesHaveIsAbstract();
	Identity semanticErr_OnlySuperSetReferenceInstancesHaveSubSet();
	Identity semanticErr_OnlySuperSetReferenceInstancesHaveSuperSet();
	Identity semanticErr_OnlyTransportContainerCanHaveContentElements();
	Identity semanticErr_OnlyVisibilityInstancesHaveFromSubGraph();
	Identity semanticErr_OnlyVisibilityInstancesHaveToSubGraph();
	Identity semanticErr_operationIsIllegalOnThisInstance();
	Identity semanticErr_operationIsNotYetImplemented();
	Identity semanticErr_TargetIsNotWithinVisibility();
	Identity semanticErr_ThisSetIsNotAvailableInMemory();
	Identity semanticErr_ValueIsNotAnInstanceOfVariableOfCategoryOfInstance();
	Identity semanticErr_ValueIsNotAssigned();
	Identity semanticErr_VariableCantBeRemovedCategoryStillHasInstances();
	Identity setMaintenanceCommand() ;
	Identity setValue() ;
	Identity size() ;
	Identity subGraph() ;
	Identity superSetReference();

	Identity target() ;
	Identity technicalName();
	Identity to();
	Identity toArray() ;
	Identity toArrayInstance() ;
	Identity toEdgeEnd() ;

	Identity union() ;

	Identity value() ;
	Identity values() ;
	Identity variables() ;
	Identity vertex();
	Identity visibility();
	Identity visibleInstancesForSubGraph() ;

}
