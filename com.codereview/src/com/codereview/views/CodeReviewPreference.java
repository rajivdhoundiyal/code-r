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
import com.codereview.util.StringConstants;
import com.codereview.web.RestClientUtil;

public class CodeReviewPreference extends FieldEditorPreferencePage
		implements
			IWorkbenchPreferencePage,
			MouseListener {

	private StringFieldEditor url;
	private StringFieldEditor userName;
	private StringFieldEditor password;

	private Button btnTest;

	public CodeReviewPreference() {
		super(GRID);
	}

	public void createFieldEditors() {
		url = new StringFieldEditor("URL", "URL :", getFieldEditorParent());
		userName = new StringFieldEditor("USERNAME", "Username :", getFieldEditorParent());
		password = new StringFieldEditor("PASSWORD", "Password :", getFieldEditorParent());
		addField(url);
		addField(userName);
		addField(password);
		btnTest = new Button(getFieldEditorParent(), SWT.RIGHT_TO_LEFT | SWT.BUTTON3);
		btnTest.setText("Test");
		btnTest.addMouseListener(this);
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Enter your login details here to connect to code review.");
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
			MessageDialog.openInformation(getShell(), "Test Connection!!!", "Connection Successful !!!");
		} else {
			MessageDialog.openError(getShell(), "Invalid!!!", "The username or password provided is incorrect.");
		}
	}

	private boolean isValidUser() {
		RestClientUtil restClientUtil = new RestClientUtil(StringConstants.BASE_URL.getValue());
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
			MessageDialog.openError(getShell(), "Invalid!!!", "The username or password provided is incorrect.");
		}
		return false;
	}

	public void mouseUp(MouseEvent event) {
	}
}