package project.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.qnaboard.command.ActionCommand;

public interface Action {

	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception;
	
}
