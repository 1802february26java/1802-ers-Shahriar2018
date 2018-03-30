package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.model.Employee;

public class HomeControllerAlpha implements HomeController {

    private static Logger logger = Logger.getLogger(HomeControllerAlpha.class);
	
    private static HomeControllerAlpha homeController = new HomeControllerAlpha();
	
	private HomeControllerAlpha() {}
	
	public static HomeControllerAlpha getInstance() {
		return homeController;
	}
	@Override
	public String showEmployeeHome(HttpServletRequest request) {
		
      
		
        Employee loggedEmployee = (Employee) request.getSession().getAttribute("username");
		
		/* If customer is not logged in */
		if(loggedEmployee == null ) {
		   
			logger.trace("We do not have logged Employee information, return back to login page.");
			return "login.html";
			
		}
		
		//return back different home page based on the role
		if(loggedEmployee.getEmployeeRole().getId() == 1){
			  logger.trace("Entering home from  loggedEmployee.getEmployeeRole().getId() == 1");
			return "EmployeePage.html";
		}
		
		else{
			  logger.trace("Entering home from  loggedEmployee.getEmployeeRole().getId() == 2");
			return "HomeManager.html";
		}
	}

}
