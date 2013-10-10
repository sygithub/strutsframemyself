package com.bdqn.mystruts.action;

import java.util.Map;

public class ActionMapping {
	private String actionName;
	private String actionClass;
	private Map<String, Result> actions;
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionClass() {
		return actionClass;
	}
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}
	public Map<String, Result> getActions() {
		return actions;
	}
	public void setActions(Map<String, Result> actions) {
		this.actions = actions;
	}
	public ActionMapping(String actionName, String actionClass,
			Map<String, Result> actions) {
		super();
		this.actionName = actionName;
		this.actionClass = actionClass;
		this.actions = actions;
	}
	
}
