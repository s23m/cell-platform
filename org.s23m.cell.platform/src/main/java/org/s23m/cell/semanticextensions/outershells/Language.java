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
 * The Original Code is Gmodel.
 *
 * The Initial Developer of the Original Code is
 * SoftMetaWare Ltd. (SoftMetaWare).
 * Portions created by the Initial Developer are
 * Copyright (C) 2011 SoftMetaWare Ltd.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.semanticextensions.outershells;

import static org.s23m.cell.G.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.Root;


public class Language {

	public static final Set language = Root.universalartifactengineering.addConcrete(SemanticEnterprise.what, SemanticExtensionsDomain.language);
	public static final Set languageElement = language.addAbstract(SemanticEnterprise.what, SemanticExtensionsDomain.languageElement);
	public static final Set abstractWord = language.addAbstract(SemanticEnterprise.what, SemanticExtensionsDomain.abstractWord);
	public static final Set wordSeparator = language.addAbstract(SemanticEnterprise.what, SemanticExtensionsDomain.wordSeparator);
	public static final Set word = language.addConcrete(SemanticEnterprise.what, SemanticExtensionsDomain.word);
	public static final Set whiteSpaceElement = language.addConcrete(SemanticEnterprise.what, SemanticExtensionsDomain.whiteSpaceElement);
	public static final Set sentenceSeparator = language.addConcrete(SemanticEnterprise.what, SemanticExtensionsDomain.sentenceSeparator);

	public static Set instantiateFeature() {

			Instantiation.link(coreGraphs.superSetReference, language, SemanticEnterprise.what);
			Instantiation.link(coreGraphs.superSetReference, languageElement, SemanticEnterprise.what);
			Instantiation.link(coreGraphs.superSetReference, abstractWord, languageElement);
			Instantiation.link(coreGraphs.superSetReference, wordSeparator, languageElement);
			Instantiation.link(coreGraphs.superSetReference, word, abstractWord);
			Instantiation.link(coreGraphs.superSetReference, whiteSpaceElement, wordSeparator);
			Instantiation.link(coreGraphs.superSetReference, sentenceSeparator, wordSeparator);

		return language;
	}

}
