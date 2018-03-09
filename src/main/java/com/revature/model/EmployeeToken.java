package com.revature.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Used for password recovery. Once a token is successfully used it should be deleted.
 * 
 * You can go ahead and try to provide a timer logic for them to expire.
 * 
 * @author Revature LLC
 */
public class EmployeeToken implements Serializable {

	/**
	 * Compatibility with Java 1.x
	 */
	private static final long serialVersionUID = -6347088033833089677L;

	/**
	 * PRIMARY KEY
	 */
	private int id;
	
	/**
	 * NOT NULL
	 * Recommended to use hashing functions combining the TIMESTAMP of it was created 
	 * with the username.
	 */
	private String token;
	
	/**
	 * NOT NULL
	 * It represents the TIMESTAMP of when it was created.
	 */
	private LocalDateTime creationDate;
	
	/**
	 * NOT NULL
	 * FOREIGN KEY coming from EMPLOYEE which represents the requester.
	 */
	private Employee requester;

	public EmployeeToken() {}
	
	public EmployeeToken(int id, String token, LocalDateTime creationDate, Employee requester) {
		this.id = id;
		this.token = token;
		this.creationDate = creationDate;
		this.requester = requester;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Employee getRequester() {
		return requester;
	}

	public void setRequester(Employee requester) {
		this.requester = requester;
	}

	@Override
	public String toString() {
		return "EmployeeToken [id=" + id + ", token=" + token + ", creationDate=" + creationDate + ", requester="
				+ requester + "]";
	}
}
