package com.codeproof.spec;

import com.codeproof.model.ReviewRole;

public interface ReviewRoleBusinessService {
	
	ReviewRole find(String id);

	void save(ReviewRole reviewRole);

	void update(ReviewRole reviewRole);

}
