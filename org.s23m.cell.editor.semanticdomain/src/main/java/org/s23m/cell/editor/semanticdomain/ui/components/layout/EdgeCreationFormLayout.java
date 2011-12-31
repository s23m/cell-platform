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

import org.s23m.cell.Set;
import org.s23m.cell.editor.semanticdomain.ui.components.EdgeCreationForm;
import org.s23m.cell.editor.semanticdomain.ui.components.MultitabPanel;

import com.vaadin.event.Action;
import com.vaadin.ui.GridLayout;

@SuppressWarnings("serial")
public class EdgeCreationFormLayout extends GridLayout {

	private static final int ROW_SIZE = 8;
	private static final int COL_SIZE = 2;
	private static final String WINDOW_WIDTH = "600px";
	private static final String WINDOW_HT = "520px";

	private final Set sourceInstance;
	private final Action action;
	private final MultitabPanel parent;

	public EdgeCreationFormLayout(final Set sourceInstance, final Action action, final MultitabPanel parent) {
		super(COL_SIZE, ROW_SIZE);
		this.sourceInstance = sourceInstance;
		this.action = action;
		this.parent = parent;
		initForm();
	}

	private void initForm() {
		setCaption(action.getCaption());
		setWidth(WINDOW_WIDTH);
		setHeight(WINDOW_HT);
		setMargin(true);
		setSpacing(true);
		addComponent(new EdgeCreationForm(sourceInstance, this));
	}

	@Override
	public MultitabPanel getParent() {
		return parent;
	}

}
