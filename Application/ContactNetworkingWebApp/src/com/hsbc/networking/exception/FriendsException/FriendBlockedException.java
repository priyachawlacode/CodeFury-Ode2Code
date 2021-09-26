package com.hsbc.networking.exception.FriendsException;

public class FriendBlockedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FriendBlockedException() {
	}

	public FriendBlockedException(String message) {
		super(message);
	}

	public FriendBlockedException(Throwable cause) {
		super(cause);
	}

	public FriendBlockedException(String message, Throwable cause) {
		super(message, cause);
	}

	public FriendBlockedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
