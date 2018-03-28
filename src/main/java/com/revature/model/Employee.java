package com.revature.model;

import java.io.Serializable;

/**
 * Main entity of the application.
 * Feel free to use your own naming conventions to map this object to the database.
 * 
 * @author Revature LLC
 */
public class Employee implements Serializable, Comparable<Employee> {

	/**
	 * Compatibility with Java 1.x
	 */
	private static final long serialVersionUID = -6154669016957069543L;

	/**
	 * PRIMARY KEY
	 */
	private int id;

	/**
	 * NOT NULL
	 */
	private String firstName;
	
	/**
	 * NOT NULL
	 */
	private String lastName;

	/**
	 * NOT NULL and UNIQUE
	 */
	private String username;
	
	/**
	 * NOT NULL and HASHED
	 */
	private String password;
	
	/**
	 * NOT NULL
	 */
	private String email;
	
	/**
	 * NOT NULL
	 * FOREIGN KEY coming from EMPLOYEE_ROLE
	 */
	private EmployeeRole employeeRole;	

	public Employee() {}

	public Employee(int id, String firstName, String lastName, String username, String password, String email,
			EmployeeRole employeeRole) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.employeeRole = employeeRole;
	}
	public Employee( String firstName, String lastName, String username, String password, String email
			) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	
	}
	public Employee( String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmployeeRole getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(EmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeRole == null) ? 0 : employeeRole.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeRole == null) {
			if (other.employeeRole != null)
				return false;
		} else if (!employeeRole.equals(other.employeeRole))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", employeeRole=" + employeeRole + "]";
	}

	/**
	 * Natural order defined by the id.
	 */
	@Override
	public int compareTo(Employee employee) {
		return new Integer(this.id).compareTo(employee.id);
	}
}
