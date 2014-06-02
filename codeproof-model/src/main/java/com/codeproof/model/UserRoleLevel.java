package com.codeproof.model;

public enum UserRoleLevel {
	
	ADMIN("Admin");
	
	private String roleLevel;
	
	UserRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	
	String getRoleLevel() {
		return this.roleLevel;
	}

}
