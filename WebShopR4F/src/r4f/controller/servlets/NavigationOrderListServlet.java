package r4f.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.controller.services.OrderService;
import r4f.model.Order;
import r4f.model.User;

/**
 * Servlet implementation class NavigationOrderListServlet
 */
@WebServlet("/NavigationOrderListServlet")
public class NavigationOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationOrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String successURL = "Test.jsp";
		String errorURL = "Test.jsp";
		RequestDispatcher dispatcher;
		OrderService orderService = new OrderService();
		User user;
		
		user = (User) request.getSession().getAttribute("user");
		
		if(user != null){
			List<Order> orderList = orderService.getOrderList(user);
			
			request.setAttribute("orderList", orderList);
			
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
		}else{
			//Errorhandling please login			
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
		}
	}

}
