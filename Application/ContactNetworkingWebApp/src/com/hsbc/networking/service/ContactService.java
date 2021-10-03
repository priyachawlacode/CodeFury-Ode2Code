package com.hsbc.networking.service;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hsbc.networking.model.Contact;
import com.hsbc.networking.dao.ContactDao.ContactDao;
import com.hsbc.networking.dao.ContactDao.ContactDaoFactory;
import com.hsbc.networking.exception.ContactException.*;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;

public class ContactService {
	private static ContactDao cl = ContactDaoFactory.getContactDao();

	private static void validateContactDetails(Contact c) throws ContactDetailsNotValidException, SQLException {
		System.out.println("Inside validate contact details service");
		// Validating mandatory fields
		if (c.getFullName() == null || c.getEmail() == null) {
			throw new ContactDetailsNotValidException("Full Name and Email id is mandatory to enter");
		}
		// validate mail
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(c.getEmail());
		if (!matcher.matches()) {
			throw new ContactDetailsNotValidException("Email not in correct format");
		}

		// validate phone number - 10 digits
		if (!c.getContactNo().matches("\\d{10}")) {
			throw new ContactDetailsNotValidException("Contact number incorrect");
		}
	}

	// to add add contacts after validation done
	public boolean addContact(Contact c, String userId)
			throws ContactDetailsNotValidException, ContactAlreadyExistsException, SQLException {
		validateContactDetails(c);
		return cl.addContact(c, userId);
	}

	// to update details of User (Username,email,gender,dob will not get updated)
	// Prefill data of user
	public boolean updateContact(Contact c, String userId) throws ContactDetailsNotValidException, SQLException {
		System.out.println(c.toString());
		validateContactDetails(c);
		return cl.updateContact(c, userId);
	}

	// Deleting contacts for a specific user
	public boolean deleteContact(String contactId, String userId) throws ContactNotFoundException {
		System.out.println("Inside service of delete contact");
		System.out.println("Contact id from service " + contactId);
		System.out.println("User id from service " + userId);
		return cl.deleteContact(contactId, userId);
	}

	// Getting a contact for a particular user
	public Contact getContactDetails(String contactId, String userId) throws ContactNotFoundException {
		return cl.getContactDetails(contactId, userId);
	}

	// get user details by address/state/city/phone number etc -> returns list of
	// cpntacts
	// contacts specific to user being filtered
	public List<Contact> getContactsByFilter(String type, String value, String userId) {
		return cl.getContactsByFilter(type, value, userId);
	}

	// Retrieving all contacts for a particular user
	public List<Contact> getAllContacts(String userId) {

		return cl.getAllContacts(userId);
	}

	// Check if contact with mail id exists. if exists return contact and give
	// option of 1. add or 2. abort adding or 3. view and edit contact option
	// If does not exist check if it is a registered user ad return it with option
	// to add as contact
	// This is implemented in dao layer

	public Contact checkContactMailIdExists(String mailId, String userId) throws UserDetailsNotFoundException {

		return cl.checkContactMailIdExists(mailId, userId);

	}

	public boolean deleteContactsInBatch(List<String> contactIds, String userId)
			throws ContactNotFoundException, SQLException {

		return cl.deleteContactsInBatch(contactIds, userId);
	}

	// Retrieving all contacts for a particular user sorted
	public List<Contact> getAllContactsSorted(String userId, String sortBy, boolean ascending) {
		return cl.getAllContactsSorted(userId, sortBy, ascending);
	}
}