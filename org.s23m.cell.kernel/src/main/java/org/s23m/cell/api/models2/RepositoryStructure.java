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
 * The Original Code is Gmodel.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api.models2;

import static org.s23m.cell.G.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.api.models.Root;

public class RepositoryStructure {

	// STRUCTURE OF SEMANTIC DOMAIN REPOSITORY
	public static final Set integers = Instantiation.addSemanticDomain("integers", "set of integers", GmodelSemanticDomains.infiniteSets);
	public static final Set rationals = Instantiation.addSemanticDomain("rationals", "set of rationals", GmodelSemanticDomains.infiniteSets);
	public static final Set reals = Instantiation.addSemanticDomain("reals", "set of reals", GmodelSemanticDomains.infiniteSets);
	public static final Set complex = Instantiation.addSemanticDomain("complex", "set of complex", GmodelSemanticDomains.infiniteSets);
	public static final Set dates = Instantiation.addSemanticDomain("dates", "set of dates", GmodelSemanticDomains.infiniteSets);
	public static final Set strings = Instantiation.addSemanticDomain("strings", "set of strings", GmodelSemanticDomains.infiniteSets);
	public static final Set htmlRepresentations = Instantiation.addSemanticDomain("htmlRepresentations", "set of htmlRepresentations", GmodelSemanticDomains.finiteSets);

	// STRUCTURE OF MODEL REPOSITORY
	public static final Set domainengineering = Root.models.addConcrete(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("domain engineering", "set of domain engineering", GmodelSemanticDomains.finiteSets));
	public static final Set projectmanagement = Root.models.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("project management", "", GmodelSemanticDomains.finiteSets));
	public static final Set productmanagement = Root.models.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("product management", "", GmodelSemanticDomains.finiteSets));
	public static final Set productlinemanagement = Root.models.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("product line management", "", GmodelSemanticDomains.finiteSets));
	public static final Set graphVisualizations = Root.models.addAbstract(coreGraphs.vertex, Instantiation.addDisjunctSemanticIdentitySet("graph visualizations", "", GmodelSemanticDomains.finiteSets));
	public static final Set applicationengineering = Instantiation.instantiateConcrete(domainengineering, Instantiation.addDisjunctSemanticIdentitySet("application engineering", "", GmodelSemanticDomains.finiteSets));
	public static final Set applicationoperation = Instantiation.instantiateConcrete(applicationengineering, Instantiation.addDisjunctSemanticIdentitySet("application operation", "", GmodelSemanticDomains.finiteSets));

	public static final Set organization = productlinemanagement.addConcrete(EnterpriseArchitecture.who, Instantiation.addDisjunctSemanticIdentitySet("organization", "organizations", GmodelSemanticDomains.finiteSets));
	public static final Set system = productlinemanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("system", "systems", GmodelSemanticDomains.finiteSets));
	public static final Set managedfeature = productlinemanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("managed feature", "managed features", GmodelSemanticDomains.finiteSets));

	public static final Set product = productmanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("product", "products", GmodelSemanticDomains.finiteSets));
	public static final Set productfeedback = productmanagement.addConcrete(EnterpriseArchitecture.what, Instantiation.addDisjunctSemanticIdentitySet("product feedback", "product feedbacks", GmodelSemanticDomains.finiteSets));
	public static final Set usecase = productmanagement.addConcrete(EnterpriseArchitecture.how, Instantiation.addDisjunctSemanticIdentitySet("use case", "use cases",  GmodelSemanticDomains.finiteSets));

	public static final Set timebox = projectmanagement.addConcrete(EnterpriseArchitecture.when, Instantiation.addDisjunctSemanticIdentitySet("timebox", "timeboxes", GmodelSemanticDomains.finiteSets));

	public static void instantiateFeature() {

		// VISIBILITIES WITHIN THE MODEL REPOSITORY
		Instantiation.link(coreGraphs.visibility, Root.models, domainengineering);

		Instantiation.link(coreGraphs.visibility, domainengineering, GmodelSemanticDomains.finiteSets);
		Instantiation.link(coreGraphs.visibility, projectmanagement, GmodelSemanticDomains.finiteSets);

		Instantiation.link(coreGraphs.visibility, domainengineering, productlinemanagement);
		Instantiation.link(coreGraphs.visibility, domainengineering, productmanagement);

		Instantiation.link(coreGraphs.visibility, projectmanagement, productmanagement);
		Instantiation.link(coreGraphs.visibility, productmanagement, productlinemanagement);
		Instantiation.link(coreGraphs.visibility, applicationengineering, productmanagement);
		Instantiation.link(coreGraphs.visibility, applicationengineering, productlinemanagement);
		Instantiation.link(coreGraphs.visibility, applicationengineering, domainengineering);

		Instantiation.link(coreGraphs.visibility, organization, system);
		Instantiation.link(coreGraphs.visibility, system, managedfeature);

		Instantiation.link(coreGraphs.visibility, product, productfeedback);
		Instantiation.link(coreGraphs.visibility, product, usecase);
		Instantiation.link(coreGraphs.visibility, productfeedback, organization);
		Instantiation.link(coreGraphs.visibility, usecase, organization);
		Instantiation.link(coreGraphs.visibility, usecase, managedfeature);

		Instantiation.link(coreGraphs.visibility, timebox, product);

	}

}
