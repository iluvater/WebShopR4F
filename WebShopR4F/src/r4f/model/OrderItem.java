/**
 * 
 */
package r4f.model;

/**
 * @author Ture
 *
 */
public class OrderItem {
	private int position;
	private int amount;
	private double price;
	private Article article;
	private int size;
	private String color;
	
	
	/**
	 * Constructor for the regular use
	 * @param position the position to set
	 * @param amount the amount to set
	 * @param price the price to set
	 * @param the article to set
	 * @param size the size to set
	 * @param color color to set
	 */
	public OrderItem(int position, int amount, double price, Article article, int size, String color){
		this.position = position;
		this.amount = amount;
		this.article = article;
		this.price = price;
		this.size = size;
		this.color = color;
	}
	
	/**
	 * Constructor that is needed for being a java bean
	 */
	public OrderItem(){
		article = null;
		color = null;
	}
	
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
