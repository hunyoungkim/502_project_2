package project.basket.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasketFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requstURL = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requstURL.substring(contextPath.length());

		BasketActionCommand basketActionCommand = null;

		BasketAction basketAction = null;

		if (pathURL.equals("/BasketList.bs")) {
			basketAction = new BasketListService();

			try {
				basketActionCommand = basketAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/Basketadd.bs")) {
			basketAction = new BasketAddService();

			try {
				basketActionCommand = basketAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BasketDeiete.bs")) {
			basketAction = new BasketDeleteService();

			try {
				basketActionCommand = basketAction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		if (basketActionCommand != null) {
			if (basketActionCommand.isRedirect()) {
				response.sendRedirect(basketActionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(basketActionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
