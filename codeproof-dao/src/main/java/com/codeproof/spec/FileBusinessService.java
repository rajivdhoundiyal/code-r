package com.codeproof.spec;

import java.util.List;

import com.codeproof.common.model.dto.ReviewDTO;
import com.codeproof.model.File;

public interface FileBusinessService {

	File find(String id);

	void save(File file);

	void update(File file);
	
	List<ReviewDTO> getFileDetailsByReviewCode(String reviewCode);
	
	List<ReviewDTO> getFileContentByReviewCodeAndFileName(String reviewCode, String filePath);
	
}
