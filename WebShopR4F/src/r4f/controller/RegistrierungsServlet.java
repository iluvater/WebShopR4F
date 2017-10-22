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
import r4f.model.ErrorMessage;

/**
 * Servlet implementation class RegistrierungsServlet
 */
@WebServlet("/RegistrierungsServlet")
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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vorname, nachname, email, password, strasse, hausnummer, postleitzahl, stadt, geburtstdatum_string,
				anrede;
		Date geburtstdatum;
		Benutzer benutzer;
		RegistrierungsService registrierungsService;
		RequestDispatcher dispatcher;
		String errorURL = "TestRegistrierung.jsp";
		String successURL = "Willkommen.jsp";

		// Getting all data
		vorname = request.getParameter("vorname");
		nachname = request.getParameter("nachname");
		email = request.getParameter("email");
		password = request.getParameter("password");
		strasse = request.getParameter("strasse");
		hausnummer = request.getParameter("hausnummer");
		postleitzahl = request.getParameter("postleitzahl");
		stadt = request.getParameter("stadt"); 
		geburtstdatum_string = request.getParameter("geburtsdatum");
		anrede = request.getParameter("anrede");

		// check whether all input parameters are filled or not
		if (vorname.equals("") || vorname == null || nachname.equals("") || nachname == null || email.equals("")
				|| email == null || password.equals("") || password == null || strasse.equals("") || strasse == null
				|| hausnummer.equals("") || hausnummer == null || postleitzahl.equals("") || postleitzahl == null
				|| stadt.equals("") || stadt == null || geburtstdatum_string.equals("") || geburtstdatum_string == null
				|| anrede.equals("") || anrede == null) {
			// errorhandling missing input...
			ErrorMessage errorMessage = new ErrorMessage(100, "Fehlende Eingabe");
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
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
							// check date format
							if (geburtstdatum_string.matches("\\d{4}-\\d{2}-\\d{2}")) {
								int jahr = Integer.parseInt(geburtstdatum_string.substring(0, 4)) - 1900;
								int monat = Integer.parseInt(geburtstdatum_string.substring(5, 7)) -1;
								int tag = Integer.parseInt(geburtstdatum_string.substring(8, 10));
								geburtstdatum = new Date(jahr, monat, tag);

								benutzer = new Benutzer(vorname, nachname, email, geburtstdatum, password, strasse,
										hausnummer, postleitzahl, stadt, anrede);

								benutzer = registrierungsService.createBenutzerInDB(benutzer);
								if (benutzer != null) {
									dispatcher = request.getRequestDispatcher(successURL);
									dispatcher.forward(request, response);
									return;
								} else {
									// Errorhandling something went wrong during
									// registration
									ErrorMessage errorMessage = new ErrorMessage(100,
											"Es ist etwas während der Registrierung schiefgegangen. Bitte probieren Sie es erneut.");
									request.setAttribute("error", errorMessage);
									dispatcher = request.getRequestDispatcher(errorURL);
									dispatcher.forward(request, response);
									return;
								}
							} else {
								// errorhandling wrong date format
								ErrorMessage errorMessage = new ErrorMessage(100,
										"Bitte geben Sie das DAtum im richtigen Format ein.");
								request.setAttribute("error", errorMessage);
								dispatcher = request.getRequestDispatcher(errorURL);
								dispatcher.forward(request, response);
								return;
							}
						} else {
							// errorhandling already user with this email
							ErrorMessage errorMessage = new ErrorMessage(100,
									"Diese Email ist bereits registriert. Melden Sie sich an.");
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						}

					} else {
						// errorhandling wrong anrede
						ErrorMessage errorMessage = new ErrorMessage(100, "Bitte Wählen Sie ein gültige Anrede aus.");
						request.setAttribute("error", errorMessage);
						dispatcher = request.getRequestDispatcher(errorURL);
						dispatcher.forward(request, response);
						return;
					}
				} else {
					// errorhandling wrong plz
					ErrorMessage errorMessage = new ErrorMessage(100, "Bitte Geben Sie eine gültige Postleitzahl ein.");
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			} else {
				// errorhandling wrong mail
				ErrorMessage errorMessage = new ErrorMessage(100, "Bitte Geben Sie ein gültige Email ein.");
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		}

	}

}
