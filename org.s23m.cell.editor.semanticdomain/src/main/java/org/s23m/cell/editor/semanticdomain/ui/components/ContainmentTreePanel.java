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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.SemanticStateOfInMemoryModel;
import org.s23m.cell.api.Query;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.core.F_Transaction;
import org.s23m.cell.editor.semanticdomain.Editor;
import org.s23m.cell.editor.semanticdomain.EditorIcon;
import org.s23m.cell.editor.semanticdomain.data.DetailsData;
import org.s23m.cell.editor.semanticdomain.data.TreeContainer;
import org.s23m.cell.editor.semanticdomain.data.TreeNode;
import org.s23m.cell.platform.api.models.CellPlatform;
import org.s23m.cell.repository.client.RepositoryClient;
import org.s23m.cell.repository.client.mediator.RepositoryClientMediator;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ArtefactContainer.Content;
import org.s23m.cell.serialization.container.ObjectFactoryHolder;
import org.s23m.cell.serialization.serializer.ArtifactContainerContentMapper;
import org.s23m.cell.serialization.serializer.ProtocolType;
import org.s23m.cell.serialization.serializer.SerializationType;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
public class ContainmentTreePanel extends Panel {

	// TODO remove the need for this system property
	private static final String IN_MEMORY_INSTANCES_PROPERTY = "gmodel.development.inmemory.artefacts";

	private static final int NOTIFICATION_TIME_DELAY = 1000;

	private static final float SEARCH_TEXT_EXPANSION_RATIO = 0.9f;

	private static final String TAB_SHEET_HEIGHT_RATIO = "100%";

	private static final String INSTANCE_SEARCH_CMD = "searchInstance";

	private static final String INSTANCE_RETRIEVAL_CMD = "getGmodelInstances";

	private static final String CHANGESET_COMMIT_CMD = "commitChangeset";

	private static final String CHANGESET_ROLLBACK_CMD = "rollbackChangeset";

	private static boolean isEditMode = false;

	private static final Map<String,String> scopeMap = new HashMap<String,String>();

	private static final Logger log = Logger.getLogger(ContainmentTreePanel.class.getCanonicalName());

	private final Tree containmentTree;

	private final Editor application;

	private TextField searchText;

	private Button commitButton;

	private Button rollbackButton;

	public ContainmentTreePanel(final Editor application) {
		this.application = application;
		addStyleName(Runo.PANEL_LIGHT);
		getLayout().setMargin(true);

		addComponent(createSearchPanel());

		addComponent(createOperationsPanel());

		containmentTree = buildContainmentTree();
		addComponent(containmentTree);

		setScrollable(true);
		setImmediate(true);
		setSizeFull();
	}

	private Panel createSearchPanel() {
		final Panel searchPanel = new Panel();
		final HorizontalLayout searchLayout = new HorizontalLayout();
		searchLayout.setWidth(TAB_SHEET_HEIGHT_RATIO);
		searchLayout.setSpacing(true);
		searchPanel.addStyleName(Runo.PANEL_LIGHT);
		searchPanel.getLayout().setMargin(true);
		searchPanel.setLayout(searchLayout);

		searchText = new TextField();
		searchText.setWidth(TAB_SHEET_HEIGHT_RATIO);

		final Button searchBtn = new Button();
		searchBtn.setDescription("Search");
		searchBtn.setClickShortcut(KeyCode.ENTER);
		searchBtn.setIcon(EditorIcon.SEARCH.getIconImage());
		searchBtn.setWidth(null);
		searchBtn.addListener(Button.ClickEvent.class, this, INSTANCE_SEARCH_CMD);

		searchPanel.addComponent(searchText);
		searchPanel.addComponent(searchBtn);

		searchLayout.setExpandRatio(searchText, SEARCH_TEXT_EXPANSION_RATIO);

		return searchPanel;
	}

	private Panel createOperationsPanel() {
		final Panel operationsPanel = new Panel();
		final HorizontalLayout operationsPanelLayout = new HorizontalLayout();
		operationsPanelLayout.setWidth(TAB_SHEET_HEIGHT_RATIO);
		operationsPanelLayout.setSpacing(true);
		operationsPanel.addStyleName(Runo.PANEL_LIGHT);
		operationsPanel.getLayout().setMargin(true);
		operationsPanel.setLayout(operationsPanelLayout);

		commitButton = new Button();
		commitButton.setDescription("Commit");
		commitButton.setIcon(EditorIcon.PERSIST.getIconImage());
		commitButton.addListener(Button.ClickEvent.class, this, CHANGESET_COMMIT_CMD);

		rollbackButton = new Button();
		rollbackButton.setDescription("Rollback");
		// TODO use a more appropriate icon
		rollbackButton.setIcon(EditorIcon.DELETE.getIconImage());
		rollbackButton.addListener(Button.ClickEvent.class, this, CHANGESET_ROLLBACK_CMD);

		commitButton.setImmediate(true);
		rollbackButton.setImmediate(true);
		commitButton.setEnabled(false);
		rollbackButton.setEnabled(false);

		final Button retrievalBtn = new Button();
		retrievalBtn.setDescription("Retrieve");
		retrievalBtn.setIcon(EditorIcon.RETRIEVE.getIconImage());
		retrievalBtn.setWidth(null);
		retrievalBtn.addListener(Button.ClickEvent.class, this, INSTANCE_RETRIEVAL_CMD);

		operationsPanel.addComponent(retrievalBtn);
		operationsPanel.addComponent(commitButton);
		operationsPanel.addComponent(rollbackButton);

		return operationsPanel;
	}

	private Tree buildContainmentTree() {
		final Tree tree = new Tree("");
		tree.setImmediate(true);
		tree.setSelectable(true);
		tree.setDragMode(TreeDragMode.NODE);
		tree.setItemCaptionPropertyId(TreeContainer.NAME);
		tree.addActionHandler(new TreeActionHandler(application));

		tree.addListener(new ItemClickEvent.ItemClickListener() {
			public void itemClick(final ItemClickEvent event) {
				final TreeNode node = (TreeNode) event.getItemId();
				final DetailsData detailsData = new DetailsData(node.getSet());
				final BeanItem<DetailsData> detailsItem = new BeanItem<DetailsData>(detailsData);
				application.getDetailsForm().setItemDataSource(detailsItem);
				application.getDetailsForm().setVisibleItemProperties(DetailsData.getDisplayOrder());
			}
		});

		tree.setItemStyleGenerator(new Tree.ItemStyleGenerator() {
		    public String getStyle(final Object itemId) {
		    	final TreeNode node = (TreeNode) itemId;
		        if (isEditMode && !isInScope(node.getUrr())) {
					return "disabled";
				}
		        return null;
		    }

		    private boolean isInScope(final String uuid) {
		    	return scopeMap.containsKey(uuid);
		    }
		});

		return tree;
	}

	public Tree getContainmentTree() {
		return containmentTree;
	}

	public void getGmodelInstances(final Button.ClickEvent event) {
		application.getMainWindow().setStyleName("select-wait");
		recreateOutshellInstances();
		containmentTree.setContainerDataSource(TreeContainer.setupContainmentTree());
		application.getMainWindow().setStyleName("select-auto");
	}

	public void recreateOutshellInstances() {
		if (!SemanticStateOfInMemoryModel.cellEditorIsLive()) {
			log.info("Editor is not online and outershell re-creation is to be done.");

			final boolean inMemoryInstances = Boolean.valueOf(System.getProperty(IN_MEMORY_INSTANCES_PROPERTY));
			if (inMemoryInstances) {
				S23MKernel.completeCellKernelInitialization();
				CellPlatform.instantiateFeature();
			} else {
				log.info("Loading semantic extension instances.");
				final RepositoryClient client = RepositoryClientMediator.getInstance().getComponent(ProtocolType.REPOSITORY_CLIENT);
				log.info("client: " + client);
				final ArtefactContainer container = ObjectFactoryHolder.getInstance().createArtefactContainer();
				container.setContentType(SerializationType.CONTAINMENT_TREE.toString());
				log.info("container: " + container);
				final Content uuidContent = ObjectFactoryHolder.getInstance().createArtefactContainerContent();
				uuidContent.setContent(Root.root.identity().uniqueRepresentationReference().toString());
				log.info("uuidContent: " + uuidContent);
				final Content depthContent = ObjectFactoryHolder.getInstance().createArtefactContainerContent();
				depthContent.setContent(""+0);
				container.getContent().add(uuidContent);
				container.getContent().add(depthContent);
				final ArtefactContainer returnedArtifacts = client.get(container);
				log.info("returnedArtifacts: " + returnedArtifacts);

				if (!returnedArtifacts.getContent().isEmpty()) {
					new ArtifactContainerContentMapper().recreateInstancesFromArtifactContainer(returnedArtifacts);
				}
				log.info("Semantic extension initialization is done.");
			}
			S23MKernel.goLiveWithCellEditor();
		} else {
			log.info("Editor is online and outershell re-creation is skipped.");
		}
	}

	public Map<String,String> getScopeMap() {
		return scopeMap;
	}

	public String getSearchText() {
		return searchText.getValue().toString().trim();
	}

//	public void persistAllInstancesInMemory() {
//		application.getMainWindow().setStyleName("select-wait");
//		if(!GQuery.gmodelEditorIsLive()) {
//			SemanticExtensions.instantiateFeature();
//		}
//		final ArtefactContainer container = ObjectFactoryHolder.getInstance().createArtefactContainer();
//		container.setContentType(SerializationType.IN_MEMORY_PERSISTENCE.name());
//		final RepositoryClient client = RepositoryClientMediator.getInstance().getComponent(ProtocolType.REPOSITORY_CLIENT);
//		client.put(container);
//		showNotification("Saved", "All instances are successfully persisted", NOTIFICATION_TIME_DELAY);
//		application.getMainWindow().setStyleName("select-auto");
//	}

	public void commitChangeset() {
		application.getMainWindow().setStyleName("select-wait");

		log.info("Size of changesets: "+Query.changedSets().size());

		if (Query.changedSets().size() > 0) {
			final RepositoryClient client = RepositoryClientMediator.getInstance().getComponent(ProtocolType.REPOSITORY_CLIENT);
			final ArtefactContainer reqContainer = ObjectFactoryHolder.getInstance().createArtefactContainer();
			reqContainer.setContentType(SerializationType.CHANGESET_PERSISTENCE.name());
			client.put(reqContainer);
			F_Transaction.commitChangedSets();
			application.getMainWindow().setStyleName("select-auto");
			showNotification("Committed", "Changes were successfully committed", NOTIFICATION_TIME_DELAY);
		} else {
			application.getMainWindow().setStyleName("select-auto");
			showNotification("Empty Changeset", "No changed instances", NOTIFICATION_TIME_DELAY);
		}
		update();
	}

	public void rollbackChangeset() {
		application.getMainWindow().setStyleName("select-wait");

		log.info("Size of changesets: "+Query.changedSets().size());

		if (!Query.changedSets().isEmpty()) {
			final RepositoryClient client = RepositoryClientMediator.getInstance().getComponent(ProtocolType.REPOSITORY_CLIENT);
			final ArtefactContainer reqContainer = ObjectFactoryHolder.getInstance().createArtefactContainer();
			reqContainer.setContentType(SerializationType.CHANGESET_PERSISTENCE.name());
			client.put(reqContainer);
			F_Transaction.rollbackChangedSets();
			application.getMainWindow().setStyleName("select-auto");
			showNotification("Rolled back", "Instances were rolled back successfully", NOTIFICATION_TIME_DELAY);
		} else {
			application.getMainWindow().setStyleName("select-auto");
			showNotification("Empty Changeset", "No changed instances", NOTIFICATION_TIME_DELAY);
		}
		update();
	}

	public void searchInstance(final Button.ClickEvent event) {
		application.displaySearchResults();
		application.getMultiTabPanel().selectTab(MultitabPanel.SEARCH_TAB_INDEX);
	}

	public void setEditMode(final boolean isEditMode) {
		ContainmentTreePanel.isEditMode = isEditMode;
	}

	private void showNotification(final String caption, final String description, final int timeDelay) {
		final Notification note = new Notification(caption, description);
		note.setDelayMsec(timeDelay);
		application.getMainWindow().showNotification(note);
	}

	public void update() {
		log.info("Containment tree is to be updated, the current changeset size: "+Query.changedSets().size());

		if (Query.changedSets().size() <= 0) {
			commitButton.setEnabled(false);
			rollbackButton.setEnabled(false);
		} else {
			commitButton.setEnabled(true);
			rollbackButton.setEnabled(true);
		}

		application.getMainWindow().setStyleName("select-wait");
		//containmentTree.setContainerDataSource(null);
		containmentTree.setContainerDataSource(TreeContainer.setupContainmentTree());

		commitButton.requestRepaint();
		rollbackButton.requestRepaint();
		application.getMainWindow().setStyleName("select-auto");
	}

}
