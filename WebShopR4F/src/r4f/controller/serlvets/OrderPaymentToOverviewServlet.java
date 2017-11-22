package r4f.controller.serlvets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r4f.model.ErrorMessage;
import r4f.model.Order;
import r4f.model.ShoppingBasket;
import r4f.model.User;

/**
 * Servlet implementation class OrderPaymentToOverviewServlet
 */
@WebServlet("/OrderPaymentToOverviewServlet")
public class OrderPaymentToOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPaymentToOverviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String successURL = "Test.jsp";
		String errorURL = "Test.jsp";
		String paymentMethod;
		RequestDispatcher dispatcher;
		
		Order order = null;
		
		order = (Order) request.getSession().getAttribute("order");
		
		paymentMethod = request.getParameter("paymentMethod");
		
		if(paymentMethod != null && !paymentMethod.equals("") && Order.checkPaymentMethod(paymentMethod)){
			order.setPaymentMethod(paymentMethod);
			request.getSession().setAttribute("order", order);
			
			dispatcher = request.getRequestDispatcher(successURL);
			dispatcher.forward(request, response);
			return;
			
			
		}else{
			//Errorhandling missing input
			ErrorMessage errorMessage = new ErrorMessage(130);
			request.setAttribute("error", errorMessage);
			dispatcher = request.getRequestDispatcher(errorURL);
			dispatcher.forward(request, response);
			return;
		}
		
	}

}
