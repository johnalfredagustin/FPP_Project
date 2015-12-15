package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.StudentDAL;
import orm.CourseObject;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;

public class StudentController {

	private StudentDAL studentDAL = new StudentDAL();
	
	public StudentObject getStudentDetail(int studentID) throws SQLException {
		
		return studentDAL.getStudentDetail(studentID);
	}
	
	public boolean signUp(StudentObject studentObj) throws SQLException {
		return studentDAL.signup(studentObj);
	}
	
	public ArrayList<ProfessorStudentCourseMetricObject> getAllCourse(int studentID) throws SQLException {
		return studentDAL.getAllCourse(studentID);
	}
	
	public void saveCourse(int studentID, String courseCode) throws SQLException {
		studentDAL.saveCourse(studentID, courseCode);
	}
}
