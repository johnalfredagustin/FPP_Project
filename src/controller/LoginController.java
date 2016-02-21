package controller;

import java.sql.SQLException;

import model.*;

public class LoginController {

	private LoginDAL loginDAL = new LoginDAL();
	
	public boolean isValidLogin(String username, String password, String role) throws SQLException {
		
		return loginDAL.isValidLogin(username, password, role);
	}
	
}
