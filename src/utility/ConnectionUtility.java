package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

	public static final String URL = "jdbc:sqlserver://DESKTOP-8K96FP1;databaseName=odbCourseRegistration";
	public static final String DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String USER_NAME = "sqlJavaServiceAccount";
	public static final String PASSWORD = "sqlJavaServiceAccount";
	private static Connection conn = null;
	
	public ConnectionUtility() {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection() {
		
	}
}
