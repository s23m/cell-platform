package org.s23m.cell.kernel.artifactinstantiation;

import java.util.ArrayList;
import java.util.List;

import org.s23m.cell.Set;
import org.s23m.cell.api.EventListener;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;

public abstract class AbstractInstantiationSequence implements InstantiationSequence, EventListener {

	private static volatile Boolean kernelHasBooted = false;

	protected static InstantiationData testData;

	protected static InstantiationSequences instantiationSequences;

	protected final List<Set> setMaintenanceEvents;

	public AbstractInstantiationSequence() {
		this.setMaintenanceEvents = new ArrayList<Set>();

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

	public final Set execute() {
		executeInstantiationSequence();
		return Query.runtimeErrors();
	}

	public void raiseError() {
		Instantiation.raiseError(S23MSemanticDomains.kernelDefect_KernelHasReachedAnIllegalState, S23MSemanticDomains.kernelDefect);
	}

	public Set processEvent(final Set event) {
		setMaintenanceEvents.add(event);
		return event;
	}

	protected abstract void executeInstantiationSequence();

}
