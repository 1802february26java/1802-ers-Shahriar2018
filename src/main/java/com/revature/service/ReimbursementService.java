package com.revature.service;

import java.util.Set;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;

/**
 * The ReimbursementService performs operations on the ReimbursementRepository
 * to provide persistency of Reimbursement requests or to obtain a single or
 * multiple reimbursement requests.
 * 
 * It should also contain business logic specifically regarding to reimbursements.
 * 
 * @author Revature LLC
 */
public interface ReimbursementService {
	
	/**
	 * Creates a reimbursement request in the database.
	 * 
	 * Input validation can be provided here.
	 * 
	 * It returns true if the reimbursement was stored successfully.
	 */
	public boolean submitRequest(Reimbursement reimbursement);
	
	/**
	 * Updates a reimbursement request status in the database.
	 * 
	 * It returns true if the reimbursement was updated successfully.
	 * 
	 * An email should be sent to the employee stating that a specific
	 * request got finalized.
	 */
	public boolean finalizeRequest(Reimbursement reimbursement);
	
	/**
	 * Returns a single reimbursement request information.
	 * 
	 * The parameter should at least contain the reimbursement id.
	 */
	public Reimbursement getSingleRequest(Reimbursement reimbursement);
	
	/**
	 * Returns a set of PENDING requests of a specific employee.
	 * 
	 * The parameter should at least contain the employee id.
	 */
	public Set<Reimbursement> getUserPendingRequests(Employee employee);
	
	/**
	 * Returns a set of APPROVED and/or DECLINED requests of a specific employee.
	 * 
	 * The parameter should at least contain the employee id.
	 */
	public Set<Reimbursement> getUserFinalizedRequests(Employee employee);
	
	/**
	 * Returns a set of all PENDING reimbursement requests.
	 */
	public Set<Reimbursement> getAllPendingRequests();
	
	/**
	 * Returns a set of all APPROVED or DECLINED reimbursement requests.
	 */
	public Set<Reimbursement> getAllResolvedRequests();
	
	/**
	 * Returns a set of all reimbursement types.
	 */
	public Set<ReimbursementType> getReimbursementTypes();
}
