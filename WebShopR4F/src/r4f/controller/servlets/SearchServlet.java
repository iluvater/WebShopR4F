package r4f.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.filter.FilterList;
import r4f.controller.services.ArticleService;
import r4f.model.Article;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String successURL = "Uebersichtsseite.jsp";

		RequestDispatcher dispatcher;
		FilterList filterList = null;
		String searchPattern = request.getParameter("search");

		request.getSession().removeAttribute("filterList");
		request.getSession().removeAttribute("filter");
		if (filterList == null) {
			filterList = new FilterList();
		}

		ArticleService articleService = new ArticleService();
		List<Article> articles = articleService.getArticleListSearch(searchPattern);
		request.setAttribute("articleList", articles);

		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
