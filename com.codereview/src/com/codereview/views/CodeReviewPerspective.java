package com.codereview.views;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class CodeReviewPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout iPageLayout) {

		String editorArea = iPageLayout.getEditorArea();

		iPageLayout.addView(IPageLayout.ID_PROJECT_EXPLORER, IPageLayout.LEFT, 0.25f, editorArea);

	}
}