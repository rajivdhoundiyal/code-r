package com.codereview.views;

import javax.ws.rs.core.MediaType;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.codeproof.model.User;
import com.codereview.Activator;
import com.codereview.i18n.I18NResources;
import com.codereview.util.StringConstants;
import com.codereview.web.RestClientUtil;

public class CodeReviewPreference extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, MouseListener {

	private StringFieldEditor url;
	private StringFieldEditor userName;
	private StringFieldEditor password;
	private static final String COLON = StringConstants.SPACE + StringConstants.COLON + StringConstants.SPACE;

	private Button btnTest;

	public CodeReviewPreference() {
		super(GRID);
	}

	public void createFieldEditors() {

		url = new StringFieldEditor("URL", I18NResources.LABEL_URL + COLON, getFieldEditorParent());
		userName = new StringFieldEditor("USERNAME", I18NResources.LABEL_USERNAME + COLON, getFieldEditorParent());
		password = new StringFieldEditor("PASSWORD", I18NResources.LABEL_PASSWORD + COLON, getFieldEditorParent());
		addField(url);
		addField(userName);
		addField(password);
		btnTest = new Button(getFieldEditorParent(), SWT.RIGHT_TO_LEFT | SWT.BUTTON3);
		btnTest.setText(I18NResources.BTN_CAPTION_TEST);
		btnTest.addMouseListener(this);
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(I18NResources.DESC_PREFERENCES);
	}

	public void mouseDoubleClick(MouseEvent event) {
	}

	public void mouseDown(MouseEvent event) {
		if (event.getSource().equals(btnTest)) {
			performTestAction();
		}
	}

	private void performTestAction() {
		if (isValidUser()) {
			MessageDialog.openInformation(getShell(), I18NResources.TITLE_TEST_CONNECTION,
					I18NResources.MSG_TEST_CONNECTION);
		} else {
			MessageDialog.openError(getShell(), I18NResources.TITLE_TEST_INVALID, I18NResources.MSG_TEST_INVALID);
		}
	}

	private boolean isValidUser() {
		RestClientUtil restClientUtil = new RestClientUtil(StringConstants.BASE_URL);
		boolean isValidUser = false;
		User user = new User();
		user.setUserName(userName.getStringValue());
		user.setPassword(password.getStringValue());
		User returnUser = (User) restClientUtil.doPost("login/validate", MediaType.APPLICATION_JSON,
				MediaType.APPLICATION_JSON, user, User.class);
		if (returnUser != null && returnUser.getUserName() != null) {
			isValidUser = true;
		}

		return isValidUser;
	}

	@Override
	public boolean performOk() {
		if (isValidUser()) {
			return super.performOk();
		} else {
			MessageDialog.openError(getShell(), I18NResources.TITLE_TEST_INVALID, I18NResources.MSG_TEST_INVALID);
		}
		return false;
	}

	public void mouseUp(MouseEvent event) {
	}
}