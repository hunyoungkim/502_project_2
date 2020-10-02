package project.product.service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.product.action.Action;
import project.product.command.ActionCommand;
import project.product.dao.ProductDAO;
import project.product.model.ProductVO;



public class ProductUpdateService implements Action {

	
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		ProductDAO DAO = new ProductDAO();
		ProductVO VO = new ProductVO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./productimage";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {
			//System.out.println("이곳은 업데이트 서비스");
			MultipartRequest multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			int goods_num = Integer.parseInt(multipartRequest.getParameter("goods_num"));
			
			VO.setGoods_num(goods_num);
			VO.setGoods_name(multipartRequest.getParameter("goods_name"));
			VO.setCategory(multipartRequest.getParameter("category"));
			VO.setEnergy_efcnc(multipartRequest.getParameter("energy_efcnc"));
			VO.setLiter(multipartRequest.getParameter("liter"));
			VO.setPrice(multipartRequest.getParameter("price"));
			VO.setImage(multipartRequest.getFilesystemName((String) multipartRequest.getFileNames().nextElement()));
			VO.setOld_file(multipartRequest.getParameter("old_file"));			
			result = DAO.ProductUpdate(VO);
			
			if (result == false) {
				System.out.println("상품 수정 실패");
				return null;
			}
			System.out.println("상품 수정 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./ProductDetail.pr?goods_num=" + VO.getGoods_num());
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
