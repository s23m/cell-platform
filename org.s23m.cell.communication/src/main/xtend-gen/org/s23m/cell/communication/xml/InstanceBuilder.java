package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.schemainstance.CategoryIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.Command;
import org.s23m.cell.communication.xml.schemainstance.ContainerIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.Edge;
import org.s23m.cell.communication.xml.schemainstance.EdgeEnd;
import org.s23m.cell.communication.xml.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.Model;
import org.s23m.cell.communication.xml.schemainstance.Query;
import org.s23m.cell.communication.xml.schemainstance.SemanticIdentityIdentityReference;
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
  
  public ArtifactSet build() {
    return this.artifactSet;
  }
  
  public Model model(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final ContainerIdentityReference container, final IsAbstractIdentityReference isAbstract, final Procedure1<? super Model> initialiser) {
    Model _xblockexpression = null;
    {
      Model _model = new Model(this.namespace, this.terminology);
      final Model result = _model;
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      result.setContainer(container);
      result.setIsAbstract(isAbstract);
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public Vertex vertex(final Procedure1<? super Vertex> initialiser) {
    Vertex _xblockexpression = null;
    {
      Vertex _vertex = new Vertex(this.namespace, this.terminology);
      final Vertex result = _vertex;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public Visibility visibility(final Procedure1<? super Visibility> initialiser) {
    Visibility _xblockexpression = null;
    {
      Visibility _visibility = new Visibility(this.namespace, this.terminology);
      final Visibility result = _visibility;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public Edge edge(final Procedure1<? super Edge> initialiser) {
    Edge _xblockexpression = null;
    {
      Edge _edge = new Edge(this.namespace, this.terminology);
      final Edge result = _edge;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public EdgeEnd edgeEnd(final Procedure1<? super EdgeEnd> initialiser) {
    EdgeEnd _xblockexpression = null;
    {
      EdgeEnd _edgeEnd = new EdgeEnd(this.namespace, this.terminology);
      final EdgeEnd result = _edgeEnd;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public SuperSetReference superSetReference(final Procedure1<? super SuperSetReference> initialiser) {
    SuperSetReference _xblockexpression = null;
    {
      SuperSetReference _superSetReference = new SuperSetReference(this.namespace, this.terminology);
      final SuperSetReference result = _superSetReference;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public Command command(final Procedure1<? super Command> initialiser) {
    Command _xblockexpression = null;
    {
      Command _command = new Command(this.namespace, this.terminology);
      final Command result = _command;
      initialiser.apply(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public void query(final Procedure1<? super Query> initialiser) {
      Query _query = new Query(this.namespace, this.terminology);
      final Query result = _query;
      initialiser.apply(result);
  }
  
  public SemanticIdentityIdentityReference semanticIdentity(final String uniqueRepresentationReference, final String identifier) {
    SemanticIdentityIdentityReference _semanticIdentityIdentityReference = new SemanticIdentityIdentityReference(this.namespace, this.terminology, uniqueRepresentationReference, identifier);
    return _semanticIdentityIdentityReference;
  }
  
  public CategoryIdentityReference category(final String uniqueRepresentationReference, final String identifier) {
    CategoryIdentityReference _categoryIdentityReference = new CategoryIdentityReference(this.namespace, this.terminology, uniqueRepresentationReference, identifier);
    return _categoryIdentityReference;
  }
  
  public ContainerIdentityReference container(final String uniqueRepresentationReference, final String identifier) {
    ContainerIdentityReference _containerIdentityReference = new ContainerIdentityReference(this.namespace, this.terminology, uniqueRepresentationReference, identifier);
    return _containerIdentityReference;
  }
  
  public IsAbstractIdentityReference isAbstract(final String uniqueRepresentationReference, final String identifier) {
    IsAbstractIdentityReference _isAbstractIdentityReference = new IsAbstractIdentityReference(this.namespace, this.terminology, uniqueRepresentationReference, identifier);
    return _isAbstractIdentityReference;
  }
}
