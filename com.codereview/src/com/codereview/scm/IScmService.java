package com.codereview.scm;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.codereview.exception.VersionControlException;
import com.codereview.model.FileDiff;
import com.codereview.model.RevisionLog;

public interface IScmService {
	
	List<RevisionLog> getRevisionLogs() throws VersionControlException;
	List<FileDiff> getFileDiff(String newVersion, String oldVersion) throws VersionControlException;
	Set<File> getFileStatus(File files) throws VersionControlException;

}
