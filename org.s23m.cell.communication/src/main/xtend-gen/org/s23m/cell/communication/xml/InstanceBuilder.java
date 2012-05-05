package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.schemainstance.Command;
import org.s23m.cell.communication.xml.schemainstance.Edge;
import org.s23m.cell.communication.xml.schemainstance.EdgeEnd;
import org.s23m.cell.communication.xml.schemainstance.IdentityReference;
import org.s23m.cell.communication.xml.schemainstance.Model;
import org.s23m.cell.communication.xml.schemainstance.Query;
import org.s23m.cell.communication.xml.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.schemainstance.Vertex;
import org.s23m.cell.communication.xml.schemainstance.Visibility;

@SuppressWarnings("all")
public class InstanceBuilder {
  private ArtifactSet artifactSet;
  
  private Namespace namespace;
  
  private XmlSchemaTerminology terminology;
  
  public InstanceBuilder(final Namespace namespace, final XmlSchemaTerminology terminology, final String languageIdentifier, final Procedure1<? super ArtifactSet> initialiser) {
      this.namespace = namespace;
      this.terminology = terminology;
      ArtifactSet _artifactSet = new ArtifactSet(namespace, terminology, languageIdentifier);
      this.artifactSet = _artifactSet;
      initialiser.apply(this.artifactSet);
  }
  
  public void model(final Procedure1<? super Model> initialiser) {
      Model _model = new Model(this.namespace, this.terminology);
      final Model result = _model;
      initialiser.apply(result);
  }
  
  public void vertex(final Procedure1<? super Vertex> initialiser) {
      Vertex _vertex = new Vertex(this.namespace, this.terminology);
      final Vertex result = _vertex;
      initialiser.apply(result);
  }
  
  public void visibility(final Procedure1<? super Visibility> initialiser) {
      Visibility _visibility = new Visibility(this.namespace, this.terminology);
      final Visibility result = _visibility;
      initialiser.apply(result);
  }
  
  public void edge(final Procedure1<? super Edge> initialiser) {
      Edge _edge = new Edge(this.namespace, this.terminology);
      final Edge result = _edge;
      initialiser.apply(result);
  }
  
  public void edgeEnd(final Procedure1<? super EdgeEnd> initialiser) {
      EdgeEnd _edgeEnd = new EdgeEnd(this.namespace, this.terminology);
      final EdgeEnd result = _edgeEnd;
      initialiser.apply(result);
  }
  
  public IdentityReference identityReference(final String name, final String uniqueRepresentationReference, final String identifier) {
    IdentityReference _identityReference = new IdentityReference(this.namespace, this.terminology, name, uniqueRepresentationReference, identifier);
    return _identityReference;
  }
  
  public void superSetReference(final Procedure1<? super SuperSetReference> initialiser) {
      SuperSetReference _superSetReference = new SuperSetReference(this.namespace, this.terminology);
      final SuperSetReference result = _superSetReference;
      initialiser.apply(result);
  }
  
  public void command(final Procedure1<? super Command> initialiser) {
      Command _command = new Command(this.namespace, this.terminology);
      final Command result = _command;
      initialiser.apply(result);
  }
  
  public void query(final Procedure1<? super Query> initialiser) {
      Query _query = new Query(this.namespace, this.terminology);
      final Query result = _query;
      initialiser.apply(result);
  }
}
