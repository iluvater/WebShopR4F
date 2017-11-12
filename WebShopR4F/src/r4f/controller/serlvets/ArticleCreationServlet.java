package r4f.controller.serlvets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.Blob;

import r4f.controller.services.ArticleService;
import r4f.controller.services.ImageService;
import r4f.model.Article;
import r4f.model.ErrorMessage;
import r4f.model.Image;

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
		String name;
		String description;
		int size;
		double price;
		String manufacturer;
		String color;
		String category;
		String sport;
		Article article;
		Part imagePart;
		RequestDispatcher dispatcher;
		String errorURL = "Artikeldatenerfassung.jsp";
		String successURL = "Artikeldatenerfassung.jsp";

		name = request.getParameter("name");
		description = request.getParameter("description");
		try {
			price = Double.parseDouble(request.getParameter("price"));
		} catch (Exception e) {
			// error handling missing input
			ErrorMessage errorMessage = new ErrorMessage(115);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		try {
			size = Integer.parseInt(request.getParameter("size"));
		} catch (Exception e) {
			// error handling missing input
			ErrorMessage errorMessage = new ErrorMessage(116);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		manufacturer = request.getParameter("manufacturer");
		color = request.getParameter("color");
		category = request.getParameter("category");
		sport = request.getParameter("sport");
		imagePart = request.getPart("image");

		if (name != null && !name.equals("")) {
			if (description != null && !description.equals("")) {
				if (manufacturer != null && !manufacturer.equals("") && Article.checkManufacturer(manufacturer)) {
					if (color != null && !color.equals("")) {
						if (category != null && !category.equals("") && Article.checkCategory(category)) {
							if (sport != null && !sport.equals("") && Article.checkSport(sport)) {
								if (imagePart != null) {
									article = new Article(name, description, size, price, manufacturer, color, category,
											sport);

									ArticleService artikelService = new ArticleService();
									article = artikelService.createArtikelInDB(article);

									ImageService imageService = new ImageService();
									boolean imageCreated = imageService.createImageInDB(imagePart);

									if (article != null && imageCreated) {
										ErrorMessage successMessage = new ErrorMessage(600);
										request.setAttribute("success", successMessage);
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
									// Errorhandling missing input
									ErrorMessage errorMessage = new ErrorMessage(124);
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
