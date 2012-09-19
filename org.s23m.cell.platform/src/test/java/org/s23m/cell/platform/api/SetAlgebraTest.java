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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.api;

import static org.s23m.cell.S23MKernel.coreGraphs;
import junit.framework.TestCase;

import org.s23m.cell.Set;
import org.s23m.cell.api.SetAlgebra;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.models.CellEngineering;
import org.s23m.cell.platform.api.models.Organization;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;

public class SetAlgebraTest extends TestCase {

	private static boolean agencyTestFoundationInitialised = false;

	@Override
	protected void setUp() throws Exception {
		S23MPlatform.boot();
		if (!agencyTestFoundationInitialised) {
			AgencyTestFoundation.instantiateFeature();
			agencyTestFoundationInitialised = true;
		}
	}

	public void testSetAlgebra() {
		final Set root = AgencyTestFoundation.test1.filter(CellEngineering.organization).extractFirst();
		final Set graph = root.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("g", "g", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));

		final Set v1 = createVertex(graph, "v1");
		final Set v2 = createVertex(graph, "v2");
		final Set v3 = createVertex(graph, "v3");
		final Set v4 = createVertex(graph, "v4");
		final Set v5 = createVertex(graph, "v5");
		final Set v6 = createVertex(graph, "v6");
		final Set v7 = createVertex(graph, "v7");
		final Set v8 = createVertex(graph, "v8");

		final Set z15 = addVisibility(v1, v5);
		final Set z16 = addVisibility(v1, v6);

		final Set z36 = addVisibility(v3, v6);
		final Set z38 = addVisibility(v3, v8);

		final Set z45 = addVisibility(v4, v5);

		final Set z52 = addVisibility(v5, v2);
		final Set z57 = addVisibility(v5, v7);
		final Set z58 = addVisibility(v5, v8);

		final Set z59 = addVisibility(v6, v7);
		final Set z68 = addVisibility(v6, v8);

		final Set emptySet = SetAlgebra.anEmptySet();

		Set a = SetAlgebra.anEmptySet();
		a = SetAlgebra.addElementToOrderedSet(a, v1);
		a = SetAlgebra.addElementToOrderedSet(a, v2);
		a = SetAlgebra.addElementToOrderedSet(a, v3);
		a = SetAlgebra.addElementToOrderedSet(a, v4);

		Set b = SetAlgebra.anEmptySet();
		b = SetAlgebra.addElementToOrderedSet(b, v5);
		b = SetAlgebra.addElementToOrderedSet(b, v6);
		b = SetAlgebra.addElementToOrderedSet(b, v7);
		b = SetAlgebra.addElementToOrderedSet(b, v8);

		Set c = SetAlgebra.anEmptySet();
		c = SetAlgebra.addElementToOrderedSet(c, z15);
		c = SetAlgebra.addElementToOrderedSet(c, z16);
		c = SetAlgebra.addElementToOrderedSet(c, z36);
		c = SetAlgebra.addElementToOrderedSet(c, z38);

		Set d = SetAlgebra.anEmptySet();
		d = SetAlgebra.addElementToOrderedSet(d, z45);
		d = SetAlgebra.addElementToOrderedSet(d, z52);
		d = SetAlgebra.addElementToOrderedSet(d, z57);
		d = SetAlgebra.addElementToOrderedSet(d, z58);
		d = SetAlgebra.addElementToOrderedSet(d, z59);
		d = SetAlgebra.addElementToOrderedSet(d, z68);

		assertEquals(true, a.containsRepresentation(v1));
		assertEquals(true, a.containsRepresentation(v2));
		assertEquals(true, a.containsRepresentation(v3));
		assertEquals(true, a.containsRepresentation(v4));
		assertEquals(false, c.containsRepresentation(v1));
		assertEquals(false, c.containsRepresentation(v2));
		assertEquals(false, c.containsRepresentation(v3));
		assertEquals(false, c.containsRepresentation(v4));
		assertEquals(true, a.containsSemanticMatch(v1));
		assertEquals(true, a.containsSemanticMatch(v2));
		assertEquals(true, a.containsSemanticMatch(v3));
		assertEquals(true, a.containsSemanticMatch(v4));
		assertEquals(false, c.containsSemanticMatch(v1));
		assertEquals(false, c.containsSemanticMatch(v2));
		assertEquals(false, c.containsSemanticMatch(v3));
		assertEquals(false, c.containsSemanticMatch(v4));

		final Set e = a.union(c);
		assertEquals(true, e.containsRepresentation(v1));
		assertEquals(true, e.containsRepresentation(v2));
		assertEquals(true, e.containsRepresentation(v3));
		assertEquals(true, e.containsRepresentation(v4));
		assertEquals(true, e.containsRepresentation(z15));
		assertEquals(true, e.containsRepresentation(z16));
		assertEquals(true, e.containsRepresentation(z36));
		assertEquals(true, e.containsRepresentation(z38));

		assertEquals(true, e.complement(a).containsAllRepresentations(c));
		assertEquals(false, e.complement(a).containsAllRepresentations(a));

		assertEquals(true, e.complement(emptySet).containsAllRepresentations(e));
		assertEquals(0, a.complement(a).size());

		assertEquals(0, a.intersection(c).size());
		assertEquals(true, e.intersection(c).containsAllRepresentations(c));
		assertEquals(4, e.intersection(c).size());

	}

	private Set createVertex(final Set container, final String label) {
		return container.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet(label, label, Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
	}

	private Set addEdge(final Set from, final Set to) {
		return Instantiation.arrow(coreGraphs.edge,
			Instantiation.addDisjunctSemanticIdentitySet("test-edge", "test-edge", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)),
			from,
			from,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_FALSE,
			S23MSemanticDomains.isContainer_FALSE,
			to,
			to,
			S23MSemanticDomains.minCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.maxCardinality_NOTAPPLICABLE,
			S23MSemanticDomains.isNavigable_TRUE,
			S23MSemanticDomains.isContainer_FALSE
		);
	}
	private Set addVisibility(final Set from, final Set to) {
		return Instantiation.arrow(coreGraphs.visibility,
			from,
			to
		);

	}
}