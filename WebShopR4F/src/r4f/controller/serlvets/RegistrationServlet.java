package r4f.controller.serlvets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.EmailService;
import r4f.controller.services.UserService;
import r4f.model.ErrorMessage;
import r4f.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
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

		String firstName, lastName, email, password, street, houseNumber, postcode, city, birthday_string, salutation;
		Date birthday;
		User user;
		UserService userService;
		RequestDispatcher dispatcher;
		String errorURL = "Registrierungsmaske.jsp";
		String successURL = "Willkommen.jsp";
		int year, month, day;

		// Getting all data
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		email = request.getParameter("email");
		password = request.getParameter("password");
		street = request.getParameter("street");
		houseNumber = request.getParameter("houseNumber");
		postcode = request.getParameter("postCode");
		city = request.getParameter("city");
		birthday_string = request.getParameter("birthday");
		salutation = request.getParameter("salutation");

		// check whether all input parameters are filled or not
		if (firstName == null || firstName.equals("")) {
			ErrorMessage errorMessage = new ErrorMessage(100);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		} else {
			if (lastName == null || lastName.equals("")) {
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
						if (street == null || street.equals("")) {
							ErrorMessage errorMessage = new ErrorMessage(110);
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						} else {
							if (houseNumber == null || houseNumber.equals("")) {
								ErrorMessage errorMessage = new ErrorMessage(111);
								request.setAttribute("error", errorMessage);
								dispatcher = request.getRequestDispatcher(errorURL);
								dispatcher.forward(request, response);
								return;
							} else {
								if (postcode.equals("") || postcode == null) {
									ErrorMessage errorMessage = new ErrorMessage(105);
									request.setAttribute("error", errorMessage);
									dispatcher = request.getRequestDispatcher(errorURL);
									dispatcher.forward(request, response);
									return;
								} else {
									if (city == null || city.equals("")) {
										ErrorMessage errorMessage = new ErrorMessage(112);
										request.setAttribute("error", errorMessage);
										dispatcher = request.getRequestDispatcher(errorURL);
										dispatcher.forward(request, response);
										return;
									} else {
										if (birthday_string == null || birthday_string.equals("")) {
											ErrorMessage errorMessage = new ErrorMessage(113);
											request.setAttribute("error", errorMessage);
											dispatcher = request.getRequestDispatcher(errorURL);
											dispatcher.forward(request, response);
											return;
										} else {
											if (salutation == null || salutation.equals("")) {

												// errorhandling missing
												ErrorMessage errorMessage = new ErrorMessage(104);
												request.setAttribute("error", errorMessage);
												dispatcher = request.getRequestDispatcher(errorURL);
												dispatcher.forward(request, response);
												return;
											} else {
												// check mail for format
												if (User.checkEmail(email)) {
													// check PLZ for format
													if (User.checkPostCode(postcode)) {
														// check value of anrede
														if (User.checkSalutation(salutation)) {
															// Check if email
															// exists
															userService = new UserService();
															if (!userService.checkEmailExists(email)) {
																// check date
																// format
																if (birthday_string.matches("\\d{4}-\\d{2}-\\d{2}")
																		|| birthday_string
																				.matches("\\d{2}.\\d{2}.\\d{4}")) {
																	if (birthday_string
																			.matches("\\d{4}-\\d{2}-\\d{2}")) {
																		year = Integer.parseInt(
																				birthday_string.substring(0, 4)) - 1900;
																		month = Integer.parseInt(
																				birthday_string.substring(5, 7)) - 1;
																		day = Integer.parseInt(
																				birthday_string.substring(8, 10));
																	} else {
																		year = Integer.parseInt(
																				birthday_string.substring(6, 10))
																				- 1900;
																		month = Integer.parseInt(
																				birthday_string.substring(3, 5)) - 1;
																		day = Integer.parseInt(
																				birthday_string.substring(0, 2));
																	}
																	birthday = new Date(year, month, day);
																	if (User.checkBirthday(birthday)) {
																		user = new User(firstName, lastName, email,
																				birthday, password, street, houseNumber,
																				postcode, city, salutation);

																		user = userService.createBenutzerInDB(user);
																		if (user != null) {
																			EmailService emailService = new EmailService();
																			emailService
																					.sendRegistrationConfirmation(user);
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
																		// Error too young
																		ErrorMessage errorMessage = new ErrorMessage(
																				129);
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
