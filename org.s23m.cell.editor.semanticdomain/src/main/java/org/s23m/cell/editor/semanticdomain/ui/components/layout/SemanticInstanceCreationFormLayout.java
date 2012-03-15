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
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.ui.components.layout;

import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.core.F_Transaction;
import org.s23m.cell.editor.semanticdomain.Editor;
import org.s23m.cell.editor.semanticdomain.EditorController;
import org.s23m.cell.editor.semanticdomain.data.SemanticInstanceData;
import org.s23m.cell.editor.semanticdomain.data.TreeNode;
import org.s23m.cell.editor.semanticdomain.ui.components.MultitabPanel;
import org.s23m.cell.editor.semanticdomain.ui.components.TreeActionHandler;

import com.vaadin.data.Item;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.Action;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class SemanticInstanceCreationFormLayout extends VerticalLayout {

	private static final String FULL_SIZE = "100%";

	private static final String INPUT_FORM_WIDTH = "95%";

	private static final String BUTTON_BAR_HEIGHT = "25px";

	private final Set containerInstnace;

	private final Action action;

	private final MultitabPanel parentTab;

	private final PropertyChangeSupport changeSupport;

	private static final Logger log = Logger.getLogger(SemanticInstanceCreationFormLayout.class.getCanonicalName());

	public SemanticInstanceCreationFormLayout(final Set containerInstance, final Action action, final MultitabPanel multiTabPanel) {
		this.containerInstnace = containerInstance;
		this.action = action;
		this.parentTab = multiTabPanel;
		initLayout();
		changeSupport = new PropertyChangeSupport(this);
		//changeSupport.addPropertyChangeListener(EditorController.getInstance());
	}

	private void initLayout() {
		setCaption(action.getCaption());
		setWidth(FULL_SIZE);
		setMargin(true);
		setSpacing(true);

		final Form inputForm = new Form();
		inputForm.setReadOnly(true);
		inputForm.setWidth(INPUT_FORM_WIDTH);
		addComponent(inputForm);
		inputForm.setImmediate(true);
		inputForm.setWriteThrough(false);

		inputForm.setFormFieldFactory(
			new FormFieldFactory() {
				public Field createField(final Item item, final Object propertyId,
						final Component uiContext) {
					final String textLabel = DefaultFieldFactory.createCaptionByPropertyId(propertyId);
			    	 final TextField f = new TextField(textLabel);
					 if (propertyId.toString().equals(SemanticInstanceData.REFERENCED_SEMANTIC_IDENTITY_NAME)) {
						f.setRequiredError(textLabel+" is a mandatory field.");
						f.setRequired(true);
					 }
					 if (action.equals(TreeActionHandler.ACTION_ADD_SEMANTIC_DOMAIN)) {
						 if (propertyId.toString().equals(SemanticInstanceData.NAME)) {
								f.setRequiredError(textLabel+" is a mandatory field.");
								f.setRequired(true);
						 }
					 }
			    	 return f;
				}
		});

		//set up form data binding
		final SemanticInstanceData formData = new SemanticInstanceData("", "", containerInstnace);
		final BeanItem<SemanticInstanceData> item = new BeanItem<SemanticInstanceData>(formData);
		inputForm.setItemDataSource(item);
		if (action.equals(TreeActionHandler.ACTION_ADD_SEMANTIC_ROLE)) {
			inputForm.setVisibleItemProperties(SemanticInstanceData.getDisplayedInstancesForSemanticRole());
		} else {
			inputForm.setVisibleItemProperties(SemanticInstanceData.getDisplayedInstances());
		}

		// drop handler
		final DragAndDropWrapper targetWrapper = new DragAndDropWrapper(inputForm);
		targetWrapper.setWidth(INPUT_FORM_WIDTH);
		targetWrapper.setHeight(INPUT_FORM_WIDTH);

		targetWrapper.setDropHandler(new DropHandler() {

			public void drop(final DragAndDropEvent event) {
				final DataBoundTransferable t = (DataBoundTransferable) event
						.getTransferable();
				final TreeNode node = (TreeNode) t.getData("itemId");
				inputForm.getItemDataSource()
						.getItemProperty(SemanticInstanceData.REFERENCED_SEMANTIC_IDENTITY)
						.setValue(node.getSet());
				inputForm.getField(SemanticInstanceData.REFERENCED_SEMANTIC_IDENTITY_NAME).requestRepaint();
			}

			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}
		});

		addComponent(targetWrapper);

		final HorizontalLayout buttonBarLayout = new HorizontalLayout();
		buttonBarLayout.setSpacing(true);
		buttonBarLayout.setHeight(BUTTON_BAR_HEIGHT);
		inputForm.getFooter().addComponent(buttonBarLayout);

		final Button okBtn = new Button("OK", new Button.ClickListener() {
			public void buttonClick(final ClickEvent event) {
				try {
				if (action.equals(TreeActionHandler.ACTION_ADD_SEMANTIC_IDENTITY)) {
					inputForm.commit();
					if(isAnonymousData(formData)) {
						Instantiation.addAnonymousDisjunctSemanticIdentitySet(formData.getSemanticDomain());
					} else {
						Instantiation.addDisjunctSemanticIdentitySet(
								formData.getName(), formData.getPluralName(),
									formData.getSemanticDomain());
					}
					closeOperation("New semantic identity is created");
				} else if (action.equals(TreeActionHandler.ACTION_ADD_SEMANTIC_DOMAIN)) {
					inputForm.commit();
					if(!isAnonymousData(formData)) {
						Instantiation.addSemanticDomain(
							formData.getName(), formData.getPluralName(),
							formData.getSemanticDomain());
						closeOperation("New semantic domain is created");
					}
				}  else if (action.equals(TreeActionHandler.ACTION_ADD_SEMANTIC_ROLE)) {
					inputForm.commit();
					if (formData.getReferencedSemanticIdentity() != null) {
						F_Transaction.commitChangedSets();
						log.info("Changeset size "+org.s23m.cell.api.Query.changedSets().size());

						if (isAnonymousData(formData)) {
							final Set r = Instantiation.addAnonymousSemanticRole(formData.getSemanticDomain(), formData.getReferencedSemanticIdentity());
							log.info("AnonymousSemanticRole "+r.identity().uniqueRepresentationReference()+" created.");
						} else {
							final Set r = Instantiation.addSemanticRole(formData.getName(), formData.getPluralName(), formData.getSemanticDomain(),
								formData.getReferencedSemanticIdentity());
							log.info("Semantic role "+r.identity().name()+" created.");
						}

						log.info("Changeset size after creation "+org.s23m.cell.api.Query.changedSets().size());
						closeOperation("New semantic role is created");
					} else {
						parentTab.getMainApplication().getMainWindow().showNotification("Missing referenced semantic identity", Window.Notification.TYPE_ERROR_MESSAGE);
					}
				}
				} catch (final EmptyValueException th) {}
			}

			private void closeOperation(final String message) {
				parentTab.getMainApplication().getMainWindow().showNotification(message);
				((Editor)getApplication()).updateContainmentTree();
				//changeSupport.firePropertyChange(EditorEvents.CHANGESET_MODIFIED.getEventName(), null, null);
				//changeSupport.removePropertyChangeListener(EditorController.getInstance());
				parentTab.closeTab(SemanticInstanceCreationFormLayout.this);
			}
		});

		final Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			public void buttonClick(final ClickEvent event) {
				inputForm.discard();
				((org.s23m.cell.editor.semanticdomain.Editor)getApplication()).updateContainmentTree();
				//changeSupport.firePropertyChange(EditorEvents.CHANGESET_MODIFIED.getEventName(), null, null);
				changeSupport.removePropertyChangeListener(EditorController.getInstance());
				parentTab.closeTab(SemanticInstanceCreationFormLayout.this);
			}
		});

		buttonBarLayout.addComponent(okBtn);
		buttonBarLayout.addComponent(cancelBtn);
		buttonBarLayout.setComponentAlignment(cancelBtn, Alignment.TOP_RIGHT);

	}

	private boolean isAnonymousData(final SemanticInstanceData instanceData) {
		if (instanceData.getName().equals("") && instanceData.getPluralName().equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
