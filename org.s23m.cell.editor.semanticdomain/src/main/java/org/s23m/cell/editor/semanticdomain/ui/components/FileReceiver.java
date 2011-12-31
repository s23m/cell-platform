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

package org.s23m.cell.editor.semanticdomain.ui.components;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.s23m.cell.serialization.serializer.SerializationType;
import org.s23m.cell.serialization.serializer.SerializerHolder;

import com.vaadin.ui.Upload.Receiver;

@SuppressWarnings("serial")
public class FileReceiver implements Receiver {

	final List<Byte> byteList;

	public FileReceiver() {
		this.byteList = new ArrayList<Byte>();
	}

	public void clearCache() {
		byteList.clear();
	}

	public String getBase64ContentString() {
		final Byte[] bytes = byteList.toArray(new Byte[byteList.size()]);
		return SerializerHolder.getGmodelInstanceSerializer(SerializationType.XML).encodeByteArrayInBase64(ArrayUtils.toPrimitive(bytes));
	}

	public OutputStream receiveUpload(final String filename, final String mimeType) {
		 return new OutputStream() {
			 @Override
             public void write(final int b) throws IOException {
				 byteList.add((byte)b);
             }
         };
	}

}
