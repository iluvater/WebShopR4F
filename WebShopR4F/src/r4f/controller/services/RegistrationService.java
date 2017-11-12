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
	
	public User createBenutzerInDB(User benutzer){
		int userId = super.getDbConnection().createUserInDB(benutzer);
		
		int shoppingBasketId = super.getDbConnection().createShoppingBasketInDB(userId);
		
		User user = super.getDbConnection().getUser(userId);
		
		user.setShoppingBasket(shoppingBasketId);
		
		super.getDbConnection().updateUserInDB(user);
		
		return user;
	}
}
