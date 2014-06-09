package com.codereview.views;

import static com.codereview.i18n.I18NResources.TITLE_REVIEW_CREATION;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.FakedTrackingVariable;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.codereview.exception.VersionControlException;
import com.codereview.model.RevisionLog;
import com.codereview.scm.GitService;
import com.codereview.scm.IScmService;
import com.codereview.util.GitUtil;
import com.codereview.util.ScmFactory;
import com.codereview.util.StringConstants;

public class CodeReviewWizard extends Wizard {

	protected SelectVersionWizardPage versionWizPage;
	protected SelectFilesWizardPage createWizPage;
	List sharedData = new ArrayList();

	public CodeReviewWizard() {
		super();
		setDefaultPageImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("com.codereview",
				"icons/paw-64.png"));
		setNeedsProgressMonitor(true);
	}

	@Override
	public String getWindowTitle() {
		return TITLE_REVIEW_CREATION;
	}

	@Override
	public void addPages() {
		versionWizPage = new SelectVersionWizardPage(sharedData);
		createWizPage = new SelectFilesWizardPage(sharedData);
		addPage(versionWizPage);
		addPage(createWizPage);
	}

	@Override
	public boolean performFinish() {
		sharedData.clear();
		for (Object revLog : versionWizPage.getCheckTblVwr().getCheckedElements()) {
			sharedData.add(revLog);
		}
		if (diff2RemoteRevision()) {
			RevisionLog revLogNew = (RevisionLog) sharedData.get(0);
			RevisionLog revLogOld = (RevisionLog) sharedData.get(1);
			ObjectId revisionIdNew = revLogNew.getRevisionId();
			ObjectId revisionIdOld = revLogOld.getRevisionId();

			IScmService iScmService = ScmFactory.getScmProvider(GitService.class);

			try {
				iScmService.getFileDiff(revisionIdNew, revisionIdOld);
			} catch (VersionControlException e) {
				e.printStackTrace();
			}
		} else {

			RevisionLog revLogOld = (RevisionLog) sharedData.get(0);
			ObjectId revisionIdOld = revLogOld.getRevisionId();

			IScmService iScmService = ScmFactory.getScmProvider(GitService.class);

			try {
				iScmService.getFileDiff(null, revisionIdOld);
			} catch (VersionControlException e) {
				e.printStackTrace();
			}

		}
		return true;
	}

	@Override
	public boolean canFinish() {
		return canDoFinish();
	}

	protected boolean canDoFinish() {

		if (is2RevisionSelected() && versionWizPage.isCurrentPage()) {
			return true;
		} else if (!is2RevisionSelected() && createWizPage.isCurrentPage()) {
			return true;
		}
		return false;
	}

	private boolean diff2RemoteRevision() {
		if (sharedData.size() == 1) {
			return false;
		}
		return true;
	}

	private boolean is2RevisionSelected() {
		Object[] object = versionWizPage.getCheckTblVwr().getCheckedElements();
		return object != null && object.length > 1 && object.length < 3;
	}

}
