package com.hsbc.networking.model;

public class Admin {

	private String name;
	private String email;
	private String phoneNo;
	private String username;
	private String password;
	private String url;
	
	public Admin() {
	}

	public Admin(String name, String email, String phoneNo, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "AdminInfo [name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", username=" + username
				+ ", password=" + password + "]";
	}

}