package com.hsbc.networking.dao.FriendsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hsbc.networking.dao.DBUtil.DBUtil;
import com.hsbc.networking.model.FriendRequest;
import com.hsbc.networking.model.User;

public class FriendsDaoImpl implements FriendsDao {

	private static Connection conn;
	private static PreparedStatement getFriendList, getFriendList2, remFriend, getFrndReqList, isFrnd, sendFrndReq,
			accptFrndReq, accptFrndReq2, declineReq, isPending,acceptFrndReq3;

	static {
		try {
			conn = DBUtil.getDBConnection();
			getFriendList = conn.prepareStatement(
					"select u.isDeactivated,u.isDisabled,u.user_id, u.username,u.emailId,u.address,u.city,u.state,u.country,u.company,u.profileImage,u.gender,u.fullName ,u.dob,u.contactNo from users u,friends f where f.user_id=?   and f.friend_id=u.user_id");

			getFriendList2 = conn.prepareStatement(
					"select u.isDeactivated,u.isDisabled,u.user_id,u.username,u.emailId,u.address,u.city,u.state,u.country,u.company,u.profileImage,u.gender,u.fullName ,u.dob,u.contactNo from users u,friends f where   f.friend_id=? and f.user_id=u.user_id");

			remFriend = conn.prepareStatement(
					"delete from friends where  (user_id=? and friend_id=?) or (user_id=? and friend_id=?)");

			getFrndReqList = conn.prepareStatement(
					" select f.message , u.isDeactivated,u.isDisabled,u.user_id,u.fullname,u.profileImage from users u join friendListRequest f on f.sender_id=u.user_id where f.user_id=? ");

			isFrnd = conn.prepareStatement(
					"select * from friends where (user_id=? and friend_id=?) or (user_id=? and friend_id=?)");

			sendFrndReq = conn
					.prepareStatement("insert into friendListRequest (user_id,sender_id, message) values(?, ?, ?) ");

			accptFrndReq = conn.prepareStatement("delete from friendListRequest where user_id=? and sender_id=?");

			accptFrndReq2 = conn.prepareStatement("insert into friends (user_id,friend_id) values (?,?)");
			

			declineReq = conn.prepareStatement("delete from friendListRequest where user_id=? and sender_id=?");

			isPending = conn.prepareStatement(
					"select 1 as flag from friendListRequest where user_id=? and sender_id=? ");

		} catch (SQLException e) {
			System.out.println("DB CONN ERROR: " + e.getMessage());
		}
	}

	@Override
	public List<User> getFriendList(String userId) {
		//System.out.println("INside frnd daoimpl rec userid: " + userId);
		List<User> friendList = new ArrayList<User>();
		try {
			getFriendList.setString(1, userId);

			ResultSet rs = getFriendList.executeQuery();
			while (rs.next()) {

				User u = new User();
				u.setDeactivated(rs.getBoolean("isDeactivated"));
				u.setDeactivated(rs.getBoolean("isDisabled"));
				u.setUserId(rs.getString("user_id"));
				u.setAddress(rs.getString("address"));
				u.setCity(rs.getString("city"));
				u.setCompany(rs.getString("company"));
				u.setDob(rs.getDate("dob"));
				u.setCountry(rs.getString("country"));
				u.setEmail(rs.getString("emailId"));
				u.setGender(rs.getString("gender").charAt(0));
				u.setContactNo(rs.getString("contactNo"));
				u.setProfileImage(rs.getBlob("profileImage"));
				u.setFullName(rs.getString("fullname"));
				u.setState(rs.getString("state"));
				u.setUserName(rs.getString("username"));
				//System.out.println("INside frnd daoimpl rec friend: " + u.toString());
				friendList.add(u);

			}
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
//		for (User u : friendList) {
//			System.out.println("Returning friend list from frienddaoimpl :" + u.toString());
//		}
		return friendList;
	}

	@Override
	public void removeFriend(String userId,String friendId) {

		try {
			remFriend.setString(1, userId);
			remFriend.setString(2, friendId);
			remFriend.setString(3, friendId);
			remFriend.setString(4, userId);
			remFriend.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public HashMap<FriendRequest, User> getFriendRequestList(String userId) {
		HashMap<FriendRequest, User> friendRequestMap = new HashMap<FriendRequest, User>();
		try {
			getFrndReqList.setString(1, userId);
			ResultSet rs = getFrndReqList.executeQuery();
			while (rs.next()) {
				User u = new User();
				FriendRequest request = new FriendRequest(rs.getString("user_id"), userId, rs.getString("message"));
				u.setUserId(rs.getString("user_id"));
				u.setDeactivated(rs.getBoolean("isDeactivated"));
				u.setDeactivated(rs.getBoolean("isDisabled"));
				u.setProfileImage(rs.getBlob("profileImage"));
				u.setFullName(rs.getString("fullname"));
				friendRequestMap.put(request, u);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(friendRequestMap);
		return friendRequestMap;
	}

	@Override
	public boolean isFriend(String userId, String requestId) {
		boolean b = true;
		try {
			isFrnd.setString(1, userId);
			isFrnd.setString(2, requestId);
			isFrnd.setString(3, requestId);
			isFrnd.setString(4, userId);
			ResultSet rs = isFrnd.executeQuery();
			if (!rs.next()) {
				b = false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return b;
	}

	public void sendFriendRequest(FriendRequest request) {
		try {
			sendFrndReq.setString(1, request.getFriend_id());
			sendFrndReq.setString(2, request.getUser_id());
			sendFrndReq.setString(3, request.getMessage());
			sendFrndReq.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean acceptFriendRequest(FriendRequest request) throws SQLException {
		try {
			conn.setAutoCommit(false);
			accptFrndReq.setString(1, request.getUser_id());
			accptFrndReq.setString(2, request.getFriend_id());
			accptFrndReq.executeUpdate();
			
			accptFrndReq2.setString(1, request.getUser_id());
			accptFrndReq2.setString(2, request.getFriend_id());
			accptFrndReq2.executeUpdate();
			

			accptFrndReq2.setString(1, request.getFriend_id());
			accptFrndReq2.setString(2,request.getUser_id());
			accptFrndReq2.executeUpdate();


			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				System.err.println("Error in rollback!");
				// TODO: Add log
			}
			System.out.println(e);
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				System.err.println("Error in finally block!");
				// TODO: Add log
			}
		}

		return true;

	}

	@Override
	public void declineFriendRequest(FriendRequest request) {

		try {
			declineReq.setString(1, request.getUser_id());
			declineReq.setString(2, request.getFriend_id());
			declineReq.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	@Override
	public boolean isPending(String userId, String requestId) {
		boolean flag = false;
		try {
			isPending.setString(1, requestId);
			isPending.setString(2, userId);
	
			ResultSet rs = isPending.executeQuery();
			while(rs.next()) {
				flag = rs.getBoolean("flag");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
