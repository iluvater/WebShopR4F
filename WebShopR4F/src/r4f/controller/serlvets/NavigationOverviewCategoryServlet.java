package r4f.controller.serlvets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.filter.FilterCategory;
import r4f.controller.filter.FilterList;
import r4f.controller.services.ArticleService;

/**
 * Servlet implementation class NavigationOverviewCategoryServlet
 */
@WebServlet("/NavigationOverviewCategoryServlet")
public class NavigationOverviewCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationOverviewCategoryServlet() {
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
		String successURL = "Uebersichtsseite.jsp";
		String category = null;
		
		RequestDispatcher dispatcher;
		FilterList filterList = null; 
		
		request.getSession().getAttribute("filterList");
		if(filterList ==null){
			filterList = new FilterList();
		}
		
		category = request.getParameter("category");
		
		if(category != null && !category.equals("")){
			FilterCategory categoryFilter = new FilterCategory(category);
			filterList.getFilters().add(categoryFilter);
			
			request.getSession().setAttribute("filterList", filterList);
		}	
		ArticleService articleService = new ArticleService();
		request.setAttribute("articleList", articleService.getArticleList(filterList));
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
		
	}

}
