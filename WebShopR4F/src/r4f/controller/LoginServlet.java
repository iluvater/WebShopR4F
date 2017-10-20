package r4f.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.Benutzer;
import r4f.model.DatenbankVerbindung;
import r4f.model.ErrorMessage;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email;
		String password;
		Benutzer benutzer;
		LoginService loginService;
		RequestDispatcher dispatcher;
		
		String errorURL = "Error.jsp";
		String succesURL = "Willkommen.jsp";

		email = request.getParameter("email");
		password = request.getParameter("password");
		
		loginService = new LoginService();
		
		if(loginService.checkLogin(email, password)){
			benutzer = loginService.getBenutzer(email);
			
			request.getSession().setAttribute("bentuzer", benutzer);
			dispatcher = request.getRequestDispatcher(succesURL);
			dispatcher.forward(request, response);
			return;
		}else{
			ErrorMessage errorMessage = new ErrorMessage(101, "Falsches Password oder Emailaddresse");
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}

	}

}
