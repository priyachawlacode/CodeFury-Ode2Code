package com.hsbc.networking.dao.UserDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.networking.model.User;
import com.hsbc.networking.dao.DBUtil.DBUtil;
import com.hsbc.networking.dao.UserAuth.UserAuthImpl;
import com.hsbc.networking.exception.UserException.UserAlreadyExistsException;
import com.hsbc.networking.exception.UserException.UserAuthDoesNotExistsException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;

public class UserDaoImpl implements UserDao {
	private static Connection conn;
	private static PreparedStatement addUser, upUser, getUserDet, dctvUser;

	static {
		try {
			conn = DBUtil.getDBConnection();

			addUser = conn.prepareStatement("insert into users(user_id," + "username,fullName,emailId,contactNo,"
					+ "gender,dob,address,city,state," + "country,company, profileImage) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			upUser = conn.prepareStatement("update users set fullName=?," + "contactNo=?,address=?,"
					+ "city=?,state=?,country=?,company=?," + " profileImage=? where user_id=?");

			getUserDet = conn.prepareStatement("select * from users where username=?");

			dctvUser = conn.prepareStatement("update users set isDeactivated = 1 where user_id= ?");

		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
	}

	@Override
	public boolean addUser(User u) throws UserAlreadyExistsException, UserAuthDoesNotExistsException {
		boolean isAdded = false;
		// CHECK NOT DISABLED BY USER
		//System.out.println(" UserDetails: from user dao" + u.toString());
		// before adding, add authentication
		if (!UserAuthImpl.checkExistingUser(u.getUserName())) {
			throw new UserAuthDoesNotExistsException("User Authentication not created");
		}
		Date sqlDate = new Date(u.getDob().getTime());
		//System.out.println(" UserDetails: from user dob in sql" + u.getDob());
		try {
			addUser.setString(1, u.getUserId());
			addUser.setString(2, u.getUserName());
			addUser.setString(3, u.getFullName());
			addUser.setString(4, u.getEmail());
			addUser.setString(5, u.getContactNo());
			addUser.setString(6, String.valueOf(u.getGender()));
			addUser.setDate(7, sqlDate);
			addUser.setString(8, u.getAddress());
			addUser.setString(9, u.getCity());
			addUser.setString(10, u.getState());
			addUser.setString(11, u.getCountry());
			addUser.setString(12, u.getCompany());
			addUser.setBlob(13, u.getProfileImage());

			int num = addUser.executeUpdate();
			if (num > 0) {
				isAdded = true;
			}

		} catch (SQLException e) {
			throw new UserAlreadyExistsException(e.getMessage());
		}

		return isAdded;
	}

	@Override
	public boolean updateCurrentUser(User u) {
		boolean isUpdated = false;
		try {
			upUser.setString(1, u.getFullName());
			upUser.setString(2, u.getContactNo());
			upUser.setString(3, u.getAddress());
			upUser.setString(4, u.getCity());
			upUser.setString(5, u.getState());
			upUser.setString(6, u.getCountry());
			upUser.setString(7, u.getCompany());
			upUser.setBlob(8, u.getProfileImage());
			upUser.setString(9, u.getUserId());

			int num = upUser.executeUpdate();
			if (num > 0) {
				isUpdated = true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return isUpdated;
	}

	// returns one user
	@Override
	public User getCurrentUserDetail(String username) throws UserDetailsNotFoundException {
		User u = null;

		try {
			getUserDet.setString(1, username);
			ResultSet rs = getUserDet.executeQuery();
			while (rs.next()) {
				u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6).charAt(0), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), rs.getBlob(13), rs.getBoolean(14), rs.getBoolean(15));
			}
			if (u == null)
				throw new UserDetailsNotFoundException("No details found!");

		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
		return u;
	}

	@Override
	public boolean deactivateUser(String username) {
		boolean isDeactivated = false;
		try {
			dctvUser.setString(1, username);
			int num = dctvUser.executeUpdate();
			if (num > 0) {
				isDeactivated = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return isDeactivated;
	}

	@Override
	public List<User> getFilteredListUsers(String type, String value) throws UserDetailsNotFoundException {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement getFilteredUserDet = conn.prepareStatement("select * from users where " + type + "=?");
			getFilteredUserDet.setString(1, value);
			ResultSet rs = getFilteredUserDet.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6).charAt(0), rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12), rs.getBlob(13), rs.getBoolean(14), rs.getBoolean(15)));
			}
			if (users.isEmpty())
				throw new UserDetailsNotFoundException("No details found for the search type!");
		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
//		for(User u:users ) {
//			System.out.println(u.toString());
//		}
		return users;
	}

}
