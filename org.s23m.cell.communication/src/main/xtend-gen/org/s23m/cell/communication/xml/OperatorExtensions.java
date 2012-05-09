package org.s23m.cell.communication.xml;

import java.util.LinkedHashMap;
import org.eclipse.xtext.xbase.lib.Pair;
import org.s23m.cell.communication.xml.schemainstance.Command;
import org.s23m.cell.communication.xml.schemainstance.Edge;
import org.s23m.cell.communication.xml.schemainstance.Model;
import org.s23m.cell.communication.xml.schemainstance.Query;
import org.s23m.cell.communication.xml.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.schemainstance.Vertex;
import org.s23m.cell.communication.xml.schemainstance.Visibility;

/**
 * Contains syntactic sugar for operations so that we do not
 * need to pollute the domain model with foreign types
 */
@SuppressWarnings("all")
public class OperatorExtensions {
  public static <A extends Object, B extends Object> B operator_add(final LinkedHashMap<A,B> map, final Pair<A,B> pair) {
    A _key = pair.getKey();
    B _value = pair.getValue();
    B _put = map.put(_key, _value);
    return _put;
  }
  
  public static <A extends Object, B extends Object> void operator_add(final LinkedHashMap<A,B> map, final LinkedHashMap<A,B> additions) {
    map.putAll(additions);
  }
  
  public static void operator_add(final Model m, final Vertex v) {
    m.addVertex(v);
  }
  
  public static void operator_add(final Model m, final Edge e) {
    m.addEdge(e);
  }
  
  public static void operator_add(final Model m, final Command c) {
    m.addCommand(c);
  }
  
  public static void operator_add(final Model m, final Query q) {
    m.addQuery(q);
  }
  
  public static void operator_add(final Model m, final Visibility v) {
    m.addVisibility(v);
  }
  
  public static void operator_add(final Model m, final SuperSetReference s) {
    m.addSuperSetReference(s);
  }
}
