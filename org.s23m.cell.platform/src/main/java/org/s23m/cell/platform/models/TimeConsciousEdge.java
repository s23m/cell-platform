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


public class TimeConsciousEdge {

	public static final Set timeConsciousEdge = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.timeConsciousEdge,
			Cell.artifact,
			Cell.artifact,
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

	//private static final Set v2 = F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, Root.universalartifactengineering, timeConsciousEdge);


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

	public static final Set timeConsciousEdge_to_transaction = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.timeConsciousEdge_to_transaction,
			timeConsciousEdge,
			timeConsciousEdge,
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


	public static Set instantiateFeature() {

		Instantiation.arrow(coreGraphs.superSetReference, timeConsciousEdge, coreGraphs.edge);

		return timeConsciousEdge;
	}
}
