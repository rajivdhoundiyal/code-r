package com.codereview.util;

public enum StringConstants {
	
	BASE_URL("http://localhost:8080/codeproof-webapp");
	
	private String value;
	
	StringConstants(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
