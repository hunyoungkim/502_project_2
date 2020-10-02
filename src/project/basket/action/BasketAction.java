package project.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BasketAction {

	public BasketActionCommand execute(HttpServletRequest request,HttpServletResponse response)
	throws Exception;
}
