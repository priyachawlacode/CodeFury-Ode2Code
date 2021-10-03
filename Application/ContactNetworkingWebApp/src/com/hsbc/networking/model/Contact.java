package com.hsbc.networking.model;

import java.sql.Blob;
import java.util.Date;
import java.util.UUID;

public class Contact {
	private String contactId;
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
	
	
	//Constructors
	public Contact() {
		super();
	}


	public Contact(String contactId, String fullName, String email, String contactNo, char gender, Date dob,
			String address, String city, String state, String country, String company, Blob profileImage) {
		super();
		//Generating unique id for every contact
		this.contactId = UUID.randomUUID().toString();
		System.out.println(this.contactId);
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
	}

	public Contact(String fullName, String email, String contactNo, char gender, Date dob,
			String address, String city, String state, String country, String company, Blob profileImage) {
		super();
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
	}

	
	//Getter Setter Methods
	public String getContactId() {
		return contactId;
	}


	public void setContactId(String contactId) {
		this.contactId = contactId;
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


	public void setDob(Date dob) {
		this.dob = dob;
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


	
	//To string method

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", fullName=" + fullName + ", email=" + email + ", contactNo="
				+ contactNo + ", gender=" + gender + ", dob=" + dob + ", address=" + address + ", city=" + city
				+ ", State=" + state + ", Country=" + country + ", company=" + company + ", profileImage="
				+ profileImage + "]";
	}
	
}