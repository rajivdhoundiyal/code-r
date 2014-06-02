package com.codeproof.data.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.codeproof.data.spec.ReviewDataService;
import com.codeproof.data.spec.ReviewRoleDataService;
import com.codeproof.data.spec.UserDataService;
import com.codeproof.model.FakeFactoryFile;
import com.codeproof.model.FakeFactoryReview;
import com.codeproof.model.FakeFactoryReviewRole;
import com.codeproof.model.FakeFactoryUser;
import com.codeproof.model.File;
import com.codeproof.model.Review;
import com.codeproof.model.ReviewRole;
import com.codeproof.model.ReviewRoleType;
import com.codeproof.model.User;
import com.codeproof.util.StringConstants;

public class ReviewDataServiceTest extends AbstractDataServiceTest<ReviewDataService> {

	@Autowired
	ReviewRoleDataService reviewRoleDataService;
	
	@Autowired
	UserDataService userDataService;
	
	private Review review;
	
	@Test
	public void testFind() {
		dataService.save(review);
		Review dbReview = dataService.find(review.getReviewId());
		assertNotNull(dbReview);
		assertNotNull(dbReview.getReviewId());
	}

	@Test
	public void testSave() {
		dataService.save(review);
		assertNotNull(review.getReviewId());
	}

	@Test
	public void testUpdate() {
		dataService.save(review);
		
		Review dbReview = dataService.find(review.getReviewId());

		dbReview.setReviewCode("Temp-2");

		dataService.update(dbReview);

		dbReview = dataService.find(review.getReviewId());

		assertEquals("Temp-2", dbReview.getReviewCode());

	}
	
	@Test
	public void testFindByRoleType() {
		dataService.save(review);
		
		List<Review> dbReviews = dataService.findByReviewRole(StringConstants.REVIEWEE);

		assertNotNull(dbReviews);
		
		assertEquals(1, dbReviews.size());
	}
	
	@Test
	public void testFindByReviewer() {
		dataService.save(review);
		List<Review> dbReviews = dataService.findByReviewer("User3");

		assertNotNull(dbReviews);
		
		assertEquals(1, dbReviews.size());
	}

	@Test
	public void testGetReferenceClass() {
		assertEquals(Review.class,
				((ReviewDataServiceImpl) dataService).getReferenceClass());
	}

	@Resource
	protected void setDataService(ReviewDataService reviewDataService) {
		this.dataService = reviewDataService;
	}
	
	@Before
	public void setup() {
		review = FakeFactoryReview.createReview();
		
		/*File file1 = FakeFactoryFile.createFile("V001", "Temp Content", "temp.abc", "e:/temp.abc", "abc");
		File file2 = FakeFactoryFile.createFile("V001", "Temp Content 2", "temp1.java", "e:/temp.java", "java");
		
		Map<String, File> files = new HashMap<String, File>();
		String file = file1.getFullPath().replace(".", "");
		files.put(file, file1);
		file = file2.getFullPath().replace(".", "");
		files.put(file, file2);*/
		
		//review.setFiles(files);
				
		User user1 = FakeFactoryUser.createUser("User1", "User1");
		User user2 = FakeFactoryUser.createUser("User2", "User2");
		User user3 = FakeFactoryUser.createUser("User3", "User3");
		
		userDataService.save(user1);
		userDataService.save(user2);
		userDataService.save(user3);
		
		ReviewRoleType reviewRoleType1 = FakeFactoryReviewRole.createReviewRoleReviwer();
		
		reviewRoleDataService.save(reviewRoleType1);
		
		List<ReviewRole> reviewers = new ArrayList<ReviewRole>();
		ReviewRole reviewRole1 = new ReviewRole();
		reviewRole1.setReviewerName(user1.getUserName());
		reviewRole1.setReviewRoleType(reviewRoleType1);
		
		ReviewRole reviewRole2 = new ReviewRole();
		reviewRole2.setReviewerName(user2.getUserName());
		reviewRole2.setReviewRoleType(reviewRoleType1);
		
		ReviewRole reviewRole3 = new ReviewRole();
		reviewRole3.setReviewerName(user3.getUserName());
		reviewRole3.setReviewRoleType(reviewRoleType1);
		/*reviewers.put(user1.getId(), reviewRole1.getReviewRoleId());
		reviewers.put(user2.getId(), reviewRole2.getReviewRoleId());
		reviewers.put(user3.getId(), reviewRole3.getReviewRoleId());*/
		
		reviewers.add(reviewRole1);
		reviewers.add(reviewRole2);
		reviewers.add(reviewRole3);
		
		review.setReviewers(reviewers);
		
		review.setReviewCode("#0010");
		review.setReviewDescription("First review.");

	}

}
