package org.s23m.cell.kernel.tests;

import java.util.ArrayList;
import java.util.List;

import org.s23m.cell.Set;
import org.s23m.cell.api.EventListener;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationData;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequence;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;

import junit.framework.TestCase;

public abstract class S23MTestCase extends TestCase implements EventListener {

	private static volatile Boolean kernelHasBooted = false;

	protected static InstantiationData testData;

	protected static InstantiationSequences instantiationSequences;

	protected final List<Set> setMaintenanceEvents;

	private final InstantiationSequence sequence;

	public S23MTestCase(final InstantiationSequence sequence) {
		this.sequence = sequence;
		this.setMaintenanceEvents = new ArrayList<Set>();
	}

	@Override
	public void setUp() {
		// initialisation must only ever be done once
		if (!kernelHasBooted) {
			synchronized (kernelHasBooted) {
				if (!kernelHasBooted) {
					org.s23m.cell.S23MKernel.boot();
					//org.s23m.cell.platform.S23MPlatform.bootTemplate();
					instantiationSequences = InstantiationSequences.getInstance();
					testData = new InstantiationData(instantiationSequences);

					kernelHasBooted = true;
				}
			}
		}
		this.setMaintenanceEvents.clear();
	}

	public void testInstantiationSequence() {
		final Set runtimeErrors = sequence.execute();
		if (!runtimeErrors.isEmpty()) {
			final StringBuilder builder = new StringBuilder(getClass().getSimpleName() + ": The following runtime errors were encountered:\n");
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
