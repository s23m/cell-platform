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
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.repository.domainclasses;


public class Document {

	private String binaryContent; //base64
	private String content;
	private String id;

	public Document() {
	}

	public Document(final String id, final String content) {
		this();
		this.id = id;
		this.content = content;
	}

	public String getBinaryContent() {
		return binaryContent;
	}

	public String getContent() {
		return content;
	}

	public String getId() {
		return id;
	}

	public void setBinaryContent(final String binaryContent) {
		this.binaryContent = binaryContent;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
		+ ((binaryContent == null) ? 0 : binaryContent.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		final Document other = (Document) obj;
		if (binaryContent == null) {
			if (other.binaryContent != null) {
				return false;
			}
		} else if (!binaryContent.equals(other.binaryContent)) {
			return false;
		}
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

}
