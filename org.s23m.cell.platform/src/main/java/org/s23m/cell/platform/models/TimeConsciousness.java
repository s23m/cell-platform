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


public class TimeConsciousness {

	public static final Set timeConsciousVertex = CellEngineering.timeConsciousness.addConcrete(coreGraphs.vertex, CellPlatformDomain.timeConsciousVertex);
	public static final Set lifeCycle = CellEngineering.timeConsciousness.addConcrete(coreGraphs.vertex, CellPlatformDomain.lifeCycle);
	private static final Set v0 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.timeConsciousness);
	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.timeConsciousness, CellEngineering.agency);

	public static final Set state = lifeCycle.addConcrete(coreGraphs.vertex, CellPlatformDomain.state);
	private static final Set v4 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.timeConsciousness, TimeConsciousness.lifeCycle);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, timeConsciousVertex, coreGraphs.vertex);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, lifeCycle, coreGraphs.vertex);

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

	public static final Set cell_to_lifeCycle = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.cell_to_lifeCycle,
			TimeConsciousness.timeConsciousVertex,
			TimeConsciousness.timeConsciousVertex,
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

	public static final Set cell_to_state = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.cell_to_state,

			TimeConsciousness.timeConsciousVertex,
			TimeConsciousness.timeConsciousVertex,
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


	public static final Set timeConsciousEdge = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.timeConsciousEdge,
			timeConsciousVertex,
			timeConsciousVertex,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			coreGraphs.vertex,
			coreGraphs.vertex,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set timeConsciousEdge_to_validityInterval = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.timeConsciousEdge_to_validityInterval,
			timeConsciousEdge,
			timeConsciousEdge,
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

	public static final Set cell_to_validityInterval = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.cell_to_validityInterval,
			TimeConsciousness.timeConsciousVertex,
			TimeConsciousness.timeConsciousVertex,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			ValidityInterval.validityInterval,
			ValidityInterval.validityInterval,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, timeConsciousEdge, coreGraphs.edge);

	/* transactions */

	public static final Set transaction = CellEngineering.timeConsciousness.addConcrete(coreGraphs.vertex, CellPlatformDomain.transaction);
	public static final Set creationTimestamp = CellEngineering.timeConsciousness.addConcrete(coreGraphs.vertex, CellPlatformDomain.creationTimestamp);
	/* TODO relocate transactions under agents/stages  */
	private static final Set v3 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.timeConsciousness, CellEngineering.agency);

	public static final Set transaction_to_creationTimestamp = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.transaction_to_creationTimestamp,
			transaction,
			transaction,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			creationTimestamp,
			creationTimestamp,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set transaction_to_creator = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.transaction_to_creator,
			transaction,
			transaction,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.creator,
			Agency.agent,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, transaction, coreGraphs.vertex);
	private static final Set s5 = Instantiation.arrow(coreGraphs.superSetReference, creationTimestamp, coreGraphs.vertex);

	public static final Set timeConsciousEdge_to_transaction = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.timeConsciousEdge_to_transaction,
			timeConsciousEdge,
			timeConsciousEdge,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			transaction,
			transaction,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set cell_to_transaction = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.cell_to_transaction,
			TimeConsciousness.timeConsciousVertex,
			TimeConsciousness.timeConsciousVertex,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.producer,
			transaction,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE

	);

	public static Set instantiateFeature() {

		return CellEngineering.timeConsciousness;
	}
}
