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
import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;

/**
 * {@link Cell} implements all instantiation semantics related to the modelling of container state machines
 * that must be enforced for all Instances/artifacts (instantiation level n, with n > 0)
 *
 * The semantics enforced in Artifact provide the basis for modelling the dynamic evolution of the S23M instantiation semantics
 */
public final class Cell {

	//public static final Set lifeCycle = F_SemanticStateOfInMemoryModel.instantiateConcrete(coreGraphs.vertex, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("life cycle", "life cycles", S23MSemanticDomains.S23M));
	public static final Set lifeCycle = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.lifeCycle);

	public static final Set state = lifeCycle.addConcrete(coreGraphs.vertex, CellPlatformDomain.state);
	public static final Set transition = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.transition,
			CellPlatformDomain.source,
			state,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.target,
			state,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	//public static final Set container = F_SemanticStateOfInMemoryModel.instantiateConcrete(coreGraphs.vertex, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("state conscious container", "set of state conscious artifacts", S23MSemanticDomains.S23M));
	public static final Set artifact = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.cell);

	public static final Set artifact_to_lifeCycle = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.cell_to_lifeCycle,
			artifact,
			artifact,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.lifeCycle,
			lifeCycle,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	//private static final Set v1 = F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, Root.models, lifeCycle);
	//private static final Set v1 = F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, container, lifeCycle);
	private static final Set v2 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, lifeCycle);

	public static final Set artifact_to_state = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.cell_to_state,

			artifact,
			artifact,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.cellState,
			state,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set artifact_to_validityInterval = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.cell_to_validityInterval,
			artifact,
			artifact,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			ValidityInterval.validityInterval,
			ValidityInterval.validityInterval,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set artifact_to_transaction = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.cell_to_transaction,
			artifact,
			artifact,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			Transaction.transaction,
			Transaction.transaction,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	static Set instantiateFeature() {

		Instantiation.arrow(coreGraphs.superSetReference, artifact, coreGraphs.vertex);
		// additional semantics
		Instantiation.arrow(coreGraphs.superSetReference, lifeCycle, coreGraphs.vertex);

		return artifact;
	}

}
