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
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2010 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.s23m.cell.serialization.container.ArtefactContainer.Content;
import org.s23m.cell.serialization.container.ArtefactContainer.SearchResult;
import org.s23m.cell.serialization.serializer.SerializationType;

public class ContainerTypeMapper {

	private static ArtefactContainer createArtefactContainer(final SerializationType type) {
		final ObjectFactory facto = ObjectFactoryHolder.getInstance();
		final ArtefactContainer artifactContainer = facto.createArtefactContainer();
		artifactContainer.setContentType(type.name());
		return artifactContainer;
	}

	public static ArtefactContainer mapArugmentListToArtefactContainerContent(final List<String> argList, final SerializationType type) {
		final ArtefactContainer artifactContainer = createArtefactContainer(type);
		for (final String arg : argList) {
			final Content content = ObjectFactoryHolder.getInstance().createArtefactContainerContent();
			content.setContent(arg);
			artifactContainer.getContent().add(content);
		}
		return artifactContainer;
	}

	public static ArtefactContainer mapArugmentToArtefactContainerContent(final String arg, final SerializationType type) {
		final ArtefactContainer artifactContainer = createArtefactContainer(type);
		final Content content = ObjectFactoryHolder.getInstance().createArtefactContainerContent();
		content.setContent(arg);
		artifactContainer.getContent().add(content);
		return artifactContainer;
	}

	public static List<String> mapContentsToStringList(final ArtefactContainer container) {
		final List<String> lst = new ArrayList<String>();
		for (final Content c : container.getContent()) {
			lst.add(c.getContent());
		}
		return lst;
	}

	public static List<String> mapContentToStringList(final ArtefactContainer container) {
		final List<String> contentList = new ArrayList<String>();
		for(final Content c : container.getContent()) {
			contentList.add(c.getContent());
		}
		return contentList;
	}

	public static List<SearchResultType> mapSearchResultListToSearchResultTypeList(final List<SearchResult> results) {
		final List<SearchResultType> mappedSearchList = new ArrayList<SearchResultType>();
		for (final SearchResult r : results) {
			final SearchResultType mappedResult = ObjectFactoryHolder.getInstance().createSearchResultType();
			mappedResult.setContainerIdentity(r.getContainerIdentity());
			mappedResult.setInstanceIdentity(r.getInstanceIdentity());
			mappedResult.setMetaInstanceIdentity(r.getMetaInstanceIdentity());
			mappedSearchList.add(mappedResult);
		}
		return mappedSearchList;
	}

	public static List<SearchResult> mapSearchResultTypeListToSearchResultList(final List<SearchResultType> results) {
		final List<SearchResult> mappedList = new ArrayList<SearchResult>();
		for (final SearchResultType r : results) {
			final SearchResult searchRes = ObjectFactoryHolder.getInstance().createArtefactContainerSearchResult();
			searchRes.setContainerIdentity(r.getContainerIdentity());
			searchRes.setInstanceIdentity(r.getInstanceIdentity());
			searchRes.setMetaInstanceIdentity(r.getMetaInstanceIdentity());
			mappedList.add(searchRes);
		}
		return mappedList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String,String> mapToArtefactMap(final ArtefactContainer container) {
		final Map map = new ListOrderedMap();
		for (final Content c : container.getContent()) {
			map.put(c.getId(), c.getContent());
		}
		return map;
	}

	public static void mapToContentList(final ArtefactContainer container, final List<String> contentList) {
		for (final String id : contentList) {
			final Content c = ObjectFactoryHolder.getInstance().createArtefactContainerContent();
			c.setContent(id);
			container.getContent().add(c);
		}
	}

}
