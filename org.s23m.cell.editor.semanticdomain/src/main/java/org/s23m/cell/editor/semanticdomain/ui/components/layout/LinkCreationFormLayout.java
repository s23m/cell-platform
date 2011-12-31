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
import java.util.HashMap;
import java.util.Map;

import org.s23m.cell.G;
import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.editor.semanticdomain.EditorController;
import org.s23m.cell.editor.semanticdomain.data.LinkData;
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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class LinkCreationFormLayout extends VerticalLayout {

	private static final String BUTTON_BAR_HEIGHT = "25px";

	private static final String FIELD_WIDTH = "80%";

	private static final String FULL_SIZE = "100%";

	private static final String INPUT_FORM_WIDTH = "60%";

	private final Action action;

	private final Set fromInstance;

	private final MultitabPanel parent;

	private final PropertyChangeSupport changeSupport;

	public LinkCreationFormLayout(final Set fromInstance, final Action action, final MultitabPanel parent) {
		this.fromInstance = fromInstance;
		this.action = action;
		this.parent = parent;
		changeSupport = new PropertyChangeSupport(this);
		changeSupport.addPropertyChangeListener(EditorController.getInstance());
		initLayout();
	}

	private Map<String, String> addAllVisibleInstanceUUIDs(final Set instance) {
		final Map<String, String> instanceMap = new HashMap<String, String>();
		final Set visibleInstances = instance.container().visibleArtifactsForSubGraph(instance);
		for (final Set set : visibleInstances) {
			if (set.flavor().isEqualTo(G.coreGraphs.vertex)) {
				if (!instanceMap.containsKey(set.identity().uniqueRepresentationReference().toString())) {
					instanceMap.put(set.identity().uniqueRepresentationReference().toString(), "");
				}
				for (final Set subSet : set) {
					if (subSet.flavor().isEqualTo(G.coreGraphs.vertex)) {
						if (!instanceMap.containsKey(subSet.identity().uniqueRepresentationReference().toString())) {
							instanceMap.put(subSet.identity().uniqueRepresentationReference()
									.toString(), "");
						}
					}
				}
			}
		}
		return instanceMap;
	}

	public void initLayout() {
		setCaption(action.getCaption());
		// main window layout
		setWidth(FULL_SIZE);
		setMargin(true);
		setSpacing(true);
		final Form inputForm = new Form();
		inputForm.setReadOnly(true);
		inputForm.setWidth(INPUT_FORM_WIDTH);
		addComponent(inputForm);
		inputForm.setImmediate(true);
		inputForm.setWriteThrough(false);

		// set up form data binding
		final LinkData linkData = new LinkData(fromInstance);
		linkData.setToInstance(G.coreGraphs.vertex); //default target instance
		final BeanItem<LinkData> item = new BeanItem<LinkData>(linkData);
		inputForm.setItemDataSource(item);
		inputForm.setVisibleItemProperties(LinkData.getDisplayedInstances());
		final HorizontalLayout buttonBarLayout = new HorizontalLayout();
		buttonBarLayout.setSpacing(true);
		buttonBarLayout.setHeight(BUTTON_BAR_HEIGHT);
		inputForm.getFooter().addComponent(buttonBarLayout);

		for (final String key : LinkData.getDisplayedInstances()) {
			inputForm.getField(key).setWidth(FIELD_WIDTH);
		}

		// drop handler
		final DragAndDropWrapper targetWrapper = new DragAndDropWrapper(
				inputForm);
		targetWrapper.setWidth(FULL_SIZE);
		targetWrapper.setHeight(FULL_SIZE);

		targetWrapper.setDropHandler(new DropHandler() {

			public void drop(final DragAndDropEvent event) {
				final DataBoundTransferable t = (DataBoundTransferable) event
						.getTransferable();
				final TreeNode node = (TreeNode) t.getData("itemId");
				inputForm.getItemDataSource()
						.getItemProperty(LinkData.TO_INSTANCE)
						.setValue(node.getSet());
				inputForm.getField(LinkData.TARGET).requestRepaint();
			}

			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}
		});
		addComponent(targetWrapper);
		//parent.getMainApplication().getContainmentTreePanel().setEditMode(true);
		//add all visible instances from the fromInstance
		//parent.getMainApplication().getContainmentTreePanel().getScopeMap()
		//		.putAll(addAllVisibleInstanceUUIDs(fromInstance));
		//changeSupport.firePropertyChange(EditorEvents.CHANGESET_MODIFIED.getEventName(), null, null);
		//((org.s23m.cell.editor.semanticdomain.Editor)getApplication()).updateContainmentTree();

		final Button okBtn = new Button("OK", new Button.ClickListener() {
			public void buttonClick(final ClickEvent event) {
				// create a new link instance
				final Set metaInstance = (action.equals(TreeActionHandler.ACTION_CREATE_VISIBILITY)) ? G.coreGraphs.visibility : G.coreGraphs.superSetReference;
				if (action.equals(TreeActionHandler.ACTION_CREATE_VISIBILITY)
						|| action.equals(TreeActionHandler.ACTION_CREATE_SSR)) {
					final Set src = (Set) inputForm.getItemDataSource().getItemProperty(LinkData.FROM_INSTANCE).getValue();
					final Set trgt = (Set) inputForm.getItemDataSource().getItemProperty(LinkData.TO_INSTANCE).getValue();
					if (src != null && trgt != null) {
						final Set v = Instantiation.link(metaInstance, src, trgt);
						String msg = "";
						if (!v.identity().isEqualTo(GmodelSemanticDomains.semanticErr_TargetIsNotWithinVisibility.identity())) {
							inputForm.commit();
							msg = "Successfully created a new link";
							parent.getMainApplication().getMainWindow().showNotification(msg);
							((org.s23m.cell.editor.semanticdomain.Editor)getApplication()).updateContainmentTree();
							parent.closeTab(LinkCreationFormLayout.this);
						} else {
							msg = "Failed creating a visibility link: "
									+ v.identity().name();
							parent.getMainApplication().getMainWindow().showNotification("Error", v.identity().name(), Notification.TYPE_ERROR_MESSAGE);
						}
						//parent.getMainApplication().getConsole().setValue(
						//		parent.getMainApplication().getConsole().getValue() + msg
						//				+ "\n");
					}
				}
				//parent.getMainApplication().getContainmentTreePanel().setEditMode(false);
				//parent.getMainApplication().getContainmentTreePanel().getScopeMap().clear();
				//changeSupport.firePropertyChange(EditorEvents.CHANGESET_MODIFIED.getEventName(), null, null);
			}
		});

		final Button closeBtn = new Button("Cancel",
				new Button.ClickListener() {
					public void buttonClick(final ClickEvent event) {
						inputForm.discard();
						parent.getMainApplication().getContainmentTreePanel().setEditMode(false);
						parent.getMainApplication().getContainmentTreePanel().getScopeMap().clear();
						((org.s23m.cell.editor.semanticdomain.Editor)getApplication()).updateContainmentTree();
						//changeSupport.firePropertyChange(EditorEvents.CHANGESET_MODIFIED.getEventName(), null, null);
						//changeSupport.removePropertyChangeListener(EditorController.getInstance());
						parent.closeTab(LinkCreationFormLayout.this);
					}
				});

		buttonBarLayout.addComponent(okBtn);
		buttonBarLayout.addComponent(closeBtn);
		buttonBarLayout.setComponentAlignment(closeBtn, Alignment.TOP_RIGHT);
	}

}
