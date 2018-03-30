package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.ajax.ClientMessage;
import com.revature.model.Employee;
import com.revature.service.EmployeeServiceAlpha;
import com.revature.util.FinalUtil;



public class LoginControllerAlpha implements LoginController {
    
	private static Logger logger = Logger.getLogger(LoginControllerAlpha.class);
	
    private static LoginController loginController = new LoginControllerAlpha();
	
	private LoginControllerAlpha() {}
	
	public static LoginController getInstance() {
		return loginController;
	}
	
	@Override
	public Object login(HttpServletRequest request) {
		//If user only want a view returned back
	if(request.getMethod().equals("GET")) {
			logger.trace("Thie action is GET, we return back a login view");
			return "login.html";
		}
		//Below is for POST
logger.info("about to authenticate \t"+new Employee(request.getParameter(FinalUtil.EMPLOYEE_USERNAME),request.getParameter(FinalUtil.EMPLOYEE_PASSWORD)));
		Employee loggedEmployee = EmployeeServiceAlpha.getInstance().authenticate(
				
					new Employee(request.getParameter(FinalUtil.EMPLOYEE_USERNAME),
								 request.getParameter(FinalUtil.EMPLOYEE_PASSWORD))
				);
		
		
		logger.trace("The loggedEmployee is: "+loggedEmployee);
		
		/* If authentication failed */
		if(loggedEmployee == null) {
			return new ClientMessage(FinalUtil.LOGIN_FAIL);
		}
		
		/* Store the user information on the session */
		request.getSession().setAttribute("employee", loggedEmployee);
		logger.trace("printing logged Employee /t:"+loggedEmployee);
		
		return loggedEmployee;
	}

	
	@Override
	public String logout(HttpServletRequest request) {
		
		request.getSession().invalidate();
		return "login.html";
	}

}
