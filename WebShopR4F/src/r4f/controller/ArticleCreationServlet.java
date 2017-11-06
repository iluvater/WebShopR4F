package r4f.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.Article;
import r4f.model.ErrorMessage;

/**
 * Servlet implementation class ArticleCreationServlet
 */
@WebServlet("/ArticleCreationServlet")
public class ArticleCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleCreationServlet() {
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
		String bezeichnung;
		String beschreibung;
		int groesse;
		double preis;
		String hersteller;
		String farbe;
		String kategorie;
		String sportart;
		Article artikel;
		RequestDispatcher dispatcher;
		String errorURL = "Artikeldatenerfassung.jsp";
		String successURL = "Artikeldatenerfassung.jsp";

		bezeichnung = request.getParameter("bezeichnung");
		beschreibung = request.getParameter("beschreibung");
		try {
			preis = Double.parseDouble(request.getParameter("preis"));
		} catch (Exception e) {
			// error handling missing input
			ErrorMessage errorMessage = new ErrorMessage(115);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		try {
			groesse = Integer.parseInt(request.getParameter("groesse"));
		} catch (Exception e) {
			// error handling missing input
			ErrorMessage errorMessage = new ErrorMessage(116);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		hersteller = request.getParameter("hersteller");
		farbe = request.getParameter("farbe");
		kategorie = request.getParameter("kategorie");
		sportart = request.getParameter("sportart");

		if (bezeichnung != null && !bezeichnung.equals("")) {
			if (beschreibung != null && !beschreibung.equals("")) {
				if (hersteller != null && !hersteller.equals("") && Article.checkManufacturer(hersteller)) {
					if (farbe != null && !farbe.equals("")) {
						if (kategorie != null && !kategorie.equals("") && Article.checkCategory(kategorie)) {
							if (sportart != null && !sportart.equals("") && Article.checkSport(sportart)) {
								artikel = new Article(bezeichnung, beschreibung, groesse, preis, hersteller, farbe,
										kategorie, sportart);

								ArticleService artikelService = new ArticleService();
								artikel = artikelService.createArtikelInDB(artikel);

								if (artikel != null) {
									ErrorMessage successMeldung = new ErrorMessage(600);
									request.setAttribute("erfolg", successMeldung);
									dispatcher = request.getRequestDispatcher(successURL);
									dispatcher.forward(request, response);
									return;
								} else {
									// Errorhandling something wrong during
									// creating
									ErrorMessage errorMessage = new ErrorMessage(122);
									request.setAttribute("error", errorMessage);
									dispatcher = request.getRequestDispatcher(errorURL);
									dispatcher.forward(request, response);
									return;
								}
							} else {
								// errorhandling missing input
								ErrorMessage errorMessage = new ErrorMessage(123);
								request.setAttribute("error", errorMessage);
								dispatcher = request.getRequestDispatcher(errorURL);
								dispatcher.forward(request, response);
								return;
							}
						} else {
							// errorhandling missing input
							ErrorMessage errorMessage = new ErrorMessage(121);
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						}
					} else {
						// errorhandling missing input
						ErrorMessage errorMessage = new ErrorMessage(120);
						request.setAttribute("error", errorMessage);
						dispatcher = request.getRequestDispatcher(errorURL);
						dispatcher.forward(request, response);
						return;
					}
				} else {
					// errorhandling missing input
					ErrorMessage errorMessage = new ErrorMessage(119);
					request.setAttribute("error", errorMessage);
					dispatcher = request.getRequestDispatcher(errorURL);
					dispatcher.forward(request, response);
					return;
				}
			} else {
				// errorhandling missing input
				ErrorMessage errorMessage = new ErrorMessage(118);
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}
		} else {
			// errorhandling missing input
			ErrorMessage errorMessage = new ErrorMessage(117);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
	}

}
