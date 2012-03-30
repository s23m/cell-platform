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
 * {@link CellEngineering} implements all instantiation semantics related to the modelling of container state machines
 * that must be enforced for all Instances/cells (instantiation level n, with n > 0)
 *
 * The semantics enforced in CellEngineering provide the basis for modelling the dynamic evolution of the S23M instantiation semantics
 */
public final class CellEngineering {

	public static final Set language = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.language);
	public static final Set location = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.location);

	public static final Set agency = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.agency);
	public static final Set jargon = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.jargon);
	private static final Set s0 = Instantiation.arrow(coreGraphs.superSetReference, jargon, language);
	private static final Set v0 = Instantiation.arrow(coreGraphs.visibility, jargon, language);

	public static final Set timeConsciousness = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.timeConsciousness);

	public static final Set legal = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.legal);
	public static final Set terminology = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.terminology);
	public static final Set organization = Root.cellengineering.addConcrete(TimeConsciousness.timeConsciousVertex, CellPlatformDomain.organization);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, organization, TimeConsciousness.timeConsciousVertex);

	public static final Set formula = Root.cellengineering.addAbstract(Organization.cell, CellPlatformDomain.formula);
	public static final Set logicalFormula = Root.cellengineering.addAbstract(Organization.cell, CellPlatformDomain.logicalFormula);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, formula, Organization.cell);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, logicalFormula, formula);
	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, logicalFormula, formula);

	public static final Set representationStyleCategories = Root.cellengineering.addConcrete(coreGraphs.vertex, CellPlatformDomain.representationStyleCategories);

	public static final Set collaboration = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.collaboration,
			CellPlatformDomain.consumer,
			organization,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.supplier,
			organization,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	public static final Set organization_to_location = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.organization_to_location,
			CellPlatformDomain.organization,
			organization,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_n,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			CellPlatformDomain.location,
			location,
			S23MSemanticDomains.minCardinality_0,
			S23MSemanticDomains.maxCardinality_1,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
	);

	static Set instantiateFeature() {


		// additional semantics


		return Root.cellengineering;
	}

}
