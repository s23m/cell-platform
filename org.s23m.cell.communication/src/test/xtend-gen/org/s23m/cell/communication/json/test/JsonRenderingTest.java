package org.s23m.cell.communication.json.test;

import java.util.HashMap;
import java.util.UUID;
import junit.framework.TestCase;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Test;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.communication.json.JsonRendering;
import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.OperatorExtensions;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.CategoryIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Command;
import org.s23m.cell.communication.xml.model.schemainstance.ContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Edge;
import org.s23m.cell.communication.xml.model.schemainstance.EdgeEnd;
import org.s23m.cell.communication.xml.model.schemainstance.FromIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsNavigableIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MaximumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MinimumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.Parameter;
import org.s23m.cell.communication.xml.model.schemainstance.Query;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticDomainNode;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticIdentityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.ToIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;
import org.s23m.cell.communication.xml.test.DefaultXmlSchemaTerminology;
import org.s23m.cell.communication.xml.test.GenericFactory;
import org.s23m.cell.communication.xml.test.IdempotentMethodInvocationHandler;
import org.s23m.cell.communication.xml.test.MethodDescriptor;
import org.s23m.cell.communication.xml.test.MockIdentity;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.models.CellPlatformAgent;

@SuppressWarnings("all")
public class JsonRenderingTest extends TestCase {
  private Identity identity = this.createIdentity();
  
  private Set set;
  
  @Override
  public void setUp() {
    S23MPlatform.boot();
    Set _createSet = this.createSet();
    this.set = _createSet;
  }
  
  @Test
  public void testSerialisationRoundTrip() {
    final ArtifactSet artifactSetModel = this.createInstanceModel();
    final String json = JsonRendering.render(artifactSetModel);
    System.out.println(("JSON:\n" + json));
  }
  
  private ArtifactSet createInstanceModel() {
    ArtifactSet _xblockexpression = null;
    {
      final Namespace s23m = NamespaceConstants.NS_S23M;
      final XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
      final Set language = CellPlatformAgent.cellMetaLanguage;
      final InstanceBuilder builder = new InstanceBuilder(s23m, terminology, language);
      final ArtifactSet result = builder.artifactSet();
      SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category = builder.category(this.set);
      ContainerIdentityReference _container = builder.container(this.set);
      IsAbstractIdentityReference _isAbstract = builder.isAbstract(this.set);
      final Model model = builder.model(_semanticIdentity, _category, _container, _isAbstract);
      Vertex _vertex = this.vertex(builder, this.set);
      OperatorExtensions.operator_add(model, _vertex);
      Visibility _visibility = this.visibility(builder, this.set);
      OperatorExtensions.operator_add(model, _visibility);
      Edge _edge = this.edge(builder, this.set);
      OperatorExtensions.operator_add(model, _edge);
      Command _command = this.command(builder, this.set);
      OperatorExtensions.operator_add(model, _command);
      Query _query = this.query(builder, this.set);
      OperatorExtensions.operator_add(model, _query);
      SuperSetReference _superSetReference = this.superSetReference(builder, this.set);
      OperatorExtensions.operator_add(model, _superSetReference);
      result.addModel(model);
      SemanticIdentityIdentityReference _semanticIdentity_1 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_1 = builder.category(this.set);
      ContainerIdentityReference _container_1 = builder.container(this.set);
      IsAbstractIdentityReference _isAbstract_1 = builder.isAbstract(this.set);
      final SemanticDomainNode semanticDomain = builder.semanticDomain(_semanticIdentity_1, _category_1, _container_1, _isAbstract_1);
      Vertex _vertex_1 = this.vertex(builder, this.set);
      OperatorExtensions.operator_add(semanticDomain, _vertex_1);
      Visibility _visibility_1 = this.visibility(builder, this.set);
      OperatorExtensions.operator_add(semanticDomain, _visibility_1);
      Edge _edge_1 = this.edge(builder, this.set);
      OperatorExtensions.operator_add(semanticDomain, _edge_1);
      Command _command_1 = this.command(builder, this.set);
      OperatorExtensions.operator_add(semanticDomain, _command_1);
      Query _query_1 = this.query(builder, this.set);
      OperatorExtensions.operator_add(semanticDomain, _query_1);
      SuperSetReference _superSetReference_1 = this.superSetReference(builder, this.set);
      OperatorExtensions.operator_add(semanticDomain, _superSetReference_1);
      final org.s23m.cell.communication.xml.model.schemainstance.Identity identity = builder.identity(this.set);
      identity.setPayload("contents");
      identity.setNameAttribute("name");
      identity.setPluralName("pluralName");
      identity.setCodeName("codeName");
      identity.setPluralCodeName("pluralCodeName");
      semanticDomain.addIdentity(identity);
      result.addSemanticDomain(semanticDomain);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  private SuperSetReference superSetReference(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    IsAbstractIdentityReference _isAbstract = builder.isAbstract(set);
    FromIdentityReference _from = builder.from(set);
    ToIdentityReference _to = builder.to(set);
    return builder.superSetReference(_semanticIdentity, _category, _isAbstract, _from, _to);
  }
  
  private Query query(final InstanceBuilder builder, final Set set) {
    Query _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
      CategoryIdentityReference _category = builder.category(set);
      final Query query = builder.query(_semanticIdentity, _category);
      SemanticIdentityIdentityReference _semanticIdentity_1 = builder.semanticIdentity(set);
      CategoryIdentityReference _category_1 = builder.category(set);
      Parameter _parameter = builder.parameter(_semanticIdentity_1, _category_1);
      query.addParameter(_parameter);
      _xblockexpression = query;
    }
    return _xblockexpression;
  }
  
  private Command command(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    return builder.command(_semanticIdentity, _category);
  }
  
  private Edge edge(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    IsAbstractIdentityReference _isAbstract = builder.isAbstract(set);
    SemanticIdentityIdentityReference _semanticIdentity_1 = builder.semanticIdentity(set);
    CategoryIdentityReference _category_1 = builder.category(set);
    IsAbstractIdentityReference _isAbstract_1 = builder.isAbstract(set);
    MinimumCardinalityIdentityReference _minCardinality = builder.minCardinality(set);
    MaximumCardinalityIdentityReference _maxCardinality = builder.maxCardinality(set);
    IsContainerIdentityReference _isContainer = builder.isContainer(set);
    IsNavigableIdentityReference _isNavigable = builder.isNavigable(set);
    EdgeEnd _fromEdgeEnd = builder.fromEdgeEnd(_semanticIdentity_1, _category_1, _isAbstract_1, _minCardinality, _maxCardinality, _isContainer, _isNavigable);
    SemanticIdentityIdentityReference _semanticIdentity_2 = builder.semanticIdentity(set);
    CategoryIdentityReference _category_2 = builder.category(set);
    IsAbstractIdentityReference _isAbstract_2 = builder.isAbstract(set);
    MinimumCardinalityIdentityReference _minCardinality_1 = builder.minCardinality(set);
    MaximumCardinalityIdentityReference _maxCardinality_1 = builder.maxCardinality(set);
    IsContainerIdentityReference _isContainer_1 = builder.isContainer(set);
    IsNavigableIdentityReference _isNavigable_1 = builder.isNavigable(set);
    EdgeEnd _edgeEnd = builder.toEdgeEnd(_semanticIdentity_2, _category_2, _isAbstract_2, _minCardinality_1, _maxCardinality_1, _isContainer_1, _isNavigable_1);
    return builder.edge(_semanticIdentity, _category, _isAbstract, _fromEdgeEnd, _edgeEnd);
  }
  
  private Visibility visibility(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    IsAbstractIdentityReference _isAbstract = builder.isAbstract(set);
    FromIdentityReference _from = builder.from(set);
    ToIdentityReference _to = builder.to(set);
    return builder.visibility(_semanticIdentity, _category, _isAbstract, _from, _to);
  }
  
  private Vertex vertex(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    IsAbstractIdentityReference _isAbstract = builder.isAbstract(set);
    MaximumCardinalityIdentityReference _maxCardinality = builder.maxCardinality(set);
    return builder.vertex(_semanticIdentity, _category, _isAbstract, _maxCardinality);
  }
  
  private Set createSet() {
    Set _xblockexpression = null;
    {
      final IdempotentMethodInvocationHandler identityHandler = new IdempotentMethodInvocationHandler(this.identity);
      final IdempotentMethodInvocationHandler categoryHandler = new IdempotentMethodInvocationHandler(org.s23m.cell.api.Query.vertex);
      final IdempotentMethodInvocationHandler valueHandler = new IdempotentMethodInvocationHandler(org.s23m.cell.api.Query.vertex);
      MethodDescriptor _methodDescriptor = new MethodDescriptor("identity");
      Pair<MethodDescriptor, IdempotentMethodInvocationHandler> _mappedTo = Pair.<MethodDescriptor, IdempotentMethodInvocationHandler>of(_methodDescriptor, identityHandler);
      MethodDescriptor _methodDescriptor_1 = new MethodDescriptor("category");
      Pair<MethodDescriptor, IdempotentMethodInvocationHandler> _mappedTo_1 = Pair.<MethodDescriptor, IdempotentMethodInvocationHandler>of(_methodDescriptor_1, categoryHandler);
      MethodDescriptor _methodDescriptor_2 = new MethodDescriptor("value", Set.class);
      Pair<MethodDescriptor, IdempotentMethodInvocationHandler> _mappedTo_2 = Pair.<MethodDescriptor, IdempotentMethodInvocationHandler>of(_methodDescriptor_2, valueHandler);
      final HashMap<MethodDescriptor, IdempotentMethodInvocationHandler> handlers = CollectionLiterals.<MethodDescriptor, IdempotentMethodInvocationHandler>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2);
      _xblockexpression = GenericFactory.<Set>createInstance(Set.class, handlers);
    }
    return _xblockexpression;
  }
  
  private MockIdentity createIdentity() {
    MockIdentity _xblockexpression = null;
    {
      final UUID identifier = UUID.randomUUID();
      final UUID uniqueRepresentationReference = UUID.randomUUID();
      _xblockexpression = new MockIdentity(identifier, uniqueRepresentationReference);
    }
    return _xblockexpression;
  }
}
