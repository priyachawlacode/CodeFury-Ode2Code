package com.hsbc.networking.controller.UserServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.exception.UserException.UserDetailsNotFoundException;
import com.hsbc.networking.exception.UserException.UserIsDeactivatedException;
import com.hsbc.networking.exception.UserException.UserIsDisabledException;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.UserService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("Inside Search");
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		// System.out.println("Recieved type and value: "+type+" "+value);
		UserService userService = new UserService();
		User currentUser = new User();
		List<User> listUsers = new ArrayList<>();

		// creating new session object to get current user object's username
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("username");
		try {
			currentUser = userService.getCurrentUserDetail(name);
			// System.out.println(currentUser.toString());
		} catch (UserDetailsNotFoundException | UserIsDeactivatedException | UserIsDisabledException e1) {
			request.setAttribute("errorMessage", e1.getMessage());
			request.getRequestDispatcher("/views/UserInformation.jsp").forward(request, response);
		}

		try {
			listUsers = userService.getFilteredListUsers(type, value, currentUser.getUserId());
//			for(User u:listUsers ) {
//				System.out.println(u.toString());
//			}
		} catch (UserDetailsNotFoundException e) {
			// System.out.println("ERRORRRR"+e.getMessage());
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/views/UserInformation.jsp").forward(request, response);
		}

		request.setAttribute("FilteredSearchResult", listUsers);
		request.getRequestDispatcher("/views/SearchResult.jsp").forward(request, response);

	}

}
