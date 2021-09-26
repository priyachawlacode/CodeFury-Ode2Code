package com.hsbc.networking.controller.UserServlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hsbc.networking.exception.UserException.UserDetailsNotValidException;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.UserService;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// configures upload settings
		/*
		 * DiskFileItemFactory factory = new DiskFileItemFactory();
		 * factory.setSizeThreshold(THRESHOLD_SIZE);
		 * 
		 * ServletFileUpload upload = new ServletFileUpload(factory);
		 * upload.setFileSizeMax(MAX_FILE_SIZE); upload.setSizeMax(MAX_REQUEST_SIZE);
		 * List formItems = null; try { formItems = upload.parseRequest(request); }
		 * catch (FileUploadException e5) { // TODO Auto-generated catch block
		 * e5.printStackTrace(); } Iterator iter = formItems.iterator();
		 * 
		 * String user_id = null, full_name = null, emailId = null, contact = null,
		 * gender = null, dob = null, address = null, city = null, state = null, country
		 * = null, company = null, username = null; Blob blob = null; byte[] photoData =
		 * null; // iterates over all form's fields while (iter.hasNext()) { FileItem
		 * item = (FileItem) iter.next();
		 * 
		 * // processes only fields that are not form fields if (!item.isFormField()) {
		 * if (item.getFieldName().equals("photo")) { photoData = item.get(); } }
		 * 
		 * // processes only fields that are not form fields if (item.isFormField()) {
		 * if (item.getFieldName().equals("userId")) { user_id = item.getString(); } if
		 * (item.getFieldName().equals("fullName")) { full_name = item.getString(); }
		 * 
		 * if (item.getFieldName().equals("emailId")) { emailId = item.getString(); }
		 * 
		 * if (item.getFieldName().equals("contactNo")) { contact = item.getString(); }
		 * if (item.getFieldName().equals("gender")) { gender = item.getString(); } if
		 * (item.getFieldName().equals("dob")) { dob = item.getString(); } if
		 * (item.getFieldName().equals("address")) { address = item.getString(); } if
		 * (item.getFieldName().equals("city")) { city = item.getString(); } if
		 * (item.getFieldName().equals("state")) { state = item.getString(); } if
		 * (item.getFieldName().equals("country")) { country = item.getString(); } if
		 * (item.getFieldName().equals("company")) { company = item.getString(); } if
		 * (item.getFieldName().equals("userName")) { username = item.getString(); }
		 * 
		 * } }
		 */

//		try {
//			blob = new SerialBlob(photoData);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		HttpSession session  = request.getSession();
		User uc = (User) session.getAttribute("currentUser");
		String user_id = request.getParameter("userId");
		String username = request.getParameter("userName");
		String full_name = request.getParameter("fullName");
		String emailId = request.getParameter("emailId");
		String contact = request.getParameter("contactNo");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country =request.getParameter("country");
		String company = request.getParameter("company");
		Blob blob = uc.getProfileImage();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = formatter.parse(dob);
			// System.out.print("\nINSIDE DATEFORMATTER REGISTER SERVLET recieved dob
			// string: "+dob+"\n converted date: "+date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (full_name.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || country.isEmpty()
				|| company.isEmpty() || contact.isEmpty()) {
			request.setAttribute("error", "All feilds are mandatory");
			RequestDispatcher req = request.getRequestDispatcher("/views/MainPage.jsp");
			req.include(request, response);
			return;
		}

		else if (contact.matches("\\d{10}") == false) {
			request.setAttribute("error", "Phone number should contain 10 digits");
			// System.out.print("INSIDE contact CHECK");
			RequestDispatcher req = request.getRequestDispatcher("/views/MainPage.jsp");
			req.include(request, response);
			return;
		} else {
			UserService uservice = new UserService();

			User updateUser = new User();
			updateUser.setUserId(user_id);
			updateUser.setUserName(username);
			updateUser.setFullName(full_name);
			updateUser.setEmail(emailId);
			updateUser.setContactNo(contact);
			updateUser.setDob(date);
			updateUser.setAddress(address);
			updateUser.setCity(city);
			updateUser.setState(state);
			updateUser.setCountry(country);
			updateUser.setCompany(company);
			updateUser.setProfileImage(blob);
			updateUser.setDeactivated(false);
			updateUser.setDisabled(false);

			char gen = 'f';
			if (gender.toLowerCase().equals("male"))
				gen = 'm';
			else if (gender.toLowerCase().equals("other"))
				gen = 'o';
			updateUser.setGender(gen);
		

			try {
				// System.out.println(" UserDetails:" + newUser.toString());
				uservice.updateCurrentUser(updateUser);
			} catch (UserDetailsNotValidException | SQLException e) {
				request.setAttribute("error", "Internal Server Error, try later");
				RequestDispatcher req = request.getRequestDispatcher("/views/MainPage.jsp");
				req.include(request, response);
				return;
			}
			// Check
			// update userdetails in mainpage
			//System.out.println("inside : dob: "+dob+ " "+updateUser.toString());
			session.setAttribute("currentUser", updateUser);
			RequestDispatcher req = request.getRequestDispatcher("/views/MainPage.jsp");
			req.forward(request, response);
		}
	}
}
