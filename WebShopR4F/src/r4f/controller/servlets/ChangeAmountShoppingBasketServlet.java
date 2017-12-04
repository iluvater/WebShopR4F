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
 * Servlet implementation class ChangeAmountShoppingBasketServlet
 */
@WebServlet("/ChangeAmountShoppingBasketServlet")
public class ChangeAmountShoppingBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAmountShoppingBasketServlet() {
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
		int amount;
		int articleId;
		RequestDispatcher dispatcher;
		String successURL = "WarenkorbVersuch2.jsp";
		String errorURL = "Benutzerauthentifizierung.jsp";
		ShoppingBasket shoppingBasket;

		try {
			articleId = Integer.parseInt(request.getParameter("articleId"));
			amount = Integer.parseInt(request.getParameter("amount"));
		} catch (NumberFormatException e) {
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		shoppingBasket = (ShoppingBasket) request.getSession().getAttribute("shoppingBasket");
		
		if (shoppingBasket == null) {
			// Errorhandling not logged in
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		} else {			
				shoppingBasket.setAmountOfArticle(articleId, amount);			
		}
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
