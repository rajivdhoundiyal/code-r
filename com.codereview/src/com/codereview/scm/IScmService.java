package com.codereview.scm;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.eclipse.jgit.lib.ObjectId;

import com.codereview.exception.VersionControlException;
import com.codereview.model.FileDiff;
import com.codereview.model.RevisionLog;

public interface IScmService {
	
	List<RevisionLog> getRevisionLogs() throws VersionControlException;
	List<FileDiff> getFileDiff(ObjectId newVersion, ObjectId oldVersion) throws VersionControlException;
	Set<File> getFileStatus(File files) throws VersionControlException;

}
