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

package org.s23m.cell.api;

import org.s23m.cell.Set;
import org.s23m.cell.core.F_Query;

// functionality is scheduled to be removed
// TODO remove all usage of this class

public final class ProperClasses {

	public final Set vertex;
	public final Set edgeEnd;
	public final Set arrow;
	public final Set edge;
	public final Set superSetReference;
	public final Set visibility;
	public final Set graph;

	public ProperClasses() {
		vertex = F_Query.vertex();
		edgeEnd = F_Query.edgeEnd();
		arrow = F_Query.arrow();
		edge = F_Query.edge();
		superSetReference = F_Query.superSetReference();
		visibility = F_Query.visibility();
		graph = F_Query.graph();
	}

}
