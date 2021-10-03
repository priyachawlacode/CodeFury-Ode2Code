package com.hsbc.networking.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hsbc.networking.model.User;
import com.hsbc.networking.dao.UserDao.UserDao;
import com.hsbc.networking.dao.UserDao.UserFactory;
import com.hsbc.networking.exception.UserException.UserAlreadyExistsException;
import com.hsbc.networking.exception.UserException.UserAuthDoesNotExistsException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserDetailsNotValidException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;

public class UserService {
	private static UserDao ud = UserFactory.getUserDao();

	@SuppressWarnings("deprecation")
	private static void validateUserDetails(User u) throws UserDetailsNotValidException, SQLException {
		// validate all fields mandatory
		if (u.getUserId().isEmpty() || u.getUserName().isEmpty() || u.getState().isEmpty() || u.getGender() == ' '
				|| u.getFullName().isEmpty() || u.getEmail().isEmpty() || u.getDob() == null || u.getCountry().isEmpty()
				|| u.getContactNo().isEmpty() || u.getCompany().isEmpty() || u.getCity().isEmpty()
				|| u.getAddress().isEmpty())
			throw new UserDetailsNotValidException("Each field is mandatory to enter");

		// validate mail
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(u.getEmail());
		if (!matcher.matches()) {
			throw new UserDetailsNotValidException("Email not in correct format");
		}

		// validate phone number - 10 digits
		if (!u.getContactNo().matches("\\d{10}")) {
			throw new UserDetailsNotValidException("Contact number incorrect");
		}

		// validate Age>18
		int currYear = new Date().getYear();
		int age = currYear - u.getDob().getYear();

		if (age < 18) {
			throw new UserDetailsNotValidException("User age should be greater than 18!");
		}
	}

	// to add User
	public boolean addUser(User u) throws UserAlreadyExistsException, UserAuthDoesNotExistsException,
			UserDetailsNotValidException, SQLException {
		// User id should be passed using UserAuth
		validateUserDetails(u);
		return ud.addUser(u);
	}

	// to update details of User (Username,email,gender,dob will not get updated)
	public boolean updateCurrentUser(User u) throws UserDetailsNotValidException, SQLException {
		validateUserDetails(u);
		return ud.updateCurrentUser(u);
	}

	
	public User getCurrentUserDetail(String username)
			throws UserDetailsNotFoundException, UserIsDeactivatedException, UserIsDisabledException {
		User u = ud.getCurrentUserDetail(username);
		if (u.isDeactivated())
			throw new UserIsDeactivatedException("Cannot give details-> user is deactivated");
		if (u.isDisabled())
			throw new UserIsDisabledException("Cannot give details-> user is disabled");
		return u;
	}

	// get user details by address/state/city/phone number etc -> returns list of
	// users, example: type "city" value: "udaipur", type "state": "Rajasthan" etc
	public Dictionary<User, Integer> getFilteredListUsers(String type, String value, String currentUserId)
			throws UserDetailsNotFoundException {
		List<User> uList = ud.getFilteredListUsers(type, value);
		List<Integer> toRemove = new ArrayList<>();
		for (User u : uList) {
			// check for user is blocked and disabled!
			if (!checkUserIsValid(u, currentUserId)) {
				toRemove.add(uList.indexOf(u));
			}
		}
		int count =0 ;
		for (int index : toRemove) {
			uList.remove(index-count);
			count++;
		}
		
		//check if the searched user has status -> pending request ="0",is a friend ="1,"or send friend ="2"
		return getSearchedUserStatus(uList,currentUserId);
//		return uList;
	}

	public Dictionary<User, Integer> getSearchedUserStatus(List<User> uList, String currUserId) {
		Dictionary<User, Integer> uStatusList = new Hashtable<User, Integer>();;
		FriendsService fs = new FriendsService();
		for(User u : uList) {
			//status -> pending request ="0",is a friend ="1,"or send friend ="2"
			int num = 2;
			if(fs.isPending(currUserId,u.getUserId())) {
				num = 0;
			}
			else if(fs.isFriend(currUserId,u.getUserId())) {
				num = 1;
			}
			else {
				num = 2;
			}
			uStatusList.put(u, num);	
		}
//		for (Entry<User, Integer> entry : ((Hashtable<User, Integer>) uStatusList).entrySet()) {
//		    User key = entry.getKey();
//		    int val = entry.getValue();
//		    System.out.println("User for which :"+key.getUserName()+" Status is: "+val);
//		}
		return uStatusList;
	}

	// Deactivate user Account
	public boolean deactivateUser(String username) {
		return ud.deactivateUser(username);
	}

	// Check if its user's birthday today
	@SuppressWarnings("deprecation")
	public boolean checkUserBirthday(Date dob) {
		boolean isBirthday = false;

		// get current date
		LocalDate currentDate = LocalDate.now();

		// get current date and month
		int date = currentDate.getDayOfMonth();
		int month = currentDate.getMonthValue();
		if (dob.getDate() == date && (dob.getMonth() + 1) == month) {
			isBirthday = true;
		}
		
		return isBirthday;
	}

	// checks for disabled, deactivated and blocked users
	boolean checkUserIsValid(User u, String currentUserId) {
		boolean checked = true;
		// checks if user is disabled or deactivated
		if (u.getUserId().equals(currentUserId)) {
			checked = false;
		} else if (u.isDeactivated() || u.isDisabled()) {
			checked = false;
		} else {
			BlockService bs = new BlockService();
			if (bs.isBlocked(currentUserId, u.getUserId())) {
				checked = false;
			}
		}
		return checked;
	}

	public User checkUserMailIdExists(String emailId) {
		User u = null;
		u = ud.checkUserMailIdExists(emailId);
		return u;
	}
	
	
}
