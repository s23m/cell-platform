package org.s23m.cell.repository.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.s23m.cell.repository.ConfigValues;
import org.s23m.cell.repository.RelationalDatabaseRepository;
import org.s23m.cell.serialization.container.ArtefactContainer;
import org.s23m.cell.serialization.serializer.SerializationType;
import org.s23m.cell.serialization.serializer.Serializer;
import org.s23m.cell.serialization.serializer.SerializerHolder;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MapRpcServer;

public class RepositoryAccessServer {

	private final static RelationalDatabaseRepository client = RelationalDatabaseRepository.getRepository();
	private Channel ch;
	private List<ServiceLauncher> launchers;

	public static void main(final String[] args) {
		try {
			new RepositoryAccessServer().startServers();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}


	private RepositoryAccessServer() throws IOException {
		setupConnection();
	}

	private MapRpcServer createRepositoryAccessServer() throws IOException {
		final MapRpcServer server = new MapRpcServer(ch, ConfigValues.getValue("RepositoryConnector.QUEUE_NAME")) {
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
						return createResponseMessage("",serializedResults); //$NON-NLS-1$
					} else {
						client.put(artifacts);
						return createResponseMessage("",null); //$NON-NLS-1$
					}
				} else {
					return createResponseMessage("Missing artefact","Missing artefact");
				}
			}

			private boolean isGetOperation(final SerializationType typeOfService) {
				boolean isGetOp = false;
				switch (typeOfService) {
				case ARTIFACT_RETRIEVAL:
					isGetOp = true;
					break;
				case CONTAINMENT_TREE:
					isGetOp = true;
					break;
				case DEPENDENT_INSTANCES:
					isGetOp = true;
					break;
				case CONTAINMENT_TREE_UUIDS_RETRIEVAL:
					isGetOp = true;
					break;
				case SEARCH_ARGUMENTS:
					isGetOp = true;
					break;
				default: throw new UnsupportedOperationException( ConfigValues.getValue("RepositoryConnector.NOT_SUPPORTED_ERROR"));
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
		connFactory.setHost(ConfigValues.getValue("RepositoryConnector.HOST_NAME"));
		connFactory.setPort(Integer.parseInt(ConfigValues.getValue("RepositoryConnector.PORT_NUMBER")));
		final Connection conn = connFactory.newConnection();
		ch = conn.createChannel();
		ch.queueDeclare(ConfigValues.getValue("RepositoryConnector.QUEUE_NAME"), false, false, false, null);
	}

	private List<ServiceLauncher> setUpServers() throws IOException {
		final List<ServiceLauncher> serverLaunchers = new ArrayList<ServiceLauncher>();
		final MapRpcServer repoServer = createRepositoryAccessServer();
		serverLaunchers.add(new ServiceLauncher(repoServer));
		return serverLaunchers;
	}

	private void startServers() throws IOException{
		launchers = setUpServers();
		for (final ServiceLauncher launcher : launchers) {
			launcher.start();
		}
	}

	private void terminateServers() throws InterruptedException{
		for (final ServiceLauncher launcher : launchers) {
			launcher.terminate();
		}
	}

}