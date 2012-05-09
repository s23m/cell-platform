package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class NamespaceExtensions {
  public static String xmlns(final String name) {
    String _qualifiedName = NamespaceExtensions.qualifiedName("xmlns", name);
    return _qualifiedName;
  }
  
  public static String qualifiedName(final String namespacePrefix, final String name) {
    String _operator_plus = StringExtensions.operator_plus(namespacePrefix, ":");
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, name);
    return _operator_plus_1;
  }
}
