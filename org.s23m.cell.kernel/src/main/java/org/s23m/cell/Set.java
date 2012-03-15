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
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell;

import org.s23m.cell.properclasses.ProperClassOfOrderedPairs;
import org.s23m.cell.properclasses.ProperClassOfOrderedSets;


/**
 * <p>The S23M {@link Set} interface enables the attachment of new artifacts and container content
 * to the S23M repository. An {@link Set} has the role of {@link Graph}, which includes the role
 * of a list ({@link ProperClassOfOrderedSets}).</p>
 * 
 * <p><b>IMPORTANT NOTE: S23M does not make use of the Java typing mechanism</b></p>
 * 
 * <p>In S23M every element is part of the following technical Java interface hierarchy:
 * 
 * <ul>
 * <li>{@link ProperClassOfOrderedPairs} &lt;-- {@link Artifact} &lt;-- {@link Set}</li>
 * </ul>
 * 
 * <p>The user can plug in an external SemanticIdentity mechanism as needed
 * The classification mechanism used in S23M is the <i>isInformation</i>,
 * which is a recursively applicable mechanism that enables multi-level modelling</p>
 * 
 * <p>In S23M the only things that a user creates are:
 * 
 * <ol>
 * <li>{@link Set}s, which require an externally provided {@link Identity}</li>
 * <li>Links between {@link Set}s, which are also {@link Set}s</li>
 * <li>{@link Variables} and {@link Values}, are {@link Set}s that reference {@link Set}s
 * 		that lie beyond the container boundary, and which are not considered
 * 		to be first-class citizens from the
 *		<i>view point</i> of the container that is being modelled</li>
 * </ol>
 * </p>
 */
public interface Set extends ProperClassOfOrderedPairs {


}
