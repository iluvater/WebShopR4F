package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.filter.CheckboxStatus;
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
		String successURL = "Artikeldaten.jsp";
		String errorURL = "Artikelsuche.jsp";
		RequestDispatcher dispatcher;
		ArticleService articleService = new ArticleService();
		Article article;
		
		try{
			articleId = Integer.parseInt(request.getParameter("id"));
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
				CheckboxStatus checkboxStatus = new CheckboxStatus();
				for (String item : article.getColor()) {
					switch (item) {
					case "gelb":
						checkboxStatus.setColor1("checked");
						break;
					case "orange":
						checkboxStatus.setColor2("checked");
						break;
					case "rot":
						checkboxStatus.setColor3("checked");
						break;
					case "pink":
						checkboxStatus.setColor4("checked");
						break;
					case "grün":
						checkboxStatus.setColor5("checked");
						break;
					case "blau":
						checkboxStatus.setColor6("checked");
						break;
					case "schwarz":
						checkboxStatus.setColor7("checked");
						break;
					case "weiß":
						checkboxStatus.setColor8("checked");
						break;
					}
				}
				for (int item : article.getSize()) {
					switch (item) {
					case 36:
						checkboxStatus.setSize1("checked");
						break;
					case 37:
						checkboxStatus.setSize2("checked");
						break;
					case 38:
						checkboxStatus.setSize3("checked");
						break;
					case 39:
						checkboxStatus.setSize4("checked");
						break;
					case 40:
						checkboxStatus.setSize5("checked");
						break;
					case 41:
						checkboxStatus.setSize6("checked");
						break;
					case 42:
						checkboxStatus.setSize7("checked");
						break;
					case 43:
						checkboxStatus.setSize8("checked");
						break;
					case 44:
						checkboxStatus.setSize9("checked");
						break;
					case 45:
						checkboxStatus.setSize10("checked");
						break;
					}
				}
				request.setAttribute("filter", checkboxStatus);
				
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
