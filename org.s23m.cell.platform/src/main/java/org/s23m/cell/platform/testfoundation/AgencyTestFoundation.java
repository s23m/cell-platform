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
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.platform.testfoundation;

import org.s23m.cell.Set;


public class AgencyTestFoundation {

	public static Set instantiateFeature() {
		final Set snowy = org.s23m.cell.platform.api.Instantiation.addAgent("Snowy", "set of Snowy");
			org.s23m.cell.platform.api.Instantiation.addStage("dev1", "dev1s", snowy);
			org.s23m.cell.platform.api.Instantiation.addStage("test1", "test1s", snowy);
			org.s23m.cell.platform.api.Instantiation.addStage("prod1", "prod1s", snowy);
		final Set scruffy = org.s23m.cell.platform.api.Instantiation.addAgent("Scruffy", "set of Scruffy");
			org.s23m.cell.platform.api.Instantiation.addStage("dev2", "dev2s", scruffy);
			org.s23m.cell.platform.api.Instantiation.addStage("test2", "test2s", scruffy);
			org.s23m.cell.platform.api.Instantiation.addStage("prod2", "prod2s", scruffy);
		final Set louise = org.s23m.cell.platform.api.Instantiation.addAgent("Louise", "set of Louise");

		return snowy;
	}
}
