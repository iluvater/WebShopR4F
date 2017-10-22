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
		String errorURL = "Registrierungsmaske.jsp";
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
		if (vorname == null || vorname.equals("")) {
			ErrorMessage errorMessage = new ErrorMessage(100);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		} else {
			if (nachname == null || nachname.equals("")) {
				ErrorMessage errorMessage = new ErrorMessage(107);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			} else {
				if (email == null || email.equals("")) {
					ErrorMessage errorMessage = new ErrorMessage(108);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				} else {
					if (password == null || password.equals("")) {
						ErrorMessage errorMessage = new ErrorMessage(109);
						request.setAttribute("error", errorMessage);
						dispatcher = request.getRequestDispatcher(errorURL);
						dispatcher.forward(request, response);
						return;
					} else {
						if (strasse == null || strasse.equals("")) {
							ErrorMessage errorMessage = new ErrorMessage(110);
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						} else {
							if (hausnummer == null || hausnummer.equals("")) {
								ErrorMessage errorMessage = new ErrorMessage(111);
								request.setAttribute("error", errorMessage);
								dispatcher = request.getRequestDispatcher(errorURL);
								dispatcher.forward(request, response);
								return;
							} else {
								if (postleitzahl.equals("") || postleitzahl == null) {
									ErrorMessage errorMessage = new ErrorMessage(105);
									request.setAttribute("error", errorMessage);
									dispatcher = request.getRequestDispatcher(errorURL);
									dispatcher.forward(request, response);
									return;
								} else {
									if (stadt == null || stadt.equals("")) {
										ErrorMessage errorMessage = new ErrorMessage(112);
										request.setAttribute("error", errorMessage);
										dispatcher = request.getRequestDispatcher(errorURL);
										dispatcher.forward(request, response);
										return;
									} else {
										if (geburtstdatum_string == null || geburtstdatum_string.equals("")) {
											ErrorMessage errorMessage = new ErrorMessage(113);
											request.setAttribute("error", errorMessage);
											dispatcher = request.getRequestDispatcher(errorURL);
											dispatcher.forward(request, response);
											return;
										} else {
											if (anrede == null || anrede.equals("")) {

												// errorhandling missing
												ErrorMessage errorMessage = new ErrorMessage(104);
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
															// Check if email
															// exists
															registrierungsService = new RegistrierungsService();
															if (!registrierungsService.checkEmailExists(email)) {
																// check date
																// format
																if (geburtstdatum_string
																		.matches("\\d{4}-\\d{2}-\\d{2}")) {
																	int jahr = Integer.parseInt(
																			geburtstdatum_string.substring(0, 4))
																			- 1900;
																	int monat = Integer.parseInt(
																			geburtstdatum_string.substring(5, 7)) - 1;
																	int tag = Integer.parseInt(
																			geburtstdatum_string.substring(8, 10));
																	geburtstdatum = new Date(jahr, monat, tag);

																	benutzer = new Benutzer(vorname, nachname, email,
																			geburtstdatum, password, strasse,
																			hausnummer, postleitzahl, stadt, anrede);

																	benutzer = registrierungsService
																			.createBenutzerInDB(benutzer);
																	if (benutzer != null) {
																		dispatcher = request
																				.getRequestDispatcher(successURL);
																		dispatcher.forward(request, response);
																		return;
																	} else {
																		// Errorhandling
																		// something
																		// went
																		// wrong
																		// during
																		// registration
																		ErrorMessage errorMessage = new ErrorMessage(
																				101);
																		request.setAttribute("error", errorMessage);
																		dispatcher = request
																				.getRequestDispatcher(errorURL);
																		dispatcher.forward(request, response);
																		return;
																	}
																} else {
																	// errorhandling
																	// wrong
																	// date
																	// format
																	ErrorMessage errorMessage = new ErrorMessage(102);
																	request.setAttribute("error", errorMessage);
																	dispatcher = request.getRequestDispatcher(errorURL);
																	dispatcher.forward(request, response);
																	return;
																}
															} else {
																// errorhandling
																// already user
																// with this
																// email
																ErrorMessage errorMessage = new ErrorMessage(103);
																request.setAttribute("error", errorMessage);
																dispatcher = request.getRequestDispatcher(errorURL);
																dispatcher.forward(request, response);
																return;
															}

														} else {
															// errorhandling
															// wrong anrede
															ErrorMessage errorMessage = new ErrorMessage(104);
															request.setAttribute("error", errorMessage);
															dispatcher = request.getRequestDispatcher(errorURL);
															dispatcher.forward(request, response);
															return;
														}
													} else {
														// errorhandling wrong
														// plz
														ErrorMessage errorMessage = new ErrorMessage(105);
														request.setAttribute("error", errorMessage);
														dispatcher = request.getRequestDispatcher(errorURL);
														dispatcher.forward(request, response);
														return;
													}
												} else {
													// errorhandling wrong mail
													ErrorMessage errorMessage = new ErrorMessage(106);
													request.setAttribute("error", errorMessage);
													dispatcher = request.getRequestDispatcher(errorURL);
													dispatcher.forward(request, response);
													return;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

}
