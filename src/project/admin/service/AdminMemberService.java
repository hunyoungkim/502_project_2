package project.admin.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.admin.action.Action;
import project.admin.command.ActionCommand;
import project.admin.dao.AdminDAO;
import project.admin.model.AdminVO;


public class AdminMemberService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<AdminVO> arrayList = new ArrayList<AdminVO>();
		AdminDAO adminDAO = new AdminDAO();

		arrayList = adminDAO.AdminMember();

		request.setAttribute("arrayList", arrayList);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./admin/admin_member.jsp");
		return actionCommand;
	}

}
