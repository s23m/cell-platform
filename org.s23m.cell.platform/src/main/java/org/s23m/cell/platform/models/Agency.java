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


public class Agency {

	private static final Set v0 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.agency);

	public static final Set agent = CellEngineering.agency.addConcrete(coreGraphs.vertex, CellPlatformDomain.agent);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, agent, coreGraphs.vertex);
	public static final Set person = CellEngineering.agency.addConcrete(coreGraphs.vertex, CellPlatformDomain.person);
	public static final Set system = CellEngineering.agency.addConcrete(coreGraphs.vertex, CellPlatformDomain.system);
	public static final Set stage = CellEngineering.agency.addConcrete(coreGraphs.vertex, CellPlatformDomain.stage);
	private static final Set s0 = Instantiation.arrow(coreGraphs.superSetReference, stage, agent);


	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, person, agent);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, system, agent);
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

	public static final Set agent_to_stages = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.agent_to_stages,
			agent,
			agent,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.stage,
			stage,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);


	private static final Set v3 = Instantiation.arrow(coreGraphs.visibility, perspective, CellEngineering.language);
	private static final Set v4 = Instantiation.arrow(coreGraphs.visibility, perspective, CellEngineering.jargon);

	public static final Set perspective_to_jargon = Instantiation.arrow(coreGraphs.edge,
			CellPlatformDomain.perspective_to_jargon,
			perspectiveV,
			perspectiveV,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.jargon,
			CellEngineering.language,
			S23MSemanticDomains.minCardinality_1,
			S23MSemanticDomains.maxCardinality_1,
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

	public static final Set s23mCellPlatform = org.s23m.cell.platform.api.Instantiation.addAgent("S23M cell platform", "S23M cell platform");
	public static final Set development = org.s23m.cell.platform.api.Instantiation.addStage("development", "development", s23mCellPlatform);
	public static final Set testing = org.s23m.cell.platform.api.Instantiation.addStage("test", "test", s23mCellPlatform);
	public static final Set production = org.s23m.cell.platform.api.Instantiation.addStage("production", "production", s23mCellPlatform);

	public static Set instantiateFeature() {
		return CellEngineering.agency;
	}
}
