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

package org.s23m.cell.core;

import static org.s23m.cell.G.coreGraphs;
import static org.s23m.cell.G.coreSets;
import static org.s23m.cell.core.F_Instantiation.identityFactory;

import org.s23m.cell.G;
import org.s23m.cell.Identity;
import org.s23m.cell.SemanticStateOfInMemoryModel;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.SemanticDomain;
import org.s23m.cell.impl.SemanticDomainCode;

public class Vertex extends Graph {

	/* Reify the Gmodel Vertex concept */
	public static final Vertex vertex = new Vertex();
	/* Reify the Gmodel  SemanticIdentity concept */
	private Graph container;

	protected Vertex(final Graph container, final Identity semanticIdentity, final Set category) {
		super(semanticIdentity, category);
		container.addToVertices(this);
		this.setContainer(container);
		Graph.addSetToInMemorySets(this);

		if (SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			(this.container()).setContainsNewSets(true);
			Graph.addSetToChangedSets(this);
			Graph.addSetToChangedSets(this.container());
		}
	}

	private Vertex() {
		super(identityFactory.vertex());
		this.setContainer(Graph.graph);
		// Jorn: added max cardinality variable
		//this.addToVariables(coreSets.maxCardinality);
		this.addToValues(coreSets.isAbstract_FALSE);
		Graph.graph.addToVertices(this);
		this.addFlavorQueries();
		this.addCategoryQueries();
		this.addCategoryCommands();

	}

	/* Implementation of semantics */

	@Override
	public Graph container() {
		return container;
	}
	private void setContainer(final Graph artifact) {
		this.container = artifact;
	}
	@Override
	public Set flavor() {
		return coreGraphs.vertex;
	}
	/**
	 * category commands
	 */
	protected final void addCategoryCommands() {
		if (SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
			if (SemanticDomain.semanticIdentity.isSuperSetOf(this).isEqualTo(G.coreSets.is_TRUE)) {
				this.addToCommands(coreSets.union);
				this.addToCommands(coreSets.intersection);
				this.addToCommands(coreSets.complement);
				this.addToCommands(coreSets.addElement);
				this.addToCommands(coreSets.removeElement);

			}
		}
	}
	/**
	 * category queries
	 */
	protected final void addCategoryQueries() {
		if (SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
			if (SemanticDomain.semanticIdentity.isSuperSetOf(this).isEqualTo(G.coreSets.is_TRUE)) {
				this.addToCommands(coreSets.isElementOf);
			}
		}
	}
	@Override
	public Set addElement(final Set semanticIdentity){
		return SemanticDomainCode.addElement(this, semanticIdentity);
	}
	@Override
	public Set removeElement(final Set semanticIdentity){
		return SemanticDomainCode.removeElement(this, semanticIdentity);

	}
	@Override
	public Set elementsOfSemanticIdentitySet(){
		return F_Query.elementsOfSemanticIdentitySet(this.category(), this);
	}
	@Override
	public Set extractFirst() {
		if (F_SemanticStateOfInMemoryModel.semanticDomainIsInitialized() && this.category().isASemanticIdentity()) {
			this.elementsOfSemanticIdentitySet().extractFirst();
		}
		return this.filterInstances().extractFirst();
	}

	@Override
	public Set extractSecond() {
		if (F_SemanticStateOfInMemoryModel.semanticDomainIsInitialized() && this.category().isASemanticIdentity()) {
			this.elementsOfSemanticIdentitySet().extractSecond();
		}
		return this.filterInstances().extractSecond();
	}

	@Override
	public Set extractLast() {
		if (F_SemanticStateOfInMemoryModel.semanticDomainIsInitialized() && this.category().isASemanticIdentity()) {
			this.elementsOfSemanticIdentitySet().extractLast();
		}
		return this.filterInstances().extractLast();
	}
}
