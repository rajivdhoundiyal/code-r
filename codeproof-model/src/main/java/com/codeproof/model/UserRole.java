package com.codeproof.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserRole {

	@Id
	private String roleId;
	private UserRoleLevel roleLevel;
	private String roleDescription;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public UserRoleLevel getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(UserRoleLevel roleLevel) {
		this.roleLevel = roleLevel;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

}
