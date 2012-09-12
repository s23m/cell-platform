package org.s23m.cell.communication.xml.test;

import com.google.common.base.Charsets;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Test;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.OperatorExtensions;
import org.s23m.cell.communication.xml.XmlRendering;
import org.s23m.cell.communication.xml.XmlSchemaFactory;
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
import org.s23m.cell.communication.xml.sax.ArtifactSetElementHandler;
import org.s23m.cell.communication.xml.test.DefaultXmlSchemaTerminology;
import org.s23m.cell.communication.xml.test.GenericFactory;
import org.s23m.cell.communication.xml.test.IdempotentMethodInvocationHandler;
import org.s23m.cell.communication.xml.test.MethodDescriptor;
import org.s23m.cell.communication.xml.test.MockIdentity;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.models.CellPlatformAgent;
import org.xml.sax.SAXException;

@SuppressWarnings("all")
public class RoundTrippingTest extends TestCase {
  private Identity identity = new Function0<Identity>() {
    public Identity apply() {
      MockIdentity _createIdentity = RoundTrippingTest.this.createIdentity();
      return _createIdentity;
    }
  }.apply();
  
  private Set set;
  
  public void setUp() {
    S23MPlatform.boot();
    Set _createSet = this.createSet();
    this.set = _createSet;
  }
  
  @Test
  public void testSerialisationRoundTrip() {
    final ArtifactSet artifactSetModel = this.createInstanceModel();
    final String xml = XmlRendering.render(artifactSetModel);
    final ArtifactSet deserialised = this.deserialise(xml);
    final String renderedDeserialisedModel = XmlRendering.render(deserialised);
    Assert.assertEquals(xml, renderedDeserialisedModel);
  }
  
  private ArtifactSet deserialise(final String xml) {
    ArtifactSet _xblockexpression = null;
    {
      XmlSchemaFactory _xmlSchemaFactory = new XmlSchemaFactory();
      final XmlSchemaFactory schemaFactory = _xmlSchemaFactory;
      final XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
      final Schema schema = schemaFactory.createSchema(terminology);
      final SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setNamespaceAware(true);
      factory.setSchema(schema);
      ArtifactSet _xtrycatchfinallyexpression = null;
      try {
        ArtifactSet _xblockexpression_1 = null;
        {
          final SAXParser saxParser = factory.newSAXParser();
          byte[] _bytes = xml.getBytes(Charsets.UTF_8);
          ByteArrayInputStream _byteArrayInputStream = new ByteArrayInputStream(_bytes);
          final ByteArrayInputStream is = _byteArrayInputStream;
          ArtifactSetElementHandler _artifactSetElementHandler = new ArtifactSetElementHandler(NamespaceConstants.NS_S23M, terminology, CellPlatformAgent.cellMetaLanguage);
          final ArtifactSetElementHandler handler = _artifactSetElementHandler;
          saxParser.parse(is, handler);
          ArtifactSet _result = handler.getResult();
          _xblockexpression_1 = (_result);
        }
        _xtrycatchfinallyexpression = _xblockexpression_1;
      } catch (final Throwable _t) {
        if (_t instanceof ParserConfigurationException) {
          final ParserConfigurationException e1 = (ParserConfigurationException)_t;
          IllegalStateException _illegalStateException = new IllegalStateException("De-serialisation failed", e1);
          throw _illegalStateException;
        } else if (_t instanceof SAXException) {
          final SAXException e1_1 = (SAXException)_t;
          IllegalStateException _illegalStateException_1 = new IllegalStateException("De-serialisation failed", e1_1);
          throw _illegalStateException_1;
        } else if (_t instanceof IOException) {
          final IOException e = (IOException)_t;
          IllegalStateException _illegalStateException_2 = new IllegalStateException("De-serialisation failed", e);
          throw _illegalStateException_2;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      _xblockexpression = (_xtrycatchfinallyexpression);
    }
    return _xblockexpression;
  }
  
  private ArtifactSet createInstanceModel() {
    ArtifactSet _xblockexpression = null;
    {
      final Namespace s23m = NamespaceConstants.NS_S23M;
      final XmlSchemaTerminology terminology = DefaultXmlSchemaTerminology.getInstance();
      final Set language = CellPlatformAgent.cellMetaLanguage;
      InstanceBuilder _instanceBuilder = new InstanceBuilder(s23m, terminology, language);
      final InstanceBuilder builder = _instanceBuilder;
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
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  private SuperSetReference superSetReference(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    IsAbstractIdentityReference _isAbstract = builder.isAbstract(set);
    FromIdentityReference _from = builder.from(set);
    ToIdentityReference _to = builder.to(set);
    SuperSetReference _superSetReference = builder.superSetReference(_semanticIdentity, _category, _isAbstract, _from, _to);
    return _superSetReference;
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
      _xblockexpression = (query);
    }
    return _xblockexpression;
  }
  
  private Command command(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    Command _command = builder.command(_semanticIdentity, _category);
    return _command;
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
    Edge _edge = builder.edge(_semanticIdentity, _category, _isAbstract, _fromEdgeEnd, _edgeEnd);
    return _edge;
  }
  
  private Visibility visibility(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    IsAbstractIdentityReference _isAbstract = builder.isAbstract(set);
    FromIdentityReference _from = builder.from(set);
    ToIdentityReference _to = builder.to(set);
    Visibility _visibility = builder.visibility(_semanticIdentity, _category, _isAbstract, _from, _to);
    return _visibility;
  }
  
  private Vertex vertex(final InstanceBuilder builder, final Set set) {
    SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(set);
    CategoryIdentityReference _category = builder.category(set);
    IsAbstractIdentityReference _isAbstract = builder.isAbstract(set);
    MaximumCardinalityIdentityReference _maxCardinality = builder.maxCardinality(set);
    Vertex _vertex = builder.vertex(_semanticIdentity, _category, _isAbstract, _maxCardinality);
    return _vertex;
  }
  
  private Set createSet() {
    Set _xblockexpression = null;
    {
      IdempotentMethodInvocationHandler _idempotentMethodInvocationHandler = new IdempotentMethodInvocationHandler(this.identity);
      final IdempotentMethodInvocationHandler identityHandler = _idempotentMethodInvocationHandler;
      IdempotentMethodInvocationHandler _idempotentMethodInvocationHandler_1 = new IdempotentMethodInvocationHandler(org.s23m.cell.api.Query.vertex);
      final IdempotentMethodInvocationHandler categoryHandler = _idempotentMethodInvocationHandler_1;
      IdempotentMethodInvocationHandler _idempotentMethodInvocationHandler_2 = new IdempotentMethodInvocationHandler(org.s23m.cell.api.Query.vertex);
      final IdempotentMethodInvocationHandler valueHandler = _idempotentMethodInvocationHandler_2;
      MethodDescriptor _methodDescriptor = new MethodDescriptor("identity");
      Pair<MethodDescriptor,IdempotentMethodInvocationHandler> _mappedTo = Pair.<MethodDescriptor, IdempotentMethodInvocationHandler>of(_methodDescriptor, identityHandler);
      MethodDescriptor _methodDescriptor_1 = new MethodDescriptor("category");
      Pair<MethodDescriptor,IdempotentMethodInvocationHandler> _mappedTo_1 = Pair.<MethodDescriptor, IdempotentMethodInvocationHandler>of(_methodDescriptor_1, categoryHandler);
      MethodDescriptor _methodDescriptor_2 = new MethodDescriptor("value", Set.class);
      Pair<MethodDescriptor,IdempotentMethodInvocationHandler> _mappedTo_2 = Pair.<MethodDescriptor, IdempotentMethodInvocationHandler>of(_methodDescriptor_2, valueHandler);
      final HashMap<MethodDescriptor,IdempotentMethodInvocationHandler> handlers = CollectionLiterals.<MethodDescriptor, IdempotentMethodInvocationHandler>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2);
      Set _createInstance = GenericFactory.<Set>createInstance(Set.class, handlers);
      _xblockexpression = (_createInstance);
    }
    return _xblockexpression;
  }
  
  private MockIdentity createIdentity() {
    MockIdentity _xblockexpression = null;
    {
      final UUID identifier = UUID.randomUUID();
      final UUID uniqueRepresentationReference = UUID.randomUUID();
      MockIdentity _mockIdentity = new MockIdentity(identifier, uniqueRepresentationReference);
      _xblockexpression = (_mockIdentity);
    }
    return _xblockexpression;
  }
}
