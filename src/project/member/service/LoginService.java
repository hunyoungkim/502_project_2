package project.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.member.action.Action;
import project.member.command.ActionCommand;
import project.member.dao.MemberDAO;


public class LoginService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO memberDAO = new MemberDAO();
		String m_id = request.getParameter("m_id");
		String m_password = request.getParameter("m_password");
		
		try {
			boolean result = memberDAO.Login(m_id, m_password);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result == false) {
				out.println("<script>");
				out.println("alert('아이디 혹은 비밀번호가 틀립니다.');");
				out.println("location.href='./Login.me';");
				out.println("</script>");
				out.close();
				
			} if (m_id.equals("admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("m_id", m_id);
				out.println("<script>");
				out.println("alert('관리자로 로그인 하셨습니다.');");
				out.println("location.href='./ProductList.pr';");
				out.println("</script>");
				out.close();
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("m_id", m_id);
				out.println("<script>");
				out.println("alert('로그인 되셨습니다.');");
				out.println("location.href='./ProductList.pr';");
				out.println("</script>");
				out.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
