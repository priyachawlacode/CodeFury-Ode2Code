package com.hsbc.networking.dao.UserAuth;


public class UserAuthFactory {
	public static UserAuthDao getUserAuthDao() {
		return new UserAuthImpl();
	}
}
