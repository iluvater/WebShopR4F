/**
 * 
 */
package r4f.controller.services;

import java.util.List;

import r4f.model.Address;
import r4f.model.Order;
import r4f.model.OrderItem;
import r4f.model.User;

/**
 * @author Ture
 *
 */
public class OrderService extends Service {

	/**
	 * This method creates an order in the database
	 * 
	 * @param order
	 *            the order that should be created
	 * @return the id of the created order
	 */
	public int createOrderInDB(Order order) {
		if (order.getDeliveryAddress().getId() == -1) {
			Address deliveryAddress = order.getDeliveryAddress();
			int deliveryAddressId = super.getDbConnection().createAddressInDB(order.getUser().getId(),
					deliveryAddress.getFirstName(), deliveryAddress.getLastName(), deliveryAddress.getStreet(),
					deliveryAddress.getHouseNumber(), deliveryAddress.getPostCode(), deliveryAddress.getCity(), false, deliveryAddress.getSalutation());
			order.getDeliveryAddress().setId(deliveryAddressId);
		}
		int orderId = super.getDbConnection().createOrderInDB(order);
		order.resetPositions();
		for (OrderItem item : order.getItems()) {
			super.getDbConnection().createOrderItemInDB(orderId, item);
		}
		return orderId;
	}

	/**
	 * This method selects all order for a certain user
	 * @param user the user
	 * @return returns a list of all orders from the user
	 */
	public List<Order> getOrderList(User user) {
		return super.getDbConnection().getOrderList(user.getId());
	}
	
	/**
	 * This method selects an order from the database
	 * @param orderId the id of the order that should be selected
	 * @return returns the order or null if no order was selected
	 */
	public Order getOrder(int orderId){
		return super.getDbConnection().getOrder(orderId);
	}

}
