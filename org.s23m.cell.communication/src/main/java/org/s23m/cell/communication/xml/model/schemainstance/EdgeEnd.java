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

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.dom.Node;

import com.google.common.collect.ImmutableList;

public class EdgeEnd extends Category {
	
	private IsAbstractIdentityReference isAbstract;
	
	private MinimumCardinalityIdentityReference minCardinality;
	
	private MaximumCardinalityIdentityReference maxCardinality;
	
	private IsContainerIdentityReference isContainer;
	
	private IsNavigableIdentityReference isNavigable;

	public static EdgeEnd fromEdgeEnd(Namespace namespace, XmlSchemaTerminology terminology) {
		return new EdgeEnd(namespace, terminology.from());
	}
	
	public static EdgeEnd toEdgeEnd(Namespace namespace, XmlSchemaTerminology terminology) {
		return new EdgeEnd(namespace, terminology.to());
	}
	
	private EdgeEnd(Namespace namespace, String name) {
		super(namespace, name);
	}

	public IsAbstractIdentityReference getIsAbstract() {
		return isAbstract;
	}
	
	public void setIsAbstract(IsAbstractIdentityReference isAbstract) {
		this.isAbstract = isAbstract;
	}

	public MinimumCardinalityIdentityReference getMinCardinality() {
		return minCardinality;
	}
	
	public void setMinCardinality(MinimumCardinalityIdentityReference minCardinality) {
		this.minCardinality = minCardinality;
	}

	public MaximumCardinalityIdentityReference getMaxCardinality() {
		return maxCardinality;
	}

	public void setMaxCardinality(MaximumCardinalityIdentityReference maxCardinality) {
		this.maxCardinality = maxCardinality;
	}

	public IsContainerIdentityReference getIsContainer() {
		return isContainer;
	}

	public void setIsContainer(IsContainerIdentityReference isContainer) {
		this.isContainer = isContainer;
	}

	public IsNavigableIdentityReference getIsNavigable() {
		return isNavigable;
	}

	public void setIsNavigable(IsNavigableIdentityReference isNavigable) {
		this.isNavigable = isNavigable;
	}

	@Override
	protected Iterable<? extends Node> getAdditionalChildren() {
		return ImmutableList.of(
			isAbstract,
			minCardinality,
			maxCardinality,
			isContainer,
			isNavigable
		);
	}
}
