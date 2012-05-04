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

import org.s23m.cell.communication.xml.dom.BasicCompositeNode;
import org.s23m.cell.communication.xml.dom.Namespace;

/*
	<!-- minOccurs="0" maxOccurs="unbounded" -->
	<s23m:edge>
		<s23m:semanticIdentity>
			<s23m:uniqueRepresentationReference>23</s23m:uniqueRepresentationReference>
			<s23m:identifier>24</s23m:identifier>
		</s23m:semanticIdentity>
		<s23m:category>
			<s23m:uniqueRepresentationReference>25</s23m:uniqueRepresentationReference>
			<s23m:identifier>26</s23m:identifier>
		</s23m:category>	
		
		<s23m:isAbstract>
			<s23m:uniqueRepresentationReference>27</s23m:uniqueRepresentationReference>
			<s23m:identifier>28</s23m:identifier>
		</s23m:isAbstract>
		<s23m:from>
			<s23m:semanticIdentity>
				<s23m:uniqueRepresentationReference>23</s23m:uniqueRepresentationReference>
				<s23m:identifier>24</s23m:identifier>
			</s23m:semanticIdentity>
			<s23m:category>
				<s23m:uniqueRepresentationReference>25</s23m:uniqueRepresentationReference>
				<s23m:identifier>26</s23m:identifier>
			</s23m:category>
			
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
		</s23m:from>
		<s23m:to>
			<s23m:semanticIdentity>
				<s23m:uniqueRepresentationReference>23</s23m:uniqueRepresentationReference>
				<s23m:identifier>24</s23m:identifier>
			</s23m:semanticIdentity>
			<s23m:category>
				<s23m:uniqueRepresentationReference>25</s23m:uniqueRepresentationReference>
				<s23m:identifier>26</s23m:identifier>
			</s23m:category>			
		
			<s23m:isAbstract>
				<s23m:uniqueRepresentationReference>39</s23m:uniqueRepresentationReference>
				<s23m:identifier>40</s23m:identifier>
			</s23m:isAbstract>
			<s23m:minCardinality>
				<s23m:uniqueRepresentationReference>41</s23m:uniqueRepresentationReference>
				<s23m:identifier>42</s23m:identifier>
			</s23m:minCardinality>
			<s23m:maxCardinality>
				<s23m:uniqueRepresentationReference>43</s23m:uniqueRepresentationReference>
				<s23m:identifier>44</s23m:identifier>
			</s23m:maxCardinality>
			<s23m:isContainer>
				<s23m:uniqueRepresentationReference>45</s23m:uniqueRepresentationReference>
				<s23m:identifier>46</s23m:identifier>
			</s23m:isContainer>
			<s23m:isNavigable>
				<s23m:uniqueRepresentationReference>47</s23m:uniqueRepresentationReference>
				<s23m:identifier>48</s23m:identifier>
			</s23m:isNavigable>
		</s23m:to>				
	</s23m:edge>
 */
public class Edge extends BasicCompositeNode {

	public Edge(Namespace namespace, String name) {
		super(namespace, name);
	}

}
