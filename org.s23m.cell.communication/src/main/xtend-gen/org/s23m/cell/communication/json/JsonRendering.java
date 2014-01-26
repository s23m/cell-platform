package org.s23m.cell.communication.json;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.s23m.cell.communication.xml.StringUtils;
import org.s23m.cell.communication.xml.model.dom.CompositeNode;
import org.s23m.cell.communication.xml.model.dom.Node;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.StringElement;

@SuppressWarnings("all")
public class JsonRendering {
  private final static int INDENTATION = 2;
  
  private final static String NEWLINE = "\n";
  
  private final static String COMMA = ",";
  
  private final static String COLON = ": ";
  
  private final static String START_BRACE = "{";
  
  private final static String END_BRACE = "}";
  
  public static String render(final ArtifactSet node) {
    CharSequence _doRender = JsonRendering.doRender(node);
    String _string = _doRender.toString();
    return _string;
  }
  
  private static CharSequence doRender(final CompositeNode node) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    String _render = JsonRendering.render(node, 1);
    _builder.append(_render, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private static String _render(final CompositeNode node, final int level) {
    StringConcatenation _builder = new StringConcatenation();
    final Iterable<? extends Node> children = node.getChildren();
    _builder.newLineIfNotEmpty();
    String _renderPrefix = JsonRendering.renderPrefix(node, level);
    _builder.append(_renderPrefix, "");
    _builder.append("{");
    _builder.newLineIfNotEmpty();
    {
      boolean _hasElements = false;
      for(final Node n : children) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(JsonRendering.COMMA, "");
        }
        int _plus = (level + 1);
        String _render = JsonRendering.render(n, _plus);
        _builder.append(_render, "");
        _builder.newLineIfNotEmpty();
      }
    }
    String _renderSuffix = JsonRendering.renderSuffix(node, level);
    _builder.append(_renderSuffix, "");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  private static String _render(final StringElement node, final int level) {
    String _renderPrefix = JsonRendering.renderPrefix(node, level);
    String _text = node.getText();
    String _quote = JsonRendering.quote(_text);
    String _plus = (_renderPrefix + _quote);
    return _plus;
  }
  
  private static String _render(final Node node, final int level) {
    String _xblockexpression = null;
    {
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder builder = _stringBuilder;
      String _renderPrefix = JsonRendering.renderPrefix(node, level);
      builder.append(_renderPrefix);
      builder.append(JsonRendering.START_BRACE);
      int _plus = (level + 1);
      String _renderAttributes = JsonRendering.renderAttributes(node, _plus);
      builder.append(_renderAttributes);
      builder.append(JsonRendering.NEWLINE);
      String _whitespace = JsonRendering.whitespace(level);
      builder.append(_whitespace);
      builder.append(JsonRendering.END_BRACE);
      String _string = builder.toString();
      _xblockexpression = (_string);
    }
    return _xblockexpression;
  }
  
  private static String renderPrefix(final Node node, final int level) {
    String _whitespace = JsonRendering.whitespace(level);
    String _name = node.getName();
    String _quote = JsonRendering.quote(_name);
    String _plus = (_whitespace + _quote);
    String _plus_1 = (_plus + JsonRendering.COLON);
    return _plus_1;
  }
  
  private static String renderSuffix(final Node node, final int level) {
    String _whitespace = JsonRendering.whitespace(level);
    String _plus = (_whitespace + JsonRendering.END_BRACE);
    return _plus;
  }
  
  private static String whitespace(final int level) {
    int _multiply = (level * JsonRendering.INDENTATION);
    String _repeat = StringUtils.repeat(_multiply, " ");
    return _repeat;
  }
  
  private static String quote(final String s) {
    String _plus = ("\"" + s);
    String _plus_1 = (_plus + "\"");
    return _plus_1;
  }
  
  private static String renderAttributes(final Node node, final int level) {
    String _xblockexpression = null;
    {
      final String indentation = JsonRendering.whitespace(level);
      StringBuilder _stringBuilder = new StringBuilder();
      final StringBuilder builder = _stringBuilder;
      Map<String,String> _attributes = node.getAttributes();
      final Set<Entry<String,String>> entrySet = _attributes.entrySet();
      final Iterator<Entry<String,String>> iterator = entrySet.iterator();
      boolean _hasNext = iterator.hasNext();
      boolean _while = _hasNext;
      while (_while) {
        {
          final Entry<String,String> entry = iterator.next();
          builder.append(JsonRendering.NEWLINE);
          builder.append(indentation);
          String _key = entry.getKey();
          String _quote = JsonRendering.quote(_key);
          builder.append(_quote);
          builder.append(JsonRendering.COLON);
          String _value = entry.getValue();
          String _quote_1 = JsonRendering.quote(_value);
          builder.append(_quote_1);
          boolean _hasNext_1 = iterator.hasNext();
          if (_hasNext_1) {
            builder.append(JsonRendering.COMMA);
          }
        }
        boolean _hasNext_1 = iterator.hasNext();
        _while = _hasNext_1;
      }
      String _string = builder.toString();
      _xblockexpression = (_string);
    }
    return _xblockexpression;
  }
  
  private static String render(final Node node, final int level) {
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
