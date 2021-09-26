/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hsbc.networking.controller.AdminServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.User;
import com.hsbc.networking.service.UserService;
import com.hsbc.networking.service.AdminService;

/**
 * Servlet implementation class UnBlockServlet
 */
@WebServlet("/DisableServlet")
public class DisableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			AdminService adminService = new AdminService();
			
			HttpSession session = request.getSession();
			
			String id = (String)session.getAttribute("disableId");

			adminService.disableUser(id);

			request.getRequestDispatcher("/AdminServlet").forward(request, response);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
