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
	private final double shippingPrice = 4.95;

	/**
	 * Constructor for a new shopping basket that is not stored in the database
	 * yet
	 */
	public ShoppingBasket() {
		setItems(new ArrayList<ShoppingBasketItem>());
	}

	/**
	 * Constructor for a new shopping basket that has a id
	 * @param id the id to set
	 */
	public ShoppingBasket(int id) {
		this.id = id;
		setItems(new ArrayList<ShoppingBasketItem>());
	}

	/**
	 * Constructor for a shopping basket that is already stored in the database
	 * 
	 * @param id the id to set
	 * @param items the items to set
	 */
	public ShoppingBasket(int id, List<ShoppingBasketItem> items) {
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
	 * @param id
	 *            the id to set
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
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<ShoppingBasketItem> items) {
		this.items = items;
	}

	/**
	 * This method adds an article to the shopping basket if an article is
	 * already in the shopping basket the amount of this article will be
	 * increased
	 * 
	 * @param article
	 *            The article that should be added to the shopping basket
	 * @param size
	 *            the size of the article
	 * @param color
	 *            the color of the article
	 */
	public void addItem(Article article, int size, String color) {
		boolean notFound = true;
		for (int i = 0; i < items.size() && notFound; i++) {
			ShoppingBasketItem item = items.get(i);
			if (item.getArticle().getId() == article.getId() && item.getColor().equals(color)
					&& item.getSize() == size) {
				notFound = false;
				item.setAmount(item.getAmount() + 1);
			}
		}
		if (notFound) {
			ShoppingBasketItem item = new ShoppingBasketItem(1, article, size, color);
			items.add(item);
		}
	}

	/**
	 * This method returns the total price of the shopping basket
	 * 
	 * @return the total price of the shopping basket inc. the shippingPrice The
	 *         price is rounded at two digits.
	 */
	public double getTotalPrice() {
		double sum = 0;
		for (ShoppingBasketItem shoppingBasketItem : items) {
			sum += shoppingBasketItem.getItemPrice();
		}
		sum += shippingPrice;
		return ((double) Math.round(sum * 100)) / 100.0;
	}

	/**
	 * This method returns the price of all items of the shopping Basket
	 * 
	 * @return the total price of the shopping basket. The price is rounded at
	 *         two digits.
	 */
	public double getOrderPrice() {
		double sum = 0;
		for (ShoppingBasketItem shoppingBasketItem : items) {
			sum += shoppingBasketItem.getItemPrice();
		}
		return ((double) Math.round(sum * 100)) / 100.0;
	}

	/**
	 * @return the shippingPrice
	 */
	public double getShippingPrice() {
		return shippingPrice;
	}

	/**
	 * This method changes the amount of an article in the shopping basket
	 * 
	 * @param articleId
	 *            the id of the article which amount should be changed
	 * @param amount
	 *            the new amount
	 * @param size
	 *            the size of the article
	 * @param color
	 *            the color of the article
	 * 
	 */
	public void setAmountOfArticle(int articleId, int amount, int size, String color) {
		if (amount <= 0) {
			removeItem(articleId, size, color);
		} else {
			for (ShoppingBasketItem shoppingBasketItem : items) {
				if (shoppingBasketItem.getArticle().getId() == articleId && shoppingBasketItem.getSize() == size
						&& shoppingBasketItem.getColor().equals(color)) {
					shoppingBasketItem.setAmount(amount);
				}
			}
		}
	}

	/**
	 * This method removes an article from the shopping basket
	 * 
	 * @param articleId
	 *            the id of the article that should be removed
	 * @param size
	 *            the size of the article
	 * @param color
	 *            the color of the article
	 */
	public void removeItem(int articleId, int size, String color) {
		List<ShoppingBasketItem> deleteItems = new ArrayList<ShoppingBasketItem>();
		for (ShoppingBasketItem shoppingBasketItem : items) {
			if (shoppingBasketItem.getArticle().getId() == articleId && shoppingBasketItem.getSize() == size
					&& shoppingBasketItem.getColor().equals(color)) {
				deleteItems.add(shoppingBasketItem);
			}
		}
		for (ShoppingBasketItem shoppingBasketItem : deleteItems) {
			items.remove(shoppingBasketItem);
		}
	}

	/**
	 * This method removes all items from the shopping Basket
	 */
	public void removeAllItems() {
		items.clear();
	}

	/**
	 * This method adds a wishlist to the shopping basket
	 * 
	 * @param wishlist the wishlist that should be added
	 */
	public void addWishlist(Wishlist wishlist) {
		for (WishlistItem item : wishlist.getList()) {
			addItem(item.getArticle(), item.getSize(), item.getColor());
		}
	}

	/**
	 * This method changes the size and the color of an article
	 * 
	 * @param articleId
	 *            the id of the article
	 * @param size
	 *            the old size
	 * @param color
	 *            the old color
	 * @param sizeNew
	 *            the new size
	 * @param colorNew
	 *            the new color
	 */
	public void setSizeColorOfArticle(int articleId, int size, String color, int sizeNew, String colorNew) {
		for (int i = 0; i < items.size(); i++) {
			ShoppingBasketItem shoppingBasketItem = items.get(i);
			if (shoppingBasketItem.getArticle().getId() == articleId && shoppingBasketItem.getSize() == size
					&& shoppingBasketItem.getColor().equals(color)) {
				items.get(i).setColor(colorNew);
				items.get(i).setSize(sizeNew);
			}
		}
		removeDublicates();
	}

	/**
	 * This method removes duplicates from the shopping basket and adds the
	 * amounts of the duplicates
	 */
	private void removeDublicates() {
		List<ShoppingBasketItem> duplicates = new ArrayList<ShoppingBasketItem>();
		for (int i = 0; i < items.size(); i++) {
			ShoppingBasketItem item = items.get(i);
			boolean found = false;
			for (int j = 0; j < duplicates.size(); j++) {

				ShoppingBasketItem duplicate = duplicates.get(j);
				if (duplicate.getArticle().getId() == item.getId() && duplicate.getSize() == item.getSize()
						&& duplicate.getColor().equals(item.getColor())) {
					found = true;
				}
			}
			if (!found) {
				for (int k = i + 1; k < items.size(); k++) {
					ShoppingBasketItem posDuplicate = items.get(k);
					if (posDuplicate.getArticle().getId() == item.getId() && posDuplicate.getSize() == item.getSize()
							&& posDuplicate.getColor().equals(item.getColor())) {
						duplicates.add(posDuplicate);
						item.setAmount(item.getAmount() + posDuplicate.getAmount());
					}
				}
			}
		}
		for (ShoppingBasketItem shoppingBasketItem : duplicates) {
			items.remove(shoppingBasketItem);
		}
	}
}
