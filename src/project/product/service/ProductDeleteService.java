package project.product.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import project.product.action.Action;
import project.product.command.ActionCommand;
import project.product.dao.ProductDAO;

public class ProductDeleteService implements Action {



	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		boolean result = false;
		
		int goods_num = Integer.parseInt(request.getParameter("goods_num"));
		ProductDAO DAO = new ProductDAO();

		
		result= DAO.ProductDelete(goods_num);

		if(result == false) {
			System.out.println("상품 삭제 실패");
			return null;
		}
		System.out.println("상품 삭제 성공");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제하였습니다.');");
		out.println("location.href='./ProductList.pr'");
		out.println("</script>");
		out.close();
		actionCommand.setRedirect(true);
		actionCommand.setPath("./ProductList.do");
		return null;
	}

}
