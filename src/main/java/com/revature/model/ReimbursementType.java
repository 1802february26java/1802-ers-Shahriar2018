package com.revature.model;

import java.io.Serializable;

/**
 * It defines the type of the Reimbursement request.
 * 
 * @author Revature LLC
 */
public class ReimbursementType implements Serializable {
	
	/**
	 * Compatibility with Java 1.x
	 */
	private static final long serialVersionUID = -6682033542018508191L;

	/**
	 * PRIMARY KEY
	 */
	private int id;
	
	/**
	 * NOT NULL
	 */
	private String type;
	
	public ReimbursementType() {}

	public ReimbursementType(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReimbursementType [id=" + id + ", type=" + type + "]";
	}
}
