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


public class SessionHandling {

	private static final Set v0 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.sessionHandling);
	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.sessionHandling, CellEngineering.timeConsciousness);

	public static final Set session = CellEngineering.sessionHandling.addConcrete(Organization.cell, CellPlatformDomain.session);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, session, Organization.cell);
	public static final Set error = CellEngineering.sessionHandling.addConcrete(Organization.cell, CellPlatformDomain.error);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, error, Organization.cell);

	public static final Set session_to_transaction = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.session_to_transaction,
			CellPlatformDomain.session,
			session,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.transaction,
			TimeConsciousness.transaction,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set session_to_error = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.session_to_error,
			CellPlatformDomain.session,
			session,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.error,
			error,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set error_to_transaction = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.error_to_transaction,
			CellPlatformDomain.error,
			error,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.transaction,
			TimeConsciousness.transaction,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static Set instantiateFeature() {
		session.identity().makePartOfUniversalCellConcept();
		return CellEngineering.sessionHandling;
	}
}
