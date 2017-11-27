package r4f.controller.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import r4f.controller.services.ArticleService;
import r4f.controller.services.ImageService;
import r4f.model.Article;
import r4f.model.ErrorMessage;

/**
 * Servlet implementation class ChangeArticleServlet
 */
@WebServlet("/ChangeArticleServlet")
public class ChangeArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeArticleServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = null;
		String description = null;
		int size = -1;
		double price = -1;
		String manufacturer = null;
		String color = null;
		String category = null;
		String sport = null;
		Article article;
		int articleId = -1;
		InputStream imageStream = null;
		String imageType = null;
		RequestDispatcher dispatcher;
		String errorURL = "Artikeldaten.jsp";
		String successURL = "Artikeldaten.jsp";

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(16777216);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(16777216);

		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.isFormField()) {
					switch (item.getFieldName()) {
					case "name":
						name = item.getString();
						break;
					case "description":
						description = item.getString();
						break;
					case "price":
						try {
							price = Double.parseDouble(item.getString());
						} catch (Exception e) {
							// error handling missing input
							ErrorMessage errorMessage = new ErrorMessage(115);
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						}
						break;
					case "size":
						try {
							size = Integer.parseInt(item.getString());
						} catch (Exception e) {
							// error handling missing input
							ErrorMessage errorMessage = new ErrorMessage(116);
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						}
						break;
					case "manufacturer":
						manufacturer = item.getString();
						break;
					case "color":
						color = item.getString();
						break;
					case "category":
						category = item.getString();
						break;
					case "sport":
						sport = item.getString();
						break;
					case "articleId":
						try {
							size = Integer.parseInt(item.getString());
						} catch (Exception e) {
							// error handling missing input
							ErrorMessage errorMessage = new ErrorMessage(126);
							request.setAttribute("error", errorMessage);
							dispatcher = request.getRequestDispatcher(errorURL);
							dispatcher.forward(request, response);
							return;
						}
						break;
					}
				} else {
					imageType = item.getContentType();
					imageStream = item.getInputStream();
				}
			}
		} catch (FileUploadException e) {
			ErrorMessage errorMessage = new ErrorMessage(125);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}

		if (name != null && !name.equals("")) {
			if (description != null && !description.equals("")) {
				if (manufacturer != null && !manufacturer.equals("") && Article.checkManufacturer(manufacturer)) {
					if (color != null && !color.equals("") && Article.checkColor(color)) {
						if (category != null && !category.equals("") && Article.checkCategory(category)) {
							if (sport != null && !sport.equals("") && Article.checkSport(sport)) {
								if (imageStream != null) {

									ArticleService articleService = new ArticleService();
									article = articleService.getArticle(articleId);

									article.setName(name);
									article.setDescription(description);
									article.setManufacturer(manufacturer);
									article.setColor(color);
									article.setCategory(category);
									article.setSport(sport);
									article.setPrice(price);
									article.setSize(size);

									boolean updateArticle = articleService.updateArticleinDB(article);

									ImageService imageService = new ImageService();
									boolean updateImage = imageService.updateImageInDB(article.getImage(), imageStream,
											imageType);
									if (updateArticle && updateImage) {
										ErrorMessage successMessage = new ErrorMessage(600);
										request.setAttribute("success", successMessage);
										dispatcher = request.getRequestDispatcher(successURL);
										dispatcher.forward(request, response);
										return;
									} else {
										// Errorhandling something wrong during
										// update
										ErrorMessage errorMessage = new ErrorMessage(126);
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