package org.s23m.cell.communication.xml.test

import java.lang.reflect.Method

class IdempotentMethodInvocationHandler implements MethodInvocationHandler {
	
	private Object result
	
	new(Object result) {
		this.result = result
	}
	
	override invoke(Object proxy, Method method, Object[] args) {
		result
	}
}