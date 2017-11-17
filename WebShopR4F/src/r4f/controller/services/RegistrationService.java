package r4f.controller.services;

import r4f.model.User;

public class RegistrationService extends Service{
		
	/**
	 * 
	 * @param email email that is checked whether a user exists or not
	 * @return return true when a user with this email exists
	 *         returns false when there is no user with this email
	 */
	public boolean checkEmailExists(String email){
		User benutzer = super.getDbConnection().getUser(email);
		if(benutzer == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * This method creates an user in the database
	 * @param user the user that should be created
	 * @return
	 */
	public User createBenutzerInDB(User user){
		int addressId = super.getDbConnection().createAddressInDB(user.getStreet(), user.getHouseNumber(), user.getPostCode(), user.getCity(), true);
		
		int userId = super.getDbConnection().createUserInDB(user, addressId);
		
		int shoppingBasketId = super.getDbConnection().createShoppingBasketInDB(userId);
		
		user = super.getDbConnection().getUser(userId);
		
		user.setShoppingBasket(shoppingBasketId);
		
		super.getDbConnection().updateUserInDB(user);
		
		return user;
	}
}
