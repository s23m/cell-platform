package org.s23m.cell.communication.xml.test;

import java.lang.reflect.Method;
import org.s23m.cell.communication.xml.test.MethodInvocationHandler;

@SuppressWarnings("all")
public class IdempotentMethodInvocationHandler implements MethodInvocationHandler {
  private Object result;
  
  public IdempotentMethodInvocationHandler(final Object result) {
    this.result = result;
  }
  
  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args) {
    return this.result;
  }
}
