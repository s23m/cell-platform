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

package org.s23m.cell.editor.semanticdomain.ui.components.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.s23m.cell.S23MKernel;
import org.s23m.cell.Set;
import org.s23m.cell.api.serializerinterface.Reconstitution;
import org.s23m.cell.editor.semanticdomain.ui.components.MultitabPanel;
import org.s23m.cell.editor.semanticdomain.ui.components.StyledLabel;
import org.s23m.cell.repository.client.RepositoryClient;
import org.s23m.cell.repository.client.mediator.RepositoryClientMediator;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ArtefactContainer.Content;
import org.s23m.cell.serialization.container.ObjectFactoryHolder;
import org.s23m.cell.serialization.serializer.InstanceGetter;
import org.s23m.cell.serialization.serializer.ProtocolType;
import org.s23m.cell.serialization.serializer.SerializationType;

import com.vaadin.event.Action;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
public class ImpactAnalysisFormLayout extends VerticalLayout {

	private static final String RESULTS_CONTENT_PANEL_WIDTH = "80%";

	private static final String RESULTS_CONTENT_PANEL_HEIGHT = "100%";

	private static final String RESULTS_PANEL_HEIGHT = "800px";

	private static final String RESULTS_PANEL_WIDTH = "1000px";

	private static final int PAGE_SIZE = 10;

	private final Set analysedSet;

	private final Action formAction;

	private final MultitabPanel parent;

	private Panel resultsPanel;

	private int pageIndex;

	public ImpactAnalysisFormLayout(final Set set, final Action actionImpactAnalysis,
			final MultitabPanel multiTabPanel) {
		this.analysedSet = set;
		this.formAction = actionImpactAnalysis;
		this.parent = multiTabPanel;
		init();
	}

	private void init() {
		setMargin(true);
		resultsPanel = new Panel();
		resultsPanel.addStyleName(Runo.PANEL_LIGHT);
		resultsPanel.setWidth(RESULTS_PANEL_WIDTH);
		resultsPanel.setHeight(RESULTS_PANEL_HEIGHT);
		addComponent(resultsPanel);
		parent.getMainApplication().getMainWindow().showNotification("Processing results...");
		final List<Set> dependentInstances = doImapactAnalysis(analysedSet.identity().uniqueRepresentationReference().toString());
		this.pageIndex = 0;
		final Label lblTitle = new Label();
		lblTitle.setValue(analysedSet.identity().name()+"<br/> can impact : "+dependentInstances.size()+" instaces(s)");
		lblTitle.setContentMode(Label.CONTENT_XHTML);
		lblTitle.setStyleName("title-label");
		resultsPanel.addComponent(lblTitle);
		if (dependentInstances.size() > 0) {
			 final int startIndex = pageIndex * PAGE_SIZE;
			 final int endIndex = startIndex + PAGE_SIZE;
  			 final Panel resultsContentPanel = new Panel();
  			resultsContentPanel.setWidth(RESULTS_CONTENT_PANEL_WIDTH);
  			resultsContentPanel.setHeight(RESULTS_CONTENT_PANEL_HEIGHT);
			 resultsPanel.addComponent(resultsContentPanel);
			 buildResultsPage(dependentInstances, resultsContentPanel, startIndex, endIndex);
		}
	}

	private void buildResultsPage(final List<Set> dependentInstances, final Panel resultsContentPanel, final int startIndex, final int endIndex) {
		resultsContentPanel.removeAllComponents();
		for (int n = startIndex; (n < endIndex && n < dependentInstances.size()); n++) {
			final Set s = dependentInstances.get(n);
			final HorizontalLayout hl1 = new HorizontalLayout();
			hl1.setData(s);
			final HorizontalLayout hl2 = new HorizontalLayout();
			Label lblDescription = null;
			if (analysedSet.isEqualToRepresentation(s.category())) {
				lblDescription = new StyledLabel("Meta Element of", StyledLabel.VALUE_TAG_STYLE);
			} else if (s.properClass().isEqualTo(S23MKernel.coreGraphs.visibility)) {
				lblDescription = new StyledLabel("Part of Visibility", StyledLabel.VALUE_TAG_STYLE);
			} else if (s.properClass().isEqualTo(S23MKernel.coreGraphs.superSetReference)) {
				lblDescription = new StyledLabel("Part of Superset Reference", StyledLabel.VALUE_TAG_STYLE);
			} else if (s.properClass().isEqualTo(S23MKernel.coreGraphs.edge)) {
				lblDescription = new StyledLabel("Part of Edge", StyledLabel.VALUE_TAG_STYLE);
			}
			resultsContentPanel.addComponent(lblDescription);
			final Label lblMeta = new StyledLabel(s.category().identity().name()+" : ", StyledLabel.META_ELEMENT_STYLE);
			final Label lblInstance = new StyledLabel(s.identity().name(), StyledLabel.INSTANCE_ELEMENT_STYLE);
			final Label lblArtifact = new StyledLabel("part of: "+s.container().identity().name(), StyledLabel.VALUE_TAG_STYLE);
			final Label lblNameTag =  new StyledLabel("name:", StyledLabel.NAME_TAG_STYLE);
			final Label lblName =  new StyledLabel(s.identity().name(),StyledLabel.VALUE_TAG_STYLE);
			final Label lblPlNameTag =  new StyledLabel("plural name:", StyledLabel.NAME_TAG_STYLE);
			final Label lblPlName =  new StyledLabel(s.identity().pluralName(), StyledLabel.VALUE_TAG_STYLE);
			hl1.addComponent(lblMeta);
			hl1.addComponent(lblInstance);
			hl2.addComponent(lblArtifact);
			hl2.addComponent(lblNameTag);
			hl2.addComponent(lblName);
			hl2.addComponent(lblPlNameTag);
			hl2.addComponent(lblPlName);

			resultsContentPanel.addComponent(hl1);
			resultsContentPanel.addComponent(hl2);
		}
	    if (dependentInstances.size() > PAGE_SIZE) {
	    	createPagebar(dependentInstances, resultsContentPanel, dependentInstances.size());
	    }
	}

	private void createPagebar(final List<Set> dependentInstances, final Panel searchResultPanel, final int resultSize) {
		int numPages =resultSize / PAGE_SIZE;
		if (resultSize % PAGE_SIZE > 0) {
			numPages++;
		}
		final HorizontalLayout hl = new HorizontalLayout();
		for (int n = 0; n < numPages; n++) {
			final Button linkBtn = new Button(""+(n+1));
			linkBtn.setData(n);
			linkBtn.setStyleName("link");
			linkBtn.setWidth("10px");
			linkBtn.addListener(new Button.ClickListener() {
				public void buttonClick(final Button.ClickEvent event) {
					//reset pageIndex
					pageIndex = (Integer) linkBtn.getData();
					//rebuild page
					rebuildPage(dependentInstances, searchResultPanel, pageIndex);
				}
			});
			hl.addComponent(linkBtn);
		}
		searchResultPanel.addComponent(hl);
	}

	private void rebuildPage(final List<Set> dependentInstances, final Panel resultsPanel, final int index) {
		 final int startIndex = pageIndex * PAGE_SIZE;
		 final int endIndex = startIndex + PAGE_SIZE;
		 buildResultsPage(dependentInstances, resultsPanel, startIndex, endIndex);
	}

	private List<Set> doImapactAnalysis(final String uuid) {
		parent.getMainApplication().getMainWindow().setStyleName("select-wait");
		final List<Set> dependentSets = new ArrayList<Set>();
		final RepositoryClient client = RepositoryClientMediator.getInstance().getComponent(ProtocolType.REPOSITORY_CLIENT);
		final ArtefactContainer container = ObjectFactoryHolder.getInstance().createArtefactContainer();
		container.setContentType(SerializationType.DEPENDENT_INSTANCES.toString());
		final Content uuidContent = ObjectFactoryHolder.getInstance().createArtefactContainerContent();
		uuidContent.setContent(uuid);
		final Content depthContent = ObjectFactoryHolder.getInstance().createArtefactContainerContent();
		depthContent.setContent(""+0);
		container.getContent().add(uuidContent);
		container.getContent().add(depthContent);
		final ArtefactContainer results = client.get(container);
		for (final Content c : results.getContent()) {
			final Set set = Reconstitution.getSetFromLocalMemory(Reconstitution.reconstituteIdentity
					("", "", UUID.fromString(c.getContent()), UUID.fromString(c.getContent())));
			if (!InstanceGetter.isNonMemoryResidentInstance(set)) {
				dependentSets.add(set);
			}
		}
		System.err.println("Got # dependent instance UUIDs "+results.getContent().size());
		parent.getMainApplication().getMainWindow().setStyleName("select-auto");
		return dependentSets;
	}


}
