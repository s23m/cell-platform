package org.s23m.cell.communication.xml;

import com.google.common.base.Charsets;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Test;
import org.s23m.cell.communication.xml.DefaultXmlSchemaTerminology;
import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.OperatorExtensions;
import org.s23m.cell.communication.xml.XmlRendering;
import org.s23m.cell.communication.xml.XmlSchemaFactory;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.CategoryIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.ContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.FromIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MaximumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticIdentityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.ToIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;
import org.s23m.cell.communication.xml.sax.ArtifactSetElementHandler;
import org.xml.sax.SAXException;

@SuppressWarnings("all")
public class RoundTrippingTest extends TestCase {
  private static int counter = 1;
  
  @Test
  public void testSerialisationRoundTrip() {
      ArtifactSet _createInstanceModel = this.createInstanceModel();
      final ArtifactSet model = _createInstanceModel;
      CharSequence _render = XmlRendering.render(model);
      String _string = _render.toString();
      final String xml = _string;
      ArtifactSet _deserialise = this.deserialise(xml);
      final ArtifactSet deserialised = _deserialise;
      CharSequence _render_1 = XmlRendering.render(model);
      String _string_1 = _render_1.toString();
      final String renderedModel = _string_1;
      CharSequence _render_2 = XmlRendering.render(deserialised);
      String _string_2 = _render_2.toString();
      final String renderedDeserialisedModel = _string_2;
      Assert.assertEquals(renderedModel, renderedDeserialisedModel);
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
      String _id = this.id();
      String _id_1 = this.id();
      Pair<String,String> _operator_mappedTo = ObjectExtensions.<String, String>operator_mappedTo(_id, _id_1);
      SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(_operator_mappedTo);
      String _id_2 = this.id();
      String _id_3 = this.id();
      Pair<String,String> _operator_mappedTo_1 = ObjectExtensions.<String, String>operator_mappedTo(_id_2, _id_3);
      CategoryIdentityReference _category = builder.category(_operator_mappedTo_1);
      String _id_4 = this.id();
      String _id_5 = this.id();
      Pair<String,String> _operator_mappedTo_2 = ObjectExtensions.<String, String>operator_mappedTo(_id_4, _id_5);
      ContainerIdentityReference _container = builder.container(_operator_mappedTo_2);
      String _id_6 = this.id();
      String _id_7 = this.id();
      Pair<String,String> _operator_mappedTo_3 = ObjectExtensions.<String, String>operator_mappedTo(_id_6, _id_7);
      IsAbstractIdentityReference _isAbstract = builder.isAbstract(_operator_mappedTo_3);
      Model _model = builder.model(_semanticIdentity, _category, _container, _isAbstract);
      final Model model = _model;
      String _id_8 = this.id();
      String _id_9 = this.id();
      Pair<String,String> _operator_mappedTo_4 = ObjectExtensions.<String, String>operator_mappedTo(_id_8, _id_9);
      SemanticIdentityIdentityReference _semanticIdentity_1 = builder.semanticIdentity(_operator_mappedTo_4);
      String _id_10 = this.id();
      String _id_11 = this.id();
      Pair<String,String> _operator_mappedTo_5 = ObjectExtensions.<String, String>operator_mappedTo(_id_10, _id_11);
      CategoryIdentityReference _category_1 = builder.category(_operator_mappedTo_5);
      String _id_12 = this.id();
      String _id_13 = this.id();
      Pair<String,String> _operator_mappedTo_6 = ObjectExtensions.<String, String>operator_mappedTo(_id_12, _id_13);
      IsAbstractIdentityReference _isAbstract_1 = builder.isAbstract(_operator_mappedTo_6);
      String _id_14 = this.id();
      String _id_15 = this.id();
      Pair<String,String> _operator_mappedTo_7 = ObjectExtensions.<String, String>operator_mappedTo(_id_14, _id_15);
      MaximumCardinalityIdentityReference _maxCardinality = builder.maxCardinality(_operator_mappedTo_7);
      Vertex _vertex = builder.vertex(_semanticIdentity_1, _category_1, _isAbstract_1, _maxCardinality);
      OperatorExtensions.operator_add(model, _vertex);
      String _id_16 = this.id();
      String _id_17 = this.id();
      Pair<String,String> _operator_mappedTo_8 = ObjectExtensions.<String, String>operator_mappedTo(_id_16, _id_17);
      SemanticIdentityIdentityReference _semanticIdentity_2 = builder.semanticIdentity(_operator_mappedTo_8);
      String _id_18 = this.id();
      String _id_19 = this.id();
      Pair<String,String> _operator_mappedTo_9 = ObjectExtensions.<String, String>operator_mappedTo(_id_18, _id_19);
      CategoryIdentityReference _category_2 = builder.category(_operator_mappedTo_9);
      String _id_20 = this.id();
      String _id_21 = this.id();
      Pair<String,String> _operator_mappedTo_10 = ObjectExtensions.<String, String>operator_mappedTo(_id_20, _id_21);
      IsAbstractIdentityReference _isAbstract_2 = builder.isAbstract(_operator_mappedTo_10);
      String _id_22 = this.id();
      String _id_23 = this.id();
      Pair<String,String> _operator_mappedTo_11 = ObjectExtensions.<String, String>operator_mappedTo(_id_22, _id_23);
      FromIdentityReference _from = builder.from(_operator_mappedTo_11);
      String _id_24 = this.id();
      String _id_25 = this.id();
      Pair<String,String> _operator_mappedTo_12 = ObjectExtensions.<String, String>operator_mappedTo(_id_24, _id_25);
      ToIdentityReference _to = builder.to(_operator_mappedTo_12);
      Visibility _visibility = builder.visibility(_semanticIdentity_2, _category_2, _isAbstract_2, _from, _to);
      OperatorExtensions.operator_add(model, _visibility);
      ArtifactSet _build = builder.build();
      _xblockexpression = (_build);
    }
    return _xblockexpression;
  }
  
  private String id() {
    String _xblockexpression = null;
    {
      final int result = RoundTrippingTest.counter;
      int _operator_plus = IntegerExtensions.operator_plus(RoundTrippingTest.counter, 1);
      RoundTrippingTest.counter = _operator_plus;
      String _string = Integer.valueOf(result).toString();
      _xblockexpression = (_string);
    }
    return _xblockexpression;
  }
}
