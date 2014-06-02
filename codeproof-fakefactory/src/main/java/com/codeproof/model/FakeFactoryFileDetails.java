package com.codeproof.model;

import java.util.ArrayList;
import java.util.List;

public class FakeFactoryFileDetails {

	private FileDetails createFileDetails() {

		FileDetails fileDetails = new FileDetails();
		fileDetails.setFullPath("e:path.txt");
		fileDetails.setName("FileName");
		fileDetails.setType("txt");

		List<ReviewComment> reviewComments = new ArrayList<ReviewComment>();
		ReviewComment reviewComment1 = FakeFactoryReviewComment.createReviewComment(ReviewComment.CommentStatus.OPEN,
				ReviewComment.CommentType.ERROR, 50l, "First Comment");
		ReviewComment reviewComment2 = FakeFactoryReviewComment.createReviewComment(ReviewComment.CommentStatus.OPEN,
				ReviewComment.CommentType.COMMENT, 20l, "Second Comment");

		reviewComments.add(reviewComment1);
		reviewComments.add(reviewComment2);

		fileDetails.setReviewComments(reviewComments);

		/*
		 * List<FileContent> fileContents = new ArrayList<FileContent>();
		 * FileContent fileContent1 =
		 * FakeFactoryFileContent.createFileContentWithVersion("1.0");
		 * FileContent fileContent2 =
		 * FakeFactoryFileContent.createFileContentWithVersion("2.0");
		 * 
		 * fileContents.add(fileContent1); fileContents.add(fileContent2);
		 * 
		 * fileDetails.set
		 */

		return fileDetails;
	}

}
