package r4f.controller;

import r4f.model.Benutzer;
import r4f.model.DatenbankVerbindung;

public class LoginService {
	
	DatenbankVerbindung dbVerbindung;
	
	/**
	 * Constructor that creates a connection to the database
	 */
	public LoginService(){
		dbVerbindung = new DatenbankVerbindung();
	}
	/**
	 * 
	 * @param email the possible email
	 * @param password the possible password
	 * @return returns true if the login in was successful
	 * 		   returns false when there was an error during login
	 */
	public boolean checkLogin(String email, String password){
		Benutzer benutzer;
		if (!email.equals("") && email != null && !password.equals("") && password != null) {
			benutzer = dbVerbindung.getBenutzer(email);
			if(benutzer != null){
				if(benutzer.checkPassword(password)){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}			
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param email email of the user that should be selected in the database
	 * @return return an object of the class Benutzer representing the user with the email adress of the input parameter
	 */
	public Benutzer getBenutzer(String email){
		return dbVerbindung.getBenutzer(email);
	}
}
