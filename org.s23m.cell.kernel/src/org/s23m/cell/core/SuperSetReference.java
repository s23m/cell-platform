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

import org.s23m.cell.Identity;
import org.s23m.cell.SemanticStateOfInMemoryModel;
import org.s23m.cell.Set;

public final class SuperSetReference extends Link  {

	/* Reify the Gmodel GeneralizationReference concept */
	protected static final SuperSetReference superSetReference = new SuperSetReference();

	/* Instantiate GeneralizationReferences */
	protected static final SuperSetReference graph_SPECIALIZES_property =
		F_InstantiationImpl.createSuperSetReference(Graph.graph, coreSets.orderedPair, superSetReference);

	protected static final SuperSetReference vertex_SPECIALIZES_graph =
		F_InstantiationImpl.createSuperSetReference(Vertex.vertex, Graph.graph, superSetReference);

	protected static final SuperSetReference link_SPECIALIZES_graph =
		F_InstantiationImpl.createSuperSetReference(Link.link, Graph.graph, superSetReference);

	protected static final SuperSetReference edgeEnd_SPECIALIZES_graph =
		F_InstantiationImpl.createSuperSetReference(EdgeEnd.edgeEnd, Graph.graph, superSetReference);

	protected static final SuperSetReference edge_SPECIALIZES_link =
		F_InstantiationImpl.createSuperSetReference(Edge.edge, Link.link, superSetReference);

	protected static final SuperSetReference visibility_SPECIALIZES_link =
		F_InstantiationImpl.createSuperSetReference(Visibility.visibility, Link.link, superSetReference);

	protected static final SuperSetReference superSetReference_SPECIALIZES_link =
		F_InstantiationImpl.createSuperSetReference(SuperSetReference.superSetReference, Link.link, superSetReference);

	private Set container;
	private Set to;
	private Set from;

	private SuperSetReference() {
		super(identityFactory.superSetReference());
		this.setContainer(Graph.graph);
		this.addToValues(coreSets.isAbstract_FALSE);
		this.addFlavorQueries();
	}

	protected SuperSetReference(final Set specialization, final Set generalization, final Set category) {
		super(identityFactory.createAnonymousIdentity(specialization.identity().isPartOfKernel()), category);
		this.setFrom(specialization);
		this.setTo(generalization);
		this.setContainer(specialization.container());
		((Graph) this.container()).addToSuperSetReferences(this);
		// Jorn this.setValue(coreSets.isAbstract_FALSE);
		this.addToValues(coreSets.isAbstract_FALSE);

		Graph.addSetToInMemorySets(this);
		if (SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			((Graph) this.container()).setContainsNewSets(true);
			Graph.addSetToChangedSets(this);
			Graph.addSetToChangedSets(this.container());
		}
	}

	/* only used for reconstitution during deserialisation */
	protected SuperSetReference(final Identity identity, final Set specialization, final Set generalization, final Set category) {
		super(identity, category);
		this.setFrom(specialization);
		this.setTo(generalization);
		this.setContainer(specialization.container());
		((Graph) this.container()).addToSuperSetReferences(this);
		// jorn this.setValue(coreSets.isAbstract_FALSE);
		this.addToValues(coreSets.isAbstract_FALSE);

		Graph.addSetToInMemorySets(this);
		if (SemanticStateOfInMemoryModel.gmodelEditorIsLive()) {
			((Graph) this.container()).setContainsNewSets(true);
			Graph.addSetToChangedSets(this);
			Graph.addSetToChangedSets(this.container());
		}
	}

	@Override
	public String toString() {
		return localVisualRecognitionText();
	}

	@Override
	public String localVisualRecognitionText() {
		if (category().isEqualTo(this)) {
			return visualRecognitionText() + " : " + visualRecognitionText();
		}
		if (isExternal().is_TRUE()) {
			return "(" + from().identity().name() + " -S-> "
			+ to().visualRecognitionText()  + ") : "
			+ category().localVisualRecognitionText();
		} else {
			return "(" + from().identity().name() + " -S-> "
			+ to().identity().name()  + ") : "
			+ category().localVisualRecognitionText();
		}
	}

	@Override
	public String visualRecognitionText() {
		if (category().isEqualTo(this)) {
			return identity().name();
		} else {
			if (isExternal().is_TRUE()) {
				return "(" + from().identity().name() + " -S-> "
				+ to().visualRecognitionText() + ")."
				+ container().visualRecognitionText() ;
			} else {
				return "(" + from().identity().name() + " -S-> "
				+ to().identity().name() + ")."
				+ container().visualRecognitionText();
			}
		}
	}

	@Override
	public String fullVisualRecognitionText() {
		return visualRecognitionText() + " : " + category().visualRecognitionText();
	}


	/* Implementation of semantics */

	@Override
	public Set container() {
		return container;
	}

	private void setContainer(final Set artifact) {
		this.container = artifact;
	}

	private void setFrom(final Set subSet) {
		this.from = subSet;
	}

	protected Set getSuperSet() {
		return to;
	}

	private void setTo(final Set superSet) {
		this.to = superSet;
	}

	@Override
	public Set isExternal() {
		if (getSuperSet().flavor().isEqualTo(coreSets.orderedPair) && !(container().isEqualTo(Graph.graph))) {
			return coreSets.is_TRUE;
		} else if (!(((Graph) getSuperSet()).container().isEqualTo(container()))) {
			return coreSets.is_TRUE;
		} else {
			return coreSets.is_FALSE;
		}
	}

	@Override
	public Set flavor() {
		return coreGraphs.superSetReference;
	}

	/**
	 * the elements connected to a link
	 */
	@Override
	public Set from() {
		return from;
	}

	@Override
	public Set to(){
		return to;
	}

	/**
	 * SuperSetReferenceFlavor queries
	 */
	@Override
	protected final void addFlavorQueries() {
		super.addFlavorQueries();
		addToQueries(coreSets.from);
		addToQueries(coreSets.to);
	}
}
