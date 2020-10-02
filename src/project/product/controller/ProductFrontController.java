package project.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import project.product.action.Action;
import project.product.command.ActionCommand;
import project.product.service.ProductAddService;
import project.product.service.ProductDeleteService;
import project.product.service.ProductDetailService;
import project.product.service.ProductListService;
import project.product.service.ProductSearchListService;
import project.product.service.ProductUpdateDetail;
import project.product.service.ProductUpdateService;


public class ProductFrontController extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requstURL = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requstURL.substring(contextPath.length());

		ActionCommand actionCommand = null;
		System.out.println(contextPath);
		System.out.println(pathURL);
		Action action = null;

		if (pathURL.equals("/ProductList.pr")) {
			action = new ProductListService();
			
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/ProductWrite.pr")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./product/product_write.jsp");

		} else if (pathURL.equals("/ProductAdd.pr")) {
			action = new ProductAddService();
			//System.out.println("11111");
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/ProductDetail.pr")) {
			action = new ProductDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/ProductModify.pr")) {
			action = new ProductUpdateDetail();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/ProductModifyService.pr")) {
			action = new ProductUpdateService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if(pathURL.equals("/ProductDeleteService.pr")) {
			action = new ProductDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/ProductSearchList.pr")) {
			action = new ProductSearchListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(actionCommand != null) {
			if(actionCommand.isRedirect()) {
				response.sendRedirect(actionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}
}
