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

package org.s23m.cell.editor.semanticdomain.data;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.s23m.cell.Set;

import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class InstanceContainer extends BeanItemContainer<InstanceData> implements Serializable {

	public static final Object[] NATURAL_COL_ORDER = new Object[] {"property", "value"};

	public static final String[] COL_HEADERS = new String[] {"Property", "Value"};

	public InstanceContainer() throws InstantiationException, IllegalAccessException {
		super(InstanceData.class);
	}

	public static InstanceContainer createWithInstanceData(final Set instance) {
		InstanceContainer container = null;
		try {
			container = new InstanceContainer();
			container.addItem(new InstanceData(instance, "meta-instance", instance.category().identity().name()));
			container.addItem(new InstanceData(instance, "name", instance.identity().name()));
			container.addItem(new InstanceData(instance, "plural name", instance.identity().pluralName()));
		} catch (final InstantiationException ex) {
			Logger.getLogger("global").log(Level.SEVERE, null, ex);
		} catch (final IllegalAccessException ex) {
			Logger.getLogger("global").log(Level.SEVERE, null, ex);
		}

		container.addListener(new ItemSetChangeListener() {
			public void containerItemSetChange(final ItemSetChangeEvent event) {
				System.err.println("Changed: "+event);
			}

		});

		return container;
	}


}
