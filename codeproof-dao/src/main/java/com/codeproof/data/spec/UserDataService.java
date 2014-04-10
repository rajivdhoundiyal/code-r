package com.codeproof.data.spec;

import com.codeproof.model.User;


public interface UserDataService {

	User find(String id);
	
	User save(User user);
	
	User update(User user);
}
