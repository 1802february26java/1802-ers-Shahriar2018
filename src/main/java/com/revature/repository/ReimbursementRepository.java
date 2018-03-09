package com.revature.repository;

import java.util.Set;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;

/**
 * The ReimbursementRepository performs operations directly to the database utilizing
 * a data access library or framework, specifically on the REIMBURSEMENT, REIMBURSEMENT_STATUS,
 * REIMBURSEMENT_TYPE and in some cases, the USER table (read only for the user part).
 * 
 * For the scope of this project, there is only going to be one JDBC implementation.
 * The idea of having an interface is that in the future you might want to implement
 * it with Hibernate, Mongo, or any other data access technology.
 * 
 * It should only contain data access logic regarding to reimbursements.
 * 
 * @author Revature LLC
 */
public interface ReimbursementRepository {
	
	/**
	 * Inserts a reimbursement request in the database.
	 * 
	 * It returns true if the reimbursement was stored successfully.
	 */
	public boolean insert(Reimbursement reimbursement);
	
	/**
	 * Updates a reimbursement request status in the database.
	 * 
	 * It returns true if the reimbursement was updated successfully.
	 */
	public boolean update(Reimbursement reimbursement);
	
	/**
	 * Returns a single reimbursement request.
	 * 
	 * A join should be performed with respective reimbursement status and type.
	 */
	public Reimbursement select(int reimbursementId);
	
	/**
	 * Returns a set of PENDING requests of a specific employee.
	 * 
	 * A join should be performed with respective reimbursement status and type.
	 */
	public Set<Reimbursement> selectPending(int employeeId);
	
	/**
	 * Returns a set of APPROVED or DECLINED requests of a specific employee.
	 * 
	 * A join should be performed with respective reimbursement status and type.
	 */
	public Set<Reimbursement> selectFinalized(int employeeId);
	
	/**
	 * Returns a set of all PENDING reimbursement requests.
	 * 
	 * A join should be performed with respective reimbursement status, type and employee.
	 */
	public Set<Reimbursement> selectAllPending();
	
	/**
	 * Returns a set of all APPROVED or DECLINED reimbursement requests.
	 * 
	 * A join should be performed with respective reimbursement status, type and employee.
	 */
	public Set<Reimbursement> selectAllFinalized();
	
	/**
	 * Returns a set of all reimbursement types.
	 */
	public Set<ReimbursementType> selectTypes();
}
