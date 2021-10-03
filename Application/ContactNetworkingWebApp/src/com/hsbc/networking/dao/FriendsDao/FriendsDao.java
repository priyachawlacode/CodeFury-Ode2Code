package com.hsbc.networking.dao.FriendsDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.hsbc.networking.model.FriendRequest;
import com.hsbc.networking.model.User;

public interface FriendsDao {

	List<User> getFriendList(String userId);

	HashMap<FriendRequest, User> getFriendRequestList(String userId);

	void sendFriendRequest(FriendRequest request);

	void declineFriendRequest(FriendRequest request);

	void removeFriend(String userId,String friendId);

	boolean acceptFriendRequest(FriendRequest request) throws SQLException;

	boolean isFriend(String userId, String requestId);

	boolean isPending(String userId,String requestId);

}
