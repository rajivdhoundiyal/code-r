package com.codeproof.utilities;

public enum ViewConstants {
	
	INDEX("index");
	
	private String viewName;
	
	private ViewConstants(final String viewName) {
		this.viewName = viewName;
	}
	
	public String getViewName() {
		return viewName;
	}

}
