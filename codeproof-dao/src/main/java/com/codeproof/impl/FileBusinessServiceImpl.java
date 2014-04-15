package com.codeproof.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeproof.data.spec.FileDataService;
import com.codeproof.model.File;
import com.codeproof.spec.FileBusinessService;

public class FileBusinessServiceImpl extends AbstractBusinessService implements FileBusinessService {

	@Autowired
	FileDataService fileDataService;
	
	@Override
	public File find(String id) {
		return fileDataService.find(id);
	}

	@Override
	public void save(File file) {
		fileDataService.save(file);
	}

	@Override
	public void update(File file) {
		fileDataService.update(file);
	}

}
