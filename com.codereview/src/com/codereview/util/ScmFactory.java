package com.codereview.util;

import com.codereview.scm.GitService;
import com.codereview.scm.IScmService;

public final class ScmFactory {
	
	private static GitService gitService;
	
	public static IScmService getScmProvider(Class sourceCodeProvider) {
		if(sourceCodeProvider.equals(GitService.class)) {
			return (gitService == null) ? new GitService() : gitService;
		}
		
		return null;
	}

}
