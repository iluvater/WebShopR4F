package r4f.controller.services;

import r4f.model.User;

public class LoginService extends Service{
	

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
			benutzer = super.getDbConnection().getUser(email);
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
	public User getUser(String email){
		return super.getDbConnection().getUser(email);
	}
	
}
