package project.qnaboard.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.qnaboard.action.Action;
import project.qnaboard.command.ActionCommand;
import project.qnaboard.dao.BoardDAO;

public class BoardDeleteService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(request.getParameter("num"));
		
		BoardDAO boardDAO = new BoardDAO();
		
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		
		usercheck = boardDAO.isBoardWriter(num, m_id);

		if (usercheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("location.href='./BoardList.qa';");
			//out.println("hestory.back( )");
			out.println("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제하였습니다.');");
			out.println("location.href='./BoardList.qa';");
			out.println("</script>");
			out.close();
		}

		result = boardDAO.boardDeletet(num);
		if (result == false) {
			System.out.println("게시글 삭제 실패");
			return null;
		} 
		System.out.println("게시글 삭제 성공");
		actionCommand.setRedirect(true);
		actionCommand.setPath("./BoardList.qa");
		//return actionCommand;
		return null;
		
	}

}
