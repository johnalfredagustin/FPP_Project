package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.CourseDAL;
import model.StudentDAL;
import orm.CourseObject;
import orm.PersonObject;
import orm.StudentObject;

public class StudentController {

	private StudentDAL student = new StudentDAL();
	
	public PersonObject getStudentDetail(int studentID) throws SQLException {
		
		return student.getStudentDetail(studentID);
	}
	
	public boolean signUp(PersonObject personObj) throws SQLException {
		return student.signup(personObj);
	}
	
	public ArrayList<CourseObject> getAllCourse(int studentID) throws SQLException {
		return student.getAllCourse(studentID);
	}
	
	public void saveCourse(int studentID, String courseCode) throws SQLException {
		student.saveCourse(studentID, courseCode);
	}
}
