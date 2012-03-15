package org.s23m.cell.kernel.tests;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.InstanceDerivation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.core.SemanticIdentityRegistry;
import org.s23m.cell.impl.DerivationCode;

public class ArtefactDerivationTest extends S23MTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set entityrelationshipschema = instantiationSequences.entityrelationshipschema;
		final Set testDomain = instantiationSequences.testDomain;
		final Set entity = instantiationSequences.entity;
		final SemanticIdentityRegistry path = SemanticIdentityRegistry.somePathInFileSystem;


		final Set sqltabledefinition = entityrelationshipschema.addConcrete(InstanceDerivation.derivedFile,
				Instantiation.addDisjunctSemanticIdentitySet("sql table definition", "sql table definitions" , testDomain));
		final Set userviewdefinition = entityrelationshipschema.addConcrete(InstanceDerivation.derivedFile,
				Instantiation.addDisjunctSemanticIdentitySet("user view definition", "user view definitions" , testDomain));

	  	final Set entityTargetLocation = org.s23m.cell.core.F_Instantiation.addDisjunctSemanticIdentitySetInKernel("some_path_in_filesystem", "some_path_in_filesystem" , S23MSemanticDomains.cellKernel, path.ordinal());

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

		// Execute the derivations for sqltabledefinitions
		DerivationCode.execute(derived_sqltabledefinition_to_entity, entityrelationshipschema.filterProperClass(coreGraphs.vertex));
	}

}
