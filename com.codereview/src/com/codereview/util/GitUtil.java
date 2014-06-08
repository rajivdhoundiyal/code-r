package com.codereview.util;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class GitUtil {

	private static RepositoryBuilder repo = new RepositoryBuilder();

	private static Git git = null;

	private static Git initialize(File file) throws IllegalArgumentException, IOException {
		if (git == null) {
			git = new Git(getRepositoryBaseOnGitDir(file));
		}
		return git;
	}

	public static Repository getRepositoryBaseOnGitDir(File file) throws IllegalArgumentException, IOException {
		return repo.findGitDir(file).setup().build();
	}

	public static RepositoryBuilder getRepositoryBuilder() throws IllegalArgumentException, IOException {
		return repo;
	}

	public static StatusCommand callStatusCommand(File file) throws NoWorkTreeException, GitAPIException,
			IllegalArgumentException, IOException {
		initialize(file);
		return git.status();
	}

	public static Status callStatus(File file) throws NoWorkTreeException, GitAPIException, IllegalArgumentException,
			IOException {
		initialize(file);
		StatusCommand sc = git.status();
		return sc.call();
	}

	public static Iterable<RevCommit> callLog(File file) throws NoWorkTreeException, GitAPIException, IllegalArgumentException,
			IOException {
		initialize(file);
		LogCommand log = git.log();
		return log.call();
	}

	public static DiffCommand callDiffCommand(File file) throws NoWorkTreeException, GitAPIException,
			IllegalArgumentException, IOException {
		initialize(file);
		return git.diff();
	}

	private static ObjectId getObjectId(String objectId) throws RevisionSyntaxException, AmbiguousObjectException,
			IncorrectObjectTypeException, IllegalArgumentException, IOException {
		return getRepositoryBaseOnGitDir(WorkbenchUtil.getProjectDirectoryAsFile()).resolve(objectId);
	}

	public static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId) throws IOException,
			MissingObjectException, IncorrectObjectTypeException {
		// from the commit we can build the tree which allows us to construct
		// the TreeParser
		RevWalk walk = new RevWalk(repository);
		RevCommit commit = walk.parseCommit(getObjectId(objectId));
		RevTree tree = walk.parseTree(commit.getTree().getId());

		CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
		ObjectReader oldReader = repository.newObjectReader();
		try {
			oldTreeParser.reset(oldReader, tree.getId());
		} finally {
			oldReader.release();
		}
		return oldTreeParser;
	}
}
