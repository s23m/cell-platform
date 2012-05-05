package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.s23m.cell.communication.xml.dom.Namespace;

@SuppressWarnings("all")
public class InstanceConstants {
  public static String S23M = "s23m";
  
  public static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012";
  
  public static Namespace NS_S23M = new Function0<Namespace>() {
    public Namespace apply() {
      Namespace _namespace = new Namespace(InstanceConstants.S23M, InstanceConstants.S23M_SCHEMA);
      return _namespace;
    }
  }.apply();
}
