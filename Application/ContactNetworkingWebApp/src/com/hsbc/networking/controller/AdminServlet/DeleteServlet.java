package com.hsbc.networking.controller.AdminServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.User;
import com.hsbc.networking.service.UserService;
import com.hsbc.networking.service.AdminService;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			AdminService adminService = new AdminService();
			HttpSession session = request.getSession();
			String id = request.getParameter("deleteId");
			adminService.deleteUser(id);
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
