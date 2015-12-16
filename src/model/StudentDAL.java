package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import orm.CourseObject;
import orm.ProfessorObject;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;
import utility.ConnectionUtility;

public class StudentDAL {

	private String sqlComm;
	private Connection sqlConn = ConnectionUtility.getConnection();
	private Statement statement;
	private ResultSet resultSet = null;
	private ArrayList<ProfessorStudentCourseMetricObject> profStudCourse = new ArrayList<ProfessorStudentCourseMetricObject>();
	private StudentObject studentObject;
	private CourseObject courseObject;
	private ProfessorObject professorObject;

	@SuppressWarnings("finally")
	public StudentObject getStudentDetail(int studentID) throws SQLException {

		sqlComm = "EXEC dbo.spSelect_StudentInfo " + studentID;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			while (resultSet.next()) {

				String strDOB = resultSet.getDate("DOB").toString();
				String strEntry = resultSet.getDate("Entry").toString();

				int yearDOB = Integer.parseInt(strDOB.substring(0, 3));
				int monthDOB = Integer.parseInt(strDOB.substring(5, 6));
				int dayDOB = Integer.parseInt(strDOB.substring(8));

				int yearEntry = Integer.parseInt(strEntry.substring(0, 3));
				int monthEntry = Integer.parseInt(strEntry.substring(5, 6));
				int dayEntry = Integer.parseInt(strEntry.substring(8));

				studentObject = new StudentObject(resultSet.getString("FirstName"), resultSet.getString("LastName"),
						new GregorianCalendar(yearDOB, monthDOB, dayDOB));

				studentObject.setGender(resultSet.getString("Gender"));
				studentObject.setEntryDate(new GregorianCalendar(yearEntry, monthEntry, dayEntry));
				studentObject.setID(resultSet.getInt("StudentID"));
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

	@SuppressWarnings("finally")
	public boolean signup(StudentObject studentObj) throws SQLException {

		int recordCount = 0;
		sqlComm = "EXEC dbo.spInsert_NewStudentInfo " + studentObj.getID() + ", '" + studentObj.getFirstName() + "', '"

				+ studentObj.getLastName() + "', '" + studentObj.getDOB() + "', '" + studentObj.getGender() + "','"
				+ studentObj.getNationality() + "',NULL,'" + studentObj.getPassword() + "'";

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
	public ArrayList<ProfessorStudentCourseMetricObject> getAllCourse(int studentID) throws SQLException {

		sqlComm = "EXEC dbo.spSelect_AllAvaiableCoursesToStudent " + studentID;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);
			
			while (resultSet.next()) {

				String strDOB = resultSet.getDate("DOB").toString();

				int yearDOB = Integer.parseInt(strDOB.substring(0, 3));
				int monthDOB = Integer.parseInt(strDOB.substring(5, 6));
				int dayDOB = Integer.parseInt(strDOB.substring(8));

				studentObject = new StudentObject(studentID);

				professorObject = new ProfessorObject(resultSet.getString("ProfessorFirstName"),
						resultSet.getString("ProfessorLastName"), new GregorianCalendar(yearDOB, monthDOB, dayDOB));
				professorObject.setID(resultSet.getInt("ProfessorID"));

				courseObject = new CourseObject();
				courseObject.setCourseID(resultSet.getInt("CourseID"));
				courseObject.setCourseCode(resultSet.getString("CourseCode"));
				courseObject.setCourseDesc(resultSet.getString("CourseDescription"));
				courseObject.setCourseName(resultSet.getString("CourseName"));

				ProfessorStudentCourseMetricObject temp = new ProfessorStudentCourseMetricObject(studentObject,
						professorObject, courseObject);

				profStudCourse.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return profStudCourse;
		}
	}
	
	@SuppressWarnings("finally")
	public int saveCourse(int studentID, String courseCode, int professorID) throws SQLException {

		sqlComm = "EXEC dbo.spInsert_RegisterCourseStudent " +studentID +" "+ courseCode+ " "+professorID;
		int rows = 0;
		try {
			statement = sqlConn.createStatement();
			 rows = statement.executeUpdate(sqlComm);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			statement.close();
			sqlConn.close();
			return rows;
		}
	}

}
