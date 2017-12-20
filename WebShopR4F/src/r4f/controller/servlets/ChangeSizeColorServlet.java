package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeSizeColorServlet
 * @author Ture
 */
@WebServlet("/ChangeSizeColorServlet")
public class ChangeSizeColorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeSizeColorServlet() {
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
		String color, size;
		int articleId;
		RequestDispatcher dispatcher;
		String successURL = "./NavigationArticleDetailsServlet";

		try {
			color = request.getParameter("color");
			size = request.getParameter("size");
			request.getSession().setAttribute("color", color);
			request.getSession().setAttribute("size", size);
			articleId = Integer.parseInt(request.getParameter("articleId"));
			request.setAttribute("articleId", articleId);
		} catch (NumberFormatException e) {
			
		}
		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
		
	}

}
