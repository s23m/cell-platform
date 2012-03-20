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

package org.s23m.cell.api.models;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.core.F_Instantiation;
import org.s23m.cell.core.SemanticIdentityRegistry;
import org.s23m.cell.impl.SemanticDomainCode;

/**
 * {@arrow InstanceDerivation} implements all instantiation semantics related to DerivedInstances
 * that must be enforced for all instances (instantiation level n, with n > 0)
 * 
 * The semantics enforced in InstanceDerivation apply to all use cases that involve model transformation and text generation
 * on the basis of instances.
 * 
 */
public final class InstanceDerivation {

	public static final Set file = coreGraphs.vertex.addConcrete(coreGraphs.vertex, F_Instantiation.addDisjunctSemanticIdentitySetInKernel("file", "files", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.file.ordinal()));
	public static final Set derivedFile = coreGraphs.vertex.addConcrete(coreGraphs.vertex, F_Instantiation.addDisjunctSemanticIdentitySetInKernel("derived file", "derived files", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.derivedFile.ordinal()));
	public static final Set derivationTechnology = F_Instantiation.addDisjunctSemanticIdentitySetInKernel("derivation technology", "derivation technology", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.derivationTechnology.ordinal());
	public static final Set xpand = F_Instantiation.addDisjunctSemanticIdentitySetInKernel("xpand", "xpand", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.xpand.ordinal());
	public static final Set locationFunction = F_Instantiation.addDisjunctSemanticIdentitySetInKernel("location function", "location functions", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.locationFunction.ordinal());

	final private static Set file_isSubSetOf_vertex = F_Instantiation.arrow(coreGraphs.superSetReference, file, coreGraphs.vertex);
	final private static Set filederivation = F_Instantiation.arrow(coreGraphs.superSetReference,derivedFile, file);

	public static final Set derivationRule = org.s23m.cell.api.Instantiation.arrow(coreGraphs.edge,
			F_Instantiation.addDisjunctSemanticIdentitySetInKernel("derivation rule", "set of derivation rules", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.derivationRule.ordinal()),
			F_Instantiation.addDisjunctSemanticIdentitySetInKernel("derived instance", "set of derived instances", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.derivedInstance.ordinal()),
			coreGraphs.vertex,
			coreSets.minCardinality_0,
			coreSets.maxCardinality_n,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE,
			F_Instantiation.addDisjunctSemanticIdentitySetInKernel("source instance", "set of source instances", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.sourceInstance.ordinal()),
			coreGraphs.vertex,
			coreSets.minCardinality_1,
			coreSets.maxCardinality_1,
			coreSets.isNavigable_FALSE,
			coreSets.isContainer_FALSE
			);

	/**
	 * execute command
	 */
	public static Set execute = derivationRule.addToCommands(org.s23m.cell.core.F_InstantiationImpl.createFunction(F_Instantiation.addDisjunctSemanticIdentitySetInKernel("execute", "execute", S23MSemanticDomains.cellKernel, SemanticIdentityRegistry.execute.ordinal()).identity(), coreSets.orderedSet, coreSets.commandFunction));

	public static Set instantiateFeature() {
		SemanticDomainCode.addElement(derivationTechnology, xpand);
		derivationRule.addToVariables(derivationTechnology);
		derivationRule.addToVariables(locationFunction);

		return coreGraphs.graph;
	}
}
