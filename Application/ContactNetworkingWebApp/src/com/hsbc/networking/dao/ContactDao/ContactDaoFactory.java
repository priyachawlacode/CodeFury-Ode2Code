package com.hsbc.networking.dao.ContactDao;

public class ContactDaoFactory {

	public static ContactDao getContactDao() {
		return new ContactDaoImpl();
	}

}
