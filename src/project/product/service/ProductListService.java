package project.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import project.product.action.Action;
import project.product.command.ActionCommand;
import project.product.dao.ProductDAO;

public class ProductListService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDAO productDAO = new ProductDAO();

		List<?> productList = new ArrayList<Object>();

		int page = 1;
		int limit = 10;

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
	}
		int listcount = productDAO.getListCount();
		System.out.println("보드리스트서비스 입장1");
		productList = productDAO.getProductList(page, limit);
		
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = (((int) ((double) page / 10 +0.9)) - 1) *10 +1;
		int endpage = startpage + 10 -1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		System.out.println("보드리스트서비스 입장2");
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("productList", productList) ;
		ActionCommand actionCommand = new ActionCommand();
		System.out.println(listcount);
		System.out.println(productList);
		actionCommand.setRedirect(false);
		actionCommand.setPath("./product/product_list.jsp");
		return actionCommand;
	}
	

}
