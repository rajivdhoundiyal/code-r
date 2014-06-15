package com.codeproof.common.model.dto;

import java.util.Set;

public class FileDetailsDTO {

	private String fileDetailsId;
	private String name;
	private String fullPath;
	private String type;
	private Set<FileContentDTO> fileContents;
	
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
	public Set<FileContentDTO> getFileContents() {
		return fileContents;
	}
	public void setFileContents(Set<FileContentDTO> fileContents) {
		this.fileContents = fileContents;
	}
	}
