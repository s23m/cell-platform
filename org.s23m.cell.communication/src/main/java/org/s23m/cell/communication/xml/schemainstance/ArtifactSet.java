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
 * The Original Code is S23M.
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
package org.s23m.cell.communication.xml.schemainstance;

import java.util.ArrayList;
import java.util.List;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.AbstractCompositeNode;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

// TODO generate this class from the schema instance (and reduce duplication)?
public class ArtifactSet extends AbstractCompositeNode {
	
	private final StringElement languageIdentifier;
	
	private final List<Model> modelList;
	
	private final List<SemanticDomain> semanticDomainList;
	
	public ArtifactSet(Namespace namespace, XmlSchemaTerminology terminology, String languageIdentifier) {
		super(namespace, terminology.artifactSet());
		this.languageIdentifier = new StringElement(namespace, terminology.languageIdentifier(), languageIdentifier);
		this.modelList = new ArrayList<Model>();
		this.semanticDomainList = new ArrayList<SemanticDomain>();
	}
	
	public StringElement getLanguageIdentifier() {
		return languageIdentifier;
	}
	
	public List<Model> getModelList() {
		return modelList;
	}
	
	public void addModel(Model model) {
		modelList.add(model);
	}
	
	public List<SemanticDomain> getSemanticDomainList() {
		return semanticDomainList;
	}
	
	public void addSemanticDomain(SemanticDomain semanticDomain) {
		semanticDomainList.add(semanticDomain);
	}

	@Override
	public Iterable<? extends Node> getChildren() {
		return Iterables.concat(
			ImmutableList.of(languageIdentifier),
			modelList,
			semanticDomainList
		);
	}
}