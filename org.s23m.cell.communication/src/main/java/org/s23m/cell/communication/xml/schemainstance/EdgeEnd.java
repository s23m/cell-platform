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

import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.dom.Namespace;
import org.s23m.cell.communication.xml.dom.Node;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/*
	<s23m:[name]>
		<s23m:isAbstract>
			<s23m:uniqueRepresentationReference>29</s23m:uniqueRepresentationReference>
			<s23m:identifier>30</s23m:identifier>
		</s23m:isAbstract>
		<s23m:minCardinality>
			<s23m:uniqueRepresentationReference>31</s23m:uniqueRepresentationReference>
			<s23m:identifier>32</s23m:identifier>
		</s23m:minCardinality>
		<s23m:maxCardinality>
			<s23m:uniqueRepresentationReference>33</s23m:uniqueRepresentationReference>
			<s23m:identifier>34</s23m:identifier>
		</s23m:maxCardinality>
		<s23m:isContainer>
			<s23m:uniqueRepresentationReference>35</s23m:uniqueRepresentationReference>
			<s23m:identifier>36</s23m:identifier>
		</s23m:isContainer>
		<s23m:isNavigable>
			<s23m:uniqueRepresentationReference>37</s23m:uniqueRepresentationReference>
			<s23m:identifier>38</s23m:identifier>
		</s23m:isNavigable>
	</s23m:[name]>
 */
public class EdgeEnd extends Category {
	
	private IdentityReference isAbstract;
	
	private IdentityReference minCardinality;
	
	private IdentityReference maxCardinality;
	
	private IdentityReference isContainer;
	
	private IdentityReference isNavigable;

	public EdgeEnd(Namespace namespace, XmlSchemaTerminology terminology) {
		super(namespace, terminology.edgeEnd());
	}

	public IdentityReference getIsAbstract() {
		return isAbstract;
	}

	public void setIsAbstract(IdentityReference isAbstract) {
		this.isAbstract = isAbstract;
	}

	public IdentityReference getMinCardinality() {
		return minCardinality;
	}

	public void setMinCardinality(IdentityReference minCardinality) {
		this.minCardinality = minCardinality;
	}

	public IdentityReference getMaxCardinality() {
		return maxCardinality;
	}

	public void setMaxCardinality(IdentityReference maxCardinality) {
		this.maxCardinality = maxCardinality;
	}

	public IdentityReference getIsContainer() {
		return isContainer;
	}

	public void setIsContainer(IdentityReference isContainer) {
		this.isContainer = isContainer;
	}

	public IdentityReference getIsNavigable() {
		return isNavigable;
	}

	public void setIsNavigable(IdentityReference isNavigable) {
		this.isNavigable = isNavigable;
	}

	@Override
	public Iterable<? extends Node> getChildren() {
		final Iterable<? extends Node> categoryChildren = super.getChildren();
		final Iterable<? extends Node> scalarValues = ImmutableList.of(
				isAbstract,
				minCardinality,
				maxCardinality,
				isContainer,
				isNavigable
		);
		return Iterables.concat(categoryChildren, scalarValues);
	}
}
