package com.hsbc.networking.service;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.networking.model.Admin;
import com.hsbc.networking.model.User;
import com.hsbc.networking.dao.AdminDao.AdminDao;
import com.hsbc.networking.dao.AdminDao.AdminDaoFactory;
import com.hsbc.networking.dao.UserAuth.UserAuthDao;
import com.hsbc.networking.dao.UserAuth.UserAuthFactory;

public class AdminService {

	private AdminDao dao = AdminDaoFactory.getAdminDao();
	private UserAuthDao uAuth = UserAuthFactory.getUserAuthDao();

	public List<User> userAll() {
		return dao.userAll();
	}

	public List<User> getDisableList() {
		return dao.getDisableList();
	}

	public int returnUserCount() {
		return dao.returnUserCount();
	}

	public boolean adminLogin(String username, String password) {
		// System.out.println("In Service - revieved"+username+" pass:"+password);
		return dao.adminLogin(username, password);
	}

	public void disableUser(String user_id) {
		dao.disableUser(user_id);
	}

	public void deleteUser(String id) {
		uAuth.deleteUserAuth(id);

	}

	public List<User> getDeleteList(int days) {
		return dao.getDeleteList(days);
	}

	public Admin getAdminInfo(String username) {
		Admin ad = dao.getAdminInfo(username);
		return ad;
	}

	public boolean addUserActivity(String userId) {
		// TODO Auto-generated method stub
		return dao.addUserActivity(userId);

	}
	
	public boolean disbaleInBatch(List<String> userIdList) throws SQLException {
		return dao.disbaleInBatch(userIdList);
	}
}