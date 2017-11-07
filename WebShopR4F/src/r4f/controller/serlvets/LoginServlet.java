package r4f.controller.serlvets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.User;
import r4f.controller.services.LoginService;
import r4f.controller.services.ShoppingBasketService;
import r4f.model.DatabaseConnection;
import r4f.model.ErrorMessage;
import r4f.model.ShoppingBasket;

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
		LoginService loginService;
		ShoppingBasketService shoppingBasketService;
		RequestDispatcher dispatcher;
		
		String errorURL = "Benutzerauthentifizierung.jsp";
		String successURL = "Willkommen.jsp";

		email = request.getParameter("email");
		password = request.getParameter("password");
		
		loginService = new LoginService();
		shoppingBasketService = new ShoppingBasketService();
		
		if(loginService.checkLogin(email, password)){
			user = loginService.getBenutzer(email);
			shoppingBasket = shoppingBasketService.getShoppingBasket(user.getId());
			
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("shoppingBasket", shoppingBasket);
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
		}else{
			ErrorMessage errorMessage = new ErrorMessage(114);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}

	}

}
