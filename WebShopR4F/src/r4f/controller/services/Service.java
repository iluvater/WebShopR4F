/**
 * 
 */
package r4f.controller.services;

import r4f.model.DatabaseConnection;

/**
 * @author Ture
 *
 */
public abstract class Service {

	private DatabaseConnection dbConnection;
	
	/**
	 * Constructor that a connection to the database
	 */
	public Service() {
		// TODO Auto-generated constructor stub
		setDbConnection(new DatabaseConnection());
	}

	/**
	 * @return the dbConnection
	 */
	public DatabaseConnection getDbConnection() {
		return dbConnection;
	}

	/**
	 * @param dbConnection the dbConnection to set
	 */
	public void setDbConnection(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

}
