import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.s23m.cell.persistence.jdbc.dao.JdbcIdentityDao;
import org.s23m.cell.persistence.model.Identity;

import com.jolbox.bonecp.BoneCPDataSource;


public class MysqlIdentityTest {
	public static void main(final String[] args) {
		final String jdbcConnectionString = "jdbc:mysql://127.0.0.1/cell?useUnicode=true&characterEncoding=UTF-8";

		final BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl(jdbcConnectionString);
		dataSource.setUser("root");
		dataSource.setPassword("root");

		final QueryRunner runner = new QueryRunner(dataSource);
		final JdbcIdentityDao dao = new JdbcIdentityDao(runner);

		final Identity identity = new Identity();
		identity.setUuid(UUID.randomUUID().toString());
		identity.setName("test");
		identity.setPluralName("tests");
		identity.setCodeName("test");
		identity.setPluralCodeName("tests");
		identity.setPayload("some text");

		// save identity
		dao.saveOrUpdate(identity);

		// now retrieve the result
		final Identity retrieved = dao.get(identity.getUuid());
		if (retrieved == null) {
			throw new RuntimeException("Retrieved identity was null");
		}

		if (!retrieved.getUuid().equals(identity.getUuid())) {
			throw new RuntimeException("Retrieved identity UUID does not match provided UUID");
		}

		System.out.println("Done!");
	}
}
