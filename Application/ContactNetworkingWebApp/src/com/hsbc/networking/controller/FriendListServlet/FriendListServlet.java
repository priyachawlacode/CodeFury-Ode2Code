/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hsbc.networking.controller.FriendListServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.User;
import com.hsbc.networking.service.FriendsService;
import com.hsbc.networking.service.UserService;

/**
 *
 * @author chawla
 */
@WebServlet("/FriendListServlet")
public class FriendListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.print("Inside DOGET: FriendListServlet");
		PrintWriter out = response.getWriter();
		try {
			UserService userService = new UserService();
			HttpSession session = request.getSession();
			User u = userService.getCurrentUserDetail(session.getAttribute("username").toString());
			String id = u.getUserId();

			FriendsService friendService = new FriendsService();
			List<User> list = friendService.getFriendList(id);
//			for(User user: list) {
//				System.out.println("INside servlet rec list of friends:"+user.toString());
//			}
			session.setAttribute("friendList", list);
			request.getRequestDispatcher("/views/FriendList.jsp").forward(request, response);

		} catch (Exception e) {
			out.println("error");
			request.getRequestDispatcher("/views/FriendList.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
