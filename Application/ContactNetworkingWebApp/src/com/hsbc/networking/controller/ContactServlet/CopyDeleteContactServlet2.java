//package com.hsbc.networking.controller.ContactServlet;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.hsbc.networking.exception.ContactException.ContactNotFoundException;
//import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
//import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
//import com.hsbc.networking.exception.UserException.UserIsDisabledException;
//import com.hsbc.networking.model.User;
//import com.hsbc.networking.service.UserService;
//import com.hsbc.networking.service.ContactService;
//
///**
// * Servlet implementation class DeleteContactServlet
// */
//@WebServlet("/DeleteContactServlet")
//public class CopyDeleteContactServlet2 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * Default constructor.
//	 */
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("Inside doPost of DDelete Contact Servlet");
//
//		User user = new User();
//
//		UserService userService = new UserService();
//
//		// create new contact service instance
//		ContactService contactService = new ContactService();
//
//		// creating new session object to get current user object's username
//		HttpSession session = request.getSession();
//		String name = (String) session.getAttribute("username");
//
//		try {
//			user = userService.getCurrentUserDetail(name);
//		} catch (UserDetailsNotFoundException | UserIsDeactivatedException | UserIsDisabledException e) {
//			request.setAttribute("errorMessage", "Internal Server Error, try later!");
//			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
//		}
//
//		String contactId = request.getParameter("contactId");
//
//		boolean delete = true;
//		try {
//			delete = contactService.deleteContact(contactId, user.getUserId());
////			System.out.println(contactService.deleteContact(contactId, user.getUserId()));
//			
//		} catch (ContactNotFoundException e) {
//			request.setAttribute("errorMessage", "Could not Delete");
//			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
//		}
//		System.out.println(delete);
//		if (delete==true) {
//			request.getRequestDispatcher("/ViewContactServlet").forward(request, response);
//		} else {
////			request.setAttribute("errorMessage", "Could not Delete");
////			request.getRequestDispatcher("/views/ViewContact.jsp").forward(request, response);
//			request.getRequestDispatcher("/ViewContactServlet").forward(request, response);
//
//		}
//	}
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("Inside doGet of DDelete Contact Servlet");
//		doPost(req, resp);
//	}
//}