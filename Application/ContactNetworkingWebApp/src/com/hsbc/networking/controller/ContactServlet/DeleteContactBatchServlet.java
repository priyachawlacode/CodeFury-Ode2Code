package com.hsbc.networking.controller.ContactServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.exception.ContactException.ContactNotFoundException;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.ContactService;
import com.mysql.cj.Session;

@WebServlet("/deleteContactBatchServlet")
public class DeleteContactBatchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ContactService contactService = new ContactService();
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("currentUser");
		
		System.out.println("Inside DoPost of delete batch");
		List<String> delBatch = new ArrayList<String>();
		String actions[] = req.getParameter("delValue").split(",");

		for (int i = 0; i < actions.length; i++) {
			delBatch.add(actions[i]);
		}
		
		boolean check = false;
		try {
			check=contactService.deleteContactsInBatch(delBatch, u.getUserId());
		} catch (ContactNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (check) {
			req.getRequestDispatcher("/ViewContactServlet").forward(req, resp);
		} else {
			req.setAttribute("errorMessage", "Error in Deleting contacts, try later!");
			req.getRequestDispatcher("/ViewContactServlet").forward(req, resp);
		}
	}
}
