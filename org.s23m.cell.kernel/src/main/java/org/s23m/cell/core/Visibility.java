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
import org.s23m.cell.SemanticStateOfInMemoryModel;
import org.s23m.cell.Set;

public final class Visibility extends Arrow  {

	/* Reify the S23M Visibility concept */
	public static final Visibility visibility = new Visibility();
	private Set container;
	private Set from;
	private Set to;

	protected Visibility(final Set fromSubGraph, final Set toSubGraph, final Set category) {
		super(identityFactory.createAnonymousIdentity(fromSubGraph.identity().isPartOfKernel()), category);
		this.setFrom(fromSubGraph);
		this.setTo(toSubGraph);
		this.setContainer(fromSubGraph.container());
		((Graph)(this.container())).addToVisibilities(this);
		this.addToValues(coreSets.isAbstract_TRUE);

		Graph.addSetToInMemorySets(this);
		if (SemanticStateOfInMemoryModel.cellEditorIsLive()) {
			((Graph) this.container()).setContainsNewSets(true);
			Graph.addSetToChangedSets(this);
			Graph.addSetToChangedSets(this.container());
		}
	}
	/* only used for reconstitution during deserialisation */
	protected Visibility(final Identity identity, final Set fromSubGraph, final Set toSubGraph, final Set category) {
		super(identity, category);
		this.setFrom(fromSubGraph);
		this.setTo(toSubGraph);
		this.setContainer(fromSubGraph.container());
		((Graph)(this.container())).addToVisibilities(this);
		this.addToValues(coreSets.isAbstract_TRUE);

		Graph.addSetToInMemorySets(this);
		if (SemanticStateOfInMemoryModel.cellEditorIsLive()) {
			((Graph) this.container()).setContainsNewSets(true);
			Graph.addSetToChangedSets(this);
			Graph.addSetToChangedSets(this.container());
		}
	}

	private Visibility() {
		super(identityFactory.visibility());

		this.setContainer(Graph.graph);
		this.addToValues(coreSets.isAbstract_FALSE);
		this.addProperClassQueries();
	}

	@Override
	public String toString() {
		return this.localVisualRecognitionText();
	}

	@Override
	public String localVisualRecognitionText() {
		if (this.category().isEqualTo(this)) {
			return visualRecognitionText() + " : " + visualRecognitionText();
		}
		if (this.isExternal().is_TRUE()) {
			return "(" + this.from().identity().name() + " -V-> "
			+ this.to().visualRecognitionText()  + ") : "
			+ this.category().localVisualRecognitionText();
		} else {
			return "(" + this.from().identity().name() + " -V-> "
			+ this.to().identity().name()  + ") : "
			+ this.category().localVisualRecognitionText();
		}

	}
	@Override
	public String visualRecognitionText() {
		if (this.category().isEqualTo(this)) {
			return this.identity().name();
		} else {
			if (this.isExternal().is_TRUE()) {
				return "(" + this.from().identity().name()
				+ " -V-> " + this.to().visualRecognitionText() + ")."
				+ this.container().visualRecognitionText() ;
			} else {
				return "(" + this.from().identity().name()
				+ " -V-> " + this.to().identity().name()
				+ ")." + this.container().visualRecognitionText() ;
			}
		}
	}
	@Override
	public String fullVisualRecognitionText() {
		return this.visualRecognitionText() + " : " + this.category().visualRecognitionText();
	}

	/* Implementation of semantics */

	@Override
	public Set container() {
		return container;
	}
	private void setContainer(final Set set) {
		this.container = set;
	}
	private void setFrom(final Set fromSubGraph) {
		this.from = fromSubGraph;
	}
	private void setTo(final Set toSubGraph) {
		this.to = toSubGraph;
	}
	@Override
	public Set isExternal() {
		if (!(this.to().container().isEqualTo(this.container()))) {
			return coreSets.is_TRUE;
		} else {
			return coreSets.is_FALSE;
		}
	}
	@Override
	public Set properClass() {
		return coreGraphs.visibility;
	}
	/**
	 * the elements connected to a link
	 */
	@Override
	public Set from() {
		return this.from;
	}
	@Override
	public Set to(){
		return this.to;
	}
	/**
	 * VisibilityFlavor queries
	 */
	@Override
	protected final void addProperClassQueries() {
		super.addProperClassQueries();
		this.addToQueries(coreSets.from);
		this.addToQueries(coreSets.to);
	}
}
