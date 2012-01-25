package org.s23m.cell.kernel.tests;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Instantiation.link;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.ArtifactDerivation;
import org.s23m.cell.api.models.GmodelSemanticDomains;

public class EntityRelationshipModellingTest extends GmodelTestCase {

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
		link(coreGraphs.visibility, entityrelationshipschema, testDomain);
		// TODO: don't allow the creation of visibilities with target within a semantic domain such as: F_SemanticStateOfInMemoryModel.link(coreGraphs.visibility, entity, sex);

		link(coreGraphs.visibility, RepositoryStructure.applicationengineering, testDomain);
		link(coreGraphs.visibility, crm, testDomain);
		link(coreGraphs.visibility, crm_product, testDomain);
		*/
		link(coreGraphs.visibility, hierarchicalerschema, testDomain);

		// EXAMPLE CODE FOR DEFINING DERIVEDARTEFACTS

		final Set sqltabledefinition = entityrelationshipschema.addConcrete(ArtifactDerivation.derivedFile,
				addDisjunctSemanticIdentitySet("sql table definition", "sql table definitions" , testDomain));
		final Set userviewdefinition = entityrelationshipschema.addConcrete(ArtifactDerivation.derivedFile,
				addDisjunctSemanticIdentitySet("user view definition", "user view definitions" , testDomain));

		// FURTHER STUFF

		final Set attribute = entityrelationshipschema.addConcrete(coreGraphs.vertex,
				addDisjunctSemanticIdentitySet("attribute", "attributes" , testDomain));

		final Set entity_to_sex = link(coreGraphs.edge,
				addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				addDisjunctSemanticIdentitySet("entity to sex", "entity to sex", testDomain),
				entity,
				GmodelSemanticDomains.minCardinality_0,
				GmodelSemanticDomains.maxCardinality_n,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_TRUE,
				sex,
				sex,
				GmodelSemanticDomains.minCardinality_1,
				GmodelSemanticDomains.maxCardinality_1,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE
				);
		final Set entity_to_attribute = link(coreGraphs.edge,
				addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				addDisjunctSemanticIdentitySet("entity to attribute", "entity to attribute", testDomain),
				entity,
				GmodelSemanticDomains.minCardinality_1,
				GmodelSemanticDomains.maxCardinality_1,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_TRUE,
				addDisjunctSemanticIdentitySet("attribute", "attributes" , testDomain),
				attribute,
				GmodelSemanticDomains.minCardinality_0,
				GmodelSemanticDomains.maxCardinality_n,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE
				);
		final Set entity_to_entity = link(coreGraphs.edge,
				addDisjunctSemanticIdentitySet("source", "sources" , testDomain),
				addDisjunctSemanticIdentitySet("entity to entity", "entity to entity", testDomain),
				entity,
				GmodelSemanticDomains.minCardinality_0,
				GmodelSemanticDomains.maxCardinality_n,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE,
				addDisjunctSemanticIdentitySet("target", "targets" , testDomain),
				entity,
				GmodelSemanticDomains.minCardinality_0,
				GmodelSemanticDomains.maxCardinality_n,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE
				);

		// Instantiation level 2

		final Set crm_product_to_sex = link(entity_to_sex,
				addDisjunctSemanticIdentitySet("crm_product_to_sex", "crm_product_to_sex" , testDomain),
				crm_product,
				crm_product,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.isNavigable_NOTAPPLICABLE,
				GmodelSemanticDomains.isContainer_NOTAPPLICABLE,
				male,
				male,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.isNavigable_NOTAPPLICABLE,
				GmodelSemanticDomains.isContainer_NOTAPPLICABLE
				);
		final Set order = crm.addConcrete(entity,
	  			addDisjunctSemanticIdentitySet("order", "orders", testDomain));
	  	final Set price = crm.addConcrete(attribute,
			addDisjunctSemanticIdentitySet("price", "prices" , testDomain));

	  	final Set product_to_order = link(entity_to_entity,
				addDisjunctSemanticIdentitySet("order", "orders" , testDomain),
				addDisjunctSemanticIdentitySet("order to crm product", "order to crm product", testDomain),
				order,
				GmodelSemanticDomains.minCardinality_0,
				GmodelSemanticDomains.maxCardinality_n,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE,
				addDisjunctSemanticIdentitySet("product", "products" , testDomain),
				crm_product,
				GmodelSemanticDomains.minCardinality_0,
				GmodelSemanticDomains.maxCardinality_n,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE
				);
	  	final Set product_to_price = link(entity_to_attribute,
				addDisjunctSemanticIdentitySet("owner", "owners" , testDomain),
				addDisjunctSemanticIdentitySet("crm product to price", "crm product to price", testDomain),
				crm_product,
				GmodelSemanticDomains.minCardinality_1,
				GmodelSemanticDomains.maxCardinality_1,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_TRUE,
				addDisjunctSemanticIdentitySet("price", "prices" , testDomain),
				price,
				GmodelSemanticDomains.minCardinality_0,
				GmodelSemanticDomains.maxCardinality_n,
				GmodelSemanticDomains.isNavigable_TRUE,
				GmodelSemanticDomains.isContainer_FALSE
		);

		if (entity.allowableEdgeCategories(attribute).size() < 2) {
			raiseError();
		}

	}

}
