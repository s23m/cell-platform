import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.s23m.cell.persistence.jdbc.dao.JdbcGraphDao;
import org.s23m.cell.persistence.jdbc.dao.JdbcIdentityDao;
import org.s23m.cell.persistence.model.Graph;
import org.s23m.cell.persistence.model.Graph.ProperClasses;
import org.s23m.cell.persistence.model.Identity;

import com.jolbox.bonecp.BoneCPDataSource;


public class MysqlGraphTest {
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

		final String uuid = UUID.randomUUID().toString();
		final Identity identity = new Identity();
		identity.setUuid(uuid);
		identity.setName("graph test");
		identity.setPluralName("tests");
		identity.setCodeName("test");
		identity.setPluralCodeName("tests");
		identity.setPayload("some text");

		final Graph result = new Graph();
		result.setUrr(uuid);
		result.setUuid(uuid);
		// (typically a different uuid)
		result.setCategory(uuid);
		// (typically a different uuid)
		result.setContainer(uuid);
		// (typically a different uuid)
		result.setIsAbstractValue(uuid);
		// (typically a different uuid)
		result.setMaxCardinalityValueInContainer(uuid);
		result.setProperClass(ProperClasses.VERTEX);
		result.setContentAsXml("<xml></xml>");

		// save identity
		identityDao.saveOrUpdate(identity);

		// save graph
		graphDao.saveOrUpdate(result);

		// now retrieve the result
		final Graph retrieved = graphDao.get(result.getUrr());
		if (retrieved == null) {
			throw new RuntimeException("Retrieved Graph was null");
		}

		if (!retrieved.getUrr().equals(result.getUrr())) {
			throw new RuntimeException("Retrieved Graph UUID does not match provided UUID");
		}

		System.out.println("Done!");
	}
}
