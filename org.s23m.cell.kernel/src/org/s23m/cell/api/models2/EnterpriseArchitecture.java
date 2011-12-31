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

package org.s23m.cell.api.models2;

import static org.s23m.cell.G.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.Root;

/**
 * {@link EnterpriseArchitecture} implements all instantiation semantics related to Enterprise Architecture information modelling
 * that must be enforced for all Instances/artifacts (instantiation level n, with n > 0)
 * 
 * The semantics enforced in EnterpriseArchitecture relate to specific subsets of vertices
 * that are useful for classifying Enterprise Architecture information
 * 
 */
public final class EnterpriseArchitecture {

	//public static final Set enterpriseArchitectureGraph = F_SemanticStateOfInMemoryModel.instantiateConcrete(StateConsciousArtifact.stateConsciousArtifact, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("enterprise architecture", "set of enterprise architecture", GmodelSemanticDomains.gmodel));
	public static final Set enterpriseArchitectureGraph = Root.universalartifactengineering.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("enterprise architecture", "set of enterprise architecture", GmodelSemanticDomains.gmodel));
	public static final Set how = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("how", "how? - processes", GmodelSemanticDomains.gmodel));
	public static final Set who = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("who", "who? - persons, organisations, groups", GmodelSemanticDomains.gmodel));
	public static final Set what = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("what", "what? - funding, assets, resources", GmodelSemanticDomains.gmodel));
	public static final Set when = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("when", "when? - actions, events, episodes", GmodelSemanticDomains.gmodel));
	public static final Set where = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("where", "where? - location, addresses, spacial shapes", GmodelSemanticDomains.gmodel));
	public static final Set why = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("why", "why? - laws, policies,  agreements", GmodelSemanticDomains.gmodel));

	public static Set instantiateFeature() {

		// Here we ensure that the enterpriseArchitectureGraph is a true extension of Graph,
		// so that effectively the enterpriseArchitectureGraph is still at Instantiation level 0 - the same as Graph.
		// See the TestSequence script to understand the effect.
		Instantiation.link(coreGraphs.superSetReference, enterpriseArchitectureGraph, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, how, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, who, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, what, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, when, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, where, coreGraphs.vertex);
		Instantiation.link(coreGraphs.superSetReference, why, coreGraphs.vertex);

		return enterpriseArchitectureGraph;
	}

}
