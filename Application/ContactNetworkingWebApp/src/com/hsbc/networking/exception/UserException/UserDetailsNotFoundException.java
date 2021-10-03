package com.hsbc.networking.exception.UserException;

public class UserDetailsNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDetailsNotFoundException() {
	}

	public UserDetailsNotFoundException(String message) {
		super(message);
	}

	public UserDetailsNotFoundException(Throwable cause) {
		super(cause);
	}

	public UserDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDetailsNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
