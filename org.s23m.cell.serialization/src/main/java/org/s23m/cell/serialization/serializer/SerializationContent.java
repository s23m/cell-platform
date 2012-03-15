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

import java.util.List;

import org.s23m.cell.serialization.S23M;
import org.s23m.cell.serialization.SemanticIdentityIndex;

public class SerializationContent extends SemanticIdContent {

	private String content;
	private String contentChecksum;
	private final SemanticIdContent data = new SemanticIdContent();
	private final S23M model;
	private final String id;

	public SerializationContent(final String id, final String content, final S23M model, final List<SemanticIdentityIndex> semanticIds) {
		this.id = id;
		this.content = content;
		this.model = model;
		this.data.setSemanticIds(semanticIds);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SerializationContent other = (SerializationContent) obj;
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getContent() {
		return content;
	}

	public String getContentChecksum() {
		return contentChecksum;
	}

	public String getId() {
		return id;
	}

	public S23M getModel() {
		return model;
	}

	@Override
	public List<SemanticIdentityIndex> getSemanticIds() {
		return data.getSemanticIds();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setContentChecksum(final String contentChecksum) {
		this.contentChecksum = contentChecksum;
	}

	@Override
	public void setSemanticIds(final List<SemanticIdentityIndex> semanticIds) {
		this.data.setSemanticIds(semanticIds);
	}

	@Override
	public String toString() {
		return "SerializationContent [semanticIds=" + data.getSemanticIds()
		+ "]";
	}

}
