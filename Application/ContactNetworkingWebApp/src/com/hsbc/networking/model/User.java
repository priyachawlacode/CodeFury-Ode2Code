package com.hsbc.networking.model;

import java.sql.Blob;
import java.util.Date;


public class User {
	private String userId;
	private String userName;
	private String fullName;
	private String email;
	private String contactNo;
	private char gender;
	private Date dob;
	private String address;
	private String city;
	private String state;
	private String country;
	private String company;
	private Blob profileImage;
	private boolean isDeactivated;
	private boolean isDisabled; 
	
	//Constructors
	public User() {
		super();
	}

	
	public User(String userId, String userName, String fullName, String email, String contactNo, char gender, Date dob,
			String address, String city, String state, String country, String company, Blob profileImage,
			boolean isDeactivated, boolean isDisabled) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.contactNo = contactNo;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.company = company;
		this.profileImage = profileImage;
		this.isDeactivated = isDeactivated;
		this.isDisabled = isDisabled;
	}


	//Getter Setter Methods
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date date) {
		this.dob = (Date) date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Blob getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}

	public boolean isDeactivated() {
		return isDeactivated;
	}

	public void setDeactivated(boolean isDeactivated) {
		this.isDeactivated = isDeactivated;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}


	
	//To string method

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", fullName=" + fullName + ", email=" + email
				+ ", contactNo=" + contactNo + ", gender=" + gender + ", dob=" + dob + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", company=" + company
				+ ", profileImage=" + profileImage + ", isDeactivated=" + isDeactivated + ", isDisabled=" + isDisabled
				+ "]";
	}

	
}
