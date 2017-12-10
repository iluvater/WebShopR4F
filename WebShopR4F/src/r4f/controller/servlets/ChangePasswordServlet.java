package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.HashService;
import r4f.controller.services.UserService;
import r4f.model.ErrorMessage;
import r4f.model.User;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordServlet() {
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
		String code;
		String code_input;
		String email;
		String password, password_wdh;
		String successURL = "TEstSuccess.jsp";
		String errorURL = "Testerror.jsp";
		RequestDispatcher dispatcher;
		UserService userService = new UserService();
		HashService hashService = new HashService();
		User user;

		Object c = request.getSession().getAttribute("code");

		if (c != null) {
			code = (String) c;
			email = request.getParameter("email");
			password = request.getParameter("password");
			password_wdh = request.getParameter("password2");
			code_input = request.getParameter("code");
			if (password != null && !password.equals("")) {
				if (password_wdh != null && !password_wdh.equals("") && password_wdh.equals(password)) {
					if (email != null && !email.equals("") && User.checkEmail(email)
							&& userService.checkEmailExists(email)) {
						if (code_input != null && !code_input.equals("") && code_input.equals(code)) {
							password = hashService.encrypt(password);

							user = userService.getUser(email);
							user.setPassword(password);
							if (userService.updateUserInDB(user)) {
								// sucess

							} else {
								// error during update
								ErrorMessage errorMessage = new ErrorMessage(126);
								request.setAttribute("error", errorMessage);
								dispatcher = request.getRequestDispatcher(errorURL);
								dispatcher.forward(request, response);
								return;
							}

						} else {
							// error missing input
							ErrorMessage errorMessage = new ErrorMessage(141);
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						}
					} else {
						// error missing input
						ErrorMessage errorMessage = new ErrorMessage(106);
						request.setAttribute("error", errorMessage);
						dispatcher = request.getRequestDispatcher(errorURL);
						dispatcher.forward(request, response);
						return;
					}
				} else {
					// error missing input
					ErrorMessage errorMessage = new ErrorMessage(132);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			} else {
				// error missing input
				ErrorMessage errorMessage = new ErrorMessage(109);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		} else {
			// code not valid anymore
			ErrorMessage errorMessage = new ErrorMessage(140);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}

	}

}
