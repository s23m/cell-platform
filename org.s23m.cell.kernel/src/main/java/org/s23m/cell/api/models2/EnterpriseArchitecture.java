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
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api.models2;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.S23MSemanticDomains;

/**
 * {@link EnterpriseArchitecture} implements all instantiation semantics related to Enterprise Architecture information modelling
 * that must be enforced for all Instances/artifacts (instantiation level n, with n > 0)
 * 
 * The semantics enforced in EnterpriseArchitecture relate to specific subsets of vertices
 * that are useful for classifying Enterprise Architecture information
 * 
 */
public final class EnterpriseArchitecture {

	//public static final Set enterpriseArchitectureGraph = F_SemanticStateOfInMemoryModel.instantiateConcrete(StateConsciousArtifact.stateConsciousArtifact, F_SemanticStateOfInMemoryModel.addDisjunctSemanticIdentitySet("enterprise architecture", "set of enterprise architecture", S23MSemanticDomains.S23M));
	public static final Set enterpriseArchitectureGraph = RepositoryStructure.domainengineering.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("enterprise architecture", "set of enterprise architecture", S23MSemanticDomains.cellKernel));
	public static final Set how = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("how", "how? - processes", S23MSemanticDomains.cellKernel));
	public static final Set who = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("who", "who? - persons, organisations, groups", S23MSemanticDomains.cellKernel));
	public static final Set what = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("what", "what? - funding, assets, resources", S23MSemanticDomains.cellKernel));
	public static final Set when = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("when", "when? - actions, events, episodes", S23MSemanticDomains.cellKernel));
	public static final Set where = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("where", "where? - location, addresses, spacial shapes", S23MSemanticDomains.cellKernel));
	public static final Set why = enterpriseArchitectureGraph.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("why", "why? - laws, policies,  agreements", S23MSemanticDomains.cellKernel));

	private static final Set s1 = Instantiation.arrow(coreGraphs.superSetReference, enterpriseArchitectureGraph, coreGraphs.vertex);
	private static final Set s2 = Instantiation.arrow(coreGraphs.superSetReference, how, coreGraphs.vertex);
	private static final Set s3 = Instantiation.arrow(coreGraphs.superSetReference, who, coreGraphs.vertex);
	private static final Set s4 = Instantiation.arrow(coreGraphs.superSetReference, what, coreGraphs.vertex);
	private static final Set s5 = Instantiation.arrow(coreGraphs.superSetReference, when, coreGraphs.vertex);
	private static final Set s6 = Instantiation.arrow(coreGraphs.superSetReference, where, coreGraphs.vertex);
	private static final Set s7 = Instantiation.arrow(coreGraphs.superSetReference, why, coreGraphs.vertex);

	public static final Set sandbox_organization = RepositoryStructure.productlinemanagement.addConcrete(EnterpriseArchitecture.who, Instantiation.addDisjunctSemanticIdentitySet("sandbox sandbox_organization", "sandbox organizations", S23MSemanticDomains.sandboxSemanticDomains));
	public static final Set system = RepositoryStructure.productlinemanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("linearSystem", "systems", S23MSemanticDomains.sandboxSemanticDomains));
	public static final Set managedfeature = RepositoryStructure.productlinemanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("managed feature", "managed features", S23MSemanticDomains.sandboxSemanticDomains));
	public static final Set product = RepositoryStructure.productmanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("product", "products", S23MSemanticDomains.sandboxSemanticDomains));
	public static final Set productfeedback = RepositoryStructure.productmanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("product feedback", "product feedbacks", S23MSemanticDomains.sandboxSemanticDomains));
	public static final Set usecase = RepositoryStructure.productmanagement.addConcrete(EnterpriseArchitecture.how, Instantiation.addDisjunctSemanticIdentitySet("use case", "use cases",  S23MSemanticDomains.sandboxSemanticDomains));
	public static final Set timebox = RepositoryStructure.projectmanagement.addConcrete(EnterpriseArchitecture.when, Instantiation.addDisjunctSemanticIdentitySet("timebox", "timeboxes", S23MSemanticDomains.sandboxSemanticDomains));

	private static final Set v1 = Instantiation.arrow(coreGraphs.visibility, sandbox_organization, system);
	private static final Set v2 = Instantiation.arrow(coreGraphs.visibility, system, managedfeature);
	private static final Set v3 = Instantiation.arrow(coreGraphs.visibility, product, productfeedback);
	private static final Set v4 = Instantiation.arrow(coreGraphs.visibility, product, usecase);
	private static final Set v5 = Instantiation.arrow(coreGraphs.visibility, productfeedback, sandbox_organization);
	private static final Set v6 = Instantiation.arrow(coreGraphs.visibility, usecase, sandbox_organization);
	private static final Set v7 = Instantiation.arrow(coreGraphs.visibility, usecase, managedfeature);
	private static final Set v8 = Instantiation.arrow(coreGraphs.visibility, timebox, product);

	public static Set instantiateFeature() {

		// Here we ensure that the enterpriseArchitectureGraph is a true extension of Graph,
		// so that effectively the enterpriseArchitectureGraph is still at Instantiation level 0 - the same as Graph.
		// See the TestSequence script to understand the effect.
		/* Instantiation.arrow(coreGraphs.superSetReference, enterpriseArchitectureGraph, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, how, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, who, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, what, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, when, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, where, coreGraphs.vertex);
		Instantiation.arrow(coreGraphs.superSetReference, why, coreGraphs.vertex);
		 */
		return enterpriseArchitectureGraph;
	}

}
