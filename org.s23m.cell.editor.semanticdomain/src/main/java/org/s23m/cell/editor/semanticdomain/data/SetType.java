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
 * Chul Kim
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.editor.semanticdomain.data;

import java.util.logging.Logger;

import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.SemanticDomain;

public class SetType {

	private static final Identity SEMANTIC_DOMAIN_IDENTITY = SemanticDomain.semanticdomain.identity();

	private static final Identity DISJUNCT_SEMANTIC_IDENTITY_SET_IDENTITY = SemanticDomain.disjunctSemanticIdentitySet.identity();

	private static final Identity SEMANTIC_ROLE_IDENTITY = SemanticDomain.semanticRole.identity();

	private static final Logger log = Logger.getLogger(SetType.class.getCanonicalName());

	public static boolean isSemanticDomainNode(final Set set) {

		final Identity identity = set.category().identity();

		return identity.isEqualTo(SEMANTIC_DOMAIN_IDENTITY) ||
				identity.isEqualTo(DISJUNCT_SEMANTIC_IDENTITY_SET_IDENTITY) ||
				identity.isEqualTo(SEMANTIC_ROLE_IDENTITY);
	}

	public static boolean isSemanticIdentity(final Set set) {
		final Set category = set.category();
		return category.isEqualTo(SemanticDomain.disjunctSemanticIdentitySet);
	}

}
