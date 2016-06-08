package org.s23m.cell.kernel.artifactinstantiation;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Instantiation.arrow;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.InstanceDerivation;
import org.s23m.cell.api.models.S23MSemanticDomains;

public class EntityRelationshipModelling extends AbstractInstantiationSequence {

	@Override
	protected void executeInstantiationSequence() {
		final Set testDomain = instantiationSequences.testDomain;
		final Set entityrelationshipschema = instantiationSequences.entityrelationshipschema;
		final Set crm = instantiationSequences.crm;
		final Set crm_product = instantiationSequences.crm_product;
		final Set hierarchicalerschema = instantiationSequences.hierarchicalerschema;
		final Set entity = instantiationSequences.entity;

		final Set sex = addDisjunctSemanticIdentitySet("sex", "sexes" , testDomain);
		final Set male = addDisjunctSemanticIdentitySet("male", "males" , testDomain);
		final Set female = addDisjunctSemanticIdentitySet("female", "females" , testDomain);

		sex.addElement(male);
		sex.addElement(female);

		/*
*/

		/*
		arrow(coreGraphs.visibility, entityrelationshipschema, testDomain);
		// TODO: don't allow the creation of visibilities with target within a semantic domain such as: F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, entity, sex);

		arrow(coreGraphs.visibility, RepositoryStructure.applicationengineering, testDomain);
		arrow(coreGraphs.visibility, crm, testDomain);
		arrow(coreGraphs.visibility, crm_product, testDomain);
		*/
		arrow(coreGraphs.visibility, hierarchicalerschema, testDomain);

		// EXAMPLE CODE FOR DEFINING DERIVEDARTEFACTS

		final Set sqltabledefinition = entityrelationshipschema.addConcrete(InstanceDerivation.derivedFile,
				addDisjunctSemanticIdentitySet("sql table definition", "sql table definitions" , testDomain));
		final Set userviewdefinition = entityrelationshipschema.addConcrete(InstanceDerivation.derivedFile,
				addDisjunctSemanticIdentitySet("user view definition", "user view definitions" , testDomain));

		// FURTHER STUFF

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
		final Set order = crm.addConcrete(entity,
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
	  	final Set product_to_price = arrow(entity_to_attribute,
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

		if (entity.allowableEdgeCategories(attribute).size() < 2) {
			raiseError();
		}

	}

}
