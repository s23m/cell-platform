package org.s23m.cell.communication.xml

import org.s23m.cell.communication.xml.dom.CompositeNode
import org.s23m.cell.communication.xml.dom.Node

import org.s23m.cell.communication.xml.schema.Schema

class XmlRendering {
	
	static int INDENTATION = 2
	
	def static render(Schema node) '''
		<?xml version="1.0" encoding="UTF-8"?>
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
	
	def private static dispatch render(Node node, int level) {
		renderPrefix(node, level) + "/>"
	}
		
	def private static renderPrefix(Node node, int level) {
		whitespace(level) + "<" + name(node) + renderAttributes(node)
	}
	
	def private static renderSuffix(Node node, int level) {
		whitespace(level) + "</" + name(node) + ">"
	}
	
	def private static whitespace(int level) {
		StringUtils::repeat(level * INDENTATION, " ")
	}
	
	def private static name(Node node) {
		node.namespace.prefix + ":" + node.name
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