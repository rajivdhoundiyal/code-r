package com.codeproof.data.impl;

import org.springframework.stereotype.Repository;

import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.User;

@Repository("userDataService")
public class UserDataServiceImpl extends AbstractDataService<User> implements UserDataService {

	@Override
	protected Class<User> getReferenceClass() {
		return User.class;
	}
	
}
