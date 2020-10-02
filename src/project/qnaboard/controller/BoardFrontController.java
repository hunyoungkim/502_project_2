package project.qnaboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.qnaboard.action.Action;
import project.qnaboard.command.ActionCommand;
import project.qnaboard.service.BoardAddService;
import project.qnaboard.service.BoardDeleteService;
import project.qnaboard.service.BoardDetailService;
import project.qnaboard.service.BoardDownloadService;
import project.qnaboard.service.BoardListService;
import project.qnaboard.service.BoardModifyDetailService;
import project.qnaboard.service.BoardModifyService;
import project.qnaboard.service.BoardReplyMoveService;
import project.qnaboard.service.BoardReplyService;
import project.qnaboard.service.BoardSearchListService;

public class BoardFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requstURL = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requstURL.substring(contextPath.length());

		ActionCommand actionCommand = null;
		System.out.println(contextPath + "// qnaboard");
		System.out.println(pathURL + "// qnaboard");
		Action action = null;
		
		if (pathURL.equals("/BoardList.qa")) {
			action = new BoardListService();
			
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardWrite.qa")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./qnaboard/qnaboard_write.jsp");

		} else if (pathURL.equals("/BoardAdd.qa")) {
			action = new BoardAddService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardDetail.qa")) {
			action = new BoardDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardDownload.qa")) {
			action = new BoardDownloadService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/BoardReply.qa")) {
			action = new BoardReplyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardReplyMove.qa")) {
			action = new BoardReplyMoveService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/BoardModify.qa")) {
			action = new BoardModifyDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardModifyService.qa")) {
			action = new BoardModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		} 
//		else if(pathURL.equals("/BoardDelete.qa")) {
//			actionCommand = new ActionCommand();
//			actionCommand.setRedirect(false);
//			actionCommand.setPath("./qnaboard/qnaboard_delete.jsp");
//			
		} else if(pathURL.equals("/BoardDeleteService.qa")) {
			action = new BoardDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/BoardSearchList.qa")) {
			action = new BoardSearchListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(actionCommand != null) {
			if(actionCommand.isRedirect()) {
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
