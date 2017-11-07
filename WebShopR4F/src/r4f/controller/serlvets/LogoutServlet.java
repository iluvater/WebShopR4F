package r4f.controller.serlvets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.ShoppingBasketService;
import r4f.model.ShoppingBasket;
import r4f.model.User;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		String successURL = "Willkommen.jsp";
		ShoppingBasketService shoppingBasketService = new ShoppingBasketService();
		
		ShoppingBasket shoppingBasket = (ShoppingBasket) request.getSession().getAttribute("shoppingBasket");
		
		shoppingBasketService.updateShoppingBasketInDB(shoppingBasket);
				
				
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("shoppingBasket");
		
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		
		
	}

}
