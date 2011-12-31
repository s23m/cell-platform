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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.editor.semanticdomain.ui.components.field.factory;

import java.util.List;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;
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
				 firstEdgeEnd.value(GmodelSemanticDomains.minCardinality),
				 firstEdgeEnd.value(GmodelSemanticDomains.maxCardinality),
				 firstEdgeEnd.value(GmodelSemanticDomains.isNavigable),
				 firstEdgeEnd.value(GmodelSemanticDomains.isContainer),
				 secondEdgeEnd.value(GmodelSemanticDomains.minCardinality),
				 secondEdgeEnd.value(GmodelSemanticDomains.maxCardinality),
				 secondEdgeEnd.value(GmodelSemanticDomains.isNavigable),
				 secondEdgeEnd.value(GmodelSemanticDomains.isContainer)
			));
    	}
    	return new EdgeDisplayField(title, container);
	}

}
