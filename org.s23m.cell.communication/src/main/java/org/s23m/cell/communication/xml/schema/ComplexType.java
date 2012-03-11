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
 * The Original Code is Cell.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml.schema;

import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;
import org.s23m.cell.communication.xml.dom.WrapperNode;

public final class ComplexType extends WrapperNode {
	Namespace targetNamespace; // TODO
	
	private ComplexType(Node child, Namespace namespace) {
		super(namespace, "complexType", child);
	}
	
	public ComplexType(Namespace namespace, Sequence child) {
		this(child, namespace);
	}
	
	public ComplexType(Namespace namespace, ComplexContent child) {
		this(child, namespace);
	}
}