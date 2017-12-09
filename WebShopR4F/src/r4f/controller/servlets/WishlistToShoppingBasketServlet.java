package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.ShoppingBasket;
import r4f.model.User;
import r4f.model.Wishlist;

/**
 * Servlet implementation class WishlistToShoppingBasketServlet
 */
@WebServlet("/WishlistToShoppingBasketServlet")
public class WishlistToShoppingBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishlistToShoppingBasketServlet() {
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
		String successURL = "WarenkorbVersuch2.jsp";
		RequestDispatcher dispatcher;
		
		Wishlist wishlist = null;
		ShoppingBasket shoppingBasket;
		User user = null;;
		
		wishlist = (Wishlist)request.getSession().getAttribute("wishlist");
		user = (User)request.getSession().getAttribute("user");
		shoppingBasket = (ShoppingBasket) request.getSession().getAttribute("shoppingBasket");
		
		if(user == null || wishlist == null || shoppingBasket == null){
			// Errorhandling not logged in
			ServletException e = new ServletException();
			throw e;
		}else{
			shoppingBasket.addWishlist(wishlist);
			request.getSession().removeAttribute("shoppingBasket");
			request.getSession().setAttribute("shoppingBasket", shoppingBasket);
			
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
		}
	}

}
