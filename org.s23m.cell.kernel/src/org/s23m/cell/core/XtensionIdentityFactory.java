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
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.core;

import org.s23m.cell.Identity;

/**
 * {@link XtensionIdentityFactory} is a temporary extension of the identity factory for Semantic Identities
 * that is in use until the initial Gmodel editor is live.
 */
public class XtensionIdentityFactory {

	public XtensionIdentityFactory() {
	}
	//private final int maxXtentedSemanticIdentityIndex = 5000-1;
	/**
	 * Outer shells & semantic extensions
	 */

	public final Identity infiniteSets() {return F_Instantiation.identityFactory.createIdentityInKernel("infinite sets", "set of infinite sets", XtensionSemanticIdentityRegistry.infiniteSets.ordinal());}
	public final Identity finiteSets() {return F_Instantiation.identityFactory.createIdentityInKernel("finite sets", "set of finite sets", XtensionSemanticIdentityRegistry.finiteSets.ordinal());}
	public final Identity gmodel() {return F_Instantiation.identityFactory.createIdentityInKernel("gmodel", "gmodel", XtensionSemanticIdentityRegistry.gmodel.ordinal());}

}
