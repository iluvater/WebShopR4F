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

/**
 * Servlet implementation class CreateUserRoleMappingServlet
 */
@WebServlet("/CreateUserRoleMappingServlet")
public class CreateUserRoleMappingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserRoleMappingServlet() {
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
		String errorURL = "Test.jsp";
		String successURL = "Test.jsp";
		String email;
		String role;
		AuthorizationService authorizationService;
		
		try{
			email = request.getParameter("email");
			role = request.getParameter("role");
			authorizationService = new AuthorizationService();
			if(role!=null && !role.equals("")){
			int id = authorizationService.createUserRoleMapping(email, role);
			if(id != -1){
				//success
				ErrorMessage successMessage = new ErrorMessage(603);
				request.setAttribute("success", successMessage);
				dispatcher = request.getRequestDispatcher(successURL);
				dispatcher.forward(request, response);
			}else{
				//something wrong during creating
				ErrorMessage errorMessage = new ErrorMessage(126);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
			}else{
				//Error missing input role
				ErrorMessage errorMessage = new ErrorMessage(138);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		}catch(NumberFormatException e){
			//Error missing input email
			ErrorMessage errorMessage = new ErrorMessage(108);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		
		
	}

}
