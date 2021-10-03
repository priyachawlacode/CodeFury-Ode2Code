package com.hsbc.networking.dao.ContactDao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.networking.model.Contact;
import com.hsbc.networking.exception.ContactException.ContactAlreadyExistsException;
import com.hsbc.networking.exception.ContactException.ContactNotFoundException;

public interface ContactDao {

	// Add contact
	public boolean addContact(Contact c, String userId) throws ContactAlreadyExistsException;

	// update contact
	public boolean updateContact(Contact c, String userId);

	// delete contact
	public boolean deleteContact(String contactId, String userId) throws ContactNotFoundException;

	// Delete multiple COntacts - batchUpdate/execute - transaction
	public boolean deleteContactsInBatch(List<String> contactIds, String userId)
			throws ContactNotFoundException, SQLException;

	// get selected contact details- contact id
	public Contact getContactDetails(String contactId, String userId) throws ContactNotFoundException;

	// get contact list with filter like same email
	public List<Contact> getContactsByFilter(String type, String value, String userId);

	// get all contacts of a user
	public List<Contact> getAllContacts(String userId);

	// check contact with same mailID already exists- Contact object
	public Contact checkContactMailIdExists(String mailId, String userId);

	// get all contacts of a user sorted
		public List<Contact> getAllContactsSorted(String userId, String sortBy, boolean ascending);
}