package com.bdqn.mystruts.action;

/**
 * 负责创建Action对象实例
 * @author sunyong
 *
 */
public class ActionManager {
	public static Action myaction = null;
	/**
	 * 通过类创建该类的实例对象
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
	 * 通过类路径字符串获取类的类型
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
