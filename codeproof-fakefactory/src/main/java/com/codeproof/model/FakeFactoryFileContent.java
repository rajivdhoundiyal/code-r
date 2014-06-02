package com.codeproof.model;

public class FakeFactoryFileContent {

	public static FileContent createFileContentWithVersion(String version) {
		FileContent fileContent = new FileContent();
		fileContent.setVersion(version);
		byte [] content = new byte[1];
		fileContent.setContent(content);
		
		return fileContent;
	}
	
	public static FileContent createFileContent() {
		return createFileContentWithVersion("1.0");
	}
}
