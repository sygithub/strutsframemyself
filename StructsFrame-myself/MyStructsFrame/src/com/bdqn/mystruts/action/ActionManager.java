package com.bdqn.mystruts.action;

/**
 * ���𴴽�Action����ʵ��
 * @author sunyong
 *
 */
public class ActionManager {
	public static Action myaction = null;
	/**
	 * ͨ���ഴ�������ʵ������
	 * @param className
	 * @return
	 */
	public static Action createAction(String className){
		//Action myaction = null;
		try {
			myaction = (Action)loadClass(className).newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return myaction;
	} 
	
	/**
	 * ͨ����·���ַ�����ȡ�������
	 * @param className
	 * @return
	 */
	public static Class loadClass(String className){
		Class myclass = null;
		try {
			myclass = Thread.currentThread().getContextClassLoader().loadClass(className);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(myclass == null){
			try {
				myclass = Class.forName(className);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return myclass;
	}
}
