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


public class Legal {

	private static final Set v6 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.legal, CellEngineering.timeConsciousness);

	public static final Set license = CellEngineering.legal.addConcrete(TimeConsciousness.timeConsciousVertex, CellPlatformDomain.license);
	public static final Set legalEntity = CellEngineering.legal.addConcrete(TimeConsciousness.timeConsciousVertex, CellPlatformDomain.legalEntity);
	public static final Set usageLicense = CellEngineering.legal.addConcrete(TimeConsciousness.timeConsciousVertex, CellPlatformDomain.usageLicense);;

	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, license, TimeConsciousness.timeConsciousVertex);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, usageLicense, TimeConsciousness.timeConsciousVertex);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, legalEntity, TimeConsciousness.timeConsciousVertex);
	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.legal);
	public static final Set legalEntity_to_usageLicense = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.legalEntity_to_usageLicense,
			CellPlatformDomain.user,
			legalEntity,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.usageLicense,
			usageLicense,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set usageLicense_to_license = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.usageLicence_to_license,
			CellPlatformDomain.usageLicense,
			usageLicense,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			license,
			license,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set usageLicence_to_vertex = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.usageLicence_to_vertex,
			usageLicense,
			usageLicense,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.licencedProduct,
			coreGraphs.vertex,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set usageLicence_to_issuer = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.usageLicence_to_issuer,
			usageLicense,
			usageLicense,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.issuer,
			legalEntity,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);



	public static Set instantiateFeature() {

		return CellEngineering.legal;
	}
}
