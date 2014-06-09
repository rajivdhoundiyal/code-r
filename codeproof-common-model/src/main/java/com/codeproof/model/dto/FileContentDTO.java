package com.codeproof.model.dto;

import java.util.List;

public class FileContentDTO {

	private String fileContentId;
	private String version;
	private byte[] content;
	private List<ReviewCommentDTO> reviewComments;
	private String transactionStatus;
	
	public String getFileContentId() {
		return fileContentId;
	}
	public void setFileContentId(String fileContentId) {
		this.fileContentId = fileContentId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public List<ReviewCommentDTO> getReviewComments() {
		return reviewComments;
	}
	public void setReviewComments(List<ReviewCommentDTO> reviewComments) {
		this.reviewComments = reviewComments;
	}
	
}
