package com.revature.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The main entity from the business perspective. This is an Expense Reimbursement Application,
 * so the majority of the business logic is going to float around this entity.
 * 
 * @author Revature LLC
 */
public class Reimbursement implements Serializable, Comparable<Reimbursement> {
	
	/**
	 * Compatibility with Java 1.x
	 */
	private static final long serialVersionUID = 7817528226602188982L;

	/**
	 * PRIMARY KEY
	 */
	private int id;
	
	/**
	 * NOT NULL
	 */
	private LocalDateTime requested;
	
	/**
	 * No constraints, this should be NULL if it hasn't been approved yet.
	 */
	private LocalDateTime resolved;
	
	/**
	 * NOT NULL
	 */
	private double amount;
	
	/**
	 * No constraints.
	 */
	private String description;
	
	/**
	 * No constraints.
	 * This will be represented as a BLOB in the database.
	 * Feel free to change the data type (on the Java side) at your own will.
	 * In the end this will be transformed into a byte stream.
	 */
	private Object receipt;
	
	/**
	 * NOT NULL
	 * FOREIGN KEY coming from EMPLOYEE, which represents the owner of the reimbursement (requester).
	 */
	private Employee requester;
	private Employee firstname;
	private Employee lastname;
	
	/**
	 * This should be NULL when no one has approved the request yet.
	 * FOREIGN KEY coming from EMPLOYEE, which represents the manager which approves the reimbursement (approver).
	 */
	private Employee approver;
	
	/**
	 * NOT NULL
	 * FOREIGN KEY coming from REIMBURSEMENT_STATUS
	 */
	private ReimbursementStatus status;
	
	/**
	 * NOT NULL
	 * FOREIGN KEY coming from REIMBURSEMENT_TYPE
	 */
	private ReimbursementType type;

	public Reimbursement() {}
	
	/**
	 * It doesn't contain the receipt as a parameter.
	 */
	public Reimbursement(int id, LocalDateTime requested, LocalDateTime resolved, double amount, String description,
			Employee requester, Employee approver, ReimbursementStatus status, ReimbursementType type) {
		this.id = id;
		this.requested = requested;
		this.resolved = resolved;
		this.amount = amount;
		this.description = description;
		this.requester = requester;
		this.approver = approver;
		this.status = status;
		this.type = type;
	}
	public Reimbursement(int id,  double amount,ReimbursementType type,ReimbursementStatus status) {
		this.id = id;
		
		this.amount = amount;
		
		this.status = status;
		this.type = type;
	}
	public Reimbursement(Employee requester,double amount,ReimbursementType type,ReimbursementStatus status) {
	     this.amount = amount;
		
		this.status = status;
		this.type = type;
	}
	/*public Reimbursement((
			new Employee (rs.getString("U_FIRSTNAME"), rs.getString("U_LASTNAME")),
			
			d,
			
					new ReimbursementType(rs.getString("RT_TYPE")),
			
					new ReimbursementStatus(rs.getString("RS_STATUS"))
					)) {
		
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getRequested() {
		return requested;
	}

	public void setRequested(LocalDateTime requested) {
		this.requested = requested;
	}

	public LocalDateTime getResolved() {
		return resolved;
	}

	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getReceipt() {
		return receipt;
	}

	public void setReceipt(Object receipt) {
		this.receipt = receipt;
	}

	public Employee getRequester() {
		return requester;
	}

	public void setRequester(Employee requester) {
		this.requester = requester;
	}

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((approver == null) ? 0 : approver.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((requested == null) ? 0 : requested.hashCode());
		result = prime * result + ((requester == null) ? 0 : requester.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (approver == null) {
			if (other.approver != null)
				return false;
		} else if (!approver.equals(other.approver))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (requested == null) {
			if (other.requested != null)
				return false;
		} else if (!requested.equals(other.requested))
			return false;
		if (requester == null) {
			if (other.requester != null)
				return false;
		} else if (!requester.equals(other.requester))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/**
	 * It doesn't display the receipt.
	 */
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", requested=" + requested + ", resolved=" + resolved + ", amount=" + amount
				+ ", description=" + description + ", requester=" + requester + ", approver=" + approver + ", status="
				+ status + ", type=" + type + "]";
	}

	/**
	 * Natural order defined by the id.
	 */
	@Override
	public int compareTo(Reimbursement reimbursement) {
		return new Integer(this.id).compareTo(reimbursement.id);
	}
}
