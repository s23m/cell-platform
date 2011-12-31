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
 * SoftMetaWare Limited (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.core;

import org.s23m.cell.Set;
import org.s23m.cell.api.VisitorFunction;

public class F_Computation {

	public static void initializeWalk(final Set startSet, final VisitorFunction visitorFunction) {
		visitorFunction.initialize(startSet);
	}

	public static	Set walkDownThenRight(final Set location, final VisitorFunction visitorFunction) {
		final Set content = location.filterInstances();
		for (final Set element : content) {
			walkDownThenRight(element, visitorFunction) ;
		}
		return visitorFunction.compute(location);
	}

	public static	Set walkDownThenLeft(final Set location, final VisitorFunction visitorFunction) {
		final Set content = location.filterInstances();
		Set element = content.extractLast();
		for (int i = 0; i < content.size()  ; i++) {
			walkDownThenLeft(element, visitorFunction) ;
			element = content.extractPrevious(element);
		}
		return visitorFunction.compute(location);
	}

	public static	Set walkRightThenDown(final Set location, final VisitorFunction visitorFunction) {
		final Set content = location.filterInstances();
		for (final Set element : content) {
			visitorFunction.compute(element);
		}
		walkRightThenDown(location, visitorFunction) ;
		return visitorFunction.compute(location);
	}
	public static	Set walkLeftThenDown(final Set location, final VisitorFunction visitorFunction) {
		final Set content = location.filterInstances();
		Set element = content.extractLast();
		visitorFunction.compute(element);
		for (int i = 0; i < content.size()  ; i++) {
			element = content.extractPrevious(element);
			visitorFunction.compute(element);
		}
		walkLeftThenDown(location, visitorFunction) ;
		return visitorFunction.compute(location);
	}
}
