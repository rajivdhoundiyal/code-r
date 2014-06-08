package com.codereview.model;

public class RevisionLog {

	private String shortMessage;
	private String author;
	private String dateOfCommit;
	
	public String getShortMessage() {
		return shortMessage;
	}
	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDateOfCommit() {
		return dateOfCommit;
	}
	public void setDateOfCommit(String dateOfCommit) {
		this.dateOfCommit = dateOfCommit;
	}
}
