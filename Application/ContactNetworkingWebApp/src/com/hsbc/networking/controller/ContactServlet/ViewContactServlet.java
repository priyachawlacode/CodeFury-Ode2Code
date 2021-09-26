/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hsbc.networking.controller.ContactServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.User;
import com.hsbc.networking.service.UserService;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;
import com.hsbc.networking.model.Contact;
import com.hsbc.networking.service.ContactService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chawla
 */
public class ViewContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside View Servlet");
		User user = new User();
		List<Contact> contactList = new ArrayList<>();

		// creating user factory object to get user service instance
		UserService userService = new UserService();

		// create new contact service instance
		ContactService contactService = new ContactService();

		// creating new session object to get current user object's username
		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("username");
		try {
			user = userService.getCurrentUserDetail(name);
		} catch (UserDetailsNotFoundException | UserIsDeactivatedException | UserIsDisabledException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
		}

		// calling getAllContacts() to get all contact of current user
		contactList = contactService.getAllContacts(user.getUserId());
		for (Contact c : contactList) {
			System.out.println(c.toString());
		}
		// setting session attribute to access contactList at jsp
		session.setAttribute("contactList", contactList);

		request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
