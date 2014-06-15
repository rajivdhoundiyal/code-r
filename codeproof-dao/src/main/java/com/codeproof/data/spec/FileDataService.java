package com.codeproof.data.spec;

import java.util.List;

import com.codeproof.model.File;
import com.codeproof.model.Review;

public interface FileDataService {

	File find(String id);

	void save(File file);

	void update(File file);
	
	List<Review> getFileDetailsByReviewCode(String reviewCode);
	
	List<Review> getFileContentByReviewCodeAndFileName(String reviewCode, String filePath);

}
