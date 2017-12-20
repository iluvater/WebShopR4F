/**
 * 
 */
package r4f.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ture This class represents a wishlist
 */
public class Wishlist {
	private int id;
	private List<WishlistItem> list;

	/**
	 * This constructor can be used for a wish list that is not stored in the
	 * database
	 */
	public Wishlist() {
		list = new ArrayList<WishlistItem>();
	}

	/**
	 * This constructor can be used for a wish list that has an id
	 * 
	 * @param id
	 *            the id to set
	 */
	public Wishlist(int id) {
		this.id = id;
		list = new ArrayList<WishlistItem>();
	}

	/**
	 * Constructor for a wish list that is already stored in the database
	 * 
	 * @param id
	 *            the id to set
	 * @param list
	 *            the list to set
	 */
	public Wishlist(int id, List<WishlistItem> list) {
		this.id = id;
		this.list = list;
	}

	/**
	 * @return the list
	 */
	public List<WishlistItem> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<WishlistItem> list) {
		this.list = list;
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
	 * This method adds an article to the wish list if the wish list already
	 * contains the article nothing will happen
	 * 
	 * @param article
	 *            the article that should be added
	 * @param size
	 *            the size of the article
	 * @param color
	 *            the color of the article
	 */
	public void addItem(Article article, int size, String color) {
		boolean found = false;
		for (WishlistItem item : list) {
			if (item.getArticle().getId() == article.getId() && item.getColor().equals(color)
					&& item.getSize() == size) {
				found = true;
			}
		}
		if (!found) {
			list.add(new WishlistItem(size, color, article));
		}
	}

	/**
	 * This method adds an article to the wish list if the wish list already
	 * contains the article nothing will happen
	 * 
	 * @param wishlistItem
	 *            the item that should be added
	 */
	public void addItem(WishlistItem wishlistItem) {
		boolean found = false;
		for (WishlistItem item : list) {
			if (item.getId() == wishlistItem.getId()) {
				found = true;
			}
		}
		if (!found) {
			list.add(wishlistItem);
		}
	}

	/**
	 * This method removes all articles from the wishlist
	 */
	public void removeAllItems() {
		list.clear();
	}

	/**
	 * This method removes an article from the wish list
	 * 
	 * @param articleId
	 *            the id of the article that should be removed
	 * @param size
	 *            the size of the article
	 * @param color
	 *            the color of the article
	 */
	public void removeItem(int articleId, int size, String color) {
		List<WishlistItem> removeArticles = new ArrayList<WishlistItem>();
		for (WishlistItem item : list) {
			if (item.getArticle().getId() == articleId && item.getColor().equals(color) && item.getSize() == size) {
				removeArticles.add(item);
			}
		}
		for (WishlistItem item : removeArticles) {
			list.remove(item);
		}
	}

	public void setSizeColorOfArticle(int articleId, int size, String color, int sizeNew, String colorNew) {
		for (int i = 0; i < list.size(); i++) {
			WishlistItem wishlistItem = list.get(i);
			if (wishlistItem.getArticle().getId() == articleId && wishlistItem.getSize() == size
					&& wishlistItem.getColor().equals(color)) {
				list.get(i).setColor(colorNew);
				list.get(i).setSize(sizeNew);
			}
		}
		removeDublicates();
	}

	private void removeDublicates() {
		List<WishlistItem> duplicates = new ArrayList<WishlistItem>();
		for (int i = 0; i < list.size(); i++) {
			WishlistItem item = list.get(i);
			boolean found = false;
			for (int j = 0; j < duplicates.size(); j++) {

				WishlistItem duplicate = duplicates.get(j);
				if (duplicate.getArticle().getId() == item.getId() && duplicate.getSize() == item.getSize()
						&& duplicate.getColor().equals(item.getColor())) {
					found = true;
				}
			}
			if (!found) {
				for (int k = i + 1; k < list.size(); k++) {
					WishlistItem posDuplicate = list.get(k);
					if (posDuplicate.getArticle().getId() == item.getId() && posDuplicate.getSize() == item.getSize()
							&& posDuplicate.getColor().equals(item.getColor())) {
						duplicates.add(posDuplicate);
					}
				}
			}
		}
		for (WishlistItem wishlistItem : duplicates) {
			list.remove(wishlistItem);
		}
	}
}
