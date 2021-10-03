package com.hsbc.networking.exception.UserException;

public class UserIsDeactivatedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserIsDeactivatedException() {
	}

	public UserIsDeactivatedException(String message) {
		super(message);
	}

	public UserIsDeactivatedException(Throwable cause) {
		super(cause);
	}

	public UserIsDeactivatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserIsDeactivatedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
