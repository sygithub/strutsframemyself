package com.bdqn.mystruts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 负责解析xml文件，并获取ActionMapping对象
 * @author sunyong
 *
 */
public class ActionMappingManager {
	private static Map<String, ActionMapping> mappings = new HashMap<String, ActionMapping>();
	
	/**
	 * 初始化方法，主要是解析xml
	 * 并获取ActionMapping对象的集合mappings
	 * @param configName
	 */
	private void init(String configName){
		//获取当前xml配置文件在工程中的路径,先取得当前类-->获取当前类的运行目录-->在这个目录下要取哪个文件（返回URL类型）-->将URL类型转换为字符串路径
		String xmlPath = this.getClass().getClassLoader().getResource(configName).getPath();
		try {
			SAXReader sax = new SAXReader();
			Document doc = sax.read(xmlPath);
			Element root = doc.getRootElement();
			Element actionElement = root.element("actions");
			List<Element> actionList = actionElement.elements("action");
			for (Element action : actionList) {
				String actionName = action.attributeValue("name");
				String actionClass = action.attributeValue("class");
				List<Element> resultList = action.elements("result");
				Map<String, Result> results = new HashMap<String, Result>();
				for (Element result : resultList) {
					String resultName = result.attributeValue("name");
					String resultValue = result.getText();
					boolean redirect = false;//默认为false，即转发
					String red = result.attributeValue("redirect");
					if(red!=null){
						if(red.equals("true")){
							redirect = true;//如果为true,即重定向
						}
					}
					Result res = new Result(resultName, resultValue, redirect);
					results.put(resultName, res);
				}
				ActionMapping act = new ActionMapping(actionName, actionClass, results);
				mappings.put(actionName, act);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 该方法用来在Mappings集合中获取单个ActionMapping对象
	 * @param actionName
	 * @return
	 */
	public static ActionMapping getActionMapping(String actionName){
		return mappings.get(actionName);
	}
	/**
	 * 创建一个构造方法，用来循环解析配置文件，并添加到Mappings集合中去
	 * @param configNames 传入配置文件数组
	 */
	public ActionMappingManager(String[] configNames){
		for (String configName : configNames) {
			//循环调用初始化方法，即循环解析配置文件
			init(configName);
		}
	}
}
