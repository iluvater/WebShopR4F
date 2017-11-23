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
	
	/**
	 * This method adds an article to the shopping basket if an article is already in the shopping basket the amount of this article will be increased
	 * @param article The article that should be added to the shopping basket
	 */
	public void addItem(Article article) {
		boolean notFound = true;
		for(int i =0; i < items.size() && notFound; i++){
			ShoppingBasketItem item = items.get(i);
			if(item.getArticle().getId() == article.getId()){
				notFound = false;
				item.setAmount(item.getAmount()+1);
			}
		}
		if(notFound){
			ShoppingBasketItem item = new ShoppingBasketItem(1, article);
			items.add(item);
		}
	}

	 /**
	 * This method returns the total price of the shopping basket
	 * @return the total price of the shopping basket. The price is rounded at two digits.
	 */
	public double getTotalPrice(){
		double sum= 0;
		for (ShoppingBasketItem shoppingBasketItem : items) {
			sum += shoppingBasketItem.getItemPrice();
		}		
		return ((double) Math.round(sum * 100)) / 100.0;
	}
}
