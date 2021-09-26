package com.hsbc.networking.controller.BlockedUserServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.User;
import com.hsbc.networking.service.BlockService;
import com.hsbc.networking.service.UserService;

import java.util.List;

@WebServlet("/BlockedListServlet")
public class BlockedListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			UserService userService = new UserService();
			HttpSession session = request.getSession();
			User u = userService.getCurrentUserDetail(session.getAttribute("username").toString());
			String id = u.getUserId();

			BlockService blockedServ = new BlockService();
			List<User> list = blockedServ.getBlockList(id);

			session.setAttribute("blockedList", list);
//			for (User block : list) {
//				System.out.println(block.toString());
//			}
			request.getRequestDispatcher("/views/BlockedList.jsp").forward(request, response);

		} catch (Exception e) {
			out.println("error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
