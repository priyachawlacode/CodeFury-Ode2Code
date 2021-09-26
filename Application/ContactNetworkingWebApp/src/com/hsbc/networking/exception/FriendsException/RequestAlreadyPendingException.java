package com.hsbc.networking.exception.FriendsException;

public class RequestAlreadyPendingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestAlreadyPendingException() {
	}

	public RequestAlreadyPendingException(String message) {
		super(message);
	}

	public RequestAlreadyPendingException(Throwable cause) {
		super(cause);
	}

	public RequestAlreadyPendingException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestAlreadyPendingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
