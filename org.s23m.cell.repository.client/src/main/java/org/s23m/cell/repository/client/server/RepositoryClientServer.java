/* ***** BEGIN LICENSE BLOCK *****
 * Version: SMTL 1.0
 *
 * The contents of this file are subject to the Sofismo Model Technology License Version
 * 1.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.sofismo.ch/SMTL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis.
 * See the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is Gmodel Semantic Extensions Edition.
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

package org.s23m.cell.repository.client.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.s23m.cell.repository.client.RepositoryClient;
import org.s23m.cell.repository.client.RepositoryClientImpl;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.serializer.SerializationType;
import org.s23m.cell.serialization.serializer.Serializer;
import org.s23m.cell.serialization.serializer.SerializerHolder;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MapRpcServer;

public class RepositoryClientServer {

	private static final int PORT = Integer.parseInt(ConfigValues.getString("RepositoryClientServer.PORT"));

	private static final String LOCALHOST = ConfigValues.getString("RepositoryClientServer.HOST_NAME"); //$NON-NLS-1$

	private static final String OK_PROMPT = ConfigValues.getString("RepositoryClientServer.OK_RESPONSE_PROMPT"); //$NON-NLS-1$

	private static final String INVALID_PROMPT = ConfigValues.getString("RepositoryClientServer.INVALID_RESPONSE_PROMPT"); //$NON-NLS-1$

	private static final String QUEUE_NAME = ConfigValues.getString("RepositoryClientServer.QUEUE"); //$NON-NLS-1$

	private final static RepositoryClient client = RepositoryClientImpl.getInstance();

	private Channel ch;

	private static List<RcpServerLauncher> launchers;

	private static boolean isServerRunning = false;

	static class RepositoryClientServerHolder {
		private static final RepositoryClientServer repositoryClientServer = new RepositoryClientServer();
	}

	public static RepositoryClientServer getInstance() {
		return RepositoryClientServerHolder.repositoryClientServer;
	}

	public synchronized void start() throws IllegalStateException {
		try {
			new RepositoryClientServer().startServers();
			setServerStatus(true);
		} catch (final IOException ex) {
			throw new IllegalStateException("Repository client cannot be started.", ex);
		}
	}

	public synchronized void stop() throws IllegalStateException {
		for (final RcpServerLauncher launcher : launchers) {
			try {
				launcher.terminate();
				setServerStatus(false);
			} catch (final InterruptedException ex) {
				throw new IllegalStateException("Repository client cannot be stopped.", ex);

			}
		}
	}

	public static void main(final String[] args) {
		final boolean alreadyRunning = RepositoryClientServerHolder.repositoryClientServer.isRepositoryServerRunning();
		if (alreadyRunning) {
			System.err.println("The repository client server is already running");
		} else {
			System.out.println("Starting repository client...");
			RepositoryClientServerHolder.repositoryClientServer.start();
		}
	}

	private RepositoryClientServer() {
		try {
			setupConnection();
		} catch (final IOException ex) {
			Logger.getLogger("global").log(Level.SEVERE, "Repository client server cannot set up a connection", ex); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private MapRpcServer createRepositoryClientServer() throws IOException {
		final MapRpcServer server = new MapRpcServer(ch, QUEUE_NAME) {
			@Override
			public Map<String,Object> handleMapCall(final Map<String,Object> request) {
				if (request.entrySet().iterator().hasNext()) {
					final String serializedArtifacts = request.entrySet().iterator().next().getValue().toString();
					System.err.println("Got: "+serializedArtifacts);
					final Serializer sz = SerializerHolder.getGmodelInstanceSerializer(SerializationType.XML);
					final ArtefactContainer artifacts = sz.unmarshallContainer(serializedArtifacts);
					final SerializationType typeOfService = SerializationType.valueOf(artifacts.getContentType());
					System.err.println("Type: "+typeOfService);
					if (isGetOperation(typeOfService)) {
						final ArtefactContainer results = client.get(artifacts);
						final String serializedResults = sz.serializeContainer(results);
						return createResponseMessage(OK_PROMPT,serializedResults); //$NON-NLS-1$
					} else {
						client.put(artifacts);
						return createResponseMessage(OK_PROMPT,null); //$NON-NLS-1$
					}
				} else {
					return createResponseMessage("Missing artefact","Missing artefact");
				}
			}

			private boolean isGetOperation(final SerializationType typeOfService) {
				boolean isGetOp = false;
				switch (typeOfService) {
				case CONTAINMENT_TREE:
					isGetOp = true;
					break;
				case DEPENDENT_INSTANCES:
					isGetOp = true;
					break;
				case SEARCH_ARGUMENTS:
					isGetOp = true;
					break;
				default: throw new UnsupportedOperationException("This type is not yet supported");
				}
				return isGetOp;
			}
		};
		return server;
	}

	private Map<String,Object> createResponseMessage(final String reposonse, final String msg) {
		final Map<String,Object> map = new HashMap<String,Object>();
		map.put(reposonse, msg);
		return map;
	}

	private void setupConnection() throws IOException {
		final ConnectionFactory connFactory = new ConnectionFactory();
		connFactory.setHost(LOCALHOST);
		connFactory.setPort(PORT);
		connFactory.setVirtualHost(ConfigValues.getString("RepositoryClientServer.VHOST_NAME"));
		connFactory.setUsername(ConfigValues.getString("RepositoryClientServer.USER_NAME"));
		connFactory.setPassword(ConfigValues.getString("RepositoryClientServer.PW"));
		final Connection conn = connFactory.newConnection();
		ch = conn.createChannel();
		ch.queueDeclare(QUEUE_NAME, false, false, false, null);
	}

	private List<RcpServerLauncher> setUpServers() throws IOException {
		final List<RcpServerLauncher> serverLaunchers = new ArrayList<RcpServerLauncher>();
		final MapRpcServer repoServer = createRepositoryClientServer();
		serverLaunchers.add(new RcpServerLauncher(repoServer));
		return serverLaunchers;
	}

	private void startServers() throws IOException{
		launchers = setUpServers();
		for (final RcpServerLauncher launcher : launchers) {
			launcher.start();
		}
	}

	public synchronized boolean isRepositoryServerRunning() {
		return isServerRunning;
	}

	private synchronized void setServerStatus (final boolean isRunning) {
		isServerRunning = isRunning;
	}

}
