package com.hsbc.networking.dao.UserAuth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hsbc.networking.model.UserAuth;
import com.hsbc.networking.dao.DBUtil.DBUtil;
import com.hsbc.networking.exception.UserException.UserAlreadyExistsException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;

public class UserAuthImpl implements UserAuthDao {

	private static Connection conn;
	private static PreparedStatement addUserAuth, upUserAuth, getUserAuth, dltUser, chckExistUser, deleteFromContact,
			getUserId;

	static {
		try {
			conn = DBUtil.getDBConnection();

			addUserAuth = conn
					.prepareStatement("insert into authentication(user_id, username, password) " + "values(?,?,?)");

			upUserAuth = conn.prepareStatement("update authentication set password=? where user_id=?");

			getUserAuth = conn.prepareStatement("select * from authentication where username=?");

			dltUser = conn.prepareStatement("delete from authentication where user_id=?");

			chckExistUser = conn.prepareStatement("select 1 from authentication where username = ? ");

			deleteFromContact = conn.prepareStatement("delete from contactdetails "
					+ "join contactlist on contactdetails.contact_id = contactlist.contact_id " + "where user_id = ?");
			getUserId = conn.prepareStatement("select user_id from authentication where username=?");
		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
	}

	@Override
	public boolean addUserAuth(UserAuth u) throws UserAlreadyExistsException {

		boolean userAuthAdded = false;
		// check if already exists
		if (checkExistingUser(u.getUserName())) {
			throw new UserAlreadyExistsException("Username already exists!");
		}
		try {
			addUserAuth.setString(1, u.getUserId());
			addUserAuth.setString(2, u.getUserName());
			addUserAuth.setString(3, u.getEncrptPass());

			int num = addUserAuth.executeUpdate();
			if (num > 0) {
				userAuthAdded = true;
			}

		} catch (Exception e) {
			throw new UserAlreadyExistsException(e.getMessage());
		}
		return userAuthAdded;
	}

	@Override
	public boolean updateUserAuth(UserAuth u) {
		boolean userAuthUpdated = false;
		try {
			upUserAuth.setString(1, u.getEncrptPass());
			upUserAuth.setString(2, u.getUserId());

			int num = upUserAuth.executeUpdate();
			if (num > 0) {
				userAuthUpdated = true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return userAuthUpdated;
	}

	@Override
	public UserAuth getUserAuth(String username) throws UserDetailsNotFoundException {
		UserAuth ua = new UserAuth();
		try {
			getUserAuth.setString(1, username);
			ResultSet rs = getUserAuth.executeQuery();
			while (rs.next()) {
				ua = new UserAuth(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			throw new UserDetailsNotFoundException("DB CONN ERROR: " + e.getMessage());
		}
		return ua;
	}

	@Override
	public boolean deleteUserAuth(String username) {
		// first delete all the contacts available for that user_id then delete the user
		boolean userDeleted = deleteAllContacts(getUserId(username));
		try {
			dltUser.setString(1, username);
			int res = dltUser.executeUpdate();
			if (res > 0) {
				userDeleted = true;
			}
		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
		return userDeleted;
	}

	public static boolean checkExistingUser(String username) {
		boolean checkExists = false;
		try {
			chckExistUser.setString(1, username);
			ResultSet rs = chckExistUser.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					checkExists = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
		return checkExists;
	}

	//Deletes all the contacts available for a given user, before deleting the user itself
	private boolean deleteAllContacts(String userId) {
		boolean contactsDeleted = false;
		try {
			deleteFromContact.setString(1, userId);
			int res = deleteFromContact.executeUpdate();
			if (res > 0) {
				contactsDeleted = true;
			}
		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
		return contactsDeleted;
	}

	private String getUserId(String username) {
		String uid = null;
		try {
			getUserId.setString(1, username);
			ResultSet rs = getUserId.executeQuery();
			while (rs.next()) {
				uid = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
		return uid;
	}
}
