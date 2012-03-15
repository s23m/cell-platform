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
package org.s23m.cell.editor.semanticdomain;

import com.vaadin.terminal.ThemeResource;

public enum EditorIcon {
	SEARCH,
	RETRIEVE,
	ADD,
	ADMIN,
	DELETE,
	PERSIST,
	INSTANTIATE,
	IMPACT_ANALYSIS,
	DETAILS,
	VERTEX,
	VISIBILITY,
	SSR,
	EDGE,
	SEMANTIC_ID,
	SEMANTIC_DOMAIN,
	SEMANTIC_ROLE,
	INMEM_PERSISTENCE;

	private static final String DIRECTORY_PATH = "icons";
	private static final String FILE_EXTENSION = ".png";

	private ThemeResource iconImage;

	private EditorIcon() {
		final String path = DIRECTORY_PATH + "/" + name().toLowerCase() + FILE_EXTENSION;
		iconImage = new ThemeResource(path);
	}

	public ThemeResource getIconImage() {
		return iconImage;
	}

}
