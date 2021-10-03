package com.hsbc.networking.exception.ContactException;

public class ContactAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactAlreadyExistsException() {
	}

	public ContactAlreadyExistsException(String message) {
		super(message);
	}

	public ContactAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public ContactAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContactAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
