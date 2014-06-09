package com.codereview.views;

import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import com.codereview.exception.VersionControlException;
import com.codereview.i18n.I18NResources;
import com.codereview.model.RevisionLog;
import com.codereview.scm.GitService;
import com.codereview.scm.IScmService;
import com.codereview.util.ScmFactory;
import com.codereview.util.StringConstants;

public class SelectVersionWizardPage extends WizardPage implements ICheckStateListener {

	private Composite container;
	private IScmService scmService;
	private List sharedData;
	private CheckboxTableViewer checkTblVwr;

	protected SelectVersionWizardPage(List sharedData) {
		super(I18NResources.TITLE_SELECT_VERSION, I18NResources.TITLE_SELECT_VERSION, null);
		setDescription(I18NResources.DESC_SELECT_VERSION);
		this.sharedData = sharedData;
	}

	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		scmService = ScmFactory.getScmProvider(GitService.class);

		List<RevisionLog> revisionLog = null;

		try {
			revisionLog = scmService.getRevisionLogs();
		} catch (VersionControlException e) {
			e.printStackTrace();
		}

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		checkTblVwr = CheckboxTableViewer.newCheckList(container, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION);
		Table table = checkTblVwr.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		checkTblVwr.setContentProvider(new ArrayContentProvider());
		table.setLayoutData(gd);

		TableViewerColumn checkbox = new TableViewerColumn(checkTblVwr, SWT.NONE);
		checkbox.getColumn().setWidth(50);
		checkbox.getColumn().setText(StringConstants.EMPTY);
		checkbox.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return "";
			}
		});

		TableViewerColumn colRevTitle = new TableViewerColumn(checkTblVwr, SWT.NONE);
		colRevTitle.getColumn().setWidth(350);
		colRevTitle.getColumn().setText(I18NResources.TBL_COL_MESSAGE);
		colRevTitle.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				RevisionLog revisionLog = (RevisionLog) element;
				return revisionLog.getShortMessage();
			}
		});

		TableViewerColumn colCommiter = new TableViewerColumn(checkTblVwr, SWT.NONE);
		colCommiter.getColumn().setWidth(150);
		colCommiter.getColumn().setText(I18NResources.TBL_COL_COMMITTED_BY);
		colCommiter.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				RevisionLog revisionLog = (RevisionLog) element;
				return revisionLog.getAuthor();
			}
		});

		TableViewerColumn colCommitDate = new TableViewerColumn(checkTblVwr, SWT.NONE);
		colCommitDate.getColumn().setWidth(120);
		colCommitDate.getColumn().setText(I18NResources.TBL_COL_COMMIT_DATE);
		colCommitDate.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				RevisionLog revisionLog = (RevisionLog) element;
				return revisionLog.getDateOfCommit();
			}
		});

		checkTblVwr.setInput(revisionLog);

		checkTblVwr.addCheckStateListener(this);
		// required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}
	class GitLogContentProvider implements IStructuredContentProvider {
		private final Object[] EMPTY = new Object[]{};

		public Object[] getElements(Object object) {
			List revisionList = (List) object;
			if (revisionList != null) {
				return revisionList.toArray();
			}
			return EMPTY;
		}

		public void dispose() {
		}

		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		}
	}

	@Override
	public boolean canFlipToNextPage() {
		return canFlipToPage();
	}

	protected boolean canFlipToPage() {
		Object[] object = checkTblVwr.getCheckedElements();
		if (object != null && object.length == 1) {
			return true;
		}
		return false;
	}

	public void checkStateChanged(CheckStateChangedEvent event) {
		canFlipToNextPage();
		getWizard().getContainer().updateButtons();
	}

	public CheckboxTableViewer getCheckTblVwr() {
		return checkTblVwr;
	}

	public void setCheckTblVwr(CheckboxTableViewer checkTblVwr) {
		this.checkTblVwr = checkTblVwr;
	}
	
	@Override
	public boolean isCurrentPage() {
		return super.isCurrentPage();
	}

}
