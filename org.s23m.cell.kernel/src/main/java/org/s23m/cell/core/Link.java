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

package org.s23m.cell.core;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;

public class Link extends Graph  {

	/* Reify the Gmodel AbstractEdge concept */
	protected static final Link link =  new Link();

	protected Link(final Identity semanticIdentity, final Set category) {
		super(semanticIdentity, category);
	}
	protected Link(final Identity semanticIdentity) {
		super(semanticIdentity);
	}
	private Link() {
		super(identityFactory.link());
		this.addToValues(coreSets.isAbstract_TRUE);
		this.addFlavorQueries();
	}

	/* Implementation of semantics */

	@Override
	public Set container() {
		if (this.isEqualTo(link)) {
			return Graph.graph;
		} else {
			return null; // null case is (and must be) overriden in sub classes
		}
	}
	@Override
	public Set flavor() {
		return coreGraphs.link;
	}

	/**
	 * LinkFlavor queries
	 */
	@Override
	protected void addFlavorQueries() {
		super.addFlavorQueries();
		this.addToQueries(coreSets.from);
		this.addToQueries(coreSets.isExternal);
		this.addToQueries(coreSets.to);
	}
}
