package com.bdqn.mystruts.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	/**
	 * �����߼���ַ
	 * @param request
	 * @return
	 */
	public String execute(HttpServletRequest request);
}
