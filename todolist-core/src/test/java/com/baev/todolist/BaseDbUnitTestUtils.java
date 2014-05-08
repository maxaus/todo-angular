package com.baev.todolist;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;

/**
 * DbUnit utility class for  tests.
 *
 * @author Maxim Baev
 */
public class BaseDbUnitTestUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDbUnitTestUtils.class);

	private static final String TEST_DATA_FOLDER = "dbunit";

	/**
	 * Table names.
	 */
	private static final String[] tableNames = new String[]{
			"user",
			"note"
	};

	/**
	 * Insert test data to DB.
	 *
	 * @param databaseConnection DB connection
	 * @throws Exception some exception occurred
	 */
	public static void insertData(final IDatabaseConnection databaseConnection) throws Exception {
		// Insert all needed test data

		final Connection connection = databaseConnection.getConnection();
		try {
			connection.setAutoCommit(true);

			final int numOfTables = tableNames.length;
			final IDataSet[] dataSets = new IDataSet[numOfTables];
			for (int i = 0; i < numOfTables; i++) {
				try {
					dataSets[i] = getDataSet(tableNames[i]);
				} catch (DataSetException e) {
					if (e.getCause().getClass().equals(MalformedURLException.class)) {
						LOGGER.info("File {}.xml doesn't exist, ignoring.", tableNames[i]);
					} else {
						LOGGER.error("Exception reading " + tableNames[i] + ".xml", e);
						throw e;
					}
				}
			}

			//truncate from tail
			for (int i = numOfTables - 1; i >= 0; i--) {
				final String tableName = tableNames[i];
				try {
					final IDataSet dataSet = new DefaultDataSet(new DefaultTable(tableNames[i].toUpperCase()));
					DatabaseOperation.DELETE_ALL.execute(databaseConnection, dataSet);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Error while clearing table: " + tableName, e);
				}
			}

			//insert from head
			for (int i = 0; i < dataSets.length; i++) {
				final String tableName = tableNames[i];
				try {
					final IDataSet dataSet = dataSets[i];
					if (dataSet == null)
						continue;//could not be read
					DatabaseOperation.INSERT.execute(databaseConnection, dataSet);
				} catch (Exception e) {
					throw new Exception("Error while inserting data into: " + tableName, e);
				}
			}
		} finally {
			if (connection != null) {
				connection.setAutoCommit(false);
				connection.close();
			}
		}
	}

	/**
	 * Returns data from test XML file.
	 *
	 * @param tableName table name.
	 * @return Data loaded from test file.
	 * @throws DataSetException is some error occurs.
	 */
	public static IDataSet getDataSet(final String tableName) throws DataSetException {
		final String fileName = TEST_DATA_FOLDER + File.separator + tableName + ".xml";
		final FlatXmlProducer producer = new FlatXmlProducer(new InputSource(
				BasePersistenceTest.class.getClassLoader().getResourceAsStream(fileName)), false, true);
		return new FlatXmlDataSet(producer);
	}
}
