package com.hsbc.networking.dao.UserDao;

import java.util.List;

import com.hsbc.networking.model.User;
import com.hsbc.networking.exception.UserException.UserAlreadyExistsException;
import com.hsbc.networking.exception.UserException.UserAuthDoesNotExistsException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;

public interface UserDao {
	
		//to add User
		public boolean addUser(User p) throws UserAlreadyExistsException, UserAuthDoesNotExistsException;
		
		//to update details of User (Username,email,gender,dob will not get updated)
		//Prefill data of user
		public boolean updateCurrentUser(User p);
		
		//get Current user details username 
		public User getCurrentUserDetail(String username) throws UserDetailsNotFoundException;
		
		//get user details by address/state/city/phone number etc -> returns list of users
		public List<User> getFilteredListUsers(String type, String value) throws UserDetailsNotFoundException;
		
		//Deactivate user Account
		public boolean deactivateUser(String username);

		public User checkUserMailIdExists(String emailId);
}
