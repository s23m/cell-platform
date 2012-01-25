package org.s23m.cell.kernel.tests;

import static org.s23m.cell.G.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.ArtifactDerivation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.core.SemanticIdentityRegistry;
import org.s23m.cell.impl.DerivationCode;

public class ArtefactDerivationTest extends GmodelTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set entityrelationshipschema = instantiationSequences.entityrelationshipschema;
		final Set testDomain = instantiationSequences.testDomain;
		final Set entity = instantiationSequences.entity;
		final SemanticIdentityRegistry path = SemanticIdentityRegistry.somePathInFileSystem;


		final Set sqltabledefinition = entityrelationshipschema.addConcrete(ArtifactDerivation.derivedFile,
				Instantiation.addDisjunctSemanticIdentitySet("sql table definition", "sql table definitions" , testDomain));
		final Set userviewdefinition = entityrelationshipschema.addConcrete(ArtifactDerivation.derivedFile,
				Instantiation.addDisjunctSemanticIdentitySet("user view definition", "user view definitions" , testDomain));

	  	final Set entityTargetLocation = org.s23m.cell.core.F_Instantiation.addDisjunctSemanticIdentitySetInKernel("some_path_in_filesystem", "some_path_in_filesystem" , GmodelSemanticDomains.gmodel, path.ordinal());

	  	ArtifactDerivation.locationFunction.addElement(entityTargetLocation);

		final Set derived_sqltabledefinition_to_entity = Instantiation.link(ArtifactDerivation.derivationRule,
				Instantiation.addDisjunctSemanticIdentitySet("SQL table definition to entity", "SQL table definition to entity", testDomain),
				sqltabledefinition,
				sqltabledefinition,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.isNavigable_FALSE,
				GmodelSemanticDomains.isContainer_FALSE,
				entity,
				entity,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.isNavigable_FALSE,
				GmodelSemanticDomains.isContainer_FALSE
		);
		derived_sqltabledefinition_to_entity.addToValues(ArtifactDerivation.xpand);
		derived_sqltabledefinition_to_entity.addToValues(entityTargetLocation);

		final Set derived_userviewdefinition_to_entity = Instantiation.link(ArtifactDerivation.derivationRule,
				Instantiation.addDisjunctSemanticIdentitySet("user view definition to entity", "user view definition to entity", testDomain),
				Instantiation.addAnonymousDisjunctSemanticIdentitySet(testDomain),
				userviewdefinition,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.isNavigable_FALSE,
				GmodelSemanticDomains.isContainer_FALSE,
				entity,
				entity,
				GmodelSemanticDomains.minCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE,
				GmodelSemanticDomains.isNavigable_FALSE,
				GmodelSemanticDomains.isContainer_FALSE
		);
		derived_userviewdefinition_to_entity.addToValues(ArtifactDerivation.xpand);
		derived_userviewdefinition_to_entity.addToValues(entityTargetLocation);

		// Execute the derivations for sqltabledefinitions
		DerivationCode.execute(derived_sqltabledefinition_to_entity, entityrelationshipschema.filterFlavor(coreGraphs.vertex));
	}

}
