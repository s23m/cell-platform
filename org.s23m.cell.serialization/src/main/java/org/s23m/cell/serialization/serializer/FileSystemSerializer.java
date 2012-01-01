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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.naming.OperationNotSupportedException;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.binary.Base64;
import org.s23m.cell.G;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.Root;
import org.s23m.cell.serialization.Gmodel;
import org.s23m.cell.serialization.SemanticIdType;
import org.s23m.cell.serialization.container.ArtefactContainer;

public class FileSystemSerializer implements Serializer {

	public FileSystemSerializer() {
		initGmodelMarshaller();
	}

	public byte[] compressSerializationContent(final String content) throws IOException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final BufferedOutputStream bufOutputStream = new BufferedOutputStream(new GZIPOutputStream(outputStream));
		bufOutputStream.write(content.getBytes(CONTENT_ENCODING));
		bufOutputStream.close();
		return outputStream.toByteArray();
	}

	public String compressSerializationContentBase64(final String content)throws IOException {
		final byte[] contentByte = compressSerializationContent(content);
		return Base64.encodeBase64URLSafeString(contentByte);
	}

	public Marshaller createMarshaller(final String packageName) throws JAXBException {
		return SerializationMarshaller.getMarshaller(packageName);
	}

	public byte[] decodeBase64StringToByteArray(final String content) {
		return Base64.decodeBase64(content);
	}

	public String decompressSerializationContent(final byte[] compressedContent) throws IOException {
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedContent);
		final BufferedInputStream bufInputStream = new BufferedInputStream(new GZIPInputStream(inputStream));
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String decompressedContent;
		final int bufferSize = 1024;
		final byte[] buf = new byte[bufferSize];
		int len;
		while( (len = bufInputStream.read(buf)) > 0 ) {
			outputStream.write(buf, 0, len);
		}
		decompressedContent = outputStream.toString(CONTENT_ENCODING);
		inputStream.close();
		bufInputStream.close();
		outputStream.close();
		return decompressedContent;
	}

	public String decompressSerializationContentBase64(final String compressedContent) throws IOException {
		final byte[] decodedByte = Base64.decodeBase64(compressedContent);
		return decompressSerializationContent(decodedByte);
	}

	public void deserializeInstances(final Map<String, Gmodel> artifacts) {
		final DeserializationMapper deSerializationMapper = new DeserializationMapper();
		deSerializationMapper.deserializeInstances(artifacts);
	}

	public void doInitialFullDeserialization(final Map<String,Gmodel> artifacts) throws IllegalArgumentException, IllegalAccessException {
		final DeserializationMapper deSerializationMapper = new DeserializationMapper();
		try {
			deSerializationMapper.deserializeSemanticDomains(artifacts);
			deSerializationMapper.deserializeRootModels(artifacts);
		} catch (final OperationNotSupportedException ex) {
			throw new IllegalArgumentException("The given artifacts cannot be deserialized.",ex);
		}
	}

	public String encodeByteArrayInBase64(final byte[] binaryContent) {
		return Base64.encodeBase64String(binaryContent);
	}

	private void getAllChildVerticesInMemory(final Set parentInstance, final List<Set> allInstancesInMemory) {
		if (parentInstance.flavor().isEqualTo(G.coreGraphs.vertex)) {
			allInstancesInMemory.add(parentInstance);
		}
		for (final Set i : parentInstance.filterInstances()) {
			//if (i.flavor().isEqualTo(F_SemanticStateOfInMemoryModel.coreGraphs.vertex) && !i.identity().isEqualTo(Root.transportcontainer.identity())) {
			if (i.flavor().isEqualTo(G.coreGraphs.vertex)) {

				getAllChildVerticesInMemory(i, allInstancesInMemory);
			}
		}
	}

	private void getAllIdentities(final Set instance, final List<Gmodel> identities) {
		final Gmodel serializedModel = InstanceBuilder.getObjectFactory().createGmodel();
		final Gmodel.Instance serializedInstance = InstanceBuilder.getObjectFactory().createGmodelInstance();
		final SemanticIdType sId = InstanceBuilder.mapSemanticIdentity(instance.identity());
		serializedInstance.setSemanticIdentity(sId);
		serializedModel.getInstance().add(serializedInstance);
		identities.add(serializedModel);
		for (final Set i : instance.filterInstances()) {
			getAllIdentities(i, identities);
		}
	}

	/*
	private Set getTransportContainerContent() {
		final Set containedSet = null;
		for (final Set set : Root.transportcontainer.instanceSet()) {
			if (!set.isEqualToRepresentation(Root.transmissionTimestamp)) {
		containedSet = set;
			}
		}
		return containedSet;
	}
	 */

	private void initGmodelMarshaller() {
		try {
			SerializationMarshaller.getMarshaller(Gmodel.class.getPackage().getName());
			SerializationMarshaller.getUnmarshaller(Gmodel.class.getPackage().getName());
			SerializationMarshaller.getMarshaller(ArtefactContainer.class.getPackage().getName());
			SerializationMarshaller.getUnmarshaller(ArtefactContainer.class.getPackage().getName());
		} catch (final JAXBException ex) {
			java.util.logging.Logger.getLogger("global").log(
					java.util.logging.Level.SEVERE, null, ex);		}
	}

	public <T> String marshallModel(final T model) throws JAXBException {
		final Marshaller marshaller = createMarshaller(model.getClass().getPackage().getName());
		final Writer sw = new StringWriter();
		marshaller.marshal(model, sw);
		return sw.toString();
	}

	public List<String> serializeAllInstancesInMemory() {
		final List<String> serializedInstances = new ArrayList<String>();
		final List<Set> allInstancesInMemory = new ArrayList<Set>();
		final Set rootSet = G.coreGraphs.graph;//(Vertex) Root.root;
		getAllChildVerticesInMemory(rootSet, allInstancesInMemory);
		//for all instances memory sort them by urr and write to a file
		Collections.sort(allInstancesInMemory, new InstanceComparator());
		final StringBuffer sBuff = new StringBuffer();
		for (final Set v : allInstancesInMemory) {
			if (!v.identity().name().contains("this set is not available in memory")) {
				sBuff.append(v.identity().uniqueRepresentationReference()+","+v.identity().name()+"\r\n");
			}
		}
		//dumpContent(""+UUID.randomUUID(),sBuff.toString());

		for (final Set v : allInstancesInMemory) {
			serializedInstances.add(serializeInstance(v, true).getContent());
		}
		return serializedInstances;
	}

	public String serializeContainer(final ArtefactContainer container) {
		final Writer sw = new StringWriter();
		try {
			final Marshaller marshaller = createMarshaller(container.getClass().getPackage().getName());
			marshaller.marshal(container, sw);
			final String content = sw.toString(); //dumpContent(gmodel.getId(), content);	//do content dump
			return content;
		} catch (final JAXBException ex) {
			// TODO Auto-generated catch block
			throw new IllegalStateException ("Unmarshalling failed.", ex);
		}
	}

	public List<Gmodel> serializeIdentities(final Set instance) {
		final List<Gmodel> ids = new ArrayList<Gmodel>();
		getAllIdentities(instance, ids);
		Marshaller marshaller;
		try {
			marshaller = ids.isEmpty() ? null : createMarshaller(ids.get(0).getClass().getPackage().getName());
			for (final Gmodel id : ids) {
				final Writer sw = new StringWriter();
				marshaller.marshal(id, sw);
			}
			return ids;
		} catch (final JAXBException ex) {
			throw new IllegalStateException ("Marshalling failed.", ex);
		}
	}

	/* Serialize the given vertex
	 * @return SerializationContent
	 */
	public SerializationContent serializeInstance(final Set vertex, final boolean isKernelSerialization) {
		try {
			final GmodelContent gmodel = new SerializationMapper().mapInstance(vertex, isKernelSerialization);
			final Writer sw = new StringWriter();
			final Marshaller marshaller = createMarshaller(gmodel.getContent().getClass().getPackage().getName());
			marshaller.marshal(gmodel.getContent(), sw);
			final String content = sw.toString(); //dumpContent(gmodel.getId(), content);	//do content dump
			return new SerializationContent(gmodel.getId(), content, gmodel.getContent(), gmodel.getSemanticIds());
		} catch (final JAXBException ex) {
			throw new IllegalStateException("Serialization failture.");
		}
	}

	/* Serialize Kernel Session to a list of SerializationContent
	 * @return String
	 */
	public List<SerializationContent> serializeRoot() {
		final List<GmodelContent> gmodels = new SerializationMapper().mapRoot(Root.root);
		final List<SerializationContent> serializedGmodels = new ArrayList<SerializationContent>();
		try {
			final Marshaller marshaller = gmodels.isEmpty() ? null :createMarshaller(gmodels.get(0).getContent().getClass().getPackage().getName());
			for(final GmodelContent gmodel : gmodels) {
				final Writer sw = new StringWriter();
				marshaller.marshal(gmodel.getContent(), sw);
				final String content = sw.toString();
				//do content dump
				//dumpContent(gmodel.getId(), content);
				serializedGmodels.add(new SerializationContent(gmodel.getId(), content, gmodel.getContent(), gmodel.getSemanticIds()));
			}
		} catch (final JAXBException ex) {
			java.util.logging.Logger.getLogger("global").log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		return serializedGmodels;
	}

	/* public List<SerializationContent> serializeTransportContainerContent() {
		final List<SerializationContent> serializedGmodels = new ArrayList<SerializationContent>();
		final Set containedSet = getTransportContainerContent();
		if (containedSet != null) {
			if (containedSet.isEqualToRepresentation(Root.root)) {
				serializedGmodels.addAll(serializeRoot());
			} else {
				final List<GmodelContent> gmodels = new SerializationMapper().mapRoot(containedSet);
				for(final GmodelContent gmodel : gmodels) {
					try {
						final Writer sw = new StringWriter();
						final Marshaller marshaller = createMarshaller(gmodel.getContent().getClass().getPackage().getName());
						marshaller.marshal(gmodel.getContent(), sw);
						final String content = sw.toString();
						serializedGmodels.add(new SerializationContent(gmodel.getId(), content, gmodel.getContent(), gmodel.getSemanticIds()));
					} catch (final JAXBException ex) {
						java.util.logging.Logger.getLogger("global").log(
								java.util.logging.Level.SEVERE, null, ex);
					}
				}
			}
		}
		return serializedGmodels;
	}
	 */

	private Object unMarshall(final String modelClass, final String xmlString) throws JAXBException {
		final ByteArrayInputStream input = new ByteArrayInputStream (xmlString.getBytes());
		final Unmarshaller unMarsahller = SerializationMarshaller.getUnmarshaller(modelClass);
		return unMarsahller.unmarshal(input);
	}

	public ArtefactContainer unmarshallContainer (final String xmlString) {
		try {
			return (ArtefactContainer) unMarshall(ArtefactContainer.class.getPackage().getName(), xmlString);
		} catch (final JAXBException ex) {
			throw new IllegalStateException ("Unmarshalling failed.", ex);
		}
	}

	public Gmodel unmarshallModel (final String xmlString) {
		try {
			return (Gmodel) unMarshall(Gmodel.class.getPackage().getName(), xmlString);
		} catch (final JAXBException ex) {
			throw new IllegalStateException ("Unmarshalling failed.", ex);
		}
	}

	public Gmodel unmarshallModel (final URI uri) {
		try {
			final Unmarshaller unMarsahller = SerializationMarshaller.getUnmarshaller(Gmodel.class.getPackage().getName());
			return (Gmodel) unMarsahller.unmarshal( new FileInputStream(uri.getPath()));
		} catch (final JAXBException ex) {
			throw new IllegalStateException ("Unmarshalling failed.", ex);
		} catch (final FileNotFoundException ex) {
			throw new IllegalArgumentException("Invalid URI path.");
		}
	}

}
