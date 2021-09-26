package com.hsbc.networking.dao.ContactDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.networking.model.Contact;
import com.hsbc.networking.dao.DBUtil.DBUtil;
import com.hsbc.networking.exception.ContactException.ContactAlreadyExistsException;
import com.hsbc.networking.exception.ContactException.ContactNotFoundException;

public class ContactDaoImpl implements ContactDao {

	private static Connection conn;
	private static PreparedStatement addContact, upContact, getContact, dltContact, mailIdExists, addContactList,
			getAllContacts, getContactFilter, getUser,dltContactList;

	static {
		try {
			conn = DBUtil.getDBConnection();

			addContact = conn.prepareStatement("insert into contactdetails(contact_id,fullName,emailId,contactNo, gender,dob,address,city,state, country,company, profileImage) values(?,?,?,?,?,?,?,?,?,?,?,?)");

			addContactList = conn.prepareStatement("insert into contactlist(user_id, contact_id) VALUES(?,?)");

			upContact = conn.prepareStatement("update contactdetails set fullName=?,contactNo=?,address=?, city=?,state=?,country=?,company=?,profileImage=? where contact_id =?");

			getUser = conn.prepareStatement("select * from users where mailId=?");

			getContact = conn.prepareStatement("select contactlist.contact_id, fullname, emailId, contactNo, gender, dob, address,city, state, country, company , profileImage from contactlist  join contactdetails on contactlist.contact_id = contactdetails.contact_id where user_id =? and contactlist.contact_id =?");

			dltContact = conn.prepareStatement("delete from contactdetails where contact_id=?");
			
			dltContactList = conn.prepareStatement("delete from contactlist(user_id, contact_id) VALUES(?,?)");

			mailIdExists = conn.prepareStatement("select contactlist.contact_id, fullname, emailId, contactNo, gender, dob, address,city, state, country, company , profileImage from contactlist  join contactdetails on contactlist.contact_id = contactdetails.contact_id where user_id =? and emailId =?");

			getContactFilter = conn
					.prepareStatement("select contactlist.contact_id, fullname, emailId, contactNo,gender, dob, address,city, state, country, company , profileImage from contactlist  join contactdetails on contactlist.contact_id = contactdetails.contact_id where user_id=? and ?=?");

			getAllContacts = conn.prepareStatement("select contactlist.contact_id, fullname, emailId, contactNo,gender,dob, address,city, state, country, company , profileImage from contactlist  join contactdetails on contactlist.contact_id = contactdetails.contact_id where user_id =?");

		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
	}

	@Override
	public boolean addContact(Contact c, String userId) throws ContactAlreadyExistsException {
		boolean isAdded = false;

		try {
			// adding a contact which is not a registered user in our db
			addContact.setString(1, c.getContactId());
			addContact.setString(2, c.getFullName());
			addContact.setString(3, c.getEmail());
			addContact.setString(4, c.getContactNo());
			addContact.setString(5, String.valueOf(c.getGender()));
			addContact.setDate(6,  new java.sql.Date(c.getDob().getTime()));
			addContact.setString(7, c.getAddress());
			addContact.setString(8, c.getCity());
			addContact.setString(9, c.getState());
			addContact.setString(10, c.getCountry());
			addContact.setString(11, c.getCompany());
			addContact.setBlob(12, c.getProfileImage());
			int num = addContact.executeUpdate();

			addContactList.setString(1, userId);
			addContactList.setString(2, c.getContactId());
			addContactList.executeUpdate();

			if (num > 0) {
				isAdded = true;
			}

		} catch (SQLException e) {
			throw new ContactAlreadyExistsException(e.getMessage());
		}
		return isAdded;
	}

	@Override
	public boolean updateContact(Contact c, String userId) {
		System.out.println("Inside ContactDaoImpl updateontact()");
		System.out.println(c.toString());
		boolean isUpdated = false;
		try {
			upContact.setString(1, c.getFullName());
			upContact.setString(2, c.getContactNo());
			upContact.setString(3, c.getAddress());
			upContact.setString(4, c.getCity());
			upContact.setString(5, c.getState());
			upContact.setString(6, c.getCountry());
			upContact.setString(7, c.getCompany());
			upContact.setBlob(8, c.getProfileImage());
			upContact.setString(9, c.getContactId());

			int num = upContact.executeUpdate();
			if (num > 0) {
				isUpdated = true;
			}

		} catch (SQLException e) {
			System.out.println("Inside exception");
			System.out.println(e.getMessage());
		}

		return isUpdated;
	}

	@Override
	public boolean deleteContact(String contactId, String userId) throws ContactNotFoundException {
		boolean isDeleted = false;
		try {
			dltContact.setString(1, contactId);
			int num = dltContact.executeUpdate();
			System.out.println("Contact deleted from contact details for id "+contactId);
			System.out.println("Number is "+num);
			dltContactList.setString(1, userId);
			dltContactList.setString(2, contactId);
			dltContactList.executeUpdate();
			System.out.println("Contact deleted from contact list for userid and contactId "+userId+" "+contactId);
			System.out.println("Number is "+num);
			if (num > 0) {
				System.out.println("Inside true block");
				isDeleted = true;
			}
		} catch (SQLException e) {
			throw new ContactNotFoundException(e.getMessage());
		}
		return isDeleted;
	}

	@Override
	public boolean deleteContactsInBatch(List<String> contactIds, String userId)
			throws ContactNotFoundException, SQLException {
		boolean isBatchDeleted = false;
		conn.setAutoCommit(false);
		for (int i = 0; i < contactIds.size(); i++) {
			dltContact.setString(1, contactIds.get(i));
			dltContact.setString(2, userId);
			dltContact.addBatch();
		}
		int successCount[] = dltContact.executeBatch();
		int successCountLen = successCount.length;
		for (int i = 0; i < successCountLen; i++) {
			int success = successCount[i];
			if (success > 0) {
				isBatchDeleted = true;
			}
			conn.commit();
			conn.setAutoCommit(true);

		}
		return isBatchDeleted;
	}

	@Override
	public Contact getContactDetails(String contactId, String userId) throws ContactNotFoundException {
		ResultSet resultSet = null;
		Contact c = new Contact();

		try {
			getContact.setString(1, userId);
			getContact.setString(2, contactId);
			resultSet = getContact.executeQuery();

			if (resultSet.next()) {
				// Fetching value for id column from DB and putting it in Contact object
				c.setContactId(resultSet.getString(1));
				c.setFullName(resultSet.getString(2));
				c.setEmail(resultSet.getString(3));
				c.setContactNo(resultSet.getString(4));
				char chr = resultSet.getString(5).charAt(0);
				c.setGender(chr); // error this is char how to resolve
				c.setDob(resultSet.getDate(6));
				c.setAddress(resultSet.getString(7));
				c.setCity(resultSet.getString(8));
				c.setState(resultSet.getString(9));
				c.setCountry(resultSet.getString(10));
				c.setCompany(resultSet.getString(11));
				c.setProfileImage(resultSet.getBlob(12));

			} else {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Returning Contact object
		return c;
	}

	@Override
	public List<Contact> getContactsByFilter(String type, String value, String userId) {
		ResultSet resultSet = null;
		List<Contact> cList = new ArrayList<Contact>();
		try {
			getContactFilter.setString(1, userId);
			getContactFilter.setString(2, type);
			getContactFilter.setString(3, value);
			resultSet = getContactFilter.executeQuery();

			while (resultSet.next()) {
				Contact c = new Contact();
				// Fetching value for id column from DB and putting it in Contact object
				c.setContactId(resultSet.getString(1));
				c.setFullName(resultSet.getString(2));
				c.setEmail(resultSet.getString(3));
				c.setContactNo(resultSet.getString(4));
				c.setGender(resultSet.getString(5).charAt(0)); // error this is char how to resolve
				c.setDob(resultSet.getDate(6));
				c.setAddress(resultSet.getString(7));
				c.setCity(resultSet.getString(8));
				c.setState(resultSet.getString(9));
				c.setCountry(resultSet.getString(10));
				c.setCompany(resultSet.getString(11));
				c.setProfileImage(resultSet.getBlob(12));
				cList.add(c);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cList;

	}

	// to be completed
	@Override
	public List<Contact> getAllContacts(String userId) {
		System.out.println("Inside getAllCOntacts");
		ResultSet resultSet = null;
		List<Contact> cList = new ArrayList<Contact>();
		try {
			getAllContacts.setString(1, userId);
			resultSet = getAllContacts.executeQuery();

			while (resultSet.next()) {
				System.out.println("Inside result set");
				Contact c = new Contact();
				// Fetching value for id column from DB and putting it in Contact object
				c.setContactId(resultSet.getString("contact_id"));
				c.setFullName(resultSet.getString("fullname"));
				c.setEmail(resultSet.getString("emailId"));
				c.setContactNo(resultSet.getString("contactNo"));
				c.setGender(resultSet.getString("gender").charAt(0)); // error this is char how to resolve
				c.setDob(resultSet.getDate("dob"));
				c.setAddress(resultSet.getString("address"));
				c.setCity(resultSet.getString("city"));
				c.setState(resultSet.getString("state"));
				c.setCountry(resultSet.getString("country"));
				c.setCompany(resultSet.getString("company"));
				c.setProfileImage(resultSet.getBlob("profileImage"));
				cList.add(c);

			}
		} catch (SQLException e) {
			System.out.println("Inside getAllCOntacts error");
			e.printStackTrace();
		}
		System.out.println("Inside getAllCOntacts success");
		return cList;
	}

	@Override
	public Contact checkContactMailIdExists(String userId, String emailId) {
		// Returning the contact obj if mailid exists else null. service layer impl

		ResultSet resultSet = null;
		Contact c = new Contact();
		try {
			mailIdExists.setString(1, userId);
			mailIdExists.setString(2, emailId);
			resultSet = mailIdExists.executeQuery();

			if (resultSet.next()) {
				// Fetching value for id column from DB and putting it in Contact object
				c.setContactId(resultSet.getString(1));
				c.setFullName(resultSet.getString(2));
				c.setEmail(resultSet.getString(3));
				c.setContactNo(resultSet.getString(4));
				c.setGender(resultSet.getString(5).charAt(0)); // error this is char how to resolve
				c.setDob(resultSet.getDate(6));
				c.setAddress(resultSet.getString(7));
				c.setCity(resultSet.getString(8));
				c.setState(resultSet.getString(9));
				c.setCountry(resultSet.getString(10));
				c.setCompany(resultSet.getString(11));
				c.setProfileImage(resultSet.getBlob(12));

			} else {
				// problem we are returning contact obj and not user obj
				/**
				 * CHECKKKKKKKKK
				 */
				getUser.setString(1, emailId);
				resultSet = getUser.executeQuery();

				while (resultSet.next()) {
					c.setContactId(resultSet.getString(1));
					c.setFullName(resultSet.getString(2));
					c.setEmail(resultSet.getString(3));
					c.setContactNo(resultSet.getString(4));
					c.setGender(resultSet.getString(5).charAt(0)); // error this is char how to resolve
					c.setDob(resultSet.getDate(6));
					c.setAddress(resultSet.getString(7));
					c.setCity(resultSet.getString(8));
					c.setState(resultSet.getString(9));
					c.setCountry(resultSet.getString(10));
					c.setCompany(resultSet.getString(11));
					c.setProfileImage(resultSet.getBlob(12));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Returning Contact object
		return c;
	}

}