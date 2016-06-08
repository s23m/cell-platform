package org.s23m.cell.kernel.artifactinstantiation;

import org.s23m.cell.Set;

public class RunInstantiationSequence {

	public static void run() {
		new RunInstantiationSequence();
	}

	private RunInstantiationSequence() {
		execute(new ArtefactDerivation());
		execute(new EcoreEmulation());
		execute(new EnterpriseArchitectureModelling());
		execute(new EntityRelationshipModelling());
		execute(new GraphVisualisationCreation());
		execute(new InformationQualityLogicSequence1());
		execute(new InformationQualityLogicSequence2());
		execute(new QuerySequence());
		execute(new SemanticIdentityReconstitution());
		execute(new VisualisationExample());

		// there is no corresponding unit test, as this appears to cause
		// sporadic test failures due to kernel constraint violations
		execute(new EventHandling());
	}

	private void execute(final InstantiationSequence sequence) {
		final Set runtimeErrors = sequence.execute();
		if (!runtimeErrors.isEmpty()) {
			final StringBuilder builder = new StringBuilder(getClass().getSimpleName() + ": The following runtime errors were encountered:\n");
			// TODO improve display of sets
			for (final Set set: runtimeErrors) {
				builder.append(set);
				builder.append("\n");
			}
			throw new IllegalStateException(builder.toString());
		}
	}
}
