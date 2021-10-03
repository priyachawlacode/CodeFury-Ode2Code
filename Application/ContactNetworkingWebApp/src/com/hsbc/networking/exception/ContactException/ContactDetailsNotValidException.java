package com.hsbc.networking.exception.ContactException;

public class ContactDetailsNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactDetailsNotValidException() {
	}

	public ContactDetailsNotValidException(String message) {
		super(message);
	}

	public ContactDetailsNotValidException(Throwable cause) {
		super(cause);
	}

	public ContactDetailsNotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContactDetailsNotValidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
