/**
 * 
 */
package r4f.model;

/**
 * @author Ture
 *
 */
public class ShoppingBasketItem {

	private int amount;
	private Article article;
	private int id;
	private int size;
	private String color;

	/**
	 * Constructor for using a shopping basket item that is not created in the database, but has values
	 * @param amount the amount to set
	 * @param article the article to set
	 * @param size the size to set
	 * @param color color to set
	 */
	public ShoppingBasketItem(int amount, Article article, int size, String color) {
		this.amount = amount;
		this.article = article;
		this.size= size;
		this.color = color;
	}
	/**
	 * Constructor for using a shopping basket item that is not created in the database
	 */
	public ShoppingBasketItem(){
		article = null;
		color = null;
	}

	/**
	 * constructor for usinng a shopping basket item that is created in the database
	 * @param id id to set
	 * @param amount amount to set
	 * @param article article to set
	 * @param size the size to set
	 * @param color color to set
	 */
	public ShoppingBasketItem(int id, int amount, Article article, int size, String color) {
		this.id=id;
		this.amount = amount;
		this.article = article;
		this.size= size;
		this.color = color;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article
	 *            the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
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
	 * This method return the price of this item
	 * @return the items price rounded at two digits
	 */
	public double getItemPrice(){
		return ((double)Math.round(amount * article.getPrice() * 100)) / 100.0 ;
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

}
