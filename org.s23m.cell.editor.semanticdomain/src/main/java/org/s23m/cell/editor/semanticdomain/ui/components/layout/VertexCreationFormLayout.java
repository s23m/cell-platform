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
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.ui.components.layout;

import java.beans.PropertyChangeSupport;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.editor.semanticdomain.EditorController;
import org.s23m.cell.editor.semanticdomain.data.InstantiationData;
import org.s23m.cell.editor.semanticdomain.data.SetType;
import org.s23m.cell.editor.semanticdomain.data.TreeNode;
import org.s23m.cell.editor.semanticdomain.ui.components.MultitabPanel;
import org.s23m.cell.editor.semanticdomain.ui.components.TreeActionHandler;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.Action;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class VertexCreationFormLayout extends VerticalLayout {

	private static final String CANCEL_BUTTON_TEXT = "Cancel";

	private static final String OK_BUTTON_TEXT = "OK";

	private static final String FIELD_WIDTH = "80%";

	private static final String BUTTON_BAR_HEIGHT = "25px";

	private static final String INPUT_FORM_WIDTH = "60%";

	private static final String WINDOW_WIDTH = "600px";

	private static final String INSTANCE_INSTANTIATION_MSG = "New Instance has been instantiated.";

	private static final String INSTANCE_ADDITION_MSG = "New instance has been added.";

	private final MultitabPanel parent;

	private final Action action;

	private final Set containerInstance;

	private final Set metaInstance;

	private final PropertyChangeSupport changeSupport;


	public VertexCreationFormLayout(final Set metaInstance, final Set containerInstance, final MultitabPanel parent, final Action action) {
		this.metaInstance = metaInstance;
		this.containerInstance = containerInstance;
		this.parent = parent;
		this.action = action;
		createInputForm();
		changeSupport = new PropertyChangeSupport(this);
		changeSupport.addPropertyChangeListener(EditorController.getInstance());
	}

	private void createInputForm() {
		setCaption(action.getCaption());
		setWidth(WINDOW_WIDTH);

		setMargin(true);
		setSpacing(true);

		final Form inputForm = new Form();
		inputForm.setWidth(INPUT_FORM_WIDTH);
		addComponent(inputForm);
		inputForm.setImmediate(true);
		inputForm.setWriteThrough(false);

		final InstantiationData instData = new InstantiationData(metaInstance, containerInstance.identity().name(), false, "", "");
		final BeanItem<InstantiationData> instItem = new BeanItem<InstantiationData>(instData);
		inputForm.setItemDataSource(instItem);
		inputForm.setVisibleItemProperties(InstantiationData.getDisplayOrder());

		// drop handler
		final DragAndDropWrapper targetWrapper = new DragAndDropWrapper(inputForm);
		targetWrapper.setWidth(WINDOW_WIDTH);
		targetWrapper.setHeight("100%");

		targetWrapper.setDropHandler(new DropHandler() {

			public void drop(final DragAndDropEvent event) {
				final DataBoundTransferable t = (DataBoundTransferable) event
						.getTransferable();
				final TreeNode node = (TreeNode) t.getData("itemId");
				if(SetType.isSemanticDomainNode(node.getSet())) {
					instData.setIdentity(node.getSet());
					inputForm.getField(InstantiationData.NAME).requestRepaint();
					inputForm.getField(InstantiationData.PLURAL_NAME).requestRepaint();
					inputForm.setComponentError(null);
				} else {
					inputForm.getItemDataSource()
						.getItemProperty(InstantiationData.METAINSTANCE)
						.setValue(node.getSet());
					inputForm.getField(InstantiationData.METAINSTANCE_NAME).requestRepaint();

				}
			}

			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}
		});
		addComponent(targetWrapper);

		final HorizontalLayout okbar = new HorizontalLayout();
		okbar.setSpacing(true);
		okbar.setHeight(BUTTON_BAR_HEIGHT);
		inputForm.getFooter().addComponent(okbar);
		for (final String key : InstantiationData.getDisplayOrder()) {
			inputForm.getField(key).setWidth(FIELD_WIDTH);
		}

		final Button okBtn = new Button(OK_BUTTON_TEXT, new Button.ClickListener() {
		    public void buttonClick(final ClickEvent event) {
		    	//create a new instance
		    	inputForm.commit();
		    	if (!checkForEmptyInstances(instData)) {
			    	Set s = null;
			    	String msgCaption = "";

			    	if (action.equals(TreeActionHandler.ACTION_ADD)) {
			    		msgCaption = INSTANCE_ADDITION_MSG;
				    	if (instData.isAbstract()) {;
				    		s = containerInstance.addAbstract(instData.getMetaInstance(), instData.getIdentity());
				    	} else {
				    		s =	containerInstance.addConcrete(instData.getMetaInstance(), instData.getIdentity());
				    	}
			    	} else if (action.equals(TreeActionHandler.ACTION_INSTANTIATE)) {
			    		msgCaption = INSTANCE_INSTANTIATION_MSG;
				    	if (instData.isAbstract()) {
				    		s = Instantiation.instantiateAbstract(metaInstance, instData.getIdentity());
				    	} else {
				    		s = Instantiation.instantiateConcrete(metaInstance, instData.getIdentity());
				    	}
			    	}

			    	if (s != null && s.identity().name().equals(instData.getName())) {
			    		parent.getMainApplication().getMainWindow().showNotification(msgCaption);
						((org.s23m.cell.editor.semanticdomain.Editor)getApplication()).updateContainmentTree();
			    		//changeSupport.firePropertyChange(EditorEvents.CHANGESET_MODIFIED.getEventName(), null, null);
				    	//changeSupport.removePropertyChangeListener(EditorController.getInstance());
				    	parent.closeTab(VertexCreationFormLayout.this);
			    	} else {
			    		parent.getMainApplication().getMainWindow().showNotification("Error", s.identity().name(), Notification.TYPE_ERROR_MESSAGE);
					}
		    	}
		    }

			private boolean checkForEmptyInstances(final InstantiationData instData) {
				boolean gotEmptyValue = false;
				if(instData.getMetaInstance() == null) {
					inputForm.setComponentError(new UserError(DefaultFieldFactory.createCaptionByPropertyId(InstantiationData.METAINSTANCE)+
							" is missing."));
					gotEmptyValue = true;
				}
				if(instData.getIdentity() == null) {
					inputForm.setComponentError(new UserError(DefaultFieldFactory.createCaptionByPropertyId(InstantiationData.IDENTITY)+
						" is missing."));
					gotEmptyValue = true;
				}
				return gotEmptyValue;
			}
		});

		final Button closeBtn = new Button(CANCEL_BUTTON_TEXT, new Button.ClickListener() {
		    public void buttonClick(final ClickEvent event) {
		    	inputForm.discard();
				((org.s23m.cell.editor.semanticdomain.Editor)getApplication()).updateContainmentTree();
		    	//changeSupport.firePropertyChange(EditorEvents.CHANGESET_MODIFIED.getEventName(), null, null);
		    	changeSupport.removePropertyChangeListener(EditorController.getInstance());
		    	parent.closeTab(VertexCreationFormLayout.this);
		    }
		});

		okbar.addComponent(okBtn);
		okbar.addComponent(closeBtn);
		okbar.setComponentAlignment(closeBtn, Alignment.TOP_RIGHT);
	}

}
