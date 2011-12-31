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
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.api.models;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.core.Graph;
import org.s23m.cell.core.SemanticIdentityRegistry;

public class Root {

	public static final Set root = ((Graph)coreGraphs.vertex).addConcrete(coreGraphs.vertex, identityFactory.createIdentityInKernel("root", "roots", SemanticIdentityRegistry.root.ordinal()));
	public static final Set timestamp = ((Graph)coreGraphs.vertex).addConcrete(coreGraphs.vertex, identityFactory.createIdentityInKernel("timestamp", "set of timestamps", SemanticIdentityRegistry.timestamp.ordinal()));

	// ALL MODELS ARE BASED ON SEMANTIC DOMAINS

	public static final Set semanticdomains = ((Graph)Root.root).addConcrete(coreGraphs.vertex, identityFactory.createIdentityInKernel("semantic domains", "set of semantic domains", SemanticIdentityRegistry.semanticdomains.ordinal()));
	public static final Set models = ((Graph)Root.root).addConcrete(coreGraphs.vertex, identityFactory.createIdentityInKernel("models", "set of models", SemanticIdentityRegistry.models.ordinal()));
	public static final Set universalartifactengineering = ((Graph)models).addConcrete(coreGraphs.vertex, identityFactory.createIdentityInKernel("universal container engineering", "set of universal container engineering", SemanticIdentityRegistry.universalartifactengineering.ordinal()));

	public static void instantiateFeature() {
		Instantiation.link(coreGraphs.visibility, Root.root, semanticdomains);
		Instantiation.link(coreGraphs.visibility, Root.root, models);

		// MODELS --> SEMANTICDOMAINS
		Instantiation.link(coreGraphs.visibility, models, semanticdomains);

	}

}
