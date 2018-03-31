package com.revature.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.util.ConnectionUtil;

public class ReimbursementRepositoryJDBC implements ReimbursementRepository {
	private static Logger logger = Logger.getLogger(ReimbursementRepositoryJDBC.class);

	/*Singleton transformation of JDBC implementation object */
	private static ReimbursementRepositoryJDBC reimbursement=null;
	
	private ReimbursementRepositoryJDBC() {}
	
	public static ReimbursementRepositoryJDBC getInstance() {
		if(reimbursement == null) {
			reimbursement = new ReimbursementRepositoryJDBC();
		}
		
		return reimbursement;
	}


	@Override
	public boolean insert(Reimbursement reimbursement) {
logger.trace("Inserting reimbursement.");
		
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			int parameterIndex = 0;
			
			String sql = "INSERT INTO REIMBURSEMENT(R_ID,R_REQUESTED,R_RESOLVED,"
					+ "R_AMOUNT,R_DESCRIPTION,R_RECEIPT,EMPLOYEE_ID,MANAGER_ID,RS_ID,RT_ID)"
					+ " VALUES(NULL,?,NULL,?,?,NULL,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);								
								
			statement.setTimestamp(++parameterIndex, Timestamp.valueOf(reimbursement.getRequested()));
			//statement.setTimestamp(++parameterIndex, Timestamp.valueOf(reimbursement.getResolved()));
			statement.setDouble(++parameterIndex, reimbursement.getAmount());
			statement.setString(++parameterIndex, reimbursement.getDescription());
			statement.setInt(++parameterIndex, reimbursement.getRequester().getId());
			statement.setInt(++parameterIndex, reimbursement.getApprover().getId());
			statement.setInt(++parameterIndex, reimbursement.getStatus().getId());
			statement.setInt(++parameterIndex, reimbursement.getType().getId());

			if(statement.executeUpdate() > 0) {
				return true;	
			}
		} catch (SQLException e) {
			logger.error("Exception thrown while inserting new Reimbursement", e);
		}
		return false;
	}

	@Override
	public boolean update(Reimbursement reimbursement) {

		try(Connection connection = ConnectionUtil.getConnection()) {
			
			int parameterIndex = 0;
			
			String sql = "UPDATE REIMBURSEMENT SET R_RESOLVED =?,"
			+ "R_AMOUNT=?,R_DESCRIPTION=?,R_RECEIPT=NULL,RS_ID=?,RT_ID=? WHERE R_ID=?";

			PreparedStatement statement = connection.prepareStatement(sql);								
								
			statement.setTimestamp(++parameterIndex, Timestamp.valueOf(reimbursement.getResolved()));
			statement.setDouble(++parameterIndex, reimbursement.getAmount());
			statement.setString(++parameterIndex, reimbursement.getDescription());
			statement.setInt(++parameterIndex, reimbursement.getStatus().getId());
			statement.setInt(++parameterIndex, reimbursement.getType().getId());
			statement.setInt(++parameterIndex, reimbursement.getId());

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.warn("something went wrong", e);
		}
		
		return false;
	}

	@Override
	public Reimbursement select(int reimbursementId) {
		
		try (Connection connection = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM REIMBURSEMENT R, REIMBURSEMENT_STATUS RS, REIMBURSEMENT_TYPE RT\r\n" + 
					"WHERE R.RS_ID = RS.RS_ID AND R.RT_ID = RT.RT_ID AND R.R_ID =  ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, reimbursementId);
			ResultSet rs = statement.executeQuery();
	
			while(rs.next()) {
		return new Reimbursement(
						rs.getInt("R_ID"),
						rs.getDouble("R_AMOUNT"),
						new ReimbursementType(rs.getString("RT_TYPE")),
			                       new ReimbursementStatus (rs.getString("RS_STATUS")

                        		));
                     }
		} catch (SQLException e) {
			logger.error("SQLException on select(reimbursementId): "+e);
		}
		return new Reimbursement();
	}

	@Override
	public Set<Reimbursement> selectPending(int employeeId) {
		try (Connection connection = ConnectionUtil.getConnection()){
			HashSet<Reimbursement> ReimbSet = new HashSet<>();
			Reimbursement r=new Reimbursement();
			String sql = "SELECT * FROM USER_T U, REIMBURSEMENT R, REIMBURSEMENT_TYPE RT, "
					+ "REIMBURSEMENT_STATUS RS\r\n" + 
					"WHERE R.RS_ID = RS.RS_ID AND R.RT_ID = RT.RT_ID AND R.EMPLOYEE_ID =U.U_ID "
					+ "AND U_ID=? AND RS_STATUS='PENDING'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, employeeId);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				 r=new Reimbursement(
						new Employee (rs.getString("U_FIRSTNAME"), rs.getString("U_LASTNAME")),
						
						rs.getDouble("R_AMOUNT"),
						
								new ReimbursementType(rs.getString("RT_TYPE")),
						
								new ReimbursementStatus(rs.getString("RS_STATUS"))
								
							
								);
					
				
                        ReimbSet.add(r);
                        return ReimbSet;
				
				
			} 
		} catch (SQLException e) {
			logger.error("SQLException on select(employeeId): "+e);
		}
		return null;
	}

	@Override
	public Set<Reimbursement> selectFinalized(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Reimbursement> selectAllPending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Reimbursement> selectAllFinalized() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ReimbursementType> selectTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	/***************************Reimbursement Test*********************************/
	public static void main(String[] args) {
		ReimbursementRepositoryJDBC x=ReimbursementRepositoryJDBC.getInstance();
		/*//logger.trace(x.select(100));
		Reimbursement z= new Reimbursement();
		Reimbursement y= new Reimbursement(12,4000.00,new ReimbursementType("GAS"),
				new ReimbursementStatus("APPROVED"));
		
		logger.trace(x.select(12));*/

	
		
	}
	/***************************Reimbursement Test*********************************/

}
