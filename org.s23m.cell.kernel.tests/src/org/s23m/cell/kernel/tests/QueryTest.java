package org.s23m.cell.kernel.tests;

import static org.s23m.cell.api.models.GmodelSemanticDomains.is_TRUE;
import static org.s23m.cell.api.models.GmodelSemanticDomains.is_UNKNOWN;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;

public class QueryTest extends GmodelTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set crm_product = InstantiationSequences.crm_product;
		final Set entityrelationshipschema = InstantiationSequences.entityrelationshipschema;

		final boolean t1 = Query.visibility.isEqualToRepresentation(Query.findSet(Query.visibility.identity().uniqueRepresentationReference().toString()));
		final boolean t2 = crm_product.isEqualToRepresentation(Query.findSet(crm_product.identity().uniqueRepresentationReference().toString()));
		final boolean t3 = entityrelationshipschema.isEqualToRepresentation(Query.findSet(entityrelationshipschema.identity().uniqueRepresentationReference().toString()));
		final boolean t4 = GmodelSemanticDomains.maxCardinality_n.isEqualToRepresentation(Query.findSet(GmodelSemanticDomains.maxCardinality_n.identity().uniqueRepresentationReference().toString()));
		final boolean t5 = GmodelSemanticDomains.maxCardinality_n.isEqualToRepresentation(Query.findSet(GmodelSemanticDomains.maxCardinality_n.identity().uniqueRepresentationReference().toString()));
		final boolean t6 = (GmodelSemanticDomains.gmodel.filterInstances().filterByEquivalenceClass(is_UNKNOWN).size() == 0);
		// changed > 25 to > 5
		final boolean t7 = (Query.inMemorySets().filterByLinkedTo(Query.vertex).size() > 5);
		final boolean t71 = (Query.inMemorySets().filter(Query.vertex).size() > 25);
		final boolean t72 = (Query.inMemorySets().filterPolymorphic(Query.vertex).size() > 25);
		final boolean t73 = (Query.inMemorySets().filterFromAndTo().size() > 25);
		final boolean t8 = (GmodelSemanticDomains.gmodel.filterInstances().filterByEquivalenceClass(GmodelSemanticDomains.isAbstract_FALSE).size() == 0);
		final boolean t9 = (GmodelSemanticDomains.gmodel.filterInstances().filterBySemanticIdentity(is_TRUE).size() == 1);

		final boolean result = (t1 && t2 && t3 && t4 && t5 && t6 && t7 && t71 && t72 && t73 && t8 && t9);
		if (!result) {
			raiseError();
		}
	}

}
