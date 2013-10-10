package com.bdqn.mystruts.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	/**
	 * ·µ»ØÂß¼­µØÖ·
	 * @param request
	 * @return
	 */
	public String execute(HttpServletRequest request);
}
