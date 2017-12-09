package r4f.controller.servlets;

import java.io.IOException;

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
 * Servlet implementation class CreateRoleServlet
 */
@WebServlet("/CreateRoleServlet")
public class CreateRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRoleServlet() {
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
		String name;
		String description;
		RequestDispatcher dispatcher;
		String errorURL = "Test.jsp";
		String successURL = "TEst.jsp";
		AuthorizationService authorizationService = new AuthorizationService();
		
		name = request.getParameter("name");
		description = request.getParameter("description");
		
		if(name != null && !name.equals("")){
			if(description != null && !description.equals("")){
				Role role = new Role(name, description);
				int roleId = authorizationService.createRoleInDB(role);
				if(roleId != -1){
					//success
					ErrorMessage successMessage = new ErrorMessage(604);
					request.setAttribute("success", successMessage);
					dispatcher = request.getRequestDispatcher(successURL);
					dispatcher.forward(request, response);
					return;
				}else{
					//error something wrong during creation
					ErrorMessage errorMessage = new ErrorMessage(126);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			}else{
				//error missing input description
				ErrorMessage errorMessage = new ErrorMessage(118);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		}else{
			//error missing input name
			ErrorMessage errorMessage = new ErrorMessage(117);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		
	}

}
