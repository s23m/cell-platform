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
 * The Original Code is Gmodel.
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

package org.s23m.cell.semanticextensions.outershells;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;


public class LegalEntity {

	// STRUCTURE OF MODEL REPOSITORY
	public static final Set legalEntity = Root.universalartifactengineering.addConcrete(SemanticEnterprise.who, SemanticExtensionsDomain.legalEntity);
	public static final Set organization = Root.universalartifactengineering.addConcrete(SemanticEnterprise.who, SemanticExtensionsDomain.organization);
	public static final Set person = Root.universalartifactengineering.addConcrete(SemanticEnterprise.who, SemanticExtensionsDomain.person);
	public static final Set user = organization.addConcrete(SemanticEnterprise.who, SemanticExtensionsDomain.user);
	public static final Set role = organization.addConcrete(SemanticEnterprise.who, SemanticExtensionsDomain.role);
	public static final Set languagePreference = user.addConcrete(SemanticEnterprise.what, SemanticExtensionsDomain.languagePreference);

	public static final Set user_to_roles = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
			SemanticExtensionsDomain.user_to_roles,
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
	public static final Set organization_to_subOrganizations = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
			SemanticExtensionsDomain.organization_to_subOrganizations,
			SemanticExtensionsDomain.parentOrganization,
			organization,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			SemanticExtensionsDomain.subOrganization,
			organization,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set role_to_includedRoles = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
			SemanticExtensionsDomain.role_to_includedRoles,
			SemanticExtensionsDomain.parentRole,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_TRUE,
			SemanticExtensionsDomain.includedRole,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set role_to_excludedRoles = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
			SemanticExtensionsDomain.role_to_excludedRoles,
			SemanticExtensionsDomain.disjunctRole,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			SemanticExtensionsDomain.excludedRole,
			role,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	private static final Set v0 = Instantiation.link(coreGraphs.visibility, organization, Language.language);
	private static final Set v1 = Instantiation.link(coreGraphs.visibility, user, Language.language);
	public static final Set languagePreference_to_language = Instantiation.link(TimeConsciousEdge.timeConsciousEdge,
			SemanticExtensionsDomain.languagePreference_to_language,
			languagePreference,
			languagePreference,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			Language.language,
			Language.language,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	private static final Set v5 = Instantiation.link(coreGraphs.visibility, Root.universalartifactengineering, organization);

	public static final Set artifact_to_producer = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.artifact_to_producer,
			Artifact.artifact,
			Artifact.artifact,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			SemanticExtensionsDomain.producer,
			LegalEntity.role,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set artifact_to_consumers = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.artifact_to_consumers,
			Artifact.artifact,
			Artifact.artifact,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			SemanticExtensionsDomain.consumer,
			LegalEntity.role,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static Set instantiateFeature() {

			Instantiation.link(coreGraphs.superSetReference, legalEntity, SemanticEnterprise.who);
			Instantiation.link(coreGraphs.superSetReference, organization, legalEntity);
			Instantiation.link(coreGraphs.superSetReference, person, legalEntity);

		return legalEntity;
	}

}
