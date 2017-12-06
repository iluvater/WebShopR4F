/**
 * 
 */
package r4f.controller.services;

import r4f.model.Article;
import r4f.model.Wishlist;
import r4f.model.WishlistItem;

/**
 * @author Ture
 *
 */
public class WishlistService extends Service {
	
	/**
	 * This method selects the wishlist with the id from the database
	 * @param wishlistId the id of th wishlist that should be selected
	 * @return returns the wishlist or null if no wishlist was selected
	 */
	public Wishlist getWishlist(int wishlistId){
		return super.getDbConnection().getWishlist(wishlistId);
	}
	
	/**
	 * This method updates the wishlist in the database. Therefore first all items will be deleted and than created again
	 * @param wishlist the wishlist that should be updated
	 */
	public void updateWishlistInDB(Wishlist wishlist){
		super.getDbConnection().deleteAllItemsOfWishlistInDB(wishlist.getId());
		
		for (WishlistItem item : wishlist.getList()) {
			super.getDbConnection().createWishlistItemInDB(item, wishlist.getId());
		}
	}
}
