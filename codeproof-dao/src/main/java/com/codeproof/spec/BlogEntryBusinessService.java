package com.codeproof.spec;

import java.util.List;

import com.codeproof.common.model.dto.BlogEntryDTO;

public interface BlogEntryBusinessService {

	BlogEntryDTO find(String id);

	void save(BlogEntryDTO blogEntry);

	void update(BlogEntryDTO blogEntry);
	
	List<BlogEntryDTO> getBlogEntriesByReviewCode(String reviewCode);
	
	List<BlogEntryDTO> getBlogEntriesByReviewCodeAndFileName(String reviewCode, String fileName);
}
