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

package org.s23m.cell.editor.semanticdomain.ui.components;

import java.beans.PropertyChangeSupport;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.Transaction;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.serializerinterface.Reconstitution;
import org.s23m.cell.editor.semanticdomain.Editor;
import org.s23m.cell.editor.semanticdomain.EditorController;
import org.s23m.cell.editor.semanticdomain.EditorIcon;
import org.s23m.cell.editor.semanticdomain.data.DetailsData;
import org.s23m.cell.editor.semanticdomain.data.TreeNode;
import org.s23m.cell.editor.semanticdomain.ui.components.field.factory.ListFields;
import org.s23m.cell.editor.semanticdomain.ui.components.layout.AdminFormLayout;
import org.s23m.cell.repository.client.RepositoryClient;
import org.s23m.cell.repository.client.RepositoryClientImpl;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ArtefactContainer.Content;
import org.s23m.cell.serialization.container.ArtefactContainer.SearchResult;
import org.s23m.cell.serialization.container.InstanceIdentityType;
import org.s23m.cell.serialization.container.ObjectFactoryHolder;
import org.s23m.cell.serialization.container.SearchResultType;
import org.s23m.cell.serialization.serializer.InstanceGetter;
import org.s23m.cell.serialization.serializer.SerializationType;
import org.s23m.cell.serialization.serializer.SerializerHolder;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

// TODO create separate classes for each panel
@SuppressWarnings("serial")
public class MultitabPanel extends Panel {

	private static final String SEARCH_RESULTS_PANEL_HEIGHT = "500px";

	private static final String CONSOLE_PANEL_HEIGHT = "10%";

	private static final String CANCEL_BUTTON_LABEL = "Cancel";

	private static final String SAVE_BUTTON_LABEL = "Save";

	private static final String EDIT_BUTTON_LABEL = "Edit";

	private static final String FORM_EVENT_HANDLER = "formEventHandler";

	private static final String TAB_SHEET_WIDTH_RATIO = "95%";

	private static final String BUTTON_BAR_HEIGHT = "25px";

	private static final String RESULTS_PANEL_HEIGHT = "100%";

	private static final String RESULTS_PANEL_WIDTH = "1000px";

	private static final int PAGE_SIZE = 10;

	private static final String ADMIN_TAB_LABEL = "Admin";

	private static final String DETAILS_TAB_LABEL = "Details";

	private static final String SEARCH_TAB_LABEL = "Search";

	private static final String VISUALIZATION_TAB = "Visualization";

	public static final int SEARCH_TAB_INDEX = 0;

	private int pageIndex = 0;

	private Panel searchResultPanel;

	private List<SearchResultType> searchResults;

	private Form detailsForm;

	private Button editBtn;

	private Button saveBtn;

	private Button cancelBtn;

	private final Editor application;

	private final TextArea consoleText;

	private TabSheet tabSheet;

	private final PropertyChangeSupport changeSupport;

	public MultitabPanel (final Editor application) {
		this.application = application;
		this.consoleText = new TextArea("Semantic Error Console");
		setWidth(RESULTS_PANEL_WIDTH);
		setHeight(RESULTS_PANEL_HEIGHT);
		createMultitabPanel();
		createConsolePanel();
		changeSupport = new PropertyChangeSupport(this);
		changeSupport.addPropertyChangeListener(EditorController.getInstance());
	}

	public void addToTabSheet(final Component component, final String title, final ThemeResource icon) {
		tabSheet.addTab(component, title, icon);
		tabSheet.setSelectedTab(component);
		tabSheet.getTab(component).setClosable(true);
	}

	private void buildResultsPage(final Panel resultsPanel, final int startIndex, final int endIndex) {
		resultsPanel.removeAllComponents();
		for (int n = startIndex; (n < endIndex && n < searchResults.size()); n++) {
			final SearchResultType r = searchResults.get(n);
			final Tree cTree = application.getContainmentTreePanel().getContainmentTree();
			final HorizontalLayout hl1 = new HorizontalLayout();
			hl1.setData(r.getInstanceIdentity());
			hl1.addListener(new LayoutClickListener(){
				private void expandAllAncestors(final TreeNode rootNode, final TreeNode node2Select) {
					if (!rootNode.equals(node2Select)) {
						final TreeNode parentNode =  (TreeNode)cTree.getParent(node2Select);
						cTree.expandItem(parentNode);
						expandAllAncestors(rootNode, parentNode);
					}
				}

				public void layoutClick(final LayoutClickEvent event) {
					if (event.getSource() instanceof HorizontalLayout) {
						final HorizontalLayout layout = (HorizontalLayout) event.getSource();
						final InstanceIdentityType id = (InstanceIdentityType) layout.getData();
						//Fetch and deserialize outershell instances if they are not available in memory
						application.getContainmentTreePanel().recreateOutshellInstances();
						application.getContainmentTreePanel().update();
						if (cTree.rootItemIds().size() > 0) {
							final TreeNode rootNode = (TreeNode) cTree.rootItemIds().iterator().next();
							cTree.collapseItemsRecursively(rootNode);
							//select a corresponding tree node
							final Set set = Reconstitution.getSetFromLocalMemory(
									Reconstitution.reconstituteIdentity(id.getName(),
												id.getPluralName(),
												UUID.fromString(id.getUuid()),
												UUID.fromString(id.getUuid())));
							// TODO name based equivalence test needs to be replaced by isEqualToRepresentation()
								if (!set.identity().name().equals((S23MSemanticDomains.semanticErr_ThisSetIsNotAvailableInMemory.identity().name()))) {

								TreeNode node2Select = null;
								if (set.properClass().isEqualTo(S23MKernel.coreGraphs.edge)) {
									node2Select = new TreeNode(set.container().identity().uniqueRepresentationReference().toString(),
											TreeNode.NO_SET);
								} else {
									node2Select = new TreeNode(id.getUuid(), TreeNode.NO_SET);
								}
						        expandAllAncestors(rootNode, node2Select);
						        cTree.setValue(node2Select);
							}
						}
					}
				}//
			});

			final HorizontalLayout hl2 = new HorizontalLayout();

			final Label lblMeta = new Label();
			lblMeta.setStyleName("meta-element");
			final Label lblInstance = new Label();
			lblInstance.setStyleName("instance-element");

			final Label lblArtifact =  new StyledLabel("part of: "+r.getContainerIdentity().getName(), StyledLabel.VALUE_TAG_STYLE);
			final Label lblNameTag =  new StyledLabel("name:", StyledLabel.NAME_TAG_STYLE);
			final Label lblName =  new StyledLabel(r.getInstanceIdentity().getName(), StyledLabel.VALUE_TAG_STYLE);
			final Label lblPlNameTag =  new StyledLabel("plural name:", StyledLabel.NAME_TAG_STYLE);
			final Label lblPlName =  new StyledLabel(r.getInstanceIdentity().getPluralName(), StyledLabel.VALUE_TAG_STYLE);
			if (r.getMetaInstanceIdentity().getName() != null) {
				lblMeta.setValue(r.getMetaInstanceIdentity().getName()+" : ");
			} else {
				final Set metaSet = Reconstitution.getSetFromLocalMemory(
						Reconstitution.reconstituteIdentity("", "",
								UUID.fromString(r.getMetaInstanceIdentity().getUuid()), UUID.fromString(r.getMetaInstanceIdentity().getUuid()))
				);
				// TODO name based equivalence test needs to be replaced by isEqualToRepresentation()
				     if (!metaSet.identity().name().equals((S23MSemanticDomains.semanticErr_ThisSetIsNotAvailableInMemory.identity().name()))) {

			    	 lblMeta.setValue(metaSet.identity().name()+" : ");
			     } else {
			    	 lblMeta.setValue(" : ");
				}
			}
			lblInstance.setValue(r.getInstanceIdentity().getName());

			hl1.addComponent(lblMeta);
			hl1.addComponent(lblInstance);
			hl2.addComponent(lblArtifact);
			hl2.addComponent(lblNameTag);
			hl2.addComponent(lblName);
			hl2.addComponent(lblPlNameTag);
			hl2.addComponent(lblPlName);

			resultsPanel.addComponent(hl1);
			resultsPanel.addComponent(hl2);
		}
	    if (searchResults.size() > PAGE_SIZE) {
	    	createPagebar(resultsPanel, searchResults.size());
	    }
	}

	private void createConsolePanel() {
		final VerticalLayout consoleLayout = new VerticalLayout();
		consoleLayout.setHeight(CONSOLE_PANEL_HEIGHT);
		consoleText.setWidth(TAB_SHEET_WIDTH_RATIO);
		consoleLayout.addComponent(consoleText);
		this.addComponent(consoleLayout);
	}

	private void createMultitabPanel() {
		addStyleName(Runo.PANEL_LIGHT);
		getLayout().setMargin(true);

		// Search results tab content
		searchResultPanel = new Panel();
		final VerticalLayout searchLayer = new VerticalLayout();
		searchLayer.setHeight(SEARCH_RESULTS_PANEL_HEIGHT);
		searchResultPanel.addStyleName(Runo.PANEL_LIGHT);
		searchLayer.addComponent(searchResultPanel);


		// Details form tab content
		final VerticalLayout detailsFormLayout = new VerticalLayout();
		detailsFormLayout.setMargin(true);
		detailsForm = new Form() {
	        @SuppressWarnings("unchecked")
			@Override
	        public void setReadOnly(final boolean readOnly) {
	            super.setReadOnly(readOnly);
	            final BeanItem<DetailsData> dataSrc = (BeanItem<DetailsData>) detailsForm.getItemDataSource();
	            final Set detailsInstance = dataSrc.getBean().getInstance();
	            if (!InstanceGetter.hasModifiableName(detailsInstance)) {
	            	for (final String id : DetailsData.getNameFieldIds()) {
		            	final Field f = detailsForm.getField(id);
		            	f.setReadOnly(true);
	            	}
	            }
	        }
	    };
		detailsForm.setCaption("Instance Details");
		detailsForm.setImmediate(true);
		detailsForm.setWriteThrough(false);

		final DetailsData detailsData = new DetailsData(Root.root);
		final BeanItem<DetailsData> detailsItem = new BeanItem<DetailsData>(detailsData);

		final FormFieldFactory formFieldFactory = new PanelFormFieldFactory();
		detailsForm.setFormFieldFactory(formFieldFactory);
		//addImageUploadControls(detailsData);
		detailsForm.setItemDataSource(detailsItem);
		detailsForm.setVisibleItemProperties(DetailsData.getDisplayOrder());
		detailsForm.setFooter(new VerticalLayout());

		final HorizontalLayout formButtonBar = new HorizontalLayout();
		formButtonBar.setSpacing(true);
		formButtonBar.setHeight(BUTTON_BAR_HEIGHT);

		final Layout footer = detailsForm.getFooter();

		footer.addComponent(formButtonBar);

		editBtn = new Button(EDIT_BUTTON_LABEL, this, FORM_EVENT_HANDLER);

		saveBtn = new Button(SAVE_BUTTON_LABEL, this, FORM_EVENT_HANDLER);
		saveBtn.setVisible(false);

		cancelBtn = new Button(CANCEL_BUTTON_LABEL, this, FORM_EVENT_HANDLER);
		cancelBtn.setVisible(false);

		formButtonBar.addComponent(editBtn);
		formButtonBar.setComponentAlignment(editBtn, Alignment.TOP_RIGHT);
		formButtonBar.addComponent(saveBtn);
		formButtonBar.addComponent(cancelBtn);

		detailsFormLayout.addComponent(detailsForm);

		tabSheet = new TabSheet();
		tabSheet.setWidth(TAB_SHEET_WIDTH_RATIO);
		tabSheet.setHeight("90%");

		tabSheet.addTab(searchLayer, SEARCH_TAB_LABEL,  EditorIcon.SEARCH.getIconImage());
		tabSheet.addTab(detailsFormLayout, DETAILS_TAB_LABEL, EditorIcon.DETAILS.getIconImage());
		tabSheet.addTab(new AdminFormLayout(this), ADMIN_TAB_LABEL, EditorIcon.ADMIN.getIconImage());
		//tabSheet.addTab(new GraphVisualizationLayout(this), VISUALIZATION_TAB, EditorIcon.ADMIN.getIconImage());
		addComponent(tabSheet);
	}

	private void addImageUploadControls(final DetailsData detailsData) {
		final Embedded img = new Embedded("Icon", EditorIcon.VERTEX.getIconImage());
		final FileReceiver receiver = new FileReceiver();
		final Upload upload = new Upload("",receiver);

		upload.addListener(new Upload.FinishedListener() {

			public void uploadFinished(final FinishedEvent event) {
				final String imgContent = receiver.getBase64ContentString();
				final byte[] imgData = SerializerHolder.getS23MInstanceSerializer(SerializationType.XML).decodeBase64StringToByteArray(imgContent);
				DataOutputStream os = null;
				try {
					final String id = detailsData.getInstance().identity().identifier().toString();
					final String resourcePath = File.createTempFile(id,"").getAbsolutePath();
					os = new DataOutputStream(new FileOutputStream(resourcePath));
					os.write(imgData);
					img.setSource(new FileResource(new File(resourcePath), application));
				} catch (final IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (os != null) {
							os.close();
						}
					} catch (final IOException e) {}
				}
				receiver.clearCache();
			}

	    });
		detailsForm.getLayout().addComponent(img);
		detailsForm.getLayout().addComponent(upload);
	}

	private void createPagebar(final Panel searchResultPanel, final int resultSize) {
		int numPages =resultSize / PAGE_SIZE;
		if (resultSize % PAGE_SIZE > 0) {
			numPages++;
		}
		final HorizontalLayout hl = new HorizontalLayout();
		for (int n = 0; n < numPages; n++) {
			final Button linkBtn = new Button(""+(n+1));
			linkBtn.setData(n);
			linkBtn.setStyleName("link");
			linkBtn.setWidth("15px");
			linkBtn.addListener(new Button.ClickListener() {
				public void buttonClick(final Button.ClickEvent event) {
					//reset pageIndex
					pageIndex = (Integer) linkBtn.getData();
					//rebuild page
					rebuildPage(searchResultPanel, pageIndex);
				}
			});
			hl.addComponent(linkBtn);
		}
		searchResultPanel.addComponent(hl);
	}

	public void displaySearchResults() {
		application.getMainWindow().showNotification("Processing results...");
		this.pageIndex = 0;
		final RepositoryClient client = RepositoryClientImpl.getInstance();
		final String inputText = application.getContainmentTreePanel().getSearchText();
		final ArtefactContainer container = ObjectFactoryHolder.getInstance().createArtefactContainer();
		container.setContentType(SerializationType.SEARCH_ARGUMENTS.toString());
		final Content searchContent = ObjectFactoryHolder.getInstance().createArtefactContainerContent();
		searchContent.setContent(inputText);
		container.getContent().add(searchContent);
		final ArtefactContainer resultsContainer = client.get(container);
		searchResults = new ArrayList<SearchResultType>();
		for (final SearchResult sr : resultsContainer.getSearchResult()) {
			searchResults.add(sr);
		}
		searchResultPanel.removeAllComponents();

		final Panel resultsPanel = new Panel();
		final Label lblTitle = new Label();
		lblTitle.setValue(inputText+"<br/>Returned: "+searchResults.size()+" result(s)");
		lblTitle.setContentMode(Label.CONTENT_XHTML);
		lblTitle.setStyleName("title-label");
		searchResultPanel.addComponent(lblTitle);

		if (searchResults.size() > 0) {
			//display 10 per page and put a navigation toolbar at the bottom
			searchResultPanel.addComponent(resultsPanel);
		    final int startIndex = pageIndex * PAGE_SIZE;
		    final int endIndex = startIndex + PAGE_SIZE;
	    	buildResultsPage(resultsPanel, startIndex, endIndex);
		}
	}

	public void formEventHandler(final Button.ClickEvent event) {
		final Button source = event.getButton();

		if (source.equals(saveBtn)) {
			detailsForm.commit();
	    	//changeSupport.firePropertyChange(EditorEvents.CHANGESET_MODIFIED.getEventName(), null, null);
	    	((org.s23m.cell.editor.semanticdomain.Editor)getApplication()).updateContainmentTree();
			detailsForm.setReadOnly(true);
			toggleButtonVisibilities(false);
		} else if (source.equals(cancelBtn)) {
			detailsForm.discard();
			detailsForm.setReadOnly(true);
			Transaction.rollbackChangedSets();
			toggleButtonVisibilities(false);
		} else if (source.equals(editBtn)){
			detailsForm.setReadOnly(false);
			toggleButtonVisibilities(true);
		}
	}

	public TextArea getConsole() {
		return consoleText;
	}

	public Form getDetailsForm() {
		return detailsForm;
	}

	public Panel getSearchResultPanel() {
		return searchResultPanel;
	}

	private void rebuildPage(final Panel resultsPanel, final int index) {
		 final int startIndex = pageIndex * PAGE_SIZE;
		 final int endIndex = startIndex + PAGE_SIZE;
		 buildResultsPage(resultsPanel, startIndex, endIndex);
	}

	public void setDetailsForm(final Form detailsForm) {
		this.detailsForm = detailsForm;
	}

	public void selectTab(final int tabIndex) {
		if (tabIndex == SEARCH_TAB_INDEX) {
			tabSheet.setSelectedTab(tabSheet.getTab(tabIndex).getComponent());
		}
	}

	private void toggleButtonVisibilities(final boolean isEditOn) {
		editBtn.setVisible(!isEditOn);
		saveBtn.setVisible(isEditOn);
		cancelBtn.setVisible(isEditOn);
	}

	public void closeTab(final Component component) {
		tabSheet.removeComponent(component);
	}

	public Editor getMainApplication() {
		return this.application;
	}

	private static class PanelFormFieldFactory implements FormFieldFactory {

		public Field createField(final Item item, final Object propertyId, final Component uiContext) {
			final String pid = (String) propertyId;
			final boolean isListFieldId = DetailsData.getListFieldIds().contains(pid);
			final boolean isModifiable = DetailsData.getModifiableFieldIds().contains(pid);
			if (!isModifiable && !isListFieldId) {
				final TextField f = new TextField(DefaultFieldFactory.createCaptionByPropertyId(pid));
				f.setColumns(30);
				f.setReadOnly(true);
				return f;
		    } else if (isListFieldId) {
		    	return createListField(item, propertyId, pid);
			} else {
				// read-only
				final TextField f = new TextField(DefaultFieldFactory.createCaptionByPropertyId(pid));
				f.setReadOnly(true);
				return f;
			}
		}

		@SuppressWarnings("unchecked")
		private Field createListField(final Item item, final Object propertyId, final String pid) {
			final List<Set> sets = (List<Set>) item.getItemProperty(propertyId).getValue();
			final String title = DefaultFieldFactory.createCaptionByPropertyId(pid);
			return ListFields.create(pid, title, sets);
		}
	}
}
