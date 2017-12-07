package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.UserService;
import r4f.model.ErrorMessage;
import r4f.model.User;

/**
 * Servlet implementation class ConfirmationCodeSerlvet
 */
@WebServlet("/ConfirmationCodeSerlvet")
public class ConfirmationCodeSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmationCodeSerlvet() {
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
		UserService userService;
		RequestDispatcher dispatcher;
		User user;
		String registrierungsURL = "Registrierungsmaske.jsp";
		String errorURL = "BestaetigungsCode.jsp";
		String successURL = "BestaetigungsCode.jsp";
		String code;

		code = request.getParameter("code");

		if (code != null && !code.equals("")) {
			user = (User) request.getSession().getAttribute("user");
			if (user!=null) {
				if (code.equals(user.getConfirmationCode())) {
					userService = new UserService();
					user = userService.createBenutzerInDB(user);
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", user);
					dispatcher = request.getRequestDispatcher(successURL);
					dispatcher.forward(request, response);
					return;
				} else {
					ErrorMessage errorMessage = new ErrorMessage(137);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			} else {
				ErrorMessage errorMessage = new ErrorMessage(102);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		} else {
			ErrorMessage errorMessage = new ErrorMessage(136);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(registrierungsURL);
			dispatcher.forward(request, response);
			return;
		}

	}

}
