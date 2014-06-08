package com.codereview.exception;

import com.codereview.i18n.I18NResources;

public class VersionControlException extends Exception {

	private static final long serialVersionUID = -2758104764753886713L;

	public VersionControlException() {
		super(I18NResources.EXCEPTION_VERSION);
	}

	public VersionControlException(String message, Throwable cause) {
		super(message, cause);
	}

	public VersionControlException(String message) {
		super(message);
	}

	public VersionControlException(Throwable cause) {
		super(cause);
	}

}
