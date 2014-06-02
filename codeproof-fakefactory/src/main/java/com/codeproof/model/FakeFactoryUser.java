package com.codeproof.model;


public class FakeFactoryUser {

	public static User createUser() {
		User user = new User();

		user.setUserName("Temp");
		user.setPassword("Password");
		UserRole role = new UserRole();
		role.setRoleLevel(UserRoleLevel.ADMIN);
		role.setRoleDescription("Admin Role");
		
		//user.setUserRole(role);

		return user;
	}
	
	public static User createUser(String userName, String password) {
		User user = new User();

		user.setUserName(userName);
		user.setPassword(password);
		UserRole role = new UserRole();
		role.setRoleLevel(UserRoleLevel.ADMIN);
		role.setRoleDescription("Admin Role");
		
		//user.setUserRole(role);

		return user;
	}

}
