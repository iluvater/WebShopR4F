package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.AddressService;
import r4f.model.Address;
import r4f.model.ErrorMessage;
import r4f.model.Order;
import r4f.model.User;

/**
 * Servlet implementation class OrderInputToOverviewServlet
 */
@WebServlet("/OrderInputToOverviewServlet")
public class OrderInputToOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderInputToOverviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String successURL = "Bestelluebersicht.jsp";
		String errorURL = "Bestellung.jsp";
		String matchingAddresses;
		String paymentMethod;
		RequestDispatcher dispatcher;
		AddressService addressService;
		
		Order order = null;
		User user = null;
		
		order = (Order) request.getSession().getAttribute("order");
		user = (User) request.getSession().getAttribute("user");
		
		matchingAddresses = request.getParameter("matchingAddresses");
		paymentMethod = request.getParameter("paymentMethod");
		
		if(matchingAddresses != null && !matchingAddresses.equals("matching")){
			addressService= new AddressService();			
			Address address = addressService.getAddress(user);
			
			order.setBillingAddress(address);
			order.setDeliveryAddress(address);
//			
//			request.getSession().setAttribute("order", order);
//			
//			dispatcher = request.getRequestDispatcher(successURL);
//			dispatcher.forward(request, response);
//			return;
		}else{
			//deliveryAddress does not match billingAddress
			String street;
			String houseNumber;
			String postCode;
			String city;
			
			street = request.getParameter("street");
			houseNumber = request.getParameter("houseNumber");
			postCode = request.getParameter("postCode");
			city = request.getParameter("city");
			
			if(street != null && !street.equals("")){
				if(houseNumber != null && !houseNumber.equals("")){
					if(postCode != null && !postCode.equals("")){
						if(city != null && !city.equals("")){
							addressService = new AddressService();
							Address deliveryAddress = new Address(street, houseNumber, postCode, city);
							Address billingAddress = addressService.getAddress(user);
							
							order.setBillingAddress(billingAddress);
							order.setDeliveryAddress(deliveryAddress);
							
//							request.getSession().setAttribute("order", order);
//							
//							dispatcher = request.getRequestDispatcher(successURL);
//							dispatcher.forward(request, response);
//							return;
						}else{
							//Errorhandling missing input
							ErrorMessage errorMessage = new ErrorMessage(112);
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						}
					}else{
						//Errorhandling missing input
						ErrorMessage errorMessage = new ErrorMessage(105);
						request.setAttribute("error", errorMessage);
						dispatcher = request.getRequestDispatcher(errorURL);
						dispatcher.forward(request, response);
						return;
					}
				}else{
					//Errorhandling missing input
					ErrorMessage errorMessage = new ErrorMessage(111);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			}else{
				//Errorhandling missing input
				ErrorMessage errorMessage = new ErrorMessage(110);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		}
		
		if(paymentMethod != null && !paymentMethod.equals("") && Order.checkPaymentMethod(paymentMethod)){
			order.setPaymentMethod(paymentMethod);
			request.getSession().setAttribute("order", order);
			
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
			
			
		}else{
			//Errorhandling missing input
			ErrorMessage errorMessage = new ErrorMessage(130);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		
	}

}
