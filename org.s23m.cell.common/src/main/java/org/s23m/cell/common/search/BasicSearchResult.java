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

package org.s23m.cell.common.search;

public class BasicSearchResult implements SearchResult {

	private static final String NOT_DEFINED_VALUE = "NOT DEFINED";
	private final BasicSearchIdentity instanceIdentity;
	private final BasicSearchIdentity parentIdentity;
	private final String categoryName;
	private final String categoryTypeName;
	private String errorCode;

	public BasicSearchResult(final String errorCode) {
		this(NOT_DEFINED_VALUE, NOT_DEFINED_VALUE, null, null);
		this.errorCode = errorCode;
	}

	public BasicSearchResult(final String categoryName, final String categoryTypeName, final BasicSearchIdentity parentIdentity, final BasicSearchIdentity instanceIdentity) {
		this.categoryName = categoryName;
		this.categoryTypeName = categoryTypeName;
		this.parentIdentity = parentIdentity;
		this.instanceIdentity = instanceIdentity;
	}

	public BasicSearchIdentity getArtifactIdentity() {
		return parentIdentity;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public BasicSearchIdentity getInstanceIdentity() {
		return instanceIdentity;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public String getCategoryTypeName() {
		return this.categoryTypeName;
	}

	public void setErrorCode(final String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "BasicSearchResult [instanceIdentity=" + instanceIdentity
		+ ", parentIdentity=" + parentIdentity + "]";
	}

}
