package com.hsbc.networking.controller.AdminServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.networking.service.AdminService;
import com.hsbc.networking.service.UserAuthService;

/**
 * Servlet implementation class DeleteBatch
 */
@WebServlet("/DeleteBatch")
public class DeleteBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteBatch() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside Do POst admin batch delete");
		String[] deleteList = request.getParameter("delValue").split(",");
		List<String> dList = new ArrayList<>();
		for (String id : deleteList) {
			dList.add(id);
		}
		UserAuthService us = new UserAuthService();
		boolean flag = true;
		try {
			flag = us.deleteInBatch(dList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (flag) {
			request.getRequestDispatcher("/AdminServlet").forward(request, response);
		} else {
			request.setAttribute("error", "Cannot delete in batch");
			request.getRequestDispatcher("/AdminServlet").forward(request, response);
		}
	}
}
