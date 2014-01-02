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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml.model.schema;

import java.util.Map;

public class Cardinality {
    private static final int DEFAULT_VALUE = 1;
    public static final String MIN_OCCURS = "minOccurs";
    public static final String MAX_OCCURS = "maxOccurs";
    
	public final int minOccurs;
	public final int maxOccurs;
    
	public static Cardinality OPTIONAL = new Cardinality(0, 1);
	public static Cardinality ZERO_TO_MANY = new Cardinality(0, -1);
	public static Cardinality EXACTLY_ONE = new Cardinality(1, 1);
    
    public Cardinality(int minOccurs, int maxOccurs) {
    	this.minOccurs = minOccurs;
    	this.maxOccurs = maxOccurs;
    }
    
	public void addToAttributes(Map<String, String> attributes) {
		if (minOccurs != DEFAULT_VALUE || maxOccurs != DEFAULT_VALUE) {
			attributes.put(MIN_OCCURS, displayedValue(minOccurs));
			attributes.put(MAX_OCCURS, displayedValue(maxOccurs));
		}
	}

    public static void removeFromAttributes(Map<String, String> attributes) {
    	attributes.remove(MIN_OCCURS);
		attributes.remove(MAX_OCCURS);
    }

	private static String displayedValue(int number) {
		if (number == -1) {
			return "unbounded";
		} else {
			return String.valueOf(number);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxOccurs;
		result = prime * result + minOccurs;
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
		Cardinality other = (Cardinality) obj;
		if (maxOccurs != other.maxOccurs)
			return false;
		if (minOccurs != other.minOccurs)
			return false;
		return true;
	}
}