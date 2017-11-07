package r4f.controller;

import r4f.model.DatabaseConnection;
import r4f.model.User;

public class LoginService {
	
	DatabaseConnection dbConnection;
	
	/**
	 * Constructor that creates a connection to the database
	 */
	public LoginService(){
		dbConnection = new DatabaseConnection();
	}
	/**
	 * 
	 * @param email the possible email
	 * @param password the possible password
	 * @return returns true if the login in was successful
	 * 		   returns false when there was an error during login
	 */
	public boolean checkLogin(String email, String password){
		User benutzer;
		if (!email.equals("") && email != null && !password.equals("") && password != null) {
			benutzer = dbConnection.getUser(email);
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
	 * @return return an object of the class User representing the user with the email adress of the input parameter
	 */
	public User getBenutzer(String email){
		return dbConnection.getUser(email);
	}
}
