package com.codereview.views;

import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.codeproof.model.dto.ReviewCommentDTO;
import com.codeproof.model.dto.ReviewDTO;
import com.codereview.Activator;
import com.codereview.exception.VersionControlException;
import com.codereview.i18n.I18NResources;
import com.codereview.model.RevisionLog;
import com.codereview.scm.GitService;
import com.codereview.scm.IScmService;
import com.codereview.util.ScmFactory;
import com.codereview.util.StringConstants;
import com.codereview.web.RestClientUtil;

public class SelectVersionWizardPage extends WizardPage implements ICheckStateListener, MouseListener, FocusListener {

	private Composite container;
	private Composite reviewContainer;
	private IScmService scmService;
	private List sharedData;
	private CheckboxTableViewer checkTblVwr;
	private Button newReview;
	private Button existingReview;
	private Text text;
	private org.eclipse.swt.widgets.List list;
	private final String[] values = {I18NResources.VALUE_EXISTING_REVIEW};

	protected SelectVersionWizardPage(List sharedData) {
		super(I18NResources.TITLE_SELECT_VERSION, I18NResources.TITLE_SELECT_VERSION, null);
		setDescription(I18NResources.DESC_SELECT_VERSION);
		this.sharedData = sharedData;
	}

	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		createReviewContainer();
		createVersionContainer();
		// required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}

	private void createVersionContainer() {
		Group versionGroup = new Group(container, NONE);
		versionGroup.setText("Version Selection");
		GridLayout versionLayout = new GridLayout();
		versionGroup.setLayout(versionLayout);
		scmService = ScmFactory.getScmProvider(GitService.class);

		List<RevisionLog> revisionLog = null;

		try {
			revisionLog = scmService.getRevisionLogs();
		} catch (VersionControlException e) {
			e.printStackTrace();
		}

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		// gd.heightHint = 200;
		checkTblVwr = CheckboxTableViewer.newCheckList(versionGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
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

	}

	private void createReviewContainer() {
		Group reviewGroup = new Group(container, SWT.NONE);
		reviewGroup.setText("Review Creation");
		GridLayout reviewLayout = new GridLayout();
		reviewLayout.numColumns = 2;
		reviewGroup.setLayout(reviewLayout);
		GridData groupData = new GridData();
		groupData.horizontalAlignment = GridData.FILL;
		groupData.grabExcessHorizontalSpace = true;
		groupData.heightHint = 200;
		reviewGroup.setLayoutData(groupData);

		newReview = new Button(reviewGroup, SWT.RADIO + SWT.HORIZONTAL);
		newReview.setText("New Review");
		newReview.setSelection(true);
		existingReview = new Button(reviewGroup, SWT.RADIO + SWT.HORIZONTAL);
		existingReview.setText("Existing Review");
		newReview.addMouseListener(this);
		existingReview.addMouseListener(this);

		GridData textData = new GridData();
		textData.horizontalAlignment = GridData.FILL;
		textData.horizontalSpan = GridData.GRAB_HORIZONTAL;
		// textData.verticalAlignment = GridData.FILL;
		textData.grabExcessHorizontalSpace = true;
		// textData.grabExcessVerticalSpace = true;
		text = new Text(reviewGroup, SWT.NONE + SWT.BORDER);
		text.setLayoutData(textData);
		text.setText(I18NResources.VALUE_REVIEW_NAME);
		text.addMouseListener(this);
		text.addFocusListener(this);

		String[] values = {I18NResources.VALUE_EXISTING_REVIEW};

		list = new org.eclipse.swt.widgets.List(reviewGroup, SWT.BORDER);
		GridData listData = new GridData();
		listData.horizontalAlignment = GridData.FILL;
		listData.horizontalSpan = GridData.GRAB_HORIZONTAL;
		listData.verticalAlignment = GridData.FILL;
		listData.grabExcessHorizontalSpace = true;
		listData.grabExcessVerticalSpace = true;
		listData.widthHint = 200;
		list.setEnabled(false);

		list.setItems(values);
		list.setLayoutData(listData);
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

	public void mouseDoubleClick(MouseEvent event) {

	}

	public void mouseDown(MouseEvent event) {

	}

	public void mouseUp(MouseEvent event) {
		if (event.getSource() == text && text.getText().equals(I18NResources.VALUE_REVIEW_NAME)) {
			text.setText(StringConstants.EMPTY);
		} else if (event.getSource() == existingReview && existingReview.getSelection()) {
			setTextEnabled(!existingReview.getSelection());
			setValue(existingReview.getSelection());
			getAssociatedReviews();
		} else if (event.getSource() == newReview && newReview.getSelection()) {
			setTextEnabled(newReview.getSelection());
			setValue(!newReview.getSelection());
		}
	}

	private void setTextEnabled(boolean status) {
		text.setEnabled(status);
		list.setEnabled(!status);
	}
	
	private Set<ReviewDTO> getAssociatedReviews() {
		String userName = Activator.getDefault().getPreferenceStore()
		        .getString(I18NResources.TAG_USERNAME);
		String url = Activator.getDefault().getPreferenceStore()
		        .getString(I18NResources.TAG_URL);
		RestClientUtil restClientUtil = new RestClientUtil(url);
		restClientUtil.doGet("review/filter/" + userName, MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, ReviewDTO.class);
		return null;
	}

	private void setValue(boolean status) {
		if (status) {
			list.removeAll();
		} else {
			list.setItems(values);
		}
	}

	public void focusGained(FocusEvent event) {

	}

	public void focusLost(FocusEvent event) {
		if (text.getText().equals(StringConstants.EMPTY)) {
			text.setText(I18NResources.VALUE_REVIEW_NAME);
		}
	}

}
