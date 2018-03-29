package com.revature.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.EmployeeToken;
import com.revature.repository.EmployeeRepositoryJDBC;

public class EmployeeServiceAlpha implements EmployeeService {
	private static Logger logger = Logger.getLogger(EmployeeServiceAlpha.class);

	private static EmployeeServiceAlpha employeeServiceAlpha = new EmployeeServiceAlpha();

	private EmployeeServiceAlpha() { }

	public static EmployeeServiceAlpha getInstance() {
		return employeeServiceAlpha;

	}

	@Override
	public Employee authenticate(Employee employee) {
		logger.trace("showing username .................culprit\t"+employee.getUsername());
		Employee e1 = EmployeeRepositoryJDBC.getInstance().select(employee.getUsername());
		logger.trace("expecting toString() "+e1);
		logger.trace("showing pass\t"+EmployeeRepositoryJDBC.getInstance().getPasswordHash(employee));
		logger.trace("showing username \t"+e1.getUsername());

		if(e1 != null && e1.getPassword().equals(EmployeeRepositoryJDBC.getInstance().getPasswordHash(employee))) {
			return e1;
		}
		else

		return null;
	}

	@Override
	public Employee getEmployeeInformation(Employee employee) {
		if (employee.getId()>0){
			return EmployeeRepositoryJDBC.getInstance().select(employee.getId());
		}
		else{
			return EmployeeRepositoryJDBC.getInstance().select(employee.getUsername());
		}
	}

	@Override
	public Set<Employee> getAllEmployeesInformation() {

		return EmployeeRepositoryJDBC.getInstance().selectAll();

	}

	@Override
	public boolean createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployeeInformation(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUsernameTaken(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPasswordToken(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePasswordToken(EmployeeToken employeeToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTokenExpired(EmployeeToken employeeToken) {
		// TODO Auto-generated method stub
		return false;
	}

}
