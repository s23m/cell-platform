package org.s23m.cell.communication.xml;

import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.s23m.cell.communication.xml.DefaultXmlSchemaTerminology;
import org.s23m.cell.communication.xml.InstanceBuilder;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.NamespaceExtensions;
import org.s23m.cell.communication.xml.XmlRendering;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.schemainstance.CategoryIdentityReference;
import org.s23m.cell.communication.xml.schemainstance.Container;
import org.s23m.cell.communication.xml.schemainstance.IsAbstract;
import org.s23m.cell.communication.xml.schemainstance.Model;
import org.s23m.cell.communication.xml.schemainstance.SemanticIdentity;

@SuppressWarnings("all")
public class InstanceBuilderTest {
  public static void main(final String[] args) {
      final Namespace s23m = NamespaceConstants.NS_S23M;
      XmlSchemaTerminology _instance = DefaultXmlSchemaTerminology.getInstance();
      final XmlSchemaTerminology terminology = _instance;
      final String languageIdentifier = "ENGLISH";
      final Procedure1<ArtifactSet> _function = new Procedure1<ArtifactSet>() {
          public void apply(final ArtifactSet it) {
            {
              String _xmlns = NamespaceExtensions.xmlns(NamespaceConstants.INSTANCE_NAMESPACE_PREFIX);
              it.setAttribute(_xmlns, NamespaceConstants.INSTANCE_SCHEMA_URI);
              String _xmlns_1 = NamespaceExtensions.xmlns(NamespaceConstants.S23M_SCHEMA);
              it.setAttribute(_xmlns_1, NamespaceConstants.S23M_SCHEMA);
            }
          }
        };
      InstanceBuilder _instanceBuilder = new InstanceBuilder(s23m, terminology, languageIdentifier, _function);
      final InstanceBuilder builder = _instanceBuilder;
      SemanticIdentity _semanticIdentity = builder.semanticIdentity("1", "2");
      CategoryIdentityReference _category = builder.category("3", "4");
      Container _container = builder.container("5", "6");
      IsAbstract _isAbstract = builder.isAbstract("7", "8");
      final Procedure1<Model> _function_1 = new Procedure1<Model>() {
          public void apply(final Model it) {
          }
        };
      Model _model = builder.model(_semanticIdentity, _category, _container, _isAbstract, _function_1);
      final Model model = _model;
      ArtifactSet _build = builder.build();
      final ArtifactSet result = _build;
      CharSequence _render = XmlRendering.render(result);
      String _string = _render.toString();
      final String xml = _string;
      String _operator_plus = StringExtensions.operator_plus("xml: ", xml);
      InputOutput.<String>println(_operator_plus);
  }
}
