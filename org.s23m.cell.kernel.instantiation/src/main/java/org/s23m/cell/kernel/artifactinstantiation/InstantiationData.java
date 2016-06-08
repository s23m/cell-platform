package org.s23m.cell.kernel.artifactinstantiation;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Instantiation.arrow;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.RepositoryStructure;

// This could eventually replace the fields in TestSequence
public class InstantiationData {

	public final Set crm_product;
	public final Set product_to_price;
	public final Set order;

	public InstantiationData(final InstantiationSequences instantiationSequences) {
		final Set entityrelationshipschema = instantiationSequences.entityrelationshipschema;
		final Set entity = instantiationSequences.entity;
		final Set testDomain = instantiationSequences.testDomain;
		final Set crm = instantiationSequences.crm;

		crm_product = instantiationSequences.crm_product;

		final Set sex = addDisjunctSemanticIdentitySet("sex", "sexes" , testDomain);
		final Set male = addDisjunctSemanticIdentitySet("male", "males" , testDomain);
		final Set female = addDisjunctSemanticIdentitySet("female", "females" , testDomain);

		sex.addElement(male);
		sex.addElement(female);

		arrow(coreGraphs.visibility, entityrelationshipschema, testDomain);
		// TODO: don't allow the creation of visibilities with target within a semantic domain such as: F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, entity, sex);

		arrow(coreGraphs.visibility, RepositoryStructure.applicationengineering, testDomain);
		arrow(coreGraphs.visibility, crm, testDomain);
		arrow(coreGraphs.visibility, crm_product, testDomain);

		final Set attribute = entityrelationshipschema.addConcrete(coreGraphs.vertex,
				addDisjunctSemanticIdentitySet("attribute", "attributes" , testDomain));

		final Set entity_to_sex = arrow(coreGraphs.edge,
				addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				addDisjunctSemanticIdentitySet("entity to sex", "entity to sex", testDomain),
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
				);
		final Set entity_to_attribute = arrow(coreGraphs.edge,
				addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				addDisjunctSemanticIdentitySet("entity to attribute", "entity to attribute", testDomain),
				entity,
				S23MSemanticDomains.minCardinality_1,
				S23MSemanticDomains.maxCardinality_1,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_TRUE,
				addDisjunctSemanticIdentitySet("attribute", "attributes" , testDomain),
				attribute,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				);
		final Set entity_to_entity = arrow(coreGraphs.edge,
				addDisjunctSemanticIdentitySet("source", "sources" , testDomain),
				addDisjunctSemanticIdentitySet("entity to entity", "entity to entity", testDomain),
				entity,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE,
				addDisjunctSemanticIdentitySet("target", "targets" , testDomain),
				entity,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				);

		// Instantiation level 2

		final Set crm_product_to_sex = arrow(entity_to_sex,
				addDisjunctSemanticIdentitySet("crm_product_to_sex", "crm_product_to_sex" , testDomain),
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
		order = crm.addConcrete(entity,
	  			addDisjunctSemanticIdentitySet("order", "orders", testDomain));
	  	final Set price = crm.addConcrete(attribute,
			addDisjunctSemanticIdentitySet("price", "prices" , testDomain));

	  	final Set product_to_order = arrow(entity_to_entity,
				addDisjunctSemanticIdentitySet("order", "orders" , testDomain),
				addDisjunctSemanticIdentitySet("order to crm product", "order to crm product", testDomain),
				order,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE,
				addDisjunctSemanticIdentitySet("product", "products" , testDomain),
				crm_product,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				);
	  	product_to_price = arrow(entity_to_attribute,
				addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				addDisjunctSemanticIdentitySet("crm product to price", "crm product to price", testDomain),
				crm_product,
				S23MSemanticDomains.minCardinality_1,
				S23MSemanticDomains.maxCardinality_1,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_TRUE,
				addDisjunctSemanticIdentitySet("price", "prices" , testDomain),
				price,
				S23MSemanticDomains.minCardinality_0,
				S23MSemanticDomains.maxCardinality_n,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE
				);


	}

}
