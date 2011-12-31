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
 * Chul Kim
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.ui.components.field;

import java.util.Collection;

import org.s23m.cell.editor.semanticdomain.data.DetailsData;
import org.s23m.cell.editor.semanticdomain.data.EdgeData;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class EdgeDisplayField extends CustomField implements Container.Viewer {

	private static final String TABLE_WIDTH = "85%";

	private static final String DEFAULT_COLUMN_HEADER = "Edge";

	private final Table table;

	public EdgeDisplayField(final String title, final BeanItemContainer<EdgeData> edgeDataContainer) {
		final Label lblList = new Label(title);
		table = new Table();
		table.setContainerDataSource(edgeDataContainer);
		table.setColumnHeader(EdgeData.EDGE_IDENTITY_INSTANCE, DEFAULT_COLUMN_HEADER);
		table.setColumnHeader(EdgeData.SOURCE_INSTANCE, DefaultFieldFactory.createCaptionByPropertyId(EdgeData.SOURCE_INSTANCE));
		table.setColumnHeader(EdgeData.FIRST_MIN_CARDINALITY, "");
		table.setColumnHeader(EdgeData.FIRST_MAX_CARDINALITY, "");
		table.setColumnHeader(EdgeData.TARGET_INSTANCE, DefaultFieldFactory.createCaptionByPropertyId(EdgeData.TARGET_INSTANCE));
		table.setColumnHeader(EdgeData.SECOND_MIN_CARDINALITY, "");
		table.setColumnHeader(EdgeData.SECOND_MAX_CARDINALITY, "");
		table.setVisibleColumns(EdgeData.getDisplayedTableInstances());
		table.setPageLength(5);
		table.setWidth(TABLE_WIDTH);
		table.setSelectable(true);

		final VerticalLayout layout = new VerticalLayout();
		layout.addComponent(lblList);
		layout.addComponent(table);
		setImmediate(true);
		setCompositionRoot(layout);
	}

	@Override
	public Class<?> getType() {
		return DetailsData.class;
	}

	public Container getContainerDataSource() {
		return table.getContainerDataSource();
	}

	public void setContainerDataSource(final Container newDataSource) {
		table.setContainerDataSource(newDataSource);
	}

	public Collection<?> getItemIds() {
		return table.getItemIds();
	}
}
