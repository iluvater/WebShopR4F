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
import r4f.model.ErrorMessage;

/**
 * Servlet implementation class SearchArticleForChangeServlet
 */
@WebServlet("/SearchArticleForChangeServlet")
public class SearchArticleForChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchArticleForChangeServlet() {
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
		int articleId = -1;
		String successURL = "";
		String errorURL = "";
		RequestDispatcher dispatcher;
		ArticleService articleService = new ArticleService();
		Article article;
		
		try{
			articleId = Integer.parseInt(request.getParameter("articleId"));
		}catch(NumberFormatException e){
			//Error wrong input format
			ErrorMessage errorMessage = new ErrorMessage(127);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		
		if(articleId != -1){
			article = articleService.getArticle(articleId);
			if(article != null){
				request.setAttribute("article", article);
				dispatcher = request.getRequestDispatcher(successURL);
				dispatcher.forward(request, response);
				return;
			}else{
				//Error missing input
				ErrorMessage errorMessage = new ErrorMessage(128);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		} else{
			//Error missing input
			ErrorMessage errorMessage = new ErrorMessage(127);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}		
	}

}
