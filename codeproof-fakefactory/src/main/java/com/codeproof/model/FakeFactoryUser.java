package com.codeproof.model;


public class FakeFactoryUser {

	public static User createUser() {
		User user = new User();

		user.setUserName("Temp");
		user.setPassword("Password");
		Role role = new Role();
		role.setRoleLevel(RoleLevel.ADMIN);
		role.setRoleDecription("Admin Role");
		
		user.setUserRole(role);

		return user;
	}
	
	public static User createUser(String userName, String password) {
		User user = new User();

		user.setUserName(userName);
		user.setPassword(password);
		Role role = new Role();
		role.setRoleLevel(RoleLevel.ADMIN);
		role.setRoleDecription("Admin Role");
		
		user.setUserRole(role);

		return user;
	}

}
