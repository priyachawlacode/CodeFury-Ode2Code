package com.hsbc.networking.controller.UserServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.dao.UserDao.UserDaoImpl;

/**
 * Servlet implementation class DeactivateAccServlet
 */
@WebServlet("/DeactivateAccServlet")
public class DeactivateAccServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeactivateAccServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String Uname = (String) session.getAttribute("username");
		
		UserDaoImpl obj = new UserDaoImpl();
		obj.deactivateUser(Uname);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/LogoutServlet");
        dispatcher.forward(request, response);
        
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
