package com.hsbc.networking.controller.FriendListServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.User;
import com.hsbc.networking.service.FriendsService;

/**
 * Servlet implementation class UnfriendServlet
 */
@WebServlet("/UnfriendServlet")
public class UnfriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UnfriendServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//System.out.println("Inside doPost UnfrinedServlet");
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("currentUser");
			String id = u.getUserId();

			FriendsService friendService = new FriendsService();
			friendService.removeFriend(id, request.getParameter("removeId"));
			request.getRequestDispatcher("/FriendListServlet").forward(request, response);

		} catch (Exception e) {
			System.out.print(e.getMessage());
			request.getRequestDispatcher("/views/FriendList.jsp").forward(request, response);
		}
	}

}
