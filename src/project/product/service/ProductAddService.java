package project.product.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.product.action.Action;
import project.product.command.ActionCommand;
import project.product.dao.ProductDAO;
import project.product.model.ProductVO;

public class ProductAddService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ProductDAO productDAO = new ProductDAO();
		ProductVO productVO = new ProductVO();
		
		ActionCommand actionCommand = new ActionCommand();
		String realFolder = "";
		String saveFolder = "./productimage";
		
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		int fileSize = 10 * 1024 *1024;
		boolean result = false;
		try {
			MultipartRequest multipartRequest  = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			productVO.setGoods_name(multipartRequest.getParameter("goods_name"));
			productVO.setCategory(multipartRequest.getParameter("category"));
			productVO.setEnergy_efcnc(multipartRequest.getParameter("energy_efcnc"));
			String price = multipartRequest.getParameter("price");
			//price += "만원";
			productVO.setPrice(price+="만원");
			productVO.setLiter(multipartRequest.getParameter("liter"));
			productVO.setImage(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			
			result = productDAO.ProductInsert(productVO);
			System.out.println(productVO);
			if (result ==false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./ProductList.pr");
			return actionCommand;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return null;
	}

}
