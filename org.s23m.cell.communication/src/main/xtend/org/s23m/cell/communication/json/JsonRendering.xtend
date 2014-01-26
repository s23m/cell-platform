package org.s23m.cell.communication.json

import org.s23m.cell.communication.xml.StringUtils
import org.s23m.cell.communication.xml.model.dom.CompositeNode
import org.s23m.cell.communication.xml.model.dom.Node
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet
import org.s23m.cell.communication.xml.model.schemainstance.StringElement

class JsonRendering {
	
	final static int INDENTATION = 2
	
	final static String NEWLINE = '\n'
	final static String COMMA = ','
	final static String COLON = ': ';
	final static String START_BRACE = '{';
	final static String END_BRACE = '}';
	
	static def render(ArtifactSet node) {
		doRender(node).toString
	}
	
	private static def doRender(CompositeNode node) '''
		{
		«render(node, 1)»
		}
	'''
	
	private static def dispatch String render(CompositeNode node, int level) '''
		«val children = node.children»
		«renderPrefix(node, level)»{
		«FOR n : children SEPARATOR COMMA»
			«render(n, level + 1)»
		«ENDFOR»
		«renderSuffix(node, level)»
	'''
	
	private static dispatch def String render(StringElement node, int level) {
		renderPrefix(node, level) + quote(node.text)
	}
	
	private static dispatch def String render(Node node, int level) {
		val builder = new StringBuilder
		builder.append(renderPrefix(node, level))
		builder.append(START_BRACE)
		builder.append(renderAttributes(node, level + 1))
		builder.append(NEWLINE)
		builder.append(whitespace(level))
		builder.append(END_BRACE)
		builder.toString
	}
		
	private static def renderPrefix(Node node, int level) {
		whitespace(level) + quote(node.name) + COLON
	}
	
	private static def renderSuffix(Node node, int level) {
		whitespace(level) + END_BRACE
	}
	
	private static def whitespace(int level) {
		StringUtils.repeat(level * INDENTATION, " ")
	}
	
	private static def quote(String s) {
		'"' + s + '"'
	}
	
	// attributes and elements are rendered equivalently in JSON
	private static def renderAttributes(Node node, int level) {
		val indentation = whitespace(level);
		val builder = new StringBuilder
		val entrySet = node.attributes.entrySet
		val iterator = entrySet.iterator
		while (iterator.hasNext) {
			val entry = iterator.next
			builder.append(NEWLINE)
			builder.append(indentation)
			builder.append(quote(entry.key))
			builder.append(COLON)
			builder.append(quote(entry.value))
			if (iterator.hasNext) {
				builder.append(COMMA)
			}
		}
		builder.toString
	}
}