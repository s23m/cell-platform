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

package org.s23m.cell.core;


/**
 * {@link XtensionSemanticIdentityRegistry} is a temporary extension of the semantic identity registry
 * that is in use until the initial S23M editor is live. It is an ordered list of the SemanticIdentities
 * that are used to construct the Instances and Properties of the S23M outer shells and cell platform.
 * 
 * Important: Elements in this list may never be removed or resequenced,
 * as the stability of the UUIDs of semantic identities in the kernel of S23M depends
 * on the sequence of elements in this list.
 * 
 * 	==>	If new semantic identities need to be added to the S23M kernel,
 * 		this list needs to be appended with a corresponding element.
 * 	==>	If a semantic identity <si> becomes obsolete, the corresponding element in this list must
 * 		be renamed from <si> to <si>_DEPRECATED.
 */

public enum XtensionSemanticIdentityRegistry {
	/**
	 * outer shells & cell platform
	 */
	sandboxSemanticDomains,
	agentSemanticDomains,
	cellKernel,
	agent,
	nonLinearSystem,
	linearSystem,
}

