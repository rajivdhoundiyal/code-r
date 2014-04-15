package com.codeproof.data.spec;

import com.codeproof.model.ReviewRole;

public interface ReviewRoleDataService {

	ReviewRole find(String id);

	void save(ReviewRole reviewRole);

	void update(ReviewRole reviewRole);
	
	ReviewRole findByRoleType(String reviewRoleType);
	
}
