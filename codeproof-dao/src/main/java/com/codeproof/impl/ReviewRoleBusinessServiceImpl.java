package com.codeproof.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeproof.data.spec.ReviewRoleDataService;
import com.codeproof.model.ReviewRoleType;
import com.codeproof.spec.ReviewRoleBusinessService;

public class ReviewRoleBusinessServiceImpl extends AbstractBusinessService implements ReviewRoleBusinessService {


	@Autowired
	ReviewRoleDataService reviewRoleDataService;
	
	@Override
	public ReviewRoleType find(String id) {
		return reviewRoleDataService.find(id);
	}

	@Override
	public void save(ReviewRoleType reviewRole) {
		reviewRoleDataService.save(reviewRole);
	}

	@Override
	public void update(ReviewRoleType reviewRole) {
		reviewRoleDataService.update(reviewRole);
	}

}
