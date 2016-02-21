package model;

import java.sql.*;

import orm.*;
import utility.*;

public class LoginDAL {

	private String sqlComm;
	private Connection sqlConn;
	private Statement statement;
	private ResultSet resultSet = null;
	
	private LoginObject loginObject = new LoginObject();

	@SuppressWarnings("finally")
	public boolean isValidLogin(String username, String password, String role) throws SQLException {
		
		boolean isValidLogin = false;
		
		sqlConn = ConnectionUtility.getConnection();
		sqlComm = "EXEC dbo.spUtil_Login " + username + ", '" + password + "', '" + role + "'";

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			loginObject.setUsername(username);
			loginObject.setPassword(password);

			if (!resultSet.next()) {
				isValidLogin = false;
			} else
				isValidLogin = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return isValidLogin;
		}
	}
}
