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

package org.s23m.cell.editor.semanticdomain.ui.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.logging.Logger;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.editor.semanticdomain.EditorController;
import org.s23m.cell.editor.semanticdomain.data.EdgeData;
import org.s23m.cell.editor.semanticdomain.data.SetData;
import org.s23m.cell.editor.semanticdomain.ui.components.field.ListField;
import org.s23m.cell.editor.semanticdomain.ui.components.field.SetField;
import org.s23m.cell.editor.semanticdomain.ui.components.layout.EdgeCreationFormLayout;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
@SuppressWarnings("serial")
public class EdgeCreationForm extends Form implements PropertyChangeListener {

	public static final String NEW_TARGET_SELECTED = "new target instance selected";

	private static final String BUTTON_BAR_HEIGHT = "25px";

	private final Set sourceInstance;

	private Set targetInstance = null;

	private final EdgeCreationFormLayout containerLayout;

	private ListField metaField;

	private ListField targetField;

	private EdgeData edgeData;

	private final PropertyChangeSupport propertyChangeSupport;

	private static final Logger logger = Logger.getLogger(EdgeCreationForm.class.getCanonicalName());

	public EdgeCreationForm(final Set sourceInstance , final EdgeCreationFormLayout containerLayout) {
		this.sourceInstance = sourceInstance;
		this.containerLayout = containerLayout;
		propertyChangeSupport = new PropertyChangeSupport(this);
		propertyChangeSupport.addPropertyChangeListener(EditorController.getInstance());
		init();
	}

    @Override
    protected void attachField(final Object propertyId, final Field field) {
		// TODO improve this by creating a static data structure and looking up
		// the associated coordinates

		if (propertyId.equals(EdgeData.META_ELEMENT)) {
			containerLayout.addComponent(field, 0, 0, 1, 0);
		} else if (propertyId.equals(EdgeData.EDGE_IDENTITY_INSTANCE)) {
			containerLayout.addComponent(field, 0, 1, 1, 1);
		} else if (propertyId.equals(EdgeData.SOURCE_INSTANCE)) {
			containerLayout.addComponent(field, 0, 2);
		} else if (propertyId.equals(EdgeData.FIRST_SEMANTIC_IDENTITY)) {
			containerLayout.addComponent(field, 0, 3);
		} else if (propertyId.equals(EdgeData.FIRST_MIN_CARDINALITY)) {
			containerLayout.addComponent(field, 0, 4);
		} else if (propertyId.equals(EdgeData.FIRST_MAX_CARDINALITY)) {
			containerLayout.addComponent(field, 0, 5);
		} else if (propertyId.equals(EdgeData.FIRST_IS_NAVIGABLE)) {
			containerLayout.addComponent(field, 0, 6);
		} else if (propertyId.equals(EdgeData.FIRST_IS_CONTAINER)) {
			containerLayout.addComponent(field, 0, 7);
		} else if (propertyId.equals(EdgeData.TARGET_INSTANCE)) {
			containerLayout.addComponent(field, 1, 2);
		} else if (propertyId.equals(EdgeData.SECOND_SEMANTIC_IDENTITY)) {
			containerLayout.addComponent(field, 1, 3);
		} else if (propertyId.equals(EdgeData.SECOND_MIN_CARDINALITY)) {
			containerLayout.addComponent(field, 1, 4);
		} else if (propertyId.equals(EdgeData.SECOND_MAX_CARDINALITY)) {
			containerLayout.addComponent(field, 1, 5);
		} else if (propertyId.equals(EdgeData.SECOND_IS_NAVIGABLE)) {
			containerLayout.addComponent(field, 1, 6);
		} else if (propertyId.equals(EdgeData.SECOND_IS_CONTAINER)) {
			containerLayout.addComponent(field, 1, 7);
		}
	}

	private void init() {
		setReadOnly(true);
		setImmediate(true);
		setWriteThrough(false);
        setInvalidCommitted(false);
		// set up form data binding
        edgeData = new EdgeData(sourceInstance);
		final BeanItem<EdgeData> item = new BeanItem<EdgeData>(edgeData);

		setFormFieldFactory(
				new FormFieldFactory() {

					public Field createField(final Item item, final Object propertyId, final Component uiContext) {
						 final String pid = (String) propertyId;
						 if (EdgeData.getDisplayedInstances().contains(pid)) {
					    	 @SuppressWarnings("unchecked")
							 final Set edge = (Set)item.getItemProperty(propertyId).getValue();
					    	 //see if this set has any associated instances
					    	 if (EdgeData.getInstancesWithAssociatedList().contains(pid)) {
						    	 //Returns a field that shows a list of options
					    		 final BeanItemContainer<SetData> listContainer = new BeanItemContainer<SetData>(SetData.class);
					    		 final List<SetData> assocList = EdgeData.getAssociatedListOf(pid);
						    	 listContainer.addAll(assocList);
						    	 final ListField listField = new ListField(DefaultFieldFactory.createCaptionByPropertyId(pid), "Select...", listContainer);
						    	 return listField;
					    	 } else {
					    		 //Returns a field that shows the name of a set
					    		  if (EdgeData.META_ELEMENT.equals(pid)) {
					    			  final BeanItemContainer<SetData> listContainer = new BeanItemContainer<SetData>(SetData.class);
					    			  final List<SetData> assocList = edgeData.getAllValidEdgeFlavoredInstances(targetInstance);
								      listContainer.addAll(assocList);
								      final ListField listField = new ListField(
								    	 DefaultFieldFactory.createCaptionByPropertyId(pid),
								    	 "Select...", listContainer);
								      metaField = listField;
								      enableOrDisableMetaField(assocList);
								      return listField;
					    		  } else if (EdgeData.TARGET_INSTANCE.equals(pid)) {
					    			  //target field
					    			  final BeanItemContainer<SetData> listContainer = new BeanItemContainer<SetData>(SetData.class);
					    			  final List<SetData> assocList = edgeData.getAllVisibleVertices();
								      listContainer.addAll(assocList);
								      final ListField listField = new ListField(
								    	 DefaultFieldFactory.createCaptionByPropertyId(pid),
								    	 "Select...", listContainer);
								      targetField = listField;
								      return listField;
					    		  } else {
					    			  return new SetField(DefaultFieldFactory.createCaptionByPropertyId(pid),
		    			 					new SetData(edge), true, true);
					    		  }
					    	 }
					     } else {
					    	 final TextField f = new TextField(DefaultFieldFactory.createCaptionByPropertyId(pid));
					    	 f.setReadOnly(true);
					    	 return f;
					     }
					}

				}
		);

		setItemDataSource(item);
		setVisibleItemProperties(EdgeData.getDisplayedInstances());

		targetField.addEventListener(this);

		final HorizontalLayout buttonBarLayout = new HorizontalLayout();
		buttonBarLayout.setSpacing(true);
		buttonBarLayout.setHeight(BUTTON_BAR_HEIGHT);
		getFooter().addComponent(buttonBarLayout);

		final Button okBtn = new Button("OK", new Button.ClickListener() {
			public void buttonClick(final ClickEvent event) {
				try {
				commit();
				processFormData(edgeData);
				} catch (final Throwable th) {}
			}

			private void processFormData(final EdgeData edgeData) {
				final Set edgeIdentity = lookupSet(EdgeData.EDGE_IDENTITY_INSTANCE);

				final Set edgeSet = Instantiation.link(
					lookupSet(EdgeData.META_ELEMENT),
					edgeIdentity,
					lookupSet(EdgeData.FIRST_SEMANTIC_IDENTITY),
					lookupSet(EdgeData.SOURCE_INSTANCE),
					lookupSet(EdgeData.FIRST_MIN_CARDINALITY),
					lookupSet(EdgeData.FIRST_MAX_CARDINALITY),
					lookupSet(EdgeData.FIRST_IS_NAVIGABLE),
					lookupSet(EdgeData.FIRST_IS_CONTAINER),
					lookupSet(EdgeData.SECOND_SEMANTIC_IDENTITY),
					lookupSet(EdgeData.TARGET_INSTANCE),
					lookupSet(EdgeData.SECOND_MIN_CARDINALITY),
					lookupSet(EdgeData.SECOND_MAX_CARDINALITY),
					lookupSet(EdgeData.SECOND_IS_NAVIGABLE),
					lookupSet(EdgeData.SECOND_IS_CONTAINER)
				);

				if (edgeSet.identity().name().equals(edgeIdentity.identity().name())) {
//					propertyChangeSupport.firePropertyChange(
//							EditorEvents.CHANGESET_MODIFIED.getEventName(),
//							null, null);
					((org.s23m.cell.editor.semanticdomain.Editor)getApplication()).updateContainmentTree();
					containerLayout.getParent().closeTab(containerLayout);

				}
				containerLayout.getParent().getMainApplication().getMainWindow().showNotification("Edge created");
			}
		});

		final Button closeBtn = new Button("Cancel", new Button.ClickListener() {
			public void buttonClick(final ClickEvent event) {
				discard();
				containerLayout.getParent().closeTab(containerLayout);
			}
		});
		buttonBarLayout.addComponent(okBtn);
		buttonBarLayout.addComponent(closeBtn);
		buttonBarLayout.setComponentAlignment(closeBtn, Alignment.TOP_RIGHT);
	}

	private Set lookupSet(final String propertyId) {
		return (Set) getField(propertyId).getValue();
	}

	public void propertyChange(final PropertyChangeEvent event) {
		if (event.getPropertyName().equals(ListField.NEW_SET_SELECTED)) {
			targetInstance = (Set) event.getNewValue();
			logger.info("Target instance is now "+targetInstance.identity().name());
			final BeanItemContainer<SetData> listContainer = new BeanItemContainer<SetData>(SetData.class);
			final List<SetData> assocList = edgeData.getAllValidEdgeFlavoredInstances(targetInstance);
			logger.info("Updating the meta instance list");
			for (final SetData s : assocList) {
				logger.info("Adding to the meta-edge list "+s.getSet().identity().name());
			}
		    enableOrDisableMetaField(assocList);
			listContainer.addAll(assocList);
		    metaField.setContainerDataSource(listContainer);
		}
	}

	private void enableOrDisableMetaField(final List<SetData> assocList) {
		if (!assocList.isEmpty()) {
			metaField.setEnabled(true);
		} else {
			metaField.setEnabled(false);
		}
	}
}
