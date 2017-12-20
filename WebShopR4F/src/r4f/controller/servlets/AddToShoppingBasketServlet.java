package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.ArticleService;
import r4f.model.Article;
import r4f.model.ShoppingBasket;

/**
 * Servlet implementation class AddToShoppingBasketServlet
 * @author Ture
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
		// RequestDispatcher dispatcher;
		// ArticleService articleService = new ArticleService();
		// String successURL = "Warenkorb.jsp";
		// String loginURL = "Benutzerauthentifizierung.jsp";
		// String overviewURL = "./NavigationOverviewServlet";
		// ShoppingBasket shoppingBasket;
		// Article article;
		//
		// try {
		//
		// shoppingBasket =
		// (ShoppingBasket)request.getSession().getAttribute("shoppingBasket");
		// article =
		// (Article)request.getSession().getAttribute("articleForShoppingBasket");
		//
		// if(shoppingBasket == null){
		// if(article == null){
		// //Errorhandling no article
		// dispatcher = request.getRequestDispatcher(overviewURL);
		// dispatcher.forward(request, response);
		// return;
		// }else{
		// //Errorhandling not logged in
		// request.getSession().setAttribute("articleForShoppingBasket",
		// article);
		// dispatcher = request.getRequestDispatcher(loginURL);
		// dispatcher.forward(request, response);
		// return;
		// }
		// }else{
		// shoppingBasket.addItem(article);
		// request.getSession().removeAttribute("articleForShoppingBasket");
		// }
		// } catch (NumberFormatException e) {
		//
		// }
		//
		// dispatcher = request.getRequestDispatcher(successURL);
		// dispatcher.forward(request, response);
		// return;
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
		String successURL = "Warenkorb.jsp";
		String errorURL = "Login.jsp";
		String overviewURL = "./NavigationOverviewServlet";
		ShoppingBasket shoppingBasket;
		String size;
		String color;
		Article article;

		try {
			articleId = Integer.parseInt(request.getParameter("articleId"));
			article = articleService.getArticle(articleId);

		} catch (NumberFormatException e) {
			article = (Article) request.getSession().getAttribute("articleForShoppingBasket");

		}

		size = (String) request.getSession().getAttribute("size");
		color = (String) request.getSession().getAttribute("color");
		request.getSession().removeAttribute("size");
		request.getSession().removeAttribute("color");
		
		if(color == null || color.equals("") || size== null || size.equals("")){
			if(!article.getColor().isEmpty()){
				color = article.getColor().get(0);
			}else{
				throw new ServletException();
			}
			if(!article.getSize().isEmpty()){
				size = Integer.toString(article.getSize().get(0));
			}else{
				throw new ServletException();
			}
		}
		
		

		shoppingBasket = (ShoppingBasket) request.getSession().getAttribute("shoppingBasket");

		if (shoppingBasket == null) {
			// Errorhandling not logged in
			request.getSession().setAttribute("articleForShoppingBasket", article);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		} else {
			if (article == null || size == null || color == null) {
				dispatcher = request.getRequestDispatcher(overviewURL);
				dispatcher.forward(request, response);
				return;
			} else {
				shoppingBasket.addItem(article, Integer.parseInt(size), color);
				request.getSession().removeAttribute("articleForShoppingBasket");
			}
		}
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
