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
		String successURL = "Merkzettel.jsp";
		String errorURL = "Benutzerauthentifizierung.jsp";
		String overviewURL = "./NavigationOverviewServlet";
		Wishlist wishlist;
		Article article;

		try {
			articleId = Integer.parseInt(request.getParameter("articleId"));
			article = articleService.getArticle(articleId);

		} catch (NumberFormatException e) {
			article = (Article)request.getSession().getAttribute("articleForWishlist");
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
				wishlist.addItem(article);
				request.getSession().removeAttribute("articleForWishlist");
			}			
		}
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
