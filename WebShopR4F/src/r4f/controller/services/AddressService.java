/**
 * 
 */
package r4f.controller.services;

import r4f.model.Address;
import r4f.model.User;

/**
 * @author Ture
 *
 */
public class AddressService extends Service {
	
	public Address getAddress(User user){
		return super.getDbConnection().getAddress(user.getId(), true);
	}

}
