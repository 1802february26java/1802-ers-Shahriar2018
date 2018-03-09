package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

public class ErrorControllerAlpha implements ErrorController {

	@Override
	public String showError(HttpServletRequest request) {
		return "oops.html";
	}

}
