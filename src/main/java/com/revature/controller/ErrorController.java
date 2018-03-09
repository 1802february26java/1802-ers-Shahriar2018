package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

/** 
 * The ErrorController will precisely show a specific error page
 * based on a request. Example: 404, 403, "Oops" (which references 
 * an internal error -> 500).
 * 
 * @author Revature LLC
 */
public interface ErrorController {
	
	/**
	 * Returns the right error page depending on what error URI
	 * is requested.
	 */
	public String showError(HttpServletRequest request);
}
