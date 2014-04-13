package com.codeproof.data.spec;

import com.codeproof.model.User;


public interface UserDataService {

	User find(String id);
	
	void save(User user);
	
	void update(User user);
}
