package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * The PasswordRecoveryController will execute operations on the EmployeeService
 * to verify that the user that wants to recover its password actually 
 * exists and return the proper views and messages.
 * 
 * It can also execute a password reset leveraging the UserService,
 * once the user checks his password recovery link on his email.
 * 
 * @author Revature LLC
 */
public interface PasswordRecoveryController {
	
	/**
	 * It can return the password recovery view if the token is still available.
	 * 
	 * It can return an Object containing a message stating whether his request for 
	 * password recovery was processed or not.
	 */
	public Object recoverPassword(HttpServletRequest request);
	
	/**
	 * Resets the password of the user
	 */
	public Object resetPassword(HttpServletRequest request);
}
