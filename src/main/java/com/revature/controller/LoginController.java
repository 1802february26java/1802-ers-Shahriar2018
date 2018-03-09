package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * The LoginController consults the EmployeeService, which provides authentication
 * and authorization logic. It will return the HomeController URI if authentication
 * is successful.
 * 
 * It can also invalidate a session when the user decides to log out.
 * 
 * @author Revature LLC
 */
public interface LoginController {
	
	/**
	 * Checks whether a user was able to log in or not.
	 * If the user successfully logs in, the information of the user
	 * will be stored within the session.
	 * 
	 * It can return a URI directing the user the HomeController
	 * if he/she was authenticated.
	 * 
	 * It can return an Object containing a message stating that the
	 * the authentication failed.
	 * 
	 * It can return the employee information if the authentication is
	 * successful.
	 */
	public Object login(HttpServletRequest request);
	
	/**
	 * Invalidates the session of the user once he logs out.
	 * 
	 * It returns the login view once completed.
	 */
	public String logout(HttpServletRequest request);
}
