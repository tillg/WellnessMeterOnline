package com.tillg.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class TillGUser implements Serializable {
	
	/** A User Class that wraps different (session-) users:
	 * - Google user
	 * - Facebook-User
	 */
	
	 
	private static final long serialVersionUID = 1L;

	private User getGoogleUser () {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		return user;
	}
	
	private boolean isGoogleUserLoggedIn () {
		return (getGoogleUser() != null);
	}
	
	private String getGoogleLogoutURL(String nextPage) {
		UserService userService = UserServiceFactory.getUserService();
		return userService.createLogoutURL(nextPage);
	}

	public String getGoogleLoginURL(HttpServletRequest request) {
		String url = getGoogleLoginURL(request.getRequestURI());
		return url;
	}
	
	public String getGoogleLoginURL(String nextPage) {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(nextPage);
		return url;
	}

	public boolean isLoggedIn () {
		return isGoogleUserLoggedIn();
	}
	
	public String getLogoutURL(String nextPage) {
		if (isGoogleUserLoggedIn())
			return getGoogleLogoutURL(nextPage);
		else
			return null;
	}
	
	public String getUserId () {
		if (isGoogleUserLoggedIn()) 
			return getGoogleUser().getUserId();
		else
			return null;
	}
	
	public String getNickname () {
		if (isGoogleUserLoggedIn()) 
			return getGoogleUser().getNickname() + " (Google)";
		else
			return null;
	}
}


