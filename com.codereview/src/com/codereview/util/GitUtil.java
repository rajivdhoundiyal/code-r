package com.codereview.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
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

import com.codeproof.common.model.dto.FileContentDTO;
import com.codeproof.common.model.dto.FileDetailsDTO;

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

	public static Status callStatus(File file) throws NoWorkTreeException, GitAPIException,
			IllegalArgumentException, IOException {
		initialize(file);
		StatusCommand sc = git.status();
		return sc.call();
	}

	public static Iterable<RevCommit> callLog(File file) throws NoWorkTreeException, GitAPIException,
			IllegalArgumentException, IOException {
		initialize(file);
		LogCommand log = git.log();
		return log.call();
	}

	public static Set<FileDetailsDTO> callDiffCommand(File file, String newRevision, String oldRevision)
			throws NoWorkTreeException, GitAPIException, IllegalArgumentException, IOException {
		initialize(file);
		Repository repository = git.getRepository();
		AbstractTreeIterator newRevisionIter = prepareTreeParser(repository, newRevision);
		AbstractTreeIterator oldRevisionIter = prepareTreeParser(repository, oldRevision);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DiffFormatter df = new DiffFormatter(out);
		df.setRepository(repository);
		df.setDiffComparator(RawTextComparator.DEFAULT);
		df.setDetectRenames(true);
		df.setContext(300);
		List<DiffEntry> diffs = df.scan(newRevisionIter, oldRevisionIter);

		FileDetailsDTO fileDetailsDTO = null;
		FileContentDTO fileContentDTO = null;
		Set<FileContentDTO> fileContents = null;
		Set<FileDetailsDTO> fileDetails = new HashSet<FileDetailsDTO>();
		File fileName;
		String path;
		for (DiffEntry diffEntry : diffs) {
			df.format(diffEntry);
			String diffText = out.toString("UTF-8");
			System.out.println(diffText);
			fileDetailsDTO = new FileDetailsDTO();

			path = getFilePath(diffEntry);
			fileName = new File(path);
			fileDetailsDTO.setFullPath(fileName.getAbsolutePath());
			fileDetailsDTO.setName(fileName.getName());
			fileDetailsDTO.setType(getFileExtension(fileName));

			fileContents = new HashSet<FileContentDTO>();

			fileContentDTO = new FileContentDTO();

			fileContentDTO.setVersion(getVersion(diffEntry));
			fileContentDTO.setContent(diffText.getBytes());
			fileContents.add(fileContentDTO);
			fileDetailsDTO.setFileContents(fileContents);

			fileDetails.add(fileDetailsDTO);
			out.reset();
		}

		return fileDetails;
	}
	private static ObjectId getObjectId(String objectId) throws RevisionSyntaxException, AmbiguousObjectException,
			IncorrectObjectTypeException, IllegalArgumentException, IOException {
		return getRepositoryBaseOnGitDir(WorkbenchUtil.getProjectDirectoryAsFile()).resolve(objectId);
	}

	private static String getFilePath(DiffEntry diffEntry) {
		if (diffEntry.getChangeType().equals(ChangeType.DELETE)) {
			return diffEntry.getOldPath();
		}
		return diffEntry.getNewPath();
	}

	private static String getVersion(DiffEntry diffEntry) {
		if (diffEntry.getChangeType().equals(ChangeType.DELETE)) {
			return diffEntry.getOldId().name();
		}

		return diffEntry.getNewId().name();
	}

	private static String getFileExtension(File file) {
		String fileName = file.getName();
		int i = fileName.lastIndexOf('.');

		if (i > 0) {
			return fileName.substring(i + 1);
		}

		return null;
	}

	public static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId)
			throws IOException, MissingObjectException, IncorrectObjectTypeException {
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
