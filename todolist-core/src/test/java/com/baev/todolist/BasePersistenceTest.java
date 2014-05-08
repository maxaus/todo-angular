package com.baev.todolist;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Base class for database integration tests.
 *
 * @author Maxim Baev
 */
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/test-appContext.xml"})
public abstract class BasePersistenceTest extends AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * Flag is true when test data inserted already.
	 */
	private static boolean dataInserted;

	/**
	 * Data source bean.
	 */
	@Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;

	/**
	 * Insert test data in DB.
	 *
	 * @throws Exception exception occurred
	 */
	@Before
	public void insertTestData() throws Exception {
		if (!dataInserted) {
			BaseDbUnitTestUtils.insertData(getDatabaseConnection());
			dataInserted = true;
		}
	}

	/**
	 * Get database connection.
	 *
	 * @return DB connection
	 * @throws SQLException SQL exception occurred
	 */
	private IDatabaseConnection getDatabaseConnection() throws SQLException {
		final IDatabaseConnection databaseConnection = new DatabaseDataSourceConnection(dataSource);
		final DatabaseConfig databaseConfig = databaseConnection.getConfig();
		databaseConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
		return databaseConnection;
	}
}
