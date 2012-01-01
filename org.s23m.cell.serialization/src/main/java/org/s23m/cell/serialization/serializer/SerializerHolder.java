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
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

public class SerializerHolder {

	/**
	 * Return a Gmodle instance serializer which matches with the type
	 * @param type
	 * @return Serializer
	 */
	static class SerializerInstance {
		public static final Serializer XML_SERIALIZER =  new FileSystemSerializer();
	}

	public static Serializer getGmodelInstanceSerializer(final SerializationType type) {
		if (type.equals(SerializationType.XML)) {
			return SerializerInstance.XML_SERIALIZER;
		} else {
			throw new UnsupportedOperationException("Non file-system serializatio is not supported yet.");
		}
	}

}
