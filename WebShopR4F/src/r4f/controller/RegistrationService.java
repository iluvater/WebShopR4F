package r4f.controller;

import r4f.model.DatabaseConnection;
import r4f.model.User;

public class RegistrationService {
	
	DatabaseConnection dbConnection;
	
	/**
	 * Constructor that only initilazed a new Datenbank verbidnung
	 */
	public RegistrationService(){
		dbConnection = new DatabaseConnection();
	}
	
	/**
	 * 
	 * @param email email that is checked whether a user exists or not
	 * @return return true when a user with this email exists
	 *         returns false when there is no user with this email
	 */
	public boolean checkEmailExists(String email){
		User benutzer = dbConnection.getUser(email);
		if(benutzer == null){
			return false;
		}else{
			return true;
		}
	}
	
	public User createBenutzerInDB(User benutzer){
		int id = dbConnection.createUserInDB(benutzer);
		
		return dbConnection.getUser(id);
	}
}
