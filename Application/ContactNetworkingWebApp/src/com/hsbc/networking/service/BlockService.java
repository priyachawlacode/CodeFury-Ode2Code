package com.hsbc.networking.service;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.networking.model.User;
import com.hsbc.networking.dao.BlockDao.BlockDao;
import com.hsbc.networking.dao.BlockDao.BlockDaoFactory;

public class BlockService {
	private BlockDao blockDao = BlockDaoFactory.getDaoInstance();

	public boolean isBlocked (String userId, String blockUserId) {
		return blockDao.isBlocked(userId,blockUserId);
	}

	public List<User> getBlockList(String userId) {
		return blockDao.getBlockList(userId);
	}

	public void blockUser(String userId, String blockUserId) throws SQLException {
		blockDao.blockUser(userId, blockUserId);
	}

	public void unBlockUser(String userId, String blockUserId) throws SQLException {
		blockDao.unBlockUser(userId, blockUserId);
	}
}
