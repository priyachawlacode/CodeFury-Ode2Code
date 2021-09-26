package com.hsbc.networking.model;

public class FriendRequest {

	private String friend_id, user_id;
	private String message;

	public FriendRequest() {
		friend_id = user_id = "";
		message = "";
	}

	public FriendRequest(String sender_id, String user_id, String message) {
		super();
		this.friend_id = sender_id;
		this.user_id = user_id;
		this.message = message;
	}

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FriendRequest [sender_id=" + friend_id + ", user_id=" + user_id + ", message=" + message + "]";
	}

}