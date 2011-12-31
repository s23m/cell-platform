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

/**
 * {@link Artifact} implements all instantiation semantics related to the modelling of container state machines
 * that must be enforced for all Instances/artifacts (instantiation level n, with n > 0)
 *
 * The semantics enforced in Artifact provide the basis for modelling the dynamic evolution of the Gmodel instantiation semantics
 */
public final class Artifact {

	//public static final Set lifeCycle = F_SemanticStateOfInMemoryModel.instantiateConcrete(coreGraphs.vertex, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("life cycle", "life cycles", GmodelSemanticDomains.gmodel));
	public static final Set lifeCycle = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.lifeCycle);

	public static final Set state = lifeCycle.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.state);
	public static final Set transition = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.transition,
			SemanticExtensionsDomain.source,
			state,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			SemanticExtensionsDomain.target,
			state,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	//public static final Set container = F_SemanticStateOfInMemoryModel.instantiateConcrete(coreGraphs.vertex, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("state conscious container", "set of state conscious artifacts", GmodelSemanticDomains.gmodel));
	public static final Set artifact = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, SemanticExtensionsDomain.artifact);

	public static final Set artifact_to_lifeCycle = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.artifact_to_lifeCycle,
			artifact,
			artifact,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			SemanticExtensionsDomain.lifeCycle,
			lifeCycle,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	//private static final Set v1 = F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, Root.models, lifeCycle);
	//private static final Set v1 = F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, container, lifeCycle);
	private static final Set v2 = Instantiation.link(coreGraphs.visibility, Root.universalartifactengineering, lifeCycle);

	public static final Set artifact_to_state = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.artifact_to_state,

			artifact,
			artifact,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			SemanticExtensionsDomain.artifactState,
			state,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);
	public static final Set artifact_to_validityInterval = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.artifact_to_validityInterval,
			artifact,
			artifact,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			ValidityInterval.validityInterval,
			ValidityInterval.validityInterval,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	public static final Set artifact_to_transaction = Instantiation.link(coreGraphs.edge,
			SemanticExtensionsDomain.artifact_to_transaction,
			artifact,
			artifact,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE,
			Transaction.transaction,
			Transaction.transaction,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_TRUE,
			coreSets.isContainer_FALSE
	);

	static Set instantiateFeature() {

		Instantiation.link(coreGraphs.superSetReference, artifact, coreGraphs.vertex);
		// additional semantics
		Instantiation.link(coreGraphs.superSetReference, lifeCycle, coreGraphs.vertex);

		return artifact;
	}

}
