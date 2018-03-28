package com.revature.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/* Final utility class to obtain connections in a modular way */
public final class ConnectionUtil {

	private static Logger logger = Logger.getLogger(ConnectionUtil.class);

	/* Make Tomcat now which database driver to use */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) { 
			logger.warn("Exception thrown adding oracle driver.", e);
		}
	}

	/* Get connection to JDBC */
	public static Connection getConnection() throws SQLException {
		//String url = "jdbc:oracle:thin:@ers.ctqxu0ey9hj6.us-east-1.rds.amazonaws.com:1521:ORCL";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "REIMBURSEMENT_DB ";
		String password = "p4ssw0rd";
		logger.info("Connection successful");
		return DriverManager.getConnection(url, username, password);
		
		
	}
}
