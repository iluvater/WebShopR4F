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
 * Servlet implementation class SendContactMailServlet
 */
@WebServlet("/SendContactMailServlet")
public class SendContactMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendContactMailServlet() {
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
		String email, subject, body;
		EmailService emailService;
		RequestDispatcher dispatcher;
		String errorURL = "Kontaktseite.jsp";
		String successURL = "Kontaktseite.jsp";
		
		email = request.getParameter("email");
		subject = request.getParameter("subject");
		body = request.getParameter("body");
		
		if(email != null && !email.equals("") && User.checkEmail(email)){
			if(subject != null && !subject.equals("")){
				if(body != null && !body.equals("")){
					emailService = new EmailService();
					body = "Antwort an: " + email + " <br><br>" + body;
					emailService.sendContactMail(subject, body);
					
					dispatcher = request.getRequestDispatcher(successURL);
					dispatcher.forward(request, response);
					return;
				}else{
					//Error handling missing input
					ErrorMessage errorMessage = new ErrorMessage(134);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			}else{
				//Error handling missing input
				ErrorMessage errorMessage = new ErrorMessage(133);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		}else{
			//Error handling missing input
			ErrorMessage errorMessage = new ErrorMessage(106);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
	}

}
