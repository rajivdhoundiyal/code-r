package com.codereview.views;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.codereview.exception.VersionControlException;
import com.codereview.i18n.I18NResources;
import com.codereview.scm.GitService;
import com.codereview.scm.IScmService;
import com.codereview.util.ScmFactory;
import com.codereview.util.WorkbenchUtil;

public class SelectFilesWizardPage extends WizardPage {

	private Composite container;
	private CheckboxTreeViewer viewer;
	private Image image;
	private File file;
	private Set<File> filesInReview;
	private List sharedData;

	public SelectFilesWizardPage(List sharedData) {
		super(I18NResources.TITLE_ADD_TO_REVIEW, I18NResources.TITLE_ADD_TO_REVIEW, null);
		setDescription(I18NResources.DESC_ADD_TO_REVIEW);
		this.sharedData = sharedData;
	}

	public void createControl(Composite parent) {
		this.file = WorkbenchUtil.getProjectDirectoryAsFile().getParentFile();
		IScmService scmService = ScmFactory.getScmProvider(GitService.class);
		try {
			filesInReview = scmService.getFileStatus(file);
		} catch (VersionControlException e) {
			e.printStackTrace();
		}
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.H_SCROLL | SWT.V_SCROLL);
		Composite scrollPanel = new Composite(scrolledComposite, SWT.NONE | SWT.BORDER);
		scrollPanel.setLayout(new FillLayout());
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		scrolledComposite.setLayoutData(gridData);
		viewer = new CheckboxTreeViewer(scrollPanel, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		scrolledComposite.setContent(scrollPanel);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		setControl(container);
		setPageComplete(false);
	}

	@Override
	public void setVisible(boolean visible) {
		viewer.setAutoExpandLevel(2);
		viewer.setContentProvider(new TreeContentProvider());
		viewer.setLabelProvider(new TreeViewLabelProvider());
		viewer.setCheckStateProvider(new TreeCheckedStateProvider());
		viewer.setInput(file.listFiles());
		// viewer.expandAll();
		viewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent checkStateEvent) {
				viewer.setSubtreeChecked(checkStateEvent.getElement(), checkStateEvent.getChecked());
			}
		});
		super.setVisible(visible);
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

	class TreeCheckedStateProvider implements ICheckStateProvider {

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

	@Override
	public boolean isCurrentPage() {
		return super.isCurrentPage();
	}
	public void dispose() {
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return true;
	}

}
