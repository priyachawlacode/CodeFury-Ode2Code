package com.hsbc.networking.exception.UserException;

public class UserAuthDoesNotExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAuthDoesNotExistsException() {
	}

	public UserAuthDoesNotExistsException(String message) {
		super(message);
	}

	public UserAuthDoesNotExistsException(Throwable cause) {
		super(cause);
	}

	public UserAuthDoesNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAuthDoesNotExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
