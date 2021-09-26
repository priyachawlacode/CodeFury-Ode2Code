package com.hsbc.networking.controller.AdminServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOutServlet
 */

public class AdminLogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");  
         PrintWriter out=response.getWriter();
         HttpSession session=request.getSession();
         session.setAttribute("username",null);
         session.invalidate();  
         out.print("You are successfully logged out!");  
         out.close(); 
         request.getRequestDispatcher("/views/AdminLogin.jsp").include(request, response);  
          
	}

}
