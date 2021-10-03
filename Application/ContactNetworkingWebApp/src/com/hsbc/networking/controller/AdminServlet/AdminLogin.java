package com.hsbc.networking.controller.AdminServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hsbc.networking.model.Admin;
import com.hsbc.networking.service.AdminService;

/**
 * Servlet implementation class AdminLogin
 */
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminUserName = request.getParameter("username");
		String pswd = request.getParameter("password");
		HttpSession session = request.getSession();

		AdminService adminService = new AdminService();
		if (adminService.adminLogin(adminUserName, pswd)) {
			session.setMaxInactiveInterval(30);
			// TODO:getCurrentAdminDetails
			Admin admin = adminService.getAdminInfo(adminUserName);
			if (admin == null) {
				System.out.println("INside null");
				session.invalidate();
				request.setAttribute("errorMessage", "Internal Server error");
				request.getRequestDispatcher("/views/AdminLogin.jsp").forward(request, response);
			}
			// System.out.println(admin.toString());
			session.setAttribute("username", admin.getUsername());
			session.setAttribute("name", admin.getName());
			session.setAttribute("emailId", admin.getEmail());
			session.setAttribute("url", admin.getUrl());
			session.setAttribute("mobileNo", admin.getPhoneNo());
			request.getRequestDispatcher("/AdminServlet").forward(request, response);
		} else {
			session.invalidate();
			request.setAttribute("errorMessage", "Invalid Username or Password");
			request.getRequestDispatcher("/views/AdminLogin.jsp").forward(request, response);

		}

	}

}
