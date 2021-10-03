package com.hsbc.networking.dao.UserDao;

public class UserFactory {
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
}
