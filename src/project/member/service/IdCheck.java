package project.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.member.action.Action;
import project.member.command.ActionCommand;
import project.member.dao.MemberDAO;

public class IdCheck implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ActionCommand actionCommand = new ActionCommand();
			MemberDAO memberDAO = new MemberDAO();
			
			boolean result = false;
			
			try {
				String m_id = request.getParameter("id");
				System.out.println(m_id);
				if (m_id.equals("")) {
					System.out.println("아이디가 담기지 않았습니다.");
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('아이디를 입력해주세요.');");
					out.println("location.href='./MemberListService.me';");
					out.println("</script>");
					out.close();
					return null;
				}
				
				result = memberDAO.IdCheck(m_id);
				String check="";
				if (result == false) {
					check = "false";
					request.setAttribute("check", check);
					System.out.println("이미 있는 아이디입니다.");
					actionCommand.setRedirect(false);
					actionCommand.setPath("./member/member.jsp");
					return actionCommand;
				}
				request.setAttribute("checkId", m_id);
				System.out.println("중복체크 완료되었습니다.");
				check = "true";
				request.setAttribute("check", check);
				actionCommand.setRedirect(false);
				actionCommand.setPath("./member/member.jsp");
				return actionCommand;
			} catch (Exception e) {
				
			}
		return null;
	}

}
