package com.codereview.handler;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.ui.packageview.PackageFragmentRootContainer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.internal.Workbench;

import com.codereview.util.GitUtil;
import com.codereview.util.WorkbenchUtil;
import com.codereview.views.CodeReviewDialog;

public class AddToReviewHandler extends AbstractHandler {

	public Object execute(ExecutionEvent executionEnvironment) throws ExecutionException {
		File file = WorkbenchUtil.getProjectDirectoryAsFile();
		// repo.setGitDir(file);
		Set<String> bigSet = null;
		Set<File> filePathSet = null;
		try {
			//Repository repos = repo.findGitDir(file).setup().build();
			StatusCommand sc = GitUtil.callStatusCommand(file);
			Status status = GitUtil.callStatus(file);
			sc.addPath(file.getParent());
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
				value = sc.getPaths().get(0) + "/" + value;
				File fileTemp = new File(value);
				filePathSet.add(fileTemp);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoWorkTreeException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

		CodeReviewDialog crd = null;
		try {
			crd = new CodeReviewDialog(Workbench.getInstance().getDisplay().getActiveShell(),
					GitUtil.getRepositoryBuilder().getWorkTree(), filePathSet);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crd.create();
		
		crd.open();
		return null;
	}


	@Override
	protected void fireHandlerChanged(HandlerEvent handlerEvent) {
		super.fireHandlerChanged(handlerEvent);
	}
}
