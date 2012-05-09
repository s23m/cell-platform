package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.s23m.cell.communication.xml.DefaultXmlSchemaTerminology;
import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.OperatorExtensions;
import org.s23m.cell.communication.xml.XmlRendering;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.schemainstance.CategoryIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.ContainerIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.FromIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.MaximumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.Model;
import org.s23m.cell.communication.xml.schemainstance.SemanticIdentityIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.ToIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.Vertex;
import org.s23m.cell.communication.xml.schemainstance.Visibility;

@SuppressWarnings("all")
public class InstanceBuilderTest {
  public static void main(final String[] args) {
      final Namespace s23m = NamespaceConstants.NS_S23M;
      XmlSchemaTerminology _instance = DefaultXmlSchemaTerminology.getInstance();
      final XmlSchemaTerminology terminology = _instance;
      final String languageIdentifier = "ENGLISH";
      InstanceBuilder _instanceBuilder = new InstanceBuilder(s23m, terminology, languageIdentifier);
      final InstanceBuilder builder = _instanceBuilder;
      SemanticIdentityIdentityReference _semanticIdentity = builder.semanticIdentity("1", "2");
      CategoryIdentityReference _category = builder.category("3", "4");
      ContainerIdentityReference _container = builder.container("5", "6");
      IsAbstractIdentityReference _isAbstract = builder.isAbstract("7", "8");
      Model _model = builder.model(_semanticIdentity, _category, _container, _isAbstract);
      final Model model = _model;
      SemanticIdentityIdentityReference _semanticIdentity_1 = builder.semanticIdentity("1", "2");
      CategoryIdentityReference _category_1 = builder.category("3", "4");
      IsAbstractIdentityReference _isAbstract_1 = builder.isAbstract("9", "10");
      MaximumCardinalityIdentityReference _maxCardinality = builder.maxCardinality("11", "12");
      Vertex _vertex = builder.vertex(_semanticIdentity_1, _category_1, _isAbstract_1, _maxCardinality);
      OperatorExtensions.operator_add(model, _vertex);
      SemanticIdentityIdentityReference _semanticIdentity_2 = builder.semanticIdentity("1", "2");
      CategoryIdentityReference _category_2 = builder.category("3", "4");
      IsAbstractIdentityReference _isAbstract_2 = builder.isAbstract("9", "10");
      FromIdentityReference _from = builder.from("3", "4");
      ToIdentityReference _to = builder.to("1", "2");
      Visibility _visibility = builder.visibility(_semanticIdentity_2, _category_2, _isAbstract_2, _from, _to);
      OperatorExtensions.operator_add(model, _visibility);
      ArtifactSet _build = builder.build();
      final ArtifactSet result = _build;
      CharSequence _render = XmlRendering.render(result);
      String _string = _render.toString();
      final String xml = _string;
      String _operator_plus = StringExtensions.operator_plus("xml: ", xml);
      InputOutput.<String>println(_operator_plus);
  }
}
