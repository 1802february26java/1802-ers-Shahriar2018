package com.revature.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.controller.ReimbursementController;
import com.revature.controller.ReimbursementControllerAlpha;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;
import com.revature.repository.ReimbursementRepositoryJDBC;

public class ReimbursementServiceAlpha implements ReimbursementService {
    private static ReimbursementServiceAlpha reimbursementService = new ReimbursementServiceAlpha();
	private static Logger logger = Logger.getLogger(ReimbursementServiceAlpha.class);


private ReimbursementServiceAlpha() { }
	
	public static ReimbursementServiceAlpha getInstance() {
		return reimbursementService;
	}

	@Override
	public boolean submitRequest(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finalizeRequest(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reimbursement getSingleRequest(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return ReimbursementRepositoryJDBC.getInstance().select(reimbursement.getId());
	}

	@Override
	public Set<Reimbursement> getUserPendingRequests(Employee employee) {
		logger.trace("employeed Id is :"+employee.getId());
		
		return ReimbursementRepositoryJDBC.getInstance().selectPending(employee.getId());
	}

	@Override
	public Set<Reimbursement> getUserFinalizedRequests(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Reimbursement> getAllPendingRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Reimbursement> getAllResolvedRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ReimbursementType> getReimbursementTypes() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
