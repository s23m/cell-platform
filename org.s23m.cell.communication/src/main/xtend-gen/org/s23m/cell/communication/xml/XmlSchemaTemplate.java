package org.s23m.cell.communication.xml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class XmlSchemaTemplate {
  private static String XSD = "xsd";
  
  private static String S23M = "s23m";
  
  private static String S23M_SCHEMA = "http://schemas.s23m.org/serialization/2012";
  
  public CharSequence createHumanReadableSchema() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<");
    String _xsd = this.xsd("schema");
    _builder.append(_xsd, "");
    _builder.append(" xmlns:");
    _builder.append(XmlSchemaTemplate.XSD, "");
    _builder.append("=\"http://www.w3.org/2001/XMLSchema\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("xmlns:");
    _builder.append(XmlSchemaTemplate.S23M, "		");
    _builder.append("=\"");
    _builder.append(XmlSchemaTemplate.S23M_SCHEMA, "		");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("targetNamespace=\"");
    _builder.append(XmlSchemaTemplate.S23M_SCHEMA, "		");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("elementFormDefault=\"qualified\"");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("attributeFormDefault=\"unqualified\">");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    CharSequence _reusedElements = this.reusedElements();
    _builder.append(_reusedElements, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _rootElement = this.rootElement();
    _builder.append(_rootElement, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _modelArtefactEncoding = this.modelArtefactEncoding();
    _builder.append(_modelArtefactEncoding, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _vertices = this.vertices();
    _builder.append(_vertices, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _arrows = this.arrows();
    _builder.append(_arrows, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _artifactFunctionality = this.artifactFunctionality();
    _builder.append(_artifactFunctionality, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _semanticDomainArtefactEncoding = this.semanticDomainArtefactEncoding();
    _builder.append(_semanticDomainArtefactEncoding, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("</");
    String _xsd_1 = this.xsd("schema");
    _builder.append(_xsd_1, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createMachineReadableSchema() {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  private CharSequence reusedElements() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    String _s23m = this.s23m("identityReference");
    CharSequence _element = this.element("semanticIdentity", _s23m);
    _builder.append(_element, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_1 = this.s23m("model");
    CharSequence _element_1 = this.element("model", _s23m_1);
    _builder.append(_element_1, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_2 = this.s23m("identityReference");
    CharSequence _element_2 = this.element("category", _s23m_2);
    _builder.append(_element_2, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_3 = this.s23m("identityReference");
    CharSequence _element_3 = this.element("isAbstract", _s23m_3);
    _builder.append(_element_3, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_4 = this.s23m("identityReference");
    CharSequence _element_4 = this.element("maxCardinality", _s23m_4);
    _builder.append(_element_4, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_5 = this.s23m("identityReference");
    CharSequence _element_5 = this.element("minCardinality", _s23m_5);
    _builder.append(_element_5, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_6 = this.s23m("identityReference");
    CharSequence _element_6 = this.element("isContainer", _s23m_6);
    _builder.append(_element_6, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_7 = this.s23m("identityReference");
    CharSequence _element_7 = this.element("isNavigable", _s23m_7);
    _builder.append(_element_7, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_8 = this.s23m("identityReference");
    CharSequence _element_8 = this.element("from", _s23m_8);
    _builder.append(_element_8, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_9 = this.s23m("identityReference");
    CharSequence _element_9 = this.element("to", _s23m_9);
    _builder.append(_element_9, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _s23m_10 = this.s23m("function");
    CharSequence _element_10 = this.element("function", _s23m_10);
    _builder.append(_element_10, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    String _s23m_11 = this.s23m("uuid");
    CharSequence _element_11 = this.element("uniqueRepresentationReference", _s23m_11);
    String _s23m_12 = this.s23m("uuid");
    CharSequence _element_12 = this.element("identifier", _s23m_12);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_element_11, _element_12);
    CharSequence _complexType = this.complexType("identityReference", _asList);
    _builder.append(_complexType, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    String _s23m_13 = this.s23m("semanticIdentity");
    CharSequence _elementRef = this.elementRef(_s23m_13);
    String _s23m_14 = this.s23m("category");
    CharSequence _elementRef_1 = this.elementRef(_s23m_14);
    List<CharSequence> _asList_1 = Arrays.<CharSequence>asList(_elementRef, _elementRef_1);
    CharSequence _complexType_1 = this.complexType("category", _asList_1);
    _builder.append(_complexType_1, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    String _xsd = this.xsd("string");
    CharSequence _simpleType = this.simpleType("uuid", _xsd);
    _builder.append(_simpleType, "	");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence rootElement() {
    StringConcatenation _builder = new StringConcatenation();
    String _s23m = this.s23m("artifactSet");
    CharSequence _element = this.element("artifactSet", _s23m);
    _builder.append(_element, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _s23m_1 = this.s23m("model");
    CharSequence _elementRefList = this.elementRefList(_s23m_1);
    String _s23m_2 = this.s23m("semanticDomain");
    CharSequence _elementList = this.elementList("semanticDomain", _s23m_2);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_elementRefList, _elementList);
    CharSequence _complexType = this.complexType("artifactSet", _asList);
    _builder.append(_complexType, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence modelArtefactEncoding() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    String _s23m = this.s23m("graph");
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension("model", _s23m);
    _builder.append(_complexTypeWithExtension, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    String _s23m_1 = this.s23m("identityReference");
    CharSequence _element = this.element("container", _s23m_1);
    String _s23m_2 = this.s23m("isAbstract");
    CharSequence _elementRef = this.elementRef(_s23m_2);
    String _s23m_3 = this.s23m("vertex");
    CharSequence _elementList = this.elementList("vertex", _s23m_3);
    String _s23m_4 = this.s23m("visibility");
    CharSequence _elementList_1 = this.elementList("visibility", _s23m_4);
    String _s23m_5 = this.s23m("edge");
    CharSequence _elementList_2 = this.elementList("edge", _s23m_5);
    String _s23m_6 = this.s23m("superSetReference");
    CharSequence _elementList_3 = this.elementList("superSetReference", _s23m_6);
    String _s23m_7 = this.s23m("command");
    CharSequence _elementList_4 = this.elementList("command", _s23m_7);
    String _s23m_8 = this.s23m("query");
    CharSequence _elementList_5 = this.elementList("query", _s23m_8);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_element, _elementRef, _elementList, _elementList_1, _elementList_2, _elementList_3, _elementList_4, _elementList_5);
    CharSequence _categoryComplexType = this.categoryComplexType("graph", _asList);
    _builder.append(_categoryComplexType, "	");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence vertices() {
    StringConcatenation _builder = new StringConcatenation();
    String _s23m = this.s23m("isAbstract");
    CharSequence _elementRef = this.elementRef(_s23m);
    String _s23m_1 = this.s23m("maxCardinality");
    CharSequence _elementRef_1 = this.elementRef(_s23m_1);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_elementRef, _elementRef_1);
    CharSequence _categoryComplexType = this.categoryComplexType("vertex", _asList);
    _builder.append(_categoryComplexType, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence arrows() {
    StringConcatenation _builder = new StringConcatenation();
    String _s23m = this.s23m("isAbstract");
    CharSequence _elementRef = this.elementRef(_s23m);
    String _s23m_1 = this.s23m("from");
    CharSequence _elementRef_1 = this.elementRef(_s23m_1);
    String _s23m_2 = this.s23m("to");
    CharSequence _elementRef_2 = this.elementRef(_s23m_2);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_elementRef, _elementRef_1, _elementRef_2);
    CharSequence _categoryComplexType = this.categoryComplexType("superSetReference", _asList);
    _builder.append(_categoryComplexType, "");
    _builder.newLineIfNotEmpty();
    String _s23m_3 = this.s23m("isAbstract");
    CharSequence _elementRef_3 = this.elementRef(_s23m_3);
    String _s23m_4 = this.s23m("from");
    CharSequence _elementRef_4 = this.elementRef(_s23m_4);
    String _s23m_5 = this.s23m("to");
    CharSequence _elementRef_5 = this.elementRef(_s23m_5);
    List<CharSequence> _asList_1 = Arrays.<CharSequence>asList(_elementRef_3, _elementRef_4, _elementRef_5);
    CharSequence _categoryComplexType_1 = this.categoryComplexType("visibility", _asList_1);
    _builder.append(_categoryComplexType_1, "");
    _builder.newLineIfNotEmpty();
    String _s23m_6 = this.s23m("isAbstract");
    CharSequence _elementRef_6 = this.elementRef(_s23m_6);
    String _s23m_7 = this.s23m("edgeEnd");
    CharSequence _element = this.element("from", _s23m_7);
    String _s23m_8 = this.s23m("edgeEnd");
    CharSequence _element_1 = this.element("to", _s23m_8);
    List<CharSequence> _asList_2 = Arrays.<CharSequence>asList(_elementRef_6, _element, _element_1);
    CharSequence _categoryComplexType_2 = this.categoryComplexType("edge", _asList_2);
    _builder.append(_categoryComplexType_2, "");
    _builder.newLineIfNotEmpty();
    String _s23m_9 = this.s23m("isAbstract");
    CharSequence _elementRef_7 = this.elementRef(_s23m_9);
    String _s23m_10 = this.s23m("minCardinality");
    CharSequence _elementRef_8 = this.elementRef(_s23m_10);
    String _s23m_11 = this.s23m("maxCardinality");
    CharSequence _elementRef_9 = this.elementRef(_s23m_11);
    String _s23m_12 = this.s23m("isContainer");
    CharSequence _elementRef_10 = this.elementRef(_s23m_12);
    String _s23m_13 = this.s23m("isNavigable");
    CharSequence _elementRef_11 = this.elementRef(_s23m_13);
    List<CharSequence> _asList_3 = Arrays.<CharSequence>asList(_elementRef_7, _elementRef_8, _elementRef_9, _elementRef_10, _elementRef_11);
    CharSequence _categoryComplexType_3 = this.categoryComplexType("edgeEnd", _asList_3);
    _builder.append(_categoryComplexType_3, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence artifactFunctionality() {
    StringConcatenation _builder = new StringConcatenation();
    String _s23m = this.s23m("parameter");
    CharSequence _elementList = this.elementList("parameter", _s23m);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_elementList);
    CharSequence _categoryComplexType = this.categoryComplexType("function", _asList);
    _builder.append(_categoryComplexType, "");
    _builder.newLineIfNotEmpty();
    List<CharSequence> _emptyList = Collections.<CharSequence>emptyList();
    CharSequence _categoryComplexType_1 = this.categoryComplexType("parameter", _emptyList);
    _builder.append(_categoryComplexType_1, "");
    _builder.newLineIfNotEmpty();
    String _s23m_1 = this.s23m("function");
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension("command", _s23m_1);
    _builder.append(_complexTypeWithExtension, "");
    _builder.newLineIfNotEmpty();
    String _s23m_2 = this.s23m("function");
    CharSequence _complexTypeWithExtension_1 = this.complexTypeWithExtension("query", _s23m_2);
    _builder.append(_complexTypeWithExtension_1, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence semanticDomainArtefactEncoding() {
    StringConcatenation _builder = new StringConcatenation();
    String _s23m = this.s23m("model");
    CharSequence _elementRef = this.elementRef(_s23m);
    String _s23m_1 = this.s23m("identity");
    CharSequence _elementList = this.elementList("identity", _s23m_1);
    List<CharSequence> _asList = Arrays.<CharSequence>asList(_elementRef, _elementList);
    CharSequence _complexType = this.complexType("semanticDomain", _asList);
    _builder.append(_complexType, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _s23m_2 = this.s23m("uuid");
    CharSequence _element = this.element("identifier", _s23m_2);
    String _xsd = this.xsd("string");
    CharSequence _element_1 = this.element("name", _xsd);
    String _xsd_1 = this.xsd("string");
    CharSequence _element_2 = this.element("pluralName", _xsd_1);
    String _xsd_2 = this.xsd("string");
    CharSequence _element_3 = this.element("payload", _xsd_2);
    String _xsd_3 = this.xsd("string");
    CharSequence _element_4 = this.element("technicalName", _xsd_3);
    List<CharSequence> _asList_1 = Arrays.<CharSequence>asList(_element, _element_1, _element_2, _element_3, _element_4);
    CharSequence _complexType_1 = this.complexType("identity", _asList_1);
    _builder.append(_complexType_1, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence complexType(final String name, final List<CharSequence> elementsInSequence) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = this.xsd("complexType");
    _builder.append(_xsd, "");
    _builder.append(" name=\"");
    _builder.append(name, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<");
    String _xsd_1 = this.xsd("sequence");
    _builder.append(_xsd_1, "	");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    {
      for(final CharSequence element : elementsInSequence) {
        _builder.append("\t\t");
        _builder.append(element, "		");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("</");
    String _xsd_2 = this.xsd("sequence");
    _builder.append(_xsd_2, "	");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("</");
    String _xsd_3 = this.xsd("complexType");
    _builder.append(_xsd_3, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence complexTypeWithExtension(final String name, final String extensionBase) {
    List<CharSequence> _emptyList = Collections.<CharSequence>emptyList();
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension(name, extensionBase, _emptyList);
    return _complexTypeWithExtension;
  }
  
  private CharSequence complexTypeWithExtension(final String name, final String extensionBase, final List<CharSequence> elementsInSequence) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = this.xsd("complexType");
    _builder.append(_xsd, "");
    _builder.append(" name=\"");
    _builder.append(name, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<");
    String _xsd_1 = this.xsd("complexContent");
    _builder.append(_xsd_1, "	");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("<");
    String _xsd_2 = this.xsd("extension");
    _builder.append(_xsd_2, "		");
    _builder.append(" base=\"");
    _builder.append(XmlSchemaTemplate.S23M, "		");
    _builder.append(":");
    _builder.append(extensionBase, "		");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    {
      boolean _isEmpty = elementsInSequence.isEmpty();
      boolean _operator_not = BooleanExtensions.operator_not(_isEmpty);
      if (_operator_not) {
        _builder.append("\t\t\t");
        _builder.append("<");
        String _xsd_3 = this.xsd("sequence");
        _builder.append(_xsd_3, "			");
        _builder.append(">");
        _builder.newLineIfNotEmpty();
        {
          for(final CharSequence element : elementsInSequence) {
            _builder.append("\t\t\t");
            _builder.append("\t");
            _builder.append(element, "				");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t\t\t");
        _builder.append("</");
        String _xsd_4 = this.xsd("sequence");
        _builder.append(_xsd_4, "			");
        _builder.append(">");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("</");
    String _xsd_5 = this.xsd("extension");
    _builder.append(_xsd_5, "		");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</");
    String _xsd_6 = this.xsd("complexContent");
    _builder.append(_xsd_6, "	");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("</");
    String _xsd_7 = this.xsd("complexType");
    _builder.append(_xsd_7, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence categoryComplexType(final String name, final List<CharSequence> elementsInSequence) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _complexTypeWithExtension = this.complexTypeWithExtension(name, "category", elementsInSequence);
    _builder.append(_complexTypeWithExtension, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence simpleType(final String name, final String baseType) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = this.xsd("simpleType");
    _builder.append(_xsd, "");
    _builder.append(" name=\"");
    _builder.append(name, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<");
    String _xsd_1 = this.xsd("restriction");
    _builder.append(_xsd_1, "	");
    _builder.append(" base=\"");
    _builder.append(baseType, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("</");
    String _xsd_2 = this.xsd("simpleType");
    _builder.append(_xsd_2, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence element(final String name, final String type) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = this.xsd("element");
    _builder.append(_xsd, "");
    _builder.append(" name=\"");
    _builder.append(name, "");
    _builder.append("\" type=\"");
    _builder.append(type, "");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence elementRef(final String referencedName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = this.xsd("element");
    _builder.append(_xsd, "");
    _builder.append(" ref=\"");
    _builder.append(referencedName, "");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence elementRefList(final String referencedName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = this.xsd("element");
    _builder.append(_xsd, "");
    _builder.append(" ref=\"");
    _builder.append(referencedName, "");
    _builder.append("\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence elementList(final String name, final String type) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    String _xsd = this.xsd("element");
    _builder.append(_xsd, "");
    _builder.append(" name=\"");
    _builder.append(name, "");
    _builder.append("\" type=\"");
    _builder.append(type, "");
    _builder.append("\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private String xsd(final String name) {
    String _operator_plus = StringExtensions.operator_plus(XmlSchemaTemplate.XSD, ":");
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, name);
    return _operator_plus_1;
  }
  
  private String s23m(final String name) {
    String _operator_plus = StringExtensions.operator_plus(XmlSchemaTemplate.S23M, ":");
    String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, name);
    return _operator_plus_1;
  }
}
