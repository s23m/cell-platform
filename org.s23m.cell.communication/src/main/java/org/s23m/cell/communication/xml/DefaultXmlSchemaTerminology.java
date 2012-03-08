package org.s23m.cell.communication.xml;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

public class DefaultXmlSchemaTerminology {

	private static final InvocationHandler invocationHandler = new InvocationHandler() {
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			return method.getName();
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
			try {
				Object o = invocationHandler.invoke(terminology, method, new Object[0]);
				String term = (String) o;
				result.add(term);
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}
}
