package org.s23m.cell.communication.xml;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DefaultXmlSchemaTerminology {

	private static final XmlSchemaTerminology terminology = (XmlSchemaTerminology) Proxy.newProxyInstance(
		XmlSchemaTerminology.class.getClassLoader(),
        new Class[] { XmlSchemaTerminology.class },
        new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				return method.getName();
			}
		}
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
}
