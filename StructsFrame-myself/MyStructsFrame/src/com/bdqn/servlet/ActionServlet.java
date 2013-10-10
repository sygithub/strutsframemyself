package com.bdqn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdqn.mystruts.action.Action;
import com.bdqn.mystruts.action.ActionManager;
import com.bdqn.mystruts.action.ActionMapping;
import com.bdqn.mystruts.action.ActionMappingManager;
import com.bdqn.mystruts.action.Result;

public class ActionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ActionServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1����ȡ�����ַ��������·��
		String requestURI = request.getRequestURI();
		//2���ֽ������ַ��Ŀ����Ϊ�˻�ȡxxx.do
		String[] contexts = requestURI.split("/");
		//3����ȡxxx.do
		String requestAction = contexts[contexts.length-1];
		//4����ȡ���������xxx����Ϊֻ�и���actionName���ܴӼ����л�ȡActionMapping����
		String actionName = requestAction.substring(0,requestAction.indexOf("."));
		//5������ActionName�Ӽ����л�ȡActionMapping����
		ActionMapping am = ActionMappingManager.getActionMapping(actionName);
		//6���Ӽ����л�ȡclassName��Ŀ����Ϊ��Ҫ֪�������ĸ�Action����
		String className = am.getActionClass();
		//7������Actionʵ������
		Action myAction = ActionManager.createAction(className);
		//8��ͨ��Action��execute���������߼���ַ
		String logicPath = myAction.execute(request);
		Map<String, Result> results = am.getActions();
		String path = results.get(logicPath).getResultValue();
		boolean redirect =results.get(logicPath).isRedirect();
		if(redirect){
			response.sendRedirect(path);
		}else{
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		String myconfig = this.getInitParameter("myconfig");
		String[] configNames = null;
		if(myconfig!=null){
			if(myconfig.indexOf(",")!=-1){
				configNames = myconfig.split(",");
			}else{
				configNames =new String[]{myconfig};
			}
		}else{
			configNames =new String[]{"mystructs.xml"};
		}
		ActionMappingManager amm = new ActionMappingManager(configNames);
	}

}
