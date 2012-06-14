package org.s23m.cell.communication.xml;

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
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Test;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.communication.xml.DefaultXmlSchemaTerminology;
import org.s23m.cell.communication.xml.GenericFactory;
import org.s23m.cell.communication.xml.IdempotentMethodInvocationHandler;
import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.MethodDescriptor;
import org.s23m.cell.communication.xml.MockIdentity;
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
import org.s23m.cell.communication.xml.model.schemainstance.SemanticIdentityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.ToIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;
import org.s23m.cell.communication.xml.sax.ArtifactSetElementHandler;
import org.s23m.cell.platform.S23MPlatform;
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
      ArtifactSet _createInstanceModel = this.createInstanceModel();
      final ArtifactSet model = _createInstanceModel;
      String _render = XmlRendering.render(model);
      final String xml = _render;
      ArtifactSet _deserialise = this.deserialise(xml);
      final ArtifactSet deserialised = _deserialise;
      String _render_1 = XmlRendering.render(deserialised);
      final String renderedDeserialisedModel = _render_1;
      Assert.assertEquals(xml, renderedDeserialisedModel);
  }
  
  private ArtifactSet deserialise(final String xml) {
    ArtifactSet _xblockexpression = null;
    {
      XmlSchemaFactory _xmlSchemaFactory = new XmlSchemaFactory();
      final XmlSchemaFactory schemaFactory = _xmlSchemaFactory;
      XmlSchemaTerminology _instance = DefaultXmlSchemaTerminology.getInstance();
      final XmlSchemaTerminology terminology = _instance;
      Schema _createSchema = schemaFactory.createSchema(terminology);
      final Schema schema = _createSchema;
      SAXParserFactory _newInstance = SAXParserFactory.newInstance();
      final SAXParserFactory factory = _newInstance;
      factory.setNamespaceAware(true);
      factory.setSchema(schema);
      ArtifactSet _xtrycatchfinallyexpression = null;
      try {
        ArtifactSet _xblockexpression_1 = null;
        {
          SAXParser _newSAXParser = factory.newSAXParser();
          final SAXParser saxParser = _newSAXParser;
          byte[] _bytes = xml.getBytes(Charsets.UTF_8);
          ByteArrayInputStream _byteArrayInputStream = new ByteArrayInputStream(_bytes);
          final ByteArrayInputStream is = _byteArrayInputStream;
          ArtifactSetElementHandler _artifactSetElementHandler = new ArtifactSetElementHandler(NamespaceConstants.NS_S23M, terminology, "ENGLISH");
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
      XmlSchemaTerminology _instance = DefaultXmlSchemaTerminology.getInstance();
      final XmlSchemaTerminology terminology = _instance;
      final String languageIdentifier = "ENGLISH";
      InstanceBuilder _instanceBuilder = new InstanceBuilder(s23m, terminology, languageIdentifier);
      final InstanceBuilder builder = _instanceBuilder;
      SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category = builder.category(this.set);
      ContainerIdentityReference _container = builder.container(this.set);
      IsAbstractIdentityReference _isAbstract = builder.isAbstract(this.set);
      Model _model = builder.model(_semanticIdentity, _category, _container, _isAbstract);
      final Model model = _model;
      SemanticIdentityIdentityReference _semanticIdentity_1 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_1 = builder.category(this.set);
      IsAbstractIdentityReference _isAbstract_1 = builder.isAbstract(this.set);
      MaximumCardinalityIdentityReference _maxCardinality = builder.maxCardinality(this.set);
      Vertex _vertex = builder.vertex(_semanticIdentity_1, _category_1, _isAbstract_1, _maxCardinality);
      OperatorExtensions.operator_add(model, _vertex);
      SemanticIdentityIdentityReference _semanticIdentity_2 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_2 = builder.category(this.set);
      IsAbstractIdentityReference _isAbstract_2 = builder.isAbstract(this.set);
      FromIdentityReference _from = builder.from(this.set);
      ToIdentityReference _to = builder.to(this.set);
      Visibility _visibility = builder.visibility(_semanticIdentity_2, _category_2, _isAbstract_2, _from, _to);
      OperatorExtensions.operator_add(model, _visibility);
      SemanticIdentityIdentityReference _semanticIdentity_3 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_3 = builder.category(this.set);
      IsAbstractIdentityReference _isAbstract_3 = builder.isAbstract(this.set);
      SemanticIdentityIdentityReference _semanticIdentity_4 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_4 = builder.category(this.set);
      IsAbstractIdentityReference _isAbstract_4 = builder.isAbstract(this.set);
      MinimumCardinalityIdentityReference _minCardinality = builder.minCardinality(this.set);
      MaximumCardinalityIdentityReference _maxCardinality_1 = builder.maxCardinality(this.set);
      IsContainerIdentityReference _isContainer = builder.isContainer(this.set);
      IsNavigableIdentityReference _isNavigable = builder.isNavigable(this.set);
      EdgeEnd _fromEdgeEnd = builder.fromEdgeEnd(_semanticIdentity_4, _category_4, _isAbstract_4, _minCardinality, _maxCardinality_1, _isContainer, _isNavigable);
      SemanticIdentityIdentityReference _semanticIdentity_5 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_5 = builder.category(this.set);
      IsAbstractIdentityReference _isAbstract_5 = builder.isAbstract(this.set);
      MinimumCardinalityIdentityReference _minCardinality_1 = builder.minCardinality(this.set);
      MaximumCardinalityIdentityReference _maxCardinality_2 = builder.maxCardinality(this.set);
      IsContainerIdentityReference _isContainer_1 = builder.isContainer(this.set);
      IsNavigableIdentityReference _isNavigable_1 = builder.isNavigable(this.set);
      EdgeEnd _edgeEnd = builder.toEdgeEnd(_semanticIdentity_5, _category_5, _isAbstract_5, _minCardinality_1, _maxCardinality_2, _isContainer_1, _isNavigable_1);
      Edge _edge = builder.edge(_semanticIdentity_3, _category_3, _isAbstract_3, _fromEdgeEnd, _edgeEnd);
      OperatorExtensions.operator_add(model, _edge);
      SemanticIdentityIdentityReference _semanticIdentity_6 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_6 = builder.category(this.set);
      Command _command = builder.command(_semanticIdentity_6, _category_6);
      OperatorExtensions.operator_add(model, _command);
      SemanticIdentityIdentityReference _semanticIdentity_7 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_7 = builder.category(this.set);
      Query _query = builder.query(_semanticIdentity_7, _category_7);
      final Query query = _query;
      SemanticIdentityIdentityReference _semanticIdentity_8 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_8 = builder.category(this.set);
      Parameter _parameter = builder.parameter(_semanticIdentity_8, _category_8);
      query.addParameter(_parameter);
      OperatorExtensions.operator_add(model, query);
      SemanticIdentityIdentityReference _semanticIdentity_9 = builder.semanticIdentity(this.set);
      CategoryIdentityReference _category_9 = builder.category(this.set);
      IsAbstractIdentityReference _isAbstract_6 = builder.isAbstract(this.set);
      FromIdentityReference _from_1 = builder.from(this.set);
      ToIdentityReference _to_1 = builder.to(this.set);
      SuperSetReference _superSetReference = builder.superSetReference(_semanticIdentity_9, _category_9, _isAbstract_6, _from_1, _to_1);
      OperatorExtensions.operator_add(model, _superSetReference);
      ArtifactSet _build = builder.build();
      _xblockexpression = (_build);
    }
    return _xblockexpression;
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
      Pair<MethodDescriptor,IdempotentMethodInvocationHandler> _operator_mappedTo = ObjectExtensions.<MethodDescriptor, IdempotentMethodInvocationHandler>operator_mappedTo(_methodDescriptor, identityHandler);
      MethodDescriptor _methodDescriptor_1 = new MethodDescriptor("category");
      Pair<MethodDescriptor,IdempotentMethodInvocationHandler> _operator_mappedTo_1 = ObjectExtensions.<MethodDescriptor, IdempotentMethodInvocationHandler>operator_mappedTo(_methodDescriptor_1, categoryHandler);
      MethodDescriptor _methodDescriptor_2 = new MethodDescriptor("value", org.s23m.cell.Set.class);
      Pair<MethodDescriptor,IdempotentMethodInvocationHandler> _operator_mappedTo_2 = ObjectExtensions.<MethodDescriptor, IdempotentMethodInvocationHandler>operator_mappedTo(_methodDescriptor_2, valueHandler);
      HashMap<MethodDescriptor,IdempotentMethodInvocationHandler> _newHashMap = CollectionLiterals.<MethodDescriptor, IdempotentMethodInvocationHandler>newHashMap(_operator_mappedTo, _operator_mappedTo_1, _operator_mappedTo_2);
      final HashMap<MethodDescriptor,IdempotentMethodInvocationHandler> handlers = _newHashMap;
      Set _createInstance = GenericFactory.<Set>createInstance(org.s23m.cell.Set.class, handlers);
      _xblockexpression = (_createInstance);
    }
    return _xblockexpression;
  }
  
  private MockIdentity createIdentity() {
    MockIdentity _xblockexpression = null;
    {
      UUID _randomUUID = UUID.randomUUID();
      final UUID identifier = _randomUUID;
      UUID _randomUUID_1 = UUID.randomUUID();
      final UUID uniqueRepresentationReference = _randomUUID_1;
      MockIdentity _mockIdentity = new MockIdentity(identifier, uniqueRepresentationReference);
      _xblockexpression = (_mockIdentity);
    }
    return _xblockexpression;
  }
}
