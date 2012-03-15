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

package org.s23m.cell.editor.semanticdomain.ui.components;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.Transaction;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.editor.semanticdomain.Editor;
import org.s23m.cell.editor.semanticdomain.EditorIcon;
import org.s23m.cell.editor.semanticdomain.data.SetType;
import org.s23m.cell.editor.semanticdomain.data.TreeNode;
import org.s23m.cell.editor.semanticdomain.ui.components.layout.EdgeCreationFormLayout;
import org.s23m.cell.editor.semanticdomain.ui.components.layout.IconUploadFormLayout;
import org.s23m.cell.editor.semanticdomain.ui.components.layout.ImpactAnalysisFormLayout;
import org.s23m.cell.editor.semanticdomain.ui.components.layout.LinkCreationFormLayout;
import org.s23m.cell.editor.semanticdomain.ui.components.layout.SemanticInstanceCreationFormLayout;
import org.s23m.cell.editor.semanticdomain.ui.components.layout.VertexCreationFormLayout;

import com.vaadin.event.Action;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class TreeActionHandler implements Action.Handler   {

	public static final Action ACTION_CREATE_VISIBILITY = new Action("Create Visibility");

	public static final Action ACTION_CREATE_SSR = new Action("Create Superset Reference");

	public static final Action ACTION_CREATE_EDGE = new Action("Create Edge");

	public static final Action ACTION_INSTANTIATE = new Action("Instantiate");

	public static final Action ACTION_ADD = new Action("Add");

	public static final Action ACTION_DECOMMISSION = new Action("Decommission");

	public static final Action ACTION_ADD_SEMANTIC_IDENTITY = new Action("Add Semantic Identity");

	public static final Action ACTION_ADD_SEMANTIC_DOMAIN = new Action("Add Semantic Domain");

	public static final Action ACTION_ADD_SEMANTIC_ROLE = new Action("Add Semantic Role");

	public static final Action ACTION_IMPACT_ANALYSIS = new Action("Impact Analysis");

	public static final Action ACTION_ICON_UPLOAD = new Action("Icon Upload");

	public static final Action[] ACTIONS = {
		ACTION_CREATE_VISIBILITY,
		ACTION_CREATE_SSR,
		ACTION_CREATE_EDGE,
		ACTION_INSTANTIATE,
		ACTION_ADD,
		ACTION_DECOMMISSION,
		ACTION_IMPACT_ANALYSIS
	};

	public static final Action[] SEMANTIC_ACTIONS = {
		ACTION_ADD_SEMANTIC_IDENTITY,
		ACTION_ADD_SEMANTIC_DOMAIN,
		ACTION_ADD_SEMANTIC_ROLE,
		ACTION_IMPACT_ANALYSIS
	};

	public static final Action[] SEMANTIC_IDENTITY_ACTIONS = {
		ACTION_ADD_SEMANTIC_IDENTITY,
		ACTION_ADD_SEMANTIC_DOMAIN,
		ACTION_ADD_SEMANTIC_ROLE,
		ACTION_IMPACT_ANALYSIS,
		ACTION_ICON_UPLOAD
	};

	private final Editor mainApp;

    public TreeActionHandler(final Editor mainApp) {
    	this.mainApp = mainApp;
   }

	public Action[] getActions(final Object target, final Object sender) {
		setUpActionIcons();
		if (target instanceof TreeNode) {
			final Set set = ((TreeNode) target).getSet();
			if (SetType.isSemanticIdentity(set)) {
				return SEMANTIC_IDENTITY_ACTIONS;
			} else if (SetType.isSemanticDomainNode(set)) {
				return SEMANTIC_ACTIONS;
			}
		}
		return ACTIONS;
	}

	private void setUpActionIcons() {
		ACTION_CREATE_VISIBILITY.setIcon(EditorIcon.VISIBILITY.getIconImage());
		ACTION_CREATE_SSR.setIcon(EditorIcon.SSR.getIconImage());
		ACTION_CREATE_EDGE.setIcon(EditorIcon.EDGE.getIconImage());
		ACTION_INSTANTIATE.setIcon(EditorIcon.INSTANTIATE.getIconImage());
		ACTION_ADD.setIcon(EditorIcon.ADD.getIconImage());
		ACTION_DECOMMISSION.setIcon(EditorIcon.DELETE.getIconImage());
		ACTION_IMPACT_ANALYSIS.setIcon(EditorIcon.IMPACT_ANALYSIS.getIconImage());
		ACTION_ADD_SEMANTIC_IDENTITY.setIcon(EditorIcon.SEMANTIC_ID.getIconImage());
		ACTION_ADD_SEMANTIC_DOMAIN.setIcon(EditorIcon.SEMANTIC_DOMAIN.getIconImage());
		ACTION_ADD_SEMANTIC_ROLE.setIcon(EditorIcon.SEMANTIC_ROLE.getIconImage());
		// TODO add one for upload action
	}

	public void handleAction(final Action action, final Object sender, final Object target) {
		final TreeNode treeNode = (TreeNode) target;
		if (treeNode != null) {
			final Set set = treeNode.getSet();
			if (action.equals(ACTION_INSTANTIATE)) {
				loadInstantiationForm(set, Root.models);
			} else if (action.equals(ACTION_ADD)) {
				loadInstanceAdditionForm(set);
			} else if (action.equals(ACTION_DECOMMISSION)) {
				attemptToDecommission(set);
			} else if (action.equals(ACTION_CREATE_VISIBILITY)) {
				loadLinkCreationForm(set, action);
			} else if (action.equals(ACTION_CREATE_SSR)) {
				loadLinkCreationForm(set, action);
			} else if (action.equals(ACTION_CREATE_EDGE)) {
				loadEdgeCreationForm(set, action);
			}  else if (action.equals(ACTION_IMPACT_ANALYSIS)) {
				loadImpactAnalysisForm(set, action);
			} else if (action.equals(ACTION_ADD_SEMANTIC_IDENTITY)) {
				loadSemanticIdentityCreationForm(set, action);
			} else if (action.equals(ACTION_ADD_SEMANTIC_DOMAIN)) {
				loadSemanticDomainCreationForm(set, action);
			} else if (action.equals(ACTION_ADD_SEMANTIC_ROLE)) {
				loadSemanticRoleCreationForm(set, action);
			} else if (action.equals(ACTION_ICON_UPLOAD)) {
				loadIconUploadForm(set, action);
			}
		}
	}

	private void loadIconUploadForm(final Set set, final Action action) {
		// TODO improve icon
		addToTab(new IconUploadFormLayout(set, action, mainApp.getMultiTabPanel()), action.getCaption(), EditorIcon.SEMANTIC_ROLE.getIconImage());
	}

	private void loadSemanticRoleCreationForm(final Set set, final Action action) {
		addToTab(
				new SemanticInstanceCreationFormLayout(set, action, mainApp.getMultiTabPanel()), action.getCaption(), EditorIcon.SEMANTIC_ROLE.getIconImage());
	}

	private void loadSemanticDomainCreationForm(final Set set, final Action action) {
		addToTab(
				new SemanticInstanceCreationFormLayout(set, action, mainApp.getMultiTabPanel()), action.getCaption(), EditorIcon.SEMANTIC_DOMAIN.getIconImage());
	}

	private void loadSemanticIdentityCreationForm(final Set set, final Action action) {
		addToTab(
				new SemanticInstanceCreationFormLayout(set, action, mainApp.getMultiTabPanel()), action.getCaption(), EditorIcon.SEMANTIC_ID.getIconImage());
	}

	private void loadLinkCreationForm(final Set set, final Action action) {
		addToTab(
				new LinkCreationFormLayout(set, action, mainApp.getMultiTabPanel()), action.getCaption(), EditorIcon.VISIBILITY.getIconImage());
	}

	private void loadInstanceAdditionForm(final Set instance) {
		addToTab(
				new VertexCreationFormLayout(S23MKernel.coreGraphs.vertex, instance, mainApp.getMultiTabPanel(), ACTION_ADD), ACTION_ADD.getCaption(), EditorIcon.VERTEX.getIconImage());
	}

	private void attemptToDecommission(final Set instance) {
		final Set decommissioningResult = instance.decommission();
		if (decommissioningResult.isEqualTo(S23MKernel.coreSets.successful)) {
			mainApp.getMainWindow().showNotification("Decommissioning was successful");
			// TODO is this required?
			// Do we just enable the commit / rollback buttons instead of automatically committing?
			Transaction.commitChangedSets();
			// TODO update the containment tree

		} else {
			// show failure message
			mainApp.getMainWindow().showNotification("Decommissioning failed:\n" + decommissioningResult);
			// TODO show more user-friendly display names in the message
		}
	}

	private void loadInstantiationForm(final Set metaInstance, final Set artifact) {
		addToTab(
				new VertexCreationFormLayout(metaInstance, artifact, mainApp.getMultiTabPanel(), ACTION_INSTANTIATE), ACTION_INSTANTIATE.getCaption(), EditorIcon.INSTANTIATE.getIconImage());
	}

	private void loadEdgeCreationForm(final Set set, final Action action) {
		addToTab(
				new EdgeCreationFormLayout(set, ACTION_INSTANTIATE, mainApp.getMultiTabPanel()), action.getCaption(), EditorIcon.EDGE.getIconImage());
	}

	private void loadImpactAnalysisForm(final Set set, final Action action) {
		addToTab(
				new ImpactAnalysisFormLayout(set, ACTION_IMPACT_ANALYSIS, mainApp.getMultiTabPanel()), action.getCaption(), EditorIcon.IMPACT_ANALYSIS.getIconImage());
	}

	private void loadIconUploadForm(final Set metaInstance, final Set artifact) {
		addToTab(
				new VertexCreationFormLayout(metaInstance, artifact, mainApp.getMultiTabPanel(), ACTION_INSTANTIATE), ACTION_INSTANTIATE.getCaption(), EditorIcon.INSTANTIATE.getIconImage());
	}

	private void addToTab(final Component component, final String title, final ThemeResource icon) {
		mainApp.getMultiTabPanel().addToTabSheet(component, title, icon);
	}
}
