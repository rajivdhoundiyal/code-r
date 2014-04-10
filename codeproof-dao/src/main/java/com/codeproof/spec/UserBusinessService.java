package com.codeproof.spec;

import com.codeproof.model.User;

public interface UserBusinessService {

	User save(User user);
	
	User update(User user);

	User find(String userId);

}
