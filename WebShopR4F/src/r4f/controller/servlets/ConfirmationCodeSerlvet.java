package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.ShoppingBasketService;
import r4f.controller.services.UserService;
import r4f.controller.services.WishlistService;
import r4f.model.ErrorMessage;
import r4f.model.ShoppingBasket;
import r4f.model.User;
import r4f.model.Wishlist;

/**
 * Servlet implementation class ConfirmationCodeSerlvet
 */
@WebServlet("/ConfirmationCodeSerlvet")
public class ConfirmationCodeSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmationCodeSerlvet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService;
		WishlistService wishlistService;
		ShoppingBasketService shoppingBasketService;
		RequestDispatcher dispatcher;
		User user;
		Wishlist wishlist;
		ShoppingBasket shoppingBasket;
		String registrierungsURL = "Login.jsp";
		String errorURL = "RegistrierungAbschliesen.jsp";
		String successURL = "./NavigationOverviewServlet";
		String code;

		code = request.getParameter("code");

		if (code != null && !code.equals("")) {
			user = (User) request.getSession().getAttribute("user");
			if (user!=null) {
				if (code.equals(user.getConfirmationCode())) {
					userService = new UserService();
					wishlistService = new WishlistService();
					shoppingBasketService = new ShoppingBasketService();
					user = userService.createBenutzerInDB(user);
					wishlist = wishlistService.getWishlist(user.getWishlist());
					shoppingBasket = shoppingBasketService.getShoppingBasket(user.getShoppingBasket());
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("wishlist", wishlist);
					request.getSession().setAttribute("shoppingBasket", shoppingBasket);
					dispatcher = request.getRequestDispatcher(successURL);
					dispatcher.forward(request, response);
					return;
				} else {
					ErrorMessage errorMessage = new ErrorMessage(137);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			} else {
				ErrorMessage errorMessage = new ErrorMessage(102);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		} else {
			ErrorMessage errorMessage = new ErrorMessage(136);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(registrierungsURL);
			dispatcher.forward(request, response);
			return;
		}

	}

}
