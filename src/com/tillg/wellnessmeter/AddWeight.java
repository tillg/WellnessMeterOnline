package com.tillg.wellnessmeter;

import javax.servlet.*;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AddWeight extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String USERWEIGHTS = "userWeights"; 

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		HttpSession session = request.getSession(true);
		String jspPage = "/weights.jsp";
		String dateStr = request.getParameter("date");
		String weightStr = request.getParameter("weightvalue");
		
		// No user logged in --> Goto log in
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    if (user == null) {
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(userService.createLoginURL(request.getRequestURI()));
	    	dispatcher.forward(request, response);
	    }
		
		// Get or create the UserWeights of that session
		UserWeights userWeights = (UserWeights) session.getAttribute(USERWEIGHTS);
		if (userWeights == null) {
			userWeights = new UserWeights();
			userWeights.setUserId(user.getUserId());
		}
		
		try {
			userWeights.addWeight(dateStr, weightStr);
		} catch (Exception e) {
			session.setAttribute("error", e.getMessage());
		}
		session.setAttribute(USERWEIGHTS, userWeights);
		
//		RequestDispatcher dispatcher = getServletContext()
//				.getRequestDispatcher(jspPage);
//		dispatcher.forward(request, response);
		response.sendRedirect(jspPage);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		processRequest(request, response);
	}

}
