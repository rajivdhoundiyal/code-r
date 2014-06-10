package com.codereview.i18n;

import org.eclipse.osgi.util.NLS;

public class I18NResources extends NLS {

	private static final String BUNDLE_NAME = "com.codereview.i18n.i18n"; //$NON-NLS-1$
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, I18NResources.class);
	}


	public static String EXCEPTION_VERSION;
	public static String TITLE_REVIEW_CREATION;
	public static String TITLE_ADD_TO_REVIEW;
	public static String DESC_ADD_TO_REVIEW;
	public static String LABEL_URL;
	public static String LABEL_USERNAME;
	public static String LABEL_PASSWORD;
	public static String BTN_CAPTION_TEST;
	public static String DESC_PREFERENCES;
	public static String TITLE_TEST_CONNECTION;
	public static String MSG_TEST_CONNECTION;
	public static String TITLE_TEST_INVALID;
	public static String MSG_TEST_INVALID;
	public static String DATE_FORMAT;
	public static String TITLE_SELECT_VERSION;
	public static String DESC_SELECT_VERSION;
	public static String TBL_COL_MESSAGE;
	public static String TBL_COL_COMMITTED_BY;
	public static String TBL_COL_COMMIT_DATE;
	public static String ERROR_MSG_SELECT_VERSION;
	public static String TITLE_CREATE_REVIEW;
	public static String DESC_CREATE_REVIEW;
	public static String LABEL_REVIEW_NAME;
	public static String LABEL_ADD_TO_EXISTING;
	public static String VALUE_REVIEW_NAME;
	public static String VALUE_EXISTING_REVIEW;

	private I18NResources() {
	}
}
