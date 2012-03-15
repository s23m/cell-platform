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

package org.s23m.cell.objectpool;

import java.io.Serializable;
import java.util.UUID;


@SuppressWarnings("serial")
public class ObjectPoolArtifact implements Serializable {

	private final Object content;

	private final UUID metaTypeUUID;

	private final UUID uuid;
	
	private boolean isModified;

	public ObjectPoolArtifact(final UUID uuid, final UUID metaTypeUUID,final Object content) {
		this.uuid = uuid;
		this.metaTypeUUID = metaTypeUUID;
		this.content = content;
		this.isModified = false;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectPoolArtifact other = (ObjectPoolArtifact) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public Object getContent() {
		return content;
	}

	public UUID getMetaType() {
		return metaTypeUUID;
	}
	
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	public boolean isModified() {
		return isModified;
	}

	public void setModified(final boolean isModified) {
		this.isModified = isModified;
	}
	
	@Override
	public String toString() {
		return "ObjectPoolArtifact [uuid=" + uuid + ", metaType=" + metaTypeUUID
				+ "]";
	}
	
}
