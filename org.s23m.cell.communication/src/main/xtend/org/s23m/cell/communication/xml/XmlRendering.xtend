package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.model.dom.CompositeNode
import org.s23m.cell.communication.xml.model.dom.Node

import org.s23m.cell.communication.xml.model.schema.Schema
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet
import org.s23m.cell.communication.xml.model.schemainstance.StringElement

import static org.s23m.cell.communication.xml.NamespaceExtensions.*

class XmlRendering {
	
	static int INDENTATION = 2
	static String PREAMBLE = '<?xml version="1.0" encoding="UTF-8"?>'
	
	static def render(Schema node) {
		doRender(node).toString
	}
	
	static def render(ArtifactSet node) {
		doRender(node).toString
	}
	
	private static def doRender(CompositeNode node) '''
		«PREAMBLE»
		«render(node, 0)»
	'''
	
	private static def dispatch String render(CompositeNode node, int level) '''
		«val children = node.children»
		«renderPrefix(node, level)»«IF children.empty»/>
		«ELSE»>
		«FOR n : children»
			«render(n, level + 1)»
		«ENDFOR»
		«renderSuffix(node, level)»
		«ENDIF»
	'''
	
	private static dispatch def String render(StringElement node, int level) {
		renderPrefix(node, level) + ">" + node.text + renderSuffix(node)
	}
	
	private static dispatch def String render(Node node, int level) {
		renderPrefix(node, level) + "/>"
	}
		
	private static def renderPrefix(Node node, int level) {
		val builder = new StringBuilder
		builder.append(whitespace(level))
		builder.append("<")
		builder.append(name(node))
		builder.append(renderAttributes(node))
		builder.toString
	}
	
	private static def renderSuffix(Node node, int level) {
		whitespace(level) + renderSuffix(node)
	}
	
	private static def renderSuffix(Node node) {
		"</" + name(node) + ">"
	}
	
	private static def whitespace(int level) {
		StringUtils.repeat(level * INDENTATION, " ")
	}
	
	private static def name(Node node) {
		qualifiedName(node.namespace.prefix, node.name)
	}
	
	private static def renderAttributes(Node node) {
		val builder = new StringBuilder()
		val entrySet = node.attributes.entrySet
		for (entry : entrySet) {
			builder.append(' ')
			builder.append(entry.key)
			builder.append('="')
			builder.append(entry.value)
			builder.append('"')
		}
		builder.toString
	}
}