/**
 * 
 */
package r4f.controller.services;

import java.util.List;

import r4f.model.ShoppingBasket;
import r4f.model.ShoppingBasketItem;

/**
 * @author Ture
 *
 */
public class ShoppingBasketService extends Service {

	/**
	 * This method selects the shopping basket for the id from the database
	 * @param id id of the shopping basket
	 * @return returns the shopping basket or null if there is no shopping basket with this id
	 */
	public ShoppingBasket getShoppingBasket(int id){
		return super.getDbConnection().getShoppingBasket(id);
	}

	/**
	 * This method udates the shopping basket in the database
	 * The new values should be stored in the parameter
	 * @param shoppingBasket shopping basket that should be updates
	 */
	public void updateShoppingBasketInDB(ShoppingBasket shoppingBasket) {
		super.getDbConnection().deleteAllItemsOfShoppingBasketInDB(shoppingBasket.getId());
		
		List<ShoppingBasketItem> items = shoppingBasket.getItems();
		
		for (ShoppingBasketItem shoppingBasketItem : items) {
			 shoppingBasketItem.setId(super.getDbConnection().createShoppingBasketItem(shoppingBasketItem, shoppingBasket.getId()));
		}
		
	}

}
