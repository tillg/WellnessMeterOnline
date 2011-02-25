package com.tillg.wellnessmeter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.tillg.util.TillGUser;

public class AddMeasurement extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String USERMEASUREMENTS = "USERMEASUREMENTS"; 

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		/**
		 * THE servlet that adds a measurement to a users history. 
		 * Pre-req: To be logged in. Will throw to login page otherwise.
		 * Post: Once measurement has been added, request is redirected to mywellness.jsp.
		 */
		HttpSession session = request.getSession(true);
		String jspPage = "/mywellness.jsp";
		
		// No user logged in --> Goto log in
		TillGUser user = new TillGUser();
	    if (!user.isLoggedIn()) {
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("login.jsp");
	    	dispatcher.forward(request, response);
	    }
		
		// Get or create the UserMeasurements of that session
		UserMeasurements userMeasurements = (UserMeasurements) session.getAttribute(USERMEASUREMENTS);
		if (userMeasurements == null) {
			userMeasurements = new UserMeasurements();
			userMeasurements.setUserId(user.getUserId());
		}
		
		// Read vars from Form
		Map<String,String[]> vars = request.getParameterMap();
		String dateStr = request.getParameter("date");
		HashMap<String, String> valuesStr = new HashMap<String, String> ();
		Iterator<String> it = vars.keySet().iterator();
		while (it.hasNext()) {
			String type = it.next();
			if (MeasurementType.isMeasurementTypeId(type)) {
				valuesStr.put(type, vars.get(type)[0]);
			}
		}
				
		// Try to transform the entered data from String to Dates & Floats
		try {
			userMeasurements.addMeasurement(dateStr, valuesStr);
		} catch (Exception e) {
			session.setAttribute("error", e.getMessage());
		}
		session.setAttribute(USERMEASUREMENTS, userMeasurements);

		// Send it back to the displaying page
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
