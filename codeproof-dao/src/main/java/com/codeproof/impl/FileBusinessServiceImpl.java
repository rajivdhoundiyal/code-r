package com.codeproof.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeproof.common.model.dto.ReviewDTO;
import com.codeproof.data.spec.FileDataService;
import com.codeproof.model.File;
import com.codeproof.spec.FileBusinessService;

@Service
public class FileBusinessServiceImpl extends AbstractBusinessService implements FileBusinessService {

	@Autowired
	private FileDataService fileDataService;

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

	@Override
	public List<ReviewDTO> getFileDetailsByReviewCode(String reviewCode) {
		return dozerConverter.convertFrom(fileDataService.getFileDetailsByReviewCode(reviewCode), ReviewDTO.class);
	}

	@Override
	public List<ReviewDTO> getFileContentByReviewCodeAndFileName(String reviewCode, String fileName) {
		return dozerConverter.convertFrom(fileDataService.getFileContentByReviewCodeAndFileName(reviewCode, fileName),
				ReviewDTO.class);
	}

}
