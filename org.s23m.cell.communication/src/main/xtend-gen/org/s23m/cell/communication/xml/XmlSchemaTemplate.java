package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.s23m.cell.communication.xml.Extensions;
import org.s23m.cell.communication.xml.SchemaBuilder;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;
import org.s23m.cell.communication.xml.schema.Cardinality;
import org.s23m.cell.communication.xml.schema.ComplexType;
import org.s23m.cell.communication.xml.schema.DataType;
import org.s23m.cell.communication.xml.schema.Element;
import org.s23m.cell.communication.xml.schema.ElementReference;
import org.s23m.cell.communication.xml.schema.Extension;
import org.s23m.cell.communication.xml.schema.Schema;
import org.s23m.cell.communication.xml.schema.Sequence;
import org.s23m.cell.communication.xml.schema.SimpleType;

@SuppressWarnings("all")
public class XmlSchemaTemplate {
  /**
   * Schema constants
   */
  private static String XSD = "xsd";
  
  private static String XSD_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  
  private static String XSD_STRING = new Function0<String>() {
    public String apply() {
      String _xsd = XmlSchemaTemplate.xsd("string");
      return _xsd;
    }
  }.apply();
  
  /**
   * Namespace-related constants
   */
  private static String S23M = "s23m";
  
  private static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012";
  
  private static String SCHEMA_ATTRIBUTES = new Function0<String>() {
    public String apply() {
      String _operator_plus = StringExtensions.operator_plus("xmlns:", XmlSchemaTemplate.XSD);
      String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, "=\"http://www.w3.org/2001/XMLSchema\" ");
      String _operator_plus_2 = StringExtensions.operator_plus(_operator_plus_1, "xmlns:");
      String _operator_plus_3 = StringExtensions.operator_plus(_operator_plus_2, XmlSchemaTemplate.S23M);
      String _operator_plus_4 = StringExtensions.operator_plus(_operator_plus_3, "=\"");
      String _operator_plus_5 = StringExtensions.operator_plus(_operator_plus_4, XmlSchemaTemplate.S23M_SCHEMA);
      String _operator_plus_6 = StringExtensions.operator_plus(_operator_plus_5, "\" ");
      String _operator_plus_7 = StringExtensions.operator_plus(_operator_plus_6, "targetNamespace=\"");
      String _operator_plus_8 = StringExtensions.operator_plus(_operator_plus_7, XmlSchemaTemplate.S23M_SCHEMA);
      String _operator_plus_9 = StringExtensions.operator_plus(_operator_plus_8, "\" ");
      String _operator_plus_10 = StringExtensions.operator_plus(_operator_plus_9, "elementFormDefault=\"qualified\" ");
      String _operator_plus_11 = StringExtensions.operator_plus(_operator_plus_10, "attributeFormDefault=\"unqualified\"");
      return _operator_plus_11;
    }
  }.apply();
  
  private static Namespace NS_S23M = new Function0<Namespace>() {
    public Namespace apply() {
      Namespace _namespace = new Namespace(XmlSchemaTemplate.S23M, XmlSchemaTemplate.S23M_SCHEMA);
      return _namespace;
    }
  }.apply();
  
  private XmlSchemaTerminology terminology;
  
  private String semanticIdentity;
  
  private String identityReference;
  
  private String identityReferenceQualified;
  
  private String model;
  
  private String category;
  
  private String uuid;
  
  private String uuidQualified;
  
  private String isAbstract;
  
  private String maxCardinality;
  
  private String minCardinality;
  
  private String isContainer;
  
  private String isNavigable;
  
  private String from;
  
  private String to;
  
  private String graph;
  
  private String vertex;
  
  private String visibility;
  
  private String edge;
  
  private String edgeEnd;
  
  private String superSetReference;
  
  private String command;
  
  private String query;
  
  private String function;
  
  public XmlSchemaTemplate(final XmlSchemaTerminology terminology) {
      this.terminology = terminology;
      String _semanticIdentity = terminology.semanticIdentity();
      this.semanticIdentity = _semanticIdentity;
      String _identityReference = terminology.identityReference();
      this.identityReference = _identityReference;
      String _s23m = XmlSchemaTemplate.s23m(this.identityReference);
      this.identityReferenceQualified = _s23m;
      String _model = terminology.model();
      this.model = _model;
      String _category = terminology.category();
      this.category = _category;
      String _uuid = terminology.uuid();
      this.uuid = _uuid;
      String _s23m_1 = XmlSchemaTemplate.s23m(this.uuid);
      this.uuidQualified = _s23m_1;
      String _isAbstract = terminology.isAbstract();
      this.isAbstract = _isAbstract;
      String _maxCardinality = terminology.maxCardinality();
      this.maxCardinality = _maxCardinality;
      String _minCardinality = terminology.minCardinality();
      this.minCardinality = _minCardinality;
      String _isContainer = terminology.isContainer();
      this.isContainer = _isContainer;
      String _isNavigable = terminology.isNavigable();
      this.isNavigable = _isNavigable;
      String _from = terminology.from();
      this.from = _from;
      String _to = terminology.to();
      this.to = _to;
      String _graph = terminology.graph();
      this.graph = _graph;
      String _vertex = terminology.vertex();
      this.vertex = _vertex;
      String _visibility = terminology.visibility();
      this.visibility = _visibility;
      String _edge = terminology.edge();
      this.edge = _edge;
      String _edgeEnd = terminology.edgeEnd();
      this.edgeEnd = _edgeEnd;
      String _superSetReference = terminology.superSetReference();
      this.superSetReference = _superSetReference;
      String _command = terminology.command();
      this.command = _command;
      String _query = terminology.query();
      this.query = _query;
      String _function = terminology.function();
      this.function = _function;
  }
  
  public String createSchemaModel() {
    String _xblockexpression = null;
    {
      final Procedure1<Schema> _function = new Procedure1<Schema>() {
          public void apply(final Schema it) {
            {
              LinkedHashMap<String,String> _attributes = it.getAttributes();
              String _xmlns = XmlSchemaTemplate.xmlns(XmlSchemaTemplate.XSD);
              Pair<String,String> _operator_mappedTo = ObjectExtensions.<String, String>operator_mappedTo(_xmlns, XmlSchemaTemplate.XSD_SCHEMA);
              String _xmlns_1 = XmlSchemaTemplate.xmlns(XmlSchemaTemplate.S23M);
              Pair<String,String> _operator_mappedTo_1 = ObjectExtensions.<String, String>operator_mappedTo(_xmlns_1, XmlSchemaTemplate.S23M_SCHEMA);
              Pair<String,String> _operator_mappedTo_2 = ObjectExtensions.<String, String>operator_mappedTo("targetNamespace", XmlSchemaTemplate.S23M_SCHEMA);
              Pair<String,String> _operator_mappedTo_3 = ObjectExtensions.<String, String>operator_mappedTo("elementFormDefault", "qualified");
              Pair<String,String> _operator_mappedTo_4 = ObjectExtensions.<String, String>operator_mappedTo("attributeFormDefault", "unqualified");
              LinkedHashMap<String,String> _newLinkedHashMap = CollectionLiterals.<String, String>newLinkedHashMap(_operator_mappedTo, _operator_mappedTo_1, _operator_mappedTo_2, _operator_mappedTo_3, _operator_mappedTo_4);
              Extensions.<String, String>operator_add(_attributes, _newLinkedHashMap);
              List<Node> _children = it.getChildren();
              List<Element> _createReusedElements = XmlSchemaTemplate.this.createReusedElements();
              _children.addAll(_createReusedElements);
            }
          }
        };
      Schema _schema = SchemaBuilder.schema(_function);
      final Schema schema = _schema;
      LinkedHashMap<String,String> _attributes = schema.getAttributes();
      String _string = _attributes.toString();
      _xblockexpression = (_string);
    }
    return _xblockexpression;
  }
  
  private List<Element> createReusedElements() {
    ArrayList<Element> _xblockexpression = null;
    {
      SimpleType _simpleType = SchemaBuilder.simpleType(this.uuid, DataType.STRING);
      final SimpleType uuid = _simpleType;
      final Procedure1<Sequence> _function = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _uniqueRepresentationReference = XmlSchemaTemplate.this.terminology.uniqueRepresentationReference();
              Element _element = SchemaBuilder.element(_uniqueRepresentationReference, uuid);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              String _identifier = XmlSchemaTemplate.this.terminology.identifier();
              Element _element_1 = SchemaBuilder.element(_identifier, uuid);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
            }
          }
        };
      ComplexType _complexType = SchemaBuilder.complexType(this.identityReference, _function);
      final ComplexType identityReference = _complexType;
      Element _element = SchemaBuilder.element(this.semanticIdentity, identityReference);
      final Element semanticIdentityElement = _element;
      Element _element_1 = SchemaBuilder.element(this.isAbstract, identityReference);
      final Element isAbstractElement = _element_1;
      Element _element_2 = SchemaBuilder.element(this.minCardinality, identityReference);
      final Element minCardinalityElement = _element_2;
      Element _element_3 = SchemaBuilder.element(this.maxCardinality, identityReference);
      final Element maxCardinalityElement = _element_3;
      Element _element_4 = SchemaBuilder.element(this.isContainer, identityReference);
      final Element isContainerElement = _element_4;
      Element _element_5 = SchemaBuilder.element(this.isNavigable, identityReference);
      final Element isNavigableElement = _element_5;
      Element _element_6 = SchemaBuilder.element(this.from, identityReference);
      final Element fromElement = _element_6;
      Element _element_7 = SchemaBuilder.element(this.to, identityReference);
      final Element toElement = _element_7;
      Element _element_8 = SchemaBuilder.element(this.category, identityReference);
      final Element categoryElement = _element_8;
      final Procedure1<Sequence> _function_1 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              CollectionExtensions.<Element>operator_add(_children, semanticIdentityElement);
              List<Node> _children_1 = it.getChildren();
              CollectionExtensions.<Element>operator_add(_children_1, categoryElement);
            }
          }
        };
      ComplexType _complexType_1 = SchemaBuilder.complexType(this.category, _function_1);
      final ComplexType categoryComplexType = _complexType_1;
      final Procedure1<Sequence> _function_2 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(maxCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
            }
          }
        };
      Extension _withExtension = SchemaBuilder.withExtension(categoryComplexType, _function_2);
      ComplexType _complexType_2 = SchemaBuilder.complexType(this.vertex, _withExtension);
      final ComplexType vertexComplexType = _complexType_2;
      final Procedure1<Sequence> _function_3 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(fromElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = SchemaBuilder.element(toElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_1 = SchemaBuilder.withExtension(categoryComplexType, _function_3);
      ComplexType _complexType_3 = SchemaBuilder.complexType(this.visibility, _withExtension_1);
      final ComplexType visibilityComplexType = _complexType_3;
      final Procedure1<Sequence> _function_4 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(fromElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = SchemaBuilder.element(toElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_2 = SchemaBuilder.withExtension(categoryComplexType, _function_4);
      ComplexType _complexType_4 = SchemaBuilder.complexType(this.superSetReference, _withExtension_2);
      final ComplexType superSetReferenceComplexType = _complexType_4;
      final Procedure1<Sequence> _function_5 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(minCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              ElementReference _element_2 = SchemaBuilder.element(maxCardinalityElement);
              CollectionExtensions.<ElementReference>operator_add(_children_2, _element_2);
              List<Node> _children_3 = it.getChildren();
              ElementReference _element_3 = SchemaBuilder.element(isContainerElement);
              CollectionExtensions.<ElementReference>operator_add(_children_3, _element_3);
              List<Node> _children_4 = it.getChildren();
              ElementReference _element_4 = SchemaBuilder.element(isNavigableElement);
              CollectionExtensions.<ElementReference>operator_add(_children_4, _element_4);
            }
          }
        };
      Extension _withExtension_3 = SchemaBuilder.withExtension(categoryComplexType, _function_5);
      ComplexType _complexType_5 = SchemaBuilder.complexType(this.edgeEnd, _withExtension_3);
      final ComplexType edgeEndComplexType = _complexType_5;
      final Procedure1<Sequence> _function_6 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              ElementReference _element = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              Element _element_1 = SchemaBuilder.element(XmlSchemaTemplate.this.from, edgeEndComplexType);
              CollectionExtensions.<Element>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              Element _element_2 = SchemaBuilder.element(XmlSchemaTemplate.this.to, edgeEndComplexType);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
            }
          }
        };
      Extension _withExtension_4 = SchemaBuilder.withExtension(categoryComplexType, _function_6);
      ComplexType _complexType_6 = SchemaBuilder.complexType(this.edge, _withExtension_4);
      final ComplexType edgeComplexType = _complexType_6;
      String _parameter = this.terminology.parameter();
      final String parameter = _parameter;
      final Procedure1<Sequence> _function_7 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
          }
        };
      Extension _withExtension_5 = SchemaBuilder.withExtension(categoryComplexType, _function_7);
      ComplexType _complexType_7 = SchemaBuilder.complexType(parameter, _withExtension_5);
      final ComplexType parameterComplexType = _complexType_7;
      final Procedure1<Sequence> _function_8 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            List<Node> _children = it.getChildren();
            Element _element = SchemaBuilder.element(parameter, parameterComplexType, Cardinality.ZERO_TO_MANY);
            CollectionExtensions.<Element>operator_add(_children, _element);
          }
        };
      Extension _withExtension_6 = SchemaBuilder.withExtension(categoryComplexType, _function_8);
      ComplexType _complexType_8 = SchemaBuilder.complexType(this.function, _withExtension_6);
      final ComplexType functionComplexType = _complexType_8;
      final Procedure1<Sequence> _function_9 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
          }
        };
      Extension _withExtension_7 = SchemaBuilder.withExtension(functionComplexType, _function_9);
      ComplexType _complexType_9 = SchemaBuilder.complexType(this.command, _withExtension_7);
      final ComplexType commandComplexType = _complexType_9;
      final Procedure1<Sequence> _function_10 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
          }
        };
      Extension _withExtension_8 = SchemaBuilder.withExtension(functionComplexType, _function_10);
      ComplexType _complexType_10 = SchemaBuilder.complexType(this.query, _withExtension_8);
      final ComplexType queryComplexType = _complexType_10;
      final Procedure1<Sequence> _function_11 = new Procedure1<Sequence>() {
          public void apply(final Sequence it) {
            {
              List<Node> _children = it.getChildren();
              String _container = XmlSchemaTemplate.this.terminology.container();
              Element _element = SchemaBuilder.element(_container, identityReference);
              CollectionExtensions.<Element>operator_add(_children, _element);
              List<Node> _children_1 = it.getChildren();
              ElementReference _element_1 = SchemaBuilder.element(isAbstractElement);
              CollectionExtensions.<ElementReference>operator_add(_children_1, _element_1);
              List<Node> _children_2 = it.getChildren();
              Element _element_2 = SchemaBuilder.element(XmlSchemaTemplate.this.vertex, vertexComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_2, _element_2);
              List<Node> _children_3 = it.getChildren();
              Element _element_3 = SchemaBuilder.element(XmlSchemaTemplate.this.visibility, visibilityComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_3, _element_3);
              List<Node> _children_4 = it.getChildren();
              Element _element_4 = SchemaBuilder.element(XmlSchemaTemplate.this.edge, edgeComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_4, _element_4);
              List<Node> _children_5 = it.getChildren();
              Element _element_5 = SchemaBuilder.element(XmlSchemaTemplate.this.superSetReference, superSetReferenceComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_5, _element_5);
              List<Node> _children_6 = it.getChildren();
              Element _element_6 = SchemaBuilder.element(XmlSchemaTemplate.this.command, commandComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_6, _element_6);
              List<Node> _children_7 = it.getChildren();
              Element _element_7 = SchemaBuilder.element(XmlSchemaTemplate.this.query, queryComplexType, Cardinality.ZERO_TO_MANY);
              CollectionExtensions.<Element>operator_add(_children_7, _element_7);
            }
          }
        };
      Extension _withExtension_9 = SchemaBuilder.withExtension(categoryComplexType, _function_11);
      ComplexType _complexType_11 = SchemaBuilder.complexType(this.graph, _withExtension_9);
      final ComplexType graphComplexType = _complexType_11;
      ArrayList<Element> _newArrayList = CollectionLiterals.<Element>newArrayList();
      _xblockexpression = (_newArrayList);
    }
    return _xblockexpression;
  }
  
  public CharSequence createSchema() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    CharSequence _reusedElements = this.reusedElements();
    CharSequence _rootElement = this.rootElement();
    CharSequence _modelArtefactEncoding = this.modelArtefactEncoding();
    CharSequence _vertices = this.vertices();
    CharSequence _arrows = this.arrows();
    CharSequence _artifactFunctionality = this.artifactFunctionality();
    CharSequence _semanticDomainArtefactEncoding = this.semanticDomainArtefactEncoding();
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_reusedElements, _rootElement, _modelArtefactEncoding, _vertices, _arrows, _artifactFunctionality, _semanticDomainArtefactEncoding);
    CharSequence _node = this.node("schema", XmlSchemaTemplate.SCHEMA_ATTRIBUTES, _asList);
    _builder.append(_node, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence reusedElements() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    CharSequence _element = this.element(this.semanticIdentity, this.identityReferenceQualified);
    _builder.append(_element, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m = XmlSchemaTemplate.s23m(this.model);
    CharSequence _element_1 = this.element(this.model, _s23m);
    _builder.append(_element_1, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_2 = this.element(this.category, this.identityReferenceQualified);
    _builder.append(_element_2, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_3 = this.element(this.isAbstract, this.identityReferenceQualified);
    _builder.append(_element_3, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_4 = this.element(this.maxCardinality, this.identityReferenceQualified);
    _builder.append(_element_4, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_5 = this.element(this.minCardinality, this.identityReferenceQualified);
    _builder.append(_element_5, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_6 = this.element(this.isContainer, this.identityReferenceQualified);
    _builder.append(_element_6, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_7 = this.element(this.isNavigable, this.identityReferenceQualified);
    _builder.append(_element_7, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_8 = this.element(this.from, this.identityReferenceQualified);
    _builder.append(_element_8, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_9 = this.element(this.to, this.identityReferenceQualified);
    _builder.append(_element_9, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_1 = XmlSchemaTemplate.s23m(this.function);
    CharSequence _element_10 = this.element(this.function, _s23m_1);
    _builder.append(_element_10, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    String _uniqueRepresentationReference = this.terminology.uniqueRepresentationReference();
    CharSequence _element_11 = this.element(_uniqueRepresentationReference, this.uuidQualified);
    String _identifier = this.terminology.identifier();
    CharSequence _element_12 = this.element(_identifier, this.uuidQualified);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_element_11, _element_12);
    CharSequence _complexType = this.complexType(this.identityReference, _asList);
    _builder.append(_complexType, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _element_13 = this.element(this.semanticIdentity);
    CharSequence _element_14 = this.element(this.category);
    List<CharSequence> _asList_1 = Arrays.<CharSequence>asList(_element_13, _element_14);
    CharSequence _complexType_1 = this.complexType(this.category, _asList_1);
    _builder.append(_complexType_1, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _simpleType = this.simpleType(this.uuid, XmlSchemaTemplate.XSD_STRING);
    _builder.append(_simpleType, "	");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence rootElement() {
    StringConcatenation _builder = new StringConcatenation();
    String _artifactSet = this.terminology.artifactSet();
    final String artifactSet = _artifactSet;
    _builder.newLineIfNotEmpty();
    String _semanticDomain = this.terminology.semanticDomain();
    final String semanticDomain = _semanticDomain;
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _s23m = XmlSchemaTemplate.s23m(artifactSet);
    CharSequence _element = this.element(artifactSet, _s23m);
    _builder.append(_element, "");
    _builder.append("\t\t");
    _builder.newLineIfNotEmpty();
    String _s23m_1 = XmlSchemaTemplate.s23m(this.model);
    CharSequence _elementList = this.elementList(_s23m_1);
    String _s23m_2 = XmlSchemaTemplate.s23m(semanticDomain);
    CharSequence _elementList_1 = this.elementList(semanticDomain, _s23m_2);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_elementList, _elementList_1);
    CharSequence _complexType = this.complexType(artifactSet, _asList);
    _builder.append(_complexType, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence modelArtefactEncoding() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    String _container = this.terminology.container();
    final String container = _container;
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    String _s23m = XmlSchemaTemplate.s23m(this.graph);
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension(this.model, _s23m);
    _builder.append(_complexTypeWithExtension, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _element = this.element(container, this.identityReferenceQualified);
    CharSequence _element_1 = this.element(this.isAbstract);
    String _s23m_1 = XmlSchemaTemplate.s23m(this.vertex);
    CharSequence _elementList = this.elementList(this.vertex, _s23m_1);
    String _s23m_2 = XmlSchemaTemplate.s23m(this.visibility);
    CharSequence _elementList_1 = this.elementList(this.visibility, _s23m_2);
    String _s23m_3 = XmlSchemaTemplate.s23m(this.edge);
    CharSequence _elementList_2 = this.elementList(this.edge, _s23m_3);
    String _s23m_4 = XmlSchemaTemplate.s23m(this.superSetReference);
    CharSequence _elementList_3 = this.elementList(this.superSetReference, _s23m_4);
    String _s23m_5 = XmlSchemaTemplate.s23m(this.command);
    CharSequence _elementList_4 = this.elementList(this.command, _s23m_5);
    String _s23m_6 = XmlSchemaTemplate.s23m(this.query);
    CharSequence _elementList_5 = this.elementList(this.query, _s23m_6);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_element, _element_1, _elementList, _elementList_1, _elementList_2, _elementList_3, _elementList_4, _elementList_5);
    CharSequence _categoryComplexType = this.categoryComplexType(this.graph, _asList);
    _builder.append(_categoryComplexType, "	");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence vertices() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _element = this.element(this.isAbstract);
    CharSequence _element_1 = this.element(this.maxCardinality);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_element, _element_1);
    CharSequence _categoryComplexType = this.categoryComplexType(this.vertex, _asList);
    _builder.append(_categoryComplexType, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence arrows() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _element = this.element(this.isAbstract);
    CharSequence _element_1 = this.element(this.from);
    CharSequence _element_2 = this.element(this.to);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_element, _element_1, _element_2);
    CharSequence _categoryComplexType = this.categoryComplexType(this.superSetReference, _asList);
    _builder.append(_categoryComplexType, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_3 = this.element(this.isAbstract);
    CharSequence _element_4 = this.element(this.from);
    CharSequence _element_5 = this.element(this.to);
    List<CharSequence> _asList_1 = Arrays.<CharSequence>asList(_element_3, _element_4, _element_5);
    CharSequence _categoryComplexType_1 = this.categoryComplexType(this.visibility, _asList_1);
    _builder.append(_categoryComplexType_1, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_6 = this.element(this.isAbstract);
    String _s23m = XmlSchemaTemplate.s23m(this.edgeEnd);
    CharSequence _element_7 = this.element(this.from, _s23m);
    String _s23m_1 = XmlSchemaTemplate.s23m(this.edgeEnd);
    CharSequence _element_8 = this.element(this.to, _s23m_1);
    List<CharSequence> _asList_2 = Arrays.<CharSequence>asList(_element_6, _element_7, _element_8);
    CharSequence _categoryComplexType_2 = this.categoryComplexType(this.edge, _asList_2);
    _builder.append(_categoryComplexType_2, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _element_9 = this.element(this.isAbstract);
    CharSequence _element_10 = this.element(this.minCardinality);
    CharSequence _element_11 = this.element(this.maxCardinality);
    CharSequence _element_12 = this.element(this.isContainer);
    CharSequence _element_13 = this.element(this.isNavigable);
    List<CharSequence> _asList_3 = Arrays.<CharSequence>asList(_element_9, _element_10, _element_11, _element_12, _element_13);
    CharSequence _categoryComplexType_3 = this.categoryComplexType(this.edgeEnd, _asList_3);
    _builder.append(_categoryComplexType_3, "	");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence artifactFunctionality() {
    StringConcatenation _builder = new StringConcatenation();
    String _parameter = this.terminology.parameter();
    final String parameter = _parameter;
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _s23m = XmlSchemaTemplate.s23m(parameter);
    CharSequence _elementList = this.elementList(parameter, _s23m);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_elementList);
    CharSequence _categoryComplexType = this.categoryComplexType(this.function, _asList);
    _builder.append(_categoryComplexType, "");
    _builder.newLineIfNotEmpty();
    List<CharSequence> _emptyList = Collections.<CharSequence>emptyList();
    CharSequence _categoryComplexType_1 = this.categoryComplexType(parameter, _emptyList);
    _builder.append(_categoryComplexType_1, "");
    _builder.newLineIfNotEmpty();
    String _s23m_1 = XmlSchemaTemplate.s23m(this.function);
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension(this.command, _s23m_1);
    _builder.append(_complexTypeWithExtension, "");
    _builder.newLineIfNotEmpty();
    String _s23m_2 = XmlSchemaTemplate.s23m(this.function);
    CharSequence _complexTypeWithExtension_1 = this.complexTypeWithExtension(this.query, _s23m_2);
    _builder.append(_complexTypeWithExtension_1, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence semanticDomainArtefactEncoding() {
    StringConcatenation _builder = new StringConcatenation();
    String _identity = this.terminology.identity();
    final String identity = _identity;
    _builder.newLineIfNotEmpty();
    String _semanticDomain = this.terminology.semanticDomain();
    final String semanticDomain = _semanticDomain;
    _builder.newLineIfNotEmpty();
    String _identifier = this.terminology.identifier();
    final String identifier = _identifier;
    _builder.newLineIfNotEmpty();
    String _name = this.terminology.name();
    final String name = _name;
    _builder.newLineIfNotEmpty();
    String _pluralName = this.terminology.pluralName();
    final String pluralName = _pluralName;
    _builder.newLineIfNotEmpty();
    String _payload = this.terminology.payload();
    final String payload = _payload;
    _builder.newLineIfNotEmpty();
    String _technicalName = this.terminology.technicalName();
    final String technicalName = _technicalName;
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _element = this.element(this.model);
    String _s23m = XmlSchemaTemplate.s23m(identity);
    CharSequence _elementList = this.elementList(identity, _s23m);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_element, _elementList);
    CharSequence _complexType = this.complexType(semanticDomain, _asList);
    _builder.append(_complexType, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _s23m_1 = XmlSchemaTemplate.s23m(this.uuid);
    CharSequence _element_1 = this.element(identifier, _s23m_1);
    CharSequence _element_2 = this.element(name, XmlSchemaTemplate.XSD_STRING);
    CharSequence _element_3 = this.element(pluralName, XmlSchemaTemplate.XSD_STRING);
    CharSequence _element_4 = this.element(payload, XmlSchemaTemplate.XSD_STRING);
    CharSequence _element_5 = this.element(technicalName, XmlSchemaTemplate.XSD_STRING);
    List<CharSequence> _asList_1 = Arrays.<CharSequence>asList(_element_1, _element_2, _element_3, _element_4, _element_5);
    CharSequence _complexType_1 = this.complexType(identity, _asList_1);
    _builder.append(_complexType_1, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence complexType(final String name, final List<CharSequence> containedElements) {
    String _format = String.format("name=\"%s\"", name);
    String _join = IterableExtensions.join(containedElements, "\n");
    CharSequence _node = this.node("complexType", _format, _join);
    return _node;
  }
  
  private CharSequence complexTypeWithExtension(final String name, final String extensionBase) {
    List<CharSequence> _emptyList = Collections.<CharSequence>emptyList();
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension(name, extensionBase, _emptyList);
    return _complexTypeWithExtension;
  }
  
  private CharSequence categoryComplexType(final String name, final List<CharSequence> elementsInSequence) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension(name, this.category, elementsInSequence);
    _builder.append(_complexTypeWithExtension, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence complexTypeWithExtension(final String name, final String extensionBase, final List<CharSequence> containedElements) {
    String _format = String.format("name=\"%s\"", name);
    String _s23m = XmlSchemaTemplate.s23m(extensionBase);
    String _format_1 = String.format("base=\"%s\"", _s23m);
    CharSequence _sequence = this.sequence(containedElements);
    CharSequence _node = this.node("extension", _format_1, _sequence);
    CharSequence _node_1 = this.node("complexContent", "", _node);
    CharSequence _node_2 = this.node("complexType", _format, _node_1);
    return _node_2;
  }
  
  private CharSequence sequence(final List<CharSequence> containedElements) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = containedElements.isEmpty();
      boolean _operator_not = BooleanExtensions.operator_not(_isEmpty);
      if (_operator_not) {
        String _join = IterableExtensions.join(containedElements, "\n");
        CharSequence _node = this.node("sequence", "", _join);
        _builder.append(_node, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence simpleType(final String name, final String baseType) {
    String _format = String.format("name=\"%s\"", name);
    String _format_1 = String.format("base=\"%s\"", baseType);
    CharSequence _node = this.node("restriction", _format_1);
    CharSequence _node_1 = this.node("simpleType", _format, _node);
    return _node_1;
  }
  
  private CharSequence element(final String name, final String type) {
    String _format = String.format("name=\"%s\" type=\"%s\"", name, type);
    CharSequence _node = this.node("element", _format);
    return _node;
  }
  
  private CharSequence element(final String referencedName) {
    String _s23m = XmlSchemaTemplate.s23m(referencedName);
    String _format = String.format("ref=\"%s\"", _s23m);
    CharSequence _node = this.node("element", _format);
    return _node;
  }
  
  private CharSequence elementList(final String referencedName) {
    String _format = String.format("ref=\"%s\" minOccurs=\"0\" maxOccurs=\"unbounded\"", referencedName);
    CharSequence _node = this.node("element", _format);
    return _node;
  }
  
  private CharSequence elementList(final String name, final String type) {
    String _format = String.format("name=\"%s\" type=\"%s\" minOccurs=\"0\" maxOccurs=\"unbounded\"", name, type);
    CharSequence _node = this.node("element", _format);
    return _node;
  }
  
  private CharSequence node(final String tagName, final String attributeContents, final List<CharSequence> nestedElements) {
    String _join = IterableExtensions.join(nestedElements, "\n");
    CharSequence _node = this.node(tagName, attributeContents, _join);
    return _node;
  }
  
  private CharSequence node(final String tagName, final String attributeContents, final CharSequence nestedElements) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = XmlSchemaTemplate.xsd(tagName);
    _builder.append(_xsd, "");
    {
      boolean _isEmpty = attributeContents.isEmpty();
      if (_isEmpty) {
        _builder.append(">");
      } else {
        _builder.append(" ");
        _builder.append(attributeContents, "");
        _builder.append(">");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append(nestedElements, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</");
    String _xsd_1 = XmlSchemaTemplate.xsd(tagName);
    _builder.append(_xsd_1, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence node(final String tagName, final String attributeContents) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = XmlSchemaTemplate.xsd(tagName);
    _builder.append(_xsd, "");
    _builder.append(" ");
    _builder.append(attributeContents, "");
    _builder.append("/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private static String xsd(final String name) {
    String _qualifiedName = XmlSchemaTemplate.qualifiedName(XmlSchemaTemplate.XSD, name);
    return _qualifiedName;
  }
  
  private static String s23m(final String name) {
    String _qualifiedName = XmlSchemaTemplate.qualifiedName(XmlSchemaTemplate.S23M, name);
    return _qualifiedName;
  }
  
  private static String xmlns(final String name) {
    String _qualifiedName = XmlSchemaTemplate.qualifiedName("xmlns", name);
    return _qualifiedName;
  }
  
  private static String qualifiedName(final String namespacePrefix, final String name) {
    String _operator_plus = StringExtensions.operator_plus(namespacePrefix, ":");
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, name);
    return _operator_plus_1;
  }
}
