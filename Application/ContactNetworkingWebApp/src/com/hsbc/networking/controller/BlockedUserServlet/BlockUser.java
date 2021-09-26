package com.hsbc.networking.controller.BlockedUserServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.dao.BlockDao.BlockDaoImpl;
import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.BlockService;
import com.hsbc.networking.service.FriendsService;
import com.hsbc.networking.service.UserService;

/**
 * Servlet implementation class BlockUser
 */
@WebServlet("/BlockUser")
public class BlockUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BlockUser() {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		User currUser = new User();
		UserService userService = new UserService();

		// to get user id
		try {
			currUser = userService.getCurrentUserDetail(username);
		} catch (UserDetailsNotFoundException | UserIsDeactivatedException | UserIsDisabledException e1) {
			request.setAttribute("errorMessage", "Internal Server Error, try later!");
			request.getRequestDispatcher("/views/UserInformation.jsp").forward(request, response);
		}

		String userId = currUser.getUserId();

		// jsp se blockedUserId pass hogi
		System.out.println("Inside BlockUser : Block id recived :" + request.getParameter("blockId"));
		BlockService blockService = new BlockService();

		try {
			blockService.blockUser(userId,(String)request.getParameter("blockId"));
			request.getRequestDispatcher("/BlockedListServlet").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("errorMessage", "Internal Server Error, try later!");
			request.getRequestDispatcher("/views/MainPage.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
