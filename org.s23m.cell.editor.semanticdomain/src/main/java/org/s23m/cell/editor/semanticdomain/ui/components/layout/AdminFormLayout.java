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

import org.s23m.cell.S23MKernel;
import org.s23m.cell.editor.semanticdomain.ui.components.MultitabPanel;
import org.s23m.cell.platform.models.CellPlatform;
import org.s23m.cell.repository.client.RepositoryClient;
import org.s23m.cell.repository.client.mediator.RepositoryClientMediator;
import org.s23m.cell.repository.client.server.RepositoryClientServer;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.container.ObjectFactoryHolder;
import org.s23m.cell.serialization.serializer.ProtocolType;
import org.s23m.cell.serialization.serializer.SerializationType;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AdminFormLayout extends VerticalLayout {

	private static final String STATUS_MSG_SERVER_ACTIVE = "Running...";

	private static final String STATUS_MSG_SERVER_INACTIVE = "Repository client stopped.";

	private static final String BUTTON_BAR_HEIGHT = "25px";

	private final MultitabPanel parent;

	private static final RepositoryClient client = RepositoryClientMediator.getInstance().getComponent(ProtocolType.REPOSITORY_CLIENT);

	public AdminFormLayout(final MultitabPanel parent) {
		this.parent = parent;
		init();
	}

	private void init() {
		setMargin(true);
		final String lblMsg = STATUS_MSG_SERVER_INACTIVE;//RepositoryClientServer.getInstance().isRepositoryServerRunning() ? STATUS_MSG_SERVER_ACTIVE : STATUS_MSG_SERVER_INACTIVE;
		final Label lblStatus = new Label(lblMsg);
		addComponent(lblStatus);

		final HorizontalLayout buttonBarLayout = new HorizontalLayout();
		buttonBarLayout.setSpacing(true);
		buttonBarLayout.setHeight(BUTTON_BAR_HEIGHT);

		final HorizontalLayout checkinBarLayout = new HorizontalLayout();
		checkinBarLayout.setSpacing(true);
		checkinBarLayout.setHeight(BUTTON_BAR_HEIGHT);

		final Button okBtn = new Button("Start Repository Client", new Button.ClickListener() {
			public void buttonClick(final ClickEvent event) {
				try {
					if (!RepositoryClientServer.getInstance().isRepositoryServerRunning()) {
						RepositoryClientServer.getInstance().start();
						lblStatus.setValue(STATUS_MSG_SERVER_ACTIVE);
					}
				} catch (final Throwable th) {
					lblStatus.setValue(th);
				}
			}
		});

		final Button stopBtn = new Button("Stop Repository Client", new Button.ClickListener() {
			public void buttonClick(final ClickEvent event) {
				try {
					if (RepositoryClientServer.getInstance().isRepositoryServerRunning()) {
						RepositoryClientServer.getInstance().stop();
						lblStatus.setValue(STATUS_MSG_SERVER_INACTIVE);
					}
				} catch (final Throwable th) {
					lblStatus.setValue(th);
				}
			}
		});

		buttonBarLayout.addComponent(okBtn);
		buttonBarLayout.addComponent(stopBtn);
		buttonBarLayout.setComponentAlignment(stopBtn, Alignment.TOP_RIGHT);

		final Button checkInButton = new Button("Do initial check-in", new Button.ClickListener() {
			public void buttonClick(final ClickEvent event) {
				try {
					S23MKernel.completeCellKernelInitialization();
					CellPlatform.instantiateFeature();
					//org.s23m.cell.test.artifactinstantiation.Test.main(null);
					final ArtefactContainer container = ObjectFactoryHolder.getInstance().createArtefactContainer();
					container.setContentType(SerializationType.IN_MEMORY_PERSISTENCE.name());
					client.put(container);
					lblStatus.setValue("Initial check-in done.");
				} catch (final Throwable th) {
					lblStatus.setValue(th);
				}
			}
		});

		checkInButton.setEnabled(true);
		checkinBarLayout.addComponent(checkInButton);
		addComponent(buttonBarLayout);
		addComponent(checkinBarLayout);
	}

}
