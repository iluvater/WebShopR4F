package r4f.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.Benutzer;

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
							if(geburtstdatum_string.matches("\\d{2}.\\d{2}.\\d{4}")){
								geburtstdatum = new Date(Integer.parseInt(geburtstdatum_string.substring(6, 9)),
										Integer.parseInt(geburtstdatum_string.substring(3, 4)),
										Integer.parseInt(geburtstdatum_string.substring(0, 1)));

								benutzer = new Benutzer(vorname, nachname, email, geburtstdatum, password, strasse,
										hausnummer, postleitzahl, stadt, anrede);
								
								benutzer = registrierungsService.createBenutzerInDB(benutzer);
								if(benutzer != null){
									//Registraion was succesfull inform user
								}else{
									//Errorhandling something went wrong during registration
								}								
							}else{
								//errorhandling wrong date format
							}							
						}else{
							//errorhandling already user with this email
						}

					} else {
						// errorhandling wrong anrede
					}
				} else {
					// errorhandling wrong plz
				}
			} else {
				// errorhandling wrong mail
			}
		}

	}

}
