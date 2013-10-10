package com.bdqn.mystruts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * �������xml�ļ�������ȡActionMapping����
 * @author sunyong
 *
 */
public class ActionMappingManager {
	private static Map<String, ActionMapping> mappings = new HashMap<String, ActionMapping>();
	
	/**
	 * ��ʼ����������Ҫ�ǽ���xml
	 * ����ȡActionMapping����ļ���mappings
	 * @param configName
	 */
	private void init(String configName){
		//��ȡ��ǰxml�����ļ��ڹ����е�·��,��ȡ�õ�ǰ��-->��ȡ��ǰ�������Ŀ¼-->�����Ŀ¼��Ҫȡ�ĸ��ļ�������URL���ͣ�-->��URL����ת��Ϊ�ַ���·��
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
					boolean redirect = false;//Ĭ��Ϊfalse����ת��
					String red = result.attributeValue("redirect");
					if(red!=null){
						if(red.equals("true")){
							redirect = true;//���Ϊtrue,���ض���
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
	 * �÷���������Mappings�����л�ȡ����ActionMapping����
	 * @param actionName
	 * @return
	 */
	public static ActionMapping getActionMapping(String actionName){
		return mappings.get(actionName);
	}
	/**
	 * ����һ�����췽��������ѭ�����������ļ�������ӵ�Mappings������ȥ
	 * @param configNames ���������ļ�����
	 */
	public ActionMappingManager(String[] configNames){
		for (String configName : configNames) {
			//ѭ�����ó�ʼ����������ѭ�����������ļ�
			init(configName);
		}
	}
}
