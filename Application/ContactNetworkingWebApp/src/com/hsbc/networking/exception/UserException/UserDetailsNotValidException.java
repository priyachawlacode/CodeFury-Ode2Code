package com.hsbc.networking.exception.UserException;

public class UserDetailsNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDetailsNotValidException() {

	}

	public UserDetailsNotValidException(String message) {
		super(message);

	}

	public UserDetailsNotValidException(Throwable cause) {
		super(cause);

	}

	public UserDetailsNotValidException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserDetailsNotValidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
