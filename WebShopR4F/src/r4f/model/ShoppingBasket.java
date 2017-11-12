/**
 * 
 */
package r4f.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ture
 *
 */
public class ShoppingBasket {

	private int id;
	private List<ShoppingBasketItem> items;
	
	/**
	 * Constructor for a new shopping basket that is not stored in the database yet
	 */
	public ShoppingBasket() {
		setItems(new ArrayList<ShoppingBasketItem>());		
	}
	
	/**
	 * Constructor for a new shopping basket that has a id
	 */
	public ShoppingBasket(int id) {
		this.id=id;
		setItems(new ArrayList<ShoppingBasketItem>());		
	}
	
	/**
	 * Constructor for a shopping basket that is already stored in the database
	 * @param id
	 * @param items
	 */
	public ShoppingBasket(int id, List<ShoppingBasketItem> items){
		this.id = id;
		this.items = items;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the items
	 */
	public List<ShoppingBasketItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<ShoppingBasketItem> items) {
		this.items = items;
	}
	

}
