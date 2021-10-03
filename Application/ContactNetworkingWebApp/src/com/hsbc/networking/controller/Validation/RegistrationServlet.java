package com.hsbc.networking.controller.Validation;

import com.hsbc.networking.exception.UserException.UserAlreadyExistsException;
import com.hsbc.networking.exception.UserException.UserAuthDoesNotExistsException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserDetailsNotValidException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;
import com.hsbc.networking.model.User;
import com.hsbc.networking.model.UserAuth;
import com.hsbc.networking.service.UserAuthService;
import com.hsbc.networking.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class RegistrationServlet
 */

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// checks if the request actually contains upload file
		if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter writer = response.getWriter();
			writer.println("Request does not contain any data");
			writer.flush();
			return;
		}

		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		List formItems = null;
		try {
			formItems = upload.parseRequest(request);
		} catch (FileUploadException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		Iterator iter = formItems.iterator();

		String full_name = null, emailId = null, contact = null, gender = null, dob = null, address = null, city = null,
				state = null, country = null, company = null, username = null, password = null, confirm_password = null;
		Blob blob = null;
		byte[] photoData = null;
		// iterates over all form's fields
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();

			// processes only fields that are not form fields
			if (!item.isFormField()) {
				if (item.getFieldName().equals("photo")) {
					photoData = item.get();
				}
			}

			// processes only fields that are not form fields
			if (item.isFormField()) {
				if (item.getFieldName().equals("full_name")) {
					full_name = item.getString();
				}

				if (item.getFieldName().equals("email")) {
					emailId = item.getString();
				}

				if (item.getFieldName().equals("contact")) {
					contact = item.getString();
				}
				if (item.getFieldName().equals("gender")) {
					gender = item.getString();
				}
				if (item.getFieldName().equals("dob")) {
					dob = item.getString();
				}
				if (item.getFieldName().equals("address")) {
					address = item.getString();
				}
				if (item.getFieldName().equals("city")) {
					city = item.getString();
				}
				if (item.getFieldName().equals("state")) {
					state = item.getString();
				}
				if (item.getFieldName().equals("country")) {
					country = item.getString();
				}
				if (item.getFieldName().equals("company")) {
					company = item.getString();
				}
				if (item.getFieldName().equals("username")) {
					username = item.getString();
				}
				if (item.getFieldName().equals("password")) {
					password = item.getString();
				}
				if (item.getFieldName().equals("cpassword")) {
					confirm_password = item.getString();
				}
			}
		}

		try {
			blob = new SerialBlob(photoData);
			// System.out.println("\nBLOB:------------------------" + blob.toString() +
			// "\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		// Matcher matcher = pattern.matcher(emailId);

		// run this blockk only if dob is not null
		if (dob == "") {
			request.setAttribute("error", "One or more fields empty");
			RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
			req.include(request, response);
			return;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = formatter.parse(dob);
			// System.out.print("\nINSIDE DATEFORMATTER REGISTER SERVLET recieved dob
			// string: "+dob+"\n converted date: "+date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Instant instant = date.toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
		LocalDate givenDate = zone.toLocalDate();

		Period period = Period.between(givenDate, LocalDate.now());

		if (full_name.isEmpty() || emailId.isEmpty() || username.isEmpty() || password.isEmpty() || address.isEmpty()
				|| city.isEmpty() || state.isEmpty() || country.isEmpty() || company.isEmpty()
				|| confirm_password.isEmpty() || contact.isEmpty() || gender.isEmpty() || dob.isEmpty()) {
			request.setAttribute("error", "All feilds are mandatory");
			// System.out.print("INSIDE ALLFIELDS CHECK");
			RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
			req.include(request, response);
			return;
		}

		else if (contact.matches("\\d{10}") == false) {
			request.setAttribute("error", "Phone number should contain 10 digits");
			// System.out.print("INSIDE contact CHECK");
			RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
			req.include(request, response);
			return;
		}

		else if (period.getYears() < 18) {
			request.setAttribute("error", "Age should be above 18");
			// System.out.print("INSIDE period CHECK");
			RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
			req.include(request, response);
			return;
		}

		else if (confirm_password == password) {
			request.setAttribute("error", "Passwords should match");
			// System.out.print("INSIDE password CHECK");
			RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
			req.include(request, response);
			return;
		}

		else {
			// send email for verification
			Email obj = new Email();
			// otpgen
			Random rnd = new Random();
			int number = rnd.nextInt(999999);
			String otp = String.format("%06d", number);
			// this will convert any number sequence into 6 character.
			obj.email(emailId, otp);

			// sending otp to verification jsp
			HttpSession session = request.getSession();
			session.setAttribute("otp", otp);

			try {
				// checking if email already exists
				UserService us = new UserService();
				User checkU = us.checkUserMailIdExists(emailId);
				if (checkU != null) {
					throw new UserAlreadyExistsException("This email id is already registered!");
				}

				UserAuth userAuth = new UserAuth(username, password);
				// System.out.print("\nUser Auth created "+userAuth.toString());

				UserAuthService authService = new UserAuthService();
				boolean added = false;
				try {
					added = authService.addUserAuth(userAuth);
				} catch (UserIsDisabledException | UserIsDeactivatedException e1) {
					request.setAttribute("error", e1.getMessage());
					// System.out.print("INSIDE User is disabled or deactivated CHECK");
					RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
					req.include(request, response);
					return;
				}
				if (added) {

					/// Add ther user details
					User newUser = new User();
					newUser.setAddress(address);
					newUser.setCity(city);
					newUser.setCompany(company);
					newUser.setContactNo(contact);
					newUser.setCountry(country);
					newUser.setDeactivated(false);
					newUser.setDisabled(false);
					newUser.setDob(date);
					newUser.setEmail(emailId);
					newUser.setFullName(full_name);
					// System.out.print("inside RegServlet User dob: recieved"+ newUser.getDob());
					char gen = 'f';
					if (gender.equals("male"))
						gen = 'm';
					else if (gender.equals("other"))
						gen = 'o';
					newUser.setGender(gen);
					// set profile image for user from here data type-> Blob
					newUser.setProfileImage(blob);
					newUser.setState(state);
					newUser.setUserId(userAuth.getUserId());
					newUser.setUserName(username);
					try {
						// System.out.println(" UserDetails:" + newUser.toString());
						us.addUser(newUser);
					} catch (UserAuthDoesNotExistsException | SQLException e) {
						request.setAttribute("error", "Internal Server Error, try later");
						RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
						req.include(request, response);
						return;
					}

					RequestDispatcher req = request.getRequestDispatcher("/views/VerifyOtp.jsp");
					req.forward(request, response);
					return;
//					RequestDispatcher req = request.getRequestDispatcher("/views/LoginJSP.jsp");
//					req.forward(request, response);
				} else {
					request.setAttribute("error", "Some Error Occured");
					RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
					req.include(request, response);
					return;
				}
			}

			catch (

			UserDetailsNotFoundException e2) {
				request.setAttribute("error", e2.getMessage());
				// System.out.print("INSIDE User details not found catch1 CHECK");
				RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
				req.include(request, response);
				return;
			} catch (UserAlreadyExistsException e3) {
				request.setAttribute("error", e3.getMessage());
				// System.out.print("INSIDE User already exists found catch2 CHECK");
				RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
				req.include(request, response);
				return;
			} catch (UserDetailsNotValidException e4) {
				request.setAttribute("error", e4.getMessage());
				// System.out.print("INSIDE User details not valid catch3 CHECK");
				RequestDispatcher req = request.getRequestDispatcher("/views/RegistrationJSP.jsp");
				req.include(request, response);
				return;
			}

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
