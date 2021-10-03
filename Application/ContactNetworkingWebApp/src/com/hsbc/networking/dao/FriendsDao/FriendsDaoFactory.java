package com.hsbc.networking.dao.FriendsDao;

public class FriendsDaoFactory {
	public static FriendsDao getDaoInstance()
	{
		 return new FriendsDaoImpl();
	}
}
