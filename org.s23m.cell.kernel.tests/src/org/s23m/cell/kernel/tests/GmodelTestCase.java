package org.s23m.cell.kernel.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.s23m.cell.Set;
import org.s23m.cell.api.EventListener;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.junit.Test;

public abstract class GmodelTestCase extends TestCase implements EventListener {

	private static boolean kernelHasBooted = false;

	protected static InstantiationData testData;

	protected final List<Set> setMaintenanceEvents = new ArrayList<Set>();

	@Override
	protected void setUp() throws Exception {
		if (!kernelHasBooted) {
			org.s23m.cell.G.boot();
			testData = new InstantiationData();
			kernelHasBooted = true;
		}
	}

	@Test
	public void testInstantiationSequence() {
		this.setMaintenanceEvents.clear();
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
		Instantiation.raiseError(GmodelSemanticDomains.kernelDefect_KernelHasReachedAnIllegalState, GmodelSemanticDomains.kernelDefect);
	}

	public Set processEvent(final Set event) {
		setMaintenanceEvents.add(event);
		return event;
	}
}
