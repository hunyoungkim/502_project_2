package project.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.admin.action.Action;
import project.admin.command.ActionCommand;
import project.admin.service.AdminMemberService;


public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		ActionCommand actionCommand = null;
		Action action = null;
		
		if (pathURL.equals("/AdminMemberService.ad")) {
			action = new AdminMemberService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
    
	if (actionCommand != null) {
		if (actionCommand.isRedirect()) {
			response.sendRedirect(actionCommand.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
			dispatcher.forward(request, response);
		}
	}
}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service(request, response);
	}

}
