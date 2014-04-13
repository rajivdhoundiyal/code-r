package com.codeproof.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.User;
import com.codeproof.spec.UserBusinessService;

@Service("userBusinessService")
public class UserBusinessServiceImpl implements UserBusinessService {

	@Autowired
	UserDataService userDataService;

	@Override
	public User find(String userId) {
		return userDataService.find(userId);
	}

	@Override
	public void save(User user) {
		userDataService.save(user);
	}

	@Override
	public void update(User user) {
		userDataService.update(user);
	}

}
