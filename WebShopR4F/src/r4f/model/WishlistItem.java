/**
 * 
 */
package r4f.model;

/**
 * @author Ture
 *
 */
public class WishlistItem {
	private int id;
	private int size;
	private String color;
	private Article article;
	
	/**
	 * constructor for an wishlist item that is already stored in the database
	 * @param id id to set
	 * @param size size to set
	 * @param color color to set
	 * @param article article to set
	 */
	public WishlistItem(int id, int size, String color, Article article){
		this.id = id;
		this.size = size;
		this.color = color;
		this.article = article;
	}
	
	/**
	 * Constructor for an item that is not stored in the database yet
	 * @param size size to set
	 * @param color color to set
	 * @param article article to set
	 */
	public WishlistItem(int size, String color, Article article){
		this.size = size;
		this.color = color;
		this.article = article;
	}
	
	public WishlistItem(){
		this.article = null;
		this.color = null;
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
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}
	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}
	
}
