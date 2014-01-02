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
package org.s23m.cell.communication.xml.model.schemainstance;

import java.util.List;

import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public abstract class Graph extends Category {
		
	private ContainerIdentityReference container;
	
	private IsAbstractIdentityReference isAbstract;
	
	private final List<Vertex> vertexList;
	
	private final List<Edge> edgeList;
	
	private final List<Visibility> visibilityList;
	
	private final List<SuperSetReference> superSetReferenceList;
	
	private final List<Command> commandList;
	
	private final List<Query> queryList;

	protected Graph(Namespace namespace, String name) {
		super(namespace, name);
		
		this.vertexList = Lists.newArrayList();
		this.edgeList = Lists.newArrayList();
		this.visibilityList = Lists.newArrayList();
		this.superSetReferenceList = Lists.newArrayList();
		this.commandList = Lists.newArrayList();
		this.queryList = Lists.newArrayList();
	}

	public ContainerIdentityReference getContainer() {
		return container;
	}
	
	public void setContainer(ContainerIdentityReference container) {
		this.container = container;
	}

	public IsAbstractIdentityReference getIsAbstract() {
		return isAbstract;
	}
	
	public void setIsAbstract(IsAbstractIdentityReference isAbstract) {
		this.isAbstract = isAbstract;
	}

	public List<Vertex> getVertexList() {
		return vertexList;
	}
	
	public void addVertex(Vertex vertex) {
		vertexList.add(vertex);
	}
	
	public List<Edge> getEdgeList() {
		return edgeList;
	}
	
	public void addEdge(Edge edge) {
		edgeList.add(edge);
	}
	
	public List<Visibility> getVisibilityList() {
		return visibilityList;
	}

	public void addVisibility(Visibility visibility) {
		visibilityList.add(visibility);
	}
	
	public List<SuperSetReference> getSuperSetReferenceList() {
		return superSetReferenceList;
	}

	public void addSuperSetReference(SuperSetReference superSetReference) {
		superSetReferenceList.add(superSetReference);
	}
	
	public List<Command> getCommandList() {
		return commandList;
	}

	public void addCommand(Command command) {
		commandList.add(command);
	}
	
	public List<Query> getQueryList() {
		return queryList;
	}

	public void addQuery(Query query) {
		queryList.add(query);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Iterable<? extends Node> getAdditionalChildren() {
		return Iterables.concat(
			ImmutableList.of(container, isAbstract),
			vertexList,
			visibilityList,
			edgeList,
			superSetReferenceList,
			commandList,
			queryList
		);
	}
}
