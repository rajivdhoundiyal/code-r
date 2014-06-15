package com.codeproof.common.model.dto;


public class ReviewRoleTypeDTO {

	private String reviewRoleId;
	
	private String reviewDescription;
	
	private String reviewRoleType;
	
	public enum RoleType {
		REVIEWEE("reviewee"),
		REVIEWER("reviewer"),
		OBSERVER("observer"),
		VISITOR("reviewee");
		
		private String roleType;
		
		private RoleType(String roleType) {
			this.roleType = roleType;
		}
		
		public String toString() {
			return roleType;
		}
	}
	
	public String getReviewRoleId() {
		return reviewRoleId;
	}
	public void setReviewRoleId(String reviewRoleId) {
		this.reviewRoleId = reviewRoleId;
	}
	public String getReviewDescription() {
		return reviewDescription;
	}
	public void setReviewDescription(String reviewDesc) {
		this.reviewDescription = reviewDesc;
	}
	public String getReviewRoleType() {
		return reviewRoleType;
	}
	public void setReviewRoleType(String reviewRoleType) {
		this.reviewRoleType = reviewRoleType;
	}

}
