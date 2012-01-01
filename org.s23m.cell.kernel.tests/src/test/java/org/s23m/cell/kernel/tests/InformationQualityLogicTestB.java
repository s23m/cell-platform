package org.s23m.cell.kernel.tests;

import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Query.vertex;
import static org.s23m.cell.api.models.GmodelSemanticDomains.is_FALSE;
import static org.s23m.cell.api.models.GmodelSemanticDomains.is_NOTAPPLICABLE;
import static org.s23m.cell.api.models.GmodelSemanticDomains.is_TRUE;
import static org.s23m.cell.api.models.GmodelSemanticDomains.is_UNKNOWN;

import org.s23m.cell.Set;
import org.s23m.cell.api.models2.RepositoryStructure;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;

public class InformationQualityLogicTestB extends GmodelTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set entity = InstantiationSequences.entity;
		final Set testDomain = InstantiationSequences.testDomain;
		final Set crm_product = InstantiationSequences.crm_product;

		final Set iqLogicTest = RepositoryStructure.domainengineering.addConcrete(vertex, addDisjunctSemanticIdentitySet("IQ-Logic Test", "set of IQ-Logic Tests", testDomain));

		final Set NOTAPPLICABLE = is_NOTAPPLICABLE;
		final Set FALSE = is_FALSE;
		final Set UNKNOWN = is_UNKNOWN;
		final Set TRUE = is_TRUE;

		iqLogicTest.addConcrete(vertex, NOTAPPLICABLE);
		iqLogicTest.addConcrete(vertex, FALSE);
		iqLogicTest.addConcrete(vertex, UNKNOWN);
		iqLogicTest.addConcrete(vertex, TRUE);

		// and
		final boolean t1 = NOTAPPLICABLE.and(NOTAPPLICABLE).is_NOTAPPLICABLE();
		final boolean t2 = NOTAPPLICABLE.and(FALSE).is_NOTAPPLICABLE();
		final boolean t3 = NOTAPPLICABLE.and(UNKNOWN).is_NOTAPPLICABLE();
		final boolean t4 = NOTAPPLICABLE.and(TRUE).is_NOTAPPLICABLE();

		final boolean t5 = FALSE.and(NOTAPPLICABLE).is_NOTAPPLICABLE();
		final boolean t6 = FALSE.and(FALSE).is_FALSE();
		final boolean t7 = FALSE.and(UNKNOWN).is_FALSE();
		final boolean t8 = FALSE.and(TRUE).is_FALSE();

		final boolean t9 = UNKNOWN.and(NOTAPPLICABLE).is_NOTAPPLICABLE();
		final boolean t10 = UNKNOWN.and(FALSE).is_FALSE();
		final boolean t11 = UNKNOWN.and(UNKNOWN).is_UNKNOWN();
		final boolean t12 = UNKNOWN.and(TRUE).is_UNKNOWN();

		final boolean t13 = TRUE.and(NOTAPPLICABLE).is_NOTAPPLICABLE();
		final boolean t14 = TRUE.and(FALSE).is_FALSE();
		final boolean t15 = TRUE.and(UNKNOWN).is_UNKNOWN();
		final boolean t16 = TRUE.and(TRUE).is_TRUE();

		final boolean t17 = iqLogicTest.filterInstances().and().is_NOTAPPLICABLE();
		final boolean t18 = iqLogicTest.filterInstances().and(UNKNOWN).is_NOTAPPLICABLE();

		// or
		final boolean t33 = iqLogicTest.filterInstances().or().is_TRUE();
		final boolean t34 = iqLogicTest.filterInstances().or(UNKNOWN).is_TRUE();

		// not
		final boolean t35 = iqLogicTest.filterInstances().not().and().is_NOTAPPLICABLE();
		final boolean t36 = TRUE.not().is_FALSE();
		final boolean t37 = NOTAPPLICABLE.not().is_UNKNOWN();


		// isQuality
		final boolean t39 = TRUE.isQuality().is_FALSE();
		final boolean t41 = UNKNOWN.isQuality().is_TRUE();

		// isInformation
		final boolean t43 = TRUE.isInformation().is_TRUE();
		final boolean t44 = UNKNOWN.isInformation().is_FALSE();

		final Set iqLogicTest2 = crm_product.addConcrete(entity, addDisjunctSemanticIdentitySet("IQ-Logic Test2", "set of IQ-Logic Test2s", testDomain));

		iqLogicTest2.addConcrete(entity, NOTAPPLICABLE);
		iqLogicTest2.addConcrete(entity, FALSE);
		iqLogicTest2.addConcrete(entity, UNKNOWN);
		iqLogicTest2.addConcrete(entity, TRUE);

		final boolean t79 = iqLogicTest2.filterInstances().and().is_NOTAPPLICABLE();
		final boolean t80 = iqLogicTest2.filterInstances().or().is_TRUE();



		final boolean result = (t1 && t2 && t3 && t4 && t5 && t6 && t7 && t8 && t9 &&
							t10 && t11 && t12 && t13 && t14 && t15 && t16 && t17 &&  t18  &&
							 t33  && t34  && t35 && t36 && t37 &&  t39 &&
						 t41 &&  t43 && t44 &&
							 t79 && t80);
		if (!result) {
			raiseError();
		}
	}

}
