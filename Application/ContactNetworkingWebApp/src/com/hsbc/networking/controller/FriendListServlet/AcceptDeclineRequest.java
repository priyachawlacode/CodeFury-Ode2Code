package com.hsbc.networking.controller.FriendListServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.FriendRequest;
import com.hsbc.networking.service.FriendsService;

/**
 * Servlet implementation class AcceptDeclineRequest
 */
@WebServlet("/AcceptDeclineRequest")
public class AcceptDeclineRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public AcceptDeclineRequest() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.print("\nInside Accept Decline!");
		FriendsService fs = new FriendsService();
		String frId =(String) request.getParameter("friendId");
		String usId = (String)request.getParameter("userId");
		String msg = (String)request.getParameter("message");
		//FriendRequest fr = (FriendRequest)request.getParameter("frReq");
		System.out.print("\nValues Recieved: frd: "+frId+" user: "+usId+" message: "+msg);
//		HttpSession session = request.getSession();
		FriendRequest friendRequestObj = new FriendRequest(frId,usId,msg);
		
		//System.out.println("Recieved Friend Request using session:"+friendRequestObj.toString());
        if (request.getParameter("accept")!=null) { 
        	try {
        		fs.acceptFriendRequest(friendRequestObj);
        		RequestDispatcher rd = request.getRequestDispatcher("/FriendRequestServlet");
        		rd.forward(request, response);
        	}catch (SQLException e) {
        		e.printStackTrace();
        		RequestDispatcher rd = request.getRequestDispatcher("/views/MainPage.jsp");
        		rd.forward(request, response);
        	}
        } else if (request.getParameter("decline") != null) {
            fs.declineFriendRequest(friendRequestObj);
            RequestDispatcher rd = request.getRequestDispatcher("/FriendRequestServlet");
    		rd.forward(request, response);
        }

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
}