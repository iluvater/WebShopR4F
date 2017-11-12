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

	/**
	 * Constructor for using a shopping basket item that is not created in the database, but has values
	 * @param amount
	 * @param article
	 */
	public ShoppingBasketItem(int amount, Article article) {
		this.amount = amount;
		this.article = article;
	}
	/**
	 * Constructor for using a shopping basket item that is not created in the database
	 */
	public ShoppingBasketItem(){
		article = null;
	}

	/**
	 * constructor for usinng a shopping basket item that is created in the database
	 * @param id id to set
	 * @param amount amount to set
	 * @param article article to set
	 */
	public ShoppingBasketItem(int id, int amount, Article article) {
		this.id=id;
		this.amount = amount;
		this.article = article;
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

}
