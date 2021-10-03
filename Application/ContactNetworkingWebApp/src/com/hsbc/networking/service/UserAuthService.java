package com.hsbc.networking.service;

import com.hsbc.networking.model.User;
import com.hsbc.networking.model.UserAuth;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.networking.dao.UserAuth.UserAuthDao;
import com.hsbc.networking.dao.UserAuth.UserAuthFactory;
import com.hsbc.networking.exception.UserException.UserAlreadyExistsException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserDetailsNotValidException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;

public class UserAuthService {
	private static UserAuthDao userAuthDao = UserAuthFactory.getUserAuthDao();

	// to add User-Auth
	public boolean addUserAuth(UserAuth u) throws UserAlreadyExistsException,UserDetailsNotValidException, UserIsDisabledException, UserDetailsNotFoundException, UserIsDeactivatedException {
		//System.out.println("INSIDE UserAUth addUser");
		if(!checkUserIsDisabled(u))
			throw new UserIsDisabledException("User has been disabled by admin, contact admin");
		if(!validateUserAuthDetails(u))
			throw new UserDetailsNotValidException("Details either username/password are incorrect");
		//System.out.println("INSIDE UserAuth add user exiting..");
		return userAuthDao.addUserAuth(u);
	};

	private boolean checkUserIsDisabled(UserAuth u) throws  UserIsDeactivatedException, UserIsDisabledException {
		boolean checked = true;
		UserService us = new UserService();
		User user = new User();
		try {
			user = us.getCurrentUserDetail(u.getUserName());
		} catch (UserDetailsNotFoundException e) {
			checked = true;
		} catch (UserIsDeactivatedException e) {
			checked = true;
		} catch (UserIsDisabledException e) {
			checked = false;
		}
		if(user.isDisabled())
			checked = false;
		return checked;
	}

	// to update password
	// Pre-fill data for user
	public boolean updateUserPassword(UserAuth u) {
		return userAuthDao.updateUserAuth(u);
	}

	// get user authorization
	public UserAuth getUserAuth(String username) throws UserDetailsNotFoundException {
		//System.out.println("INSIDE USERAUTHSERVICE:GETUSERAUTH -> RECIEVED USERNAME"+username);
		return userAuthDao.getUserAuth(username);
	}

	// Delete user from system
	public boolean deleteUserAuth(String userid) {
		return userAuthDao.deleteUserAuth(userid);
	}

	// Validates User password
	public boolean validateUserPassword(String username, String password) throws UserDetailsNotFoundException {
			boolean valid = false;
			UserAuth ua = userAuthDao.getUserAuth(username);
			if(ua.getUserId() == null)
				return valid;
			UserAuth rec = new UserAuth(username,password);
			//System.out.println("INSIDE USERAUTHSERVICE:ValidateUserPassword -> RECIEVED userAuth ua = "+ua.toString()+"\nrec:"+rec.toString());
			if(ua.getEncrptPass().equals(rec.getEncrptPass())) {
				valid = true;
			}
			return valid;
		}

	private boolean validateUserAuthDetails(UserAuth u) {
		boolean isValid = true;
		if(u.getUserName().isEmpty() || u.getUserId().isEmpty() || u.getEncrptPass().isEmpty())
			isValid = false;
		return isValid;
	}
	
	public boolean deleteInBatch(List<String> userIdList) throws SQLException {
		return userAuthDao.deleteInBatch(userIdList);
	}
}
