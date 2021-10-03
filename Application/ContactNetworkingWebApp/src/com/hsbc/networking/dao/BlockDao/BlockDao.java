package com.hsbc.networking.dao.BlockDao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.networking.model.User;

public interface BlockDao {
	List<User> getBlockList(String userId);

	boolean isBlocked(String userId, String friend_id);

	void blockUser(String userId, String blockUserId) throws SQLException;

	void unBlockUser(String userId, String blockUserId) throws SQLException;
}
