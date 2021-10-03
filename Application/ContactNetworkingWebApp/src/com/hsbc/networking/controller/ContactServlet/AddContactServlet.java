package com.hsbc.networking.controller.ContactServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import com.hsbc.networking.exception.ContactException.ContactAlreadyExistsException;
import com.hsbc.networking.exception.ContactException.ContactDetailsNotValidException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;
import com.hsbc.networking.model.Contact;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.ContactService;
import com.hsbc.networking.service.UserService;

/**
 * Servlet implementation class AddContactServlet
 */
@WebServlet("/AddContactServlet")
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	
	private static UserService userService = new UserService();

	// create new contact service instance
	private static ContactService contactService = new ContactService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside doPost of Add contact servlet");

		User u = new User();
		Contact contact = new Contact();
		HttpSession session = request.getSession();
		u = (User)session.getAttribute("currentUser");

		//FORM DATA ACCESS : ----------------------------------------------
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

		String contactId = null, contactName = null, email = null, phnNo = null, gender = null, dob = null, city = null,
				state = null, country = null, company = null, address = null;
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
				if (item.getFieldName().equals("contactId")) {
					contactId = item.getString();
				}
				if (item.getFieldName().equals("contactName")) {
					contactName = item.getString();
				}

				if (item.getFieldName().equals("email")) {
					email = item.getString();
				}

				if (item.getFieldName().equals("phnNo")) {
					phnNo = item.getString();
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
				
			}
		}

		try {
			blob = new SerialBlob(photoData);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//FORM DATA ACCESS CLOSE: -----------------------------------------
		
		contact.setContactId(contactId);
		contact.setFullName(contactName);
		contact.setEmail(email);
		contact.setContactNo(phnNo);
		contact.setGender(gender.charAt(0));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = formatter.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		contact.setDob(date);
		contact.setAddress(address);
		contact.setCity(city);
		contact.setState(state);
		contact.setCountry(country);
		contact.setCompany(company);
		contact.setProfileImage(blob);
		System.out.println(contact.toString());

		
		try {
			contactService.addContact(contact, u.getUserId());
			
			request.getRequestDispatcher("/ViewContactServlet").forward(request, response);
		} catch (ContactDetailsNotValidException  | SQLException e) {
			
			e.printStackTrace();
			request.setAttribute("error", "Contact Details not valid");
			request.getRequestDispatcher("/views/AddContact.jsp").forward(request, response);
		}
		catch(ContactAlreadyExistsException e1) {
			request.getRequestDispatcher("/views/AddContact.jsp").forward(request, response);
		}

	}

}
