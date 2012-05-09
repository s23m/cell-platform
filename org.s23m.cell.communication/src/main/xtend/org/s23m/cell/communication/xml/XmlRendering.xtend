package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.CompositeNode
import org.s23m.cell.communication.xml.dom.Node

import org.s23m.cell.communication.xml.schema.Schema
import org.s23m.cell.communication.xml.schemainstance.ArtifactSet
import org.s23m.cell.communication.xml.schemainstance.StringElement

import static org.s23m.cell.communication.xml.NamespaceExtensions.*

class XmlRendering {
	
	static int INDENTATION = 2
	static String PREAMBLE = '<?xml version="1.0" encoding="UTF-8"?>'
	
	def static render(Schema node) '''
		«PREAMBLE»
		«render(node, 0)»
	'''
	
	def static render(ArtifactSet node) '''
		«PREAMBLE»
		«render(node, 0)»
	'''
	
	def private static dispatch render(CompositeNode node, int level) '''
		«val children = node.children»
		«renderPrefix(node, level)»«IF children.empty»/>
		«ELSE»>
		«FOR n : children»
			«render(n, level + 1)»
		«ENDFOR»
		«renderSuffix(node, level)»
		«ENDIF»
	'''
	
	def private static dispatch render(StringElement node, int level) {
		renderPrefix(node, level) + ">" + node.text + renderSuffix(node)
	}
	
	def private static dispatch render(Node node, int level) {
		renderPrefix(node, level) + "/>"
	}
		
	def private static renderPrefix(Node node, int level) {
		whitespace(level) + "<" + name(node) + renderAttributes(node)
	}
	
	def private static renderSuffix(Node node, int level) {
		whitespace(level) + renderSuffix(node)
	}
	
	def private static renderSuffix(Node node) {
		"</" + name(node) + ">"
	}
	
	def private static whitespace(int level) {
		StringUtils::repeat(level * INDENTATION, " ")
	}
	
	def private static name(Node node) {
		qualifiedName(node.namespace.prefix, node.name)
	}
	
	def private static renderAttributes(Node node) {
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