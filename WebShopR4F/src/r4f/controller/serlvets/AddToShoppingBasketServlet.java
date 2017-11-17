package r4f.controller.serlvets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.ArticleService;
import r4f.controller.services.ShoppingBasketService;
import r4f.model.Article;
import r4f.model.ShoppingBasket;

/**
 * Servlet implementation class AddToShoppingBasketServlet
 */
@WebServlet("/AddToShoppingBasketServlet")
public class AddToShoppingBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToShoppingBasketServlet() {
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
		int articleId;
		RequestDispatcher dispatcher;
		ArticleService articleService = new ArticleService();
		String successURL = "WarenkorbVersuch2.jsp";
		ShoppingBasket shoppingBasket;
		Article article;
		
		try {
			articleId = Integer.parseInt(request.getParameter("articleId"));
			shoppingBasket = (ShoppingBasket)request.getSession().getAttribute("shoppingBasket");
			
			if(shoppingBasket == null){
				//Errorhandling not logged in
			}else{
				article = articleService.getArticle(articleId);
				shoppingBasket.addItem(article);	
			}					
		} catch (NumberFormatException e) {
			
		}
		
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
