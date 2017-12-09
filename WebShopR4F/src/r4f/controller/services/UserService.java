/**
 * 
 */
package r4f.controller.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import r4f.model.Role;
import r4f.model.User;

/**
 * @author Ture
 *
 */
public class UserService extends Service {

	public boolean updateUserInDB(User user) {
		try {
			super.getDbConnection().updateUserInDB(user);
			super.getDbConnection().updateAddressInDB(user.getId(), user.getFirstName(), user.getLastName(),
					user.getStreet(), user.getHouseNumber(), user.getPostCode(), user.getCity(), user.getSalutation());
			super.getDbConnection().deleteRoleMappings(user);
			for (Role role : user.getRole()) {
				super.getDbConnection().createRoleMapping(user, role);
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param email
	 *            email that is checked whether a user exists or not
	 * @return return true when a user with this email exists returns false when
	 *         there is no user with this email
	 */
	public boolean checkEmailExists(String email) {
		User benutzer = super.getDbConnection().getUser(email);
		if (benutzer == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This method creates an user in the database
	 * 
	 * @param user
	 *            the user that should be created
	 * @return
	 */
	public User createBenutzerInDB(User user) {
		int userId = super.getDbConnection().createUserInDB(user);

		int shoppingBasketId = super.getDbConnection().createShoppingBasketInDB(userId);

		int wishlistId = super.getDbConnection().createWishlistInDB(userId);

		super.getDbConnection().createAddressInDB(userId, user.getFirstName(), user.getLastName(), user.getStreet(),
				user.getHouseNumber(), user.getPostCode(), user.getCity(), true, user.getSalutation());

		user.setShoppingBasket(shoppingBasketId);
		user.setId(userId);
		user.setWishlist(wishlistId);
		List<Role> roles = new ArrayList<Role>();
		roles.add(super.getDbConnection().getRole(1));
		user.setRole(roles);

		try {
			super.getDbConnection().updateUserInDB(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		user = super.getDbConnection().getUser(userId);

		return user;
	}

	/**
	 * 
	 * @param email
	 *            the possible email
	 * @param password
	 *            the possible password
	 * @return returns true if the login in was successful returns false when
	 *         there was an error during login
	 */
	public boolean checkLogin(String email, String password) {
		User benutzer;
		if (!email.equals("") && email != null && !password.equals("") && password != null) {
			benutzer = super.getDbConnection().getUser(email);
			if (benutzer != null) {
				if (benutzer.checkPassword(password)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param email
	 *            email of the user that should be selected in the database
	 * @return return an object of the class User representing the user with the
	 *         email address of the input parameter
	 */
	public User getUser(String email) {
		return super.getDbConnection().getUser(email);
	}

	public List<User> getUserList() {
		return super.getDbConnection().getUserList();
	}
	
	/**
	 * This method returns a list of user that contain the search pattern in their lastname, id, email 
	 * @param searchPattern the search pattern 
	 * @return the list of users
	 */
	public List<User> getUserSearchPattern(String searchPattern) {
		return super.getDbConnection().getUserListSearchPattern(searchPattern);
	}

}
