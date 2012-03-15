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
 * Chul Kim
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.ui.components.field;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

import org.s23m.cell.Set;
import org.s23m.cell.editor.semanticdomain.data.SetData;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ListField extends CustomField implements PropertyChangeListener  {

	public static final String NEW_SET_SELECTED = "NEW_SET_SELECTED";
	private final String caption;
	private final BeanItemContainer<SetData> container;
	private final ComboBox selectList;
	private final PropertyChangeSupport pcs;

	public ListField(final String title, final String caption, final BeanItemContainer<SetData> container) {
		pcs = new PropertyChangeSupport(this);
		this.caption = caption;
		this.container = container;
		setRequired(true);
		setRequiredError(title+" must be set.");
		selectList = new ComboBox(title);
		selectList.setImmediate(true);
		init();
	}

	@Override
	public void commit() {
		validate();
	}

	public Container getContainerDataSource() {
		return selectList.getContainerDataSource();
	}

	@SuppressWarnings("rawtypes")
	public Collection getItemIds() {
		return selectList.getItemIds();
	}

	@Override
	public Class<?> getType() {
		return  SetData.class;
	}

	private void init() {
		final VerticalLayout mainLayout	= new VerticalLayout();
   	 	selectList.setInputPrompt(caption);
		selectList.setContainerDataSource(container);
		selectList.setItemCaptionPropertyId(SetData.NAME);

		final Property.ValueChangeListener listener = new Property.ValueChangeListener() {

			public void valueChange(final com.vaadin.data.Property.ValueChangeEvent event) {
		    	if (selectList.getValue() != null) {
					System.out.println("Selected: "+((SetData)selectList.getValue()).getName());
		    		final Set newSet = ((SetData)selectList.getValue()).getSet();
					setValue(newSet);
					pcs.firePropertyChange(NEW_SET_SELECTED, null, newSet);
				}
			}
		};
		selectList.addListener(listener);
		mainLayout.addComponent(selectList);
		setImmediate(true);
		setCompositionRoot(mainLayout);
	}

	public void setContainerDataSource(final Container newDataSource) {
		selectList.setContainerDataSource(newDataSource);
	}

	public void addEventListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void propertyChange(final PropertyChangeEvent event) {
//		if (event.getPropertyName().equals(NEW_SET_SELECTED)) {
//			System.err.println("NEW VALUE "+event.getNewValue() +" from "+selectList.getCaption());
//			final BeanItemContainer<SetData> listContainer = new BeanItemContainer<SetData>(SetData.class);
//			listContainer.addBean(new SetData(Root.root));
//			selectList.setContainerDataSource(listContainer);
//			selectList.setItemCaptionPropertyId(SetData.NAME);
//		}
	}

}
