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

import org.s23m.cell.Identity;

/**
 * {@link XtensionIdentityFactory} is a temporary extension of the identity factory for Semantic Identities
 * that is in use until the initial S23M editor is live.
 */
public class XtensionIdentityFactory {

	public XtensionIdentityFactory() {
	}
	//private final int maxXtentedSemanticIdentityIndex = 5000-1;
	/**
	 * Outer shells & cell platform
	 */

	public final Identity sandboxSemanticDomains() {return F_Instantiation.identityFactory.createIdentityInKernel("sandbox semantic domains", "sandbox semantic domains", XtensionSemanticIdentityRegistry.sandboxSemanticDomains.ordinal());}
	public final Identity agentSemanticDomains() {return F_Instantiation.identityFactory.createIdentityInKernel("agent semantic domains", "agent semantic domains", XtensionSemanticIdentityRegistry.agentSemanticDomains.ordinal());}
	public final Identity cellKernel() {return F_Instantiation.identityFactory.createIdentityInKernel("cell kernel - JAVA", "cell kernel - JAVA", XtensionSemanticIdentityRegistry.cellKernel.ordinal());}
	public final Identity agent() {return F_Instantiation.identityFactory.createIdentityInKernel("agent", "agents", XtensionSemanticIdentityRegistry.agent.ordinal());}
	public final Identity nonLinearSystem() {return F_Instantiation.identityFactory.createIdentityInKernel("non-linear linearSystem", "non-linear systems", XtensionSemanticIdentityRegistry.nonLinearSystem.ordinal());}
	public final Identity linearSystem() {return F_Instantiation.identityFactory.createIdentityInKernel("linear linearSystem", "linear systems", XtensionSemanticIdentityRegistry.linearSystem.ordinal());}

}
