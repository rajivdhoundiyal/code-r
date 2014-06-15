package com.codereview.views;

import static com.codereview.i18n.I18NResources.TITLE_REVIEW_CREATION;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.codeproof.common.model.dto.FileDetailsDTO;
import com.codeproof.common.model.dto.ReviewDTO;
import com.codeproof.common.model.dto.ReviewRoleDTO;
import com.codeproof.common.model.dto.ReviewRoleTypeDTO;
import com.codereview.Activator;
import com.codereview.exception.VersionControlException;
import com.codereview.i18n.I18NResources;
import com.codereview.model.RevisionLog;
import com.codereview.scm.GitService;
import com.codereview.scm.IScmService;
import com.codereview.util.ScmFactory;
import com.codereview.util.StringConstants;
import com.codereview.web.RestClientUtil;

public class CodeReviewWizard extends Wizard {

	protected SelectVersionWizardPage versionWizPage;
	protected SelectFilesWizardPage createWizPage;
	List sharedData = new ArrayList();

	public CodeReviewWizard() {
		super();
		setDefaultPageImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("com.codereview", "icons/paw-64.png"));
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
		ReviewDTO review = null;
		Set<FileDetailsDTO> fileContents = null;
		if (diffWithLocalChanges()) {

			RevisionLog revLogOld = (RevisionLog) sharedData.get(0);
			String revisionIdOld = revLogOld.getRevisionId();

			IScmService iScmService = ScmFactory.getScmProvider(GitService.class);

			try {
				fileContents = iScmService.getFileDiff(null, revisionIdOld);
			} catch (VersionControlException e) {
				e.printStackTrace();
			}
		} else {
			RevisionLog revLogNew = (RevisionLog) sharedData.get(0);
			RevisionLog revLogOld = (RevisionLog) sharedData.get(1);
			String revisionIdNew = revLogNew.getRevisionId();
			String revisionIdOld = revLogOld.getRevisionId();

			IScmService iScmService = ScmFactory.getScmProvider(GitService.class);
			
			review = new ReviewDTO();
			String userName = Activator.getDefault().getPreferenceStore().getString(I18NResources.TAG_USERNAME);
			review.setReviewee(userName);
			ReviewRoleDTO reviewRole = new ReviewRoleDTO();
			reviewRole.setReviewerName(userName);
			ReviewRoleTypeDTO reviewRoleTypeDTO = new ReviewRoleTypeDTO();
			reviewRole.setReviewRoleType(reviewRoleTypeDTO);
			reviewRoleTypeDTO.setReviewRoleType(ReviewRoleTypeDTO.RoleType.REVIEWEE.toString());
			List<ReviewRoleDTO> reviewRoles = new ArrayList<ReviewRoleDTO>();
			reviewRoles.add(reviewRole);
			review.setReviewers(reviewRoles);
			
			if(versionWizPage.isNewReview()) {
				review.setReviewName(versionWizPage.getReviewName());
			} else {
				review.setReviewName(versionWizPage.getExistingReview());
			}

			try {
				fileContents = iScmService.getFileDiff(revisionIdNew, revisionIdOld);
				review.setFiles(fileContents);
			} catch (VersionControlException e) {
				e.printStackTrace();
			}

		}

		RestClientUtil restClient = new RestClientUtil(StringConstants.BASE_URL);
		String response = (String) restClient.doPost("review", MediaType.APPLICATION_JSON,
				MediaType.APPLICATION_JSON, review, String.class);
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

	private boolean diffWithLocalChanges() {
		if (sharedData.size() == 1) {
			return true;
		}
		return false;
	}

	private boolean is2RevisionSelected() {
		Object[] object = versionWizPage.getCheckTblVwr().getCheckedElements();
		return object != null && object.length > 1 && object.length < 3;
	}

}
