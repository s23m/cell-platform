package org.s23m.cell.communication.xml

// TODO: convert to inner class (Xtend 2.3)
// TODO: use @Data (Xtend 2.3)
class IdentityReferenceAttributes {
	public String uniqueRepresentationReference
	public String identifier
	public String nameAttribute
	
	new(String u, String i, String n) {
		this.uniqueRepresentationReference = u
		this.identifier = i
		this.nameAttribute = n
	}
}