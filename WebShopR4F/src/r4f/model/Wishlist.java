/**
 * 
 */
package r4f.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ture
 *	This class represents a wishlist
 */
public class Wishlist {
	private int id;
	private List<WishlistItem> list;
	
	/**
	 * This constructor can be used for a wish list that is not stored in the database
	 */
	public Wishlist(){
		list = new ArrayList<WishlistItem>();
	}
	
	/**
	 * This constructor can be used for a wish list that has an id
	 * @param id the id to set
	 */
	public Wishlist(int id){
		this.id = id;
		list = new ArrayList<WishlistItem>();
	}
	
	/**
	 * Constructor for a wish list that is already stored in the database
	 * @param id
	 * @param list
	 */
	public Wishlist(int id, List<WishlistItem> list){
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
	 * @param list the list to set
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
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This method adds an article to the wish list if the wish list already contains the article nothing will happen
	 * @param articleId the article that should be added
	 */
	public void addItem(Article article, int size, String color){
		boolean found = false;
		for (WishlistItem item : list) {
			if(item.getArticle().getId() == article.getId()){
				found = true;
			}
		}
		if(!found){
			list.add(new WishlistItem(size, color, article));
		}
	}
	
	/**
	 * This method adds an article to the wish list if the wish list already contains the article nothing will happen
	 * @param wishlistItem the item that should be added
	 */
	public void addItem(WishlistItem wishlistItem){
		boolean found = false;
		for (WishlistItem item : list) {
			if(item.getId() == wishlistItem.getId()){
				found = true;
			}
		}
		if(!found){
			list.add(wishlistItem);
		}
	}
	
	
	/**
	 * This method removes all articles from the wishlist
	 */
	public void removeAllItems(){
		list.clear();
	}
	
	/**
	 * This method removes an article from the wishlist
	 * @param articleId the id of the article that should be removed
	 */
	public void removeItem(int articleId){
		List<WishlistItem> removeArticles = new ArrayList<WishlistItem>();
		for (WishlistItem item : list) {
			if(item.getArticle().getId() == articleId){
				removeArticles.add(item);
			}
		}
		for (WishlistItem item : removeArticles) {
			list.remove(item);
		}
	}
}
