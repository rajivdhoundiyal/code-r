package com.codeproof.model;

import com.codeproof.model.User;

public class FakeFactoryUser {

	public static User createUser() {
		User user = new User();

		user.setUserName("Temp");
		user.setPassword("Password");

		return user;
	}

}
