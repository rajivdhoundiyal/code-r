package com.codeproof.data.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.User;

@Repository("userDataService")
public class UserDataServiceImpl extends AbstractDataService<User> implements UserDataService {

	@Override
	protected Class<User> getReferenceClass() {
		return User.class;
	}

	@Override
	public User findByUserName(String userName) {
		User user = mongoTemplate.findOne(
				 new Query(Criteria.where("userName").is(userName)),
				 User.class);
		return user;
	}
	
}
