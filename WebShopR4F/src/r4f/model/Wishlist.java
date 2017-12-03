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
	private List<Article> list;
	
	/**
	 * This constructor can be used for a wish list that is not stored in the database
	 */
	public Wishlist(){
		list = new ArrayList<Article>();
	}
	
	/**
	 * This constructor can be used for a wish list that has an id
	 * @param id the id to set
	 */
	public Wishlist(int id){
		this.id = id;
		list = new ArrayList<Article>();
	}
	
	/**
	 * Constructor for a wish list that is already stored in the database
	 * @param id
	 * @param list
	 */
	public Wishlist(int id, List<Article> list){
		this.id = id;
		this.list = list;
	}

	/**
	 * @return the list
	 */
	public List<Article> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Article> list) {
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
	public void addItem(Article article){
		boolean found = false;
		for (Article item : list) {
			if(item.getId() == article.getId()){
				found = true;
			}
		}
		if(!found){
			list.add(article);
		}
	}
	/**
	 * This method removes all articles from the wishlist
	 */
	public void removeAllItems(){
		list.clear();
	}
	
	/**
	 * This method removes an article from the database
	 * @param articleId the id of the article that should be removed
	 */
	public void removeItem(int articleId){
		List<Article> removeArticles = new ArrayList<Article>();
		for (Article item : list) {
			if(item.getId() == articleId){
				removeArticles.add(item);
			}
		}
		for (Article item : removeArticles) {
			list.remove(item);
		}
	}
}
