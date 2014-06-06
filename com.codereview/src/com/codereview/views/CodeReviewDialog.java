package com.codereview.views;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.codereview.util.GitUtil;
import com.codereview.util.WorkbenchUtil;

public class CodeReviewDialog extends Dialog {

	private CheckboxTreeViewer viewer;
	private Image image;
	private File file;
	private Set<File> filesInReview;

	public CodeReviewDialog(Shell parentShell, File file, Set<File> filesInReview) {
		super(parentShell);
		this.file = file;
		this.filesInReview = filesInReview;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite textPanel = new Composite(parent, SWT.RIGHT);
		textPanel.setLayout(new GridLayout());
		GridData gridDataText = new GridData();
		gridDataText.horizontalAlignment = GridData.FILL;
		gridDataText.verticalIndent = 5;
		gridDataText.grabExcessHorizontalSpace = true;
		Label label = new Label(textPanel, SWT.BOLD | SWT.RIGHT);
		label.setLayoutData(gridDataText);
		label.setText("Select files to add in review");

		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		Composite scrollPanel = new Composite(scrolledComposite, SWT.NONE | SWT.BORDER);
		scrollPanel.setLayout(new FillLayout());
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.heightHint = getInitialSize().y;
		gridData.verticalIndent = 10;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		scrolledComposite.setLayoutData(gridData);
		viewer = new CheckboxTreeViewer(scrollPanel, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setAutoExpandLevel(2);
		viewer.setContentProvider(new TreeContentProvider());
		viewer.setLabelProvider(new TreeViewLabelProvider());
		viewer.setCheckStateProvider(new TreeCheckedStatedProvider());
		viewer.setInput(file.listFiles());
		viewer.expandAll();
		viewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent checkStateEvent) {
				viewer.setSubtreeChecked(checkStateEvent.getElement(), checkStateEvent.getChecked());
			}
		});
		scrolledComposite.setContent(scrollPanel);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Control control = super.createDialogArea(parent);
		return control;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("File Chooser");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, 450);
	}
	
	@Override
	public void create() {
		super.create();
		Button buttonOk = getButton(OK);
		buttonOk.setText("To Review");
	}

	class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
			return (File[]) inputElement;
		}

		public Object[] getChildren(Object parentElement) {
			File file = (File) parentElement;
			return file.listFiles();
		}

		public Object getParent(Object element) {
			File file = (File) element;
			return file.getParentFile();
		}

		public boolean hasChildren(Object element) {
			File file = (File) element;
			if (file.isDirectory()) {
				return true;
			}
			return false;
		}

	}

	class TreeCheckedStatedProvider implements ICheckStateProvider {

		private boolean isValidForCheck(File file) {
			return (file != null && filesInReview.contains(file));
		}

		public boolean isChecked(Object element) {
			final File file = (File) element;
			boolean isChecked = isValidForCheck(file);
			if (isChecked) {
				viewer.setChecked(file.getParentFile(), isChecked);
			}
			return isChecked;
		}

		public boolean isGrayed(Object element) {
			return false;
		}

	}

	class TreeViewLabelProvider extends StyledCellLabelProvider {

		@Override
		public void update(ViewerCell cell) {
			Object element = cell.getElement();
			StyledString text = new StyledString();
			File file = (File) element;
			if (file.isDirectory()) {
				text.append(getFileName(file));
				cell.setImage(image);
				String[] files = file.list();
				if (files != null) {
					text.append(" (" + files.length + ") ", StyledString.COUNTER_STYLER);
				}
			} else {
				text.append(getFileName(file));
			}
			cell.setText(text.toString());
			cell.setStyleRanges(text.getStyleRanges());
			super.update(cell);

		}

		private String getFileName(File file) {
			String name = file.getName();
			return name.isEmpty() ? file.getPath() : name;
		}
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void dispose() {
		image.dispose();
	}
	
	@Override
	protected void okPressed() {
		Object [] files = viewer.getCheckedElements();
		System.out.println(files.length);
		try {
			DiffCommand command = GitUtil.callDiffCommand(WorkbenchUtil.getProjectDirectoryAsFile());
		} catch (NoWorkTreeException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.okPressed();
	}

}
