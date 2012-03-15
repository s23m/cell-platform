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

public enum SerializationType {

	ARTIFACT_PERSISTENCE,
	ARTIFACT_RETRIEVAL, CHANGESET_PERSISTENCE, CONTAINMENT_TREE, CONTAINMENT_TREE_UUIDS_RETRIEVAL,
	DEPENDENT_INSTANCE_UUIDS,DEPENDENT_INSTANCES, IN_MEMORY_PERSISTENCE, OBJECT_POOL_PERSISTENCE, SEARCH_ARGUMENTS,
	XML;

	private String typeName;

	private SerializationType() {
		typeName = name();
	}

	public String getTypeName() {
		return typeName;
	}

}
