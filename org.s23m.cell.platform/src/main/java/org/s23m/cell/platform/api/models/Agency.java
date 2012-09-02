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

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;

public class Agency {

	private static final Set v0 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.agency);

	public static final Set agent = CellEngineering.agency.addConcrete(coreGraphs.vertex, CellPlatformDomain.agent);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, agent, coreGraphs.vertex);
	public static final Set nonLinearSystem = CellEngineering.agency.addConcrete(coreGraphs.vertex, CellPlatformDomain.nonLinearSystem);
	public static final Set linearSystem = CellEngineering.agency.addConcrete(coreGraphs.vertex, CellPlatformDomain.linearSystem);
	public static final Set stage = CellEngineering.agency.addConcrete(coreGraphs.vertex, CellPlatformDomain.stage);
	private static final Set s0 = Instantiation.arrow(coreGraphs.superSetReference, stage, coreGraphs.vertex);


	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, nonLinearSystem, agent);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, linearSystem, agent);
	//private static final Set v2 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.agency, CellEngineering.timeConsciousness);
	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.agency, CellEngineering.language);
	private static final Set v2 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.agency, CellEngineering.jargon);


	public static final Set perspective = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.perspective,
			S23MSemanticDomains.from,
			agent,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			S23MSemanticDomains.to,
			agent,
			S23MSemanticDomains.minCardinality_1,
			S23MSemanticDomains.maxCardinality_1,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, perspective, coreGraphs.edge);
	public static final Set perspectiveV = perspective.addConcrete(coreGraphs.vertex, perspective);
	private static final Set s5 = Instantiation.arrow(coreGraphs.superSetReference, perspectiveV, coreGraphs.vertex);

	private static final Set v3 = Instantiation.arrow(coreGraphs.visibility, perspective, CellEngineering.language);
	private static final Set v4 = Instantiation.arrow(coreGraphs.visibility, perspective, CellEngineering.jargon);

	public static final Set perspective_to_jargons = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.perspective_to_jargons,
			perspectiveV,
			perspectiveV,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.jargon,
			CellEngineering.language,
			S23MSemanticDomains.minCardinality_1,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);
	public static final Set replication = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.replication,
			CellPlatformDomain.original,
			agent,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_1,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.copy,
			agent,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set agent_to_nativeLanguage = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.agent_to_nativeLanguage,
			agent,
			agent,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.nativeLanguage,
			CellEngineering.language,
			S23MSemanticDomains.minCardinality_1,
			S23MSemanticDomains.maxCardinality_1,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set collaborationChannel = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.collaborationChannel,
			CellPlatformDomain.consumer,
			Agency.stage,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.supplier,
			Agency.stage,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static Set instantiateFeature() {
		agent.identity().makePartOfUniversalCellConcept();
		stage.identity().makePartOfUniversalCellConcept();
		return CellEngineering.agency;
	}
}
