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

import r4f.controller.filter.CheckboxStatus;
import r4f.controller.services.ArticleService;
import r4f.controller.services.ImageService;
import r4f.model.Article;
import r4f.model.ErrorMessage;
import r4f.model.InputStreamStringHelpClass;

/**
 * Servlet implementation class ChangeArticleServlet
 * @author Ture
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
		List<Integer> size = new ArrayList<Integer>();
		double price = -1;
		String manufacturer = null;
		List<String> color = new ArrayList<String>();
		String category = null;
		String sport = null;
		Article article;
		int articleId = -1;
		InputStream mainImageStream = null;
		String mainImageType = null;
		List<InputStreamStringHelpClass> imagesHelper = new ArrayList<InputStreamStringHelpClass>();
		List<Integer> images = new ArrayList<Integer>();
		RequestDispatcher dispatcher;
		String errorURL = "Artikeldaten.jsp";
		String successURL = "Artikeldaten.jsp";
		ArticleService articleService = new ArticleService();
		int errorCode = -1;
		boolean newMainImage = true;
		boolean newImages = true;

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
						name = item.getString("UTF-8");
						break;
					case "description":
						description = item.getString("UTF-8");
						break;
					case "price":
						try {
							price = Double.parseDouble(item.getString("UTF-8"));
						} catch (Exception e) {
							// error handling missing input
							errorCode = 115;
						}
						break;
					case "size":
						try {
							size.add(Integer.parseInt(item.getString("UTF-8")));
						} catch (Exception e) {
							// error handling missing input
							errorCode = 116;
						}
						break;
					case "manufacturer":
						manufacturer = item.getString("UTF-8");
						break;
					case "color":
						color.add(item.getString("UTF-8"));
						break;
					case "category":
						category = item.getString("UTF-8");
						break;
					case "sport":
						sport = item.getString("UTF-8");
						break;
					case "id":
						try {
							articleId = Integer.parseInt(item.getString("UTF-8"));
						} catch (Exception e) {
							// error handling missing input
							errorCode = 126;
						}
						break;
					}
				} else {
					switch (item.getName()) {
					case "mainImage":
						if (item.getSize() != 0) {
							mainImageType = item.getContentType();
							mainImageStream = item.getInputStream();
						} else {
							newMainImage = false;
						}
						break;
					case "images":
						if (item.getSize() != 0) {
							InputStream inputStream = item.getInputStream();
							String imageType = item.getContentType();
							InputStreamStringHelpClass helpItem = new InputStreamStringHelpClass(inputStream,
									imageType);
							imagesHelper.add(helpItem);
						} else {
							if (images.isEmpty()) {
								newImages = false;
							}
						}

						break;
					}
					if (item.getFieldName().equals("mainImage") && item.getSize() != 0) {
						mainImageType = item.getContentType();
						mainImageStream = item.getInputStream();
					} else {
						newMainImage = false;
					}
				}
			}
		} catch (FileUploadException e) {
			errorCode = 125;
		}

		if (name != null && !name.equals("")) {
			if (description != null && !description.equals("")) {
				if (manufacturer != null && !manufacturer.equals("") && Article.checkManufacturer(manufacturer)) {
					if (!color.isEmpty()) {
						if (category != null && !category.equals("") && Article.checkCategory(category)) {
							if (sport != null && !sport.equals("") && Article.checkSport(sport)) {
								if (mainImageStream != null || !newMainImage) {

									for (int i = 0; i< size.size();i++ ) {
										switch (size.get(i)) {
										case 1:
											size.set(i, 36);
											break;
										case 2:
											size.set(i, 37);
											break;
										case 3:
											size.set(i, 38);
											break;
										case 4:
											size.set(i, 39);
											break;
										case 5:
											size.set(i, 40);
											break;
										case 6:
											size.set(i, 41);
											break;
										case 7:
											size.set(i, 42);
											break;
										case 8:
											size.set(i, 43);
											break;
										case 9:
											size.set(i, 44);
											break;
										case 10:
											size.set(i, 45);
											break;
										}
									}

									for (int i = 0; i<color.size();i++) {
										switch (color.get(i)) {
										case "1":
											color.set(i, "gelb");
											break;
										case "2":
											color.set(i, "orange");
											break;
										case "3":
											color.set(i, "rot");
											break;
										case "4":
											color.set(i, "pink");
											break;
										case "5":
											color.set(i, "grün");
											break;
										case "6":
											color.set(i, "blau");
											break;
										case "7":
											color.set(i, "schwarz");
											break;
										case "8":
											color.set(i, "weiß");
											break;
										}
									}

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

									boolean updateMainImage;
									boolean updateImages;
									ImageService imageService = new ImageService();
									if (newMainImage) {

										updateMainImage = imageService.updateImageInDB(article.getMainImage(),
												mainImageStream, mainImageType, true);
									} else {
										updateMainImage = true;
									}
									if (newImages) {
										for (InputStreamStringHelpClass item : imagesHelper) {
											imageService.createImageInDB(item.getInputStream(), item.getImageType(),
													false, articleId);
										}
									}

									if (updateArticle && updateMainImage) {
										request.setAttribute("article", articleService.getArticle(articleId));
										CheckboxStatus checkboxStatus = new CheckboxStatus();
										for (String item : article.getColor()) {
											switch (item) {
											case "gelb":
												checkboxStatus.setColor1("checked");
												break;
											case "orange":
												checkboxStatus.setColor2("checked");
												break;
											case "rot":
												checkboxStatus.setColor3("checked");
												break;
											case "pink":
												checkboxStatus.setColor4("checked");
												break;
											case "grün":
												checkboxStatus.setColor5("checked");
												break;
											case "blau":
												checkboxStatus.setColor6("checked");
												break;
											case "schwarz":
												checkboxStatus.setColor7("checked");
												break;
											case "weiß":
												checkboxStatus.setColor8("checked");
												break;
											}
										}
										for (int item : article.getSize()) {
											switch (item) {
											case 36:
												checkboxStatus.setSize1("checked");
												break;
											case 37:
												checkboxStatus.setSize2("checked");
												break;
											case 38:
												checkboxStatus.setSize3("checked");
												break;
											case 39:
												checkboxStatus.setSize4("checked");
												break;
											case 40:
												checkboxStatus.setSize5("checked");
												break;
											case 41:
												checkboxStatus.setSize6("checked");
												break;
											case 42:
												checkboxStatus.setSize7("checked");
												break;
											case 43:
												checkboxStatus.setSize8("checked");
												break;
											case 44:
												checkboxStatus.setSize9("checked");
												break;
											case 45:
												checkboxStatus.setSize10("checked");
												break;
											}
										}
										request.setAttribute("filter", checkboxStatus);
										ErrorMessage successMessage = new ErrorMessage(602);
										request.setAttribute("success", successMessage);
										dispatcher = request.getRequestDispatcher(successURL);
										dispatcher.forward(request, response);
										return;
									} else {
										// Errorhandling something wrong during
										// update
										errorCode = 126;
									}
								} else {
									// Errorhandling missing input
									errorCode = 124;
								}
							} else {
								// errorhandling missing input
								errorCode = 123;
							}
						} else {
							// errorhandling missing input
							errorCode = 121;
						}
					} else {
						// errorhandling missing input
						errorCode = 120;
					}
				} else {
					// errorhandling missing input
					errorCode = 119;
				}
			} else {
				// errorhandling missing input
				errorCode = 118;
			}
		} else {
			// errorhandling missing input
			errorCode = 117;
		}
		article = articleService.getArticle(articleId);
		request.setAttribute("article", article);
		CheckboxStatus checkboxStatus = new CheckboxStatus();
		for (String item : article.getColor()) {
			switch (item) {
			case "gelb":
				checkboxStatus.setColor1("checked");
				break;
			case "orange":
				checkboxStatus.setColor2("checked");
				break;
			case "rot":
				checkboxStatus.setColor3("checked");
				break;
			case "pink":
				checkboxStatus.setColor4("checked");
				break;
			case "grün":
				checkboxStatus.setColor5("checked");
				break;
			case "blau":
				checkboxStatus.setColor6("checked");
				break;
			case "schwarz":
				checkboxStatus.setColor7("checked");
				break;
			case "weiß":
				checkboxStatus.setColor8("checked");
				break;
			}
		}
		for (int item : article.getSize()) {
			switch (item) {
			case 36:
				checkboxStatus.setSize1("checked");
				break;
			case 37:
				checkboxStatus.setSize2("checked");
				break;
			case 38:
				checkboxStatus.setSize3("checked");
				break;
			case 39:
				checkboxStatus.setSize4("checked");
				break;
			case 40:
				checkboxStatus.setSize5("checked");
				break;
			case 41:
				checkboxStatus.setSize6("checked");
				break;
			case 42:
				checkboxStatus.setSize7("checked");
				break;
			case 43:
				checkboxStatus.setSize8("checked");
				break;
			case 44:
				checkboxStatus.setSize9("checked");
				break;
			case 45:
				checkboxStatus.setSize10("checked");
				break;
			}
		}
		request.setAttribute("filter", checkboxStatus);
		ErrorMessage errorMessage = new ErrorMessage(errorCode);
		request.setAttribute("error", errorMessage);
		dispatcher = request.getRequestDispatcher(errorURL);
		dispatcher.forward(request, response);
		return;
	}

}
