package project.product.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import project.product.action.Action;
import project.product.command.ActionCommand;
import project.product.dao.ProductDAO;
import project.product.model.ProductVO;

public class ProductDetailService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = new ProductVO();
	
		System.out.println(request.getParameter("goods_num"));
		
		int num = Integer.parseInt(request.getParameter("goods_num"));
		productVO = productDAO.getDetail(num);
		if(productVO== null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("productVO", productVO);
		System.out.println(productVO);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setPath("./product/product_view.jsp");
		return actionCommand;
	
	}

}
