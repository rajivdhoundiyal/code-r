package com.codeproof.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {

	@Id
	private String roleId;
	private RoleLevel roleLevel;
	private String roleDescription;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public RoleLevel getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(RoleLevel roleLevel) {
		this.roleLevel = roleLevel;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

}
