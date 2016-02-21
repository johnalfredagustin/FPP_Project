package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import orm.CourseObject;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;
import utility.ConnectionUtility;

public class StudentDAL {

	private String sqlComm;
	private Connection sqlConn = ConnectionUtility.getConnection();
	private Statement statement;
	private ResultSet resultSet = null;
	private ArrayList<StudentObject> students = new ArrayList<StudentObject>();
	private StudentObject studentObject;
	
	@SuppressWarnings("finally")
	public ArrayList<StudentObject> getAllStudentDetails(int studentID) throws SQLException {

		sqlComm = "EXEC dbo.spSelect_StudentInfo " + studentID;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			int i = 0;
			while (resultSet.next()){
				
				String strDate = resultSet.getDate("DOB").toString();
				
				StudentObject studentObject = new StudentObject(
						resultSet.getString("FirstName"),
						resultSet.getString("LastName"),
						new GregorianCalendar(Integer.parseInt(strDate.substring(0, 3)), 
								Integer.parseInt(strDate.substring(5, 6)), 
								Integer.parseInt(strDate.substring(8))));
				
				students.get(i).setStudentID(resultSet.getInt("StudentID"));
				students.get(i).setFirstName(resultSet.getString("FirstName"));
				students.get(i).setLastName(resultSet.getString("LastName"));
				
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return students;
		}
	}
	
	@SuppressWarnings("finally")
	public StudentObject getStudentDetail(int studentID) throws SQLException {

		sqlComm = "EXEC dbo.spSelect_StudentInfo " + studentID;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			int i = 0;
			while (resultSet.next()){
				
				String strDate = resultSet.getDate("DOB").toString();
				
				studentObject = new StudentObject(
						resultSet.getString("FirstName"),
						resultSet.getString("LastName"),
						new GregorianCalendar(Integer.parseInt(strDate.substring(0, 3)), 
								Integer.parseInt(strDate.substring(5, 6)), 
								Integer.parseInt(strDate.substring(8))));
				
				studentObject.setStudentID(resultSet.getInt("StudentID"));
				
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return studentObject;
		}
	}
}
