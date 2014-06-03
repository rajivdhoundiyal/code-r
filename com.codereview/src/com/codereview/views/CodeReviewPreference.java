package com.codereview.views;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.codereview.Activator;

public class CodeReviewPreference extends FieldEditorPreferencePage implements IWorkbenchPreferencePage,
		MouseListener {

	Button test;

	public CodeReviewPreference() {
		super(GRID);
	}

	public void createFieldEditors() {
		addField(new StringFieldEditor("URL", "URL :", getFieldEditorParent()));
		addField(new StringFieldEditor("USERNAME", "Username :", getFieldEditorParent()));
		addField(new StringFieldEditor("PASSWORD", "Password :", getFieldEditorParent()));
		test = new Button(getFieldEditorParent(), SWT.RIGHT_TO_LEFT | SWT.BUTTON3);
		test.setText("Test");
		test.addMouseListener(this);
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Enter your login details here to connect to code review.");
	}

	public void mouseDoubleClick(MouseEvent event) {

	}

	public void mouseDown(MouseEvent event) {
		System.out.println("Mouse Down Event");

	}

	public void mouseUp(MouseEvent event) {

	}
}