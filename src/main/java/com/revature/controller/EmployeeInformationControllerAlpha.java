package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.service.EmployeeServiceAlpha;

public class EmployeeInformationControllerAlpha implements EmployeeInformationController {
	private static Logger logger = Logger.getLogger(EmployeeInformationControllerAlpha.class);


	@Override
	public Object registerEmployee(HttpServletRequest request) {
		
		return null;
	}

	@Override
	public Object updateEmployee(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object viewEmployeeInformation(HttpServletRequest request) {
	logger.trace("This is a view employee information method.");
	
    Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee");
	
	/* If customer is not logged in */
	if(loggedEmployee == null ) {
	   
		logger.trace("We do not have logged Employee information, return back to login page.");
		return "login.html";
		
	}
	
	logger.trace("Return back the most updated employee information.");
	if(loggedEmployee.getEmployeeRole().getId()==1){
	    if(request.getParameter("fetch") == null) {
		     return "employee-view-profile.html";
	    } else {
	        return EmployeeServiceAlpha.getInstance().getEmployeeInformation(loggedEmployee);
	         }
	  }
	
	else{
		if(request.getParameter("fetch") == null) {
		     return "manager-view-profile.html";
	    } else {
	         return EmployeeServiceAlpha.getInstance().getEmployeeInformation(loggedEmployee);
	         }
	}
	
	
}

@Override
public Object viewAllEmployees(HttpServletRequest request) {

    logger.trace("We are in viewAlEmployees method.");
	
    Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee");
	
	/* If customer is not logged in */
	if(loggedEmployee == null ) {
	   
		logger.trace("We do not have logged Employee information, return back to login page.");
		return "login.html";
		
	}
	//If he/she is a employee not a manager
	if(loggedEmployee.getEmployeeRole().getId()==1){
		
		logger.trace("This loggedEmployeee is an employee type not a manager type.");
		return "403.html";
		
	}

	/* Client is requesting the view. */
	if(request.getParameter("fetch") == null) {
		return "list-employees.html";
	} else {
	return EmployeeServiceAlpha.getInstance().getAllEmployeesInformation();
	}
}
	

	@Override
	public Object usernameExists(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
