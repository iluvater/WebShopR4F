package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.ShoppingBasket;

/**
 * Servlet implementation class RemoveArticleFromShoppingBasket
 */
@WebServlet("/RemoveArticleFromShoppingBasket")
public class RemoveArticleFromShoppingBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveArticleFromShoppingBasket() {
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
		int articleId;
		int size;
		String color;
		RequestDispatcher dispatcher;
		String successURL = "WarenkorbVersuch2.jsp";
		String errorURL = "Login.jsp";
		ShoppingBasket shoppingBasket;

		try {
			articleId = Integer.parseInt(request.getParameter("articleId"));
			size = Integer.parseInt(request.getParameter("size"));
			color = request.getParameter("color");
		} catch (NumberFormatException e) {
			throw new ServletException();
		}
		shoppingBasket = (ShoppingBasket) request.getSession().getAttribute("shoppingBasket");
		
		if (shoppingBasket == null) {
			// Errorhandling not logged in
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		} else {			
				shoppingBasket.removeItem(articleId, size, color);			
		}
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
