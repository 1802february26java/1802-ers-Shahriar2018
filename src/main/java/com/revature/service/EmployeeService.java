package com.revature.service;

import java.util.Set;

import com.revature.model.Employee;
import com.revature.model.EmployeeToken;

/**
 * The EmployeeService performs operations on the EmployeeRepository
 * to provide persistency of employee's information, to retrieve information
 * or to perform employee's only operations.
 * 
 * It should also contain business logic specifically regarding to employees.
 * 
 * @author Revature LLC
 */
public interface EmployeeService {
	
	/**
	 * Contains the logic of user authentication.
	 * 
	 * It returns the employee information if authentication was successful.
	 */
	public Employee authenticate(Employee employee);
	
	/**
	 * Returns information of a specific employee.
	 * 
	 * The parameter should at least contain the employee id.
	 */
	public Employee getEmployeeInformation(Employee employee);
	
	/**
	 * Returns information of all employees.
	 */
	public Set<Employee> getAllEmployeesInformation();
	
	/**
	 * Registers an employee in the database.
	 * 
	 * Input validation can be provided here.
	 * 
	 * An email should be sent to the employee telling him/her that his
	 * registration was successful and be provided with a temporary password.
	 */
	public boolean createEmployee(Employee employee);
	
	/**
	 * Updates information for a specific employee in the database.
	 * 
	 * Input validation can be provided here.
	 */
	public boolean updateEmployeeInformation(Employee employee);
	
	/**
	 * Updates the password of a specific employee in the database.
	 * 
	 * Password hash needs to be performed again.
	 * 
	 * The parameter should contain at least the employee id and the desired password.
	 */
	public boolean updatePassword(Employee employee);
	
	/**
	 * Return whether a username is available or not.
	 */
	public boolean isUsernameTaken(Employee employee);
	
	/**
	 * Creates a new password token in the database.
	 * 
	 * The parameter should contain at least the employee id who's creating it.
	 * 
	 * An email should be sent to the employee providing a link to the password recovery
	 * view with the specific token.
	 */
	public boolean createPasswordToken(Employee employee);
	
	/**
	 * Deletes a password token from the database.
	 */
	public boolean deletePasswordToken(EmployeeToken employeeToken);
	
	/**
	 * Returns whether a password token has expired or not.
	 */
	public boolean isTokenExpired(EmployeeToken employeeToken);
}
