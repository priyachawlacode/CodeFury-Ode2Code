package com.hsbc.networking.controller.BlockedUserServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.User;
import com.hsbc.networking.service.BlockService;
import com.hsbc.networking.service.UserService;

/**
 * Servlet implementation class UnBlockServlet
 */
@WebServlet("/UnBlockServlet")
public class UnBlockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BlockService blockService = new BlockService();
			UserService userService = new UserService();
			HttpSession session = request.getSession();
			User u = userService.getCurrentUserDetail(session.getAttribute("username").toString());
			String id = u.getUserId();

			blockService.unBlockUser(id, request.getParameter("blockId"));

			request.getRequestDispatcher("/BlockedListServlet").forward(request, response);

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	

}
