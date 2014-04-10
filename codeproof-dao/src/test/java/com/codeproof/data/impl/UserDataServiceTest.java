package com.codeproof.data.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.FakeFactoryUser;
import com.codeproof.model.User;

public class UserDataServiceTest extends AbstractDataTest {

	@Autowired
	UserDataService userDataService;

	@Test
	public void testFind() {
		System.out.println("Inside find user Id is ");
		User user = FakeFactoryUser.createUser();
		userDataService.save(user);
		System.out.println("user Id is : " + user.getId());
		User dbUser = userDataService.find(user.getId());
		assertNotNull(dbUser);
		assertNotNull(dbUser.getId());
	}

	@Test
	public void testSave() {
		User user = FakeFactoryUser.createUser();
		userDataService.save(user);
		assertNotNull(user.getId());
	}

	@Test
	public void testUpdate() {
		User user = FakeFactoryUser.createUser();
		userDataService.save(user);
		System.out.println(user.getId());
		User dbUser = userDataService.find(user.getId());

		dbUser.setUserName("Temp-2");

		userDataService.update(dbUser);

		dbUser = userDataService.find(user.getId());

		assertEquals("Temp-2", dbUser.getUserName());

	}

	@Test
	public void testGetReferenceClass() {
		assertEquals(User.class,
				((UserDataServiceImpl) userDataService).getReferenceClass());
	}

}
