package com.hsbc.networking.controller.AdminServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.AdminService;


public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int inactivePeriodInDays = -1;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService=new AdminService();
		int size = adminService.returnUserCount();
		HttpSession session = request.getSession(); 
		System.out.println("Inside do post admin servlet");
		session.setAttribute("TotalUsers", size);
		
		List<User> disable=adminService.getDisableList();
		
		session.setAttribute("disableUsers",disable );
		for(User user: disable) { 
			System.out.println("Disable user: "+user.toString());}
	
		List<User> delete=adminService.getDeleteList(inactivePeriodInDays);
		
		session.setAttribute("deleteUsers",delete );
		
		for(User user : delete) { 
			System.out.println("delete user: "+user.toString());}
		request.getRequestDispatcher("/views/Admin.jsp").forward(request, response);
	}
}
