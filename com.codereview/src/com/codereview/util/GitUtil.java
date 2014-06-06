package com.codereview.util;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;

public class GitUtil {

	private static RepositoryBuilder repo = new RepositoryBuilder();

	private static Git git = null;

	private static Git initialize(File file) throws IllegalArgumentException, IOException {
		if (git == null) {
			git = new Git(getRepositoryBaseOnGitDir(file));
		}
		return git;
	}

	private static Repository getRepositoryBaseOnGitDir(File file) throws IllegalArgumentException, IOException {
		return repo.findGitDir(file).setup().build();
	}
	
	public static RepositoryBuilder getRepositoryBuilder() throws IllegalArgumentException, IOException {
		return repo;
	}
	
	public static StatusCommand callStatusCommand(File file) throws NoWorkTreeException, GitAPIException, IllegalArgumentException, IOException {
		initialize(file);
		return git.status();
	}
	
	public static Status callStatus(File file) throws NoWorkTreeException, GitAPIException, IllegalArgumentException, IOException {
		initialize(file);
		StatusCommand sc = git.status();
		return sc.call();
	}
	
	public static DiffCommand callDiffCommand(File file) throws NoWorkTreeException, GitAPIException, IllegalArgumentException, IOException {
		initialize(file);
		return git.diff();
	}

}
