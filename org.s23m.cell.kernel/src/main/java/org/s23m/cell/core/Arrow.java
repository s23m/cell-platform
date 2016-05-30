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

package org.s23m.cell.core;

import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.S23MKernel.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;

public class Arrow extends Graph  {

	/* Reify the S23M Arrow concept */
	protected static final Arrow arrow =  new Arrow();

	protected Arrow(final Identity semanticIdentity, final Set category) {
		super(semanticIdentity, category);
	}
	protected Arrow(final Identity semanticIdentity) {
		super(semanticIdentity);
	}
	private Arrow() {
		super(identityFactory.arrow());
		this.addToValues(coreSets.isAbstract_TRUE);
		this.addProperClassQueries();
	}

	/* Implementation of semantics */

	@Override
	public Set container() {
		if (this.isEqualTo(arrow)) {
			return Graph.graph;
		} else {
			return null; // null case is (and must be) overriden in sub classes
		}
	}
	@Override
	public Set properClass() {
		return coreGraphs.arrow;
	}

	/**
	 * Arrow queries
	 */
	@Override
	protected void addProperClassQueries() {
		super.addProperClassQueries();
		this.addToQueries(coreSets.from);
		this.addToQueries(coreSets.isExternal);
		this.addToQueries(coreSets.to);
	}
}
