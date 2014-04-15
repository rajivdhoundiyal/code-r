package com.codeproof.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeproof.data.spec.ReviewRoleDataService;
import com.codeproof.model.ReviewRole;
import com.codeproof.spec.ReviewRoleBusinessService;

public class ReviewRoleBusinessServiceImpl extends AbstractBusinessService implements ReviewRoleBusinessService {


	@Autowired
	ReviewRoleDataService reviewRoleDataService;
	
	@Override
	public ReviewRole find(String id) {
		return reviewRoleDataService.find(id);
	}

	@Override
	public void save(ReviewRole reviewRole) {
		reviewRoleDataService.save(reviewRole);
	}

	@Override
	public void update(ReviewRole reviewRole) {
		reviewRoleDataService.update(reviewRole);
	}

}
