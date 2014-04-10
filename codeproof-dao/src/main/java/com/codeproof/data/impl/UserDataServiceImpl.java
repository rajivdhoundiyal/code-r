package com.codeproof.data.impl;

import org.springframework.stereotype.Service;

import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.User;

@Service("userDataService")
public class UserDataServiceImpl extends AbstractDataService<User> implements UserDataService {

	@Override
	public User find(String id) {
		return super.find(id, getReferenceClass());
	}

	@Override
	public User save(User user) {
		return super.save(user);
	}

	@Override
	public User update(User user) {
		return super.update(user);
	}

	@Override
	protected Class<User> getReferenceClass() {
		return User.class;
	}
	
}
