package org.s23m.cell.kernel.artifactinstantiation;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Instantiation.addSemanticDomain;
import static org.s23m.cell.api.Instantiation.arrow;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.Transaction;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.EnterpriseArchitecture;

public class EventHandling extends AbstractInstantiationSequence {

	@Override
	protected void executeInstantiationSequence() {
		try {
			addSubscriptions();

			// Event #1 , triggered by "addEvent" in inMemorySets()
			//final Set testDomain = addSemanticDomain("test domain", "test domains", S23MSemanticDomains.agentSemanticDomains);
			final Set testDomain = addSemanticDomain("test domain", "test domains", S23MSemanticDomains.sandboxSemanticDomains);

			// Event #2 .. #5  , triggered by "addEvent" in inMemorySets : 1 new SemanticIdentity, 1 new Edge, and 2 new EdgeEnds
			// Event #6  , triggered by "addEvent" in who.instanceSet : 1 new Edge
			final Set l = arrow(coreGraphs.edge, addDisjunctSemanticIdentitySet("who to who", "set of who to who", testDomain),
					EnterpriseArchitecture.who, EnterpriseArchitecture.who,
					S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE,
					EnterpriseArchitecture.who, EnterpriseArchitecture.who,
					S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);

			// decommission generates no events, since no elements are removed from any sets
			l.decommission();

			// Event #7 , triggered by "commitChangedSets()", which removes the added edge from who.instanceSet
			// notice that the edge and the edge ends still remain in-memory, therefore no events on inMemorySets!
			Transaction.commitChangedSets();

		} finally {
			removeSubscriptions();
		}

		if (setMaintenanceEvents.size() != 7) {
			raiseError();
		}
	}

	private void addSubscriptions() {
		// example of adding a subscriber to the inMemorySets
		Query.inMemorySets().addSubscriber(this);
		// example of adding a subscriber to the instance set of the container of "who"
		EnterpriseArchitecture.who.container().filterInstances().addSubscriber(this);
	}

	private void removeSubscriptions() {
		EnterpriseArchitecture.who.container().filterInstances().removeSubscriber(this);
		Query.inMemorySets().removeSubscriber(this);
	}
}