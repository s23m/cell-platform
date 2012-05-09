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

import java.util.List;

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Graph extends Category {
		
	private ContainerIdentityReference container;
	
	private IsAbstractIdentityReference isAbstract;
	
	private final List<Vertex> vertexList;
	
	private final List<Edge> edgeList;
	
	private final List<Command> commandList;
	
	private final List<Query> queryList;

	public Graph(Namespace namespace, XmlSchemaTerminology terminology) {
		this(namespace, terminology.graph());
	}

	protected Graph(Namespace namespace, String name) {
		super(namespace, name);
		this.vertexList = Lists.newArrayList();
		this.edgeList = Lists.newArrayList();
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
	protected Iterable<? extends Node> getAdditionalChildren() {
		final Iterable<? extends Node> scalarValues = ImmutableList.of(container, isAbstract);
		final Iterable<? extends Node> listValues = Iterables.concat(
				vertexList,
				edgeList,
				commandList,
				queryList
		);
		return Iterables.concat(scalarValues, listValues);
	}
}
