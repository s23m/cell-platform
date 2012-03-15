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

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class StyledLabel extends Label {

	public static final String VALUE_TAG_STYLE = "value-tag";

	public static final String NAME_TAG_STYLE = "name-tag";

	public static final String INSTANCE_ELEMENT_STYLE = "instance-element";

	public static final String META_ELEMENT_STYLE = "meta-element";

	public StyledLabel(final String caption, final String style) {
	    super(caption);
		setStyleName(style);
	}

}
