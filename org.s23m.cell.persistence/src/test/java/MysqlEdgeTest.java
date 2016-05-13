import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.s23m.cell.persistence.jdbc.dao.JdbcArrowDao;
import org.s23m.cell.persistence.jdbc.dao.JdbcEdgeDao;
import org.s23m.cell.persistence.jdbc.dao.JdbcGraphDao;
import org.s23m.cell.persistence.jdbc.dao.JdbcIdentityDao;
import org.s23m.cell.persistence.model.Arrow;
import org.s23m.cell.persistence.model.Edge;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Graph.ProperClasses;
import org.s23m.cell.persistence.model.Identity;

import com.jolbox.bonecp.BoneCPDataSource;


public class MysqlEdgeTest {
	public static void main(final String[] args) {
		final String jdbcConnectionString = "jdbc:mysql://127.0.0.1/cell?useUnicode=true&characterEncoding=UTF-8";

		final BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl(jdbcConnectionString);
		dataSource.setUser("root");
		dataSource.setPassword("root");

		final QueryRunner runner = new QueryRunner(dataSource);
		final JdbcGraphDao graphDao = new JdbcGraphDao(runner);
		final JdbcIdentityDao identityDao = new JdbcIdentityDao(runner);
		final JdbcArrowDao arrowDao = new JdbcArrowDao(runner);
		final JdbcEdgeDao edgeDao = new JdbcEdgeDao(runner);

		final String uuid = UUID.randomUUID().toString();
		final Identity identity = new Identity();
		identity.setUuid(uuid);
		identity.setName("graph test");
		identity.setPluralName("tests");
		identity.setCodeName("test");
		identity.setPluralCodeName("tests");
		identity.setPayload("some text");


		final Graph graph = new Graph();
		graph.setUuid(uuid);
		graph.setUrr(uuid);
		// (typically a different uuid)
		graph.setCategory(uuid);
		// (typically a different uuid)
		graph.setContainer(uuid);
		// (typically a different uuid)
		graph.setIsAbstractValue(uuid);
		// (typically a different uuid)
		graph.setMaxCardinalityValueInContainer(uuid);
		graph.setProperClass(ProperClasses.VERTEX);
		graph.setContentAsXml("<xml></xml>");


		final Arrow arrow = new Arrow();
		arrow.setFromGraph(uuid);
		arrow.setToGraph(uuid);
		arrow.setUrr(uuid);
		// (typically a different uuid)
		arrow.setCategory(uuid);
		arrow.setProperClass(ProperClasses.VISIBILITY);


		final Edge edge = new Edge();
		edge.setUrr(uuid);
		edge.setMinCardinalityValueFromEdgeEnd(uuid);
		edge.setMinCardinalityValueToEdgeEnd(uuid);
		edge.setMaxCardinalityValueFromEdgeEnd(uuid);
		edge.setMaxCardinalityValueToEdgeEnd(uuid);
		edge.setIsNavigableValueFromEdgeEnd(uuid);
		edge.setIsNavigableValueToEdgeEnd(uuid);
		edge.setIsContainerValueFromEdgeEnd(uuid);
		edge.setIsContainerValueToEdgeEnd(uuid);
		edge.setFromEdgeEnd(uuid);
		edge.setToEdgeEnd(uuid);

		// save identity
		identityDao.saveOrUpdate(identity);

		// save graph
		graphDao.saveOrUpdate(graph);

		// save arrow
		arrowDao.saveOrUpdate(arrow);

		// save edge
		edgeDao.saveOrUpdate(edge);

		// now retrieve the result
		final Edge retrieved = edgeDao.get(edge.getUrr());
		if (retrieved == null) {
			throw new RuntimeException("Retrieved Edge was null");
		}

		if (!retrieved.getUrr().equals(edge.getUrr())) {
			throw new RuntimeException("Retrieved Edge UUID does not match provided UUID");
		}

		System.out.println("Done!");
	}
}
