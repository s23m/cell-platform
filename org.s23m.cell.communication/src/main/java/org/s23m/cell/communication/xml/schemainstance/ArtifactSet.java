package org.s23m.cell.communication.xml.schemainstance;

import java.util.ArrayList;
import java.util.List;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.AbstractNode;
import org.s23m.cell.communication.xml.dom.CompositeNode;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;
import org.s23m.cell.communication.xml.schema.Cardinality;
import org.s23m.cell.communication.xml.schema.DataType;
import org.s23m.cell.communication.xml.schema.Element;

import com.google.common.collect.Iterables;

// TODO generate this class from the schema instance (reduces duplication)
public class ArtifactSet extends AbstractNode implements CompositeNode {
	
	private final Element languageIdentifier;
	
	private final List<Model> modelList;
	
	private final List<SemanticDomain> semanticDomainList;
	
	public ArtifactSet(Namespace namespace, XmlSchemaTerminology terminology) {
		super(namespace, terminology.artifactSet());
		this.languageIdentifier = new Element(namespace, terminology.languageIdentifier(), DataType.STRING, Cardinality.EXACTLY_ONE);
		this.modelList = new ArrayList<Model>();
		this.semanticDomainList = new ArrayList<SemanticDomain>();
	}
	
	public Element getLanguageIdentifier() {
		return languageIdentifier;
	}
	
	public List<Model> getModelList() {
		return modelList;
	}
	
	public List<SemanticDomain> getSemanticDomainList() {
		return semanticDomainList;
	}

	@Override
	public Iterable<? extends Node> getChildren() {
		return Iterables.concat(modelList, semanticDomainList);
	}
}