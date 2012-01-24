package org.s23m.cell.kernel.tests;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.api.Instantiation.addDisjunctSemanticIdentitySet;
import static org.s23m.cell.api.Instantiation.link;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models2.EnterpriseArchitecture;
import org.s23m.cell.kernel.artifactinstantiation.InstantiationSequences;

public class EnterpriseArchitectureModellingTest extends GmodelTestCase {

	@Override
	protected void executeInstantiationSequence() {
		final Set acmeEA = InstantiationSequences.acmeEA;
		final Set acmeMelbourne = InstantiationSequences.acmeMelbourne;
		final Set testDomain = InstantiationSequences.testDomain;
		final Set whoToWho = InstantiationSequences.whoToWho;

		// instantiation level 1
		final Set person = acmeEA.addConcrete(EnterpriseArchitecture.who,
				addDisjunctSemanticIdentitySet("person", "persons", testDomain));
		final Set organization = acmeEA.addConcrete(EnterpriseArchitecture.who,
				addDisjunctSemanticIdentitySet("organization", "organizations", testDomain));
	  	// Notice that we can instantiate an edge, this is only because "who" is not only an instance of vertex, but also a subSet of a vertex!
	  	// The enterpriseArchitectureGraph is a true extension of Graph (we remain at instantiation level 0!), and we can refine the Graph semantics as we please
	  	final Set employeesToEmployers = link(coreGraphs.edge  , addDisjunctSemanticIdentitySet("employees To Employers", "employees To Employers", testDomain),
				addDisjunctSemanticIdentitySet("employee", "employees", testDomain),
	  			person,
	  			GmodelSemanticDomains.minCardinality_0, GmodelSemanticDomains.maxCardinality_n, GmodelSemanticDomains.isNavigable_TRUE, GmodelSemanticDomains.isContainer_FALSE,
				addDisjunctSemanticIdentitySet("employer", "employers", testDomain),
				organization,
	  			GmodelSemanticDomains.minCardinality_0, GmodelSemanticDomains.maxCardinality_n, GmodelSemanticDomains.isNavigable_TRUE, GmodelSemanticDomains.isContainer_FALSE);

	  	// Here we see that we can instantiate the whoToWho as well - this is the easy part
		// just for the fun of it we also create an instance of an edge

	  	link(whoToWho, addDisjunctSemanticIdentitySet("fun to more fun", "fun to more fun", testDomain),
	  			addDisjunctSemanticIdentitySet("fun", "fun", testDomain), person,
	  			GmodelSemanticDomains.minCardinality_0, GmodelSemanticDomains.maxCardinality_n, GmodelSemanticDomains.isNavigable_TRUE, GmodelSemanticDomains.isContainer_FALSE,
	  			addDisjunctSemanticIdentitySet("more fun", "more fun", testDomain), organization,
	  			GmodelSemanticDomains.minCardinality_0, GmodelSemanticDomains.maxCardinality_n, GmodelSemanticDomains.isNavigable_TRUE, GmodelSemanticDomains.isContainer_FALSE);

	  	// instantiation level 2
		final Set joeBloggs = acmeMelbourne.addConcrete(person, addDisjunctSemanticIdentitySet("Joe Bloggs", "Joe Bloggs", testDomain));
		final Set canberraHQ = acmeMelbourne.addConcrete(organization, addDisjunctSemanticIdentitySet("Melbourne HQ", "Melbourne HQ", testDomain));

	  	link(employeesToEmployers, addDisjunctSemanticIdentitySet("Business Analyst to Main Employer", "Business Analyst to Main Employer", testDomain),
	  			addDisjunctSemanticIdentitySet("Business Analyst", "Business Analyst", testDomain), joeBloggs,
	  			GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_TRUE, GmodelSemanticDomains.isContainer_FALSE,
	  			addDisjunctSemanticIdentitySet("main employer", "main employer", testDomain), canberraHQ,
	  			GmodelSemanticDomains.minCardinality_NOTAPPLICABLE, GmodelSemanticDomains.maxCardinality_NOTAPPLICABLE, GmodelSemanticDomains.isNavigable_TRUE, GmodelSemanticDomains.isContainer_FALSE);
	}

}
