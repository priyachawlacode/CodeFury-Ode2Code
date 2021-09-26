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
	private static int inactivePeriodInDays = 2;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService=new AdminService();
		int size = adminService.returnUserCount();
		HttpSession session = request.getSession(); 
		System.out.println("Inside do post admin servlet");
		session.setAttribute("TotalUsers", size);
		//System.out.println("Size: "+size);
		List<User> disable=adminService.getDisableList();
		System.out.println(disable);
		int disableUsers=disable.size();
		session.setAttribute("disableUsers",disableUsers );
//		for(User user: disable) { 
//			//System.out.println("Disable user: "+user.toString());
//			//System.out.println("UserId"+ user.getUserId());
//			session.setAttribute("UserId", user.getUserId());
//			session.setAttribute("UserName", user.getUserName());
//			session.setAttribute("country", user.getCountry());
//		}
		List<User> delete=adminService.getDeleteList(inactivePeriodInDays);
		int deleteUsers=delete.size();
		session.setAttribute("deleteUsers",deleteUsers );
		System.out.println(delete);
//		for(User user : delete) { 
//			//System.out.println("delete user: "+user.toString());
//			session.setAttribute("Id", user.getUserId());
//			session.setAttribute("name", user.getUserName());
//			session.setAttribute("location", user.getCity());
//		}
//		
		request.getRequestDispatcher("/views/Admin.jsp").forward(request, response);
	}
}
