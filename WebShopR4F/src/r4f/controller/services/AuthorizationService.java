/**
 * 
 */
package r4f.controller.services;

import r4f.model.Role;
import r4f.model.User;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ture
 *
 */
public class AuthorizationService extends Service{
	
	/**
	 * This method checks whether an user has an authorization or not
	 * @param user user which should be checked 
	 * @param role authorization that should be checked
	 * @return returns false if the user is not logged in or has not the authorization
	 * 			returns true if the user has the authorization
	 */
	public static boolean authorityCheck(User user, String role){
		if(user.getId() == 0){
			//authorization not granted because user has to login
			return false;
		}else{
			boolean granted = false;
			for (Role roleItem : user.getRole()) {
				if(roleItem.getName().equals(role)){
					granted = true;
				}
			}
			return granted;
		}
	}
	
	/**
	 * @param userId the userId
	 * @param name the name of the role
	 */
	public int createUserRoleMapping(int userId, String name) {
		User user = super.getDbConnection().getUser(userId);
		int roleId = super.getDbConnection().getRoleId(name);
		Role role = super.getDbConnection().getRole(roleId);
		return super.getDbConnection().createRoleMapping(user, role);
		
	}
	
	/**
	 * This method updates an role in the database
	 * @param role the role that should be updated
	 * @return true if the role was updated or false if an error occurred during the update
	 */
	public boolean updateRoleInDB(Role role) {
		try{
			super.getDbConnection().updateRoleInDB(role);
			return true;
		}catch(SQLException e){
			return false;
		}
	}

	/**
	 * This Method returns a list of all roles in the database
	 * @return a list of all roles
	 */
	public List<Role> getRoleList() {
		return super.getDbConnection().getRoleList();
	}

	/**
	 * This method creates a role in the dataBase
	 * @param role the role that should be created
	 * @return returns the id of the new role or -1 if no role was created
	 */
	public int createRoleInDB(Role role) {
		return super.getDbConnection().createRole(role);
	}

	public void deleteUserRoleMapping(int userId, int roleId) {
		super.getDbConnection().deleteUserRoleMapping(userId, roleId);		
	}
	
	

}
