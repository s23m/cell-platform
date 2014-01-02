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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;

public class DefaultXmlSchemaTerminology {
	
	public static final String IS_MACHINE_READABLE_METHOD = "isMachineEncoding";

	private static final InvocationHandler invocationHandler = new InvocationHandler() {
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			final String methodName = method.getName();
			if (methodName.equals(IS_MACHINE_READABLE_METHOD)) {
				// hide name attribute on identifierReference elements
				return true;
			} else {
				return methodName;
			}
		}
	};
	
	private static final XmlSchemaTerminology terminology = (XmlSchemaTerminology) Proxy.newProxyInstance(
		XmlSchemaTerminology.class.getClassLoader(),
        new Class[] { XmlSchemaTerminology.class },
        invocationHandler
	);
	
	private DefaultXmlSchemaTerminology() {
		// nothing to initialise
	}
	
	/**
	 * Returns a {@link XmlSchemaTerminology} which supplies the method name for each request  
	 */
	public static XmlSchemaTerminology getInstance() {
		return terminology;
	}
	
	public static Set<String> getAllTerms() {
		Method[] methods = XmlSchemaTerminology.class.getMethods();
		Set<String> result = new HashSet<String>();
		for (Method method : methods) {
			final String methodName = method.getName();
			if (!methodName.equals(IS_MACHINE_READABLE_METHOD)) {
				try {
					Object o = invocationHandler.invoke(terminology, method, new Object[0]);
					String term = (String) o;
					result.add(term);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			}
		}
		return result;
	}
}
