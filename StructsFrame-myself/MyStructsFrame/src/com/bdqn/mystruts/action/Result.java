package com.bdqn.mystruts.action;

public class Result {
	private String resultName;
	private String resultValue;
	private boolean redirect;
	public String getResultName() {
		return resultName;
	}
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	public String getResultValue() {
		return resultValue;
	}
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public Result(String resultName, String resultValue, boolean redirect) {
		super();
		this.resultName = resultName;
		this.resultValue = resultValue;
		this.redirect = redirect;
	}
	
}
