package r4f.controller.serlvets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.ArticleService;
import r4f.model.Article;

/**
 * Servlet implementation class NavigationArticleDetailsServlet
 */
@WebServlet("/NavigationArticleDetailsServlet")
public class NavigationArticleDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationArticleDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String successURL = "Artikeldetails.jsp";
		RequestDispatcher dispatcher;
		Article article;
		try{
			int id = Integer.parseInt(request.getParameter("article"));
			
			ArticleService articleService = new ArticleService();
			article = articleService.getArticle(id);
		}catch(Exception e){
			article = null;
		}
		
		
		request.setAttribute("article", article);
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		
	}

}
