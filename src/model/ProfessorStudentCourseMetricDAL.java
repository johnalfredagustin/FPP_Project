package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.sun.org.apache.regexp.internal.REUtil;

import orm.CourseObject;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;
import utility.ConnectionUtility;

public class ProfessorStudentCourseMetricDAL {

	private String sqlComm;
	private Connection sqlConn = ConnectionUtility.getConnection();
	private Statement statement;
	private ResultSet resultSet = null;
	private ArrayList<ProfessorStudentCourseMetricObject> studentCourse = new ArrayList<ProfessorStudentCourseMetricObject>();
	private StudentDAL studentDAL = new StudentDAL();

	@SuppressWarnings("finally")
	public ArrayList<ProfessorStudentCourseMetricObject> getAllStudentCourseDetail() throws SQLException {

		sqlComm = "EXEC dbo.spSelect_ProfessorStudentCourseMetric 10001";

		try {
			statement = sqlConn.createStatement();
			resultSet = statement.executeQuery(sqlComm);

			while (resultSet.next()) {
				
				String strDate = resultSet.getDate("DOB").toString();

				StudentObject studentObject = new StudentObject(
						resultSet.getString("FirstName"),
						resultSet.getString("LastName"),
						new GregorianCalendar(Integer.parseInt(strDate.substring(0, 3)), 
								Integer.parseInt(strDate.substring(5, 6)), 
								Integer.parseInt(strDate.substring(8))));
				
				ProfessorStudentCourseMetricObject temp = new ProfessorStudentCourseMetricObject();
				
				temp.setStudent(studentObject);
				
				CourseObject courseObj = new CourseObject();
				courseObj.setCourseCode(resultSet.getString("CourseCode"));
				courseObj.setCourseDesc(resultSet.getString("CourseDescription"));
				courseObj.setCourseName(resultSet.getString("CourseName"));
				
				temp.setCourse(courseObj);
				
				temp.setGradeNumber(resultSet.getDouble("GradeNumber"));
				temp.setGradeLetter(resultSet.getString("GradeLetter"));

				studentCourse.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			sqlConn.close();
			return studentCourse;
		}
	}
}
