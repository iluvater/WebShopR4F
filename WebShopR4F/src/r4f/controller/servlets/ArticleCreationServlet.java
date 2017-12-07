package r4f.controller.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import r4f.model.InputStreamStringHelpClass;

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
		String name = null;
		String description = null;
		String sizes[] = null;
		List<Integer> size = new ArrayList<Integer>();
		double price = -1;
		String manufacturer = null;
		String colors[] = null;
		List<String> color = new ArrayList<String>();
		String category = null;
		String sport = null;
		Article article;
		InputStream mainImageStream = null;
		String mainImageType = null;
		List<InputStreamStringHelpClass> imagesHelper = new ArrayList<InputStreamStringHelpClass>();
		List<Integer> images = new ArrayList<Integer>();
		RequestDispatcher dispatcher;
		String errorURL = "Artikeldatenerfassung.jsp";
		String successURL = "Artikeldatenerfassung.jsp";

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
							sizes = item.getString().split(";");
							size = new ArrayList<Integer>();
							for (int i = 0; i < sizes.length; i++) {
								size.add(Integer.parseInt(sizes[i]));
							}
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
						colors = item.getString().split(";");
						color = new ArrayList<String>();
						for (String string : colors) {
							color.add(string);
						}
						break;
					case "category":
						category = item.getString();
						break;
					case "sport":
						sport = item.getString();
						break;
					}
				} else {
					switch (item.getName()) {
					case "mainImage":
						mainImageType = item.getContentType();
						mainImageStream = item.getInputStream();
						break;
					case "images":
						InputStream inputStream = item.getInputStream();
						String imageType = item.getContentType();
						InputStreamStringHelpClass helpItem = new InputStreamStringHelpClass(inputStream, imageType);
						imagesHelper.add(helpItem);
						break;
					}
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
					if (!color.isEmpty()) {
						if (category != null && !category.equals("") && Article.checkCategory(category)) {
							if (sport != null && !sport.equals("") && Article.checkSport(sport)) {
								if (mainImageStream != null) {
									article = new Article(name, description, size, price, manufacturer, color, category,
											sport);

									int imageId = -1;
									ArticleService articleService = new ArticleService();
									article = articleService.createArtikelInDB(article);
									if (article != null) {
										ImageService imageService = new ImageService();
										imageId = imageService.createImageInDB(mainImageStream, mainImageType, true);
										
										for (InputStreamStringHelpClass item : imagesHelper) {
											int imgId = imageService.createImageInDB(item.getInputStream(), item.getImageType(), false);
											images.add(imgId);
										}
										
										if (imageId != -1) {
											article.setMainImage(imageId);
											article.setImages(images);
											articleService.updateArticleInDB(article);
										}
									}
									if (article != null && imageId != -1) {
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
