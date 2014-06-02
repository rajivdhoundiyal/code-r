package com.codeproof.model;

import com.codeproof.util.StringConstants;

public class FakeFactoryReviewRole {

	public static ReviewRoleType createReviewRoleReviwer() {
		ReviewRoleType reviewRole = new ReviewRoleType();
		reviewRole.setReviewRoleType(StringConstants.REVIEWEE);
		return reviewRole;
	}

}
