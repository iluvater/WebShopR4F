/**
 * 
 */
package r4f.controller.services;

import r4f.model.DatabaseConnection;
import r4f.model.User;

/**
 * @author Ture
 *
 */
public class AuthorizationService {
	
	/**
	 * This method checks whether an user has an authorization or not
	 * @param user user for which should be checked 
	 * @param authorization authorization that should be checked
	 * @return returns false if the user is not logged in or has not the authorization
	 * 			returns true if the user has the authorization
	 */
	public static boolean authorityCheck(User user, String authorization){
		if(user.getEmail() == null){
			//authorization not granted because user has to login
			return false;
		}else{
			DatabaseConnection dbConnection = new DatabaseConnection();
			int roleId = dbConnection.getRoleId(user.getRole());
			if(roleId == -1){
				//authorization not granted because role unkown
				return false;
			}else{
				return dbConnection.checkAuthorizationInDB(roleId, authorization);
			}
		}
	}

}
