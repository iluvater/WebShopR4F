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
import r4f.model.Wishlist;

/**
 * Servlet implementation class AddToWishlistServlet
 */
@WebServlet("/AddToWishlistServlet")
public class AddToWishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToWishlistServlet() {
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
		RequestDispatcher dispatcher;
		ArticleService articleService = new ArticleService();
		String successURL = "Merkliste.jsp";
		String errorURL = "Login.jsp";
		String overviewURL = "./NavigationOverviewServlet";
		Wishlist wishlist;
		String size;
		String color;
		Article article;

		try {
			articleId = Integer.parseInt(request.getParameter("articleId"));
			article = articleService.getArticle(articleId);

		} catch (NumberFormatException e) {
			article = (Article) request.getSession().getAttribute("articleForWishlist");

		}

		Object s = request.getSession().getAttribute("size");
		Object c = (String) request.getSession().getAttribute("color");
		request.getSession().removeAttribute("size");
		request.getSession().removeAttribute("color");
		
		if(c== null || s == null){
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
		}else{
			size = Integer.toString((Integer) s);
			color = (String) c;
		}
		wishlist = (Wishlist) request.getSession().getAttribute("wishlist");
		
		if (wishlist == null) {
			// Errorhandling not logged in
			request.getSession().setAttribute("articleForWishlist", article);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		} else {
			if(article == null){
				dispatcher = request.getRequestDispatcher(overviewURL);
				dispatcher.forward(request, response);
				return;
			}else{
				wishlist.addItem(article, Integer.parseInt(size), color);
				request.getSession().removeAttribute("articleForWishlist");
			}			
		}
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
