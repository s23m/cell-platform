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
 *
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.editor.semanticdomain.ui.components.field.factory;

import java.util.List;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.editor.semanticdomain.data.EdgeData;
import org.s23m.cell.editor.semanticdomain.ui.components.field.EdgeDisplayField;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Field;

public class EdgeListFieldFactory implements ListFieldFactory {

	public Field create(final String title, final List<Set> sets) {
		final BeanItemContainer<EdgeData> container = new BeanItemContainer<EdgeData>(EdgeData.class);
    	for (final Set s : sets) {
    		 final Set connectedEdgeEndFlavored = s.edgeEnds();
    		 final Set firstEdgeEnd = connectedEdgeEndFlavored.extractFirst();
    		 final Set secondEdgeEnd = connectedEdgeEndFlavored.extractLast();

    		 container.addBean(new EdgeData(
				 s.category(),
				 s.from(),
				 s.to(),
				 s,
				 firstEdgeEnd,
				 secondEdgeEnd,
				 firstEdgeEnd.value(S23MSemanticDomains.minCardinality),
				 firstEdgeEnd.value(S23MSemanticDomains.maxCardinality),
				 firstEdgeEnd.value(S23MSemanticDomains.isNavigable),
				 firstEdgeEnd.value(S23MSemanticDomains.isContainer),
				 secondEdgeEnd.value(S23MSemanticDomains.minCardinality),
				 secondEdgeEnd.value(S23MSemanticDomains.maxCardinality),
				 secondEdgeEnd.value(S23MSemanticDomains.isNavigable),
				 secondEdgeEnd.value(S23MSemanticDomains.isContainer)
			));
    	}
    	return new EdgeDisplayField(title, container);
	}

}
