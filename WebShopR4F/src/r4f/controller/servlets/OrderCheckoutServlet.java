package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.Order;
import r4f.model.ShoppingBasket;
import r4f.model.User;

/**
 * Servlet implementation class OrderCheckoutServlet
 */
@WebServlet("/OrderCheckoutServlet")
public class OrderCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String successURL = "Bestellung.jsp";
		RequestDispatcher dispatcher;
		
		ShoppingBasket shoppingBasket = null;
		Order order;
		User user = null;;
		
		shoppingBasket = (ShoppingBasket)request.getAttribute("shoppingBasket");
		user = (User)request.getAttribute("User");
		
		if(user == null || shoppingBasket == null){
			// Errorhandling not logged in
			return;
		}else{
			order = new Order();
			order.setUser(user);
			order.addShoppingBasket(shoppingBasket);
			
			request.getSession().setAttribute("order", order);
			
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
		}
		
		
	}

}
