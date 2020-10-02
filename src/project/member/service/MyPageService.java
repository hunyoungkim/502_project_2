package project.member.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.member.action.Action;
import project.member.command.ActionCommand;
import project.member.dao.MemberDAO;
import project.member.model.MemberVO;


public class MyPageService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<MemberVO> arrayList = new ArrayList<MemberVO>();
		MemberDAO memberDAO = new MemberDAO();
		
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		System.out.println(m_id);
		arrayList = memberDAO.MyPage(m_id);
		
		request.setAttribute("mypage", arrayList);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setRedirect(false); 
		actionCommand.setPath("./member/mypage.jsp");
		return actionCommand;
	}

}
