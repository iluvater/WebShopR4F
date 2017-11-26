package r4f.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.filter.CheckboxStatus;
import r4f.controller.filter.FilterColor;
import r4f.controller.filter.FilterList;
import r4f.controller.filter.FilterManufacturer;
import r4f.controller.filter.FilterPrice;
import r4f.controller.filter.FilterSize;
import r4f.controller.filter.FilterSport;
import r4f.controller.services.ArticleService;

/**
 * Servlet implementation class NavigationOverviewFilterServlet
 */
@WebServlet("/NavigationOverviewFilterServlet")
public class NavigationOverviewFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationOverviewFilterServlet() {
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
		String successURL = "Uebersichtsseite.jsp";
		String sport[] = null;
		String manufacturer[] = null;
		String color[] = null;
		String price[] = null;
		String size[] = null;
		CheckboxStatus checkboxStatus = new CheckboxStatus();

		RequestDispatcher dispatcher;
		FilterList filterList = (FilterList) request.getSession().getAttribute("filterList");
		request.getSession().removeAttribute("filter");

		if (filterList == null) {
			filterList = new FilterList();
		}

		sport = request.getParameterValues("sport");
		manufacturer = request.getParameterValues("manufacturer");
		color = request.getParameterValues("color");
		price = request.getParameterValues("price");
		size = request.getParameterValues("size");

		if (sport != null && !sport.equals("")) {
			FilterSport filterSport = (FilterSport) filterList.getFilter("sport");
			if (filterSport == null) {
				filterSport = new FilterSport();
			}
			filterList.removeFilter("sport");
			filterSport.getSports().clear();
			for (String string : sport) {
				switch (string) {
				case "1":
					filterSport.getSports().add("Laufen");
					checkboxStatus.setSport1(true);
					break;
				case "2":
					filterSport.getSports().add("Fussball");
					checkboxStatus.setSport2(true);
					break;
				case "3":
					filterSport.getSports().add("Golf");
					checkboxStatus.setSport3(true);
					break;
				case "4":
					filterSport.getSports().add("Basketball");
					checkboxStatus.setSport4(true);
					break;
				case "5":
					filterSport.getSports().add("Fahrrad");
					checkboxStatus.setSport5(true);
					break;
				case "6":
					filterSport.getSports().add("Schwimmen");
					checkboxStatus.setSport6(true);
					break;
				}
			}
			filterList.getFilters().add(filterSport);
		} else {
			filterList.removeFilter("sport");
		}
		if (manufacturer != null && !manufacturer.equals("")) {
			FilterManufacturer filterManufacturer = (FilterManufacturer) filterList.getFilter("manufacturer");
			if (filterManufacturer == null) {
				filterManufacturer = new FilterManufacturer();
			}
			filterList.removeFilter("manufacturer");
			filterManufacturer.getManufacturers().clear();
			for (String string : manufacturer) {
				switch (string) {
				case "1":
					filterManufacturer.getManufacturers().add("Adidas");
					checkboxStatus.setManufacturer1(true);
					break;
				case "2":
					filterManufacturer.getManufacturers().add("Asics");
					checkboxStatus.setManufacturer2(true);
					break;
				case "3":
					filterManufacturer.getManufacturers().add("Hummel");
					checkboxStatus.setManufacturer3(true);
					break;
				case "4":
					filterManufacturer.getManufacturers().add("Kempa");
					checkboxStatus.setManufacturer4(true);
					break;
				case "5":
					filterManufacturer.getManufacturers().add("Nike");
					checkboxStatus.setManufacturer5(true);
					break;
				case "6":
					filterManufacturer.getManufacturers().add("Puma");
					checkboxStatus.setManufacturer6(true);
					break;
				}
			}
			filterList.getFilters().add(filterManufacturer);
		} else {
			filterList.removeFilter("manufacturer");
		}
		if (color != null && !color.equals("")) {
			FilterColor filterColor = (FilterColor) filterList.getFilter("manufacturer");
			if (filterColor == null) {
				filterColor = new FilterColor();
			}
			filterList.removeFilter("manufacturer");
			filterColor.getColors().clear();
			for (String string : color) {
				switch (string) {
				case "1":
					filterColor.getColors().add("gelb");
					checkboxStatus.setColor1(true);
					break;
				case "2":
					filterColor.getColors().add("orange");
					checkboxStatus.setColor2(true);
					break;
				case "3":
					filterColor.getColors().add("rot");
					checkboxStatus.setColor3(true);
					break;
				case "4":
					filterColor.getColors().add("pink");
					checkboxStatus.setColor4(true);
					break;
				case "5":
					filterColor.getColors().add("grün");
					checkboxStatus.setColor5(true);
					break;
				case "6":
					filterColor.getColors().add("blau");
					checkboxStatus.setColor6(true);
					break;
				case "7":
					filterColor.getColors().add("schwarz");
					checkboxStatus.setColor7(true);
					break;
				case "8":
					filterColor.getColors().add("weiß");
					checkboxStatus.setColor8(true);
					break;
				}
			}
			filterList.getFilters().add(filterColor);
		} else {
			filterList.removeFilter("color");
		}
		if (price != null && !price.equals("")) {
			FilterPrice filterPrice = (FilterPrice) filterList.getFilter("price");
			if (filterPrice == null) {
				filterPrice = new FilterPrice();
			}
			filterList.removeFilter("price");
			filterPrice.getPrices().clear();
			for (String string : price) {
				int priceSpan[] = new int[2];
				switch (string) {
				case "1":
					priceSpan[0] = -1;
					priceSpan[1] = 50;
					break;
				case "2":
					priceSpan[0] = 50;
					priceSpan[1] = 100;
					break;
				case "3":
					priceSpan[0] = 100;
					priceSpan[1] = 150;
					break;
				case "4":
					priceSpan[0] = 150;
					priceSpan[1] = -1;
					break;
				}
				filterPrice.getPrices().add(priceSpan);
			}
			filterList.getFilters().add(filterPrice);
		} else {
			filterList.removeFilter("price");
		}
		if (size != null && !size.equals("")) {
			FilterSize filterSize = (FilterSize) filterList.getFilter("size");
			if (filterSize == null) {
				filterSize = new FilterSize();
			}
			filterList.removeFilter("size");
			filterSize.getSizes().clear();
			for (String string : size) {
				switch (string) {
				case "1":
					filterSize.getSizes().add(36);
					checkboxStatus.setSize1(true);
					break;
				case "2":
					filterSize.getSizes().add(37);
					checkboxStatus.setSize2(true);
					break;
				case "3":
					filterSize.getSizes().add(38);
					checkboxStatus.setSize3(true);
					break;
				case "4":
					filterSize.getSizes().add(39);
					checkboxStatus.setSize4(true);
					break;
				case "5":
					filterSize.getSizes().add(40);
					checkboxStatus.setSize5(true);
					break;
				case "6":
					filterSize.getSizes().add(41);
					checkboxStatus.setSize6(true);
					break;
				case "7":
					filterSize.getSizes().add(42);
					checkboxStatus.setSize7(true);
					break;
				case "8":
					filterSize.getSizes().add(43);
					checkboxStatus.setSize8(true);
					break;
				case "9":
					filterSize.getSizes().add(44);
					checkboxStatus.setSize9(true);
					break;
				case "10":
					filterSize.getSizes().add(45);
					checkboxStatus.setSize10(true);
					break;
				}
			}
			filterList.getFilters().add(filterSize);
		} else {
			filterList.removeFilter("size");
		}

		request.getSession().setAttribute("filterList", filterList);

		ArticleService articleService = new ArticleService();
		request.setAttribute("articleList", articleService.getArticleList(filterList));

		dispatcher = request.getRequestDispatcher(successURL);
		dispatcher.forward(request, response);
		return;
	}

}
