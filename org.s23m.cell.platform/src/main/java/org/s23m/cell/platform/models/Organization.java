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


public class Organization {

	// STRUCTURE OF MODEL REPOSITORY
	//public static final Set legalEntity = Root.cellengineering.addConcrete(SemanticDimension.who, CellPlatformDomain.legalEntity);
	//public static final Set organization = Root.cellengineering.addConcrete(SemanticDimension.who, CellPlatformDomain.organization);
	//public static final Set person = Root.cellengineering.addConcrete(SemanticDimension.who, CellPlatformDomain.person);
	private static final Set v6 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.organization, CellEngineering.semanticDimension);

	public static final Set user = CellEngineering.organization.addConcrete(SemanticDimension.who, CellPlatformDomain.user);
	public static final Set role = CellEngineering.organization.addConcrete(SemanticDimension.who, CellPlatformDomain.role);
	public static final Set languagePreference = user.addConcrete(SemanticDimension.what, CellPlatformDomain.languagePreference);
	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, CellEngineering.organization, SemanticDimension.who);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, user, SemanticDimension.who);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, role, SemanticDimension.who);

	public static final Set user_to_roles = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.user_to_roles,
			user,
			user,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			role,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set organization_to_subOrganizations = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.organization_to_subOrganizations,
			CellPlatformDomain.parentOrganization,
			CellEngineering.organization,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			CellPlatformDomain.subOrganization,
			CellEngineering.organization,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set role_to_includedRoles = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.role_to_includedRoles,
			CellPlatformDomain.parentRole,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			CellPlatformDomain.includedRole,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set role_to_excludedRoles = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.role_to_excludedRoles,
			CellPlatformDomain.disjunctRole,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			CellPlatformDomain.excludedRole,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set v0 = Instantiation.arrow(coreGraphs.visibility, CellEngineering.organization, CellEngineering.language);
	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, user, CellEngineering.language);
	public static final Set languagePreference_to_language = Instantiation.arrow(TimeConsciousness.timeConsciousEdge,
			CellPlatformDomain.languagePreference_to_language,
			languagePreference,
			languagePreference,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			CellEngineering.language,
			CellEngineering.language,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	private static final Set v5 = Instantiation.arrow(coreGraphs.visibility, Root.cellengineering, CellEngineering.organization);

	public static Set instantiateFeature() {

		return CellEngineering.organization;
	}

}
