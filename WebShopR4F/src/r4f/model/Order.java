/**
 * 
 */
package r4f.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ture
 *
 */
public class Order {
	private int id;
	private Date entryDate;
	private User user;
	private Address deliveryAddress;
	private Address billingAddress;
	private String paymentMethod;
	private List<OrderItem> items;
	
	/**
	 * Constructor that can be used if the order is already stored in the database
	 * @param id the id to set
	 * @param entryDate the entryDate to set
	 * @param deliveryAddress the deliveryAddress to set
	 * @param billingAddress the billingAddress to set
	 * @param paymentMethod the paymentMethod to set
	 * @param items the order items to set
	 */
	public Order(int id, Date entryDate, Address deliveryAddress, Address billingAddress, String paymentMethod, List<OrderItem> items){
		this.id = id;
		this.entryDate = entryDate;
		this.deliveryAddress = deliveryAddress;
		this.billingAddress = billingAddress;
		this.paymentMethod = paymentMethod;
		this.items = items;
	}
	
	/**
	 * Constructor that is needed for being a java bean
	 */
	public Order() {
		entryDate = null;
		user = null;
		deliveryAddress = null;
		billingAddress = null;
		paymentMethod = null;
		items = new ArrayList<OrderItem>();
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
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the deliveryAddress
	 */
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * @param deliveryAddress
	 *            the deliveryAddress to set
	 */
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the billingAddress
	 */
	public Address getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress
	 *            the billingAddress to set
	 */
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod
	 *            the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * This method adds an Article to the order
	 * @param article the article to set
	 * @param amount the amount of the article
	 */
	public void addOrderItem(Article article, int amount){
		OrderItem item = new OrderItem(getLastPosition()+1, amount, article.getPrice(), article);
		items.add(item);
	}
	
	/**
	 * This method adds all items of a shoppingBasket to the order
	 * @param shoppingBasket the shoppingBasket that should be add to the order
	 */
	public void addShoppingBasket(ShoppingBasket shoppingBasket){
		for (ShoppingBasketItem item : shoppingBasket.getItems()) {
			addOrderItem(item.getArticle(), item.getAmount());
		}
	}
	
	/**
	 * This method returns the last position number of the order
	 * @return the last Position
	 */
	private int getLastPosition(){
		int max = items.get(0).getPosition();
		for (OrderItem orderItem : items) {
			if(max< orderItem.getPosition()){
				max = orderItem.getPosition();
			}
		}
		return max;
	}
	
	/**
	 * 
	 * @param items items to set
	 */
	public void setItems(List<OrderItem> items){
		this.items = items;
	}
	
	/**
	 * 
	 * @return the items
	 */
	public List<OrderItem> getItems(){
		return this.items;
	}
	
	/**
	 * This method checks whether a string is a valid paymentMethod or not
	 * @param paymentMethod the string to check
	 * @return returns true if the string is a payment methods
	 */
	public static boolean checkPaymentMethod(String paymentMethod){
		if(paymentMethod.equals("Vorkasse") || paymentMethod.equals("Nachnahme")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This method resets the positions of the order
	 */
	public void resetPositions() {
		for (int i = 0; i < items.size(); i++) {
			OrderItem item = items.get(i);
			item.setPosition(i);
		}
	}
}