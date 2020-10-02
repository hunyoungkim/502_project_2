package project.product.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import project.product.action.Action;
import project.product.command.ActionCommand;
import project.product.dao.ProductDAO;
import project.product.model.ProductVO;

public class ProductUpdateDetail implements Action {

	
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionCommand actionCommand = new ActionCommand();
		ProductDAO DAO = new ProductDAO();
		ProductVO VO = new ProductVO();
		
		int goods_num =Integer.parseInt(request.getParameter("goods_num"));
		VO=DAO.getDetail(goods_num);
		if (VO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세 보기 성공");
		request.setAttribute("VO", VO);
		actionCommand.setRedirect(false);
		actionCommand.setPath("./product/product_update.jsp");
		
		return actionCommand;
	}

}
