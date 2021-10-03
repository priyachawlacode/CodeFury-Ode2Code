package com.hsbc.networking.controller.ContactServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.networking.model.Contact;
import com.hsbc.networking.model.User;
import com.hsbc.networking.service.ContactService;
import java.util.List;

/**
 * Servlet implementation class SortContact
 */
@WebServlet("/SortContact")
public class SortContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SortContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactService cs = new ContactService();
		String type = request.getParameter("type").equals("name")?"fullname":"emailId";
		String value = request.getParameter("cond");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("currentUser");
		// System.out.println("Inside SortServlet recieved type and cond "+ type+"
		// "+value);
		List<Contact> cList = cs.getAllContactsSorted(u.getUserId(), type, value.equals("t") ? true : false);
//		for(Contact c: cList) {
//			System.out.println("SORTED CONTACT: "+c.toString());
//		}
		
		// setting session attribute to access contactList at jsp
		session.setAttribute("contactList", cList);
		request.getRequestDispatcher("/views/ViewContact.jsp").include(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
