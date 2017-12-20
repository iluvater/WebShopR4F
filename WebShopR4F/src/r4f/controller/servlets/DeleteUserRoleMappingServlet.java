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
import r4f.controller.services.UserService;
import r4f.model.ErrorMessage;
import r4f.model.User;

/**
 * Servlet implementation class DeleteUserRoleMappingServlet
 * @author Ture
 */
@WebServlet("/DeleteUserRoleMappingServlet")
public class DeleteUserRoleMappingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserRoleMappingServlet() {
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
		int roleId;
		int userId;
		User user;
		RequestDispatcher dispatcher;
		String errorURL = "Rollenzuordnung.jsp";
		String successURL = "Rollenzuordnung.jsp";
		AuthorizationService authorizationService = new AuthorizationService();
		UserService userService = new UserService();

		user = (User) request.getSession().getAttribute("user");
		
		try {
			roleId = Integer.parseInt(request.getParameter("roleId"));
			userId = Integer.parseInt(request.getParameter("userId"));
			if(userId != user.getId()){
				authorizationService.deleteUserRoleMapping(userId, roleId);
				
				// success
				ErrorMessage successMessage = new ErrorMessage(605);
				List<User> userList = userService.getUserList();
				request.setAttribute("userList", userList);
				request.setAttribute("success", successMessage);
				dispatcher = request.getRequestDispatcher(successURL);
				dispatcher.forward(request, response);
				return;
			}else{
				ErrorMessage errorMessage = new ErrorMessage();
				List<User> userList = userService.getUserList();
				request.setAttribute("userList", userList);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}			
			
		} catch (NumberFormatException e) {
			// Error missing roleId
			ErrorMessage errorMessage = new ErrorMessage();
			List<User> userList = userService.getUserList();
			request.setAttribute("userList", userList);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
	}

}
