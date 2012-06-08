package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.s23m.cell.communication.xml.DefaultXmlSchemaTerminology;
import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.OperatorExtensions;
import org.s23m.cell.communication.xml.XmlRendering;
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

@SuppressWarnings("all")
public class InstanceBuilderTest {
  public static void main(final String[] args) {
      final Namespace s23m = NamespaceConstants.NS_S23M;
      XmlSchemaTerminology _instance = DefaultXmlSchemaTerminology.getInstance();
      final XmlSchemaTerminology terminology = _instance;
      final String languageIdentifier = "ENGLISH";
      InstanceBuilder _instanceBuilder = new InstanceBuilder(s23m, terminology, languageIdentifier);
      final InstanceBuilder builder = _instanceBuilder;
      Pair<String,String> _operator_mappedTo = ObjectExtensions.<String, String>operator_mappedTo("1", "2");
      SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity(_operator_mappedTo);
      Pair<String,String> _operator_mappedTo_1 = ObjectExtensions.<String, String>operator_mappedTo("3", "4");
      CategoryIdentityReference _category = builder.category(_operator_mappedTo_1);
      Pair<String,String> _operator_mappedTo_2 = ObjectExtensions.<String, String>operator_mappedTo("5", "6");
      ContainerIdentityReference _container = builder.container(_operator_mappedTo_2);
      Pair<String,String> _operator_mappedTo_3 = ObjectExtensions.<String, String>operator_mappedTo("7", "8");
      IsAbstractIdentityReference _isAbstract = builder.isAbstract(_operator_mappedTo_3);
      Model _model = builder.model(_semanticIdentity, _category, _container, _isAbstract);
      final Model model = _model;
      Pair<String,String> _operator_mappedTo_4 = ObjectExtensions.<String, String>operator_mappedTo("1", "2");
      SemanticIdentityIdentityReference _semanticIdentity_1 = builder.semanticIdentity(_operator_mappedTo_4);
      Pair<String,String> _operator_mappedTo_5 = ObjectExtensions.<String, String>operator_mappedTo("3", "4");
      CategoryIdentityReference _category_1 = builder.category(_operator_mappedTo_5);
      Pair<String,String> _operator_mappedTo_6 = ObjectExtensions.<String, String>operator_mappedTo("9", "10");
      IsAbstractIdentityReference _isAbstract_1 = builder.isAbstract(_operator_mappedTo_6);
      Pair<String,String> _operator_mappedTo_7 = ObjectExtensions.<String, String>operator_mappedTo("11", "12");
      MaximumCardinalityIdentityReference _maxCardinality = builder.maxCardinality(_operator_mappedTo_7);
      Vertex _vertex = builder.vertex(_semanticIdentity_1, _category_1, _isAbstract_1, _maxCardinality);
      OperatorExtensions.operator_add(model, _vertex);
      Pair<String,String> _operator_mappedTo_8 = ObjectExtensions.<String, String>operator_mappedTo("1", "2");
      SemanticIdentityIdentityReference _semanticIdentity_2 = builder.semanticIdentity(_operator_mappedTo_8);
      Pair<String,String> _operator_mappedTo_9 = ObjectExtensions.<String, String>operator_mappedTo("3", "4");
      CategoryIdentityReference _category_2 = builder.category(_operator_mappedTo_9);
      Pair<String,String> _operator_mappedTo_10 = ObjectExtensions.<String, String>operator_mappedTo("9", "10");
      IsAbstractIdentityReference _isAbstract_2 = builder.isAbstract(_operator_mappedTo_10);
      Pair<String,String> _operator_mappedTo_11 = ObjectExtensions.<String, String>operator_mappedTo("3", "4");
      FromIdentityReference _from = builder.from(_operator_mappedTo_11);
      Pair<String,String> _operator_mappedTo_12 = ObjectExtensions.<String, String>operator_mappedTo("1", "2");
      ToIdentityReference _to = builder.to(_operator_mappedTo_12);
      Visibility _visibility = builder.visibility(_semanticIdentity_2, _category_2, _isAbstract_2, _from, _to);
      OperatorExtensions.operator_add(model, _visibility);
      ArtifactSet _build = builder.build();
      final ArtifactSet result = _build;
      String _render = XmlRendering.render(result);
      String _string = _render.toString();
      final String xml = _string;
      String _operator_plus = StringExtensions.operator_plus("xml: ", xml);
      InputOutput.<String>println(_operator_plus);
  }
}
