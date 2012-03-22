/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is S23M.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api.models2;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;

public class RepositoryStructure {

	// STRUCTURE OF SEMANTIC DOMAIN REPOSITORY
	public static final Set integers = Instantiation.addSemanticDomain("integers", "set of integers", S23MSemanticDomains.infiniteSets);
	public static final Set rationals = Instantiation.addSemanticDomain("rationals", "set of rationals", S23MSemanticDomains.infiniteSets);
	public static final Set reals = Instantiation.addSemanticDomain("reals", "set of reals", S23MSemanticDomains.infiniteSets);
	public static final Set complex = Instantiation.addSemanticDomain("complex", "set of complex", S23MSemanticDomains.infiniteSets);
	public static final Set dates = Instantiation.addSemanticDomain("dates", "set of dates", S23MSemanticDomains.infiniteSets);
	public static final Set strings = Instantiation.addSemanticDomain("strings", "set of strings", S23MSemanticDomains.infiniteSets);
	public static final Set htmlRepresentations = Instantiation.addSemanticDomain("htmlRepresentations", "set of htmlRepresentations", S23MSemanticDomains.finiteSets);

	// STRUCTURE OF MODEL REPOSITORY
	//public static final Set domainengineering = Root.models.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("domain engineering", "set of domain engineering", S23MSemanticDomains.finiteSets));
	//public static final Set projectmanagement = Root.models.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("project management", "", S23MSemanticDomains.finiteSets));
	//public static final Set productmanagement = Root.models.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("product management", "", S23MSemanticDomains.finiteSets));
	//public static final Set productlinemanagement = Root.models.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("product line management", "", S23MSemanticDomains.finiteSets));
	//public static final Set graphVisualizations = Root.models.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("graph visualizations", "", S23MSemanticDomains.finiteSets));
	public static final Set domainengineering = Root.sandbox.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("domain engineering", "set of domain engineering", S23MSemanticDomains.finiteSets));
	public static final Set projectmanagement = Root.sandbox.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("project management", "", S23MSemanticDomains.finiteSets));
	public static final Set productmanagement = Root.sandbox.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("product management", "", S23MSemanticDomains.finiteSets));
	public static final Set productlinemanagement = Root.sandbox.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("product line management", "", S23MSemanticDomains.finiteSets));
	public static final Set graphVisualizations = Root.sandbox.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("graph visualizations", "", S23MSemanticDomains.finiteSets));

	// TODO Fix up top level of instantiation to attach to sandbox or to agents!
	public static final Set applicationengineering = Instantiation.instantiateConcrete(domainengineering, Instantiation.addDisjunctSemanticIdentitySet("application engineering", "", S23MSemanticDomains.finiteSets));
	public static final Set applicationoperation = Instantiation.instantiateConcrete(applicationengineering, Instantiation.addDisjunctSemanticIdentitySet("application operation", "", S23MSemanticDomains.finiteSets));
	//public static final Set applicationengineering = Root.sandbox.addConcrete(domainengineering, Instantiation.addDisjunctSemanticIdentitySet("application engineering", "", S23MSemanticDomains.finiteSets));
	//public static final Set applicationoperation = Root.sandbox.addConcrete(applicationengineering, Instantiation.addDisjunctSemanticIdentitySet("application operation", "", S23MSemanticDomains.finiteSets));
	/*
	public static final Set organization = productlinemanagement.addConcrete(EnterpriseArchitecture.who, Instantiation.addDisjunctSemanticIdentitySet("organization", "organizations", S23MSemanticDomains.finiteSets));
	public static final Set system = productlinemanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("system", "systems", S23MSemanticDomains.finiteSets));
	public static final Set managedfeature = productlinemanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("managed feature", "managed features", S23MSemanticDomains.finiteSets));

	public static final Set product = productmanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("product", "products", S23MSemanticDomains.finiteSets));
	public static final Set productfeedback = productmanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("product feedback", "product feedbacks", S23MSemanticDomains.finiteSets));
	public static final Set usecase = productmanagement.addConcrete(EnterpriseArchitecture.how, Instantiation.addDisjunctSemanticIdentitySet("use case", "use cases",  S23MSemanticDomains.finiteSets));

	public static final Set timebox = projectmanagement.addConcrete(EnterpriseArchitecture.when, Instantiation.addDisjunctSemanticIdentitySet("timebox", "timeboxes", S23MSemanticDomains.finiteSets));
	 */
	public static void instantiateFeature() {

		// VISIBILITIES WITHIN THE MODEL REPOSITORY
		//Instantiation.arrow(coreGraphs.visibility, Root.models, domainengineering);
		Instantiation.arrow(coreGraphs.visibility, Root.sandbox, domainengineering);

		Instantiation.arrow(coreGraphs.visibility, domainengineering, S23MSemanticDomains.finiteSets);
		Instantiation.arrow(coreGraphs.visibility, projectmanagement, S23MSemanticDomains.finiteSets);

		Instantiation.arrow(coreGraphs.visibility, domainengineering, productlinemanagement);
		Instantiation.arrow(coreGraphs.visibility, domainengineering, productmanagement);

		Instantiation.arrow(coreGraphs.visibility, projectmanagement, productmanagement);
		Instantiation.arrow(coreGraphs.visibility, productmanagement, productlinemanagement);
		Instantiation.arrow(coreGraphs.visibility, applicationengineering, productmanagement);
		Instantiation.arrow(coreGraphs.visibility, applicationengineering, productlinemanagement);
		Instantiation.arrow(coreGraphs.visibility, applicationengineering, domainengineering);

		/*Instantiation.arrow(coreGraphs.visibility, organization, system);
		Instantiation.arrow(coreGraphs.visibility, system, managedfeature);

		Instantiation.arrow(coreGraphs.visibility, product, productfeedback);
		Instantiation.arrow(coreGraphs.visibility, product, usecase);
		Instantiation.arrow(coreGraphs.visibility, productfeedback, organization);
		Instantiation.arrow(coreGraphs.visibility, usecase, organization);
		Instantiation.arrow(coreGraphs.visibility, usecase, managedfeature);

		Instantiation.arrow(coreGraphs.visibility, timebox, product);
		 */
	}

}
