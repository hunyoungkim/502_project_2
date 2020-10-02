package project.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.admin.command.ActionCommand;


public interface Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}
