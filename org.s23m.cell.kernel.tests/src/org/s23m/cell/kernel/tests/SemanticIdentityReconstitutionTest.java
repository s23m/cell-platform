package org.s23m.cell.kernel.tests;

import java.util.UUID;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.serializerinterface.Reconstitution;

public class SemanticIdentityReconstitutionTest extends GmodelTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set crm_product = testData.crm_product;
		final Set product_to_price = testData.product_to_price;

		final Identity semanticIdentityTest1 = Reconstitution.reconstituteIdentity("rat", "rats",
				UUID.fromString("c7c8f8b0-8b22-11df-a4ee-0800200c9a66"),
				UUID.fromString("c7c8f8b1-8b22-11df-a4ee-0800200c9a66"));
		final Identity semanticIdentityTest2 = Reconstitution.reconstituteIdentity("rabbit", "rabbits",
				UUID.fromString("c7c8f8b2-8b22-11df-a4ee-0800200c9a66"),
				UUID.fromString("f87684f0-8b22-11df-a4ee-0800200c9a66"));
		final Identity semanticIdentityTest3 = Reconstitution.reconstituteIdentity("mouse", "mice",
				UUID.fromString(UUID.randomUUID().toString()),
				UUID.fromString(UUID.randomUUID().toString()));
		final Set p1 = Reconstitution.testDeserialisationPrerequisites(crm_product);
		final Set p2 = Reconstitution.testDeserialisationPrerequisites(product_to_price);
	}
}