package org.s23m.cell.kernel.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.s23m.cell.Set;
import org.s23m.cell.api.EventListener;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;

public abstract class S23MTestCase extends TestCase implements EventListener {

	private static boolean kernelHasBooted = false;

	protected static InstantiationData testData;

	protected static InstantiationSequences instantiationSequences;

	protected final List<Set> setMaintenanceEvents;

	public S23MTestCase() {
		this.setMaintenanceEvents = new ArrayList<Set>();
	}

	@Override
	public void setUp() {
		if (!kernelHasBooted) {
			org.s23m.cell.S23MKernel.boot();
			//org.s23m.cell.platform.S23MPlatform.bootTemplate();
			instantiationSequences = InstantiationSequences.getInstance();
			testData = new InstantiationData(instantiationSequences);

			kernelHasBooted = true;
		}

		this.setMaintenanceEvents.clear();
	}

	@Test
	public void testInstantiationSequence() {
		executeInstantiationSequence();
		checkForRuntimeErrors();
	}

	protected abstract void executeInstantiationSequence();

	private void checkForRuntimeErrors() {
		final Set runtimeErrors = Query.runtimeErrors();
		if (!runtimeErrors.isEmpty()) {
			final StringBuilder builder = new StringBuilder("The following runtime errors were encountered:\n");
			// TODO improve display of sets
			for (final Set set: runtimeErrors) {
				builder.append(set);
				builder.append("\n");
			}
			fail(builder.toString());
		}
	}

	public void raiseError() {
		Instantiation.raiseError(S23MSemanticDomains.kernelDefect_KernelHasReachedAnIllegalState, S23MSemanticDomains.kernelDefect);
	}

	public Set processEvent(final Set event) {
		setMaintenanceEvents.add(event);
		return event;
	}
}
