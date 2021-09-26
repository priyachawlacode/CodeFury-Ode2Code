package com.hsbc.networking.exception.UserException;

public class UserIsDisabledException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserIsDisabledException() {
	}

	public UserIsDisabledException(String message) {
		super(message);
	}

	public UserIsDisabledException(Throwable cause) {
		super(cause);
	}

	public UserIsDisabledException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserIsDisabledException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
