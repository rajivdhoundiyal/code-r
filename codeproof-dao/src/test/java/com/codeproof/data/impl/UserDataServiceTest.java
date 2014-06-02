package com.codeproof.data.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.FakeFactoryUser;
import com.codeproof.model.User;

public class UserDataServiceTest extends AbstractDataServiceTest<UserDataService> {

	@Test
	public void testFind() {
		User user = FakeFactoryUser.createUser();
		dataService.save(user);
		User dbUser = dataService.find(user.getUserId());
		assertNotNull(dbUser);
		assertNotNull(dbUser.getUserId());
	}

	@Test
	public void testSave() {
		User user = FakeFactoryUser.createUser();
		dataService.save(user);
		assertNotNull(user.getUserId());
	}

	@Test
	public void testUpdate() {
		User user = FakeFactoryUser.createUser();
		dataService.save(user);
		User dbUser = dataService.find(user.getUserId());

		dbUser.setUserName("Temp-2");

		dataService.update(dbUser);

		dbUser = dataService.find(user.getUserId());

		assertEquals("Temp-2", dbUser.getUserName());

	}

	@Test
	public void testGetReferenceClass() {
		assertEquals(User.class, ((UserDataServiceImpl) dataService).getReferenceClass());
	}

	@Resource
	protected void setDataService(UserDataService userDataService) {
		this.dataService = userDataService;
	}

}
