package model;

import java.sql.*;
import java.util.ArrayList;

import orm.*;
import utility.*;

public class CourseDAL {

	private String sqlComm;
	private Connection sqlConn = ConnectionUtility.getConnection();
	private Statement statement;
	private ResultSet resultSet = null;
	private ArrayList<CourseObject> course = new ArrayList<CourseObject>();
	
	@SuppressWarnings("finally")
	public ArrayList<CourseObject> getAllCourse() throws SQLException {

		sqlComm = "EXEC dbo.spSelect_AllAvaiableCoursesToStudent 984946";

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			int i = 0;
			
			while (resultSet.next()){
				CourseObject temp = new CourseObject();
				temp.setCourseDesc(resultSet.getString("CourseDescription"));
				temp.setCourseName(resultSet.getString("CourseName"));
				temp.setCourseCode(resultSet.getString("CourseCode"));
				course.add(temp);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return course;
		}
	}
	
	
	@SuppressWarnings("finally")
	public ArrayList<CourseObject> getAllCourseWithProfessor() throws SQLException {

		sqlComm = "EXEC dbo.spSelect_AllAvaiableCoursesToStudent 984946";

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			int i = 0;
			
			while (resultSet.next()){
				CourseObject temp = new CourseObject();
				temp.setCourseDesc(resultSet.getString("CourseDescription"));
				temp.setCourseName(resultSet.getString("CourseName"));
				temp.setCourseCode(resultSet.getString("CourseCode"));
				course.add(temp);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return course;
		}
	}
}

