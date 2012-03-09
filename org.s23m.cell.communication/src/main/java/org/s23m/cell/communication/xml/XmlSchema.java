package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.s23m.cell.communication.xml.DocumentObjectModel.CompositeNode;
import org.s23m.cell.communication.xml.DocumentObjectModel.LeafNode;
import org.s23m.cell.communication.xml.DocumentObjectModel.Node;

interface XmlSchema {
	
	class Schema extends CompositeNode {
		public Schema() {
			this.name = "schema";
		}
	}

	class ComplexType extends CompositeNode {
		public ComplexType() {
			this.name = "complexType";
		}
		
		public ComplexType(Extension extension) {
			this();
			children.add(new ComplexContent(extension));
		}
	}
	
	class ComplexContent extends Node {
		final Extension extension;
		
		public ComplexContent(Extension extension) {
			this.name = "complexContent";
			this.extension = extension;
		}
	}
	
	class Extension extends Node {
		final Node base;
		final Sequence sequence;
		
		public Extension(Node base, Sequence sequence) {
			this.name = "extension";
			this.base = base;
			this.sequence = sequence;
		}
	}
	
	class Sequence extends Node {
		final List<LeafNode> children;
		
		public Sequence() {
			this.name = "sequence";
			this.children = new ArrayList<LeafNode>();
		}
	}

	class Element extends LeafNode {
		final Cardinality cardinality;
		final Node type;
		
		public Element(String name, Node type, Cardinality cardinality) {
			this.name = "element";
			this.type = type;
			this.cardinality = cardinality;
			this.attributes = new HashMap<String, String>();
			this.attributes.put("type", type.qualifiedName());
			cardinality.addToAttributes(attributes);
		}
	}
	
	class ElementReference extends LeafNode {
		final Element referencedElement;
		/* Optional cardinality which can override that of the referenced element */
		Cardinality cardinality;
		
		public ElementReference(String name, Element referencedElement, Cardinality cardinality) {
			this.name = "element";
			this.referencedElement = referencedElement;
			this.attributes = new HashMap<String, String>(referencedElement.attributes);
			if (cardinality != null) {
				// replace attributes
				Cardinality.removeFromAttributes(attributes);
				cardinality.addToAttributes(attributes);
			}
		}
	}
    
    class Cardinality {
        private static int DEFAULT_VALUE = 1;
        
    	final int minOccurs;
        final int maxOccurs;
        
        static Cardinality OPTIONAL = new Cardinality(0, 1);
        static Cardinality ZERO_TO_MANY = new Cardinality(0, -1);
        static Cardinality EXACTLY_ONE = new Cardinality(1, 1);
        
        public Cardinality(int minOccurs, int maxOccurs) {
        	this.minOccurs = minOccurs;
        	this.maxOccurs = maxOccurs;
        }
        
		public void addToAttributes(Map<String, String> attributes) {
			if (minOccurs != DEFAULT_VALUE || maxOccurs != DEFAULT_VALUE) {
				attributes.put("minOccurs", displayedValue(minOccurs));
				attributes.put("maxOccurs", displayedValue(maxOccurs));
			}
		}

        public static void removeFromAttributes(Map<String, String> attributes) {
        	attributes.remove("minOccurs");
			attributes.remove("maxOccurs");
        }

		private static String displayedValue(int number) {
			if (number == -1) {
				return "unbounded";
			} else {
				return String.valueOf(number);
			}
		}
    }
}
