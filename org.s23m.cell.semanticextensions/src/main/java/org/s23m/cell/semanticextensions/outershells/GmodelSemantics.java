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

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.Root;

/**
 * {@link GmodelSemantics} implements all instantiation semantics related to the modelling of container state machines
 * that must be enforced for all Instances/artifacts (instantiation level n, with n > 0)
 *
 * The semantics enforced in Artifact provide the basis for modelling the dynamic evolution of the Gmodel instantiation semantics
 */
public final class GmodelSemantics {

	//public static final Set gmodelSemanticLifecycle = F_SemanticStateOfInMemoryModel.instantiateConcrete(Artifact.lifeCycle, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("gmodel semantic lifecycle", "gmodel semantic lifecycle", GmodelSemanticDomains.gmodel));
	public static final Set gmodelSemanticLifecycle = Root.universalartifactengineering.addConcrete(Artifact.lifeCycle, Instantiation.addDisjunctSemanticIdentitySet("gmodel semantic lifecycle", "gmodel semantic lifecycle", GmodelSemanticDomains.gmodel));
	// definition of the life cycle of the Gmodel Semantic State
	public static final Set semanticDomainInitialized = gmodelSemanticLifecycle.addConcrete(Artifact.state, Instantiation.addDisjunctSemanticIdentitySet("semantic domain initialized", "semantic domain initialized", GmodelSemanticDomains.gmodel));

	// the gmodel semantics state machine, tied to the gmodelSemanticLifecycle
	//public static final Set gmodelSemantics = F_SemanticStateOfInMemoryModel.instantiateConcrete(Artifact.artifact, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("gmodel semantics", "gmodel semantics", GmodelSemanticDomains.gmodel));
	public static final Set gmodelSemantics = Root.universalartifactengineering.addConcrete(Artifact.artifact, Instantiation.addDisjunctSemanticIdentitySet("gmodel semantics", "gmodel semantics", GmodelSemanticDomains.gmodel));
	public static final Set gmodelSemantics_to_lifecycle = Instantiation.link(Artifact.artifact_to_lifeCycle,
			Instantiation.addDisjunctSemanticIdentitySet("gmodelSemantics_to_lifecycle", "gmodelSemantics_to_lifecycle", GmodelSemanticDomains.gmodel),
			gmodelSemantics,
			gmodelSemantics,
			GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
			GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
			GmodelSemanticDomains.isNavigable_TRUE,
			GmodelSemanticDomains.isContainer_FALSE,
			Artifact.lifeCycle,
			gmodelSemanticLifecycle,
			GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
			GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
			GmodelSemanticDomains.isNavigable_TRUE,
			GmodelSemanticDomains.isContainer_FALSE
	);

	public static Set instantiateFeature() {
		Instantiation.link(coreGraphs.visibility, Root.universalartifactengineering, gmodelSemanticLifecycle);
		//F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, Root.models, gmodelSemanticLifecycle);

		// initialization of the gmodel semantics state machine with the semanticDomainInitialized state
		final Set state = Instantiation.link(Artifact.artifact_to_state,
				Instantiation.addDisjunctSemanticIdentitySet("gmodelSemantics_to_state", "gmodelSemantics_to_state", GmodelSemanticDomains.gmodel),
				Artifact.artifact,
				gmodelSemantics,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE,
				Artifact.state,
				semanticDomainInitialized,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE
		);

		return state;
	}

}
