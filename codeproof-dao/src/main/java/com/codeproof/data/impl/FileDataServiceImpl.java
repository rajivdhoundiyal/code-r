package com.codeproof.data.impl;

import org.springframework.stereotype.Repository;

import com.codeproof.data.spec.FileDataService;
import com.codeproof.model.File;

@Repository("fileDataService")
public class FileDataServiceImpl extends AbstractDataService<File> implements FileDataService {

	@Override
	protected Class<File> getReferenceClass() {
		return File.class;
	}

}
