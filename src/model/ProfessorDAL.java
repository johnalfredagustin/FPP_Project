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

public class ProfessorDAL {

	private String sqlComm;
	private Connection sqlConn = ConnectionUtility.getConnection();
	private Statement statement;
	private ResultSet resultSet = null;
	private ArrayList<ProfessorStudentCourseMetricObject> profStudCourse = new ArrayList<ProfessorStudentCourseMetricObject>();
	private StudentObject studentObject;
	private CourseObject courseObject;
	private ProfessorObject professorObject;

	@SuppressWarnings("finally")
	public ProfessorObject getProfessorDetail(int professorID) throws SQLException {

		sqlComm = "EXEC dbo.spSelect_ProfessorInfo " + professorID;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			while (resultSet.next()) {

				String strDOB = resultSet.getDate("DOB").toString();

				int yearDOB = Integer.parseInt(strDOB.substring(0, 3));
				int monthDOB = Integer.parseInt(strDOB.substring(5, 6));
				int dayDOB = Integer.parseInt(strDOB.substring(8));

				professorObject = new ProfessorObject(resultSet.getString("FirstName"), resultSet.getString("LastName"),
						new GregorianCalendar(yearDOB, monthDOB, dayDOB));

				professorObject.setID(resultSet.getInt("ProfessorID"));
				professorObject.setGender(resultSet.getString("Gender"));
				professorObject.setNationality(resultSet.getString("Nationality"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return professorObject;
		}
	}

	@SuppressWarnings("finally")
	public boolean signup(ProfessorObject professorObj) throws SQLException {

		int recordCount = 0;
		sqlComm = "EXEC dbo.spInsert_NewProfessorInfo " + professorObj.getID() + ", '" + professorObj.getFirstName()
				+ "', '" + professorObj.getLastName() + "', '" + professorObj.getDOB() + "', '"
				+ professorObj.getGender() + "','" + professorObj.getNationality() + "', '" + professorObj.getSSN()
				+ "','" + professorObj.getPassword() + "'";
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

//	@SuppressWarnings("finally")
	public ArrayList<ProfessorStudentCourseMetricObject> getAllStudentCourseDetail(int professorID)
			throws SQLException {

		sqlComm = "EXEC dbo.spSelect_ProfessorStudentCourseMetric " + professorID;

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			while (resultSet.next()) {

				String strDOB = resultSet.getDate("DOB").toString();

				int yearDOB = Integer.parseInt(strDOB.substring(0, 3));
				int monthDOB = Integer.parseInt(strDOB.substring(5, 6));
				int dayDOB = Integer.parseInt(strDOB.substring(8));

				studentObject = new StudentObject(resultSet.getString("FirstName"), resultSet.getString("LastName"),
						new GregorianCalendar(yearDOB, monthDOB, dayDOB));
				studentObject.setID(resultSet.getInt("StudentID"));

				professorObject = new ProfessorObject(professorID);

				courseObject = new CourseObject();
				courseObject.setCourseID(resultSet.getInt("CourseID"));
				courseObject.setCourseCode(resultSet.getString("CourseCode"));
				courseObject.setCourseDesc(resultSet.getString("CourseDescription"));
				courseObject.setCourseName(resultSet.getString("CourseName"));

				ProfessorStudentCourseMetricObject temp = new ProfessorStudentCourseMetricObject(studentObject,
						professorObject, courseObject);

				temp.setGradeNumber(resultSet.getDouble("GradeNumber"));
				temp.setGradeLetter(resultSet.getString("GradeLetter"));

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
	public boolean submitOneStudentGrade(int professorID, int studentID, int courseID, double gradeNumber) throws SQLException {

		int recordCount = 0;
		sqlComm = "EXEC dbo.spUpdate_OneStudentGrade " + professorID + ", " + studentID + ", " + courseID + ", " + gradeNumber;
		try {
			statement = sqlConn.createStatement();
			recordCount = statement.executeUpdate(sqlComm);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			statement.close();
			sqlConn.close();
			return recordCount > 0 ? true : false;
		}
	}
	
}
