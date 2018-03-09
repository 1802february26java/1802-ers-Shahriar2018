package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

public class LoginControllerAlpha implements LoginController {

	@Override
	public String login(HttpServletRequest request) {
		return "login.html";
	}

	@Override
	public String logout(HttpServletRequest request) {
		return "login.html";
	}

}
