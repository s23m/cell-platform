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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

public class EditorController implements PropertyChangeListener {

	private Editor editor;

	private static Logger logger = Logger.getLogger(EditorController.class.getCanonicalName());

	@SuppressWarnings("unused")
	private static class EditorControllerHolder {
	     public static final EditorController INSTANCE = new EditorController();
	}

	public static EditorController getInstance() {
	        return EditorControllerHolder.INSTANCE;
	}

	public void setEditor(final Editor editor) {
		this.editor = editor;
	}

	public void propertyChange(final PropertyChangeEvent evt) {
		//this does not trigger UI changes reliably
		//need to look at GWT EventBus like solution
		if (evt.getPropertyName().equals(EditorEvents.CHANGESET_MODIFIED.getEventName())) {
			if (editor != null) {
				logger.info("Changset changed and update the containment tree");
				editor.updateContainmentTree();

			} else {
				throw new IllegalStateException("Editor is not set");
			}
		}
	}

}
