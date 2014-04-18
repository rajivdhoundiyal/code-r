package com.codeproof.model;

public enum RoleLevel {
	
	ADMIN("Admin");
	
	private String roleLevel;
	
	RoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	
	String getRoleLevel() {
		return this.roleLevel;
	}

}
