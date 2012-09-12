package org.s23m.cell.communication.xml;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.s23m.cell.communication.xml.NamespaceExtensions;
import org.s23m.cell.communication.xml.StringUtils;
import org.s23m.cell.communication.xml.model.dom.CompositeNode;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schema.Schema;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.StringElement;

@SuppressWarnings("all")
public class XmlRendering {
  private static int INDENTATION = 2;
  
  private static String PREAMBLE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  
  public static String render(final Schema node) {
    CharSequence _doRender = XmlRendering.doRender(node);
    String _string = _doRender.toString();
    return _string;
  }
  
  public static String render(final ArtifactSet node) {
    CharSequence _doRender = XmlRendering.doRender(node);
    String _string = _doRender.toString();
    return _string;
  }
  
  private static CharSequence doRender(final CompositeNode node) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4PREAMBLE\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4render(node, 0)\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private static CharSequence _render(final CompositeNode node, final int level) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\u00AC\u00B4val children = node.children\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4renderPrefix(node, level)\u00AC\u00AA\u00AC\u00B4IF children.empty\u00AC\u00AA/>");
    _builder.newLine();
    _builder.append("\u00AC\u00B4ELSE\u00AC\u00AA>");
    _builder.newLine();
    _builder.append("\u00AC\u00B4FOR n : children\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\u00AC\u00B4render(n, level + 1)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4ENDFOR\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4renderSuffix(node, level)\u00AC\u00AA");
    _builder.newLine();
    _builder.append("\u00AC\u00B4ENDIF\u00AC\u00AA");
    _builder.newLine();
    return _builder;
  }
  
  private static CharSequence _render(final StringElement node, final int level) {
    String _renderPrefix = XmlRendering.renderPrefix(node, level);
    String _plus = (_renderPrefix + ">");
    String _text = node.getText();
    String _plus_1 = (_plus + _text);
    String _renderSuffix = XmlRendering.renderSuffix(node);
    String _plus_2 = (_plus_1 + _renderSuffix);
    return _plus_2;
  }
  
  private static CharSequence _render(final Node node, final int level) {
    String _renderPrefix = XmlRendering.renderPrefix(node, level);
    String _plus = (_renderPrefix + "/>");
    return _plus;
  }
  
  private static String renderPrefix(final Node node, final int level) {
    String _whitespace = XmlRendering.whitespace(level);
    String _plus = (_whitespace + "<");
    String _name = XmlRendering.name(node);
    String _plus_1 = (_plus + _name);
    String _renderAttributes = XmlRendering.renderAttributes(node);
    String _plus_2 = (_plus_1 + _renderAttributes);
    return _plus_2;
  }
  
  private static String renderSuffix(final Node node, final int level) {
    String _whitespace = XmlRendering.whitespace(level);
    String _renderSuffix = XmlRendering.renderSuffix(node);
    String _plus = (_whitespace + _renderSuffix);
    return _plus;
  }
  
  private static String renderSuffix(final Node node) {
    String _name = XmlRendering.name(node);
    String _plus = ("</" + _name);
    String _plus_1 = (_plus + ">");
    return _plus_1;
  }
  
  private static String whitespace(final int level) {
    int _multiply = (level * XmlRendering.INDENTATION);
    String _repeat = StringUtils.repeat(_multiply, " ");
    return _repeat;
  }
  
  private static String name(final Node node) {
    Namespace _namespace = node.getNamespace();
    String _prefix = _namespace.getPrefix();
    String _name = node.getName();
    String _qualifiedName = NamespaceExtensions.qualifiedName(_prefix, _name);
    return _qualifiedName;
  }
  
  private static String renderAttributes(final Node node) {
    String _xblockexpression = null;
    {
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder builder = _stringBuilder;
      Map<String,String> _attributes = node.getAttributes();
      final Set<Entry<String,String>> entrySet = _attributes.entrySet();
      for (final Entry<String,String> entry : entrySet) {
        {
          builder.append(" ");
          String _key = entry.getKey();
          builder.append(_key);
          builder.append("=\"");
          String _value = entry.getValue();
          builder.append(_value);
          builder.append("\"");
        }
      }
      String _string = builder.toString();
      _xblockexpression = (_string);
    }
    return _xblockexpression;
  }
  
  private static CharSequence render(final Node node, final int level) {
    if (node instanceof StringElement) {
      return _render((StringElement)node, level);
    } else if (node instanceof CompositeNode) {
      return _render((CompositeNode)node, level);
    } else if (node != null) {
      return _render(node, level);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(node, level).toString());
    }
  }
}
