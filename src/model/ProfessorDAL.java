package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import orm.CourseObject;
import orm.ProfessorObject;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;
import utility.ConnectionUtility;

public class ProfessorDAL {

	private String sqlComm;
	private Connection sqlConn;
	private Statement statement;
	private ResultSet resultSet = null;

	private StudentObject studentObject;
	private CourseObject courseObject;
	private ProfessorObject professorObject;

	private ArrayList<ProfessorStudentCourseMetricObject> profStudCourse = new ArrayList<ProfessorStudentCourseMetricObject>();
	private ArrayList<CourseObject> course = new ArrayList<CourseObject>();

	@SuppressWarnings("finally")
	public ProfessorObject getProfessorDetail(int professorID) throws SQLException {

		sqlConn = ConnectionUtility.getConnection();
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
				professorObject.setSSN(resultSet.getString("SSN"));

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

		sqlConn = ConnectionUtility.getConnection();
		Calendar calendar = professorObj.getDOB();

		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String strYear = String.valueOf(calendar.get(Calendar.YEAR));
		String strMonth = month < 10 ? ("0" + month).substring(0, 2) : String.valueOf(month);
		String strDay = day < 10 ? ("0" + day).substring(0, 2) : String.valueOf(day);
		String strDate = strYear + "-" + strMonth + "-" + strDay;

		int recordCount = 0;
		sqlComm = "EXEC dbo.spInsert_NewProfessorInfo " + professorObj.getID() + ", " + professorObj.getSSN() + ", '"
				+ professorObj.getFirstName() + "', '" + professorObj.getLastName() + "', '" + strDate + "', '"
				+ professorObj.getGender() + "', '" + professorObj.getNationality() + "', '" + professorObj.getEmail()
				+ "', '" + professorObj.getPassword() + "'";
		try {
			statement = sqlConn.createStatement();
			recordCount = statement.executeUpdate(sqlComm);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			statement.close();
			sqlConn.close();
			return recordCount != 0 ? true : false;
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<ProfessorStudentCourseMetricObject> getAllStudentCourseDetail(int professorID)
			throws SQLException {

		sqlConn = ConnectionUtility.getConnection();
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

				int courseid = resultSet.getInt("CourseID");
				courseObject = new CourseObject(courseid);
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
	public boolean submitOneStudentGrade(int professorID, int studentID, int courseID, double gradeNumber)
			throws SQLException {

		sqlConn = ConnectionUtility.getConnection();
		int recordCount = 0;
		sqlComm = "EXEC dbo.spUpdate_OneStudentGrade " + professorID + ", " + studentID + ", " + courseID + ", "
				+ gradeNumber;
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

	@SuppressWarnings("finally")
	public boolean submitAllStudentGrade(ArrayList<ProfessorStudentCourseMetricObject> profStudCourseList)
			throws SQLException {

		sqlConn = ConnectionUtility.getConnection();
		boolean result = false;

		try {
			for (ProfessorStudentCourseMetricObject obj : profStudCourseList) {

				sqlComm = "EXEC dbo.spUpdate_OneStudentGrade " + obj.getProfessor().getID() + ", "
						+ obj.getStudent().getID() + ", " + obj.getCourse().getCourseID() + ", " + obj.getGradeNumber();

				statement = sqlConn.createStatement();

				statement.executeUpdate(sqlComm);
			}
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			statement.close();
			sqlConn.close();
			return result;
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<CourseObject> getAllCourse() throws SQLException {

		sqlConn = ConnectionUtility.getConnection();
		sqlComm = "EXEC dbo.spSelect_AllAvaiableCoursesToProfessor";

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			while (resultSet.next()) {
				CourseObject temp = new CourseObject(resultSet.getInt("CourseID"));
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
	public int saveCourse(int professorID, int courseID) throws SQLException {

		sqlConn = ConnectionUtility.getConnection();
		sqlComm = "EXEC dbo.spInsert_RegisterCourseProfessor " + professorID + ", " + courseID;

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
