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
 * Chul Kim
 * ***** END LICENSE BLOCK ***** */

package org.s23m.cell.serialization.serializer;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.s23m.cell.Set;
import org.s23m.cell.serialization.S23M;
import org.s23m.cell.serialization.container.ArtefactContainer;

/**
 * @author home
 *
 */
public interface Serializer {

	public static final String FILE_EXTENSION = ".xml";

	public static final String CONTENT_ENCODING = "ISO-8859-1";


	/**
	 * GZIPping a given String (utf-8)
	 * @param String content
	 * @return Compressed String
	 */
	byte[] compressSerializationContent(final String content) throws IOException;

	/**
	 * GZIPping a given String (utf-8) in URL safe Base64
	 * @param String content
	 * @return Compressed String
	 */
	String compressSerializationContentBase64(final String content) throws IOException;

	/**
	 * Given a class package name returns an appropriate serialization marshaller
	 * @param package name
	 * @return Marshaller
	 * @throws JAXBException
	 */
	Marshaller createMarshaller(final String  classPackageName) throws JAXBException;

	/**
	 * Decode the given base64 string to a byte array
	 * @param content
	 * @return
	 */
	byte[] decodeBase64StringToByteArray(final String content);

	/**
	 * Decompress a GZIPPed String (utf-8)
	 * @param byte[] input byte array
	 * @return Decompressed String
	 */
	String decompressSerializationContent(final byte[] compressedContent) throws IOException;

	/**
	 * Decompress a GZIPPed String in URL safe Base64
	 * @param String compressedContent
	 * @return Decompressed String
	 */
	String decompressSerializationContentBase64(final String compressedContent) throws IOException;

	/**
	 * Deserialize the given artifacts
	 * @param artifacts
	 */
	void deserializeInstances(final Map<String, S23M> artifacts);

	/**
	 * Perform the initial full deserialization
	 * @param artifacts
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	void doInitialFullDeserialization(Map<String, S23M> artifacts) throws IllegalArgumentException, IllegalAccessException;

	/**
	 * Encode the given byte array to a base64 string
	 * @param binaryContent
	 * @return
	 */
	String encodeByteArrayInBase64(byte[] binaryContent);

	/**
	 * Marshall a given object into a string
	 * @param <T>
	 * @param model
	 * @return String
	 * @throws JAXBException
	 */
	<T> String marshallModel(final T model) throws JAXBException;

	/**
	 * Serialize all instances in memory
	 * @return List<SerializationContent>
	 */
	List<String> serializeAllInstancesInMemory();

	/**
	 * Serialize the given container container
	 * @param container
	 * @return serialized content
	 */
	String serializeContainer(final ArtefactContainer container);

	/**
	 * Given vertex is serialized
	 * @param vertex
	 * @param isKernelSerialization
	 * @return serialized content
	 */
	SerializationContent serializeInstance(final Set vertex, final boolean isKernelSerialization);

	/**
	 * Serialize Kernel Root Vertex and to a list of strings
	 * @return List<SerializationContent>
	 */
	List<SerializationContent> serializeRoot();


	/**
	 * Serialize the current transport container content to a list of strings
	 * @return List<SerializationContent>
	 */
	//List<SerializationContent> serializeTransportContainerContent();

	/**
	 * Deserialized the given input to a artefact container
	 * @param xmlString
	 * @return ArtefactContainer
	 */
	ArtefactContainer unmarshallContainer (String xmlString);

	/**
	 * Unmarshall a given XML String
	 * @param String XML string
	 * @return S23M
	 */
	S23M unmarshallModel (final String xmlString);

	/**
	 * Unmarshall a given XML file at the given uri
	 * @param uri Serialization destination
	 * @return S23M
	 */
	S23M unmarshallModel (final URI uri);


}
