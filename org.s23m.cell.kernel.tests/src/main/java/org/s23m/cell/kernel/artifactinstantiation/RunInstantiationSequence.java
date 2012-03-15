package org.s23m.cell.kernel.artifactinstantiation;

import org.s23m.cell.kernel.tests.ArtefactDerivationTest;
import org.s23m.cell.kernel.tests.EcoreEmulationTest;
import org.s23m.cell.kernel.tests.EnterpriseArchitectureModellingTest;
import org.s23m.cell.kernel.tests.EntityRelationshipModellingTest;
import org.s23m.cell.kernel.tests.EventHandlingTest;
import org.s23m.cell.kernel.tests.S23MTestCase;
import org.s23m.cell.kernel.tests.GraphVisualisationCreationTest;
import org.s23m.cell.kernel.tests.InformationQualityLogicTest;
import org.s23m.cell.kernel.tests.InformationQualityLogicTestB;
import org.s23m.cell.kernel.tests.QueryTest;
import org.s23m.cell.kernel.tests.SemanticIdentityReconstitutionTest;
import org.s23m.cell.kernel.tests.VisualisationExampleTest;

public class RunInstantiationSequence {

	public static void main(final String[] args) {
		run();
	}

	public static void run() {
		new RunInstantiationSequence();
	}

	private RunInstantiationSequence() {
		execute(new ArtefactDerivationTest());
		execute(new EcoreEmulationTest());
		execute(new EnterpriseArchitectureModellingTest());
		execute(new EntityRelationshipModellingTest());
		execute(new GraphVisualisationCreationTest());
		execute(new InformationQualityLogicTest());
		execute(new InformationQualityLogicTestB());
		execute(new QueryTest());
		execute(new SemanticIdentityReconstitutionTest());
		execute(new VisualisationExampleTest());
		execute(new EventHandlingTest());
	}

	private void execute(final S23MTestCase testCase) {
		testCase.setUp();
		testCase.testInstantiationSequence();
	}
}
