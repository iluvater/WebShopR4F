package r4f.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.Artikel;
import r4f.model.ErrorMessage;

/**
 * Servlet implementation class ArtikelErfassungsServlet
 */
@WebServlet("/ArtikelErfassungsServlet")
public class ArtikelErfassungsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArtikelErfassungsServlet() {
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
		Artikel artikel;
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
				if (hersteller != null && !hersteller.equals("") && Artikel.checkHersteller(hersteller)) {
					if (farbe != null && !farbe.equals("")) {
						if (kategorie != null && !kategorie.equals("") && Artikel.checkKategorie(kategorie)) {
							if (sportart != null && !sportart.equals("") && Artikel.checkSportart(sportart)) {
								artikel = new Artikel(bezeichnung, beschreibung, groesse, preis, hersteller, farbe,
										kategorie, sportart);

								ArtikelService artikelService = new ArtikelService();
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