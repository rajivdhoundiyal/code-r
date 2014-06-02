package com.codeproof.data.spec;

import com.codeproof.model.ReviewRoleType;

public interface ReviewRoleDataService {

	ReviewRoleType find(String id);

	void save(ReviewRoleType reviewRole);

	void update(ReviewRoleType reviewRole);
	
	ReviewRoleType findByRoleType(String reviewRoleTypeir);
	
}
