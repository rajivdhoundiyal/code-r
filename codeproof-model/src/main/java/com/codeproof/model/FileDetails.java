package com.codeproof.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FileDetails {

	@Id
	private String fileDetailsId;
	private String name;
	private String fullPath;
	private String type;
	private List<String> fileContentsIds;
	
	public String getFileDetailsId() {
		return fileDetailsId;
	}
	public void setFileDetailsId(String fileDetailsId) {
		this.fileDetailsId = fileDetailsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getFileContentsIds() {
		return fileContentsIds;
	}
	public void setFileContentsIds(List<String> fileContentsIds) {
		this.fileContentsIds = fileContentsIds;
	}
}
