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
		Artikel artikel;
		RequestDispatcher dispatcher;
		String errorURL="Error.jsp";
		String successURL="Willkommen.jsp";

		bezeichnung = request.getParameter("bezeichnung");
		beschreibung = request.getParameter("beschreibung");
		try {
			groesse = Integer.parseInt(request.getParameter("groesse"));
			preis = Double.parseDouble(request.getParameter("preis"));
		} catch (Exception e) {
			//error handling missing input
			ErrorMessage errorMessage = new ErrorMessage(100, "Fehlende Eingabe");
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		hersteller = request.getParameter("hersteller");
		farbe = request.getParameter("farbe");
		kategorie = request.getParameter("kategorie");

		if (bezeichnung != null && !bezeichnung.equals("") && beschreibung != null && !beschreibung.equals("")
				&& hersteller != null && !hersteller.equals("") && farbe != null && !farbe.equals("")
				&& kategorie != null && !kategorie.equals("")) {
			artikel = new Artikel(bezeichnung, beschreibung, groesse, preis, hersteller, farbe, kategorie);
			
			ArtikelService artikelService = new ArtikelService();
			artikel = artikelService.createArtikelInDB(artikel);
			
			if(artikel !=null){
				dispatcher = request.getRequestDispatcher(successURL);
				dispatcher.forward(request, response);
				return;
			}else{
				//Errorhandling something wrong during creating
				ErrorMessage errorMessage = new ErrorMessage(100,
						"Es ist etwas während der Artikelerfassung schiefgegangen. Bitte probieren Sie es erneut.");
				request.setAttribute("error", errorMessage);
				dispatcher = request.getRequestDispatcher(errorURL);
				dispatcher.forward(request, response);
				return;
			}			
		} else {
			// errorhandling missing input
			ErrorMessage errorMessage = new ErrorMessage(100, "Fehlende Eingabe");
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
		}

	}

}
