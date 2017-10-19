package r4f.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.Benutzer;

/**
 * Servlet implementation class RegistrierungsServlet
 */
@WebServlet("./RegistrierungsServlet")
public class RegistrierungsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrierungsServlet() {
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
	//@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vorname, nachname, email, password, strasse, hausnummer, postleitzahl, stadt, geburtstdatum_string,
				anrede;
		Date geburtstdatum;
		Benutzer benutzer;
		RegistrierungsService registrierungsService;
		RequestDispatcher dispatcher;
		

		// Getting all data
		vorname = request.getParameter("vorname");
		nachname = request.getParameter("nachname");
		email = request.getParameter("email");
		password = request.getParameter("password");
		strasse = request.getParameter("strasse");
		hausnummer = request.getParameter("hausnummer");
		postleitzahl = request.getParameter("postleitzahl");
		stadt = request.getParameter("stadt");
		geburtstdatum_string = request.getParameter("geburtstdatum");
		anrede = request.getParameter("anrede");

		// check whether all input parameters are filled or not
		if (vorname.equals("") || vorname == null || nachname.equals("") || nachname == null || email.equals("")
				|| email == null || password.equals("") || password == null || strasse.equals("") || strasse == null
				|| hausnummer.equals("") || hausnummer == null || postleitzahl.equals("") || postleitzahl == null
				|| stadt.equals("") || stadt == null || geburtstdatum_string.equals("") || geburtstdatum_string == null
				|| anrede.equals("") || anrede == null) {
			// errorhandling missing input...
			dispatcher = request.getRequestDispatcher("Error.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			// check mail for format
			if (Benutzer.checkEmail(email)) {
				// check PLZ for format
				if (Benutzer.checkPostleitzahl(postleitzahl)) {
					// check value of anrede
					if (Benutzer.checkAnrede(anrede)) {
						// Check if email exists
						registrierungsService = new RegistrierungsService();
						if (!registrierungsService.checkEmailExists(email)) {
							//check date format
							if(geburtstdatum_string.matches("\\d{4}-\\d{2}-\\d{2}")){
								geburtstdatum = new Date(Integer.parseInt(geburtstdatum_string.substring(0, 3)),
										Integer.parseInt(geburtstdatum_string.substring(5, 6)),
										Integer.parseInt(geburtstdatum_string.substring(8, 9)));
								
								

								benutzer = new Benutzer(vorname, nachname, email, geburtstdatum, password, strasse,
										hausnummer, postleitzahl, stadt, anrede);
								
								benutzer = registrierungsService.createBenutzerInDB(benutzer);
								if(benutzer != null){
									dispatcher = request.getRequestDispatcher("Willkommen.jsp");
									dispatcher.forward(request, response);
									return;
								}else{
									//Errorhandling something went wrong during registration
									return;
								}								
							}else{
								//errorhandling wrong date format								
								return;
							}							
						}else{
							//errorhandling already user with this email
							return;
						}

					} else {
						// errorhandling wrong anrede
						return;
					}
				} else {
					// errorhandling wrong plz
					return;
				}
			} else {
				// errorhandling wrong mail
				return;
			}
		}

	}

}
