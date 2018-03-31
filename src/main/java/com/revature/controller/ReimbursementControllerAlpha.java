package com.revature.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementServiceAlpha;

public class ReimbursementControllerAlpha implements ReimbursementController {
	private static Logger logger = Logger.getLogger(ReimbursementControllerAlpha.class);

	private static ReimbursementController reimbursementController = new ReimbursementControllerAlpha();

	private ReimbursementControllerAlpha() {}

	public static ReimbursementController getInstance() {
		logger.trace("Inside  Request Controller now");

		return reimbursementController;

	}



	@Override
	public Object submitRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object singleRequest(HttpServletRequest request) {
		logger.trace("Inside single Request now");

		Employee loggedEmployee = (Employee) request.getSession().getAttribute("employee");

		if(loggedEmployee == null ) {

			logger.trace("We do not have logged Employee information, return back to login page.");
			return "login.html";

		}

		Reimbursement reimbursement = new Reimbursement(loggedEmployee, Integer.parseInt(request.getParameter("reimbursementId")), null, null);
		logger.trace("We are getting a single reimbursement back");
		return ReimbursementServiceAlpha.getInstance().getSingleRequest(reimbursement);
	}

	@Override
	public Object multipleRequests(HttpServletRequest request) {
		logger.trace("This is a multi requests method");

		Employee employee = (Employee) request.getSession().getAttribute("employee");


		if(employee == null ) {
			logger.trace("returning to login page");
			return "login.html";

		}

		if(employee.getEmployeeRole().getId()==1){
			logger.trace("Entering employee.......");
			 if(request.getParameter("fetch") == null) {
			     return "multipleRequests.html"; 
			}
		
		}
		return employee;

			
		}

	

	

	

	@Override
	public Object finalizeRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getRequestTypes(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
