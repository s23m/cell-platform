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
package org.s23m.cell.editor.semanticdomain.ui.components.field;

import java.util.Collection;

import org.s23m.cell.editor.semanticdomain.data.DetailsData;
import org.s23m.cell.editor.semanticdomain.data.SetData;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class VertexFlavoredSetDisplayField extends CustomField implements Container.Viewer {

	private static final String TABLE_WIDTH = "85%";

	private static final String DEFAULT_COLUMN_HEADER = "Name";

	private final Table table;

	public VertexFlavoredSetDisplayField(final String title, final BeanItemContainer<SetData> container) {
		final Label lblList = new Label(title);
		table = new Table();
		table.setContainerDataSource(container);
		table.setColumnHeader(SetData.NAME, DEFAULT_COLUMN_HEADER);
		table.setVisibleColumns(new String[]{SetData.NAME});
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
