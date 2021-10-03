package com.hsbc.networking.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UserAuth {
	private String userId;
	private String userName;
	private String encrptPass;
	private int role;

	// Constructor
	public UserAuth() {
	}

	// constructor for creating new UserAuth
	public UserAuth(String userName, String password) {
		super();
		// default 0 if creating user
		this.role = 0;
		// generating Unique Id using user-name
		this.userId = UUID.nameUUIDFromBytes(userName.getBytes()).toString();
		this.userName = userName;
		this.encrptPass = createEncrytion(password);
	}

	public UserAuth(String userId, String userName, String encrptPass, int role) {
		super();
		// while getting details from database
		this.role = role;
		this.userId = userId;
		this.userName = userName;
		this.encrptPass = encrptPass;
	}

	// Getter Setter Methods

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncrptPass() {
		return encrptPass;
	}

	public void setEncrptPass(String encrptPass) {
		this.encrptPass = encrptPass;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		// default for user 0
		this.role = 0;
	}

	// Encrytion of password using MD-5
	private String createEncrytion(String password) {
		String generatedPassword = password;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Add password bytes to digest
			md.update(password.getBytes());

			// Get the hash's bytes
			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return generatedPassword;
	}

	@Override
	public String toString() {
		return "UserAuth [userId=" + userId + ", userName=" + userName + ", encrptPass=" + encrptPass + ", role=" + role
				+ "]";
	}
	
	
}
