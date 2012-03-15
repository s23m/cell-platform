package org.s23m.cell.kernel.tests;

import static org.s23m.cell.api.InformationQualityLogic.and;
import static org.s23m.cell.api.InformationQualityLogic.isGreaterThan;
import static org.s23m.cell.api.InformationQualityLogic.isInformation;
import static org.s23m.cell.api.InformationQualityLogic.isQuality;
import static org.s23m.cell.api.InformationQualityLogic.isSmallerThan;
import static org.s23m.cell.api.InformationQualityLogic.not;
import static org.s23m.cell.api.InformationQualityLogic.or;
import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Query.vertex;
import static org.s23m.cell.api.models.S23MSemanticDomains.is_FALSE;
import static org.s23m.cell.api.models.S23MSemanticDomains.is_NOTAPPLICABLE;
import static org.s23m.cell.api.models.S23MSemanticDomains.is_TRUE;
import static org.s23m.cell.api.models.S23MSemanticDomains.is_UNKNOWN;

import org.s23m.cell.Set;
import org.s23m.cell.api.InformationQualityLogic;
import org.s23m.cell.api.models2.RepositoryStructure;

public class InformationQualityLogicTest extends S23MTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set entity = instantiationSequences.entity;
		final Set testDomain = instantiationSequences.testDomain;
		final Set crm_product = instantiationSequences.crm_product;

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
		final boolean t1 = InformationQualityLogic.is_NOTAPPLICABLE(and(NOTAPPLICABLE, NOTAPPLICABLE));
		final boolean t2 = InformationQualityLogic.is_NOTAPPLICABLE(and(NOTAPPLICABLE, FALSE));
		final boolean t3 = InformationQualityLogic.is_NOTAPPLICABLE(and(NOTAPPLICABLE, UNKNOWN));
		final boolean t4 = InformationQualityLogic.is_NOTAPPLICABLE(and(NOTAPPLICABLE, TRUE));

		final boolean t5 = InformationQualityLogic.is_NOTAPPLICABLE(and(FALSE, NOTAPPLICABLE));
		final boolean t6 = InformationQualityLogic.is_FALSE(and(FALSE, FALSE));
		final boolean t7 = InformationQualityLogic.is_FALSE(and(FALSE, UNKNOWN));
		final boolean t8 = InformationQualityLogic.is_FALSE(and(FALSE, TRUE));

		final boolean t9 = InformationQualityLogic.is_NOTAPPLICABLE(and(UNKNOWN, NOTAPPLICABLE));
		final boolean t10 = InformationQualityLogic.is_FALSE(and(UNKNOWN, FALSE));
		final boolean t11 = InformationQualityLogic.is_UNKNOWN(and(UNKNOWN, UNKNOWN));
		final boolean t12 = InformationQualityLogic.is_UNKNOWN(and(UNKNOWN, TRUE));

		final boolean t13 = InformationQualityLogic.is_NOTAPPLICABLE(and(TRUE, NOTAPPLICABLE));
		final boolean t14 = InformationQualityLogic.is_FALSE(and(TRUE, FALSE));
		final boolean t15 = InformationQualityLogic.is_UNKNOWN(and(TRUE, UNKNOWN));
		final boolean t16 = InformationQualityLogic.is_TRUE(and(TRUE, TRUE));

		final boolean t17 = InformationQualityLogic.is_NOTAPPLICABLE(and(iqLogicTest.filterInstances()));
		// or
		final boolean t18 = InformationQualityLogic.is_NOTAPPLICABLE(or(NOTAPPLICABLE, NOTAPPLICABLE));
		final boolean t19 = InformationQualityLogic.is_FALSE(or(NOTAPPLICABLE, FALSE));
		final boolean t20 = InformationQualityLogic.is_UNKNOWN(or(NOTAPPLICABLE, UNKNOWN));
		final boolean t21 = InformationQualityLogic.is_TRUE(or(NOTAPPLICABLE, TRUE));

		final boolean t22 = InformationQualityLogic.is_FALSE(or(FALSE, NOTAPPLICABLE));
		final boolean t23 = InformationQualityLogic.is_FALSE(or(FALSE, FALSE));
		final boolean t24 = InformationQualityLogic.is_UNKNOWN(or(FALSE, UNKNOWN));
		final boolean t25 = InformationQualityLogic.is_TRUE(or(FALSE, TRUE));

		final boolean t26 = InformationQualityLogic.is_UNKNOWN(or(UNKNOWN, NOTAPPLICABLE));
		final boolean t27 = InformationQualityLogic.is_UNKNOWN(or(UNKNOWN, FALSE));
		final boolean t28 = InformationQualityLogic.is_UNKNOWN(or(UNKNOWN, UNKNOWN));
		final boolean t29 = InformationQualityLogic.is_TRUE(or(UNKNOWN, TRUE));

		final boolean t30 = InformationQualityLogic.is_TRUE(or(TRUE, NOTAPPLICABLE));
		final boolean t31 = InformationQualityLogic.is_TRUE(or(TRUE, FALSE));
		final boolean t32 = InformationQualityLogic.is_TRUE(or(TRUE, UNKNOWN));
		final boolean t33 = InformationQualityLogic.is_TRUE(or(TRUE, TRUE));

		final boolean t34 = InformationQualityLogic.is_TRUE(or(iqLogicTest.filterInstances()));

		// not
		final boolean t35 = InformationQualityLogic.is_UNKNOWN(not(NOTAPPLICABLE));
		final boolean t36 = InformationQualityLogic.is_TRUE(not(FALSE));
		final boolean t37 = InformationQualityLogic.is_NOTAPPLICABLE(not(UNKNOWN));
		final boolean t38 = InformationQualityLogic.is_FALSE(not(TRUE));

		// isQuality
		final boolean t39 = InformationQualityLogic.is_TRUE(isQuality(NOTAPPLICABLE));
		final boolean t40 = InformationQualityLogic.is_FALSE(isQuality(FALSE));
		final boolean t41 = InformationQualityLogic.is_TRUE(isQuality(UNKNOWN));
		final boolean t42 = InformationQualityLogic.is_FALSE(isQuality(TRUE));

		// isInformation
		final boolean t43 = InformationQualityLogic.is_FALSE(isInformation(NOTAPPLICABLE));
		final boolean t44 = InformationQualityLogic.is_TRUE(isInformation(FALSE));
		final boolean t45 = InformationQualityLogic.is_FALSE(isInformation(UNKNOWN));
		final boolean t46 = InformationQualityLogic.is_TRUE(isInformation(TRUE));

		// isSmallerThan
		final boolean t47 = InformationQualityLogic.is_FALSE(isSmallerThan(NOTAPPLICABLE, NOTAPPLICABLE));
		final boolean t48 = InformationQualityLogic.is_TRUE(isSmallerThan(NOTAPPLICABLE, FALSE));
		final boolean t49 = InformationQualityLogic.is_TRUE(isSmallerThan(NOTAPPLICABLE, UNKNOWN));
		final boolean t50 = InformationQualityLogic.is_TRUE(isSmallerThan(NOTAPPLICABLE, TRUE));

		final boolean t51 = InformationQualityLogic.is_FALSE(isSmallerThan(FALSE, NOTAPPLICABLE));
		final boolean t52 = InformationQualityLogic.is_FALSE(isSmallerThan(FALSE, FALSE));
		final boolean t53 = InformationQualityLogic.is_TRUE(isSmallerThan(FALSE, UNKNOWN));
		final boolean t54 = InformationQualityLogic.is_TRUE(isSmallerThan(FALSE, TRUE));

		final boolean t55 = InformationQualityLogic.is_FALSE(isSmallerThan(UNKNOWN, NOTAPPLICABLE));
		final boolean t56 = InformationQualityLogic.is_FALSE(isSmallerThan(UNKNOWN, FALSE));
		final boolean t57 = InformationQualityLogic.is_FALSE(isSmallerThan(UNKNOWN, UNKNOWN));
		final boolean t58 = InformationQualityLogic.is_TRUE(isSmallerThan(UNKNOWN, TRUE));

		final boolean t59 = InformationQualityLogic.is_FALSE(isSmallerThan(TRUE, NOTAPPLICABLE));
		final boolean t60 = InformationQualityLogic.is_FALSE(isSmallerThan(TRUE, FALSE));
		final boolean t61 = InformationQualityLogic.is_FALSE(isSmallerThan(TRUE, UNKNOWN));
		final boolean t62 = InformationQualityLogic.is_FALSE(isSmallerThan(TRUE, TRUE));

		// isGreaterThan
		final boolean t63 = InformationQualityLogic.is_FALSE(isGreaterThan(NOTAPPLICABLE, NOTAPPLICABLE));
		final boolean t64 = InformationQualityLogic.is_FALSE(isGreaterThan(NOTAPPLICABLE, FALSE));
		final boolean t65 = InformationQualityLogic.is_FALSE(isGreaterThan(NOTAPPLICABLE, UNKNOWN));
		final boolean t66 = InformationQualityLogic.is_FALSE(isGreaterThan(NOTAPPLICABLE, TRUE));

		final boolean t67 = InformationQualityLogic.is_TRUE(isGreaterThan(FALSE, NOTAPPLICABLE));
		final boolean t68 = InformationQualityLogic.is_FALSE(isGreaterThan(FALSE, FALSE));
		final boolean t69 = InformationQualityLogic.is_FALSE(isGreaterThan(FALSE, UNKNOWN));
		final boolean t70 = InformationQualityLogic.is_FALSE(isGreaterThan(FALSE, TRUE));

		final boolean t71 = InformationQualityLogic.is_TRUE(isGreaterThan(UNKNOWN, NOTAPPLICABLE));
		final boolean t72 = InformationQualityLogic.is_TRUE(isGreaterThan(UNKNOWN, FALSE));
		final boolean t73 = InformationQualityLogic.is_FALSE(isGreaterThan(UNKNOWN, UNKNOWN));
		final boolean t74 = InformationQualityLogic.is_FALSE(isGreaterThan(UNKNOWN, TRUE));


		final boolean t75 = InformationQualityLogic.is_TRUE(isGreaterThan(TRUE, NOTAPPLICABLE));
		final boolean t76 = InformationQualityLogic.is_TRUE(isGreaterThan(TRUE, FALSE));
		final boolean t77 = InformationQualityLogic.is_TRUE(isGreaterThan(TRUE, UNKNOWN));
		final boolean t78 = InformationQualityLogic.is_FALSE(isGreaterThan(TRUE, TRUE));

		final Set iqLogicTest2 = crm_product.addConcrete(entity, addDisjunctSemanticIdentitySet("IQ-Logic Test2", "set of IQ-Logic Test2s", testDomain));

		iqLogicTest2.addConcrete(entity, NOTAPPLICABLE);
		iqLogicTest2.addConcrete(entity, FALSE);
		iqLogicTest2.addConcrete(entity, UNKNOWN);
		iqLogicTest2.addConcrete(entity, TRUE);

		final boolean t79 = InformationQualityLogic.is_NOTAPPLICABLE(and(iqLogicTest2.filterInstances()));
		final boolean t80 = InformationQualityLogic.is_TRUE(or(iqLogicTest2.filterInstances()));
		//final boolean t81 = InformationQualityLogic.is_NOTAPPLICABLE(and(iqLogicTest2.instanceSet()).transformToOrderedSetOfSemanticIdentities().union(semanticIdentity));
		//final boolean t82 = InformationQualityLogic.is_TRUE(or(iqLogicTest2.instanceSet()));


		final boolean result = (t1 && t2 && t3 && t4 && t5 && t6 && t7 && t8 && t9 &&
							t10 && t11 && t12 && t13 && t14 && t15 && t16 && t17 && t18 && t19 &&
							t20 && t21 && t22 && t23 && t24 && t25 && t26 && t27 && t28 && t29 &&
							t30 && t31 && t32 && t33 && t34 && t35 && t36 && t37 && t38 && t39 &&
							t40 && t41 && t42 && t43 && t44 && t45 && t46 && t47 && t48 && t49 &&
							t50 && t51 && t52 && t53 && t54 && t55 && t56 && t57 && t58 && t59 &&
							t60 && t61 && t62 && t63 && t64 && t65 && t66 && t67 && t68 && t69 &&
							t70 && t71 && t72 && t73 && t74 && t75 && t76 && t77 && t78 &t79 && t80);
		if (!result) {
			raiseError();
		}
	}

}
