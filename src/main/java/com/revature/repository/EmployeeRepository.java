package com.revature.repository;

import java.util.Set;

import com.revature.model.Employee;
import com.revature.model.EmployeeToken;

/**
 * The EmployeeRepository performs operations directly to the database utilizing
 * a data access library or framework, specifically on the EMPLOYEE table.
 * 
 * For the scope of this project, there is only going to be one JDBC implementation.
 * The idea of having an interface is that in the future you might want to implement
 * it with Hibernate, Mongo, or any other data access technology.
 * 
 * It should only contain data access logic regarding to employees.
 * 
 * @author Revature LLC
 */
public interface EmployeeRepository {
	
	/**
	 * Registers an employee in the database.
	 */
	public boolean insert(Employee employee);
	
	/**
	 * Updates information for a specific employee in the database.
	 */
	public boolean update(Employee employee);
	
	/**
	 * Returns information of a specific employee.
	 * 
	 * A join should be performed with respective user role.
	 */
	public Employee select(int employeeId);
	
	/**
	 * Returns information of a specific employee, based on his username.
	 * 
	 * A join should be performed with respective user role.
	 */
	public Employee select(String username);
	
	/**
	 * Returns information of all employees.
	 */
	public Set<Employee> selectAll();
	
	/**
	 * Executes and returns a password hash for an employee.
	 */
	public String getPasswordHash(Employee employee);
	
	/**
	 * Inserts a new password token.
	 */
	public boolean insertEmployeeToken(EmployeeToken employeeToken);
	
	/**
	 * Deletes a password token from the database.
	 */
	public boolean deleteEmployeeToken(EmployeeToken employeeToken);
	
	/**
	 * Returns a specific password token.
	 */
	public EmployeeToken selectEmployeeToken(EmployeeToken employeeToken);
}
