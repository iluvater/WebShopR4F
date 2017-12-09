package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.EmailService;
import r4f.controller.services.OrderService;
import r4f.model.ErrorMessage;
import r4f.model.Order;
import r4f.model.ShoppingBasket;

/**
 * Servlet implementation class OrderOverViewToDBServlet
 */
@WebServlet("/OrderOverViewToDBServlet")
public class OrderOverViewToDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderOverViewToDBServlet() {
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
		String successURL = "Bestellabschluss.jsp";
		String errorURL = "Bestelluebersicht.jsp";
		RequestDispatcher dispatcher;
		OrderService orderService = new OrderService();
		EmailService emailService = new EmailService();
		
		ShoppingBasket shoppingBasket;
		Order order;
		
		int orderId;
		
		order = (Order) request.getSession().getAttribute("order");
		orderId = orderService.createOrderInDB(order);
		
		if(orderId!=-1){
			//clear shopping basket
			shoppingBasket = (ShoppingBasket) request.getSession().getAttribute("shoppingBasket");
			shoppingBasket.getItems().clear();
			request.getSession().setAttribute("shoppingBasket", shoppingBasket);
			
			//remove order object from session
			request.getSession().removeAttribute("order");
			
			//send Confirmationmail
			
			//successMessage something went from wrong during creation
			ErrorMessage successMessage = new ErrorMessage(601);
			successMessage.setErrorMessage(successMessage.getErrorMessage().replaceAll("!orderId", Integer.toString(order.getId())));
			request.setAttribute("success", successMessage);
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
			
						
		}else{
			//Errorhandling something went from wrong during creation
			ErrorMessage errorMessage = new ErrorMessage(131);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		
	}

}
