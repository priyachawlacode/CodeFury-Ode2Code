package com.hsbc.networking.controller.ContactServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;
import com.hsbc.networking.model.Contact;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.ContactService;
import com.hsbc.networking.service.UserService;

@WebServlet("/CheckMailServlet")
public class CheckMailServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContactService contactService = new ContactService();
		UserService userService = new UserService();
		User user = new User();
		Contact contact = new Contact();
		HttpSession session = request.getSession();
		//TO-DO attribute name from jsp
		String email = (String) request.getParameter("contactEmail");
		System.out.println("Mail Id to be checked: "+email);

			User u = (User) session.getAttribute("currentUser");

		
		user=userService.checkUserMailIdExists(email);
		if(user != null) {
			System.out.println("Inside CheckMailServlet: UserMailExists function");
			//TO DO- Update jsp name
			session.setAttribute("NewUser", user);
			request.setAttribute("verified", false);
			request.getRequestDispatcher("/views/Redirect.jsp").forward(request, response);
			return;    
		}
		
				User newU=new User();
				newU.setEmail(email);
				session.setAttribute("NewUser", newU);
				try {
					contact = contactService.checkContactMailIdExists(u.getUserId(),email);
				} catch (UserDetailsNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				if(contact!=null) {
							System.out.println("Inside CheckMailServlet: ContactMailExists function");
							session.setAttribute("contactToEdit", contact);
							request.setAttribute("verified", true);
							request.getRequestDispatcher("/views/Redirect.jsp").forward(request, response);
							return;
						}
						else {
							System.out.println("Inside CheckMailServlet: Add Contact function");
							request.getRequestDispatcher("/views/AddContact.jsp").forward(request, response);
							return;
						}
	}
	
}
