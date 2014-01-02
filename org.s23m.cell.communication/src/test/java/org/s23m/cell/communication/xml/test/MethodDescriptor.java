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
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml.test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodDescriptor {
	private String name;
	
	private Class<?>[] parameterTypes;
	
	public MethodDescriptor(String name, Class<?>... parameterTypes) {
		this.name = name;
		this.parameterTypes = parameterTypes;
	}
	
	public String getName() {
		return name;
	}
	
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}
	
	public boolean matches(Method method) {
		return method.getName().equals(name) &&
				Arrays.asList(method.getParameterTypes()).equals(Arrays.asList(parameterTypes));
	}
}