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
import r4f.model.ErrorMessage;
import r4f.model.Role;

/**
 * Servlet implementation class ChangeRoleServlet
 */
@WebServlet("/ChangeRoleServlet")
public class ChangeRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeRoleServlet() {
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
		RequestDispatcher dispatcher;
		String errorURL = "Rollenbearbeitung.jsp";
		String successURL = "Rollenbearbeitung.jsp";
		int roleId;
		String name;
		String description;
		AuthorizationService authorizationService;
		
		try{
			roleId = Integer.parseInt(request.getParameter("roleId"));
			name = request.getParameter("name");
			description = request.getParameter("description");
			
			if(name != null && name.equals("")){
				if(description != null && description.equals("")){
					authorizationService = new AuthorizationService();
					Role role = new Role(roleId, name, description);
					boolean update = authorizationService.updateRoleInDB(role);
					if(update){
						//success
						List<Role> roleList = authorizationService.getRoleList();
						
						request.setAttribute("roleList", roleList);
						ErrorMessage successMessage = new ErrorMessage(604);
						request.setAttribute("success", successMessage);
						dispatcher = request.getRequestDispatcher(successURL);
						dispatcher.forward(request, response);
						return;
					}else{
						//Error during update
						ErrorMessage errorMessage = new ErrorMessage(126);
						request.setAttribute("error", errorMessage);
						dispatcher = request.getRequestDispatcher(errorURL);
						dispatcher.forward(request, response);
						return;
					}
				}else{
					//Error missing input description
					ErrorMessage errorMessage = new ErrorMessage(118);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			}else{
				//Error missing input name
				ErrorMessage errorMessage = new ErrorMessage(138);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		}catch(NumberFormatException e){
			//Error missing input role
			ErrorMessage errorMessage = new ErrorMessage(138);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
	}

}
