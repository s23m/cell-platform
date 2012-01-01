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
 * Copyright (C) 2009-2012 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Jorn Bettin
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.s23m.cell.G;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.GmodelSemanticDomains;
import org.s23m.cell.serialization.EdgeType;
import org.s23m.cell.serialization.EdgeType.EdgeEnd;
import org.s23m.cell.serialization.Gmodel;
import org.s23m.cell.serialization.InstanceType;
import org.s23m.cell.serialization.LinkType;
import org.s23m.cell.serialization.ObjectFactory;
import org.s23m.cell.serialization.SemanticIdType;
import org.s23m.cell.serialization.SemanticIdentityIndex;

public final class FileIndexer {

	private static Map<String, Set> kernelElements;
	public static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssz";

	/**
	 * Return a index map of all the model instances at a given URI
	 * @param uri URI a serialization location
	 * @return Map<String, URI> index of Intance name and its URI
	 */
	public static Map<String, URI> getFileIndexAt(final URI uri) {
		final File dir = new File(uri.getPath());
		final Map<String, URI> indexMap = new HashMap<String, URI>();

		if (dir.isDirectory()) {
			for (final File file : dir.listFiles()) {
				//TODO need to do more than checking the file extension
				if (file.getName().endsWith(Serializer.FILE_EXTENSION)) {
					final URI uriF = file.toURI();
					createFileIndex(file.toURI(), indexMap);
				}
			}
		} else {
			throw new IllegalArgumentException("Non directory path");
		}
		return indexMap;
	}

	public static List<SemanticIdType> getSemanticIdIndex (final Set instance) {
		final ObjectFactory objFacto = new ObjectFactory();
		final List<SemanticIdType> ids = new ArrayList<SemanticIdType>();
		indexInstance(instance, ids, objFacto, false);
		return ids;
	}

	public static List<SemanticIdentityIndex> getSemanticIdIndexes (final Set instance) {
		final List<SemanticIdentityIndex> ids = new ArrayList<SemanticIdentityIndex>();
		indexInstance(instance, ids, false);
		return ids;
	}

	private static void indexInstance (final Set instance, final List<SemanticIdentityIndex> ids, final boolean isSession) {
		ids.add(buildSemanticIdForIndex(instance));
		for (final Set i : instance.filterInstances()) {
			if (i.flavor().isEqualTo(G.coreGraphs.vertex) && !isSession) {
				indexInstance(i, ids, isSession);
			} else if (i.flavor().isEqualTo(G.coreGraphs.edge)) {
				final Set edge = i;
				ids.add(buildSemanticIdForIndex(i));
				for (final Set j : edge.edgeEnds()) {
					ids.add(buildSemanticIdForIndex(j));
				}
			}
		}
	}

	private static SemanticIdentityIndex buildSemanticIdForIndex(final Set instance) {
		final SemanticIdentityIndex idx = new SemanticIdentityIndex();
		final String metaTypeName = (instance.flavor().isEqualTo(G.coreGraphs.vertex)) ? G.coreGraphs.vertex.identity().name(): G.coreGraphs.edge.identity().name();
		idx.setIdentifier(instance.identity().identifier().toString());
		idx.setName(instance.identity().name());
		idx.setPluralName(instance.identity().pluralName());
		idx.setUniqueRepresentationReference(instance.identity().uniqueRepresentationReference().toString());
		idx.setMetaElementId(instance.category().identity().uniqueRepresentationReference().toString());
		idx.setMetaElementTypeName(metaTypeName);
		return idx;
	}

	public static List<SemanticIdType> getSessionSemanticIdIndex (final Set session) {
		final ObjectFactory objFacto = new ObjectFactory();
		final List<SemanticIdType> ids = new ArrayList<SemanticIdType>();
		indexInstance(session, ids, objFacto, true);
		return ids;
	}

	public static List<SemanticIdentityIndex> getSessionSemanticIdIndexes (final Set session) {
		final List<SemanticIdentityIndex> ids = new ArrayList<SemanticIdentityIndex>();
		indexInstance(session, ids, true);
		return ids;
	}

	private static void indexInstance (final Set instance, final List<SemanticIdType> ids, final ObjectFactory objFactory, final boolean isSession) {
		ids.add(buildSemanticIdForIndex(objFactory, instance));
		for (final Set i : instance.filterInstances()) {
			if (i.flavor().isEqualTo(G.coreGraphs.vertex) && !isSession) {
				indexInstance(i, ids, objFactory, isSession);
			} else if (i.flavor().isEqualTo(G.coreGraphs.edge)) {
				final Set edge = i;
				ids.add(buildSemanticIdForIndex(objFactory, i));
				for (final Set j : edge.edgeEnds()) {
					ids.add(buildSemanticIdForIndex(objFactory, j));
				}
			}
		}
	}

	private static SemanticIdType buildSemanticIdForIndex(final ObjectFactory objFactory,	final Set instance) {
		final SemanticIdType id = objFactory.createSemanticIdType();
		id.setIdentifier(instance.identity().identifier().toString());
		id.setName(instance.identity().name());
		id.setPluralName(instance.identity().pluralName());
		id.setUniqueRepresentationReference(instance.identity().uniqueRepresentationReference().toString());
		return id;
	}

	/**
	 * Return semenctic identity names of all non-local instances that is found in a given instance
	 * 
	 * @param uri URI of an instance
	 * @param modelRegistry Map of instance id names and their URIs
	 * @return List<String> List of all non-local semantic Ids
	 */
	protected static List<String> getAllNonLocalInstances(final URI uri, final Map<String, URI> modelRegistry) {
		final List<String> instances = new ArrayList<String>();
		//if this model's root's container is graph then return the empty list
		try {
			final Gmodel.Instance root = getGmodelRootInstance(uri);
			if (!root.getArtifact().equals(G.coreGraphs.graph.identity().name())) {
				if (!isLocalInstance(root.getArtifact(), uri, modelRegistry)) {
					if (!instances.contains(root.getArtifact())) {
						instances.add(root.getArtifact());
					}
				}
				if (!isLocalInstance(root.getMetaElement(), uri, modelRegistry)) {
					if (!instances.contains(root.getMetaElement())) {
						instances.add(root.getMetaElement());
					}
				}
				indexNonLocalLinkType(root.getLink(), uri, instances,modelRegistry);
				for (final InstanceType instance : root.getInstance()) {
					indexNonLocalInstance(instance, uri, instances, modelRegistry);
				}
			}
		}  catch (final JAXBException ex) {
			throw new IllegalStateException("Deserialization failed.", ex);
		} catch (final FileNotFoundException ex) {
			throw new IllegalArgumentException("Invalid uri path.");
		}
		return instances;
	}

	private static void indexNonLocalInstance(final InstanceType instance, final URI uri,
			final List<String> nonLocalInstances, final Map<String, URI> modelRegistry) {
		indexNonLocalLinkType(instance.getLink(), uri, nonLocalInstances, modelRegistry);
		if (!isLocalInstance(instance.getArtifact(), uri, modelRegistry)) {
			if (!nonLocalInstances.contains(instance.getArtifact())) {
				nonLocalInstances.add(instance.getArtifact());
			}
		}
		if (!isLocalInstance(instance.getMetaElement(), uri, modelRegistry)) {
			if (!nonLocalInstances.contains(instance.getMetaElement())) {
				nonLocalInstances.add(instance.getMetaElement());
			}
		}
		for (final InstanceType i : instance.getInstance()) {
			indexNonLocalInstance(i, uri, nonLocalInstances, modelRegistry);
		}
	}

	private static void indexNonLocalLinkType(final LinkType link, final URI uri,
			final List<String> nonLocalInstances, final Map<String, URI> modelRegistry) {
		if (link != null) {
			for (final Object obj : link.getVisibilityAndEdgeAndEdgeTrace()) {
				if (obj instanceof EdgeType) {
					final EdgeType edge = (EdgeType) obj;
					for (final EdgeEnd ee : edge.getEdgeEnd()) {
						if (!isLocalInstance(ee.getMetaElement(), uri, modelRegistry)) {
							if (!nonLocalInstances.contains(ee.getMetaElement())) {
								nonLocalInstances.add(ee.getMetaElement());
							}
						}
					}
				}
			}
		}
	}

	private static boolean isLocalInstance (final String name, final URI currentURI, final Map<String, URI> modelRegistry) {
		//not kernel elements
		if (!isKernelElement(name)) {
			return currentURI.equals(modelRegistry.get(name));
		} else {
			return true;
		}
	}

	public static boolean isKernelElement(final String name) {
		if (name.equals(G.coreGraphs.graph.identity().name()) ||
				name.equals(G.coreGraphs.vertex.identity().name()) ||
				name.equals(G.coreGraphs.edge.identity().name()) ||
				name.equals(G.coreGraphs.edgeEnd.identity().name()) ||
				name.equals(GmodelSemanticDomains.anonymous.identity().name())) {
			return true;
		} else {
			return false;
		}
	}


	private static void createFileIndex(final URI uri, final Map<String, URI> indexMap) {
		try {
			final Gmodel.Instance root = getGmodelRootInstance(uri);
			indexMap.put(root.getId(), uri);
			indexLinkType(root.getLink(), uri, indexMap);
			for (final InstanceType instance : root.getInstance()) {
				indexInstance(instance, uri, indexMap);
			}
		} catch (final JAXBException ex) {
			java.util.logging.Logger.getLogger("global").log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (final FileNotFoundException ex) {
			java.util.logging.Logger.getLogger("global").log(
					java.util.logging.Level.SEVERE, null, ex);
		}
	}

	private static Gmodel.Instance getGmodelRootInstance(final URI uri)
	throws JAXBException, FileNotFoundException {
		final JAXBContext jc = JAXBContext.newInstance(Gmodel.class.getPackage().getName());
		final Unmarshaller unMarsahller = jc.createUnmarshaller();
		final Gmodel gmodel = (Gmodel) unMarsahller
		.unmarshal(new FileInputStream(uri.getPath()));
		final Gmodel.Instance root = gmodel.getInstance().get(0); // get its root
		return root;
	}

	private static void indexInstance(final InstanceType instance,
			final URI uri, final Map<String, URI> indexMap) {
		indexMap.put(instance.getId(), uri);
		indexLinkType(instance.getLink(), uri, indexMap);
		for (final InstanceType i : instance.getInstance()) {
			indexInstance(i, uri, indexMap);
		}
	}

	private static void indexInstance(final InstanceType instance,	final String ownerId, final Map<String, String> indexMap) {
		indexMap.put(instance.getId(), ownerId);
		indexLinkType(instance.getLink(), ownerId, indexMap);
		for (final InstanceType i : instance.getInstance()) {
			indexInstance(i, ownerId, indexMap);
		}
	}

	private static void indexLinkType(final LinkType link, final URI uri,
			final Map<String, URI> indexMap) {
		if (link != null) {
			for (final Object obj : link.getVisibilityAndEdgeAndEdgeTrace()) {
				if (obj instanceof EdgeType) {
					final EdgeType edge = (EdgeType) obj;
					indexMap.put(edge.getSemanticIdentity().getUniqueRepresentationReference().toString(), uri);
					for (final EdgeEnd ee : edge.getEdgeEnd()) {
						indexMap.put(ee.getSemanticIdentity().getUniqueRepresentationReference(), uri);
					}
				}
			}
		}
	}

	private static void indexLinkType(final LinkType link, final String ownerId, final Map<String, String> indexMap) {
		if (link != null) {
			for (final Object obj : link.getVisibilityAndEdgeAndEdgeTrace()) {
				if (obj instanceof EdgeType) {
					final EdgeType edge = (EdgeType) obj;
					indexMap.put(edge.getSemanticIdentity().getUniqueRepresentationReference().toString(), ownerId);
					for (final EdgeEnd ee : edge.getEdgeEnd()) {
						indexMap.put(ee.getSemanticIdentity().getUniqueRepresentationReference(), ownerId);
					}
				}
			}
		}
	}

	public static void getSemanticIdIndex(final Gmodel.Instance root, final Map<String, String> documentMap) {
		final String ownerDocId = root.getSemanticIdentity().getUniqueRepresentationReference();
		documentMap.put(ownerDocId, ownerDocId);
		indexLinkType(root.getLink(), ownerDocId, documentMap);
		for (final InstanceType instance : root.getInstance()) {
			indexInstance(instance, ownerDocId, documentMap);
		}
	}

	public static void getRootSemanticIdIndex(final Gmodel.Instance root, final Map<String, String> documentMap) {
		final String ownerDocId = root.getSemanticIdentity().getUniqueRepresentationReference();
		documentMap.put(ownerDocId, ownerDocId);
		indexLinkType(root.getLink(), ownerDocId, documentMap);
	}

	public static String getCurrentTimeStamp() {
		final SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT);
		final TimeZone tz = Calendar.getInstance().getTimeZone();
		df.setTimeZone(tz);
		return df.format(new Date());
	}

}
