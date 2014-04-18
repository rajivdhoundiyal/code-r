package com.codeproof.model;

public class FakeFactoryFile {

	public static File createFile(String version, String content, String fileName, String filePath, String fileType) {
		File file = new File();
		file.setContent(content);
		file.setVersion(version);
		file.setFullPath(filePath);
		file.setName(fileName);
		file.setType(fileType);
		return file;
	}

}
