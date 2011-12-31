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

import org.s23m.cell.editor.semanticdomain.data.SetData;
import org.s23m.cell.editor.semanticdomain.data.TreeNode;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Property;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SetField extends CustomField {

	private static final String FIELD_WIDTH = "90%";
	private final SetData setData;
	private final String caption ;
	private final boolean acceptDrop;
	private TextField txtField;

	public SetField(final String caption, final SetData setData,final boolean isRequired, final boolean acceptDrop) {
		this.caption = caption;
		this.setData = setData;
		this.acceptDrop = acceptDrop;
		setValue(setData);
		init();
		setRequired(isRequired);
		if (isRequired) {
			setRequiredError(caption+" is a required field.");
		}
	}

	@Override
	public void commit() {
		validate();
	}

	private void init() {
		final VerticalLayout mainLayout	= new VerticalLayout();
		final Label lbl = new Label(caption);
		mainLayout.addComponent(lbl);
		txtField = new TextField();
		txtField.setValue(setData.getName());
		txtField.setWidth(FIELD_WIDTH);
		setImmediate(true);
		setCompositionRoot(mainLayout);

		txtField.addListener(new Property.ValueChangeListener() {
			public void valueChange(
					final com.vaadin.data.Property.ValueChangeEvent event) {
				final String value = (String) txtField.getValue();
		        setValue(value);
			}
		});

		// drop handler
		final DragAndDropWrapper targetWrapper = new DragAndDropWrapper(txtField);
		targetWrapper.setDropHandler(new DropHandler() {

			public void drop(final DragAndDropEvent event) {
				final DataBoundTransferable t = (DataBoundTransferable) event.getTransferable();
				final TreeNode node = (TreeNode) t.getData("itemId");
				setData.setSet(node.getSet());
				txtField.setValue(setData.getName());
				setValue(node.getSet());
				txtField.requestRepaint();
			}

			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}
		});
		mainLayout.addComponent(targetWrapper);
	}

	@Override
	public Class<?> getType() {
		return SetData.class;
	}

}
