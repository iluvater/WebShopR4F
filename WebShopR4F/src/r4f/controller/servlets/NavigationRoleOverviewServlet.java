package r4f.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.AuthorizationService;
import r4f.model.Role;
import r4f.model.User;

/**
 * Servlet implementation class NavigationRoleOverviewServlet
 */
@WebServlet("/NavigationRoleOverviewServlet")
public class NavigationRoleOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationRoleOverviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String successURL = "Rollenbearbeitung.jsp";
		String errorURL = "Login.jsp";
		RequestDispatcher dispatcher;
		AuthorizationService authorizationService = new AuthorizationService();
		User user;
		
		user = (User) request.getSession().getAttribute("user");
		
		if(user != null){
			List<Role> roleList = authorizationService.getRoleList();
			
			request.setAttribute("roleList", roleList);
			
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
		}else{
			//Errorhandling please login			
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
		}
	}

}
