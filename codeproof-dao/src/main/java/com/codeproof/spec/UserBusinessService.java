package com.codeproof.spec;

import com.codeproof.exception.UserNotFoundException;
import com.codeproof.model.User;

public interface UserBusinessService {

	void save(User user);
	
	void update(User user);

	User find(String userId) throws UserNotFoundException;
	
	User findByUserName(String userName);
	
}
