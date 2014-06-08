package com.codereview.scm;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;

import com.codereview.exception.VersionControlException;
import com.codereview.i18n.I18NResources;
import com.codereview.model.FileDiff;
import com.codereview.model.RevisionLog;
import com.codereview.util.GitUtil;
import com.codereview.util.StringConstants;
import com.codereview.util.WorkbenchUtil;

public class GitService implements IScmService {

	public List<RevisionLog> getRevisionLogs() throws VersionControlException {
		Iterable<RevCommit> logs = null;
		List<RevisionLog> revCommits = new ArrayList<RevisionLog>();
		try {
			logs = GitUtil.callLog(WorkbenchUtil.getProjectDirectoryAsFile());
			RevisionLog revLog;
			for (RevCommit revCommit : logs) {
				revLog = new RevisionLog();
				PersonIdent personIdent = revCommit.getAuthorIdent();
				revLog.setAuthor(personIdent.getName());
				revLog.setShortMessage(revCommit.getShortMessage());
				DateFormat dateFormat = new SimpleDateFormat(I18NResources.DATE_FORMAT);
				revLog.setDateOfCommit(dateFormat.format(personIdent.getWhen()));
				revCommits.add(revLog);
			}

			return revCommits;
		} catch (NoWorkTreeException e) {
			throw new VersionControlException(e.getCause());
		} catch (IllegalArgumentException e) {
			throw new VersionControlException(e.getCause());
		} catch (GitAPIException e) {
			throw new VersionControlException(e.getCause());
		} catch (IOException e) {
			throw new VersionControlException(e.getCause());
		}
	}

	public List<FileDiff> getFileDiff(String newVersion, String oldVersion) throws VersionControlException {
		try {
			DiffCommand diffCommand = GitUtil.callDiffCommand(WorkbenchUtil.getProjectDirectoryAsFile());
			List<DiffEntry> diffEntries = diffCommand.call();
		} catch (NoWorkTreeException e) {
			throw new VersionControlException(e.getCause());
		} catch (IllegalArgumentException e) {
			throw new VersionControlException(e.getCause());
		} catch (GitAPIException e) {
			throw new VersionControlException(e.getCause());
		} catch (IOException e) {
			throw new VersionControlException(e.getCause());
		}
		return null;
	}

	public Set<File> getFileStatus(File file) throws VersionControlException {

		Set<String> bigSet = null;
		Set<File> filePathSet = null;

		try {

			StatusCommand sc;
			sc = GitUtil.callStatusCommand(file);
			Status status = GitUtil.callStatus(file);
			//sc.addPath(file.getParent());

			bigSet = new HashSet<String>();
			filePathSet = new HashSet<File>();

			bigSet.addAll(status.getModified());
			bigSet.addAll(status.getChanged());
			bigSet.addAll(status.getAdded());
			bigSet.addAll(status.getMissing());
			bigSet.addAll(status.getUntrackedFolders());
			bigSet.addAll(status.getUncommittedChanges());
			bigSet.addAll(status.getUntracked());

			for (String value : bigSet) {
				value = file.getAbsolutePath() + StringConstants.PATH_SEPERATOR + value;
				File fileTemp = new File(value);
				filePathSet.add(fileTemp);
			}

			return filePathSet;
		} catch (NoWorkTreeException e) {
			throw new VersionControlException(e.getCause());
		} catch (IllegalArgumentException e) {
			throw new VersionControlException(e.getCause());
		} catch (GitAPIException e) {
			throw new VersionControlException(e.getCause());
		} catch (IOException e) {
			throw new VersionControlException(e.getCause());
		}
	}

}
