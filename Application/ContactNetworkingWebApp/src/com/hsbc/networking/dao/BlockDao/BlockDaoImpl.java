package com.hsbc.networking.dao.BlockDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.networking.model.User;
import com.hsbc.networking.dao.DBUtil.DBUtil;

public class BlockDaoImpl implements BlockDao {

	private static Connection conn;
	private static PreparedStatement getBlockList, isBlocked, blockUser, unBlockUser;

	static {
		try {
			conn = DBUtil.getDBConnection();
			getBlockList = conn.prepareStatement(
					"select user_id, username, fullName,emailId,profileImage from users where user_id in (select blockUserId from blockedUser b where b.user_id =?)");

			isBlocked = conn.prepareStatement("select 1 as flag from blockedUser where user_id=? and blockUserId =?");

			blockUser = conn.prepareStatement("insert into blockedUser (user_id,blockUserId) values (?,?)");

			unBlockUser = conn.prepareStatement("delete from blockedUser where user_id=? and blockUserId=?");

		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
	}

	public List<User> getBlockList(String userId) {
		List<User> blockList = new ArrayList<User>();

		try {
			getBlockList.setString(1, userId);
			ResultSet rs = getBlockList.executeQuery();
			while (rs.next()) {

				User u = new User();
				u.setUserId(rs.getString("user_id"));
				u.setUserName(rs.getString("username"));
				u.setEmail(rs.getString("emailId"));
				u.setProfileImage(rs.getBlob("profileImage"));
				u.setFullName(rs.getString("fullName"));
				blockList.add(u);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return blockList;
	}

	public boolean isBlocked(String userId, String blockUserId) {
		//System.out.println("Inside Isblocked Dao");
		boolean flag = false;

		try {
			isBlocked.setString(1, userId);
			isBlocked.setString(2, blockUserId);
			
			ResultSet rs = isBlocked.executeQuery();
			if (rs.next()) {
				int num = rs.getInt("flag");
				if(num==1)
					flag = true;
			}
			
			isBlocked.setString(1, blockUserId);
			isBlocked.setString(2, userId);
			
			rs = isBlocked.executeQuery();
			if (rs.next()) {
				int num = rs.getInt("flag");
				if(num==1)
					flag = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("flag valule:"+flag);
		return flag;
	}

	@Override
	public void blockUser(String userId, String blockUserId) throws SQLException {
		try {
			blockUser.setString(1, userId);
			blockUser.setString(2, blockUserId);
			blockUser.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void unBlockUser(String userId, String blockUserId) throws SQLException {
		try {
			unBlockUser.setString(1, userId);
			unBlockUser.setString(2, blockUserId);
			unBlockUser.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
