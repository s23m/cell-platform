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

public final class EdgeEnd extends Graph  {

	/* Reify the Gmodel EdgeEnd concept */
	public static final EdgeEnd edgeEnd = new EdgeEnd();
	private Set connectedSet;
	private final Set container;

	protected EdgeEnd(final Set container, final Identity firstSemanticIdentity, final Set vertex) {
		super(firstSemanticIdentity, edgeEnd);
		this.connectedSet = vertex;
		this.container = container;

		if (F_SemanticStateOfInMemoryModel.gmodelSemanticDomainIsInitialized()
				&& !(firstSemanticIdentity.isEqualTo(identityFactory.element()))
				&& !(firstSemanticIdentity.isEqualTo(identityFactory.set()))
				&& !(firstSemanticIdentity.isEqualTo(identityFactory.referencingSemanticRole()))
				&& !(firstSemanticIdentity.isEqualTo(identityFactory.referencedSemanticRole()))
		) {
			Graph.addSetToInMemorySets(this);
		}
	}
	private EdgeEnd() {
		super(identityFactory.edgeEnd());
		this.addToVariables(coreSets.minCardinality);
		//this.addToVariables(coreSets.maxCardinality);
		this.addToVariables(coreSets.isNavigable);
		this.addToVariables(coreSets.isContainer);
		this.addToValues(coreSets.isAbstract_FALSE);
		this.addFlavorQueries();
		this.container = Graph.graph;
	}

	@Override
	public String toString() {
		return this.localVisualRecognitionText();
	}

	@Override
	public Set container() {
		return container;
	}

	public Set connectedSet() {
		return connectedSet;
	}
	@Override
	public Set flavor() {
		return coreGraphs.edgeEnd;
	}
}