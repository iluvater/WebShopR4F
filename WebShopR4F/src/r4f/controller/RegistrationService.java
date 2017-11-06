package r4f.controller;

import r4f.model.User;
import r4f.model.DatabaseConnection;

public class RegistrationService {
	
	DatabaseConnection dbVerbindung;
	
	/**
	 * Constructor that only initilazed a new Datenbank verbidnung
	 */
	public RegistrationService(){
		dbVerbindung = new DatabaseConnection();
	}
	
	/**
	 * 
	 * @param email email that is checked whether a user exists or not
	 * @return return true when a user with this email exists
	 *         returns false when there is no user with this email
	 */
	public boolean checkEmailExists(String email){
		User benutzer = dbVerbindung.getUser(email);
		if(benutzer == null){
			return false;
		}else{
			return true;
		}
	}
	
	public User createBenutzerInDB(User benutzer){
		dbVerbindung.createUserInDB(benutzer);
		
		benutzer = dbVerbindung.getUser(benutzer.getEmail());
		return benutzer;
	}
}
