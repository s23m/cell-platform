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

package org.s23m.cell.kernel.testbench;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.Root;

public class TestKernel {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		org.s23m.cell.S23MKernel.boot();
		// insert all ad-hoc tests here

		final Set g = coreGraphs.graph;
		final Set kerDef = g.filter(coreSets.kernelDefect);
		final Set semErr = g.filter(coreSets.semanticErr);
		final Set v = coreGraphs.vertex;
		final Set l = coreGraphs.arrow;
		final Set e = coreGraphs.edge;
		final Set viz = coreGraphs.visibility;
		final Set s = coreGraphs.superSetReference;
		final int kernelComplexity = identityFactory.kernelComplexity();
		final Set root = Root.root;
		final Set g0 = g;

	}

}
