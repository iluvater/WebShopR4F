/**
 * 
 */
package r4f.controller.services;

import r4f.model.Address;
import r4f.model.Order;
import r4f.model.OrderItem;

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
					deliveryAddress.getStreet(), deliveryAddress.getHouseNumber(), deliveryAddress.getPostCode(),
					deliveryAddress.getCity(), false);
			order.getDeliveryAddress().setId(deliveryAddressId);
		}
		int orderId = super.getDbConnection().createOrderInDB(order);
		order.resetPositions();
		for (OrderItem item : order.getItems()) {
			super.getDbConnection().createOrderItemInDB(orderId, item);
		}
		return orderId;
	}

}
