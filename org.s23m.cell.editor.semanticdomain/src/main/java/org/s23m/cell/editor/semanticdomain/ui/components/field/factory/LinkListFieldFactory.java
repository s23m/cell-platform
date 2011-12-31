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
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.editor.semanticdomain.ui.components.field.factory;

import java.util.List;

import org.s23m.cell.Set;
import org.s23m.cell.editor.semanticdomain.data.LinkDetailsData;
import org.s23m.cell.editor.semanticdomain.ui.components.field.LinkDisplayField;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Field;

public class LinkListFieldFactory implements ListFieldFactory {

	public Field create(final String title, final List<Set> sets) {
		final BeanItemContainer<LinkDetailsData> container = new BeanItemContainer<LinkDetailsData>(LinkDetailsData.class);
		for (final Set s : sets) {
			container.addBean(new LinkDetailsData(s));
		}
		return new LinkDisplayField(title, container);
	}

}
