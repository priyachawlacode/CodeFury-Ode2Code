package com.hsbc.networking.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsbc.networking.dao.BlockDao.BlockDao;
import com.hsbc.networking.dao.BlockDao.BlockDaoFactory;
import com.hsbc.networking.dao.FriendsDao.FriendsDao;
import com.hsbc.networking.dao.FriendsDao.FriendsDaoFactory;
import com.hsbc.networking.exception.FriendsException.FriendBlockedException;
import com.hsbc.networking.exception.FriendsException.RequestAlreadyPendingException;
import com.hsbc.networking.model.FriendRequest;
import com.hsbc.networking.model.User;

public class FriendsService {
	private FriendsDao friendsDao = FriendsDaoFactory.getDaoInstance();

	public List<User> getFriendList(String userId) {
		// System.out.println("INside frnd service rec userid: "+userId);
		List<User> fList = friendsDao.getFriendList(userId);
		fList = validate(fList, userId);
		return fList;
	}

	private List<User> validate(List<User> fList, String userId) {
		UserService us = new UserService();
		List<Integer> toRemove = new ArrayList<>();
		for (User u : fList) {
			// check for user is blocked and disabled!!!!!!!!!!!!!!!!!!!!!!!!!
			if (!us.checkUserIsValid(u, userId))
				toRemove.add(fList.indexOf(u));
		}
		for (int index : toRemove) {
			fList.remove(index);
		}

		return fList;

	}

	@SuppressWarnings("unchecked")
	public HashMap<FriendRequest, User> getFriendRequestList(String userId) {
		// System.out.println("In get friend request list servlet");
		HashMap<FriendRequest, User> fReq = friendsDao.getFriendRequestList(userId);
		List<User> fList = new ArrayList<>();
		List<FriendRequest> fr = new ArrayList<>();
		for (Map.Entry<FriendRequest, User> f : fReq.entrySet()) {
			fList.add(f.getValue());
			fr.add(f.getKey());
		}
		fList = validate(fList, userId);
		
		int count = 0;
		for (User u : fList) {
			count++;
			if (!fReq.containsValue(u)) {
				fReq.remove(fr.get(count));
			}
		}
		return fReq;

	}

	public void sendFriendRequest(FriendRequest request) throws RequestAlreadyPendingException, FriendBlockedException {
		BlockDao block = BlockDaoFactory.getDaoInstance();
		if (block.isBlocked(request.getUser_id(), request.getFriend_id())) {
			throw new FriendBlockedException();
			// TODO: Add log
		}
		if (friendsDao.isPending(request)) {
			throw new RequestAlreadyPendingException();
			// TODO: Add log
		}

		friendsDao.sendFriendRequest(request);
	}

	public void removeFriend(String userId, String friendId) {
		friendsDao.removeFriend(userId, friendId);
	}

	public boolean acceptFriendRequest(FriendRequest request) throws SQLException {
		return friendsDao.acceptFriendRequest(request);
	}

	public boolean isFriend(FriendRequest request) {
		return friendsDao.isFriend(request);
	}

	public void declineFriendRequest(FriendRequest request) {
		friendsDao.declineFriendRequest(request);
	}

}
