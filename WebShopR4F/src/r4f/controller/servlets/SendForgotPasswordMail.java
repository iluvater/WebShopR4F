package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.EmailService;
import r4f.model.ErrorMessage;
import r4f.model.User;

/**
 * Servlet implementation class SendForgotPasswordMail
 */
@WebServlet("/SendForgotPasswordMail")
public class SendForgotPasswordMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendForgotPasswordMail() {
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
		String email;
		String successURL = "TEstSuccess.jsp";
		String errorURL = "Testerror.jsp";
		EmailService emailService = new EmailService();
		RequestDispatcher dispatcher;
		
		email = request.getParameter("email");
		if(email != null && ! email.equals("")){
			String code = User.getConfirmationCode(8);
			
			emailService.sendForgotPasswordMail(email, code);
			
			request.getSession().setAttribute("code", code);
			
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
			
		}else{
			// Errorhandling missing input
			ErrorMessage errorMessage = new ErrorMessage(108);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
	}

}
