package com.hsbc.networking.controller.Validation;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.AdminService;
import com.hsbc.networking.service.UserAuthService;
import com.hsbc.networking.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String username = request.getParameter("Username");
		String password = request.getParameter("password");
		if (username.trim().equals("") || password.trim().equals("")) {
			request.setAttribute("error", "Username and password are required");
			RequestDispatcher req = request.getRequestDispatcher("/views/LoginJSP.jsp");
			req.include(request, response);
		}
		UserAuthService obj = new UserAuthService();

		boolean valid = false;
		try {
			valid = obj.validateUserPassword(username, password);
		} catch (UserDetailsNotFoundException e) {
			e.printStackTrace();
		}
		if (valid == false) {
			request.setAttribute("error", "Incorrect Username or Password");
			request.getRequestDispatcher("/views/LoginJSP.jsp").forward(request, response);
		}

		else {
			// before transfering add useractivity
			AdminService as = new AdminService();
			UserService us = new UserService();
			User u = new User();
			boolean isBirthday = false;
			try {
				u = us.getCurrentUserDetail(username);
				isBirthday = us.checkUserBirthday(u.getDob());
			} catch (UserDetailsNotFoundException | UserIsDeactivatedException | UserIsDisabledException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/views/LoginJSP.jsp").forward(request, response);
			}
			boolean added = as.addUserActivity(u.getUserId());
			if (!added) {
				request.setAttribute("error", "User Activity cannot be recorded");
				request.getRequestDispatcher("/views/LoginJSP.jsp").forward(request, response);
			}
			

			HttpSession session = request.getSession();
			session.setAttribute("isBirthday",isBirthday);
			System.out.println("birtday value: "+isBirthday);
			session.setAttribute("username", username);
			session.setAttribute("currentUser", u);
			request.getRequestDispatcher("/views/MainPage.jsp").forward(request, response);
		}
	}

}
