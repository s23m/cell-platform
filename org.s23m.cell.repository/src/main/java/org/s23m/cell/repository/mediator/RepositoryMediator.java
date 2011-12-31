package org.s23m.cell.repository.mediator;

import org.s23m.cell.repository.ConfigValues;
import org.s23m.cell.repository.RelationalDatabaseRepository;
import org.s23m.cell.repository.Repository;
import org.s23m.cell.repository.connector.RepositoryConnector;
import org.s23m.cell.serialization.serializer.ProtocolType;

public class RepositoryMediator {

	private static class MediatorHolder {
		public static final RepositoryMediator MEDIATOR = new RepositoryMediator();
	}

	private RepositoryMediator() {
	}

	public static RepositoryMediator getInstance() {
		return MediatorHolder.MEDIATOR;
	}

	public Repository getComponent(final ProtocolType type)throws UnsupportedOperationException {
		if (!type.equals(ProtocolType.REPOSITORY_ACCESS)) {
			throw new UnsupportedOperationException("Not supported protocol");
		}
		final String s = ConfigValues.getValue("RepositoryConnector.IMPL_CLASS");
		if(isLocallyAvailable(ConfigValues.getValue("RepositoryConnector.IMPL_CLASS"))) {
			return RelationalDatabaseRepository.getRepository();
		} else {
			return RepositoryConnector.getComponent(); //pass back an interface that send artefacts over AMQP
		}
	}

	private boolean isLocallyAvailable(final String className) {
		final ClassLoader classLoader = RepositoryMediator.class.getClassLoader();
		boolean isAvaiable = false;
		try {
			final Class aClass = classLoader.loadClass(className);
			isAvaiable = true;
		} catch (final ClassNotFoundException ex) {}
		return isAvaiable;
	}

}
