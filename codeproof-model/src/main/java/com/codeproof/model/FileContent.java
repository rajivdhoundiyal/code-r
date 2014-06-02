package com.codeproof.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FileContent {

	@Id
	private String fileContentId;
	private String version;
	private byte[] content;
	private List<ReviewComment> reviewComments;
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
	public List<ReviewComment> getReviewComments() {
		return reviewComments;
	}
	public void setReviewComments(List<ReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}
	
}
