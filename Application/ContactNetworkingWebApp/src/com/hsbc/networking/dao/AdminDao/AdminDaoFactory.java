package com.hsbc.networking.dao.AdminDao;

public class AdminDaoFactory {

	public static AdminDao getAdminDao() {
		return new AdminDaoImpl();
	}

}
