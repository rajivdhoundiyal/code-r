package com.codereview.views;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.annotation.PreDestroy;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class CodeReviewDialog extends Dialog {

	private CheckboxTreeViewer viewer;
	private Image image;
	private File file;
	private Set<String> filesInReview;

	public CodeReviewDialog(Shell parentShell, File file, Set<String> filesInReview) {
		super(parentShell);
		this.file = file;
		this.filesInReview = filesInReview;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite textPanel = new Composite(parent, SWT.BORDER);
		textPanel.setLayout(new FillLayout());
		textPanel.setSize(580, 150);
		Label label = new Label(textPanel, SWT.BOLD);
		label.setSize(580, 150);
		label.setText("Select files to add in review");

		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		Composite scrollPanel = new Composite(scrolledComposite, SWT.NONE);
		scrollPanel.setLayout(new FillLayout());
		scrollPanel.setSize(580, 450);
		viewer = new CheckboxTreeViewer(scrollPanel, SWT.MULTI);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(file.listFiles());
		scrolledComposite.setContent(scrollPanel);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		return super.createDialogArea(parent);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("File Chooser");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(600, 400);
	}

	/*
	 * private void createImage() { Bundle bundle =
	 * FrameworkUtil.getBundle(ViewLabelProvider.class); URL url =
	 * FileLocator.find(bundle, new Path("icons/folder.png"), null);
	 * ImageDescriptor imageDcr = ImageDescriptor.createFromURL(url); this.image
	 * = imageDcr.createImage(); }
	 */

	class ViewContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
			System.out.println("Get Elements");
			for (File file : (File[]) inputElement) {
				validateAndCheck(file);
			}
			return (File[]) inputElement;
		}

		public Object[] getChildren(Object parentElement) {
			System.out.println("Get Childern");
			File file = (File) parentElement;
			validateAndCheck(file);
 
			return file.listFiles();
		}

		public Object getParent(Object element) {
			System.out.println("Get Parent");
			File file = (File) element;
			validateAndCheck(file.getParentFile());
			return file.getParentFile();
		}

		public boolean hasChildren(Object element) {
			File file = (File) element;
			validateAndCheck(file);
			if (file.isDirectory()) {
				return true;
			}
			return false;
		}

		private void validateAndCheck(File file) {
			if (file != null && filesInReview.contains(file.getAbsolutePath())) {
				viewer.setChecked(file, true);
				viewer.setChecked(file.getParentFile(), true);
			}
		}

	}

	class ViewLabelProvider extends StyledCellLabelProvider {
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

	@PreDestroy
	public void dispose() {
		image.dispose();
	}

}
