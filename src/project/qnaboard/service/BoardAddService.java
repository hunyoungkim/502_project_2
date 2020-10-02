package project.qnaboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.qnaboard.action.Action;
import project.qnaboard.command.ActionCommand;
import project.qnaboard.dao.BoardDAO;
import project.qnaboard.model.BoardVO;

public class BoardAddService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardVO boardDTO = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		ActionCommand actionCommand = new ActionCommand();
		String realFolder = "";
		String saveFolder = "./qnaboardUpload";
		
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		int fileSize = 10 * 1024 *1024;
		boolean result = false;
		try {
			MultipartRequest multipartRequest  = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			String subject =  multipartRequest.getParameter("category");
			subject =  subject + multipartRequest.getParameter("subject");
			
			boardDTO.setM_id(multipartRequest.getParameter("m_id"));

			boardDTO.setSubject(subject);
			boardDTO.setContent(multipartRequest.getParameter("content"));
			boardDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			
			result = boardDAO.boardInsert(boardDTO);
			if (result ==false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardList.qa");
			return actionCommand;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return null;
	}

}
