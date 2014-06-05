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

import com.codereview.views.CodeReviewDialog;

public class AddToReviewHandler extends AbstractHandler {

	public Object execute(ExecutionEvent executionEnvironment) throws ExecutionException {
		IProject project = getCurrentProject();
		File file = new File(project.getRawLocationURI());
		RepositoryBuilder repo = new RepositoryBuilder();
		// repo.setGitDir(file);
		Set<String> bigSet = null;
		Set<File> filePathSet = null;
		try {
			Repository repos = repo.findGitDir(file).setup().build();
			Git git = new Git(repos);
			StatusCommand sc = git.status();
			Status status = sc.call();
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

		CodeReviewDialog crd = new CodeReviewDialog(Workbench.getInstance().getDisplay().getActiveShell(),
				repo.getWorkTree(), filePathSet);
		crd.create();
		
		crd.open();
		return null;
	}

	public static IProject getCurrentProject() {
		ISelectionService selectionService = Workbench.getInstance().getActiveWorkbenchWindow()
				.getSelectionService();

		ISelection selection = selectionService.getSelection();

		IProject project = null;
		if (selection instanceof IStructuredSelection) {
			for (Object element : ((IStructuredSelection) selection).toList()) {
				if (element instanceof IResource) {
					project = ((IResource) element).getProject();
				} else if (element instanceof PackageFragmentRootContainer) {
					IJavaProject jProject = ((PackageFragmentRootContainer) element).getJavaProject();
					project = jProject.getProject();
				} else if (element instanceof IJavaElement) {
					IJavaProject jProject = ((IJavaElement) element).getJavaProject();
					project = jProject.getProject();
				}
			}
		}
		return project;
	}

	@Override
	protected void fireHandlerChanged(HandlerEvent handlerEvent) {
		super.fireHandlerChanged(handlerEvent);
	}
}
