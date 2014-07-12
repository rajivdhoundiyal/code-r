package com.codeproof.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeproof.common.model.dto.BlogEntryDTO;
import com.codeproof.data.spec.BlogEntryDataService;
import com.codeproof.model.BlogEntry;
import com.codeproof.spec.BlogEntryBusinessService;

@Service("blogEntryBusinessService")
public class BlogEntryBusinessServiceImpl extends AbstractBusinessService<BlogEntryDTO, BlogEntry>
		implements
			BlogEntryBusinessService {

	@Autowired
	BlogEntryDataService blogEntryDataService;

	@Override
	public BlogEntryDTO find(String id) {
		return dozerConverter.convertFrom(blogEntryDataService.find(id), BlogEntryDTO.class);
	}

	@Override
	public void save(BlogEntryDTO blogEntry) {
		blogEntryDataService.save(dozerConverter.convertTo(blogEntry, BlogEntry.class));
	}

	@Override
	public void update(BlogEntryDTO blogEntry) {
		blogEntryDataService.update(dozerConverter.convertTo(blogEntry, BlogEntry.class));
	}

	@Override
	public List<BlogEntryDTO> getBlogEntriesByReviewCode(String reviewCode) {
		return dozerConverter.convertFrom(blogEntryDataService.getBlogEntriesByReviewCode(reviewCode),
				BlogEntryDTO.class);
	}

	@Override
	public List<BlogEntryDTO> getBlogEntriesByReviewCodeAndFileName(String reviewCode, String fileName) {
		return dozerConverter.convertFrom(
				blogEntryDataService.getBlogEntriesByReviewCodeAndFileName(reviewCode, fileName), BlogEntryDTO.class);
	}
}
