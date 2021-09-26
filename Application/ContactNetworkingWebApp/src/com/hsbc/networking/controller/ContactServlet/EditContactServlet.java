/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hsbc.networking.controller.ContactServlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.hsbc.networking.model.User;
import com.hsbc.networking.service.UserService;
import com.hsbc.networking.exception.ContactException.ContactDetailsNotValidException;
import com.hsbc.networking.exception.ContactException.ContactNotFoundException;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;
import com.hsbc.networking.model.Contact;
import com.hsbc.networking.service.ContactService;

/**
 *
 * @author chawla
 */
@WebServlet("/EditContactServlet")
public class EditContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private static UserService userService = new UserService();

	// create new contact service instance
	private static ContactService contactService = new ContactService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("INside DoPOst EdtCOntact servlet");
		User user = new User();
		Contact contact = new Contact();

		// creating new session object to get current user object's username
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("username");
		try {
			user = userService.getCurrentUserDetail(name);
		} catch (UserDetailsNotFoundException | UserIsDeactivatedException | UserIsDisabledException e1) {
			request.setAttribute("errorMessage", "Internal Server Error, try later!");
			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
		}

		// adding data to contact object
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

		// profile image remaining

		// calling update contact method
		boolean edit = false;
		try {
			edit = contactService.updateContact(contact, user.getUserId());
		} catch (ContactDetailsNotValidException | SQLException e) {
			request.setAttribute("errorMessage", "Internal Server Error, try later!");
			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
		}

		if (edit) {
			request.getRequestDispatcher("/ViewContactServlet").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Error in Updating contact, try later!");
			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("INside DoGet EdtCOntact servlet , recieved contact id: "+request.getParameter("contactId"));
		// creating new session object to get current user object's username
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("username");
		User user = new User();
		try {
			user = userService.getCurrentUserDetail(name);
		} catch (UserDetailsNotFoundException | UserIsDeactivatedException | UserIsDisabledException e1) {
					request.setAttribute("errorMessage", "Internal Server Error, try later!");
					request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
				}
		
		Contact contact = new Contact();
		try {
			contact = contactService.getContactDetails(request.getParameter("contactId"), user.getUserId());
		} catch (ContactNotFoundException e) {
			request.setAttribute("errorMessage", "Internal Server Error, try later!");
			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
		}
		System.out.println("Get contact success: "+ contact.toString()+"\n setting attribute");
		session.setAttribute("contactToEdit", contact);
		request.getRequestDispatcher("/views/EditDetails.jsp").include(request, response);
	}

}
