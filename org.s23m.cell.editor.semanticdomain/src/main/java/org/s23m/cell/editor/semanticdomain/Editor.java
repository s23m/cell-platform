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

package org.s23m.cell.editor.semanticdomain;

import java.util.logging.Logger;

import org.s23m.cell.SemanticStateOfInMemoryModel;
import org.s23m.cell.editor.semanticdomain.ui.components.ContainmentTreePanel;
import org.s23m.cell.editor.semanticdomain.ui.components.MultitabPanel;

import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class Editor extends Application {

	private static final String THEME_NAME = "org.s23m.cell.editor.semanticdomaintheme";

	private static final int DEFAULT_L_WIDTH = 250;

	private static final Logger log = Logger.getLogger(Editor.class.getCanonicalName());

	private Window mainWindow;

	private MultitabPanel multitabPanel;

	private ContainmentTreePanel containmentTreePanel;

	public void displaySearchResults() {
		multitabPanel.displaySearchResults();
	}


	public TextArea getConsole() {
		return multitabPanel.getConsole();
	}


	public ContainmentTreePanel getContainmentTreePanel() {
		return containmentTreePanel;
	}

	public Form getDetailsForm() {
		return multitabPanel.getDetailsForm();
	}

	public MultitabPanel getMultiTabPanel() {
		return multitabPanel;
	}

	@Override
	public void init() {
		EditorController.getInstance().setEditor(this);
		mainWindow = new Window("Gmodel");
		//mainWindow.addComponent(pusher);
		setMainWindow(mainWindow);
		setTheme(THEME_NAME);

		initializeGmodelKernel();

		final HorizontalSplitPanel splitter = new HorizontalSplitPanel();
		splitter.setSplitPosition(DEFAULT_L_WIDTH, Sizeable.UNITS_PIXELS);
		mainWindow.setContent(splitter);

		containmentTreePanel = new ContainmentTreePanel(this);
		multitabPanel = new MultitabPanel(this);
		//((VerticalLayout)multitabPanel.getConsole().getParent()).addComponent(pusher);
		splitter.addComponent(containmentTreePanel);
		splitter.addComponent(multitabPanel);
	}

//TODO: override getWindow when a multi-user support is on.
//	@Override
//	public Window getWindow(final String name) {
//		Window mainWindow = super.getWindow(name);
//		if (mainWindow == null) {
//    		EditorController.getInstance().setEditor(this);
//    		mainWindow = new Window("Gmodel");
//    		setMainWindow(mainWindow);
//    		setTheme(THEME_NAME);
//
//    		//initializeGmodelKernel();
//
//    		final HorizontalSplitPanel splitter = new HorizontalSplitPanel();
//    		splitter.setSplitPosition(DEFAULT_L_WIDTH, Sizeable.UNITS_PIXELS);
//    		mainWindow.setContent(splitter);
//
//    		containmentTreePanel = new ContainmentTreePanel(this);
//    		multitabPanel = new MultitabPanel(this);
//
//    		splitter.addComponent(containmentTreePanel);
//    		splitter.addComponent(multitabPanel);
//            addWindow(mainWindow);
//		}
//		return mainWindow;
//	}

	private void initializeGmodelKernel() {
		if (SemanticStateOfInMemoryModel.semanticDomainIsInitialized()) {
			log.info("Gmodel open source kernel was set up");
		} else {
			log.info("Setting up Gmodel kernel...");
			org.s23m.cell.G.completeOpenSourceKernelInitialization();
		}
	}

	public void searchInstance(final Button.ClickEvent event) {
		multitabPanel.displaySearchResults();
	}

	public void updateContainmentTree() {
		containmentTreePanel.requestRepaint();
		containmentTreePanel.update();
	}

}
