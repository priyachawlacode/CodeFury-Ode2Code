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

/**
 * Servlet implementation class DeleteBatch
 */
@WebServlet("/DisableBatch")
public class DisableBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisableBatch() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside Do POst admin batch disable");
		String[] disableList = request.getParameter("disValue").split(",");
		List<String> dList = new ArrayList<>();
		for (String id : disableList) {
			dList.add(id);
			//System.out.println("Recieved Id : " + id);
		}
		AdminService ad = new AdminService();
		boolean flag = true;
		try {
			flag = ad.disbaleInBatch(dList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag) {
			request.getRequestDispatcher("/AdminServlet").forward(request, response);
		}
		else {
			request.setAttribute("error", "Cannot disable in batch");
			request.getRequestDispatcher("/AdminServlet").forward(request, response);
		}
	}

}
