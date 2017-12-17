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
 * @author Ture
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
		int size, sizeNew;
		String color, colorNew;
		RequestDispatcher dispatcher;
		String successURL = "Warenkorb.jsp";
		String errorURL = "Login.jsp";
		ShoppingBasket shoppingBasket;

		try {
			articleId = Integer.parseInt(request.getParameter("articleId"));
			size = Integer.parseInt(request.getParameter("size"));
			sizeNew = Integer.parseInt(request.getParameter("sizeNew"));
			colorNew = request.getParameter("colorNew");
			amount = Integer.parseInt(request.getParameter("amount"));
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
				shoppingBasket.setAmountOfArticle(articleId, amount, size, color);		
				shoppingBasket.setSizeColorOfArticle(articleId, size, color, sizeNew, colorNew);
		}
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
