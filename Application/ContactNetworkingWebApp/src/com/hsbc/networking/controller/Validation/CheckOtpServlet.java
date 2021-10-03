package com.hsbc.networking.controller.Validation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckOtpServlet
 */
@WebServlet("/CheckOtpServlet")
public class CheckOtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CheckOtpServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userOtp = request.getParameter("otp");
		HttpSession session = request.getSession();
		String otp = (String)session.getAttribute("otp");
		
		if(userOtp.trim().equals(otp)) {
			RequestDispatcher req = request.getRequestDispatcher("/views/LoginJSP.jsp");
			req.forward(request, response);
		}
		request.setAttribute("error","Incorrect otp");
		RequestDispatcher req = request.getRequestDispatcher("/views/VerifyOtp.jsp");
		req.forward(request, response);
	}

}
