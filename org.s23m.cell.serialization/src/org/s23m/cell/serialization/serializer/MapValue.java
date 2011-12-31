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
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import org.s23m.cell.serialization.OrderedPair;

public class MapValue {
	
	final private OrderedPair orderedPair;
	final private String hostInstanceId;
	
	public OrderedPair getOrderedPair() {
		return orderedPair;
	}

	public String getHostInstanceId() {
		return hostInstanceId;
	}

	public MapValue(final OrderedPair orderedPair, final String hostInstanceId) {
		super();
		this.orderedPair = orderedPair;
		this.hostInstanceId = hostInstanceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((orderedPair == null) ? 0 : orderedPair.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapValue other = (MapValue) obj;
		if (orderedPair == null) {
			if (other.orderedPair != null)
				return false;
		} else if (!orderedPair.getElement().equals(other.orderedPair.getElement()))
			return false;
		return true;
	}
			
}
