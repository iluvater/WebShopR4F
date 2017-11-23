/**
 * 
 */
package r4f.controller.services;

import java.sql.SQLException;

import r4f.model.User;

/**
 * @author Ture
 *
 */
public class UserService extends Service {

	public boolean updateUserInDB(User user) {
		try{
			super.getDbConnection().updateUserInDB(user);
			super.getDbConnection().updateAddressInDB(user.getId(), user.getStreet(), user.getHouseNumber(), user.getPostCode(), user.getCity());
			return true;
		}catch(SQLException e){
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

		user = super.getDbConnection().getUser(userId);
		
		super.getDbConnection().createAddressInDB(user.getId(), user.getStreet(), user.getHouseNumber(),
				user.getPostCode(), user.getCity(), true);

		user.setShoppingBasket(shoppingBasketId);

		try {
			super.getDbConnection().updateUserInDB(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
	 *         email adress of the input parameter
	 */
	public User getUser(String email) {
		return super.getDbConnection().getUser(email);
	}

}
