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

package org.s23m.cell.kernel.testbench;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.InstanceDerivation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.EnterpriseArchitecture;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.core.SemanticIdentityRegistry;
import org.s23m.cell.impl.DerivationCode;

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
		entityRelationshipModelling();
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


	public static void entityRelationshipModelling() {

		final Set sex = Instantiation.addDisjunctSemanticIdentitySet("sex", "sexes" , testDomain);
		final Set male = Instantiation.addDisjunctSemanticIdentitySet("male", "males" , testDomain);
		final Set female = Instantiation.addDisjunctSemanticIdentitySet("female", "females" , testDomain);

		sex.addElement(male);
		sex.addElement(female);

		Instantiation.arrow(coreGraphs.visibility, entityrelationshipschema, testDomain);
		// TODO: don't allow the creation of visibilities with target within a semantic domain such as: F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, entity, sex);

		Instantiation.arrow(coreGraphs.visibility, RepositoryStructure.applicationengineering, testDomain);
		Instantiation.arrow(coreGraphs.visibility, crm, testDomain);
		Instantiation.arrow(coreGraphs.visibility, crm_product, testDomain);
		Instantiation.arrow(coreGraphs.visibility, hierarchicalerschema, testDomain);

		// EXAMPLE CODE FOR DEFINING DERIVEDARTEFACTS

		final Set sqltabledefinition = entityrelationshipschema.addConcrete(InstanceDerivation.derivedFile,
				Instantiation.addDisjunctSemanticIdentitySet("sql table definition", "sql table definitions" , testDomain));
		final Set userviewdefinition = entityrelationshipschema.addConcrete(InstanceDerivation.derivedFile,
				Instantiation.addDisjunctSemanticIdentitySet("user view definition", "user view definitions" , testDomain));

	  	final Set entityTargetLocation = org.s23m.cell.core.F_Instantiation.addDisjunctSemanticIdentitySetInKernel("some_path_in_filesystem", "some_path_in_filesystem" , S23MSemanticDomains.cellKernel, nameRegistry.somePathInFileSystem.ordinal());

	  	InstanceDerivation.locationFunction.addElement(entityTargetLocation);

		final Set derived_sqltabledefinition_to_entity = Instantiation.arrow(InstanceDerivation.derivationRule,
				Instantiation.addDisjunctSemanticIdentitySet("SQL table definition to entity", "SQL table definition to entity", testDomain),
				sqltabledefinition,
				sqltabledefinition,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				entity,
				entity,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE
		);
		derived_sqltabledefinition_to_entity.addToValues(InstanceDerivation.xpand);
		derived_sqltabledefinition_to_entity.addToValues(entityTargetLocation);

		final Set derived_userviewdefinition_to_entity = Instantiation.arrow(InstanceDerivation.derivationRule,
				Instantiation.addDisjunctSemanticIdentitySet("user view definition to entity", "user view definition to entity", testDomain),
				Instantiation.addAnonymousDisjunctSemanticIdentitySet(testDomain),
				userviewdefinition,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				entity,
				entity,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE
		);
		derived_userviewdefinition_to_entity.addToValues(InstanceDerivation.xpand);
		derived_userviewdefinition_to_entity.addToValues(entityTargetLocation);

		// FURTHER STUFF

		final Set attribute = entityrelationshipschema.addConcrete(coreGraphs.vertex,
				Instantiation.addDisjunctSemanticIdentitySet("attribute", "attributes" , testDomain));

		//TODO Fix up
		/* final Set entity_to_sex = Instantiation.link(coreGraphs.edge,
				Instantiation.addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				Instantiation.addDisjunctSemanticIdentitySet("entity to sex", "entity to sex", testDomain),
				entity,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_TRUE,
				sex,
				sex,
				S23MSemanticDomains.minCardinality_1,
				S23MSemanticDomains.maxCardinality_1,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				); */
		final Set entity_to_attribute = Instantiation.arrow(coreGraphs.edge,
				Instantiation.addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				Instantiation.addDisjunctSemanticIdentitySet("entity to attribute", "entity to attribute", testDomain),
				entity,
				S23MSemanticDomains.minCardinality_1,
				S23MSemanticDomains.maxCardinality_1,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_TRUE,
				Instantiation.addDisjunctSemanticIdentitySet("attribute", "attributes" , testDomain),
				attribute,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				);
		final Set entity_to_entity = Instantiation.arrow(coreGraphs.edge,
				Instantiation.addDisjunctSemanticIdentitySet("source", "sources" , testDomain),
				Instantiation.addDisjunctSemanticIdentitySet("entity to entity", "entity to entity", testDomain),
				entity,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet("target", "targets" , testDomain),
				entity,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				);

		// Instantiation level 2

	  	//crm_product.setValue(male);
		//TODO Fix up

		/* final Set crm_product_to_sex = Instantiation.link(entity_to_sex,
				Instantiation.addDisjunctSemanticIdentitySet("crm_product_to_sex", "crm_product_to_sex" , testDomain),
				crm_product,
				crm_product,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_NOTAPPLICABLE,
				S23MSemanticDomains.isContainer_NOTAPPLICABLE,
				male,
				male,
				S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_NOTAPPLICABLE,
				S23MSemanticDomains.isContainer_NOTAPPLICABLE
				);
 */
	  	final Set order = crm.addConcrete(entity,
	  			Instantiation.addDisjunctSemanticIdentitySet("order", "orders", testDomain));
	  	final Set price = crm.addConcrete(attribute,
			Instantiation.addDisjunctSemanticIdentitySet("price", "prices" , testDomain));

	  	final Set product_to_order = Instantiation.arrow(entity_to_entity,
				Instantiation.addDisjunctSemanticIdentitySet("order", "orders" , testDomain),
				Instantiation.addDisjunctSemanticIdentitySet("order to crm product", "order to crm product", testDomain),
				order,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet("product", "products" , testDomain),
				crm_product,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				);
	  	final Set product_to_price = Instantiation.arrow(entity_to_attribute,
				Instantiation.addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				Instantiation.addDisjunctSemanticIdentitySet("crm product to price", "crm product to price", testDomain),
				crm_product,
				S23MSemanticDomains.minCardinality_1,
				S23MSemanticDomains.maxCardinality_1,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_TRUE,
				Instantiation.addDisjunctSemanticIdentitySet("price", "prices" , testDomain),
				price,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				);

		//allowableEdgeTest()
		if (entity.allowableEdgeCategories(attribute).size() < 2) {
			Instantiation.raiseError(S23MSemanticDomains.kernelDefect_KernelHasReachedAnIllegalState, S23MSemanticDomains.kernelDefect);
		}


	  	// EXAMPLE OF CODE GENERATION
	  	// Execute the derivations for sqltabledefinitions

		DerivationCode.execute(derived_sqltabledefinition_to_entity, entityrelationshipschema.filterProperClass(coreGraphs.vertex));
	}

	public static final Set crm_aviz = RepositoryStructure.graphVisualizations.addConcrete(Visualization.graphVisualization,
			Instantiation.addDisjunctSemanticIdentitySet("crm schema container visualizedGraph", "crm schema container graphVisualizations", testDomain));



}
