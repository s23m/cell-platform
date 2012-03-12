package org.s23m.cell.communication.xml.dom;

import java.util.Map;

import org.s23m.cell.communication.xml.schema.Cardinality;

public interface Node {

	Namespace getNamespace();
	
	String getName();
		
	Map<String, String> getAttributes();
	
	void setAttribute(String key, String value);
	
	void updateCardinality(Cardinality cardinality);
}
