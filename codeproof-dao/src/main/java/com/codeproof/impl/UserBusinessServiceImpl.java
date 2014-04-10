package com.codeproof.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.User;
import com.codeproof.spec.UserBusinessService;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

	@Autowired
	UserDataService userDataService;

	@Override
	public User find(String userId) {
		return userDataService.find(userId);
	}

	@Override
	public User save(User user) {
		return userDataService.save(user);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
