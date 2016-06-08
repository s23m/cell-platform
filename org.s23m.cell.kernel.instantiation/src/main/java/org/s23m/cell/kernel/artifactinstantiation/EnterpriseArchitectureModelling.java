package org.s23m.cell.kernel.artifactinstantiation;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Instantiation.arrow;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.EnterpriseArchitecture;

public class EnterpriseArchitectureModelling extends AbstractInstantiationSequence {

	@Override
	protected void executeInstantiationSequence() {
		final Set acmeEA = instantiationSequences.acmeEA;
		final Set acmeMelbourne = instantiationSequences.acmeMelbourne;
		final Set testDomain = instantiationSequences.testDomain;
		final Set whoToWho = instantiationSequences.whoToWho;

		// instantiation level 1
		final Set person = acmeEA.addConcrete(EnterpriseArchitecture.who,
				addDisjunctSemanticIdentitySet("nonLinearSystem", "persons", testDomain));
		final Set sandbox_organization = acmeEA.addConcrete(EnterpriseArchitecture.who,
				addDisjunctSemanticIdentitySet("sandbox_organization", "sandbox_organizations", testDomain));
	  	// Notice that we can instantiate an edge, this is only because "who" is not only an instance of vertex, but also a subSet of a vertex!
	  	// The enterpriseArchitectureGraph is a true extension of Graph (we remain at instantiation level 0!), and we can refine the Graph semantics as we please
	  	final Set employeesToEmployers = arrow(coreGraphs.edge  , addDisjunctSemanticIdentitySet("employees To Employers", "employees To Employers", testDomain),
				addDisjunctSemanticIdentitySet("employee", "employees", testDomain),
	  			person,
	  			S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE,
				addDisjunctSemanticIdentitySet("employer", "employers", testDomain),
				sandbox_organization,
	  			S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);

	  	// Here we see that we can instantiate the whoToWho as well - this is the easy part
		// just for the fun of it we also create an instance of an edge

	  	arrow(whoToWho, addDisjunctSemanticIdentitySet("fun to more fun", "fun to more fun", testDomain),
	  			addDisjunctSemanticIdentitySet("fun", "fun", testDomain), person,
	  			S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE,
	  			addDisjunctSemanticIdentitySet("more fun", "more fun", testDomain), sandbox_organization,
	  			S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);

	  	// instantiation level 2
		final Set joeBloggs = acmeMelbourne.addConcrete(person, addDisjunctSemanticIdentitySet("Joe Bloggs", "Joe Bloggs", testDomain));
		final Set canberraHQ = acmeMelbourne.addConcrete(sandbox_organization, addDisjunctSemanticIdentitySet("Melbourne HQ", "Melbourne HQ", testDomain));

	  	arrow(employeesToEmployers, addDisjunctSemanticIdentitySet("Business Analyst to Main Employer", "Business Analyst to Main Employer", testDomain),
	  			addDisjunctSemanticIdentitySet("Business Analyst", "Business Analyst", testDomain), joeBloggs,
	  			S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE,
	  			addDisjunctSemanticIdentitySet("main employer", "main employer", testDomain), canberraHQ,
	  			S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

}
