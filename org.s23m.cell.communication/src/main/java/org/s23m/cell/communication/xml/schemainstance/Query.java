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
	<s23m:query>
		<s23m:semanticIdentity>
			<s23m:uniqueRepresentationReference>67</s23m:uniqueRepresentationReference>
			<s23m:identifier>68</s23m:identifier>
		</s23m:semanticIdentity>
		<s23m:category>
			<s23m:uniqueRepresentationReference>69</s23m:uniqueRepresentationReference>
			<s23m:identifier>70</s23m:identifier>
		</s23m:category>
		
		<!-- minOccurs="0" maxOccurs="unbounded" -->
		<s23m:parameter>
			<s23m:semanticIdentity>
				<s23m:uniqueRepresentationReference>71</s23m:uniqueRepresentationReference>
				<s23m:identifier>72</s23m:identifier>
			</s23m:semanticIdentity>
			<s23m:category>
				<s23m:uniqueRepresentationReference>73</s23m:uniqueRepresentationReference>
				<s23m:identifier>74</s23m:identifier>
			</s23m:category>
		</s23m:parameter>			
	</s23m:query>
 */
public class Query extends BasicCompositeNode {

	public Query(Namespace namespace, String name) {
		super(namespace, name);
	}

}
