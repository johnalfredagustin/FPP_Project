package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.CourseDAL;
import model.LoginDAL;
import model.StudentDAL;
import orm.CourseObject;
import orm.StudentObject;

public class StudentController {

	private StudentDAL student = new StudentDAL();
	
	public StudentObject getStudentDetail(int studentID) throws SQLException {
		
		return student.getStudentDetail(studentID);
	}
}
