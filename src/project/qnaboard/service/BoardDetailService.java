package project.qnaboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.qnaboard.action.Action;
import project.qnaboard.command.ActionCommand;
import project.qnaboard.dao.BoardDAO;
import project.qnaboard.model.BoardVO;

public class BoardDetailService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardVO boardDTO = new BoardVO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		boardDAO.setReadCountUpdate(num);
		boardDTO = boardDAO.getDetail(num);
		if(boardDTO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("boardDTO", boardDTO);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setPath("./qnaboard/qnaboard_view.jsp");
		return actionCommand;
	}

}
