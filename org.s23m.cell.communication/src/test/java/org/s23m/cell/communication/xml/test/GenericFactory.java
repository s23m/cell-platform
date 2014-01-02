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
import java.util.Arrays;
import java.util.Map;

public class GenericFactory {
	
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(Class<T> type, Map<MethodDescriptor, ? extends MethodInvocationHandler> registeredHandlers) {
		validateHandlers(type, registeredHandlers);
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, createInvocationHandler(registeredHandlers));
	}
	
	private static <T> void validateHandlers(Class<T> type, Map<MethodDescriptor, ? extends MethodInvocationHandler> handlers) {
		for (MethodDescriptor descriptor: handlers.keySet()) {
			String name = descriptor.getName();
			Class<?>[] parameterTypes = descriptor.getParameterTypes();
			try {
				type.getMethod(name, parameterTypes);
			} catch (Exception e) {
				throw new IllegalArgumentException("Could not find method with name '" + name + "' and parameter types " + Arrays.asList(parameterTypes), e);
			}	
		}
	}

	private static InvocationHandler createInvocationHandler(final Map<MethodDescriptor, ? extends MethodInvocationHandler> registeredHandlers) {
		return new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				for (Map.Entry<MethodDescriptor, ? extends MethodInvocationHandler> entry: registeredHandlers.entrySet()) {
					MethodDescriptor descriptor = entry.getKey();
					if (descriptor.matches(method)) {
						final MethodInvocationHandler handler = entry.getValue();
						return handler.invoke(proxy, method, args);
					}
				}
				throw new IllegalArgumentException("No matching invocation handler found for method '" + method.getName() + "'");
			}
		};
	}
}
