package org.s23m.cell.communication.xml

class NamespaceExtensions {
	
	def static String xmlns(String name) {
		qualifiedName("xmlns", name)
	}
	
	def static String qualifiedName(String namespacePrefix, String name) {
		namespacePrefix + ":" + name
	}
}