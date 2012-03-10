/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Cell.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.s23m.cell.communication.xml.DocumentObjectModel.CompositeNode;
import org.s23m.cell.communication.xml.DocumentObjectModel.LeafNode;
import org.s23m.cell.communication.xml.DocumentObjectModel.Namespace;
import org.s23m.cell.communication.xml.DocumentObjectModel.Node;

interface XmlSchema {
	
	class Schema extends CompositeNode {
		public Schema(Namespace namespace) {
			super(namespace, "schema");
		}
	}

	class ComplexType extends CompositeNode {
		public ComplexType(Namespace namespace) {
			super(namespace, "complexType");
		}
		
		public ComplexType(Namespace namespace, Extension extension) {
			this(namespace);
			children.add(new ComplexContent(namespace, extension));
		}
	}
	
	class ComplexContent extends Node {
		final Extension extension;
		
		public ComplexContent(Namespace namespace, Extension extension) {
			super(namespace, "complexContent");
			this.extension = extension;
		}
	}
	
	class Extension extends Node {
		final Node base;
		final Sequence sequence;
		
		public Extension(Namespace namespace, Node base, Sequence sequence) {
			super(namespace, "extension");
			this.base = base;
			this.sequence = sequence;
		}
	}
	
	class Sequence extends Node {
		final List<LeafNode> children;
		
		public Sequence(Namespace namespace) {
			super(namespace, "sequence");
			this.children = new ArrayList<LeafNode>();
		}
	}

	class Element extends LeafNode {
		final Cardinality cardinality;
		final Node type;
		
		public Element(Namespace namespace, String name, Node type, Cardinality cardinality) {
			super(namespace, "element");
			this.type = type;
			this.cardinality = cardinality;
			this.attributes.put("type", type.qualifiedName());
			cardinality.addToAttributes(attributes);
		}
	}
	
	class ElementReference extends LeafNode {
		final Element referencedElement;
		/* Optional cardinality which can override that of the referenced element */
		final Cardinality cardinality;
		
		public ElementReference(Namespace namespace, Element referencedElement) {
			this(namespace, referencedElement, null);
		}
		
		public ElementReference(Namespace namespace, Element referencedElement, Cardinality cardinality) {
			super(namespace, "element");
			this.referencedElement = referencedElement;
			this.cardinality = cardinality;
			this.attributes.putAll(referencedElement.attributes);
			if (cardinality != null) {
				// replace attributes
				Cardinality.removeFromAttributes(attributes);
				cardinality.addToAttributes(attributes);
			}
		}
	}
    
    class Cardinality {
        private static final int DEFAULT_VALUE = 1;
        private static final String MIN_OCCURS = "minOccurs";
        private static final String MAX_OCCURS = "maxOccurs";
        
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
				attributes.put(MIN_OCCURS, displayedValue(minOccurs));
				attributes.put(MAX_OCCURS, displayedValue(maxOccurs));
			}
		}

        public static void removeFromAttributes(Map<String, String> attributes) {
        	attributes.remove(MIN_OCCURS);
			attributes.remove(MAX_OCCURS);
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
