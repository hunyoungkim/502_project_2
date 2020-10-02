package project.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.member.action.Action;
import project.member.command.ActionCommand;
import project.member.service.IdCheck;
import project.member.service.LoginService;
import project.member.service.LogoutService;
import project.member.service.MemberDeleteService;
import project.member.service.MemberModifyService;
import project.member.service.MemberService;
import project.member.service.ModifyService;
import project.member.service.MyPageService;

public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());

		ActionCommand actionCommand = null;
		Action action = null;

		if (pathURL.equals("/MyPageService.me")) {
			action = new MyPageService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberListService.me")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/member.jsp");

		} else if (pathURL.equals("/MemberService.me")) {
			action = new MemberService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/LoginService.me")) {
			action = new LoginService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/Login.me")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./login/login.jsp");

		} else if (pathURL.equals("/LogoutService.me")) {
			action = new LogoutService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/ModifyService.me")) {
			action = new ModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberModifyService.me")) {
			action = new MemberModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/DeleteService.me")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/member_delete.jsp");
		} else if (pathURL.equals("/MemberDeleteService.me")) {
			action = new MemberDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/IdCheck.me")) {
			action = new IdCheck();
			try {
				actionCommand = action.execute(request, response);
			} catch(Exception e) {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service(request, response);
	}
}
