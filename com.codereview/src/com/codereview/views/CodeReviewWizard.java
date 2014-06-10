package com.codereview.views;

import static com.codereview.i18n.I18NResources.TITLE_REVIEW_CREATION;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.eclipse.jdt.internal.compiler.ast.FakedTrackingVariable;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.codeproof.model.dto.FileDetailsDTO;
import com.codereview.exception.VersionControlException;
import com.codereview.model.RevisionLog;
import com.codereview.scm.GitService;
import com.codereview.scm.IScmService;
import com.codereview.util.GitUtil;
import com.codereview.util.ScmFactory;
import com.codereview.util.StringConstants;
import com.codereview.web.RestClientUtil;

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
		Set<FileDetailsDTO> fileContents = null;
		if (diff2RemoteRevision()) {
			RevisionLog revLogNew = (RevisionLog) sharedData.get(0);
			RevisionLog revLogOld = (RevisionLog) sharedData.get(1);
			String revisionIdNew = revLogNew.getRevisionId();
			String revisionIdOld = revLogOld.getRevisionId();

			IScmService iScmService = ScmFactory.getScmProvider(GitService.class);

			try {
				fileContents = iScmService.getFileDiff(revisionIdNew, revisionIdOld);
			} catch (VersionControlException e) {
				e.printStackTrace();
			}
		} else {

			RevisionLog revLogOld = (RevisionLog) sharedData.get(0);
			String revisionIdOld = revLogOld.getRevisionId();

			IScmService iScmService = ScmFactory.getScmProvider(GitService.class);

			try {
				fileContents = iScmService.getFileDiff(null, revisionIdOld);
			} catch (VersionControlException e) {
				e.printStackTrace();
			}
		}

		RestClientUtil restClient = new RestClientUtil(StringConstants.BASE_URL);
		String response = (String) restClient.doPost("file/details", MediaType.APPLICATION_JSON,
				MediaType.APPLICATION_JSON, fileContents, String.class);
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
