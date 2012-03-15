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
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.util.UUID;


public class LocalTransportContainer implements Container<LocalTransportContent<SerializationContent>> {

	private LocalTransportContent<SerializationContent> content;
	private final UUID uuid;

	public LocalTransportContainer(final UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final LocalTransportContainer other = (LocalTransportContainer) obj;
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}

	public UUID getContentUUID() {
		return uuid;
	}

	//public String getTransmissionTimeStamp() {
	//	return Root.transmissionTimestamp.identity().payload();
	//}

	//protected Set getTransportationContainer() {
	//	return Root.transportcontainer;
	//}

	public LocalTransportContent<SerializationContent> getTransportationContent() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	//public void setTransmissionTimeStamp() {
	//	Root.transmissionTimestamp.identity().setPayload(FileIndexer.getCurrentTimeStamp());
	//}

	public void setTransportationContent(final LocalTransportContent<SerializationContent> content) {
		this.content = content;
	}

}
