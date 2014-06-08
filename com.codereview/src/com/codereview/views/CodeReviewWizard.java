package com.codereview.views;

import static com.codereview.i18n.I18NResources.TITLE_REVIEW_CREATION;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class CodeReviewWizard extends Wizard {

	protected SelectVersionWizardPage versionWizPage;
	protected SelectFilesWizardPage createWizPage;
	
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
		List sharedData = new ArrayList();
		versionWizPage = new SelectVersionWizardPage(sharedData);
		createWizPage = new SelectFilesWizardPage(sharedData);
		addPage(versionWizPage);
		addPage(createWizPage);
	}
	
	@Override
	public boolean performFinish() {
		return false;
	}
	
}
