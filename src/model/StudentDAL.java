package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import orm.CourseObject;
import orm.PersonObject;
import orm.StudentObject;
import utility.ConnectionUtility;

public class StudentDAL {

	private String sqlComm;
	private Connection sqlConn = ConnectionUtility.getConnection();
	private Statement statement;
	private ResultSet resultSet = null;
	private ArrayList<PersonObject> personArrayList = new ArrayList<PersonObject>();
	private ArrayList<CourseObject> course = new ArrayList<CourseObject>();
	private StudentObject studentObject;
	private PersonObject personObject;
	private int recordCount = 0;

	@SuppressWarnings("finally")
	public ArrayList<PersonObject> getAllStudentDetails(int studentID) throws SQLException {

		sqlComm = "EXEC dbo.spSelect_StudentInfo " + studentID;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			while (resultSet.next()) {

				String strDate = resultSet.getDate("DOB").toString();

				PersonObject personObject = new StudentObject(resultSet.getString("FirstName"),
						resultSet.getString("LastName"),
						new GregorianCalendar(Integer.parseInt(strDate.substring(0, 3)),
								Integer.parseInt(strDate.substring(5, 6)), Integer.parseInt(strDate.substring(8))));

				personObject.setID(resultSet.getInt("StudentID"));

				personArrayList.add(studentObject);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return personArrayList;
		}
	}

	@SuppressWarnings("finally")
	public PersonObject getStudentDetail(int studentID) throws SQLException {

		sqlComm = "EXEC dbo.spSelect_StudentInfo " + studentID;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			while (resultSet.next()) {

				String strDate = resultSet.getDate("DOB").toString();

				personObject = new StudentObject(resultSet.getString("FirstName"), resultSet.getString("LastName"),
						new GregorianCalendar(Integer.parseInt(strDate.substring(0, 3)),
								Integer.parseInt(strDate.substring(5, 6)), Integer.parseInt(strDate.substring(8))));

				personObject.setID(resultSet.getInt("StudentID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return personObject;
		}
	}

	@SuppressWarnings("finally")
	public boolean signup(PersonObject personObj) throws SQLException {

		sqlComm = "EXEC dbo.spInsert_NewStudentInfo " + personObj + ", '" + studentObject.getFirstName() + "', '"

				+ studentObject.getLastName() + "', '" + studentObject.getDOB() + "', '" + studentObject.getGender()
				+ "','" + studentObject.getNationality() + "',NULL,'" + studentObject.getPassword() + "'";

		try {
			statement = sqlConn.createStatement();
			recordCount = statement.executeUpdate(sqlComm);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return recordCount > 0 ? true : false;
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<CourseObject> getAllCourse(int studentID) throws SQLException {

		sqlComm = "EXEC dbo.spSelect_AllAvaiableCoursesToStudent "+studentID;

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
	
	public void saveCourse(int studentID, String courseCode) throws SQLException {

		sqlComm = "EXEC dbo.spSave_SelectedCourseForStudent " +studentID +" "+ courseCode;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
		}
	}
}
