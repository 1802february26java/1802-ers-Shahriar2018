package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

/** 
 * The EmployeeInformationController will execute operations on
 * the EmployeeService to perform CRUD operations for Employees.
 * 
 * Employees can be registered by a manager, they can update their
 * information, and/or a manager might request a list of all employees.
 * 
 * @author Revature LLC
 */
public interface EmployeeInformationController {
	
	/**
	 * Registers an employee.
	 * 
	 * This operation can only be performed by managers.
	 * 
	 * It should return a message stating whether the user was successfully
	 * registered or not.
	 */
	public Object registerEmployee(HttpServletRequest request);
	
	/**
	 * Updates employee profile information.
	 * 
	 * It should return a message stating whether the user was successfully
	 * updated or not.
	 */
	public Object updateEmployee(HttpServletRequest request);
	
	/**
	 * Returns information regarding a specific employee.
	 * 
	 * This operation can be performed by regular or manager employees.
	 */
	public Object viewEmployeeInformation(HttpServletRequest request);
	
	/**
	 * Returns a collection of employees.
	 * 
	 * This operation can only be performed by managers.
	 */
	public Object viewAllEmployees(HttpServletRequest request);
	
	/**
	 * Returns a message stating if the username is available or not.
	 */
	public Object usernameExists(HttpServletRequest request);
}
