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
package org.s23m.cell.platform.api.models;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;


public class ValidityInterval {

	public static final Set validityInterval = CellEngineering.timeConsciousness.addConcrete(coreGraphs.vertex, CellPlatformDomain.validityInterval);
	public static final Set validFromTimestamp = CellEngineering.timeConsciousness.addConcrete(coreGraphs.vertex, CellPlatformDomain.validFromTimestamp);
	public static final Set validUntilTimestamp = CellEngineering.timeConsciousness.addConcrete(coreGraphs.vertex, CellPlatformDomain.validUntilTimestamp);



	public static final Set validityInterval_to_validFromTimestamp = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.validityInterval_to_validFromTimestamp,
			validityInterval,
			validityInterval,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			validFromTimestamp,
			validFromTimestamp,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set validityInterval_to_validUntilTimestamp = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.validityInterval_to_validUntilTimestamp,
			validityInterval,
			validityInterval,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			validUntilTimestamp,
			validUntilTimestamp,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	private static final Set s1 =	Instantiation.arrow(coreGraphs.superSetReference, validityInterval, coreGraphs.vertex);
	private static final Set s2 =	Instantiation.arrow(coreGraphs.superSetReference, validFromTimestamp, coreGraphs.vertex);
	private static final Set s3 =	Instantiation.arrow(coreGraphs.superSetReference, validUntilTimestamp, coreGraphs.vertex);

	public static Set instantiateFeature() {


		return validityInterval;
	}
}
