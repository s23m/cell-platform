package org.s23m.cell.communication.xml;

import org.s23m.cell.communication.xml.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.schemainstance.Command;
import org.s23m.cell.communication.xml.schemainstance.Edge;
import org.s23m.cell.communication.xml.schemainstance.Model;
import org.s23m.cell.communication.xml.schemainstance.Parameter;
import org.s23m.cell.communication.xml.schemainstance.Query;
import org.s23m.cell.communication.xml.schemainstance.SemanticDomain;
import org.s23m.cell.communication.xml.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.schemainstance.Vertex;
import org.s23m.cell.communication.xml.schemainstance.Visibility;

/**
 * Syntactic sugar for operations
 */
@SuppressWarnings("all")
public class OperatorExtensions {
  /**
   * ArtifactSet
   */
  public static void operator_add(final ArtifactSet a, final Model m) {
    a.addModel(m);
  }
  
  public static void operator_add(final ArtifactSet a, final SemanticDomain s) {
    a.addSemanticDomain(s);
  }
  
  /**
   * Model
   */
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
  
  /**
   * Function
   */
  public static void operator_add(final Command c, final Parameter p) {
    c.addParameter(p);
  }
  
  public static void operator_add(final Query q, final Parameter p) {
    q.addParameter(p);
  }
}
