package com.codeproof.data.spec;

import java.util.List;

import com.codeproof.model.BlogEntry;

public interface BlogEntryDataService {

	BlogEntry find(String id);

	void save(BlogEntry blogEntry);

	void update(BlogEntry blogEntry);
	
	List<BlogEntry> getBlogEntriesByReviewCode(String reviewCode);
	
	List<BlogEntry> getBlogEntriesByReviewCodeAndFileName(String reviewCode, String fileName);
}
