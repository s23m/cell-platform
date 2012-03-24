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

package org.s23m.cell.kernel.artifactinstantiation;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.EnterpriseArchitecture;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.core.SemanticIdentityRegistry;

public class KernelTestSequence {

	private static SemanticIdentityRegistry nameRegistry;
	// just for the fun of it we create an instance of an edge
	//public static final Set testDomain = Instantiation.addSemanticDomain("test domain", "test domains", S23MSemanticDomains.agentSemanticDomains);
	public static final Set testDomain = Instantiation.addSemanticDomain("test domain", "test domains", S23MSemanticDomains.sandboxSemanticDomains);

	public static final Set whoToWho = Instantiation.arrow(coreGraphs.edge, Instantiation.addDisjunctSemanticIdentitySet("who to who", "set of who to who", testDomain),
//			F_SemanticStateOfInMemoryModel.addAnonymousDisjunctSemanticIdentitySet(testDomain), EnterpriseArchitecture.who,
			EnterpriseArchitecture.who, EnterpriseArchitecture.who,
			S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE,
//			F_SemanticStateOfInMemoryModel.addAnonymousDisjunctSemanticIdentitySet(testDomain), EnterpriseArchitecture.who,
			EnterpriseArchitecture.who, EnterpriseArchitecture.who,
			S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);


	public static void run() {
		// empty for now
	}

	public static final Set acmeEA = RepositoryStructure.domainengineering.addConcrete(EnterpriseArchitecture.enterpriseArchitectureGraph,
			Instantiation.addDisjunctSemanticIdentitySet("ACME Enterprise Architecture", "set of ACME Enterprise Architecture", testDomain));

	public static final Set acmeMelbourne = RepositoryStructure.applicationengineering.addConcrete(acmeEA,
			Instantiation.addDisjunctSemanticIdentitySet("ACME Melbourne Enterprise Architecture", "set of ACME Melbourne Enterprise Architecture", testDomain));

	public static final Set entityrelationshipschema = RepositoryStructure.domainengineering.addConcrete(coreGraphs.vertex,
			Instantiation.addDisjunctSemanticIdentitySet("entity relationship schema", "entity relationship schemas", testDomain));
	public static final Set hierarchicalerschema = RepositoryStructure.domainengineering.addConcrete(coreGraphs.vertex,
			Instantiation.addDisjunctSemanticIdentitySet("hierarchical entity relationship schema", "hierarchical entity relationship schemas", testDomain));

	public static final Set crm = RepositoryStructure.applicationengineering.addConcrete(entityrelationshipschema,
			Instantiation.addDisjunctSemanticIdentitySet("customer relationship management", "customer relationship management", testDomain));
	public static final Set entity = entityrelationshipschema.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("entity", "entities" , testDomain));

	public static final Set crm_product = crm.addConcrete(entity,
			Instantiation.addDisjunctSemanticIdentitySet("product", "products" , testDomain));



	public static final Set crm_aviz = RepositoryStructure.graphVisualizations.addConcrete(Visualization.graphVisualization,
			Instantiation.addDisjunctSemanticIdentitySet("crm schema container visualizedGraph", "crm schema container graphVisualizations", testDomain));



}
