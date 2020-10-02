package project.basket.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.basket.DAO.BasketDAO;


public class BasketListService implements BasketAction {

	@Override
	public BasketActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BasketDAO DAO = new BasketDAO();
		List<?> BasketList = new ArrayList<Object>();
		
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		
		BasketList = DAO.getBasketList (m_id);
		
		request.setAttribute("BasketList", BasketList);
		BasketActionCommand actionCommand = new BasketActionCommand();
		System.out.println("보드리스트서비스 입장");
		actionCommand.setRedirect(false);

		actionCommand.setPath("./basket/basketList.jsp");
		return actionCommand;
	}

}
