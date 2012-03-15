package org.s23m.cell.repository.connector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.s23m.cell.repository.ConfigValues;
import org.s23m.cell.repository.Repository;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.serializer.SerializationType;
import org.s23m.cell.serialization.serializer.Serializer;
import org.s23m.cell.serialization.serializer.SerializerHolder;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.RpcClient;
import com.rabbitmq.client.ShutdownSignalException;

public class RepositoryConnector implements Repository {

	private RpcClient repositoryAccessClient;

	private static class RepositoryConnectorHolder {
		public static final RepositoryConnector CONNECTOR = new RepositoryConnector();
	}

	private RepositoryConnector() {
		initConnector();
	}

	private void initConnector() {
		final ConnectionFactory cfconn = new ConnectionFactory();
		cfconn.setHost(ConfigValues.getValue("RepositoryConnector.HOST_NAME")); //$NON-NLS-1$
		cfconn.setPort(Integer.parseInt("RepositoryConnector.PORT_NUMBER")); //$NON-NLS-1$
		Connection conn;
		try {
			conn = cfconn.newConnection();
			final Channel ch = conn.createChannel();
			repositoryAccessClient = new RpcClient(ch, "", "RepositoryConnector.QUEUE_NAME"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (final IOException ex) {
			throw new IllegalStateException("connector initialization set up is failed",ex); //$NON-NLS-1$
		}
	}

	public static Repository getComponent() {
		return RepositoryConnectorHolder.CONNECTOR;
	}

	public ArtefactContainer get(final ArtefactContainer artifact)throws UnsupportedOperationException {
		final Map<String,Object> artifactsToGet = new HashMap<String,Object>();
		Map<String,Object> returnedArtifacts = null;
		final Serializer sz = SerializerHolder.getS23MInstanceSerializer(SerializationType.XML);
		artifactsToGet.put(artifact.getContentType(), sz.serializeContainer(artifact));
		try {
			returnedArtifacts = repositoryAccessClient.mapCall(artifactsToGet);
		} catch (final ShutdownSignalException ex) {
			throw new UnsupportedOperationException(ConfigValues.getValue("RepositoryConnector.INVOCATION_ERROR"),ex);
		} catch (final IOException ex) {
			throw new UnsupportedOperationException(ConfigValues.getValue("RepositoryConnector.INVOCATION_ERROR"),ex);
		}
		final String serializedResponse = returnedArtifacts.entrySet().iterator().next().getValue().toString();
		System.err.println("Response: \n\r"+serializedResponse);
		return sz.unmarshallContainer(serializedResponse);
	}

	public void put(final ArtefactContainer artifact)	throws UnsupportedOperationException {
		get(artifact);
	}

}
