package org.s23m.cell.communication.xml;

@SuppressWarnings("all")
public class NamespaceExtensions {
  public static String xmlns(final String name) {
    return NamespaceExtensions.qualifiedName("xmlns", name);
  }
  
  public static String qualifiedName(final String namespacePrefix, final String name) {
    return ((namespacePrefix + ":") + name);
  }
}
