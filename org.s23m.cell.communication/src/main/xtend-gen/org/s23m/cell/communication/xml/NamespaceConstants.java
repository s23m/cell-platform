package org.s23m.cell.communication.xml;

import org.s23m.cell.communication.xml.model.dom.Namespace;

@SuppressWarnings("all")
public class NamespaceConstants {
  /**
   * Schema
   */
  public static String S23M = "s";
  
  public static String S23M_SCHEMA_URI = "http://schemas.s23m.org/serialization/2012";
  
  public static Namespace NS_S23M = new Namespace(NamespaceConstants.S23M, NamespaceConstants.S23M_SCHEMA_URI);
  
  /**
   * Instance
   */
  public static String INSTANCE_NAMESPACE_PREFIX = "xsi";
  
  public static String INSTANCE_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema-instance";
}
