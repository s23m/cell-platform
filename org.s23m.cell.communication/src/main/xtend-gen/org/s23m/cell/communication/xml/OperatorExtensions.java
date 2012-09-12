package org.s23m.cell.communication.xml;

import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.Command;
import org.s23m.cell.communication.xml.model.schemainstance.Edge;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.Parameter;
import org.s23m.cell.communication.xml.model.schemainstance.Query;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticDomainNode;
import org.s23m.cell.communication.xml.model.schemainstance.Structure;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;

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
  
  public static void operator_add(final ArtifactSet a, final SemanticDomainNode s) {
    a.addSemanticDomain(s);
  }
  
  /**
   * Structure
   */
  public static void operator_add(final Structure s, final Vertex v) {
    s.addVertex(v);
  }
  
  public static void operator_add(final Structure s, final Edge e) {
    s.addEdge(e);
  }
  
  public static void operator_add(final Structure s, final Command c) {
    s.addCommand(c);
  }
  
  public static void operator_add(final Structure s, final Query q) {
    s.addQuery(q);
  }
  
  public static void operator_add(final Structure s, final Visibility v) {
    s.addVisibility(v);
  }
  
  public static void operator_add(final Structure s, final SuperSetReference superSetReference) {
    s.addSuperSetReference(superSetReference);
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
