package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.HashService;
import r4f.controller.services.ShoppingBasketService;
import r4f.controller.services.UserService;
import r4f.controller.services.WishlistService;
import r4f.model.Article;
import r4f.model.ErrorMessage;
import r4f.model.ShoppingBasket;
import r4f.model.User;
import r4f.model.Wishlist;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email;
		String password;
		User user;
		ShoppingBasket shoppingBasket;
		Wishlist wishlist;
		UserService userService;
		ShoppingBasketService shoppingBasketService;
		WishlistService wishlistService;
		HashService hashService;
		RequestDispatcher dispatcher;
		Article article;

		String errorURL = "Login.jsp";
		String successURL = "./NavigationOverviewSerlvet.jsp";
		String shoppingBasketURL = "./AddToShoppingBasketServlet";
		String wishlistURL = "./AddToWishlistServlet";

		email = request.getParameter("email");
		password = request.getParameter("password");

		userService = new UserService();
		shoppingBasketService = new ShoppingBasketService();
		hashService = new HashService();
		wishlistService = new WishlistService();
		
		password = hashService.encrypt(password);

		if(userService.checkLogin(email, password)){
			user = userService.getUser(email);
			shoppingBasket = shoppingBasketService.getShoppingBasket(user.getShoppingBasket());
			wishlist = wishlistService.getWishlist(user.getWishlist());
			
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("shoppingBasket", shoppingBasket);
			request.getSession().setAttribute("wishlist", wishlist);
			
			article = (Article) request.getSession().getAttribute("articleForShoppingBasket");
			if(article == null){
				article = (Article) request.getSession().getAttribute("articleForWishlist");
				if(article == null){
					dispatcher = request.getRequestDispatcher(successURL);
					dispatcher.forward(request, response);
					return;
				}else{
					dispatcher = request.getRequestDispatcher(wishlistURL);
					dispatcher.forward(request, response);
					return;
				}				
			}else{
				dispatcher = request.getRequestDispatcher(shoppingBasketURL);
				dispatcher.forward(request, response);
				return;
			}
			
		}else{
			ErrorMessage errorMessage = new ErrorMessage(114);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}

	}

}
