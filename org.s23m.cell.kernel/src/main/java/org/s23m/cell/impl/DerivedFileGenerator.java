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
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.impl;

import org.s23m.cell.Set;

/**
 * Enables components which produce textual output to
 * integrate with S23M's support for derived artefacts.
 */
public interface DerivedFileGenerator {

	/**
	 * Generates textual output in the form of a String, using
	 * <code>inputInstance</code> as input to the template given
	 * by <code>derivationTemplate</code>.
	 * 
	 * @param inputInstance
	 * @param derivationTemplate
	 * @return generated output
	 */
	String generate(Set inputInstance, String derivationTemplate);

}
