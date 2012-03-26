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
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.platform.models;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;

/**
 * {@link CellSemantics} implements all instantiation semantics related to the modelling of container state machines
 * that must be enforced for all Instances/cells (instantiation level n, with n > 0)
 *
 * The semantics enforced in CellEngineering provide the basis for modelling the dynamic evolution of the S23M instantiation semantics
 */
public final class CellSemantics {

	//public static final Set S23MSemanticLifecycle = F_SemanticStateOfInMemoryModel.instantiateConcrete(Artifact.lifeCycle, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("S23M semantic lifecycle", "S23M semantic lifecycle", S23MSemanticDomains.S23M));
	public static final Set s23mSemanticLifecycle = Root.cellengineering.addConcrete(TimeConsciousness.lifeCycle, Instantiation.addDisjunctSemanticIdentitySet("S23M semantic lifecycle", "S23M semantic lifecycle", S23MSemanticDomains.cellKernel));
	// definition of the life cycle of the S23M Semantic State
	public static final Set semanticDomainInitialized = s23mSemanticLifecycle.addConcrete(TimeConsciousness.state, Instantiation.addDisjunctSemanticIdentitySet("semantic domain initialized", "semantic domain initialized", S23MSemanticDomains.cellKernel));

	// the S23M semantics state machine, tied to the S23MSemanticLifecycle
	//public static final Set S23MSemantics = F_SemanticStateOfInMemoryModel.instantiateConcrete(Artifact.artifact, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("S23M semantics", "S23M semantics", S23MSemanticDomains.S23M));
	public static final Set s23mSemantics = Root.cellengineering.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("S23M semantics", "S23M semantics", S23MSemanticDomains.cellKernel));
	public static final Set s23mSemantics_to_lifecycle = Instantiation.arrow(TimeConsciousness.cell_to_lifeCycle,
			Instantiation.addDisjunctSemanticIdentitySet("S23MSemantics_to_lifecycle", "S23MSemantics_to_lifecycle", S23MSemanticDomains.cellKernel),
			s23mSemantics,
			s23mSemantics,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE,
			TimeConsciousness.lifeCycle,
			s23mSemanticLifecycle,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static Set instantiateFeature() {
		Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, s23mSemanticLifecycle);
		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, Root.models, S23MSemanticLifecycle);

		// initialization of the S23M semantics state machine with the semanticDomainInitialized state
		final Set state = Instantiation.arrow(TimeConsciousness.cell_to_state,
				Instantiation.addDisjunctSemanticIdentitySet("S23MSemantics_to_state", "S23MSemantics_to_state", S23MSemanticDomains.cellKernel),
				Organization.cell,
				s23mSemantics,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE,
				TimeConsciousness.state,
				semanticDomainInitialized,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
		);

		return state;
	}

}
