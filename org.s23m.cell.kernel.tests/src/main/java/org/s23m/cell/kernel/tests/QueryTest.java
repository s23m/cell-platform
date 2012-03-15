package org.s23m.cell.kernel.tests;

import static org.s23m.cell.api.models.S23MSemanticDomains.is_TRUE;
import static org.s23m.cell.api.models.S23MSemanticDomains.is_UNKNOWN;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;

public class QueryTest extends S23MTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set crm_product = instantiationSequences.crm_product;
		final Set entityrelationshipschema = instantiationSequences.entityrelationshipschema;

		final boolean t1 = Query.visibility.isEqualToRepresentation(Query.findSet(Query.visibility.identity().uniqueRepresentationReference().toString()));
		final boolean t2 = crm_product.isEqualToRepresentation(Query.findSet(crm_product.identity().uniqueRepresentationReference().toString()));
		final boolean t3 = entityrelationshipschema.isEqualToRepresentation(Query.findSet(entityrelationshipschema.identity().uniqueRepresentationReference().toString()));
		final boolean t4 = S23MSemanticDomains.maxCardinality_n.isEqualToRepresentation(Query.findSet(S23MSemanticDomains.maxCardinality_n.identity().uniqueRepresentationReference().toString()));
		final boolean t5 = S23MSemanticDomains.maxCardinality_n.isEqualToRepresentation(Query.findSet(S23MSemanticDomains.maxCardinality_n.identity().uniqueRepresentationReference().toString()));
		final boolean t6 = (S23MSemanticDomains.cellKernel.filterInstances().filterByEquivalenceClass(is_UNKNOWN).size() == 0);
		// changed > 25 to > 5
		final boolean t7 = (Query.inMemorySets().filterByLinkedTo(Query.vertex).size() > 5);
		final boolean t71 = (Query.inMemorySets().filter(Query.vertex).size() > 25);
		final boolean t72 = (Query.inMemorySets().filterPolymorphic(Query.vertex).size() > 25);
		final boolean t73 = (Query.inMemorySets().filterFromAndTo().size() > 25);
		final boolean t8 = (S23MSemanticDomains.cellKernel.filterInstances().filterByEquivalenceClass(S23MSemanticDomains.isAbstract_FALSE).size() == 0);
		final boolean t9 = (S23MSemanticDomains.cellKernel.filterInstances().filterBySemanticIdentity(is_TRUE).size() == 1);

		final boolean result = (t1 && t2 && t3 && t4 && t5 && t6 && t7 && t71 && t72 && t73 && t8 && t9);
		if (!result) {
			raiseError();
		}
	}

}
