package com.codeproof.spec;

import com.codeproof.model.ReviewRoleType;

public interface ReviewRoleBusinessService {
	
	ReviewRoleType find(String id);

	void save(ReviewRoleType reviewRole);

	void update(ReviewRoleType reviewRole);

}
