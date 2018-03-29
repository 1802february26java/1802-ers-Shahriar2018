package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.EmployeeRole;
import com.revature.model.EmployeeToken;
import com.revature.util.ConnectionUtil;

public class EmployeeRepositoryJDBC implements EmployeeRepository{
	private static Logger logger = Logger.getLogger(EmployeeRepositoryJDBC.class);


	/*Singleton transformation of JDBC implementation object */
	private static EmployeeRepositoryJDBC customerRepository=null;

	private EmployeeRepositoryJDBC() {}

	public static EmployeeRepositoryJDBC getInstance() {
		if(customerRepository == null) {
			customerRepository = new EmployeeRepositoryJDBC();
		}

		return customerRepository;
	}

	@Override
	public boolean insert(Employee employee) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "INSERT INTO USER_T VALUES(null,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, employee.getFirstName().toUpperCase());
			statement.setString(++statementIndex, employee.getLastName().toUpperCase());
			statement.setString(++statementIndex, employee.getUsername().toLowerCase());
			statement.setString(++statementIndex, employee.getPassword());
			statement.setString(++statementIndex, employee.getEmail() );
			statement.setInt(++statementIndex, employee.getEmployeeRole().getId());;

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Exception Inserting a new customer", e);
		}
		return false;
	}

	@Override
	public boolean update(Employee employee) {
		try(Connection connection = ConnectionUtil.getConnection()) {

			int statementIndex = 0;

			String sql = "UPDATE USER_T SET U_FIRSTNAME = ?, U_LASTNAME = ?,"
					+ " U_PASSWORD = ?, U_EMAIL = ? WHERE U_USERNAME = ?";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(++statementIndex, employee.getFirstName());
			statement.setString(++statementIndex, employee.getLastName());
			statement.setString(++statementIndex, employee.getPassword());
			statement.setString(++statementIndex, employee.getEmail());
			statement.setString(++statementIndex,employee.getUsername().toLowerCase());

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("Update wasn't succefull!!!", e);
		}
		return false;
	}

	@Override
	public Employee select(int employeeId) {
		try(Connection connection = ConnectionUtil.getConnection()) {

			int parameterIndex = 0;

			String sql = "SELECT * FROM USER_T WHERE U_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(++parameterIndex, employeeId);

			ResultSet rs = statement.executeQuery();
			//logger.info("\n moving on to sql 1 \n");
			while(rs.next()) {
				int id = rs.getInt("U_ID");
				String firstName = rs.getString("U_FIRSTNAME");
				String lastName = rs.getString("U_LASTNAME");
				String username = rs.getString("U_USERNAME");
				String password = rs.getString("U_PASSWORD");
				String email = rs.getString("U_EMAIL");
				String role = null;
				int roleId = rs.getInt("UR_ID");
				String sql2 = "SELECT UR_TYPE FROM USER_ROLE WHERE UR_ID =?";
				PreparedStatement statement2 = connection.prepareStatement(sql2);

				statement2.setInt(1, roleId);
				ResultSet rs2 = statement2.executeQuery();
				rs2.next();
				//logger.info("\n moving on to sql 2 \n");
				role = rs2.getString("UR_TYPE");
				//logger.info("\n moving on to sql 2 \n");
				EmployeeRole employeeRole = new EmployeeRole(roleId, role);
				return new Employee(id, firstName, lastName, username, password, email, employeeRole);

			}
		}
		catch (SQLException e) {
			logger.error("Exception selecting employee by employeeId.", e);
		}
		return new Employee();

	}

	@Override
	public Employee select(String username) {
		Employee e1=new Employee();
		try(Connection connection = ConnectionUtil.getConnection()) {

			int parameterIndex = 0;

			String sql = "SELECT * FROM USER_T WHERE U_USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(++parameterIndex, username);

			ResultSet rs = statement.executeQuery();
			//logger.info("\n moving on to sql 1 \n");
			if(rs.next()) {
				int id = rs.getInt("U_ID");
				String firstName = rs.getString("U_FIRSTNAME");
				String lastName = rs.getString("U_LASTNAME");
				username = rs.getString("U_USERNAME");
				String password = rs.getString("U_PASSWORD");
				String email = rs.getString("U_EMAIL");
				String role = null;
				int roleId = rs.getInt("UR_ID");
				String sql2 = "SELECT UR_TYPE FROM USER_ROLE WHERE UR_ID =?";
				PreparedStatement statement2 = connection.prepareStatement(sql2);

				statement2.setInt(1, roleId);
				ResultSet rs2 = statement2.executeQuery();
				rs2.next();
				//logger.info("\n moving on to sql 2 \n");
				role = rs2.getString("UR_TYPE");
				//logger.info("\n moving on to sql 2 \n");
				EmployeeRole employeeRole = new EmployeeRole(roleId, role);
				logger.trace("About to return");
				return new Employee(id, firstName, lastName, username, password, email, employeeRole);

			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
			logger.error("Exception selecting employee by employeeId.", e);
		}
	return e1;
	}

	@Override
	public Set<Employee> selectAll() {
		HashSet<Employee> empSet = new HashSet<>();
		try(Connection connection = ConnectionUtil.getConnection()) {	
			String sql = "SELECT * FROM USER_T ";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			logger.info("\n moving on to sql 1 \n");
			while(rs.next()) {
				int id = rs.getInt("U_ID");
				String firstName = rs.getString("U_FIRSTNAME");
				String lastName = rs.getString("U_LASTNAME");
				String username = rs.getString("U_USERNAME");
				String password = rs.getString("U_PASSWORD");
				String email = rs.getString("U_EMAIL");
				String role = null;
				int roleId = rs.getInt("UR_ID");
				String sql2 = "SELECT UR_TYPE FROM USER_ROLE WHERE UR_ID =?";
				PreparedStatement statement2 = connection.prepareStatement(sql2);

				statement2.setInt(1, roleId);
				ResultSet rs2 = statement2.executeQuery();
				while(rs2.next()) {
					//logger.info("\n moving on to sql 2 \n");
					role = rs2.getString("UR_TYPE");
					logger.info("\n moving on to sql 2 \n");

				}


				EmployeeRole employeeRole = new EmployeeRole(roleId, role);
				Employee emp=new Employee(id, firstName, lastName, username, password, email, employeeRole);
				empSet.add(emp);

			}
		}
		catch (SQLException e) {
			logger.error("Exception selecting employee by employeeId.", e);
		}
		return empSet;
	}

	@Override
	public String getPasswordHash(Employee employee) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String sql = "SELECT GET_HASH(?) AS HASH FROM DUAL";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(++statementIndex, employee.getPassword());
			ResultSet rs = statement.executeQuery();

			if(rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException e) {
			logger.warn("Exception getting employee hash", e);
		} 
		return null;
	}

	@Override
	public boolean insertEmployeeToken(EmployeeToken employeeToken) {
		if(employeeToken == null) {
			return false;
		}
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO PASSWORD_RECOVERY VALUES(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			int parameterIndex = 0;
			statement.setInt(++parameterIndex, employeeToken.getId());
			statement.setString(++parameterIndex, employeeToken.getToken());
			statement.setTimestamp(++parameterIndex, Timestamp.valueOf(employeeToken.getCreationDate()));
			statement.setInt(++parameterIndex, employeeToken.getRequester().getId());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.error("SQLException in insertEmployeeToken(EmployeeToken) " + e);
		}
		return false;
	}

	@Override
	public boolean deleteEmployeeToken(EmployeeToken employeeToken) {
		if(employeeToken == null) {
			return false;
		}
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM PASSWORD_RECOVERY WHERE PR_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			int parameterIndex = 0;
			statement.setInt(++parameterIndex, employeeToken.getId());

			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			logger.error("SQLException in insertEmployeeToken(EmployeeToken) " + e);
		}
		return false;
	}

	@Override
	public EmployeeToken selectEmployeeToken(EmployeeToken employeeToken) {
		// Assuming we can't just do "return employeeToken; ...
		if(employeeToken == null) {
			return null;
		}
		try(Connection connection = ConnectionUtil.getConnection()){
			int id = employeeToken.getId();
			String sql = "SELECT * FROM PASSWORD_RECOVERY WHERE PR_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				String token = result.getString("PR_TOKEN");
				LocalDateTime creationDate = result.getTimestamp("PR_TIME").toLocalDateTime();
				int requesterId = result.getInt("U_ID");
				Employee requester = select(requesterId);

				if(requester != null) {
					return new EmployeeToken(id, token, creationDate, requester);
				}
			}
		} catch (SQLException e) {
			logger.warn("SQLException in insertEmployeeToken(EmployeeToken) " + e);
		}
		return null;
	}

	/*********************************Test Methods************************************************/
	public static void main(String[] args) {
		EmployeeRepositoryJDBC x=EmployeeRepositoryJDBC.getInstance();
		/*Test Insert()*/
		Employee emp = new Employee(5,"fahim","glows","fahimglows","2018","mohammed2018@gmail.com",new EmployeeRole(1,"EMPLOYEE"));
		//Employee emp2 = new Employee(50,"shahriar","glows","shahriarglows","2018","mohammed2018@gmail.com",new EmployeeRole(2,"Manager"));
		//logger.trace(x.insert(emp));
		//logger.trace(x.insert(emp2));

		/*Test update()*/
		//Employee emp2 = new Employee(50,"fahim","mohammed","shahriarglows","2018","mohammed2018@gmail.com",new EmployeeRole(2,"Manager"));
		//logger.trace(x.update(emp2));

		/*Test select(int employeeId)*/ 
		//logger.trace(x.select(8));

		/*Test select(String username)*/ 
		//logger.trace(x.select("fahimglows"));

		/* Test Set<Employee> selectAll()*/
		//		Set<Employee> empSet2=x.selectAll(); 
		//        for (Employee temp : empSet2) {
		//	        System.out.println(temp+"\n");}

		/* Test getPasswordHash*/
		logger.trace(x.getPasswordHash(emp));

		/*********************************TEST Methods************************************************/
	}

	@Override
	public int CheckRole(int id) {


		return 0;
	}}
