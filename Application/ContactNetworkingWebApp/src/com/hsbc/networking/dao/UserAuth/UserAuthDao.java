package com.hsbc.networking.dao.UserAuth;

import com.hsbc.networking.model.UserAuth;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.networking.exception.UserException.UserAlreadyExistsException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;

public interface UserAuthDao {
	// to add User-Auth
	public boolean addUserAuth(UserAuth u) throws UserAlreadyExistsException;

	// to update password
	// Pre fill data of user
	public boolean updateUserAuth(UserAuth u);

	// get user autherization
	public UserAuth getUserAuth(String username) throws UserDetailsNotFoundException;

	// Delete user from system
	public boolean deleteUserAuth(String id);

	boolean deleteInBatch(List<String> userIdList) throws SQLException;
	
}
