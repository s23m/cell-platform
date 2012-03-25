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
package org.s23m.cell.platform.api;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.api.models2.Visualization;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.F_InstantiationImpl;
import org.s23m.cell.core.Graph;
import org.s23m.cell.impl.SemanticDomainCode;
import org.s23m.cell.platform.models.Agency;
import org.s23m.cell.platform.models.CellEngineering;
import org.s23m.cell.platform.models.CellPlatformDomain;

public class Instantiation {

	/**
	 * QUERIES
	 */
	// TODO this value must be made private, but is currently accessed by a test that is external to the kernel - the test needs to be reworked
	public static final int indexIsNotAvailable = org.s23m.cell.core.F_SemanticStateOfInMemoryModel.indexIsNotAvailable;

	public static Set addAnonymousDisjunctSemanticIdentitySet(final Set semanticDomain) {
		return ((Graph)semanticDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, identityFactory.createAnonymousIdentity(false));
	}

	public static Set addAnonymousSemanticRole(final Set semanticDomain, final Set referencedSemanticIdentity) {
		final Set result = ((Graph)semanticDomain).addConcrete(SemanticDomain.semanticRole, identityFactory.createAnonymousIdentity(false));
		SemanticDomainCode.addSemanticRole(result, referencedSemanticIdentity);
		return result;
	}

	public static Set addDisjunctSemanticIdentitySet(final String name, final String pluralName, final Set semanticDomain) {
		return ((Graph)semanticDomain).addConcrete(SemanticDomain.disjunctSemanticIdentitySet, identityFactory.createIdentity(name, pluralName, Instantiation.indexIsNotAvailable));
	}

	public static Set addAgent(final String name, final String pluralName) {
		final Set sd = org.s23m.cell.api.Instantiation.addSemanticDomain(name, pluralName, S23MSemanticDomains.agentSemanticDomains);
		return  F_Instantiation.instantiateConcrete(F_Instantiation.reuseSemanticIdentity(sd.identity()), Agency.agent);
	}
	public static Set addStage(final String name, final String pluralName, final Set semanticDomain) {
		//if (semanticDomain.category().isSuperSetOf(SemanticDomain.semanticdomain).is_TRUE()) {
		if (SemanticDomain.semanticdomain.isSuperSetOf(semanticDomain.category()).is_TRUE()) {
			final Set sd = org.s23m.cell.api.Instantiation.addSemanticDomain(name, pluralName, semanticDomain);
			final Set t = Instantiation.toAgent(semanticDomain);
			if (t.category().isEqualTo(Agency.agent)) {
				final Set stage = t.addConcrete(Agency.stage, sd);
				Instantiation.addDefaultContainers(stage);
				return stage;
			} else {
				final Set s = Instantiation.toStage(semanticDomain);
				if (s.category().isEqualTo(Agency.stage)) {
					final Set stage = s.addConcrete(Agency.stage, sd);
					Instantiation.addDefaultContainers(stage);
					return stage;
				}
			}
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		} else {
			final Set sd = Instantiation.toSemanticDomain(semanticDomain);
			//if (sd.category().isSuperSetOf(SemanticDomain.semanticdomain).is_TRUE()) {
			if (SemanticDomain.semanticdomain.isSuperSetOf(sd.category()).is_TRUE()) {
				return Instantiation.addStage(name, pluralName, sd);
			} else {
				return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
			}
		}
	}

	private static Set addDefaultContainers(final Set stage) {
		final Set time = org.s23m.cell.platform.api.Instantiation.instantiateConcrete(CellEngineering.timeConsciousness, stage, CellPlatformDomain.time);
		final Set languages = org.s23m.cell.platform.api.Instantiation.instantiateConcrete(CellEngineering.language, stage, CellPlatformDomain.languages);
		final Set licenses = org.s23m.cell.platform.api.Instantiation.instantiateConcrete(CellEngineering.licensing, stage, CellPlatformDomain.licenses);
		final Set terminologies = org.s23m.cell.platform.api.Instantiation.instantiateConcrete(CellEngineering.terminology, stage, CellPlatformDomain.terminologies);
		final Set cellVisualizatons = org.s23m.cell.platform.api.Instantiation.instantiateConcrete(Visualization.graphVisualization, stage, CellPlatformDomain.cellVisualizations);
		// TODO fix up organisation model
		final Set organizations = org.s23m.cell.platform.api.Instantiation.instantiateConcrete(CellEngineering.organization, stage, CellPlatformDomain.organizations);
		final Set cells = org.s23m.cell.platform.api.Instantiation.instantiateConcrete(CellEngineering.cellContent, stage, CellPlatformDomain.cells);

		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, organizations, time);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, cells, languages);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, cells, time);
		org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, cells, terminologies);
	    org.s23m.cell.platform.api.Instantiation.arrow(coreGraphs.visibility, cells, licenses);
		return stage;
	}

	private static Set toSemanticDomain(final Set set) {
		final Set r =  Query.inMemorySets().filterBySemanticIdentity(set).filterPolymorphic(SemanticDomain.semanticdomain);
		if (r.size()== 1) {
			return r.extractFirst();
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}
	private static Set toAgent(final Set semanticDomain) {
		final Set r =  Query.inMemorySets().filterBySemanticIdentity(semanticDomain).filterPolymorphic(Agency.agent);
		if (r.size()== 1) {
			return r.extractFirst();
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}
	private static Set toStage(final Set semanticDomain) {
		final Set r =  Query.inMemorySets().filterBySemanticIdentity(semanticDomain).filterPolymorphic(Agency.stage);
		if (r.size()== 1) {
			return r.extractFirst();
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	public static Set addSemanticIdentitySet(final String name, final String pluralName, final Set semanticDomain) {
		return ((Graph)semanticDomain).addConcrete(SemanticDomain.semanticIdentitySet, identityFactory.createIdentity(name, pluralName, Instantiation.indexIsNotAvailable));
	}

	public static Set addSemanticRole(final String name, final String pluralName, final Set semanticDomain, final Set referencedSemanticIdentity) {
		return F_Instantiation.addSemanticRole(name, pluralName, semanticDomain, referencedSemanticIdentity);
	}

	public static Set instantiateAbstract(final Set category, final Set stage, final Set semanticIdentity) {
		return F_Instantiation.instantiateAbstract(org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(semanticIdentity), stage, category, Agency.stage, Agency.agent);
	}
	public static Set instantiateConcrete(final Set category, final Set stage, final Set semanticIdentity) {
		return F_Instantiation.instantiateConcrete(org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(semanticIdentity), stage, category, Agency.stage, Agency.agent);
	}

	public static Set arrow(final Set category, final Set fromInstance, final Set toInstance) {
		return org.s23m.cell.core.F_Instantiation.arrow(category, fromInstance, toInstance);
	}
	public static Set declareFunction(final Set semanticIdentity, final Set category, final Set parameters) {
		return org.s23m.cell.core.F_InstantiationImpl.declareFunction(semanticIdentity.identity(), category, parameters);
	}

	public static Set arrow(final Set category,
			final Set edgeIdentity,
			final Set firstSemanticIdentity,
			final Set firstOrderedPair,
			final Set firstMinCardinality,
			final Set firstMaxCardinality,
			final Set firstIsNavigable,
			final Set firstIsContainer,
			final Set secondSemanticIdentity,
			final Set secondOrderedPair,
			final Set secondMinCardinality,
			final Set secondMaxCardinality,
			final Set secondIsNavigable,
			final Set secondIsContainer
			) {
		return org.s23m.cell.core.F_Instantiation.arrow(category,
				org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(edgeIdentity),
				org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(firstSemanticIdentity),
				firstOrderedPair,
				firstMinCardinality,
				firstMaxCardinality,
				firstIsNavigable,
				firstIsContainer,
				org.s23m.cell.core.F_Instantiation.reuseSemanticIdentity(secondSemanticIdentity),
				secondOrderedPair,
				secondMinCardinality,
				secondMaxCardinality,
				secondIsNavigable,
				secondIsContainer
				);
	}

	public static Set arrowToEquivalenceClass(final Set newSemanticRole, final Set equivalenceClass) {
		return F_Instantiation.arrowToEquivalenceClass(newSemanticRole, equivalenceClass);
	}

	public static Set raiseError(final Set semanticIdentity, final Set category) {
		return org.s23m.cell.core.F_Instantiation.raiseError(semanticIdentity.identity(), category);
	}

}
