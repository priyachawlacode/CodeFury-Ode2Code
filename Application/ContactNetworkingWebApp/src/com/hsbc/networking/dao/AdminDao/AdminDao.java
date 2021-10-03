package com.hsbc.networking.dao.AdminDao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.networking.model.Admin;
import com.hsbc.networking.model.User;

public interface AdminDao {
	public List<User> userAll();

	public List<User> getDisableList();

	public int returnUserCount();

	public List<User> getDeleteList(int days);

	public boolean adminLogin(String username, String password);

	public void disableUser(String user_id);

	public Admin getAdminInfo(String username);
	public boolean addUserActivity(String userId);

	boolean disbaleInBatch(List<String> userIdList) throws SQLException;
}