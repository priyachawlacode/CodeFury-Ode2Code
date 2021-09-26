package com.hsbc.networking.controller.FriendListServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.exception.FriendsException.FriendBlockedException;
import com.hsbc.networking.exception.FriendsException.RequestAlreadyPendingException;
import com.hsbc.networking.model.FriendRequest;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.FriendsService;

/**
 * Servlet implementation class SendRequestServlet
 */
@WebServlet("/SendRequestServlet")
public class SendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SendRequestServlet() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FriendsService fs = new FriendsService();
		FriendRequest fr = new FriendRequest();
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("currentUser");
		fr.setFriend_id((String)session.getAttribute("frndId"));
		fr.setMessage((String)session.getAttribute("sendMessage"));
		fr.setUser_id(u.getUserId());
		try {
			fs.sendFriendRequest(fr);
			request.getRequestDispatcher("/FriendListServlet").forward(request, response);
		} catch (RequestAlreadyPendingException | FriendBlockedException e) {
			e.printStackTrace();
			request.setAttribute("error", "Not able to send friend request");
			request.getRequestDispatcher("/views/MainPage.jsp").forward(request, response);
		}
	}

}
