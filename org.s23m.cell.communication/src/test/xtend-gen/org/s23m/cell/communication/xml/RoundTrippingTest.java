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
import org.xml.sax.SAXException;

@SuppressWarnings("all")
public class RoundTrippingTest extends TestCase {
  private static int counter = 1;
  
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
      String _id_26 = this.id();
      String _id_27 = this.id();
      Pair<String,String> _operator_mappedTo_13 = ObjectExtensions.<String, String>operator_mappedTo(_id_26, _id_27);
      SemanticIdentityIdentityReference _semanticIdentity_3 = builder.semanticIdentity(_operator_mappedTo_13);
      String _id_28 = this.id();
      String _id_29 = this.id();
      Pair<String,String> _operator_mappedTo_14 = ObjectExtensions.<String, String>operator_mappedTo(_id_28, _id_29);
      CategoryIdentityReference _category_3 = builder.category(_operator_mappedTo_14);
      String _id_30 = this.id();
      String _id_31 = this.id();
      Pair<String,String> _operator_mappedTo_15 = ObjectExtensions.<String, String>operator_mappedTo(_id_30, _id_31);
      IsAbstractIdentityReference _isAbstract_3 = builder.isAbstract(_operator_mappedTo_15);
      String _id_32 = this.id();
      String _id_33 = this.id();
      Pair<String,String> _operator_mappedTo_16 = ObjectExtensions.<String, String>operator_mappedTo(_id_32, _id_33);
      SemanticIdentityIdentityReference _semanticIdentity_4 = builder.semanticIdentity(_operator_mappedTo_16);
      String _id_34 = this.id();
      String _id_35 = this.id();
      Pair<String,String> _operator_mappedTo_17 = ObjectExtensions.<String, String>operator_mappedTo(_id_34, _id_35);
      CategoryIdentityReference _category_4 = builder.category(_operator_mappedTo_17);
      String _id_36 = this.id();
      String _id_37 = this.id();
      Pair<String,String> _operator_mappedTo_18 = ObjectExtensions.<String, String>operator_mappedTo(_id_36, _id_37);
      IsAbstractIdentityReference _isAbstract_4 = builder.isAbstract(_operator_mappedTo_18);
      String _id_38 = this.id();
      String _id_39 = this.id();
      Pair<String,String> _operator_mappedTo_19 = ObjectExtensions.<String, String>operator_mappedTo(_id_38, _id_39);
      MinimumCardinalityIdentityReference _minCardinality = builder.minCardinality(_operator_mappedTo_19);
      String _id_40 = this.id();
      String _id_41 = this.id();
      Pair<String,String> _operator_mappedTo_20 = ObjectExtensions.<String, String>operator_mappedTo(_id_40, _id_41);
      MaximumCardinalityIdentityReference _maxCardinality_1 = builder.maxCardinality(_operator_mappedTo_20);
      String _id_42 = this.id();
      String _id_43 = this.id();
      Pair<String,String> _operator_mappedTo_21 = ObjectExtensions.<String, String>operator_mappedTo(_id_42, _id_43);
      IsContainerIdentityReference _isContainer = builder.isContainer(_operator_mappedTo_21);
      String _id_44 = this.id();
      String _id_45 = this.id();
      Pair<String,String> _operator_mappedTo_22 = ObjectExtensions.<String, String>operator_mappedTo(_id_44, _id_45);
      IsNavigableIdentityReference _isNavigable = builder.isNavigable(_operator_mappedTo_22);
      EdgeEnd _fromEdgeEnd = builder.fromEdgeEnd(_semanticIdentity_4, _category_4, _isAbstract_4, _minCardinality, _maxCardinality_1, _isContainer, _isNavigable);
      String _id_46 = this.id();
      String _id_47 = this.id();
      Pair<String,String> _operator_mappedTo_23 = ObjectExtensions.<String, String>operator_mappedTo(_id_46, _id_47);
      SemanticIdentityIdentityReference _semanticIdentity_5 = builder.semanticIdentity(_operator_mappedTo_23);
      String _id_48 = this.id();
      String _id_49 = this.id();
      Pair<String,String> _operator_mappedTo_24 = ObjectExtensions.<String, String>operator_mappedTo(_id_48, _id_49);
      CategoryIdentityReference _category_5 = builder.category(_operator_mappedTo_24);
      String _id_50 = this.id();
      String _id_51 = this.id();
      Pair<String,String> _operator_mappedTo_25 = ObjectExtensions.<String, String>operator_mappedTo(_id_50, _id_51);
      IsAbstractIdentityReference _isAbstract_5 = builder.isAbstract(_operator_mappedTo_25);
      String _id_52 = this.id();
      String _id_53 = this.id();
      Pair<String,String> _operator_mappedTo_26 = ObjectExtensions.<String, String>operator_mappedTo(_id_52, _id_53);
      MinimumCardinalityIdentityReference _minCardinality_1 = builder.minCardinality(_operator_mappedTo_26);
      String _id_54 = this.id();
      String _id_55 = this.id();
      Pair<String,String> _operator_mappedTo_27 = ObjectExtensions.<String, String>operator_mappedTo(_id_54, _id_55);
      MaximumCardinalityIdentityReference _maxCardinality_2 = builder.maxCardinality(_operator_mappedTo_27);
      String _id_56 = this.id();
      String _id_57 = this.id();
      Pair<String,String> _operator_mappedTo_28 = ObjectExtensions.<String, String>operator_mappedTo(_id_56, _id_57);
      IsContainerIdentityReference _isContainer_1 = builder.isContainer(_operator_mappedTo_28);
      String _id_58 = this.id();
      String _id_59 = this.id();
      Pair<String,String> _operator_mappedTo_29 = ObjectExtensions.<String, String>operator_mappedTo(_id_58, _id_59);
      IsNavigableIdentityReference _isNavigable_1 = builder.isNavigable(_operator_mappedTo_29);
      EdgeEnd _edgeEnd = builder.toEdgeEnd(_semanticIdentity_5, _category_5, _isAbstract_5, _minCardinality_1, _maxCardinality_2, _isContainer_1, _isNavigable_1);
      Edge _edge = builder.edge(_semanticIdentity_3, _category_3, _isAbstract_3, _fromEdgeEnd, _edgeEnd);
      OperatorExtensions.operator_add(model, _edge);
      String _id_60 = this.id();
      String _id_61 = this.id();
      Pair<String,String> _operator_mappedTo_30 = ObjectExtensions.<String, String>operator_mappedTo(_id_60, _id_61);
      SemanticIdentityIdentityReference _semanticIdentity_6 = builder.semanticIdentity(_operator_mappedTo_30);
      String _id_62 = this.id();
      String _id_63 = this.id();
      Pair<String,String> _operator_mappedTo_31 = ObjectExtensions.<String, String>operator_mappedTo(_id_62, _id_63);
      CategoryIdentityReference _category_6 = builder.category(_operator_mappedTo_31);
      Command _command = builder.command(_semanticIdentity_6, _category_6);
      OperatorExtensions.operator_add(model, _command);
      String _id_64 = this.id();
      String _id_65 = this.id();
      Pair<String,String> _operator_mappedTo_32 = ObjectExtensions.<String, String>operator_mappedTo(_id_64, _id_65);
      SemanticIdentityIdentityReference _semanticIdentity_7 = builder.semanticIdentity(_operator_mappedTo_32);
      String _id_66 = this.id();
      String _id_67 = this.id();
      Pair<String,String> _operator_mappedTo_33 = ObjectExtensions.<String, String>operator_mappedTo(_id_66, _id_67);
      CategoryIdentityReference _category_7 = builder.category(_operator_mappedTo_33);
      Query _query = builder.query(_semanticIdentity_7, _category_7);
      final Query query = _query;
      String _id_68 = this.id();
      String _id_69 = this.id();
      Pair<String,String> _operator_mappedTo_34 = ObjectExtensions.<String, String>operator_mappedTo(_id_68, _id_69);
      SemanticIdentityIdentityReference _semanticIdentity_8 = builder.semanticIdentity(_operator_mappedTo_34);
      String _id_70 = this.id();
      String _id_71 = this.id();
      Pair<String,String> _operator_mappedTo_35 = ObjectExtensions.<String, String>operator_mappedTo(_id_70, _id_71);
      CategoryIdentityReference _category_8 = builder.category(_operator_mappedTo_35);
      Parameter _parameter = builder.parameter(_semanticIdentity_8, _category_8);
      query.addParameter(_parameter);
      OperatorExtensions.operator_add(model, query);
      String _id_72 = this.id();
      String _id_73 = this.id();
      Pair<String,String> _operator_mappedTo_36 = ObjectExtensions.<String, String>operator_mappedTo(_id_72, _id_73);
      SemanticIdentityIdentityReference _semanticIdentity_9 = builder.semanticIdentity(_operator_mappedTo_36);
      String _id_74 = this.id();
      String _id_75 = this.id();
      Pair<String,String> _operator_mappedTo_37 = ObjectExtensions.<String, String>operator_mappedTo(_id_74, _id_75);
      CategoryIdentityReference _category_9 = builder.category(_operator_mappedTo_37);
      String _id_76 = this.id();
      String _id_77 = this.id();
      Pair<String,String> _operator_mappedTo_38 = ObjectExtensions.<String, String>operator_mappedTo(_id_76, _id_77);
      IsAbstractIdentityReference _isAbstract_6 = builder.isAbstract(_operator_mappedTo_38);
      String _id_78 = this.id();
      String _id_79 = this.id();
      Pair<String,String> _operator_mappedTo_39 = ObjectExtensions.<String, String>operator_mappedTo(_id_78, _id_79);
      FromIdentityReference _from_1 = builder.from(_operator_mappedTo_39);
      String _id_80 = this.id();
      String _id_81 = this.id();
      Pair<String,String> _operator_mappedTo_40 = ObjectExtensions.<String, String>operator_mappedTo(_id_80, _id_81);
      ToIdentityReference _to_1 = builder.to(_operator_mappedTo_40);
      SuperSetReference _superSetReference = builder.superSetReference(_semanticIdentity_9, _category_9, _isAbstract_6, _from_1, _to_1);
      OperatorExtensions.operator_add(model, _superSetReference);
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
