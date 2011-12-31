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
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api.models;

import static org.s23m.cell.G.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.SemanticIdentityRegistry;

/**
 * {@link HTMLRepresentation} implements all instantiation semantics related to HTMLRepresentation
 * that must be enforced for all Instances/artifacts (instantiation level n, with n > 0)
 * 
 * The semantics enforced in HTMLRepresentation apply to Gmodel artifacts.
 * 
 */
public final class HTMLRepresentation {

	public static final Set htmlRepresentation = coreGraphs.vertex.addConcrete(ArtifactDerivation.derivedFile, F_Instantiation.addDisjunctSemanticIdentitySetInKernel("html representation", "html representation", GmodelSemanticDomains.gmodel, SemanticIdentityRegistry.htmlRepresentation.ordinal()));
	public static final Set htmlTargetLocation = F_Instantiation.addDisjunctSemanticIdentitySetInKernel("/html/", "/html/", GmodelSemanticDomains.gmodel, SemanticIdentityRegistry.htmlTargetLocation.ordinal());

	public static final Set html_to_artifact = org.s23m.cell.api.Instantiation.link(ArtifactDerivation.derivationRule,
			F_Instantiation.addDisjunctSemanticIdentitySetInKernel("html to container", "html to artifacts", GmodelSemanticDomains.gmodel, SemanticIdentityRegistry.html_to_artifact.ordinal()),
			htmlRepresentation,
			htmlRepresentation,
			GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
			GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
			GmodelSemanticDomains.isNavigable_FALSE,
			GmodelSemanticDomains.isContainer_FALSE,
			GmodelSemanticDomains.artifact,
			coreGraphs.vertex,
			GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
			GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
			GmodelSemanticDomains.isNavigable_FALSE,
			GmodelSemanticDomains.isContainer_TRUE
	);


	public static Set instantiateFeature() {
		ArtifactDerivation.locationFunction.addElement(htmlTargetLocation);
		html_to_artifact.addToValues(ArtifactDerivation.xpand);
		html_to_artifact.addToValues(htmlTargetLocation);
		return coreGraphs.graph;
	}
}
