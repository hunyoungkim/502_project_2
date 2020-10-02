package project.member.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.member.action.Action;
import project.member.command.ActionCommand;
import project.member.dao.MemberDAO;
import project.member.model.MemberVO;


public class ModifyService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionCommand actionCommand = new ActionCommand();
		MemberDAO memberDAO = new MemberDAO();
		ArrayList<MemberVO> arrayList = new ArrayList<MemberVO>();
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		arrayList = memberDAO.MyPage(m_id);
		
		if (arrayList == null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		
		System.out.println("(수정)상세보기 성공");
		request.setAttribute("arrayList", arrayList);
		actionCommand.setRedirect(false);
		actionCommand.setPath("./member/member_modify.jsp");
		return actionCommand;
	}

}
