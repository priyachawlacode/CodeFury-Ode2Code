package com.hsbc.networking.controller.ContactServlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

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
		String Uname = (String) session.getAttribute("username");

		try {
			u = userService.getCurrentUserDetail(Uname);
		} catch (UserDetailsNotFoundException | UserIsDeactivatedException | UserIsDisabledException e) {
			request.setAttribute("errorMessage", "Internal Server Error, try later!");
			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
		}

		contact.setContactId(request.getParameter("contactId"));
		contact.setFullName(request.getParameter("contactName"));
		contact.setEmail(request.getParameter("email"));
		contact.setContactNo(request.getParameter("phnNo"));
		contact.setGender(request.getParameter("gender").toLowerCase().charAt(0));
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		try {
			date = formatter.parse(request.getParameter("dob"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		contact.setDob(date);
		contact.setAddress(request.getParameter("address"));
		contact.setCity(request.getParameter("city"));
		contact.setState(request.getParameter("state"));
		contact.setCountry(request.getParameter("country"));
		contact.setCompany(request.getParameter("company"));
		System.out.println(contact.toString());
		// profile image remaining

		// calling add contact method
		
		try {
			contactService.addContact(contact, u.getUserId());
			
			request.getRequestDispatcher("/ViewContactServlet").forward(request, response);
		} catch (ContactDetailsNotValidException  | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "Contact Details not valid");
			request.getRequestDispatcher("/views/AddContact.jsp").forward(request, response);
		}
		catch(ContactAlreadyExistsException e1) {
			//get contact details. TO DO
			
			request.getRequestDispatcher("/views/AddContact.jsp").forward(request, response);
		}
		// Is thisFull name or username???????
//		u.setUserName(request.getParameter("name"));
//		FriendList(request, response);
	}

}
